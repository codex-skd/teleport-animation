/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.context.CommandContextBuilder
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.multiplayer.ClientPacketListener
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.Connection
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.PacketSendListener
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.phys.Vec3
 */
package dev.codex.gtaliketeleport;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.CommandContextBuilder;
import dev.codex.gtaliketeleport.DimensionIds;
import dev.codex.gtaliketeleport.GtaLikeTeleportClientNetworking;
import dev.codex.gtaliketeleport.GtaLikeTeleportConfig;
import dev.codex.gtaliketeleport.GtaLikeTeleportConfigScreen;
import dev.codex.gtaliketeleport.GtaLikeTeleportNetworkPayloads;
import dev.codex.gtaliketeleport.TeleportCommandMatcher;
import dev.codex.gtaliketeleport.TeleportTransitionController;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketSendListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.phys.Vec3;

public final class GtaLikeTeleportClient {
    private static final String[] COMMAND_ALIASES = new String[]{"grandtp", "gtp"};
    private static final String USAGE_MESSAGE = "Usage: /gtp or /grandtp on|off|status|player_freeze <on|off|status>";
    private static boolean bypassNextCommand;
    private static boolean bypassNextPacket;
    private static boolean bypassNextJourneyMapTeleport;
    private static boolean openConfigScreenNextTick;

    static void initializeClient() {
        GtaLikeTeleportConfig.load();
        GtaLikeTeleportNetworkPayloads.register();
        GtaLikeTeleportClientNetworking.registerReceivers();
    }

    static void tick(Minecraft client) {
        if (openConfigScreenNextTick) {
            openConfigScreenNextTick = false;
            if (client != null) {
                client.m_91152_((Screen)new GtaLikeTeleportConfigScreen(null));
            }
        }
        TeleportTransitionController.tick(client);
    }

