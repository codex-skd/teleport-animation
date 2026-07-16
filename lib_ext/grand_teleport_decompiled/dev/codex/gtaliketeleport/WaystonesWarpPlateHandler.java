/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package dev.codex.gtaliketeleport;

import dev.codex.gtaliketeleport.GtaLikeTeleportConfig;
import dev.codex.gtaliketeleport.GtaLikeTeleportServer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public final class WaystonesWarpPlateHandler {
    private WaystonesWarpPlateHandler() {
    }

    public static boolean handleWarpPlateTeleport(Object warpPlate, Entity entity, Object target, ItemStack stack) {
        ServerPlayer player;
        block6: {
            block5: {
                if (!(entity instanceof ServerPlayer)) break block5;
                player = (ServerPlayer)entity;
                if (warpPlate != null && target != null && WaystonesWarpPlateHandler.isValidWaystone(target)) break block6;
            }
            return false;
        }
        Method teleportMethod = WaystonesWarpPlateHandler.findTeleportToTargetMethod(warpPlate, player, target, stack);
        BlockPos pos = WaystonesWarpPlateHandler.getWaystonePos(target);
        if (teleportMethod == null || pos == null) {
            return false;
        }
        if (!GtaLikeTeleportConfig.isWarpPlateTransitionsEnabled()) {
            GtaLikeTeleportServer.markNextServerTeleportBypassed(player);
            return false;
        }
        Vec3 targetFeet = new Vec3((double)pos.m_123341_() + 0.5, (double)pos.m_123342_(), (double)pos.m_123343_() + 0.5);
        return GtaLikeTeleportServer.scheduleServerTransition(player, 2, targetFeet, WaystonesWarpPlateHandler.getWaystoneDimension(player, target), () -> WaystonesWarpPlateHandler.runWarpPlateTeleport(warpPlate, teleportMethod, player, target, stack));
    }

    private static boolean isValidWaystone(Object target) {
        try {
            Boolean valid;
            Object result = target.getClass().getMethod("isValid", new Class[0]).invoke(target, new Object[0]);
            return !(result instanceof Boolean) || (valid = (Boolean)result) != false;
        }
        catch (ReflectiveOperationException ignored) {
            return true;
        }
    }

    private static BlockPos getWaystonePos(Object target) {
        try {
            BlockPos pos;
            Object result = target.getClass().getMethod("getPos", new Class[0]).invoke(target, new Object[0]);
            return result instanceof BlockPos ? (pos = (BlockPos)result) : null;
        }
        catch (ReflectiveOperationException ignored) {
            return null;
        }
    }

    private static ResourceKey<Level> getWaystoneDimension(ServerPlayer player, Object target) {
        try {
            Object result = target.getClass().getMethod("getDimension", new Class[0]).invoke(target, new Object[0]);
            if (result instanceof ResourceKey) {
                ResourceKey key;
                ResourceKey dimension = key = (ResourceKey)result;
                return dimension;
            }
        }
        catch (ClassCastException | ReflectiveOperationException exception) {
            // empty catch block
        }
        return player.m_9236_().m_46472_();
    }

    private static Method findTeleportToTargetMethod(Object warpPlate, ServerPlayer player, Object target, ItemStack stack) {
        for (Class<?> current = warpPlate.getClass(); current != null; current = current.getSuperclass()) {
            for (Method method : current.getDeclaredMethods()) {
                Class<?>[] parameters;
                String methodName = method.getName();
                if (!methodName.equals("teleportToWarpPlate") && !methodName.equals("teleportToTarget") || method.getParameterCount() != 3 || !(parameters = method.getParameterTypes())[0].isInstance(player) || !parameters[1].isInstance(target) || !parameters[2].isInstance(stack)) continue;
                method.setAccessible(true);
                return method;
            }
        }
        return null;
    }

    private static void runWarpPlateTeleport(Object warpPlate, Method teleportMethod, ServerPlayer player, Object target, ItemStack stack) {
        GtaLikeTeleportServer.runWithServerTeleportBypass(player, () -> {
            try {
                teleportMethod.invoke(warpPlate, player, target, stack);
            }
            catch (IllegalAccessException | InvocationTargetException reflectiveOperationException) {
                // empty catch block
            }
        });
    }
}

