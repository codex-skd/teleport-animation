package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportClient;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {
    @Inject(method = "sendCommand", at = @At("HEAD"), cancellable = true)
    private void ta$interceptCommand(String command, CallbackInfo ci) {
        if (!TeleportClient.interceptOutgoingCommand(command)) {
            ci.cancel();
        }
    }
}