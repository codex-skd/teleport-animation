/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Camera
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.client.event.ViewportEvent$ComputeCameraAngles
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Pseudo
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package dev.codex.gtaliketeleport.mixin;

import dev.codex.gtaliketeleport.TeleportTransitionController;
import dev.codex.gtaliketeleport.mixin.CameraAccessor;
import net.minecraft.client.Camera;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.ViewportEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets={"com.github.leawind.thirdperson.forge.ThirdPersonEventsForge"}, remap=false)
public abstract class LeawindThirdPersonEventsForgeMixin {
    @Inject(method={"cameraSetupEvent"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private static void gtalikeTeleport$skipCameraSetupDuringTransition(ViewportEvent.ComputeCameraAngles event, CallbackInfo ci) {
        if (TeleportTransitionController.shouldPreemptLeawindThirdPersonCamera()) {
            ci.cancel();
        }
    }

    @Inject(method={"cameraSetupEvent"}, at={@At(value="TAIL")}, require=0)
    private static void gtalikeTeleport$restoreGtpCameraAfterLeawind(ViewportEvent.ComputeCameraAngles event, CallbackInfo ci) {
        if (!TeleportTransitionController.shouldApplyPostReleaseCameraOverrideAfterLeawind()) {
            return;
        }
        Camera camera = event.getCamera();
        if (camera == null) {
            return;
        }
        Vec3 leawindCameraPos = camera.m_90583_();
        float leawindYaw = camera.m_90590_();
        float leawindPitch = camera.m_90589_();
        TeleportTransitionController.CameraFrame frame = TeleportTransitionController.getCameraFrame((float)event.getPartialTick());
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
        CameraAccessor accessor = (CameraAccessor)camera;
        accessor.gtalikeTeleport$setPosition(cameraPos);
        accessor.gtalikeTeleport$setRotation(appliedYaw, appliedPitch);
        event.setYaw(appliedYaw);
        event.setPitch(appliedPitch);
        TeleportTransitionController.rememberTransitionCameraPosition(cameraPos, leawindCameraPos, preserveLiveRotation ? Float.NaN : leawindYaw, preserveLiveRotation ? Float.NaN : leawindPitch);
        TeleportTransitionController.requestTerrainVisibilityUpdate(cameraPos);
    }
}

