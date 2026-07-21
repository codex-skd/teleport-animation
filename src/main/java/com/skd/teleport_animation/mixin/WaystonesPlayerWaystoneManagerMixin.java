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
    @Inject(method = "tryTeleport(Lnet/blay09/mods/waystones/api/WaystoneTeleportContext;)Lnet/blay09/mods/waystones/api/WaystoneTeleportResult;", at = @At("HEAD"), cancellable = true, remap = false, require = 0)
    private static void teleportAnimation$delayWaystonesTeleport(@Coerce Object context, CallbackInfoReturnable<Object> cir) {
        Object result = WaystonesTeleportHandler.delayTeleportContext(context);
        if (result != null) {
            cir.setReturnValue(result);
        }
    }
}
