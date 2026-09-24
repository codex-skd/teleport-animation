package com.skd.teleport_animation;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;

@EventBusSubscriber(modid = "teleport_animation", value = Dist.CLIENT)
public final class ClientModEvents {
    private ClientModEvents() {
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        Minecraft client = Minecraft.getInstance();
        if (client.options != null) {
            client.options.setCameraType(CameraType.FIRST_PERSON);
            client.options.hideGui = false;
        }
        TeleportClient.initializeClient();
        NeoForge.EVENT_BUS.addListener(ClientModEvents::onClientTick);
        NeoForge.EVENT_BUS.addListener(ClientModEvents::onRegisterClientCommands);
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        TeleportClient.tick(Minecraft.getInstance());
    }

    private static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        TeleportClient.registerClientCommands((CommandDispatcher<CommandSourceStack>) event.getDispatcher());
    }
}
