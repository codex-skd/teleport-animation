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
    private void ta$preemptLeawindThirdPersonCamera(BlockGetter level, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickProgress, CallbackInfo ci) {
        if (TeleportTransitionController.shouldPreemptLeawindThirdPersonCamera() && this.ta$applyCameraFrame(tickProgress)) {
            ci.cancel();
        }
    }

    @Inject(method = "setup", at = @At("TAIL"))
    private void ta$overrideCamera(BlockGetter level, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickProgress, CallbackInfo ci) {
        this.ta$applyCameraFrame(tickProgress);
    }

    private boolean ta$applyCameraFrame(float tickProgress) {
        TeleportTransitionController.CameraFrame frame = TeleportTransitionController.getCameraFrame(tickProgress);
        if (frame == null) {
            return false;
        }
        Camera camera = (Camera) (Object) this;
        Vec3 vanillaCameraPos = camera.getPosition();
        float liveYaw = camera.getYRot();
        float livePitch = camera.getXRot();
        Vec3 cameraPos = TeleportTransitionController.stabilizeCameraInsideBlock(frame.pos());
        boolean preserveLiveRotation = TeleportTransitionController.shouldPreservePostReleaseCameraRotation();
        if (preserveLiveRotation && !TeleportTransitionController.shouldApplyPostReleaseCameraOverrideAfterLeawind()) {
            cameraPos = TeleportTransitionController.followPostReleaseCameraPosition(cameraPos, vanillaCameraPos);
        }
        CameraAccessor accessor = (CameraAccessor) (Object) this;
        accessor.ta$setPosition(cameraPos);
        accessor.ta$setRotation(preserveLiveRotation ? liveYaw : frame.yaw(), preserveLiveRotation ? livePitch : frame.pitch());
        if (!TeleportTransitionController.shouldApplyPostReleaseCameraOverrideAfterLeawind()) {
            TeleportTransitionController.rememberTransitionCameraPosition(cameraPos, vanillaCameraPos, preserveLiveRotation ? Float.NaN : liveYaw, preserveLiveRotation ? Float.NaN : livePitch);
            TeleportTransitionController.requestTerrainVisibilityUpdate(cameraPos);
        }
        return true;
    }
}