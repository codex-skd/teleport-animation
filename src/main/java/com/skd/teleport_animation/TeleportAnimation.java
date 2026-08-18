package com.skd.teleport_animation;

import com.mojang.logging.LogUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import com.skd.teleport_animation.TeleportNetworkPayloads.StartServerTeleportPayload;
import com.skd.teleport_animation.TeleportNetworkPayloads.ServerTeleportAckPayload;
import com.skd.teleport_animation.TeleportNetworkPayloads.BypassNextServerTeleportPayload;

@Mod("teleport_animation")
public final class TeleportAnimation {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("teleport_animation", "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public TeleportAnimation() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        TeleportServer.initialize();
        MinecraftForge.EVENT_BUS.addListener(TeleportAnimation::onServerTick);
        modBus.addListener(this::onCommonSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        // Register our messages
        TeleportAnimation.CHANNEL.registerMessage(0, StartServerTeleportPayload.class, StartServerTeleportPayload::encode, StartServerTeleportPayload::decode, 
            (payload, context) -> TeleportClientNetworking.handleStart(payload));
        TeleportAnimation.CHANNEL.registerMessage(1, ServerTeleportAckPayload.class, ServerTeleportAckPayload::encode, ServerTeleportAckPayload::decode,
            (payload, context) -> {
                ServerPlayer player = context.get().getSender();
                if (player != null) {
                    TeleportServer.handleTeleportAck(player, payload.requestId());
                }
            });
        TeleportAnimation.CHANNEL.registerMessage(2, BypassNextServerTeleportPayload.class, BypassNextServerTeleportPayload::encode, BypassNextServerTeleportPayload::decode,
            (payload, context) -> {
                ServerPlayer player = context.get().getSender();
                if (player != null) {
                    TeleportServer.markNextServerTeleportBypassed(player);
                }
            });
    }

    private static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            TeleportServer.tick(event.getServer());
        }
    }
}