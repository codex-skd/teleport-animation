/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.client.Camera
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.renderer.FogRenderer
 *  net.minecraft.world.level.material.FogType
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package dev.codex.gtaliketeleport.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.codex.gtaliketeleport.TeleportTransitionController;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={FogRenderer.class})
abstract class FogRendererMixin {
    FogRendererMixin() {
    }

    @Inject(method={"setupColor"}, at={@At(value="TAIL")})
    private static void gtalikeTeleport$useGroundSkyBackground(Camera camera, float partialTick, ClientLevel level, int renderDistanceChunks, float bossColorModifier, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldUseGroundSkyBackground() || level == null || camera.m_167685_() != FogType.NONE) {
            return;
        }
        Vec3 skyColor = level.m_171660_(camera.m_90583_(), partialTick);
        RenderSystem.clearColor((float)((float)skyColor.f_82479_), (float)((float)skyColor.f_82480_), (float)((float)skyColor.f_82481_), (float)0.0f);
    }
}

