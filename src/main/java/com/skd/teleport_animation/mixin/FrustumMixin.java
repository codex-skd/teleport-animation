package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Frustum.class)
abstract class FrustumMixin {

    @ModifyVariable(method = "prepare", at = @At("HEAD"), argsOnly = true, ordinal = 0, remap = false)
    private double teleportAnimation$modifyCamX(double camX) {
        Vec3 cameraPos = TeleportTransitionController.getTransitionCameraPositionForRendering();
        return cameraPos != null ? cameraPos.x : camX;
    }

    @ModifyVariable(method = "prepare", at = @At("HEAD"), argsOnly = true, ordinal = 1, remap = false)
    private double teleportAnimation$modifyCamY(double camY) {
        Vec3 cameraPos = TeleportTransitionController.getTransitionCameraPositionForRendering();
        return cameraPos != null ? cameraPos.y : camY;
    }

    @ModifyVariable(method = "prepare", at = @At("HEAD"), argsOnly = true, ordinal = 2, remap = false)
    private double teleportAnimation$modifyCamZ(double camZ) {
        Vec3 cameraPos = TeleportTransitionController.getTransitionCameraPositionForRendering();
        return cameraPos != null ? cameraPos.z : camZ;
    }
}
