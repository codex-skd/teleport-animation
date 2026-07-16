/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.Camera
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel$ClientLevelData
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.client.renderer.GameRenderer
 *  net.minecraft.client.renderer.LevelRenderer
 *  net.minecraft.client.renderer.LightTexture
 *  net.minecraft.client.renderer.ViewArea
 *  net.minecraft.core.SectionPos
 *  net.minecraft.world.level.LevelHeightAccessor
 *  net.minecraft.world.phys.Vec3
 *  org.joml.Matrix4f
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package dev.codex.gtaliketeleport.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.codex.gtaliketeleport.TeleportTransitionController;
import java.lang.reflect.Field;
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

@Mixin(value={LevelRenderer.class})
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

    LevelRendererMixin() {
    }

    @Inject(method={"renderLevel"}, at={@At(value="HEAD")})
    private void gtalikeTeleport$anchorViewAreaToTransitionCamera(PoseStack poseStack, float partialTick, long finishTimeNano, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f projectionMatrix, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldForceTerrainFrustumApply()) {
            return;
        }
        ViewArea viewArea = LevelRendererMixin.gtalikeTeleport$getViewArea((LevelRenderer)this);
        if (viewArea == null) {
            return;
        }
        Vec3 cameraPos = camera.m_90583_();
        viewArea.m_110850_(cameraPos.f_82479_, cameraPos.f_82481_);
        LevelRendererMixin.gtalikeTeleport$maskPlayerChunkReposition((LevelRenderer)this);
    }

    @Redirect(method={"renderSky"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/multiplayer/ClientLevel$ClientLevelData;getHorizonHeight(Lnet/minecraft/world/level/LevelHeightAccessor;)D"))
    private double gtalikeTeleport$useGroundSkyHorizon(ClientLevel.ClientLevelData data, LevelHeightAccessor level) {
        if (TeleportTransitionController.shouldUseGroundSkyBackground()) {
            return Double.NEGATIVE_INFINITY;
        }
        return data.m_171687_(level);
    }

    @Unique
    private static void gtalikeTeleport$maskPlayerChunkReposition(LevelRenderer renderer) {
        LocalPlayer player;
        Minecraft client = Minecraft.m_91087_();
        LocalPlayer localPlayer = player = client == null ? null : client.f_91074_;
        if (player == null) {
            return;
        }
        if (!LevelRendererMixin.gtalikeTeleport$ensureLastCameraFields(renderer.getClass())) {
            return;
        }
        try {
            gtalikeTeleport$lastCameraXField.setDouble(renderer, player.m_20185_());
            gtalikeTeleport$lastCameraYField.setDouble(renderer, player.m_20186_());
            gtalikeTeleport$lastCameraZField.setDouble(renderer, player.m_20189_());
            gtalikeTeleport$lastCameraChunkXField.setInt(renderer, SectionPos.m_175552_((double)player.m_20185_()));
            gtalikeTeleport$lastCameraChunkYField.setInt(renderer, SectionPos.m_175552_((double)player.m_20186_()));
            gtalikeTeleport$lastCameraChunkZField.setInt(renderer, SectionPos.m_175552_((double)player.m_20189_()));
        }
        catch (IllegalAccessException illegalAccessException) {
            // empty catch block
        }
    }

    @Unique
    private static ViewArea gtalikeTeleport$getViewArea(LevelRenderer renderer) {
        Field field = LevelRendererMixin.gtalikeTeleport$getViewAreaField(renderer.getClass());
        if (field == null) {
            return null;
        }
        try {
            ViewArea viewArea;
            Object value = field.get(renderer);
            return value instanceof ViewArea ? (viewArea = (ViewArea)value) : null;
        }
        catch (IllegalAccessException ignored) {
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
        for (String fieldName : new String[]{"viewArea", "f_109469_"}) {
            try {
                Field field = rendererClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                gtalikeTeleport$viewAreaField = field;
                return field;
            }
            catch (NoSuchFieldException noSuchFieldException) {
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
        gtalikeTeleport$lastCameraXField = LevelRendererMixin.gtalikeTeleport$getField(rendererClass, "lastCameraX", "f_109419_");
        gtalikeTeleport$lastCameraYField = LevelRendererMixin.gtalikeTeleport$getField(rendererClass, "lastCameraY", "f_109420_");
        gtalikeTeleport$lastCameraZField = LevelRendererMixin.gtalikeTeleport$getField(rendererClass, "lastCameraZ", "f_109421_");
        gtalikeTeleport$lastCameraChunkXField = LevelRendererMixin.gtalikeTeleport$getField(rendererClass, "lastCameraChunkX", "f_109422_");
        gtalikeTeleport$lastCameraChunkYField = LevelRendererMixin.gtalikeTeleport$getField(rendererClass, "lastCameraChunkY", "f_109423_");
        gtalikeTeleport$lastCameraChunkZField = LevelRendererMixin.gtalikeTeleport$getField(rendererClass, "lastCameraChunkZ", "f_109424_");
        boolean foundAll = gtalikeTeleport$lastCameraXField != null && gtalikeTeleport$lastCameraYField != null && gtalikeTeleport$lastCameraZField != null && gtalikeTeleport$lastCameraChunkXField != null && gtalikeTeleport$lastCameraChunkYField != null && gtalikeTeleport$lastCameraChunkZField != null;
        gtalikeTeleport$lastCameraLookupFailed = !foundAll;
        return foundAll;
    }

    @Unique
    private static Field gtalikeTeleport$getField(Class<?> owner, String ... names) {
        for (String name : names) {
            try {
                Field field = owner.getDeclaredField(name);
                field.setAccessible(true);
                return field;
            }
            catch (NoSuchFieldException noSuchFieldException) {
            }
        }
        return null;
    }
}

