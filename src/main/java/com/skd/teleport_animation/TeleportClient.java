package com.skd.teleport_animation;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

public final class TeleportClient {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String[] COMMAND_ALIASES = new String[]{"ta", "tpanimation"};
    private static final String USAGE_MESSAGE = "Usage: /ta or /tpanimation on|off|status|player_freeze <on|off|status>";
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
                client.gui.setScreen(null);
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

    static void handleServerTeleportRequest(TeleportNetworkPayloads.StartServerTeleportPayload payload) {
        LOGGER.info("TA client handleServerTeleportRequest: source={} reqId={} effect={} running={}", payload.source(), payload.requestId(), TeleportConfig.isEffectEnabled(), TeleportTransitionController.isRunning());
        Minecraft client = Minecraft.getInstance();
        if (!shouldPlayServerTeleportTransition(client, payload.source())) {
            LOGGER.info("TA client shouldPlay returned false, sending ACK immediately");
            TeleportClientNetworking.sendServerTeleportAck(payload.requestId());
            return;
        }
        LOGGER.info("TA client starting transition");
        TeleportTransitionController.start(client, TeleportClientNetworking.targetFeet(payload), TeleportClientNetworking.targetDimensionId(payload),
            () -> {
                LOGGER.info("TA client transition complete, sending ACK for reqId={}", payload.requestId());
                TeleportClientNetworking.sendServerTeleportAck(payload.requestId());
            }, payload.source());
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
        return true;
    }

    // ---- Waystones target resolution (used by WaystonesTeleportHandler) ----

    static WaystoneTarget getWaystonesSelectedTarget(UUID waystoneUid) {
        try {
            Minecraft client = Minecraft.getInstance();
            WaystoneTarget menuTarget = findWaystoneTargetInMenu(client.player == null ? null : client.player.containerMenu, waystoneUid);
            return menuTarget != null ? menuTarget : findWaystoneTargetInStore(waystoneUid);
        }
        catch (ReflectiveOperationException | RuntimeException ignored) {
            return null;
        }
    }

    static WaystoneTarget getWaystonesInventoryButtonTarget() {
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
        }
        catch (ReflectiveOperationException | ClassCastException ignored) {
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
        Vec3 targetFeet = new Vec3(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
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
        }
        catch (ReflectiveOperationException | ClassCastException ignored) {
            return null;
        }
    }

    // ---- Local mod commands (/ta, /tpanimation) ----

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

    record WaystoneTarget(Vec3 targetFeet, String targetDimensionId) {
    }
}
