package com.skd.teleportanimation.mixin;

import com.skd.teleportanimation.TeleportTransitionController;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
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
    private static Field ta$viewAreaField;
    @Unique
    private static boolean ta$viewAreaLookupFailed;
    @Unique
    private static Field ta$lastCameraXField;
    @Unique
    private static Field ta$lastCameraYField;
    @Unique
    private static Field ta$lastCameraZField;
    @Unique
    private static Field ta$lastCameraChunkXField;
    @Unique
    private static Field ta$lastCameraChunkYField;
    @Unique
    private static Field ta$lastCameraChunkZField;
    @Unique
    private static boolean ta$lastCameraLookupFailed;

    @Inject(method = "renderLevel", at = @At("HEAD"))
    private void ta$anchorViewAreaToTransitionCamera(DeltaTracker deltaTracker, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f matrix4f, Matrix4f projectionMatrix, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldForceTerrainFrustumApply()) {
            return;
        }
        ViewArea viewArea = ta$getViewArea((LevelRenderer) (Object) this);
        if (viewArea == null) {
            return;
        }
        Vec3 cameraPos = camera.getPosition();
        viewArea.repositionCamera(cameraPos.x, cameraPos.z);
        ta$maskPlayerChunkReposition((LevelRenderer) (Object) this);
    }

    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel$ClientLevelData;getHorizonHeight(Lnet/minecraft/world/level/LevelHeightAccessor;)D"))
    private double ta$useGroundSkyHorizon(ClientLevel.ClientLevelData data, LevelHeightAccessor level) {
        if (TeleportTransitionController.shouldUseGroundSkyBackground()) {
            return Double.NEGATIVE_INFINITY;
        }
        return data.getHorizonHeight(level);
    }

    @Unique
    private static void ta$maskPlayerChunkReposition(LevelRenderer renderer) {
        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client == null ? null : client.player;
        if (player == null) return;
        if (!ta$ensureLastCameraFields(renderer.getClass())) return;
        try {
            ta$lastCameraXField.setDouble(renderer, player.getX());
            ta$lastCameraYField.setDouble(renderer, player.getY());
            ta$lastCameraZField.setDouble(renderer, player.getZ());
            ta$lastCameraChunkXField.setInt(renderer, SectionPos.blockToSectionCoord(player.getX()));
            ta$lastCameraChunkYField.setInt(renderer, SectionPos.blockToSectionCoord(player.getY()));
            ta$lastCameraChunkZField.setInt(renderer, SectionPos.blockToSectionCoord(player.getZ()));
        } catch (IllegalAccessException ignored) {
        }
    }

    @Unique
    private static ViewArea ta$getViewArea(LevelRenderer renderer) {
        Field field = ta$getViewAreaField(renderer.getClass());
        if (field == null) return null;
        try {
            Object value = field.get(renderer);
            return value instanceof ViewArea ? (ViewArea) value : null;
        } catch (IllegalAccessException ignored) {
            return null;
        }
    }

    @Unique
    private static Field ta$getViewAreaField(Class<?> rendererClass) {
        if (ta$viewAreaField != null) return ta$viewAreaField;
        if (ta$viewAreaLookupFailed) return null;
        for (String fieldName : new String[]{"viewArea"}) {
            try {
                Field field = rendererClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                ta$viewAreaField = field;
                return field;
            } catch (NoSuchFieldException ignored) {
            }
        }
        ta$viewAreaLookupFailed = true;
        return null;
    }

    @Unique
    private static boolean ta$ensureLastCameraFields(Class<?> rendererClass) {
        if (ta$lastCameraXField != null && ta$lastCameraYField != null && ta$lastCameraZField != null && ta$lastCameraChunkXField != null && ta$lastCameraChunkYField != null && ta$lastCameraChunkZField != null) {
            return true;
        }
        if (ta$lastCameraLookupFailed) return false;
        ta$lastCameraXField = ta$getField(rendererClass, "lastCameraX");
        ta$lastCameraYField = ta$getField(rendererClass, "lastCameraY");
        ta$lastCameraZField = ta$getField(rendererClass, "lastCameraZ");
        ta$lastCameraChunkXField = ta$getField(rendererClass, "lastCameraChunkX");
        ta$lastCameraChunkYField = ta$getField(rendererClass, "lastCameraChunkY");
        ta$lastCameraChunkZField = ta$getField(rendererClass, "lastCameraChunkZ");
        boolean foundAll = ta$lastCameraXField != null && ta$lastCameraYField != null && ta$lastCameraZField != null && ta$lastCameraChunkXField != null && ta$lastCameraChunkYField != null && ta$lastCameraChunkZField != null;
        ta$lastCameraLookupFailed = !foundAll;
        return foundAll;
    }

    @Unique
    private static Field ta$getField(Class<?> owner, String... names) {
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
