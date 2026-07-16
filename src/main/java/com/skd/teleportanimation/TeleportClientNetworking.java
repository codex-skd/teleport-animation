package com.skd.teleportanimation;

import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

final class TeleportClientNetworking {
    private TeleportClientNetworking() {
    }

    static void registerReceivers() {
    }

    static void handleStart(TeleportNetworkPayloads.StartServerTeleportPayload payload) {
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
        if (!canSendServerTeleportAck()) {
            return;
        }
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
        String dimension = payload.dimension();
        return DimensionIds.normalize(dimension);
    }
}
