package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportServer;
import java.util.Set;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Relative;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Inject(method = "teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FFZ)V", at = @At("HEAD"), cancellable = true)
    private void teleportAnimation$delayExternalTeleport(ServerLevel level, double x, double y, double z, Set<Relative> relatives, float yaw, float pitch, boolean resetCamera, CallbackInfo ci) {
        if (TeleportServer.tryDelayExternalTeleport((ServerPlayer) (Object) this, level, x, y, z, relatives, yaw, pitch, resetCamera)) {
            ci.cancel();
        }
    }
}
