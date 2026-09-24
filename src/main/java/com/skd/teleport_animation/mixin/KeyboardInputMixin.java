package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public abstract class KeyboardInputMixin extends Input {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void ta$blockMovementInput(boolean slowDown, float movementMultiplier, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldBlockPlayerInput()) {
            return;
        }
        this.leftImpulse = 0.0f;
        this.forwardImpulse = 0.0f;
        this.jumping = false;
        this.shiftKeyDown = false;
        this.left = false;
        this.right = false;
        ci.cancel();
    }
}
