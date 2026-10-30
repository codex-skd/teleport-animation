package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "com.github.leawind.thirdperson.impl.ThirdPersonImpl", remap = false)
public abstract class LeawindThirdPersonImplMixin {
    @Inject(method = "isAvailable", at = @At("HEAD"), cancellable = true, require = 0)
    private void teleportAnimation$disableLeawindDuringTransition(CallbackInfoReturnable<Boolean> cir) {
        if (TeleportTransitionController.shouldPreemptLeawindThirdPersonCamera()) {
            cir.setReturnValue(false);
        }
    }
}
