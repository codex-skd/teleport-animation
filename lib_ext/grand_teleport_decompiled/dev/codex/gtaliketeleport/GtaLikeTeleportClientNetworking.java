/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.world.phys.Vec3
 */
package dev.codex.gtaliketeleport;

import dev.codex.gtaliketeleport.DimensionIds;
import dev.codex.gtaliketeleport.GtaLikeTeleportClient;
import dev.codex.gtaliketeleport.GtaLikeTeleportNetworkPayloads;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

final class GtaLikeTeleportClientNetworking {
    private GtaLikeTeleportClientNetworking() {
    }

    static void registerReceivers() {
    }

    static void handleStart(GtaLikeTeleportNetworkPayloads.StartServerTeleportPayload payload) {
        Minecraft.m_91087_().execute(() -> GtaLikeTeleportClient.handleServerTeleportRequest(payload));
    }

    static boolean canSendServerTeleportAck() {
        return GtaLikeTeleportNetworkPayloads.canSendToServer();
    }

    static boolean isServerSideTeleportAvailable() {
        Minecraft client = Minecraft.m_91087_();
        if (client.m_91403_() == null) {
            return true;
        }
        if (client.m_91091_()) {
            return true;
        }
        return GtaLikeTeleportClientNetworking.canSendServerTeleportAck();
    }

    static void sendServerTeleportAck(long requestId) {
        if (!GtaLikeTeleportClientNetworking.canSendServerTeleportAck()) {
            return;
        }
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
        String dimension = payload.dimension();
        return DimensionIds.normalize(dimension);
    }
}

