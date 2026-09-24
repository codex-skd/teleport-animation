package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportServer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Pseudo
@Mixin(targets = "net.blay09.mods.waystones.InternalMethodsImpl", remap = false)
public abstract class WaystonesInternalMethodsMixin {
    private static final Logger TA_LOG = LoggerFactory.getLogger("TA");
    private static final Set<Object> executingDeferred = Collections.newSetFromMap(new ConcurrentHashMap<>());

    @Inject(method = "tryTeleport", at = @At("HEAD"), cancellable = true, remap = false, require = 0)
    private void ta$delayWaystoneTeleport(@Coerce Object context, CallbackInfoReturnable<Object> cir) {
        if (executingDeferred.contains(context)) {
            executingDeferred.remove(context);
            return;
        }
        try {
            Entity entity = (Entity) context.getClass().getMethod("getEntity").invoke(context);
            if (!(entity instanceof ServerPlayer player)) return;

            Object waystone = context.getClass().getMethod("getTargetWaystone").invoke(context);
            if (waystone == null) return;

            BlockPos pos = (BlockPos) waystone.getClass().getMethod("getPos").invoke(waystone);
            if (pos == null) return;

            Vec3 targetFeet = new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            ResourceKey<Level> dimension = ((ServerLevel) player.level()).dimension();

            Object dimObj = waystone.getClass().getMethod("getDimension").invoke(waystone);
            if (dimObj instanceof ResourceKey) {
                dimension = (ResourceKey<Level>) dimObj;
            }

            TA_LOG.warn("ta$delayWaystoneTeleport: player={} target={} dim={}", player.getName().getString(), targetFeet, dimension);

            final ServerPlayer fPlayer = player;
            final Vec3 fTargetFeet = targetFeet;
            final ResourceKey<Level> fDimension = dimension;
            Object self = this;
            if (TeleportServer.scheduleServerTransition(fPlayer, 1, fTargetFeet, fDimension, () -> {
                executingDeferred.add(context);
                try {
                    Class<?> ctxClass = Class.forName("net.blay09.mods.waystones.api.WaystoneTeleportContext");
                    Method tryTeleport = Class.forName("net.blay09.mods.waystones.api.InternalMethods")
                        .getMethod("tryTeleport", ctxClass);
                    tryTeleport.invoke(self, ctxClass.cast(context));
                } catch (Exception ex) {
                    TA_LOG.warn("ta$runWaystoneTeleport error: {}", ex.getMessage());
                }
            })) {
                cir.setReturnValue(null);
            }
        } catch (Exception e) {
            TA_LOG.warn("ta$delayWaystoneTeleport error: {}", e.getMessage());
        }
    }

    @Inject(method = "tryTeleportAsync", at = @At("HEAD"), cancellable = true, remap = false, require = 0)
    private void ta$delayWaystoneTeleportAsync(@Coerce Object context, CallbackInfoReturnable<Object> cir) {
        if (executingDeferred.contains(context)) {
            executingDeferred.remove(context);
            return;
        }
        try {
            Entity entity = (Entity) context.getClass().getMethod("getEntity").invoke(context);
            if (!(entity instanceof ServerPlayer player)) return;

            Object waystone = context.getClass().getMethod("getTargetWaystone").invoke(context);
            if (waystone == null) return;

            BlockPos pos = (BlockPos) waystone.getClass().getMethod("getPos").invoke(waystone);
            if (pos == null) return;

            Vec3 targetFeet = new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            ResourceKey<Level> dimension = ((ServerLevel) player.level()).dimension();

            Object dimObj = waystone.getClass().getMethod("getDimension").invoke(waystone);
            if (dimObj instanceof ResourceKey) {
                dimension = (ResourceKey<Level>) dimObj;
            }

            TA_LOG.warn("ta$delayWaystoneTeleportAsync: player={} target={} dim={}", player.getName().getString(), targetFeet, dimension);

            final ServerPlayer fPlayer = player;
            final Vec3 fTargetFeet = targetFeet;
            final ResourceKey<Level> fDimension = dimension;
            Object self = this;
            CompletableFuture<Object> future = new CompletableFuture<>();
            if (TeleportServer.scheduleServerTransition(fPlayer, 1, fTargetFeet, fDimension, () -> {
                executingDeferred.add(context);
                try {
                    Class<?> ctxClass = Class.forName("net.blay09.mods.waystones.api.WaystoneTeleportContext");
                    Method tryTeleport = Class.forName("net.blay09.mods.waystones.api.InternalMethods")
                        .getMethod("tryTeleport", ctxClass);
                    Object result = tryTeleport.invoke(self, ctxClass.cast(context));
                    future.complete(result);
                } catch (Exception ex) {
                    TA_LOG.warn("ta$runWaystoneTeleportAsync error: {}", ex.getMessage());
                    future.completeExceptionally(ex);
                }
            })) {
                cir.setReturnValue(future);
            }
        } catch (Exception e) {
            TA_LOG.warn("ta$delayWaystoneTeleportAsync error: {}", e.getMessage());
        }
    }
}