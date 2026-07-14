package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.level.portal.TeleportTransition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FF)V", at = @At("HEAD"), cancellable = true, require = 0)
    private void ta$tp7(ServerLevel level, double x, double y, double z, Set<Relative> relatives, float yaw, float pitch, CallbackInfo ci) {
        Entity self = (Entity)(Object)this;
        if (self instanceof ServerPlayer player) {
            if (TeleportServer.tryDelayExternalTeleport(player, level, x, y, z, relatives, yaw, pitch, false)) ci.cancel();
        }
    }

    @Inject(method = "teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FFZ)V", at = @At("HEAD"), cancellable = true, require = 0)
    private void ta$tp8(ServerLevel level, double x, double y, double z, Set<Relative> relatives, float yaw, float pitch, boolean resetCamera, CallbackInfo ci) {
        Entity self = (Entity)(Object)this;
        if (self instanceof ServerPlayer player) {
            if (TeleportServer.tryDelayExternalTeleport(player, level, x, y, z, relatives, yaw, pitch, resetCamera)) ci.cancel();
        }
    }

    @Inject(method = "teleport(Lnet/minecraft/world/level/portal/TeleportTransition;)Lnet/minecraft/world/entity/Entity;", at = @At("HEAD"), cancellable = true, require = 0)
    private void ta$teleportTransition(TeleportTransition transition, CallbackInfoReturnable<Entity> cir) {
        Entity self = (Entity)(Object)this;
        if (self instanceof ServerPlayer player) {
            if (TeleportServer.tryDelayExternalTeleport(player,
                    transition.newLevel(),
                    transition.position().x, transition.position().y, transition.position().z,
                    Set.of(), transition.yRot(), transition.xRot(), false)) {
                cir.setReturnValue(null);
            }
        }
    }
}