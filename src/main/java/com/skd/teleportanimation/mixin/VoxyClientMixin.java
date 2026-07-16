package com.skd.teleportanimation.mixin;

import com.skd.teleportanimation.TeleportTransitionController;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "me.cortex.voxy.client.VoxyClient", remap = false)
public abstract class VoxyClientMixin {
    @Inject(method = "disableSodiumChunkRender", at = @At("HEAD"), cancellable = true, require = 0)
    private static void gtalikeTeleport$preferVoxyTerrainDuringTransition(CallbackInfoReturnable<Boolean> cir) {
        if (TeleportTransitionController.shouldPreferVoxyOnlyTerrain()) {
            cir.setReturnValue(true);
        }
    }
}
