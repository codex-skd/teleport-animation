package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Camera.class, priority = 3000)
public abstract class CameraMixin {
    @Inject(method = "setup", at = {
        @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;move(DDD)V", ordinal = 0, shift = At.Shift.BEFORE),
        @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;move(DDD)V", ordinal = 1, shift = At.Shift.BEFORE)
    }, cancellable = true, require = 0)
    private void teleportAnimation$preemptLeawindThirdPersonCamera(BlockGetter level, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickProgress, CallbackInfo ci) {
        if (TeleportTransitionController.shouldPreemptLeawindThirdPersonCamera() && this.teleportAnimation$applyCameraFrame(tickProgress)) {
            ci.cancel();
        }
    }

    @Inject(method = "setup", at = @At("TAIL"))
    private void teleportAnimation$overrideCamera(BlockGetter level, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickProgress, CallbackInfo ci) {
        this.teleportAnimation$applyCameraFrame(tickProgress);
    }

    private boolean teleportAnimation$applyCameraFrame(float tickProgress) {
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
        if (!TeleportTransitionController.shouldApplyPostReleaseCameraOverrideAfterLeawind()) {
            TeleportTransitionController.rememberTransitionCameraPosition(cameraPos, vanillaCameraPos, preserveLiveRotation ? Float.NaN : liveYaw, preserveLiveRotation ? Float.NaN : livePitch);
            TeleportTransitionController.requestTerrainVisibilityUpdate(cameraPos);
        }
        return true;
    }
}
