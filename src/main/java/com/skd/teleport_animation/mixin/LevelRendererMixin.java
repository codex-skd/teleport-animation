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
    private void gtalikeTeleport$anchorViewAreaToTransitionCamera(net.minecraft.client.DeltaTracker deltaTracker, boolean renderBlockOutline, Camera camera, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldForceTerrainFrustumApply()) {
            return;
        }
        ViewArea viewArea = LevelRendererMixin.gtalikeTeleport$getViewArea((LevelRenderer)(Object) this);
        if (viewArea == null) {
            return;
        }
        Vec3 cameraPos = camera.position();
        viewArea.repositionCamera(SectionPos.of(BlockPos.containing(cameraPos)));
        LevelRendererMixin.gtalikeTeleport$maskPlayerChunkReposition((LevelRenderer)(Object) this);
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
        LocalPlayer player;
        Minecraft client = Minecraft.getInstance();
        LocalPlayer localPlayer = player = client == null ? null : client.player;
        if (player == null) {
            return;
        }
        if (!LevelRendererMixin.gtalikeTeleport$ensureLastCameraFields(renderer.getClass())) {
            return;
        }
        try {
            gtalikeTeleport$lastCameraXField.setDouble(renderer, player.getX());
            gtalikeTeleport$lastCameraYField.setDouble(renderer, player.getY());
            gtalikeTeleport$lastCameraZField.setDouble(renderer, player.getZ());
            gtalikeTeleport$lastCameraChunkXField.setInt(renderer, SectionPos.posToSectionCoord(player.getX()));
            gtalikeTeleport$lastCameraChunkYField.setInt(renderer, SectionPos.posToSectionCoord(player.getY()));
            gtalikeTeleport$lastCameraChunkZField.setInt(renderer, SectionPos.posToSectionCoord(player.getZ()));
        } catch (IllegalAccessException ignored) {
        }
    }

    @Unique
    private static ViewArea gtalikeTeleport$getViewArea(LevelRenderer renderer) {
        Field field = LevelRendererMixin.gtalikeTeleport$getViewAreaField(renderer.getClass());
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
    private static Field gtalikeTeleport$getViewAreaField(Class<?> rendererClass) {
        if (gtalikeTeleport$viewAreaField != null) {
            return gtalikeTeleport$viewAreaField;
        }
        if (gtalikeTeleport$viewAreaLookupFailed) {
            return null;
        }
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
        if (gtalikeTeleport$lastCameraLookupFailed) {
            return false;
        }
        gtalikeTeleport$lastCameraXField = LevelRendererMixin.gtalikeTeleport$getField(rendererClass, "lastCameraX");
        gtalikeTeleport$lastCameraYField = LevelRendererMixin.gtalikeTeleport$getField(rendererClass, "lastCameraY");
        gtalikeTeleport$lastCameraZField = LevelRendererMixin.gtalikeTeleport$getField(rendererClass, "lastCameraZ");
        gtalikeTeleport$lastCameraChunkXField = LevelRendererMixin.gtalikeTeleport$getField(rendererClass, "lastCameraChunkX");
        gtalikeTeleport$lastCameraChunkYField = LevelRendererMixin.gtalikeTeleport$getField(rendererClass, "lastCameraChunkY");
        gtalikeTeleport$lastCameraChunkZField = LevelRendererMixin.gtalikeTeleport$getField(rendererClass, "lastCameraChunkZ");
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
