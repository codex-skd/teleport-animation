package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportClient;
import com.skd.teleport_animation.TeleportTransitionController;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundForgetLevelChunkPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {
    @Inject(method = "sendCommand", at = @At("HEAD"), cancellable = true)
    private void teleportAnimation$interceptCommand(String command, CallbackInfo ci) {
        if (!TeleportClient.interceptOutgoingCommand(command)) {
            ci.cancel();
        }
    }

    @Inject(method = "handleForgetLevelChunk", at = @At("HEAD"), cancellable = true)
    private void teleportAnimation$retainDepartingChunk(ClientboundForgetLevelChunkPacket packet, CallbackInfo ci) {
        if (TeleportTransitionController.shouldRetainDepartingChunk(packet.pos().x(), packet.pos().z())) {
            ci.cancel();
        }
    }
}
