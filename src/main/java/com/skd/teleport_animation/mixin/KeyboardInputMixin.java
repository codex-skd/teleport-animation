package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.world.entity.player.Input;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public abstract class KeyboardInputMixin {
    @Unique
    private static final Input teleportAnimation$emptyInput = new Input(false, false, false, false, false, false, false);

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void teleportAnimation$blockMovementInput(boolean slowDown, float movementMultiplier, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldBlockPlayerInput()) {
            return;
        }
        ((KeyboardInput)(Object) this).keyPresses = teleportAnimation$emptyInput;
        ci.cancel();
    }
}
