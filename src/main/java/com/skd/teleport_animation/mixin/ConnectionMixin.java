package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportClient;
import net.minecraft.network.Connection;
import net.minecraft.network.PacketSendListener;
import net.minecraft.network.protocol.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Connection.class)
public abstract class ConnectionMixin {
    @Inject(method = "send", at = @At("HEAD"), cancellable = true, require = 0)
    private void teleportAnimation$interceptTeleportPacket(Packet<?> packet, PacketSendListener listener, CallbackInfo ci) {
        if (!TeleportClient.interceptOutgoingPacket((Connection)(Object) this, packet, listener)) {
            ci.cancel();
        }
    }
}
