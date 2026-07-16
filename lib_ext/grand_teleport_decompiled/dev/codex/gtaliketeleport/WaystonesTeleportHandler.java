/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Either
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package dev.codex.gtaliketeleport;

import com.mojang.datafixers.util.Either;
import dev.codex.gtaliketeleport.GtaLikeTeleportServer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public final class WaystonesTeleportHandler {
    private static final String PLAYER_WAYSTONE_MANAGER_CLASS = "net.blay09.mods.waystones.core.PlayerWaystoneManager";
    private static final String WAYSTONE_TELEPORT_CONTEXT_CLASS = "net.blay09.mods.waystones.api.IWaystoneTeleportContext";

    private WaystonesTeleportHandler() {
    }

    public static Object delayTeleportContext(Object context) {
        ResourceKey<Level> finalTargetDimension;
        Vec3 finalTargetFeet;
        boolean scheduled;
        Entity entity = WaystonesTeleportHandler.readEntity(context);
        if (!(entity instanceof ServerPlayer)) {
            return null;
        }
        ServerPlayer player = (ServerPlayer)entity;
        Object destination = WaystonesTeleportHandler.invokeNoArg(context, "getDestination");
        Vec3 targetFeet = WaystonesTeleportHandler.readDestinationLocation(destination);
        ResourceKey<Level> targetDimension = WaystonesTeleportHandler.readDestinationDimension(destination);
        if (targetFeet == null) {
            Object targetWaystone = WaystonesTeleportHandler.invokeNoArg(context, "getTargetWaystone");
            targetFeet = WaystonesTeleportHandler.readWaystoneFeet(targetWaystone);
            if (targetDimension == null) {
                targetDimension = WaystonesTeleportHandler.readWaystoneDimension(targetWaystone);
            }
        }
        if (targetFeet == null) {
            return null;
        }
        if (targetDimension == null) {
            targetDimension = player.m_9236_().m_46472_();
        }
        return (scheduled = GtaLikeTeleportServer.scheduleServerTransition(player, 1, finalTargetFeet = targetFeet, finalTargetDimension = targetDimension, () -> WaystonesTeleportHandler.runWaystonesTeleport(context))) ? Either.left(List.of(player)) : null;
    }

    private static Entity readEntity(Object context) {
        Entity entity;
        Object result = WaystonesTeleportHandler.invokeNoArg(context, "getEntity");
        return result instanceof Entity ? (entity = (Entity)result) : null;
    }

    private static Vec3 readDestinationLocation(Object destination) {
        Vec3 vec3;
        Object result = WaystonesTeleportHandler.invokeNoArg(destination, "getLocation");
        return result instanceof Vec3 ? (vec3 = (Vec3)result) : null;
    }

    private static ResourceKey<Level> readDestinationDimension(Object destination) {
        Object result = WaystonesTeleportHandler.invokeNoArg(destination, "getLevel");
        if (result instanceof ServerLevel) {
            ServerLevel level = (ServerLevel)result;
            return level.m_46472_();
        }
        return null;
    }

    private static Vec3 readWaystoneFeet(Object waystone) {
        Object result = WaystonesTeleportHandler.invokeNoArg(waystone, "getPos");
        if (result instanceof BlockPos) {
            BlockPos pos = (BlockPos)result;
            return new Vec3((double)pos.m_123341_() + 0.5, (double)pos.m_123342_(), (double)pos.m_123343_() + 0.5);
        }
        return null;
    }

    private static ResourceKey<Level> readWaystoneDimension(Object waystone) {
        Object result = WaystonesTeleportHandler.invokeNoArg(waystone, "getDimension");
        if (result instanceof ResourceKey) {
            ResourceKey key;
            ResourceKey dimension = key = (ResourceKey)result;
            return dimension;
        }
        return null;
    }

    private static Object invokeNoArg(Object target, String methodName) {
        if (target == null) {
            return null;
        }
        try {
            Method method = target.getClass().getMethod(methodName, new Class[0]);
            return method.invoke(target, new Object[0]);
        }
        catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
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
        catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException reflectiveOperationException) {
            // empty catch block
        }
    }
}

