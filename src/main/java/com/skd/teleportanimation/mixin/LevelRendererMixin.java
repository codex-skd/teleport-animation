package com.skd.teleportanimation.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.skd.teleportanimation.TeleportTransitionController;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.ViewArea;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(LevelRenderer.class)
abstract class LevelRendererMixin {
    @Unique
    private static Field gtalikeTeleport$viewAreaField;
    @Unique
    private static boolean gtalikeTeleport$viewAreaLookupFailed;
    @Unique
    private static Field gtalikeTeleport$lastCameraXField;
    @Unique
    private static Field gtalikeTeleport$lastCameraYField;
    @Unique
    private static Field gtalikeTeleport$lastCameraZField;
    @Unique
    private static Field gtalikeTeleport$lastCameraChunkXField;
    @Unique
    private static Field gtalikeTeleport$lastCameraChunkYField;
    @Unique
    private static Field gtalikeTeleport$lastCameraChunkZField;
    @Unique
    private static boolean gtalikeTeleport$lastCameraLookupFailed;

    @Inject(method = "renderLevel", at = @At("HEAD"))
    private void gtalikeTeleport$anchorViewAreaToTransitionCamera(PoseStack poseStack, float partialTick, long finishTimeNano, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f projectionMatrix, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldForceTerrainFrustumApply()) {
            return;
        }
        ViewArea viewArea = gtalikeTeleport$getViewArea((LevelRenderer) (Object) this);
        if (viewArea == null) {
            return;
        }
        Vec3 cameraPos = camera.getPosition();
        viewArea.repositionCamera(cameraPos.x, cameraPos.z);
        gtalikeTeleport$maskPlayerChunkReposition((LevelRenderer) (Object) this);
    }

    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel$ClientLevelData;getHorizonHeight(Lnet/minecraft/world/level/LevelHeightAccessor;)D"))
    private double gtalikeTeleport$useGroundSkyHorizon(ClientLevel.ClientLevelData data, LevelHeightAccessor level) {
        if (TeleportTransitionController.shouldUseGroundSkyBackground()) {
            return Double.NEGATIVE_INFINITY;
        }
        return data.getHorizonHeight(level);
    }

    @Unique
    private static void gtalikeTeleport$maskPlayerChunkReposition(LevelRenderer renderer) {
        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client == null ? null : client.player;
        if (player == null) return;
        if (!gtalikeTeleport$ensureLastCameraFields(renderer.getClass())) return;
        try {
            gtalikeTeleport$lastCameraXField.setDouble(renderer, player.getX());
            gtalikeTeleport$lastCameraYField.setDouble(renderer, player.getY());
            gtalikeTeleport$lastCameraZField.setDouble(renderer, player.getZ());
            gtalikeTeleport$lastCameraChunkXField.setInt(renderer, SectionPos.blockToSectionCoord(player.getX()));
            gtalikeTeleport$lastCameraChunkYField.setInt(renderer, SectionPos.blockToSectionCoord(player.getY()));
            gtalikeTeleport$lastCameraChunkZField.setInt(renderer, SectionPos.blockToSectionCoord(player.getZ()));
        } catch (IllegalAccessException ignored) {
        }
    }

    @Unique
    private static ViewArea gtalikeTeleport$getViewArea(LevelRenderer renderer) {
        Field field = gtalikeTeleport$getViewAreaField(renderer.getClass());
        if (field == null) return null;
        try {
            Object value = field.get(renderer);
            return value instanceof ViewArea ? (ViewArea) value : null;
        } catch (IllegalAccessException ignored) {
            return null;
        }
    }

    @Unique
    private static Field gtalikeTeleport$getViewAreaField(Class<?> rendererClass) {
        if (gtalikeTeleport$viewAreaField != null) return gtalikeTeleport$viewAreaField;
        if (gtalikeTeleport$viewAreaLookupFailed) return null;
        for (String fieldName : new String[]{"viewArea"}) {
            try {
                Field field = rendererClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                gtalikeTeleport$viewAreaField = field;
                return field;
            } catch (NoSuchFieldException ignored) {
            }
        }
        gtalikeTeleport$viewAreaLookupFailed = true;
        return null;
    }

    @Unique
    private static boolean gtalikeTeleport$ensureLastCameraFields(Class<?> rendererClass) {
        if (gtalikeTeleport$lastCameraXField != null && gtalikeTeleport$lastCameraYField != null && gtalikeTeleport$lastCameraZField != null && gtalikeTeleport$lastCameraChunkXField != null && gtalikeTeleport$lastCameraChunkYField != null && gtalikeTeleport$lastCameraChunkZField != null) {
            return true;
        }
        if (gtalikeTeleport$lastCameraLookupFailed) return false;
        gtalikeTeleport$lastCameraXField = gtalikeTeleport$getField(rendererClass, "lastCameraX");
        gtalikeTeleport$lastCameraYField = gtalikeTeleport$getField(rendererClass, "lastCameraY");
        gtalikeTeleport$lastCameraZField = gtalikeTeleport$getField(rendererClass, "lastCameraZ");
        gtalikeTeleport$lastCameraChunkXField = gtalikeTeleport$getField(rendererClass, "lastCameraChunkX");
        gtalikeTeleport$lastCameraChunkYField = gtalikeTeleport$getField(rendererClass, "lastCameraChunkY");
        gtalikeTeleport$lastCameraChunkZField = gtalikeTeleport$getField(rendererClass, "lastCameraChunkZ");
        boolean foundAll = gtalikeTeleport$lastCameraXField != null && gtalikeTeleport$lastCameraYField != null && gtalikeTeleport$lastCameraZField != null && gtalikeTeleport$lastCameraChunkXField != null && gtalikeTeleport$lastCameraChunkYField != null && gtalikeTeleport$lastCameraChunkZField != null;
        gtalikeTeleport$lastCameraLookupFailed = !foundAll;
        return foundAll;
    }

    @Unique
    private static Field gtalikeTeleport$getField(Class<?> owner, String... names) {
        for (String name : names) {
            try {
                Field field = owner.getDeclaredField(name);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException ignored) {
            }
        }
        return null;
    }
}
