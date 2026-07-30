package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.WaystonesTeleportHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "net.blay09.mods.waystones.core.WaystoneTeleportManager", remap = false)
public abstract class WaystonesPlayerWaystoneManagerMixin {
    @Inject(method = "tryTeleport", at = @At("HEAD"), cancellable = true, remap = false, require = 0)
    private static void teleportAnimation$delayTryTeleport(@Coerce Object context, CallbackInfoReturnable<Object> cir) {
        Object result = WaystonesTeleportHandler.delayTeleportContext(context);
        if (result != null) {
            cir.setReturnValue(result);
        }
    }

    @Inject(method = "teleport", at = @At("HEAD"), cancellable = true, remap = false, require = 0)
    private static void teleportAnimation$delayTeleport(@Coerce Object context, CallbackInfoReturnable<Object> cir) {
        Object result = WaystonesTeleportHandler.delayTeleportContext(context);
        if (result != null) {
            cir.setReturnValue(result);
        }
    }

    @Inject(method = "tryTeleportAsync", at = @At("HEAD"), cancellable = true, remap = false, require = 0)
    private static void teleportAnimation$delayTryTeleportAsync(@Coerce Object context, CallbackInfoReturnable<Object> cir) {
        Object result = WaystonesTeleportHandler.delayTeleportContext(context);
        if (result == null) {
            return;
        }
        if (result instanceof java.util.concurrent.CompletableFuture) {
            cir.setReturnValue(result);
        } else {
            cir.setReturnValue(java.util.concurrent.CompletableFuture.completedFuture(result));
        }
    }

    @Inject(method = "forceTeleportAsync", at = @At("HEAD"), cancellable = true, remap = false, require = 0)
    private static void teleportAnimation$delayForceTeleportAsync(@Coerce Object context, CallbackInfoReturnable<Object> cir) {
        Object result = WaystonesTeleportHandler.delayTeleportContext(context);
        if (result == null) {
            return;
        }
        if (result instanceof java.util.concurrent.CompletableFuture) {
            cir.setReturnValue(result);
        } else {
            cir.setReturnValue(java.util.concurrent.CompletableFuture.completedFuture(result));
        }
    }
}
