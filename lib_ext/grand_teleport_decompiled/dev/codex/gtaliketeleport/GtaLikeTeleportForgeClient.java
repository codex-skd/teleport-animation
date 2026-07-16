/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.client.ConfigScreenHandler$ConfigScreenFactory
 *  net.minecraftforge.client.event.RegisterClientCommandsEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.ModLoadingContext
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
 */
package dev.codex.gtaliketeleport;

import com.mojang.brigadier.CommandDispatcher;
import dev.codex.gtaliketeleport.GtaLikeTeleportClient;
import dev.codex.gtaliketeleport.GtaLikeTeleportConfigScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid="gtalike_teleport", value={Dist.CLIENT}, bus=Mod.EventBusSubscriber.Bus.MOD)
public final class GtaLikeTeleportForgeClient {
    private GtaLikeTeleportForgeClient() {
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            GtaLikeTeleportClient.initializeClient();
            ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, parent) -> new GtaLikeTeleportConfigScreen((Screen)parent)));
            MinecraftForge.EVENT_BUS.addListener(GtaLikeTeleportForgeClient::onClientTick);
            MinecraftForge.EVENT_BUS.addListener(GtaLikeTeleportForgeClient::onRegisterClientCommands);
        });
    }

    private static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            GtaLikeTeleportClient.tick(Minecraft.m_91087_());
        }
    }

    private static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        GtaLikeTeleportClient.registerClientCommands((CommandDispatcher<CommandSourceStack>)event.getDispatcher());
    }
}

