package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Create: Kinetic Waypoints changes its own teleport method between releases, so instead of hooking it we
 * hook the vanilla ServerPlayer#teleportTo it always ends up calling, and only act when that call comes from
 * a Kinetic Waypoints frame. Applied only when Kinetic Waypoints is loaded (TeleportMixinPlugin).
 */
@Mixin(ServerPlayer.class)
public abstract class KineticWaypointsTeleportMixin {
    private static final Logger TA_LOGGER = LoggerFactory.getLogger("teleport_animation");
    private static final String KINETIC_PACKAGE = "com.victorio.createkineticwaypoints.";

    @Inject(method = "teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDFF)V", at = @At("HEAD"), cancellable = true)
    private void ta$delayKineticTeleport(ServerLevel level, double x, double y, double z, float yaw, float pitch, CallbackInfo ci) {
        if (!StackWalker.getInstance().walk(frames -> frames.anyMatch(f -> f.getClassName().startsWith(KINETIC_PACKAGE)))) {
            return;
        }
        ServerPlayer player = (ServerPlayer) (Object) this;
        // Re-entry from the delayed action is bypassed inside scheduleServerTransition, so it returns false and the original runs.
        if (TeleportServer.scheduleServerTransition(player, 1, new Vec3(x, y, z), level.dimension(), () -> player.teleportTo(level, x, y, z, yaw, pitch))) {
            TA_LOGGER.info("Kinetic Waypoints teleport intercepted");
            ci.cancel();
        }
    }
}
