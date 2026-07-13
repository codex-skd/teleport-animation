package com.skd.teleport_animation;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Method;

public final class WaystonesWarpPlateHandler {
    private WaystonesWarpPlateHandler() {
    }

    public static boolean handleWarpPlateTeleport(Object warpPlate, Entity entity, Object target, ItemStack stack) {
        if (!(entity instanceof ServerPlayer player)) {
            return false;
        }
        if (warpPlate == null || target == null || !isValidWaystone(target)) {
            return false;
        }
        Method teleportMethod = findTeleportToTargetMethod(warpPlate, player, target, stack);
        BlockPos pos = getWaystonePos(target);
        if (teleportMethod == null || pos == null) {
            return false;
        }
        if (!TeleportConfig.isWarpPlateTransitionsEnabled()) {
            TeleportServer.markNextServerTeleportBypassed(player);
            return false;
        }
        Vec3 targetFeet = new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        return TeleportServer.scheduleServerTransition(player, 2, targetFeet, getWaystoneDimension(player, target), () -> runWarpPlateTeleport(warpPlate, teleportMethod, player, target, stack));
    }

    private static boolean isValidWaystone(Object target) {
        try {
            Object result = target.getClass().getMethod("isValid").invoke(target);
            if (result instanceof Boolean valid) {
                return valid;
            }
            return true;
        }
        catch (ReflectiveOperationException ignored) {
            return true;
        }
    }

    private static BlockPos getWaystonePos(Object target) {
        try {
            Object result = target.getClass().getMethod("getPos").invoke(target);
            return result instanceof BlockPos pos ? pos : null;
        }
        catch (ReflectiveOperationException ignored) {
            return null;
        }
    }

    private static ResourceKey<Level> getWaystoneDimension(ServerPlayer player, Object target) {
        try {
            Object result = target.getClass().getMethod("getDimension").invoke(target);
            if (result instanceof ResourceKey<?> key) {
                @SuppressWarnings("unchecked")
                ResourceKey<Level> dimension = (ResourceKey<Level>) key;
                return dimension;
            }
        }
        catch (ReflectiveOperationException ignored) {
        }
        return player.level().dimension();
    }

    private static Method findTeleportToTargetMethod(Object warpPlate, ServerPlayer player, Object target, ItemStack stack) {
        for (Class<?> current = warpPlate.getClass(); current != null; current = current.getSuperclass()) {
            for (Method method : current.getDeclaredMethods()) {
                String methodName = method.getName();
                if (!methodName.equals("teleportToWarpPlate") && !methodName.equals("teleportToTarget")) continue;
                if (method.getParameterCount() != 3) continue;
                Class<?>[] parameters = method.getParameterTypes();
                if (!parameters[0].isInstance(player) || !parameters[1].isInstance(target) || !parameters[2].isInstance(stack)) continue;
                method.setAccessible(true);
                return method;
            }
        }
        return null;
    }

    private static void runWarpPlateTeleport(Object warpPlate, Method teleportMethod, ServerPlayer player, Object target, ItemStack stack) {
        TeleportServer.runWithServerTeleportBypass(player, () -> {
            try {
                teleportMethod.invoke(warpPlate, player, target, stack);
            }
            catch (ReflectiveOperationException ignored) {
            }
        });
    }
}