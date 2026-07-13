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
    private static Field gtalikeTeleport$camXField;
    @Unique
    private static Field gtalikeTeleport$camYField;
    @Unique
    private static Field gtalikeTeleport$camZField;
    @Unique
    private static boolean gtalikeTeleport$lookupFailed;

    @Inject(method = "prepare", at = @At("TAIL"))
    private void gtalikeTeleport$useTransitionCameraPosition(double camX, double camY, double camZ, CallbackInfo ci) {
        Vec3 cameraPos = TeleportTransitionController.getTransitionCameraPositionForRendering();
        if (cameraPos == null || !FrustumMixin.gtalikeTeleport$ensureFields(this.getClass())) {
            return;
        }
        try {
            gtalikeTeleport$camXField.setDouble(this, cameraPos.x);
            gtalikeTeleport$camYField.setDouble(this, cameraPos.y);
            gtalikeTeleport$camZField.setDouble(this, cameraPos.z);
        } catch (IllegalAccessException ignored) {
        }
    }

    @Unique
    private static boolean gtalikeTeleport$ensureFields(Class<?> owner) {
        if (gtalikeTeleport$camXField != null && gtalikeTeleport$camYField != null && gtalikeTeleport$camZField != null) {
            return true;
        }
        if (gtalikeTeleport$lookupFailed) {
            return false;
        }
        gtalikeTeleport$camXField = FrustumMixin.gtalikeTeleport$getField(owner, "camX");
        gtalikeTeleport$camYField = FrustumMixin.gtalikeTeleport$getField(owner, "camY");
        gtalikeTeleport$camZField = FrustumMixin.gtalikeTeleport$getField(owner, "camZ");
        boolean foundAll = gtalikeTeleport$camXField != null && gtalikeTeleport$camYField != null && gtalikeTeleport$camZField != null;
        gtalikeTeleport$lookupFailed = !foundAll;
        return foundAll;
    }

    @Unique
    private static Field gtalikeTeleport$getField(Class<?> owner, String... names) {
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
