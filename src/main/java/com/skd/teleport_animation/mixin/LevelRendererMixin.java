package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.world.level.LevelHeightAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelRenderer.class)
abstract class LevelRendererMixin {
    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel$ClientLevelData;getHorizonHeight(Lnet/minecraft/world/level/LevelHeightAccessor;)D"), require = 0)
    private double teleportAnimation$useGroundSkyHorizon(ClientLevel.ClientLevelData data, LevelHeightAccessor level) {
        if (TeleportTransitionController.shouldUseGroundSkyBackground()) {
            return Double.NEGATIVE_INFINITY;
        }
        return data.getHorizonHeight(level);
    }
}
