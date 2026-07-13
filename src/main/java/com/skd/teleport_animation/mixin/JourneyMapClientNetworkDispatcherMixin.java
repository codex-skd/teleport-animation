package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportClient;
import java.lang.reflect.InvocationTargetException;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "journeymap.common.network.dispatch.ClientNetworkDispatcher", remap = false)
public abstract class JourneyMapClientNetworkDispatcherMixin {
    @Inject(method = "sendTeleportPacket(DIDLjava/lang/String;)V", at = @At("HEAD"), cancellable = true, remap = false, require = 0)
    private void teleportAnimation$interceptJourneyMapTeleport(double x, int y, double z, String dimension, CallbackInfo ci) {
        Vec3 targetFeet = new Vec3(x, y, z);
        Runnable action = () -> this.invokeJourneyMapTeleport(new Class[]{Double.TYPE, Integer.TYPE, Double.TYPE, String.class}, x, y, z, dimension);
        if (!TeleportClient.interceptJourneyMapTeleport(targetFeet, dimension, action)) {
            ci.cancel();
        }
    }

    @Inject(method = "sendTeleportPacket(DIDLjava/lang/String;Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true, remap = false, require = 0)
    private void teleportAnimation$interceptJourneyMapTeleportWithWaypoint(double x, int y, double z, String dimension, String waypointId, CallbackInfo ci) {
        Vec3 targetFeet = new Vec3(x, y, z);
        Runnable action = () -> this.invokeJourneyMapTeleport(new Class[]{Double.TYPE, Integer.TYPE, Double.TYPE, String.class, String.class}, x, y, z, dimension, waypointId);
        if (!TeleportClient.interceptJourneyMapTeleport(targetFeet, dimension, action)) {
            ci.cancel();
        }
    }

    private void invokeJourneyMapTeleport(Class<?>[] parameterTypes, Object... args) {
        try {
            this.getClass().getMethod("sendTeleportPacket", parameterTypes).invoke(this, args);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
        }
    }
}
