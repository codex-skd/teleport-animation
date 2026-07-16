package com.skd.teleportanimation.mixin;

import com.skd.teleportanimation.TeleportTransitionController;
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
    private void gtalikeTeleport$preemptLeawindThirdPersonCamera(BlockGetter level, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickProgress, CallbackInfo ci) {
        if (TeleportTransitionController.shouldPreemptLeawindThirdPersonCamera() && this.gtalikeTeleport$applyCameraFrame(tickProgress)) {
            ci.cancel();
        }
    }

    @Inject(method = "setup", at = @At("TAIL"))
    private void gtalikeTeleport$overrideCamera(BlockGetter level, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickProgress, CallbackInfo ci) {
        this.gtalikeTeleport$applyCameraFrame(tickProgress);
    }

    private boolean gtalikeTeleport$applyCameraFrame(float tickProgress) {
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
        accessor.gtalikeTeleport$setPosition(cameraPos);
        accessor.gtalikeTeleport$setRotation(preserveLiveRotation ? liveYaw : frame.yaw(), preserveLiveRotation ? livePitch : frame.pitch());
        if (!TeleportTransitionController.shouldApplyPostReleaseCameraOverrideAfterLeawind()) {
            TeleportTransitionController.rememberTransitionCameraPosition(cameraPos, vanillaCameraPos, preserveLiveRotation ? Float.NaN : liveYaw, preserveLiveRotation ? Float.NaN : livePitch);
            TeleportTransitionController.requestTerrainVisibilityUpdate(cameraPos);
        }
        return true;
    }
}
