package com.skd.teleport_animation;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Method;
import java.util.List;

public final class WaystonesTeleportHandler {
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
        if (targetFeet == null) {
            Object targetWaystone = invokeNoArg(context, "getTargetWaystone");
            targetFeet = readWaystoneFeet(targetWaystone);
            if (targetDimension == null) {
                targetDimension = readWaystoneDimension(targetWaystone);
            }
        }
        if (targetFeet == null) {
            return null;
        }
        if (targetDimension == null) {
            targetDimension = ((ServerLevel)player.level()).dimension();
        }
        return (scheduled = GtaLikeTeleportServer.scheduleServerTransition(player, 1, finalTargetFeet = targetFeet, finalTargetDimension = targetDimension, () -> runWaystonesTeleport(context))) ? Either.left(List.of(player)) : null;
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
            return new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
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
        }
        catch (ReflectiveOperationException ignored) {
            return null;
        }
    }

    private static void runWaystonesTeleport(Object context) {
        try {
            Class<?> managerClass = Class.forName(PLAYER_WAYSTONE_MANAGER_CLASS);
            Class<?> contextClass = Class.forName(WAYSTONE_TELEPORT_CONTEXT_CLASS);
            Method method = managerClass.getMethod("tryTeleport", contextClass);
            method.invoke(null, context);
        }
        catch (ReflectiveOperationException ignored) {
        }
    }
}