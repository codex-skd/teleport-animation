package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.GtaLikeTeleportServer;
import java.util.Set;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Relative;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Inject(method = "teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FF)Z", at = @At("HEAD"), cancellable = true)
    private void gtalikeTeleport$delayExternalTeleport(ServerLevel level, double x, double y, double z, Set<Relative> relatives, float yaw, float pitch, CallbackInfoReturnable<Boolean> cir) {
        if (GtaLikeTeleportServer.tryDelayExternalTeleport((ServerPlayer) (Object) this, level, x, y, z, relatives, yaw, pitch, false)) {
            cir.setReturnValue(true);
        }
    }
}
