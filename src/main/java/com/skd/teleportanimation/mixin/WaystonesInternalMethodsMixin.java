package com.skd.teleportanimation.mixin;

import com.skd.teleportanimation.TeleportServer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Method;

@Pseudo
@Mixin(targets = "net.blay09.mods.waystones.InternalMethodsImpl", remap = false)
public abstract class WaystonesInternalMethodsMixin {
    private static final Logger TA_LOG = LoggerFactory.getLogger("TA");

    @Inject(method = "tryTeleport", at = @At("HEAD"), cancellable = true, remap = false, require = 0)
    private void ta$delayWaystoneTeleport(Object context, CallbackInfoReturnable<Object> cir) {
        try {
            Entity entity = (Entity) context.getClass().getMethod("getEntity").invoke(context);
            if (!(entity instanceof ServerPlayer player)) return;

            Object waystone = context.getClass().getMethod("getTargetWaystone").invoke(context);
            if (waystone == null) return;

            BlockPos pos = (BlockPos) waystone.getClass().getMethod("getPos").invoke(waystone);
            if (pos == null) return;

            Vec3 targetFeet = new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            ResourceKey<Level> dimension = ((ServerLevel) player.level()).dimension();

            Object dimObj = waystone.getClass().getMethod("getDimension").invoke(waystone);
            if (dimObj instanceof ResourceKey) {
                dimension = (ResourceKey<Level>) dimObj;
            }

            TA_LOG.warn("ta$delayWaystoneTeleport: player={} target={} dim={}", player.getName().getString(), targetFeet, dimension);

            final ServerPlayer fPlayer = player;
            final Vec3 fTargetFeet = targetFeet;
            final ResourceKey<Level> fDimension = dimension;
            Object self = this;
            if (TeleportServer.scheduleServerTransition(fPlayer, 1, fTargetFeet, fDimension, () -> {
                try {
                    Class<?> ctxClass = Class.forName("net.blay09.mods.waystones.api.WaystoneTeleportContext");
                    Method tryTeleport = Class.forName("net.blay09.mods.waystones.api.InternalMethods")
                        .getMethod("tryTeleport", ctxClass);
                    tryTeleport.invoke(self, ctxClass.cast(context));
                } catch (Exception ex) {
                    TA_LOG.warn("ta$runWaystoneTeleport error: {}", ex.getMessage());
                }
            })) {
                cir.setReturnValue(null);
            }
        } catch (Exception e) {
            TA_LOG.warn("ta$delayWaystoneTeleport error: {}", e.getMessage());
        }
    }
}
