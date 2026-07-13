package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import com.skd.teleport_animation.mixin.CameraAccessor;
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
    private static void gtalikeTeleport$skipCameraSetupDuringTransition(ViewportEvent.ComputeCameraAngles event, CallbackInfo ci) {
        if (TeleportTransitionController.shouldPreemptLeawindThirdPersonCamera()) {
            ci.cancel();
        }
    }

    @Inject(method = "cameraSetupEvent", at = @At("TAIL"), require = 0)
    private static void gtalikeTeleport$restoreGtpCameraAfterLeawind(ViewportEvent.ComputeCameraAngles event, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldApplyPostReleaseCameraOverrideAfterLeawind()) {
            return;
        }
        Camera camera = event.getCamera();
        if (camera == null) {
            return;
        }
        Vec3 leawindCameraPos = camera.position();
        float leawindYaw = camera.yRot();
        float leawindPitch = camera.xRot();
        TeleportTransitionController.CameraFrame frame = TeleportTransitionController.getCameraFrame((float) event.getPartialTick());
        if (frame == null) {
            return;
        }
        Vec3 cameraPos = TeleportTransitionController.stabilizeCameraInsideBlock(frame.pos());
        boolean preserveLiveRotation = TeleportTransitionController.shouldPreservePostReleaseCameraRotation();
        if (preserveLiveRotation) {
            cameraPos = TeleportTransitionController.followPostReleaseCameraPosition(cameraPos, leawindCameraPos);
        }
        float appliedYaw = preserveLiveRotation ? leawindYaw : frame.yaw();
        float appliedPitch = preserveLiveRotation ? leawindPitch : frame.pitch();
        CameraAccessor accessor = (CameraAccessor) camera;
        accessor.gtalikeTeleport$setPosition(cameraPos);
        accessor.gtalikeTeleport$setRotation(appliedYaw, appliedPitch);
        event.setYaw(appliedYaw);
        event.setPitch(appliedPitch);
        TeleportTransitionController.rememberTransitionCameraPosition(cameraPos, leawindCameraPos, preserveLiveRotation ? Float.NaN : leawindYaw, preserveLiveRotation ? Float.NaN : leawindPitch);
        TeleportTransitionController.requestTerrainVisibilityUpdate(cameraPos);
    }
}
