package com.skd.teleport_animation;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public final class WaystonesTeleportHandler {
    private static final String WAYSTONE_TELEPORT_MANAGER_CLASS = "net.blay09.mods.waystones.core.WaystoneTeleportManager";
    private static final String WAYSTONE_TELEPORT_CONTEXT_CLASS = "net.blay09.mods.waystones.api.WaystoneTeleportContext";
    private static final String WAYSTONE_TELEPORT_RESULT_CLASS = "net.blay09.mods.waystones.api.WaystoneTeleportResult";

    private WaystonesTeleportHandler() {
    }

    public static Object delayTeleportContext(Object context) {
        Entity entity = readEntity(context);
        if (!(entity instanceof ServerPlayer player)) {
            return null;
        }
        Object waystone = invokeNoArg(context, "getTargetWaystone");
        if (waystone == null) {
            return null;
        }
        Vec3 targetFeet = readWaystoneFeet(waystone);
        ResourceKey<Level> targetDimension = readWaystoneDimension(waystone);
        if (targetFeet == null) {
            return null;
        }
        if (targetDimension == null) {
            targetDimension = ((ServerLevel)player.level()).dimension();
        }
        boolean scheduled = TeleportServer.scheduleServerTransition(player, 3, targetFeet, targetDimension, () -> runWaystonesTeleport(context));
        if (!scheduled) {
            return null;
        }
        return createEmptyTeleportResult();
    }

    private static Object createEmptyTeleportResult() {
        try {
            Class<?> resultClass = Class.forName(WAYSTONE_TELEPORT_RESULT_CLASS);
            Constructor<?> constructor = resultClass.getConstructor(List.class);
            return constructor.newInstance(List.of());
        } catch (ReflectiveOperationException ignored) {
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
            Method method = managerClass.getMethod("tryTeleport", contextClass);
            method.invoke(null, context);
        } catch (ReflectiveOperationException ignored) {
        }
    }
}