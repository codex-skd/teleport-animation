package com.skd.teleport_animation;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;

public final class WaystonesTeleportHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("teleport_animation");
    private static final String PLAYER_WAYSTONE_MANAGER_CLASS = "net.blay09.mods.waystones.core.PlayerWaystoneManager";
    private static final String WAYSTONE_TELEPORT_CONTEXT_CLASS = "net.blay09.mods.waystones.api.IWaystoneTeleportContext";

    private WaystonesTeleportHandler() {
    }

    public static Object delayTeleportContext(Object context) {
        ResourceKey<Level> finalTargetDimension;
        Vec3 finalTargetFeet;
        boolean scheduled;
        Entity entity = readEntity(context);
        if (!(entity instanceof ServerPlayer player)) {
            return null;
        }
        Object destination = invokeNoArg(context, "getDestination");
        Vec3 targetFeet = readDestinationLocation(destination);
        ResourceKey<Level> targetDimension = readDestinationDimension(destination);
        // Always try to resolve the target waystone (not only when the destination has no location),
        // so the destination-chunk force-load below can run for normal waystone teleports too.
        Object targetWaystone = invokeNoArg(context, "getTargetWaystone");
        if (targetFeet == null) {
            targetFeet = readWaystoneFeet(targetWaystone);
        }
        if (targetDimension == null) {
            targetDimension = readWaystoneDimension(targetWaystone);
        }
        if (targetFeet == null) {
            return null;
        }
        if (targetDimension == null) {
            targetDimension = ((ServerLevel)player.level()).dimension();
        }
        // Optional: the destination waystone's block pos, used only to force-load its chunks before
        // teleporting. If we cannot resolve it, still schedule the transition — just without the
        // pre-load (matches the pre-1.1.0 behaviour); it is not a reason to skip the animation.
        BlockPos targetWaystonePos = readWaystonePos(targetWaystone);
        ResourceKey<Level> finalTargetDimension2 = targetDimension;
        boolean scheduled2 = TeleportServer.scheduleServerTransition(player, 1, finalTargetFeet = targetFeet, finalTargetDimension = targetDimension, () -> runWaystonesTeleport(context, targetWaystonePos, finalTargetDimension2, player));
        return scheduled2 ? Either.left(List.of(player)) : null;
    }

    private static Entity readEntity(Object context) {
        Object result = invokeNoArg(context, "getEntity");
        return result instanceof Entity entity ? entity : null;
    }

    private static Vec3 readDestinationLocation(Object destination) {
        Object result = invokeNoArg(destination, "getLocation");
        return result instanceof Vec3 vec3 ? vec3 : null;
    }

    private static ResourceKey<Level> readDestinationDimension(Object destination) {
        Object result = invokeNoArg(destination, "getLevel");
        if (result instanceof ServerLevel level) {
            return level.dimension();
        }
        return null;
    }

    private static Vec3 readWaystoneFeet(Object waystone) {
        Object result = invokeNoArg(waystone, "getPos");
        if (result instanceof BlockPos pos) {
            return new Vec3(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
        }
        return null;
    }

    private static ResourceKey<Level> readWaystoneDimension(Object waystone) {
        Object result = invokeNoArg(waystone, "getDimension");
        if (result instanceof ResourceKey<?> key) {
            @SuppressWarnings("unchecked")
            ResourceKey<Level> dimension = (ResourceKey<Level>) key;
            return dimension;
        }
        return null;
    }

    private static Object invokeNoArg(Object target, String methodName) {
        if (target == null) {
            return null;
        }
        try {
            Method method = target.getClass().getMethod(methodName);
            return method.invoke(target);
        } catch (ReflectiveOperationException ignored) {
            return null;
        }
    }

    private static BlockPos readWaystonePos(Object waystone) {
        Object result = invokeNoArg(waystone, "getPos");
        return result instanceof BlockPos pos ? pos : null;
    }

    private static void forceLoadDestinationChunks(ServerLevel level, BlockPos waystonePos) {
        int chunkX = waystonePos.getX() >> 4;
        int chunkZ = waystonePos.getZ() >> 4;
        int[][] neighborOffsets = {{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] offset : neighborOffsets) {
            int cx = chunkX + offset[0];
            int cz = chunkZ + offset[1];
            try {
                level.getChunk(cx, cz);
                LOGGER.info("TA force-loaded chunk [{}, {}]", cx, cz);
            } catch (Exception e) {
                LOGGER.warn("TA failed to force-load chunk [{}, {}]: {}", cx, cz, e.getMessage());
            }
        }
    }

    private static void runWaystonesTeleport(Object context, BlockPos targetWaystonePos, ResourceKey<Level> targetDimension, ServerPlayer player) {
        if (targetWaystonePos != null) {
            ServerLevel targetLevel = player.level().getServer().getLevel(targetDimension);
            if (targetLevel != null) {
                forceLoadDestinationChunks(targetLevel, targetWaystonePos);
            } else {
                LOGGER.warn("TA Could not find ServerLevel for dimension {}, skipping chunk loading", targetDimension);
            }
        }

        Throwable tryTeleportFailure = invokeWaystonesTeleport(context, "tryTeleport", false);
        if (tryTeleportFailure == null) {
            return;
        }
        Throwable teleportFailure = invokeWaystonesTeleport(context, "teleport", false);
        if (teleportFailure == null) {
            return;
        }
        Throwable tryAsyncFailure = invokeWaystonesTeleport(context, "tryTeleportAsync", true);
        if (tryAsyncFailure == null) {
            return;
        }
        Throwable forceAsyncFailure = invokeWaystonesTeleport(context, "forceTeleportAsync", true);
        if (forceAsyncFailure == null) {
            return;
        }
        LOGGER.error("TA runWaystonesTeleport: all 4 reflection attempts failed, the player was NOT teleported by Waystones despite the animation playing as if it succeeded. tryTeleport={} teleport={} tryTeleportAsync={} forceTeleportAsync={}", tryTeleportFailure.toString(), teleportFailure.toString(), tryAsyncFailure.toString(), forceAsyncFailure.toString());
    }

    private static Throwable invokeWaystonesTeleport(Object context, String methodName, boolean async) {
        try {
            Class<?> managerClass = Class.forName(PLAYER_WAYSTONE_MANAGER_CLASS);
            Class<?> contextClass = Class.forName(WAYSTONE_TELEPORT_CONTEXT_CLASS);
            Method method = managerClass.getMethod(methodName, contextClass);
            Object result = method.invoke(null, context);
            if (async && result instanceof java.util.concurrent.CompletableFuture) {
                ((java.util.concurrent.CompletableFuture<?>) result).join();
            }
            return null;
        } catch (ReflectiveOperationException | LinkageError | RuntimeException e) {
            return e;
        }
    }
}
