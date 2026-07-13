package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import java.lang.reflect.Field;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.ViewArea;
import net.minecraft.core.SectionPos;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
abstract class LevelRendererMixin {
    @Unique
    private static Field teleportAnimation$viewAreaField;
    @Unique
    private static boolean teleportAnimation$viewAreaLookupFailed;
    @Unique
    private static Field teleportAnimation$lastCameraXField;
    @Unique
    private static Field teleportAnimation$lastCameraYField;
    @Unique
    private static Field teleportAnimation$lastCameraZField;
    @Unique
    private static Field teleportAnimation$lastCameraChunkXField;
    @Unique
    private static Field teleportAnimation$lastCameraChunkYField;
    @Unique
    private static Field teleportAnimation$lastCameraChunkZField;
    @Unique
    private static boolean teleportAnimation$lastCameraLookupFailed;

    @Inject(method = "renderLevel", at = @At("HEAD"))
    private void teleportAnimation$anchorViewAreaToTransitionCamera(net.minecraft.client.DeltaTracker deltaTracker, boolean renderBlockOutline, Camera camera, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldForceTerrainFrustumApply()) {
            return;
        }
        ViewArea viewArea = LevelRendererMixin.teleportAnimation$getViewArea((LevelRenderer)(Object) this);
        if (viewArea == null) {
            return;
        }
        Vec3 cameraPos = camera.position();
        viewArea.repositionCamera(SectionPos.of(BlockPos.containing(cameraPos)));
        LevelRendererMixin.teleportAnimation$maskPlayerChunkReposition((LevelRenderer)(Object) this);
    }

    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel$ClientLevelData;getHorizonHeight(Lnet/minecraft/world/level/LevelHeightAccessor;)D"))
    private double teleportAnimation$useGroundSkyHorizon(ClientLevel.ClientLevelData data, LevelHeightAccessor level) {
        if (TeleportTransitionController.shouldUseGroundSkyBackground()) {
            return Double.NEGATIVE_INFINITY;
        }
        return data.getHorizonHeight(level);
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
            teleportAnimation$lastCameraXField.setDouble(renderer, player.getX());
            teleportAnimation$lastCameraYField.setDouble(renderer, player.getY());
            teleportAnimation$lastCameraZField.setDouble(renderer, player.getZ());
            teleportAnimation$lastCameraChunkXField.setInt(renderer, SectionPos.posToSectionCoord(player.getX()));
            teleportAnimation$lastCameraChunkYField.setInt(renderer, SectionPos.posToSectionCoord(player.getY()));
            teleportAnimation$lastCameraChunkZField.setInt(renderer, SectionPos.posToSectionCoord(player.getZ()));
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
        if (teleportAnimation$lastCameraXField != null && teleportAnimation$lastCameraYField != null && teleportAnimation$lastCameraZField != null && teleportAnimation$lastCameraChunkXField != null && teleportAnimation$lastCameraChunkYField != null && teleportAnimation$lastCameraChunkZField != null) {
            return true;
        }
        if (teleportAnimation$lastCameraLookupFailed) {
            return false;
        }
        teleportAnimation$lastCameraXField = LevelRendererMixin.teleportAnimation$getField(rendererClass, "lastCameraX");
        teleportAnimation$lastCameraYField = LevelRendererMixin.teleportAnimation$getField(rendererClass, "lastCameraY");
        teleportAnimation$lastCameraZField = LevelRendererMixin.teleportAnimation$getField(rendererClass, "lastCameraZ");
        teleportAnimation$lastCameraChunkXField = LevelRendererMixin.teleportAnimation$getField(rendererClass, "lastCameraChunkX");
        teleportAnimation$lastCameraChunkYField = LevelRendererMixin.teleportAnimation$getField(rendererClass, "lastCameraChunkY");
        teleportAnimation$lastCameraChunkZField = LevelRendererMixin.teleportAnimation$getField(rendererClass, "lastCameraChunkZ");
        boolean foundAll = teleportAnimation$lastCameraXField != null && teleportAnimation$lastCameraYField != null && teleportAnimation$lastCameraZField != null && teleportAnimation$lastCameraChunkXField != null && teleportAnimation$lastCameraChunkYField != null && teleportAnimation$lastCameraChunkZField != null;
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
