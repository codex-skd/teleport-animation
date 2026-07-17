package com.skd.teleportanimation.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.skd.teleportanimation.TeleportTransitionController;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class)
abstract class FogRendererMixin {
    @Inject(method = "setupColor", at = @At("TAIL"))
    private static void ta$useGroundSkyBackground(Camera camera, float partialTick, ClientLevel level, int renderDistanceChunks, float bossColorModifier, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldUseGroundSkyBackground() || level == null || camera.getFluidInCamera() != FogType.NONE) {
            return;
        }
        Vec3 skyColor = level.getSkyColor(camera.getPosition(), partialTick);
        RenderSystem.clearColor((float) skyColor.x, (float) skyColor.y, (float) skyColor.z, 0.0f);
    }
}
