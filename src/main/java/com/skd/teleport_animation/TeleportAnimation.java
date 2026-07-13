package com.skd.teleport_animation;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod("teleport_animation")
public final class TeleportAnimation {
    public static final String MOD_ID = "teleport_animation";

    public TeleportAnimation(IEventBus modBus) {
        TeleportServer.initialize();
        NeoForge.EVENT_BUS.addListener(TeleportAnimation::onServerTick);
        NeoForge.EVENT_BUS.addListener(TeleportAnimation::onEntityTeleport);
        modBus.addListener(TeleportAnimation::onRegisterPayloads);
    }

    private static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(
            TeleportNetworkPayloads.StartServerTeleportPayload.TYPE,
            TeleportNetworkPayloads.StartServerTeleportPayload.STREAM_CODEC,
            (payload, context) -> TeleportClientNetworking.handleStart(payload)
        );
        registrar.playToServer(
            TeleportNetworkPayloads.ServerTeleportAckPayload.TYPE,
            TeleportNetworkPayloads.ServerTeleportAckPayload.STREAM_CODEC,
            (payload, context) -> {
                var player = (ServerPlayer) context.player();
                if (player != null) TeleportServer.handleTeleportAck(player, payload.requestId());
            }
        );
        registrar.playToServer(
            TeleportNetworkPayloads.BypassNextServerTeleportPayload.TYPE,
            TeleportNetworkPayloads.BypassNextServerTeleportPayload.STREAM_CODEC,
            (payload, context) -> {
                var player = (ServerPlayer) context.player();
                if (player != null) TeleportServer.markNextServerTeleportBypassed(player);
            }
        );
    }

    private static void onEntityTeleport(EntityTeleportEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (TeleportServer.tryDelayExternalTeleport(player, (net.minecraft.server.level.ServerLevel) player.level(),
                event.getTargetX(), event.getTargetY(), event.getTargetZ(),
                java.util.Set.of(), player.getYRot(), player.getXRot(), false)) {
            event.setCanceled(true);
        }
    }

    private static void onServerTick(ServerTickEvent.Post event) {
        TeleportServer.tick(event.getServer());
    }
}