package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportTransitionController;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(Frustum.class)
abstract class FrustumMixin {
    @Unique
    private static Field ta$camXField;
    @Unique
    private static Field ta$camYField;
    @Unique
    private static Field ta$camZField;
    @Unique
    private static boolean ta$lookupFailed;

    @Inject(method = "prepare", at = @At("TAIL"))
    private void ta$useTransitionCameraPosition(double camX, double camY, double camZ, CallbackInfo ci) {
        Vec3 cameraPos = TeleportTransitionController.getTransitionCameraPositionForRendering();
        if (cameraPos == null || !ta$ensureFields(this.getClass())) {
            return;
        }
        try {
            ta$camXField.setDouble(this, cameraPos.x);
            ta$camYField.setDouble(this, cameraPos.y);
            ta$camZField.setDouble(this, cameraPos.z);
        } catch (IllegalAccessException ignored) {
        }
    }

    @Unique
    private static boolean ta$ensureFields(Class<?> owner) {
        if (ta$camXField != null && ta$camYField != null && ta$camZField != null) {
            return true;
        }
        if (ta$lookupFailed) {
            return false;
        }
        ta$camXField = ta$getField(owner, "camX");
        ta$camYField = ta$getField(owner, "camY");
        ta$camZField = ta$getField(owner, "camZ");
        boolean foundAll = ta$camXField != null && ta$camYField != null && ta$camZField != null;
        ta$lookupFailed = !foundAll;
        return foundAll;
    }

    @Unique
    private static Field ta$getField(Class<?> owner, String... names) {
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
