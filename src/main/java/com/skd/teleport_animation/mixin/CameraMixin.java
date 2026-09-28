package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Camera.class, priority = 3000)
public abstract class CameraMixin {
    /*
     * Camera.update builds the cull frustum, view matrix and projection after
     * alignWithEntity. Applying the transition here keeps every derived value
     * in the same coordinate space as the rendered camera.
     */
    @Inject(
        method = "update",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;alignWithEntity(F)V", shift = At.Shift.AFTER),
        remap = false
    )
    private void teleportAnimation$overrideCameraBeforeCulling(DeltaTracker deltaTracker, CallbackInfo ci) {
        this.teleportAnimation$applyCameraFrame(((Camera) (Object) this).getCameraEntityPartialTicks(deltaTracker));
    }

    private boolean teleportAnimation$applyCameraFrame(float tickProgress) {
        TeleportTransitionController.CameraFrame frame = TeleportTransitionController.getCameraFrame(tickProgress);
        if (frame == null) {
            return false;
        }
        Camera camera = (Camera) (Object) this;
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
        if (!TeleportTransitionController.shouldApplyPostReleaseCameraOverrideAfterLeawind()) {
            TeleportTransitionController.rememberTransitionCameraPosition(cameraPos, vanillaCameraPos, preserveLiveRotation ? Float.NaN : liveYaw, preserveLiveRotation ? Float.NaN : livePitch);
            TeleportTransitionController.requestTerrainVisibilityUpdate(cameraPos);
        }
        return true;
    }
}
