package com.skd.teleport_animation;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod("teleport_animation")
public final class TeleportAnimation {
    public static final String MOD_ID = "teleport_animation";

    public TeleportAnimation(IEventBus modBus, ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, TeleportConfig.SPEC);
        TeleportServer.initialize();
        NeoForge.EVENT_BUS.addListener(TeleportAnimation::onServerTick);
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

    private static void onServerTick(ServerTickEvent.Post event) {
        TeleportServer.tick(event.getServer());
    }
}