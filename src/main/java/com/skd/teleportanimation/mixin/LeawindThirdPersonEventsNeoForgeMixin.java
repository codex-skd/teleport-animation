package com.skd.teleportanimation.mixin;

import com.skd.teleportanimation.TeleportTransitionController;
import com.skd.teleportanimation.mixin.CameraAccessor;
import net.minecraft.client.Camera;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.ViewportEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "com.github.leawind.thirdperson.neoforge.ThirdPersonEventsNeoForge", remap = false)
public abstract class LeawindThirdPersonEventsNeoForgeMixin {
    @Inject(method = "cameraSetupEvent", at = @At("HEAD"), cancellable = true, require = 0)
    private static void ta$skipCameraSetupDuringTransition(ViewportEvent.ComputeCameraAngles event, CallbackInfo ci) {
        if (TeleportTransitionController.shouldPreemptLeawindThirdPersonCamera()) {
            ci.cancel();
        }
    }

    @Inject(method = "cameraSetupEvent", at = @At("TAIL"), require = 0)
    private static void ta$restoreLeawindCameraAfter(ViewportEvent.ComputeCameraAngles event, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldApplyPostReleaseCameraOverrideAfterLeawind()) {
            return;
        }
        Camera camera = event.getCamera();
        if (camera == null) return;
        Vec3 leawindCameraPos = camera.getPosition();
        float leawindYaw = camera.getYRot();
        float leawindPitch = camera.getXRot();
        TeleportTransitionController.CameraFrame frame = TeleportTransitionController.getCameraFrame((float) event.getPartialTick());
        if (frame == null) return;
        Vec3 cameraPos = TeleportTransitionController.stabilizeCameraInsideBlock(frame.pos());
        boolean preserveLiveRotation = TeleportTransitionController.shouldPreservePostReleaseCameraRotation();
        if (preserveLiveRotation) {
            cameraPos = TeleportTransitionController.followPostReleaseCameraPosition(cameraPos, leawindCameraPos);
        }
        float appliedYaw = preserveLiveRotation ? leawindYaw : frame.yaw();
        float appliedPitch = preserveLiveRotation ? leawindPitch : frame.pitch();
        CameraAccessor accessor = (CameraAccessor) camera;
        accessor.ta$setPosition(cameraPos);
        accessor.ta$setRotation(appliedYaw, appliedPitch);
        event.setYaw(appliedYaw);
        event.setPitch(appliedPitch);
        TeleportTransitionController.rememberTransitionCameraPosition(cameraPos, leawindCameraPos, preserveLiveRotation ? Float.NaN : leawindYaw, preserveLiveRotation ? Float.NaN : leawindPitch);
        TeleportTransitionController.requestTerrainVisibilityUpdate(cameraPos);
    }
}
