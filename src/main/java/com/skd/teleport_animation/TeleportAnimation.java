package com.skd.teleport_animation;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod("teleport_animation")
public final class TeleportAnimation {
    public static final String MOD_ID = "teleport_animation";

    public TeleportAnimation(IEventBus modBus) {
        GtaLikeTeleportServer.initialize();
        NeoForge.EVENT_BUS.addListener(TeleportAnimation::onServerTick);
        modBus.addListener(TeleportAnimation::onRegisterPayloads);
    }

    private static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(
            GtaLikeTeleportNetworkPayloads.StartServerTeleportPayload.TYPE,
            GtaLikeTeleportNetworkPayloads.StartServerTeleportPayload.STREAM_CODEC,
            (payload, context) -> GtaLikeTeleportClientNetworking.handleStart(payload)
        );
        registrar.playToServer(
            GtaLikeTeleportNetworkPayloads.ServerTeleportAckPayload.TYPE,
            GtaLikeTeleportNetworkPayloads.ServerTeleportAckPayload.STREAM_CODEC,
            (payload, context) -> {
                var player = (ServerPlayer) context.player();
                if (player != null) GtaLikeTeleportServer.handleTeleportAck(player, payload.requestId());
            }
        );
        registrar.playToServer(
            GtaLikeTeleportNetworkPayloads.BypassNextServerTeleportPayload.TYPE,
            GtaLikeTeleportNetworkPayloads.BypassNextServerTeleportPayload.STREAM_CODEC,
            (payload, context) -> {
                var player = (ServerPlayer) context.player();
                if (player != null) GtaLikeTeleportServer.markNextServerTeleportBypassed(player);
            }
        );
    }

    private static void onServerTick(ServerTickEvent.Post event) {
        GtaLikeTeleportServer.tick(event.getServer());
    }
}