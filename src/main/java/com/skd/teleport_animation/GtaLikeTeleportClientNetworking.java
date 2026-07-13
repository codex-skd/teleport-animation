package com.skd.teleport_animation;

import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

final class GtaLikeTeleportClientNetworking {
    private GtaLikeTeleportClientNetworking() {
    }

    static void registerReceivers() {
    }

    static void handleStart(GtaLikeTeleportNetworkPayloads.StartServerTeleportPayload payload) {
        Minecraft.getInstance().execute(() -> GtaLikeTeleportClient.handleServerTeleportRequest(payload));
    }

    static boolean canSendServerTeleportAck() {
        return GtaLikeTeleportNetworkPayloads.canSendToServer();
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
        GtaLikeTeleportNetworkPayloads.sendToServer(new GtaLikeTeleportNetworkPayloads.ServerTeleportAckPayload(requestId));
    }

    static void sendBypassNextServerTeleport() {
        if (GtaLikeTeleportNetworkPayloads.canSendToServer()) {
            GtaLikeTeleportNetworkPayloads.sendToServer(new GtaLikeTeleportNetworkPayloads.BypassNextServerTeleportPayload());
        }
    }

    static Vec3 targetFeet(GtaLikeTeleportNetworkPayloads.StartServerTeleportPayload payload) {
        return new Vec3(payload.x(), payload.y(), payload.z());
    }

    static String targetDimensionId(GtaLikeTeleportNetworkPayloads.StartServerTeleportPayload payload) {
        return DimensionIds.normalize(payload.dimension());
    }
}