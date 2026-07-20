package com.skd.teleport_animation.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Inject(method = "teleportTo", at = @At("HEAD"), cancellable = true, require = 0, remap = false)
    private void ta$tp7(ServerLevel level, double x, double y, double z, java.util.Set<?> relatives, float yaw, float pitch, CallbackInfo ci) {
    }

    @Inject(method = "teleportTo", at = @At("HEAD"), cancellable = true, require = 0, remap = false)
    private void ta$tp8(ServerLevel level, double x, double y, double z, java.util.Set<?> relatives, float yaw, float pitch, boolean resetCamera, CallbackInfo ci) {
    }

    @Inject(method = "changeDimension", at = @At("HEAD"), cancellable = true, require = 0, remap = false)
    private void ta$teleportTransition(Object transition, CallbackInfoReturnable<ServerPlayer> cir) {
    }
}