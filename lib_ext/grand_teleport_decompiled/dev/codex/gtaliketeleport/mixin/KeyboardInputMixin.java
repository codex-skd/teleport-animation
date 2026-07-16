/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.player.Input
 *  net.minecraft.client.player.KeyboardInput
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package dev.codex.gtaliketeleport.mixin;

import dev.codex.gtaliketeleport.TeleportTransitionController;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={KeyboardInput.class})
public abstract class KeyboardInputMixin
extends Input {
    @Inject(method={"tick"}, at={@At(value="HEAD")}, cancellable=true)
    private void gtalikeTeleport$blockMovementInput(boolean slowDown, float movementMultiplier, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldBlockPlayerInput()) {
            return;
        }
        this.f_108566_ = 0.0f;
        this.f_108567_ = 0.0f;
        this.f_108568_ = false;
        this.f_108569_ = false;
        this.f_108570_ = false;
        this.f_108571_ = false;
        this.f_108572_ = false;
        this.f_108573_ = false;
        ci.cancel();
    }
}

