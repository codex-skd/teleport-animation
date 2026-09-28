package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public abstract class ScreenEffectRendererMixin {
    @Inject(method = "renderScreenEffect", at = @At("HEAD"), cancellable = true, require = 0, remap = false)
    private void teleportAnimation$suppressScreenEffects(boolean bl1, boolean bl2, float f, SubmitNodeCollector collector, boolean bl3, CallbackInfo ci) {
        if (TeleportTransitionController.shouldSuppressScreenEffects()) {
            ci.cancel();
        }
    }
}
