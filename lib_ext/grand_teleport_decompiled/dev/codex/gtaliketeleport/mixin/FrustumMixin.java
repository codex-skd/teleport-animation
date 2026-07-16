/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package dev.codex.gtaliketeleport.mixin;

import dev.codex.gtaliketeleport.TeleportTransitionController;
import java.lang.reflect.Field;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Frustum.class})
abstract class FrustumMixin {
    @Unique
    private static Field gtalikeTeleport$camXField;
    @Unique
    private static Field gtalikeTeleport$camYField;
    @Unique
    private static Field gtalikeTeleport$camZField;
    @Unique
    private static boolean gtalikeTeleport$lookupFailed;

    FrustumMixin() {
    }

    @Inject(method={"prepare"}, at={@At(value="TAIL")})
    private void gtalikeTeleport$useTransitionCameraPosition(double camX, double camY, double camZ, CallbackInfo ci) {
        Vec3 cameraPos = TeleportTransitionController.getTransitionCameraPositionForRendering();
        if (cameraPos == null || !FrustumMixin.gtalikeTeleport$ensureFields(this.getClass())) {
            return;
        }
        try {
            gtalikeTeleport$camXField.setDouble(this, cameraPos.f_82479_);
            gtalikeTeleport$camYField.setDouble(this, cameraPos.f_82480_);
            gtalikeTeleport$camZField.setDouble(this, cameraPos.f_82481_);
        }
        catch (IllegalAccessException illegalAccessException) {
            // empty catch block
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
        gtalikeTeleport$camXField = FrustumMixin.gtalikeTeleport$getField(owner, "camX", "f_112996_");
        gtalikeTeleport$camYField = FrustumMixin.gtalikeTeleport$getField(owner, "camY", "f_112997_");
        gtalikeTeleport$camZField = FrustumMixin.gtalikeTeleport$getField(owner, "camZ", "f_112998_");
        boolean foundAll = gtalikeTeleport$camXField != null && gtalikeTeleport$camYField != null && gtalikeTeleport$camZField != null;
        gtalikeTeleport$lookupFailed = !foundAll;
        return foundAll;
    }

    @Unique
    private static Field gtalikeTeleport$getField(Class<?> owner, String ... names) {
        for (String name : names) {
            try {
                Field field = owner.getDeclaredField(name);
                field.setAccessible(true);
                return field;
            }
            catch (NoSuchFieldException noSuchFieldException) {
            }
        }
        return null;
    }
}

