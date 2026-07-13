package com.skd.teleport_animation;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = "teleport_animation", value = Dist.CLIENT)
public final class TeleportAnimationClient {
    private TeleportAnimationClient() {
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            GtaLikeTeleportClient.initializeClient();
            NeoForge.EVENT_BUS.addListener(TeleportAnimationClient::onClientTick);
            NeoForge.EVENT_BUS.addListener(TeleportAnimationClient::onRegisterClientCommands);
        });
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        GtaLikeTeleportClient.tick(Minecraft.getInstance());
    }

    private static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        GtaLikeTeleportClient.registerClientCommands((CommandDispatcher<CommandSourceStack>) event.getDispatcher());
    }
}