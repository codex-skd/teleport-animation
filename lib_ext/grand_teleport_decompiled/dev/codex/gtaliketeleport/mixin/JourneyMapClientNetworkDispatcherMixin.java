/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Pseudo
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package dev.codex.gtaliketeleport.mixin;

import dev.codex.gtaliketeleport.GtaLikeTeleportClient;
import java.lang.reflect.InvocationTargetException;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets={"journeymap.common.network.dispatch.ClientNetworkDispatcher"}, remap=false)
public abstract class JourneyMapClientNetworkDispatcherMixin {
    @Inject(method={"sendTeleportPacket(DIDLjava/lang/String;)V"}, at={@At(value="HEAD")}, cancellable=true, remap=false, require=0)
    private void gtalikeTeleport$interceptJourneyMapTeleport(double x, int y, double z, String dimension, CallbackInfo ci) {
        Vec3 targetFeet = new Vec3(x, (double)y, z);
        Runnable action = () -> this.invokeJourneyMapTeleport(new Class[]{Double.TYPE, Integer.TYPE, Double.TYPE, String.class}, x, y, z, dimension);
        if (!GtaLikeTeleportClient.interceptJourneyMapTeleport(targetFeet, dimension, action)) {
            ci.cancel();
        }
    }

    @Inject(method={"sendTeleportPacket(DIDLjava/lang/String;Ljava/lang/String;)V"}, at={@At(value="HEAD")}, cancellable=true, remap=false, require=0)
    private void gtalikeTeleport$interceptJourneyMapTeleportWithWaypoint(double x, int y, double z, String dimension, String waypointId, CallbackInfo ci) {
        Vec3 targetFeet = new Vec3(x, (double)y, z);
        Runnable action = () -> this.invokeJourneyMapTeleport(new Class[]{Double.TYPE, Integer.TYPE, Double.TYPE, String.class, String.class}, x, y, z, dimension, waypointId);
        if (!GtaLikeTeleportClient.interceptJourneyMapTeleport(targetFeet, dimension, action)) {
            ci.cancel();
        }
    }

    private void invokeJourneyMapTeleport(Class<?>[] parameterTypes, Object ... args) {
        try {
            this.getClass().getMethod("sendTeleportPacket", parameterTypes).invoke((Object)this, args);
        }
        catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException reflectiveOperationException) {
            // empty catch block
        }
    }
}

