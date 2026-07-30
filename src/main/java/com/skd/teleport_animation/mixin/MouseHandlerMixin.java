package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.input.MouseButtonInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public abstract class MouseHandlerMixin {
    @Inject(method = "turnPlayer", at = @At("HEAD"), cancellable = true, require = 0, remap = false)
    private void teleportAnimation$blockMouseLook(double turnRate, CallbackInfo ci) {
        if (TeleportTransitionController.shouldBlockGameplayInput()) {
            ci.cancel();
        }
    }

    @Inject(method = "onButton", at = @At("HEAD"), cancellable = true, require = 0, remap = false)
    private void teleportAnimation$blockMousePress(long window, MouseButtonInfo info, int action, CallbackInfo ci) {
        if (action != 0 && TeleportTransitionController.shouldBlockGameplayInput()) {
            ci.cancel();
        }
    }

    @Inject(method = "onScroll", at = @At("HEAD"), cancellable = true, require = 0, remap = false)
    private void teleportAnimation$blockMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (TeleportTransitionController.shouldBlockGameplayInput()) {
            ci.cancel();
        }
    }
}
