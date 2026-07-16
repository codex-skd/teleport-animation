package com.skd.teleportanimation.mixin;

import com.skd.teleportanimation.TeleportTransitionController;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "com.github.leawind.thirdperson.ThirdPerson", remap = false)
public abstract class LeawindThirdPersonLegacyMixin {
    @Inject(method = "isAvailable", at = @At("HEAD"), cancellable = true, require = 0)
    private static void gtalikeTeleport$disableLegacyLeawindDuringTransition(CallbackInfoReturnable<Boolean> cir) {
        if (TeleportTransitionController.shouldPreemptLeawindThirdPersonCamera()) {
            cir.setReturnValue(false);
        }
    }
}
