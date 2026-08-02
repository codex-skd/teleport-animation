package com.skd.teleport_animation;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContextBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

public final class TeleportClient {
    private static final Logger TA_LOG = LoggerFactory.getLogger("TA");
    private static final String[] COMMAND_ALIASES = new String[]{"ta", "tpanimation"};
    private static final String USAGE_MESSAGE = "Usage: /ta or /tpanimation on|off|status|player_freeze <on|off|status>";
    private static boolean bypassNextCommand;
    private static boolean bypassNextPacket;
    private static boolean bypassNextJourneyMapTeleport;
    private static boolean openConfigScreenNextTick;

    private TeleportClient() {
    }

    static void initializeClient() {
        TeleportConfig.load();
        openConfigScreenNextTick = false;
    }

    static void tick(Minecraft client) {
        if (openConfigScreenNextTick) {
            openConfigScreenNextTick = false;
            if (client != null) {
                client.setScreen(null);
            }
        }
        TeleportTransitionController.tick(client);
    }

    static void registerClientCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        for (String commandName : COMMAND_ALIASES) {
            dispatcher.register(
                ((LiteralArgumentBuilder<CommandSourceStack>) Commands.literal(commandName).executes(context -> executeLocalCommand(commandName)))
                    .then(Commands.literal("on").executes(context -> executeLocalCommand(commandName + " on")))
                    .then(Commands.literal("off").executes(context -> executeLocalCommand(commandName + " off")))
                    .then(Commands.literal("status").executes(context -> executeLocalCommand(commandName + " status")))
                    .then(Commands.literal("player_freeze").executes(context -> executeLocalCommand(commandName + " player_freeze status"))
                        .then(Commands.literal("on").executes(context -> executeLocalCommand(commandName + " player_freeze on")))
                        .then(Commands.literal("off").executes(context -> executeLocalCommand(commandName + " player_freeze off")))
                        .then(Commands.literal("status").executes(context -> executeLocalCommand(commandName + " player_freeze status"))))
                    .then(Commands.argument("argument", StringArgumentType.greedyString()).executes(context -> executeLocalCommand(commandName + " " + StringArgumentType.getString(context, "argument"))))
            );
        }
    }

    private static int executeLocalCommand(String command) {
        handleTeleportCommand(Minecraft.getInstance(), command);
        return 1;
    }

    public static boolean interceptOutgoingCommand(String command) {
        if (bypassNextCommand) {
            return true;
        }
        Minecraft client = Minecraft.getInstance();
        if (handleTeleportCommand(client, command)) {
            return false;
        }
        return true;
    }

    public static boolean interceptOutgoingPacket(Connection connection, Packet<?> packet, Object listener) {
        if (bypassNextPacket) {
            return true;
        }
        PacketTeleportTarget teleportTarget = getTeleportPacketTarget(packet);
        if (teleportTarget == null) {
            return true;
        }
        Minecraft client = Minecraft.getInstance();
        if (!TeleportConfig.isEffectEnabled() || client.player == null || client.level == null || client.getConnection() == null) {
            return true;
        }
        if (TeleportTransitionController.isRunning()) {
            return true;
        }
        TeleportTransitionController.start(client, teleportTarget.targetFeet(), teleportTarget.targetDimensionId(), () -> sendDeferredPacket(connection, packet), !teleportTarget.keepMenuOpen());
        return false;
    }

    public static boolean interceptJourneyMapTeleport(Vec3 targetFeet, Runnable action) {
        return interceptJourneyMapTeleport(targetFeet, null, action);
    }

    public static boolean interceptJourneyMapTeleport(Vec3 targetFeet, String targetDimensionId, Runnable action) {
        if (bypassNextJourneyMapTeleport) {
            return true;
        }
        Minecraft client = Minecraft.getInstance();
        if (!TeleportConfig.isEffectEnabled() || client.player == null || client.level == null || client.getConnection() == null) {
            return true;
        }
        if (TeleportTransitionController.isRunning()) {
            return true;
        }
        TeleportTransitionController.start(client, targetFeet, targetDimensionId, () -> sendDeferredJourneyMapTeleport(action));
        return false;
    }

    static void handleServerTeleportRequest(TeleportNetworkPayloads.StartServerTeleportPayload payload) {
        Minecraft client = Minecraft.getInstance();
        if (!shouldPlayServerTeleportTransition(client, payload.source())) {
            TeleportClientNetworking.sendServerTeleportAck(payload.requestId());
            return;
        }
        TeleportTransitionController.start(client, TeleportClientNetworking.targetFeet(payload), TeleportClientNetworking.targetDimensionId(payload),
            () -> TeleportClientNetworking.sendServerTeleportAck(payload.requestId()), payload.source());
    }

    private static boolean shouldPlayServerTeleportTransition(Minecraft client, int source) {
        if (!TeleportConfig.isEffectEnabled() || client.player == null || client.level == null || client.getConnection() == null) {
            return false;
        }
        if (TeleportTransitionController.isRunning()) {
            return false;
        }
        if (source == 2) {
            return TeleportConfig.isWarpPlateTransitionsEnabled();
        }
        return TeleportConfig.isExternalTeleportTransitionsEnabled();
    }
    static void sendDeferredCommand(String command) {
        Minecraft client = Minecraft.getInstance();
        if (client.getConnection() == null) {
            TA_LOG.warn("sendDeferredCommand: connection is null");
            return;
        }
        TA_LOG.warn("sendDeferredCommand: sending '{}'", command);
        TeleportClientNetworking.sendBypassNextServerTeleport();
        bypassNextCommand = true;
        try {
            client.getConnection().sendCommand(command);
        }
        finally {
            bypassNextCommand = false;
        }
    }

    private static void sendDeferredPacket(Connection connection, Packet<?> packet) {
        TeleportClientNetworking.sendBypassNextServerTeleport();
        bypassNextPacket = true;
        try {
            connection.send(packet);
        } finally {
            bypassNextPacket = false;
        }
    }

    private static void sendDeferredJourneyMapTeleport(Runnable action) {
        TeleportClientNetworking.sendBypassNextServerTeleport();
        bypassNextJourneyMapTeleport = true;
        try {
            action.run();
        } finally {
            bypassNextJourneyMapTeleport = false;
        }
    }

    private static PacketTeleportTarget getTeleportPacketTarget(Packet<?> packet) {
        return null;
    }

    private static PacketTeleportTarget getJourneyMapTeleportTarget(FriendlyByteBuf payload, ResourceLocation id) {
        if (!id.getNamespace().equals("journeymap") || !id.getPath().equals("teleport_req")) {
            return null;
        }
        try {
            Vec3 targetFeet = new Vec3(payload.readDouble(), payload.readDouble(), payload.readDouble());
            String dimension = payload.isReadable() ? DimensionIds.normalize(payload.readUtf(Short.MAX_VALUE)) : null;
            return new PacketTeleportTarget(targetFeet, dimension, false);
        } catch (RuntimeException ignored) {
            return null;
        }
    }

    private static PacketTeleportTarget getWaystonesTeleportTarget(FriendlyByteBuf payload, ResourceLocation id) {
        if (!id.getNamespace().equals("waystones")) {
            return null;
        }
        if (id.getPath().equals("select_waystone")) {
            WaystoneTarget target = getWaystonesSelectedTarget(payload);
            return target == null ? null : new PacketTeleportTarget(target.targetFeet(), target.targetDimensionId(), true);
        }
        if (id.getPath().equals("inventory_button")) {
            WaystoneTarget target = getWaystonesInventoryButtonTarget();
            return target == null ? null : new PacketTeleportTarget(target.targetFeet(), target.targetDimensionId(), false);
        }
        return null;
    }

    private static WaystoneTarget getWaystonesSelectedTarget(FriendlyByteBuf payload) {
        try {
            UUID waystoneUid = payload.readUUID();
            Minecraft client = Minecraft.getInstance();
            AbstractContainerMenu menu = client.player == null ? null : client.player.containerMenu;
            WaystoneTarget menuTarget = findWaystoneTargetInMenu(menu, waystoneUid);
            return menuTarget != null ? menuTarget : findWaystoneTargetInStore(waystoneUid);
        } catch (ReflectiveOperationException | RuntimeException ignored) {
            return null;
        }
    }

    private static WaystoneTarget getWaystonesInventoryButtonTarget() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null) {
            return null;
        }
        try {
            Class<?> managerClass = Class.forName("net.blay09.mods.waystones.core.PlayerWaystoneManager");
            Method method = managerClass.getMethod("getInventoryButtonTarget", Player.class);
            Object result = method.invoke(null, client.player);
            if (result instanceof Optional<?> optional && optional.isPresent()) {
                return getWaystoneTarget(optional.get());
            }
            return null;
        } catch (ReflectiveOperationException | ClassCastException ignored) {
            return null;
        }
    }

    private static WaystoneTarget findWaystoneTargetInMenu(Object menu, UUID waystoneUid) throws ReflectiveOperationException {
        if (menu == null) {
            return null;
        }
        Method method = menu.getClass().getMethod("getWaystones");
        Object result = method.invoke(menu);
        if (!(result instanceof Collection<?> waystones)) {
            return null;
        }
        for (Object waystone : waystones) {
            if (!waystoneUid.equals(readUuid(waystone, "getWaystoneUid"))) continue;
            return getWaystoneTarget(waystone);
        }
        return null;
    }

    private static WaystoneTarget findWaystoneTargetInStore(UUID waystoneUid) throws ReflectiveOperationException {
        Class<?> clientClass = Class.forName("net.blay09.mods.waystones.client.WaystonesClient");
        Object store = clientClass.getMethod("getWaystonesStore").invoke(null);
        Object result = store.getClass().getMethod("getWaystoneById", UUID.class).invoke(store, waystoneUid);
        if (result instanceof Optional<?> optional && optional.isPresent()) {
            return getWaystoneTarget(optional.get());
        }
        return null;
    }

    private static WaystoneTarget getWaystoneTarget(Object waystone) throws ReflectiveOperationException {
        Object result = waystone.getClass().getMethod("getPos").invoke(waystone);
        if (!(result instanceof BlockPos pos)) {
            return null;
        }
        Vec3 targetFeet = new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        return new WaystoneTarget(targetFeet, readOptionalDimensionId(waystone, "getDimension"));
    }

    private static UUID readUuid(Object target, String methodName) throws ReflectiveOperationException {
        Method method = target.getClass().getMethod(methodName);
        return (UUID) method.invoke(target);
    }

    private static String readOptionalDimensionId(Object target, String methodName) {
        try {
            Method method = target.getClass().getMethod(methodName);
            Object result = method.invoke(target);
            if (result instanceof ResourceKey<?> key) {
                return DimensionIds.fromResourceKey(key);
            }
            return DimensionIds.normalize(result == null ? null : result.toString());
        } catch (ReflectiveOperationException | ClassCastException ignored) {
            return null;
        }
    }

    private static boolean canExecuteServerCommand(Minecraft client, String command) {
        ClientPacketListener networkHandler = client.getConnection();
        if (networkHandler == null) return false;
        String normalized = normalizeCommand(command);
        if (normalized.isEmpty()) return false;
        var parseResults = networkHandler.getCommands().parse(normalized, networkHandler.getSuggestionsProvider());
        return !parseResults.getReader().canRead() && hasExecutableCommand(parseResults.getContext());
    }

    private static boolean hasExecutableCommand(CommandContextBuilder<?> context) {
        for (CommandContextBuilder<?> current = context; current != null; current = current.getChild()) {
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
        for (end = 0; end < normalized.length() && !Character.isWhitespace(normalized.charAt(end)); end++) {
        }
        String commandName = normalized.substring(0, end).toLowerCase(Locale.ROOT);
        for (String alias : COMMAND_ALIASES) {
            if (!commandName.equals(alias)) continue;
            return normalized.substring(0, end);
        }
        return null;
    }

    private static boolean handleTeleportCommand(Minecraft client, String command) {
        String normalized = command.stripLeading();
        if (normalized.startsWith("/")) {
            normalized = normalized.substring(1).stripLeading();
        }
        String commandName = getLocalCommandName(normalized);
        if (commandName == null) return false;

        String argument = normalized.length() == commandName.length() ? "" : normalized.substring(commandName.length()).strip();
        String lowerArgument = argument.toLowerCase(Locale.ROOT);

        if (lowerArgument.equals("on")) {
            boolean saved = TeleportConfig.setEffectEnabled(true);
            sendCommandFeedback(client, true, saved);
            return true;
        }
        if (lowerArgument.equals("off")) {
            boolean saved = TeleportConfig.setEffectEnabled(false);
            sendCommandFeedback(client, false, saved);
            return true;
        }
        if (lowerArgument.isEmpty()) {
            openConfigScreenNextTick = true;
            return true;
        }
        if (lowerArgument.equals("status")) {
            sendFeedback(client, createStateFeedback(TeleportConfig.isEffectEnabled(), true, ChatFormatting.GRAY));
            return true;
        }
        if (lowerArgument.equals("player_freeze") || lowerArgument.equals("player_freeze status")) {
            sendFeedback(client, createPlayerFreezeStateFeedback(TeleportConfig.isPlayerFreezeEnabled(), true, ChatFormatting.GRAY));
            return true;
        }
        if (lowerArgument.equals("player_freeze on")) {
            boolean saved = TeleportConfig.setPlayerFreezeEnabled(true);
            sendFeedback(client, createPlayerFreezeStateFeedback(true, saved, saved ? ChatFormatting.GREEN : ChatFormatting.YELLOW));
            return true;
        }
        if (lowerArgument.equals("player_freeze off")) {
            boolean saved = TeleportConfig.setPlayerFreezeEnabled(false);
            sendFeedback(client, createPlayerFreezeStateFeedback(false, saved, saved ? ChatFormatting.GREEN : ChatFormatting.YELLOW));
            return true;
        }
        sendFeedback(client, Component.literal(USAGE_MESSAGE).withStyle(ChatFormatting.RED));
        return true;
    }

    private static void sendCommandFeedback(Minecraft client, boolean enabled, boolean saved) {
        sendFeedback(client, createStateFeedback(enabled, saved, saved ? ChatFormatting.GREEN : ChatFormatting.YELLOW));
    }

    private static Component createStateFeedback(boolean enabled, boolean saved, ChatFormatting formatting) {
        String state = enabled ? "ON" : "OFF";
        String message = "Teleport Animation:" + state + (saved ? "" : " (save failed)");
        return Component.literal(message).withStyle(formatting);
    }

    private static Component createPlayerFreezeStateFeedback(boolean enabled, boolean saved, ChatFormatting formatting) {
        String state = enabled ? "ON" : "OFF";
        String message = "Teleport Animation player_freeze:" + state + (saved ? "" : " (save failed)");
        return Component.literal(message).withStyle(formatting);
    }

    private static void sendFeedback(Minecraft client, Component message) {
        if (client.player != null) {
            client.player.sendSystemMessage(message);
        }
    }

    private record PacketTeleportTarget(Vec3 targetFeet, String targetDimensionId, boolean keepMenuOpen) {
    }

    private record WaystoneTarget(Vec3 targetFeet, String targetDimensionId) {
    }
}
