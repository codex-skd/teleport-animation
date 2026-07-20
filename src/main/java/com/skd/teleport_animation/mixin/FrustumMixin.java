package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import java.lang.reflect.Field;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Frustum.class)
abstract class FrustumMixin {
    @Unique
    private static Field teleportAnimation$camXField;
    @Unique
    private static Field teleportAnimation$camYField;
    @Unique
    private static Field teleportAnimation$camZField;
    @Unique
    private static boolean teleportAnimation$lookupFailed;

    @Inject(method = "prepare", at = @At("TAIL"), remap = false)
    private void teleportAnimation$useTransitionCameraPosition(double camX, double camY, double camZ, CallbackInfo ci) {
        Vec3 cameraPos = TeleportTransitionController.getTransitionCameraPositionForRendering();
        if (cameraPos == null || !FrustumMixin.teleportAnimation$ensureFields(this.getClass())) {
            return;
        }
        try {
            teleportAnimation$camXField.setDouble(this, cameraPos.x);
            teleportAnimation$camYField.setDouble(this, cameraPos.y);
            teleportAnimation$camZField.setDouble(this, cameraPos.z);
        } catch (IllegalAccessException ignored) {
        }
    }

    @Unique
    private static boolean teleportAnimation$ensureFields(Class<?> owner) {
        if (teleportAnimation$camXField != null && teleportAnimation$camYField != null && teleportAnimation$camZField != null) {
            return true;
        }
        if (teleportAnimation$lookupFailed) {
            return false;
        }
        teleportAnimation$camXField = FrustumMixin.teleportAnimation$getField(owner, "camX");
        teleportAnimation$camYField = FrustumMixin.teleportAnimation$getField(owner, "camY");
        teleportAnimation$camZField = FrustumMixin.teleportAnimation$getField(owner, "camZ");
        boolean foundAll = teleportAnimation$camXField != null && teleportAnimation$camYField != null && teleportAnimation$camZField != null;
        teleportAnimation$lookupFailed = !foundAll;
        return foundAll;
    }

    @Unique
    private static Field teleportAnimation$getField(Class<?> owner, String... names) {
        for (String name : names) {
            try {
                Field field = owner.getDeclaredField(name);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException ignored) {
            }
        }
        return null;
    }
}
