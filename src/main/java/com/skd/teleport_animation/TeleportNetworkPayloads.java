package com.skd.teleport_animation;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

public final class TeleportNetworkPayloads {
    private TeleportNetworkPayloads() {
    }

    static boolean canSendToServer() {
        return true;
    }

    static void sendStart(ServerPlayer player, long requestId, int source, Vec3 targetFeet, ResourceKey<Level> targetDimension) {
        String dim = DimensionIds.fromResourceKey(targetDimension);
        sendToPlayer(player, new StartServerTeleportPayload(requestId, source, targetFeet.x(), targetFeet.y(), targetFeet.z(), dim == null ? "" : dim));
    }

    public static record StartServerTeleportPayload(long requestId, int source, double x, double y, double z, String dimension) {
        public static void encode(StartServerTeleportPayload payload, FriendlyByteBuf buf) {
            buf.writeLong(payload.requestId);
            buf.writeInt(payload.source);
            buf.writeDouble(payload.x);
            buf.writeDouble(payload.y);
            buf.writeDouble(payload.z);
            buf.writeUtf(payload.dimension);
        }

        public static StartServerTeleportPayload decode(FriendlyByteBuf buf) {
            return new StartServerTeleportPayload(buf.readLong(), buf.readInt(), buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readUtf());
        }
    }

    public static record ServerTeleportAckPayload(long requestId) {
        public static void encode(ServerTeleportAckPayload payload, FriendlyByteBuf buf) {
            buf.writeLong(payload.requestId);
        }

        public static ServerTeleportAckPayload decode(FriendlyByteBuf buf) {
            return new ServerTeleportAckPayload(buf.readLong());
        }
    }

    public static record BypassNextServerTeleportPayload() {
        public static void encode(BypassNextServerTeleportPayload payload, FriendlyByteBuf buf) {
            // No data
        }

        public static BypassNextServerTeleportPayload decode(FriendlyByteBuf buf) {
            return new BypassNextServerTeleportPayload();
        }
    }

    public static void sendToServer(ServerTeleportAckPayload payload) {
        TeleportAnimation.CHANNEL.sendToServer(payload);
    }

    public static void sendToServer(BypassNextServerTeleportPayload payload) {
        TeleportAnimation.CHANNEL.sendToServer(payload);
    }

    public static void sendToPlayer(ServerPlayer player, StartServerTeleportPayload payload) {
        TeleportAnimation.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), payload);
    }
}