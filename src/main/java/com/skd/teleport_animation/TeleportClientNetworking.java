package com.skd.teleport_animation;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;

final class TeleportClientNetworking {
    private static final Logger LOGGER = LogUtils.getLogger();

    private TeleportClientNetworking() {
    }

    static void registerReceivers() {
    }

    static void handleStart(TeleportNetworkPayloads.StartServerTeleportPayload payload) {
        LOGGER.info("TA client handleStart: source={} pos={},{},{} dim={} reqId={}", payload.source(), payload.x(), payload.y(), payload.z(), payload.dimension(), payload.requestId());
        Minecraft.getInstance().execute(() -> TeleportClient.handleServerTeleportRequest(payload));
    }

    static boolean canSendServerTeleportAck() {
        return TeleportNetworkPayloads.canSendToServer();
    }

    static boolean isServerSideTeleportAvailable() {
        Minecraft client = Minecraft.getInstance();
        if (client.getConnection() == null) {
            return true;
        }
        if (client.isLocalServer()) {
            return true;
        }
        return canSendServerTeleportAck();
    }

    static void sendServerTeleportAck(long requestId) {
        if (!canSendServerTeleportAck()) return;
        TeleportNetworkPayloads.sendToServer(new TeleportNetworkPayloads.ServerTeleportAckPayload(requestId));
    }

    static void sendBypassNextServerTeleport() {
        if (TeleportNetworkPayloads.canSendToServer()) {
            TeleportNetworkPayloads.sendToServer(new TeleportNetworkPayloads.BypassNextServerTeleportPayload());
        }
    }

    static Vec3 targetFeet(TeleportNetworkPayloads.StartServerTeleportPayload payload) {
        return new Vec3(payload.x(), payload.y(), payload.z());
    }

    static String targetDimensionId(TeleportNetworkPayloads.StartServerTeleportPayload payload) {
        return DimensionIds.normalize(payload.dimension());
    }
}
