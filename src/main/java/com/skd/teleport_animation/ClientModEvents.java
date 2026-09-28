package com.skd.teleport_animation;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "teleport_animation", value = Dist.CLIENT)
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
        MinecraftForge.EVENT_BUS.addListener(ClientModEvents::onClientTick);
        MinecraftForge.EVENT_BUS.addListener(ClientModEvents::onRegisterClientCommands);
    }

    private static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            TeleportClient.tick(Minecraft.getInstance());
        }
    }

    private static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        TeleportClient.registerClientCommands((CommandDispatcher<CommandSourceStack>) event.getDispatcher());
    }
}
