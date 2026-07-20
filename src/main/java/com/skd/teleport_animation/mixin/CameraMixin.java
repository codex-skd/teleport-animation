package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Camera.class, priority = 3000)
public abstract class CameraMixin {
    @Inject(method = "extractRenderState", at = @At("TAIL"), remap = false)
    private void teleportAnimation$overrideCamera(CameraRenderState renderState, float partialTicks, CallbackInfo ci) {
        this.teleportAnimation$applyCameraFrame(renderState, partialTicks);
    }

    private boolean teleportAnimation$applyCameraFrame(CameraRenderState renderState, float tickProgress) {
        TeleportTransitionController.CameraFrame frame = TeleportTransitionController.getCameraFrame(tickProgress);
        if (frame == null) {
            return false;
        }
        Camera camera = (Camera)(Object) this;
        Vec3 vanillaCameraPos = camera.position();
        float liveYaw = camera.yRot();
        float livePitch = camera.xRot();
        Vec3 cameraPos = TeleportTransitionController.stabilizeCameraInsideBlock(frame.pos());
        boolean preserveLiveRotation = TeleportTransitionController.shouldPreservePostReleaseCameraRotation();
        if (preserveLiveRotation && !TeleportTransitionController.shouldApplyPostReleaseCameraOverrideAfterLeawind()) {
            cameraPos = TeleportTransitionController.followPostReleaseCameraPosition(cameraPos, vanillaCameraPos);
        }
        CameraAccessor accessor = (CameraAccessor) (Object) this;
        accessor.teleportAnimation$setPosition(cameraPos);
        accessor.teleportAnimation$setRotation(preserveLiveRotation ? liveYaw : frame.yaw(), preserveLiveRotation ? livePitch : frame.pitch());
        renderState.pos = cameraPos;
        renderState.xRot = preserveLiveRotation ? livePitch : frame.pitch();
        renderState.yRot = preserveLiveRotation ? liveYaw : frame.yaw();
        if (!TeleportTransitionController.shouldApplyPostReleaseCameraOverrideAfterLeawind()) {
            TeleportTransitionController.rememberTransitionCameraPosition(cameraPos, vanillaCameraPos, preserveLiveRotation ? Float.NaN : liveYaw, preserveLiveRotation ? Float.NaN : livePitch);
            TeleportTransitionController.requestTerrainVisibilityUpdate(cameraPos);
        }
        return true;
    }
}
