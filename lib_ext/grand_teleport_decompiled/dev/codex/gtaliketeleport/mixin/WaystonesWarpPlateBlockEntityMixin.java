/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Pseudo
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Coerce
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package dev.codex.gtaliketeleport.mixin;

import dev.codex.gtaliketeleport.WaystonesWarpPlateHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets={"net.blay09.mods.waystones.block.entity.WarpPlateBlockEntity"}, remap=false)
public abstract class WaystonesWarpPlateBlockEntityMixin {
    @Inject(method={"teleportToWarpPlate"}, at={@At(value="HEAD")}, cancellable=true, remap=false, require=0)
    private void gtalikeTeleport$delayWarpPlateTeleportLegacy(Entity entity, @Coerce Object target, ItemStack stack, CallbackInfo ci) {
        if (WaystonesWarpPlateHandler.handleWarpPlateTeleport(this, entity, target, stack)) {
            ci.cancel();
        }
    }
}

