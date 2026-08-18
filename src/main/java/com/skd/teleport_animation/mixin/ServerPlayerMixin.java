package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.RelativeMovement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Inject(method = "teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FF)Z", at = @At("HEAD"), cancellable = true, require = 0)
    private void ta$tp7(ServerLevel level, double x, double y, double z, Set<RelativeMovement> relatives, float yaw, float pitch, CallbackInfoReturnable<Boolean> cir) {
        if (TeleportServer.tryDelayExternalTeleport((ServerPlayer)(Object)this, level, x, y, z, relatives, yaw, pitch, false)) {
            cir.setReturnValue(true);
        }
    }
}