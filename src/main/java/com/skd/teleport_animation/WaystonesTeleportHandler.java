package com.skd.teleport_animation;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public final class WaystonesTeleportHandler {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String WAYSTONE_TELEPORT_MANAGER_CLASS = "net.blay09.mods.waystones.core.WaystoneTeleportManager";
    private static final String WAYSTONE_TELEPORT_CONTEXT_CLASS = "net.blay09.mods.waystones.api.WaystoneTeleportContext";
    private static final String WAYSTONE_TELEPORT_RESULT_CLASS = "net.blay09.mods.waystones.api.WaystoneTeleportResult";

    private WaystonesTeleportHandler() {
    }

    public static Object delayTeleportContext(Object context) {
        LOGGER.info("GTP delayTeleportContext called, context class: {}", context.getClass().getName());
        Entity entity = readEntity(context);
        if (entity == null) {
            LOGGER.info("GTP readEntity returned null");
            return null;
        }
        if (!(entity instanceof ServerPlayer player)) {
            LOGGER.info("GTP entity not ServerPlayer: {}", entity.getClass().getName());
            return null;
        }
        LOGGER.info("GTP player: {}", player.getScoreboardName());
        Object waystone = invokeNoArg(context, "getTargetWaystone");
        if (waystone == null) {
            LOGGER.info("GTP getTargetWaystone returned null");
            return null;
        }
        LOGGER.info("GTP waystone type: {}", waystone.getClass().getName());
        Vec3 targetFeet = readWaystoneFeet(waystone);
        if (targetFeet == null) {
            LOGGER.info("GTP readWaystoneFeet returned null");
            return null;
        }
        ResourceKey<Level> targetDimension = readWaystoneDimension(waystone);
        if (targetDimension == null) {
            LOGGER.info("GTP readWaystoneDimension returned null, using player dim");
            targetDimension = ((ServerLevel)player.level()).dimension();
        }
        LOGGER.info("GTP scheduling transition: player={} dim={} pos={},{},{}", player.getScoreboardName(), DimensionIds.fromResourceKey(targetDimension), targetFeet.x, targetFeet.y, targetFeet.z);
        boolean scheduled = TeleportServer.scheduleServerTransition(player, 3, targetFeet, targetDimension, () -> runWaystonesTeleport(context));
        LOGGER.info("GTP scheduleServerTransition result: {}", scheduled);
        if (!scheduled) {
            return null;
        }
        return createEmptyTeleportResult();
    }

    private static Object createEmptyTeleportResult() {
        try {
            Class<?> resultClass = Class.forName(WAYSTONE_TELEPORT_RESULT_CLASS);
            LOGGER.info("GTP WaystoneTeleportResult class found");
            Constructor<?> constructor = resultClass.getConstructor(List.class);
            Object instance = constructor.newInstance(new java.util.ArrayList<>());
            LOGGER.info("GTP empty result created: {}", instance);
            return instance;
        } catch (ReflectiveOperationException e) {
            LOGGER.error("GTP createEmptyTeleportResult failed", e);
            return null;
        }
    }

    private static Entity readEntity(Object context) {
        Object result = invokeNoArg(context, "getEntity");
        return result instanceof Entity entity ? entity : null;
    }

    private static Vec3 readWaystoneFeet(Object waystone) {
        Object result = invokeNoArg(waystone, "getPos");
        if (result instanceof BlockPos pos) {
            return new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static ResourceKey<Level> readWaystoneDimension(Object waystone) {
        Object result = invokeNoArg(waystone, "getDimension");
        if (result instanceof ResourceKey<?> key) {
            return (ResourceKey<Level>) key;
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

    private static void runWaystonesTeleport(Object context) {
        try {
            Class<?> managerClass = Class.forName(WAYSTONE_TELEPORT_MANAGER_CLASS);
            Class<?> contextClass = Class.forName(WAYSTONE_TELEPORT_CONTEXT_CLASS);
            Method tryTeleport = managerClass.getMethod("tryTeleport", contextClass);
            tryTeleport.invoke(null, context);
        } catch (ReflectiveOperationException ignored) {
            try {
                Class<?> managerClass = Class.forName(WAYSTONE_TELEPORT_MANAGER_CLASS);
                Class<?> contextClass = Class.forName(WAYSTONE_TELEPORT_CONTEXT_CLASS);
                Method teleport = managerClass.getMethod("teleport", contextClass);
                teleport.invoke(null, context);
            } catch (ReflectiveOperationException ignored2) {
            }
        }
    }
}