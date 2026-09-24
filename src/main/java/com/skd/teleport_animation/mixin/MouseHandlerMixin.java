package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public abstract class MouseHandlerMixin {
    @Inject(method = "turnPlayer", at = @At("HEAD"), cancellable = true)
    private void ta$blockMouseLook(CallbackInfo ci) {
        if (TeleportTransitionController.shouldBlockGameplayInput()) {
            ci.cancel();
        }
    }

    @Inject(method = "onPress", at = @At("HEAD"), cancellable = true)
    private void ta$blockMousePress(long window, int button, int action, int modifiers, CallbackInfo ci) {
        if (action != 0 && TeleportTransitionController.shouldBlockGameplayInput()) {
            ci.cancel();
        }
    }

    @Inject(method = "onScroll", at = @At("HEAD"), cancellable = true)
    private void ta$blockMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (TeleportTransitionController.shouldBlockGameplayInput()) {
            ci.cancel();
        }
    }
}