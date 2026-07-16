/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Pseudo
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package dev.codex.gtaliketeleport.mixin;

import dev.codex.gtaliketeleport.TeleportTransitionController;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets={"com.github.leawind.thirdperson.ThirdPerson"}, remap=false)
public abstract class LeawindThirdPersonLegacyMixin {
    @Inject(method={"isAvailable"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private static void gtalikeTeleport$disableLegacyLeawindDuringTransition(CallbackInfoReturnable<Boolean> cir) {
        if (TeleportTransitionController.shouldPreemptLeawindThirdPersonCamera()) {
            cir.setReturnValue((Object)false);
        }
    }
}

