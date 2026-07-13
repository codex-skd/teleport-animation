package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "com.seibel.distanthorizons.core.util.RenderUtil", remap = false)
abstract class DistantHorizonsRenderUtilMixin {
    @Inject(method = "getNearClipPlaneInBlocks", at = @At("HEAD"), cancellable = true, require = 0)
    private static void gtalikeTeleport$reduceNearClipDuringTransition(CallbackInfoReturnable<Float> cir) {
        if (TeleportTransitionController.shouldOverrideDistantHorizonsNearClip()) {
            cir.setReturnValue(0.1f);
        }
    }
}
