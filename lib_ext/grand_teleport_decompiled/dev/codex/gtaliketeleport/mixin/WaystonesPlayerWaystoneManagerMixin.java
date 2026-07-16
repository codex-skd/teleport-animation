/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Pseudo
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Coerce
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package dev.codex.gtaliketeleport.mixin;

import dev.codex.gtaliketeleport.WaystonesTeleportHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets={"net.blay09.mods.waystones.core.PlayerWaystoneManager"}, remap=false)
public abstract class WaystonesPlayerWaystoneManagerMixin {
    @Inject(method={"tryTeleport(Lnet/blay09/mods/waystones/api/IWaystoneTeleportContext;)Lcom/mojang/datafixers/util/Either;"}, at={@At(value="HEAD")}, cancellable=true, remap=false, require=0)
    private static void gtalikeTeleport$delayWaystonesTeleport(@Coerce Object context, CallbackInfoReturnable<Object> cir) {
        Object result = WaystonesTeleportHandler.delayTeleportContext(context);
        if (result != null) {
            cir.setReturnValue(result);
        }
    }
}