    static void registerClientCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        for (String commandName : COMMAND_ALIASES) {
            dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)commandName).executes(context -> GtaLikeTeleportClient.executeLocalCommand(commandName))).then(Commands.m_82127_((String)"on").executes(context -> GtaLikeTeleportClient.executeLocalCommand(commandName + " on")))).then(Commands.m_82127_((String)"off").executes(context -> GtaLikeTeleportClient.executeLocalCommand(commandName + " off")))).then(Commands.m_82127_((String)"status").executes(context -> GtaLikeTeleportClient.executeLocalCommand(commandName + " status")))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"player_freeze").executes(context -> GtaLikeTeleportClient.executeLocalCommand(commandName + " player_freeze status"))).then(Commands.m_82127_((String)"on").executes(context -> GtaLikeTeleportClient.executeLocalCommand(commandName + " player_freeze on")))).then(Commands.m_82127_((String)"off").executes(context -> GtaLikeTeleportClient.executeLocalCommand(commandName + " player_freeze off")))).then(Commands.m_82127_((String)"status").executes(context -> GtaLikeTeleportClient.executeLocalCommand(commandName + " player_freeze status"))))).then(Commands.m_82129_((String)"argument", (ArgumentType)StringArgumentType.greedyString()).executes(context -> GtaLikeTeleportClient.executeLocalCommand(commandName + " " + StringArgumentType.getString((CommandContext)context, (String)"argument")))));
        }
    }

    private static int executeLocalCommand(String command) {
        GtaLikeTeleportClient.handleGtaTeleportCommand(Minecraft.m_91087_(), command);
        return 1;
    }

    public static boolean interceptOutgoingCommand(String command) {
        if (bypassNextCommand) {
            return true;
        }
        Minecraft client = Minecraft.m_91087_();
        if (GtaLikeTeleportClient.handleGtaTeleportCommand(client, command)) {
            return false;
        }
        if (!GtaLikeTeleportConfig.isEffectEnabled()) {
            return true;
        }
        if (!TeleportCommandMatcher.isTeleportCommand(command) || client.f_91074_ == null || client.m_91403_() == null) {
            return true;
        }
        if (!GtaLikeTeleportClient.canExecuteServerCommand(client, command)) {
            return true;
        }
        if (TeleportTransitionController.isRunning()) {
            return true;
        }
        TeleportTransitionController.start(client, command);
        return false;
    }

    public static boolean interceptOutgoingPacket(Connection connection, Packet<?> packet, PacketSendListener listener) {
        if (bypassNextPacket) {
            return true;
        }
        PacketTeleportTarget teleportTarget = GtaLikeTeleportClient.getTeleportPacketTarget(packet);
        if (teleportTarget == null) {
            return true;
        }
        Minecraft client = Minecraft.m_91087_();
        if (!GtaLikeTeleportConfig.isEffectEnabled() || client.f_91074_ == null || client.f_91073_ == null || client.m_91403_() == null) {
            return true;
        }
        if (TeleportTransitionController.isRunning()) {
            return true;
        }
        TeleportTransitionController.start(client, teleportTarget.targetFeet(), teleportTarget.targetDimensionId(), () -> GtaLikeTeleportClient.sendDeferredPacket(connection, packet, listener), !teleportTarget.keepMenuOpen());
        return false;
    }

    public static boolean interceptJourneyMapTeleport(Vec3 targetFeet, Runnable action) {
        return GtaLikeTeleportClient.interceptJourneyMapTeleport(targetFeet, null, action);
    }

    public static boolean interceptJourneyMapTeleport(Vec3 targetFeet, String targetDimensionId, Runnable action) {
        if (bypassNextJourneyMapTeleport) {
            return true;
        }
        Minecraft client = Minecraft.m_91087_();
        if (!GtaLikeTeleportConfig.isEffectEnabled() || client.f_91074_ == null || client.f_91073_ == null || client.m_91403_() == null) {
            return true;
        }
        if (TeleportTransitionController.isRunning()) {
            return true;
        }
        TeleportTransitionController.start(client, targetFeet, targetDimensionId, () -> GtaLikeTeleportClient.sendDeferredJourneyMapTeleport(action));
        return false;
    }

    static void handleServerTeleportRequest(GtaLikeTeleportNetworkPayloads.StartServerTeleportPayload payload) {
        Minecraft client = Minecraft.m_91087_();
        if (!GtaLikeTeleportClient.shouldPlayServerTeleportTransition(client, payload.source())) {
            GtaLikeTeleportClientNetworking.sendServerTeleportAck(payload.requestId());
            return;
        }
        TeleportTransitionController.start(client, GtaLikeTeleportClientNetworking.targetFeet(payload), GtaLikeTeleportClientNetworking.targetDimensionId(payload), () -> GtaLikeTeleportClientNetworking.sendServerTeleportAck(payload.requestId()), payload.source());
    }

    private static boolean shouldPlayServerTeleportTransition(Minecraft client, int source) {
        if (!GtaLikeTeleportConfig.isEffectEnabled() || client.f_91074_ == null || client.f_91073_ == null || client.m_91403_() == null) {
            return false;
        }
        if (TeleportTransitionController.isRunning()) {
            return false;
        }
        if (source == 2) {
            return GtaLikeTeleportConfig.isWarpPlateTransitionsEnabled();
        }
        return GtaLikeTeleportConfig.isExternalTeleportTransitionsEnabled();
    }

    static void sendDeferredCommand(String command) {
        Minecraft client = Minecraft.m_91087_();
        if (client.m_91403_() == null) {
            return;
        }
        GtaLikeTeleportClientNetworking.sendBypassNextServerTeleport();
        bypassNextCommand = true;
        try {
            client.m_91403_().m_246623_(command);
        }
        finally {
            bypassNextCommand = false;
        }
    }

    private static void sendDeferredPacket(Connection connection, Packet<?> packet, PacketSendListener listener) {
        GtaLikeTeleportClientNetworking.sendBypassNextServerTeleport();
        bypassNextPacket = true;
        try {
            if (listener == null) {
                connection.m_129512_(packet);
            } else {
                connection.m_243124_(packet, listener);
            }
        }
        finally {
            bypassNextPacket = false;
        }
    }

    private static void sendDeferredJourneyMapTeleport(Runnable action) {
        GtaLikeTeleportClientNetworking.sendBypassNextServerTeleport();
        bypassNextJourneyMapTeleport = true;
        try {
            action.run();
        }
        finally {
            bypassNextJourneyMapTeleport = false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static PacketTeleportTarget getTeleportPacketTarget(Packet<?> packet) {
        if (!(packet instanceof ServerboundCustomPayloadPacket)) {
            return null;
        }
        ServerboundCustomPayloadPacket customPayloadPacket = (ServerboundCustomPayloadPacket)packet;
        ResourceLocation id = customPayloadPacket.m_179589_();
        FriendlyByteBuf data = new FriendlyByteBuf(customPayloadPacket.m_179590_().copy());
        try {
            PacketTeleportTarget journeyMapTarget = GtaLikeTeleportClient.getJourneyMapTeleportTarget(data, id);
            if (journeyMapTarget != null) {
                PacketTeleportTarget packetTeleportTarget = journeyMapTarget;
                return packetTeleportTarget;
            }
            data.readerIndex(0);
            PacketTeleportTarget packetTeleportTarget = GtaLikeTeleportClient.getWaystonesTeleportTarget(data, id);
            return packetTeleportTarget;
        }
        finally {
            data.release();
        }
    }

    private static PacketTeleportTarget getJourneyMapTeleportTarget(FriendlyByteBuf payload, ResourceLocation id) {
        if (!id.m_135827_().equals("journeymap") || !id.m_135815_().equals("teleport_req")) {
            return null;
        }
        try {
            Vec3 targetFeet = new Vec3(payload.readDouble(), payload.readDouble(), payload.readDouble());
            String dimension = payload.isReadable() ? DimensionIds.normalize(payload.m_130136_(Short.MAX_VALUE)) : null;
            return new PacketTeleportTarget(targetFeet, dimension, false);
        }
        catch (RuntimeException ignored) {
            return null;
        }
    }

    private static PacketTeleportTarget getWaystonesTeleportTarget(FriendlyByteBuf payload, ResourceLocation id) {
        if (!id.m_135827_().equals("waystones")) {
            return null;
        }
        if (id.m_135815_().equals("select_waystone")) {
            WaystoneTarget target = GtaLikeTeleportClient.getWaystonesSelectedTarget(payload);
            return target == null ? null : new PacketTeleportTarget(target.targetFeet(), target.targetDimensionId(), true);
        }
        if (id.m_135815_().equals("inventory_button")) {
            WaystoneTarget target = GtaLikeTeleportClient.getWaystonesInventoryButtonTarget();
            return target == null ? null : new PacketTeleportTarget(target.targetFeet(), target.targetDimensionId(), false);
        }
        return null;
    }

    private static WaystoneTarget getWaystonesSelectedTarget(FriendlyByteBuf payload) {
        try {
            UUID waystoneUid = payload.m_130259_();
            Minecraft client = Minecraft.m_91087_();
            AbstractContainerMenu menu = client.f_91074_ == null ? null : client.f_91074_.f_36096_;
            WaystoneTarget menuTarget = GtaLikeTeleportClient.findWaystoneTargetInMenu(menu, waystoneUid);
            return menuTarget != null ? menuTarget : GtaLikeTeleportClient.findWaystoneTargetInStore(waystoneUid);
        }
        catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | RuntimeException | InvocationTargetException ignored) {
            return null;
        }
    }

    private static WaystoneTarget getWaystonesInventoryButtonTarget() {
        Minecraft client = Minecraft.m_91087_();
        if (client.f_91074_ == null) {
            return null;
        }
        try {
            Optional optional;
            Class<?> managerClass = Class.forName("net.blay09.mods.waystones.core.PlayerWaystoneManager");
            Method method = managerClass.getMethod("getInventoryButtonTarget", Player.class);
            Object result = method.invoke(null, client.f_91074_);
            if (!(result instanceof Optional) || (optional = (Optional)result).isEmpty()) {
                return null;
            }
            return GtaLikeTeleportClient.getWaystoneTarget(optional.get());
        }
        catch (ClassCastException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
            return null;
        }
    }

    private static WaystoneTarget findWaystoneTargetInMenu(Object menu, UUID waystoneUid) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (menu == null) {
            return null;
        }
        Method method = menu.getClass().getMethod("getWaystones", new Class[0]);
        Object result = method.invoke(menu, new Object[0]);
        if (!(result instanceof Collection)) {
            return null;
        }
        Collection waystones = (Collection)result;
        for (Object waystone : waystones) {
            if (!waystoneUid.equals(GtaLikeTeleportClient.readUuid(waystone, "getWaystoneUid"))) continue;
            return GtaLikeTeleportClient.getWaystoneTarget(waystone);
        }
        return null;
    }

    private static WaystoneTarget findWaystoneTargetInStore(UUID waystoneUid) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Optional optional;
        Class<?> clientClass = Class.forName("net.blay09.mods.waystones.client.WaystonesClient");
        Object store = clientClass.getMethod("getWaystonesStore", new Class[0]).invoke(null, new Object[0]);
        Object result = store.getClass().getMethod("getWaystoneById", UUID.class).invoke(store, waystoneUid);
        if (!(result instanceof Optional) || (optional = (Optional)result).isEmpty()) {
            return null;
        }
        return GtaLikeTeleportClient.getWaystoneTarget(optional.get());
    }

    private static WaystoneTarget getWaystoneTarget(Object waystone) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object result = waystone.getClass().getMethod("getPos", new Class[0]).invoke(waystone, new Object[0]);
        if (!(result instanceof BlockPos)) {
            return null;
        }
        BlockPos pos = (BlockPos)result;
        Vec3 targetFeet = new Vec3((double)pos.m_123341_() + 0.5, (double)pos.m_123342_(), (double)pos.m_123343_() + 0.5);
        return new WaystoneTarget(targetFeet, GtaLikeTeleportClient.readOptionalDimensionId(waystone, "getDimension"));
    }

    private static UUID readUuid(Object target, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = target.getClass().getMethod(methodName, new Class[0]);
        return (UUID)method.invoke(target, new Object[0]);
    }

    private static double readDouble(Object target, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = target.getClass().getMethod(methodName, new Class[0]);
        return ((Number)method.invoke(target, new Object[0])).doubleValue();
    }

    private static String readOptionalDimensionId(Object target, String methodName) {
        try {
            Method method = target.getClass().getMethod(methodName, new Class[0]);
            Object result = method.invoke(target, new Object[0]);
            if (result instanceof ResourceKey) {
                ResourceKey key = (ResourceKey)result;
                return DimensionIds.fromResourceKey(key);
            }
            return DimensionIds.normalize(result == null ? null : result.toString());
        }
        catch (ClassCastException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
            return null;
        }
    }

    private static boolean canExecuteServerCommand(Minecraft client, String command) {
        ClientPacketListener networkHandler = client.m_91403_();
        if (networkHandler == null) {
            return false;
        }
        String normalized = GtaLikeTeleportClient.normalizeCommand(command);
        if (normalized.isEmpty()) {
            return false;
        }
        ParseResults parseResults = networkHandler.m_105146_().parse(normalized, (Object)networkHandler.m_105137_());
        return !parseResults.getReader().canRead() && GtaLikeTeleportClient.hasExecutableCommand(parseResults.getContext());
    }

    private static boolean hasExecutableCommand(CommandContextBuilder<?> context) {
        for (CommandContextBuilder current = context; current != null; current = current.getChild()) {
            if (current.getCommand() == null) continue;
            return true;
        }
        return false;
    }

    private static String normalizeCommand(String command) {
        String normalized = command.strip();
        if (normalized.startsWith("/")) {
            normalized = normalized.substring(1).stripLeading();
        }
        return normalized;
    }

    private static String getLocalCommandName(String normalized) {
        int end;
        for (end = 0; end < normalized.length() && !Character.isWhitespace(normalized.charAt(end)); ++end) {
        }
        String commandName = normalized.substring(0, end).toLowerCase(Locale.ROOT);
        for (String alias : COMMAND_ALIASES) {
            if (!commandName.equals(alias)) continue;
            return normalized.substring(0, end);
        }
        return null;
    }

    private static boolean handleGtaTeleportCommand(Minecraft client, String command) {
        String commandName;
        String normalized = command.stripLeading();
        if (normalized.startsWith("/")) {
            normalized = normalized.substring(1).stripLeading();
        }
        if ((commandName = GtaLikeTeleportClient.getLocalCommandName(normalized)) == null) {
            return false;
        }
        String argument = normalized.length() == commandName.length() ? "" : normalized.substring(commandName.length()).strip();
        String lowerArgument = argument.toLowerCase(Locale.ROOT);
        if (lowerArgument.equals("on")) {
            boolean saved = GtaLikeTeleportConfig.setEffectEnabled(true);
            GtaLikeTeleportClient.sendCommandFeedback(client, true, saved);
            return true;
        }
        if (lowerArgument.equals("off")) {
            boolean saved = GtaLikeTeleportConfig.setEffectEnabled(false);
            GtaLikeTeleportClient.sendCommandFeedback(client, false, saved);
            return true;
        }
        if (lowerArgument.isEmpty()) {
            openConfigScreenNextTick = true;
            return true;
        }
        if (lowerArgument.equals("status")) {
            GtaLikeTeleportClient.sendFeedback(client, GtaLikeTeleportClient.createStateFeedback(GtaLikeTeleportConfig.isEffectEnabled(), true, ChatFormatting.GRAY));
            return true;
        }
        if (lowerArgument.equals("player_freeze") || lowerArgument.equals("player_freeze status")) {
            GtaLikeTeleportClient.sendFeedback(client, GtaLikeTeleportClient.createPlayerFreezeStateFeedback(GtaLikeTeleportConfig.isPlayerFreezeEnabled(), true, ChatFormatting.GRAY));
            return true;
        }
        if (lowerArgument.equals("player_freeze on")) {
            boolean saved;
            GtaLikeTeleportClient.sendFeedback(client, GtaLikeTeleportClient.createPlayerFreezeStateFeedback(true, saved, (saved = GtaLikeTeleportConfig.setPlayerFreezeEnabled(true)) ? ChatFormatting.GREEN : ChatFormatting.YELLOW));
            return true;
        }
        if (lowerArgument.equals("player_freeze off")) {
            boolean saved;
            GtaLikeTeleportClient.sendFeedback(client, GtaLikeTeleportClient.createPlayerFreezeStateFeedback(false, saved, (saved = GtaLikeTeleportConfig.setPlayerFreezeEnabled(false)) ? ChatFormatting.GREEN : ChatFormatting.YELLOW));
            return true;
        }
        GtaLikeTeleportClient.sendFeedback(client, (Component)Component.m_237113_((String)USAGE_MESSAGE).m_130940_(ChatFormatting.RED));
        return true;
    }

    private static void sendCommandFeedback(Minecraft client, boolean enabled, boolean saved) {
        GtaLikeTeleportClient.sendFeedback(client, GtaLikeTeleportClient.createStateFeedback(enabled, saved, saved ? ChatFormatting.GREEN : ChatFormatting.YELLOW));
    }

    private static Component createStateFeedback(boolean enabled, boolean saved, ChatFormatting formatting) {
        String state = enabled ? "ON" : "OFF";
        String message = "Grand Teleport:" + state + (saved ? "" : " (save failed)");
        return Component.m_237113_((String)message).m_130940_(formatting);
    }

    private static Component createPlayerFreezeStateFeedback(boolean enabled, boolean saved, ChatFormatting formatting) {
        String state = enabled ? "ON" : "OFF";
        String message = "Grand Teleport player_freeze:" + state + (saved ? "" : " (save failed)");
        return Component.m_237113_((String)message).m_130940_(formatting);
    }

    private static void sendFeedback(Minecraft client, Component message) {
        if (client.f_91074_ != null) {
            client.f_91074_.m_213846_(message);
        }
    }

    private record PacketTeleportTarget(Vec3 targetFeet, String targetDimensionId, boolean keepMenuOpen) {
    }

    private record WaystoneTarget(Vec3 targetFeet, String targetDimensionId) {
    }
}

