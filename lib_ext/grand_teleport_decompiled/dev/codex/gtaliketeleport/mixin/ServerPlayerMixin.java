/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.RelativeMovement
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package dev.codex.gtaliketeleport.mixin;

import dev.codex.gtaliketeleport.GtaLikeTeleportServer;
import java.util.Set;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.RelativeMovement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ServerPlayer.class})
public abstract class ServerPlayerMixin {
    @Inject(method={"teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDLjava/util/Set;FF)Z"}, at={@At(value="HEAD")}, cancellable=true)
    private void gtalikeTeleport$delayExternalTeleport(ServerLevel level, double x, double y, double z, Set<RelativeMovement> relatives, float yaw, float pitch, CallbackInfoReturnable<Boolean> cir) {
        if (GtaLikeTeleportServer.tryDelayExternalTeleport((ServerPlayer)this, level, x, y, z, relatives, yaw, pitch, false)) {
            cir.setReturnValue((Object)true);
        }
    }
}

