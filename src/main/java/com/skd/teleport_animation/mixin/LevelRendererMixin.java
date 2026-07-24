package com.skd.teleport_animation.mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import com.mojang.logging.LogUtils;
import com.skd.teleport_animation.TeleportTransitionController;
import java.lang.reflect.Field;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.ViewArea;
import net.minecraft.client.renderer.chunk.ChunkSectionsToRender;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
abstract class LevelRendererMixin {
    @Unique
    private static final Logger LOGGER = LogUtils.getLogger();
    @Unique
    private static Field teleportAnimation$viewAreaField;
    @Unique
    private static boolean teleportAnimation$viewAreaLookupFailed;
    @Unique
    private static Field teleportAnimation$prevCamXField;
    @Unique
    private static Field teleportAnimation$prevCamYField;
    @Unique
    private static Field teleportAnimation$prevCamZField;
    @Unique
    private static Field teleportAnimation$lastCameraSectionXField;
    @Unique
    private static Field teleportAnimation$lastCameraSectionYField;
    @Unique
    private static Field teleportAnimation$lastCameraSectionZField;
    @Unique
    private static boolean teleportAnimation$lastCameraLookupFailed;

    @Inject(method = "renderLevel", at = @At("HEAD"), require = 0, remap = false)
    private void teleportAnimation$anchorViewAreaToTransitionCamera(GraphicsResourceAllocator allocator, DeltaTracker deltaTracker, boolean renderBlockOutline, CameraRenderState renderState, Matrix4fc matrix, GpuBufferSlice slice, Vector4f vector, boolean b, ChunkSectionsToRender chunkSections, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldForceTerrainFrustumApply()) {
            return;
        }
        ViewArea viewArea = LevelRendererMixin.teleportAnimation$getViewArea((LevelRenderer)(Object) this);
        if (viewArea == null) {
            LOGGER.info("TA viewArea is null, cannot reposition");
            return;
        }
        Vec3 cameraPos = renderState.pos;
        LOGGER.info("TA repositioning viewArea to camera ({}, {})", (int)cameraPos.x, (int)cameraPos.z);
        teleportAnimation$repositionViewArea(viewArea, cameraPos.x, cameraPos.z);
        LevelRendererMixin.teleportAnimation$maskPlayerChunkReposition((LevelRenderer)(Object) this);
    }

    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel$ClientLevelData;getHorizonHeight(Lnet/minecraft/world/level/LevelHeightAccessor;)D"), require = 0)
    private double teleportAnimation$useGroundSkyHorizon(ClientLevel.ClientLevelData data, LevelHeightAccessor level) {
        if (TeleportTransitionController.shouldUseGroundSkyBackground()) {
            return Double.NEGATIVE_INFINITY;
        }
        return data.getHorizonHeight(level);
    }

    @Unique
    private static void teleportAnimation$repositionViewArea(ViewArea viewArea, double x, double z) {
        try {
            viewArea.getClass().getMethod("repositionCamera", Double.TYPE, Double.TYPE).invoke(viewArea, x, z);
        } catch (ReflectiveOperationException e) {
            LOGGER.error("TA repositionViewArea failed", e);
        }
    }

    @Unique
    private static void teleportAnimation$maskPlayerChunkReposition(LevelRenderer renderer) {
        LocalPlayer player;
        Minecraft client = Minecraft.getInstance();
        LocalPlayer localPlayer = player = client == null ? null : client.player;
        if (player == null) {
            return;
        }
        if (!LevelRendererMixin.teleportAnimation$ensureLastCameraFields(renderer.getClass())) {
            return;
        }
        try {
            teleportAnimation$prevCamXField.setDouble(renderer, player.getX());
            teleportAnimation$prevCamYField.setDouble(renderer, player.getY());
            teleportAnimation$prevCamZField.setDouble(renderer, player.getZ());
            teleportAnimation$lastCameraSectionXField.setInt(renderer, SectionPos.posToSectionCoord(player.getX()));
            teleportAnimation$lastCameraSectionYField.setInt(renderer, SectionPos.posToSectionCoord(player.getY()));
            teleportAnimation$lastCameraSectionZField.setInt(renderer, SectionPos.posToSectionCoord(player.getZ()));
        } catch (IllegalAccessException ignored) {
        }
    }

    @Unique
    private static ViewArea teleportAnimation$getViewArea(LevelRenderer renderer) {
        Field field = LevelRendererMixin.teleportAnimation$getViewAreaField(renderer.getClass());
        if (field == null) {
            return null;
        }
        try {
            Object value = field.get(renderer);
            return value instanceof ViewArea ? (ViewArea) value : null;
        } catch (IllegalAccessException ignored) {
            return null;
        }
    }

    @Unique
    private static Field teleportAnimation$getViewAreaField(Class<?> rendererClass) {
        if (teleportAnimation$viewAreaField != null) {
            return teleportAnimation$viewAreaField;
        }
        if (teleportAnimation$viewAreaLookupFailed) {
            return null;
        }
        for (String fieldName : new String[]{"viewArea"}) {
            try {
                Field field = rendererClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                teleportAnimation$viewAreaField = field;
                return field;
            } catch (NoSuchFieldException ignored) {
            }
        }
        teleportAnimation$viewAreaLookupFailed = true;
        return null;
    }

    @Unique
    private static boolean teleportAnimation$ensureLastCameraFields(Class<?> rendererClass) {
        if (teleportAnimation$prevCamXField != null && teleportAnimation$prevCamYField != null && teleportAnimation$prevCamZField != null && teleportAnimation$lastCameraSectionXField != null && teleportAnimation$lastCameraSectionYField != null && teleportAnimation$lastCameraSectionZField != null) {
            return true;
        }
        if (teleportAnimation$lastCameraLookupFailed) {
            return false;
        }
        teleportAnimation$prevCamXField = LevelRendererMixin.teleportAnimation$getField(rendererClass, "prevCamX");
        teleportAnimation$prevCamYField = LevelRendererMixin.teleportAnimation$getField(rendererClass, "prevCamY");
        teleportAnimation$prevCamZField = LevelRendererMixin.teleportAnimation$getField(rendererClass, "prevCamZ");
        teleportAnimation$lastCameraSectionXField = LevelRendererMixin.teleportAnimation$getField(rendererClass, "lastCameraSectionX");
        teleportAnimation$lastCameraSectionYField = LevelRendererMixin.teleportAnimation$getField(rendererClass, "lastCameraSectionY");
        teleportAnimation$lastCameraSectionZField = LevelRendererMixin.teleportAnimation$getField(rendererClass, "lastCameraSectionZ");
        boolean foundAll = teleportAnimation$prevCamXField != null && teleportAnimation$prevCamYField != null && teleportAnimation$prevCamZField != null && teleportAnimation$lastCameraSectionXField != null && teleportAnimation$lastCameraSectionYField != null && teleportAnimation$lastCameraSectionZField != null;
        teleportAnimation$lastCameraLookupFailed = !foundAll;
        return foundAll;
    }

    @Unique
    private static Field teleportAnimation$getField(Class<?> owner, String... names) {
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
