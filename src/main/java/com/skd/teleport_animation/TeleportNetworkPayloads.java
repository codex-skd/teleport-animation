package com.skd.teleport_animation;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public final class TeleportNetworkPayloads {
    public static final int SOURCE_EXTERNAL = 1;
    public static final int SOURCE_WARP_PLATE = 2;

    private TeleportNetworkPayloads() {
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath("teleport_animation", path);
    }

    static void register() {
    }

    static boolean canSendToServer() {
        return true;
    }

    static void sendToServer(CustomPacketPayload payload) {
        var connection = Minecraft.getInstance().getConnection();
        if (connection != null) {
            connection.send(new ServerboundCustomPayloadPacket(payload));
        }
    }

    static void sendStart(net.minecraft.server.level.ServerPlayer player, long requestId, int source,
                          net.minecraft.world.phys.Vec3 targetFeet,
                          net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level> targetDimension) {
        String dim = DimensionIds.fromResourceKey(targetDimension);
        net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(player,
            new StartServerTeleportPayload(requestId, source, targetFeet.x(), targetFeet.y(), targetFeet.z(), dim == null ? "" : dim));
    }

    public record StartServerTeleportPayload(long requestId, int source, double x, double y, double z, String dimension)
        implements CustomPacketPayload {

        public static final CustomPacketPayload.Type<StartServerTeleportPayload> TYPE =
            new CustomPacketPayload.Type<>(id("start_server_teleport"));

        public static final StreamCodec<FriendlyByteBuf, StartServerTeleportPayload> STREAM_CODEC =
            CustomPacketPayload.codec(StartServerTeleportPayload::write, StartServerTeleportPayload::new);

        public StartServerTeleportPayload(FriendlyByteBuf buffer) {
            this(buffer.readLong(), buffer.readInt(), buffer.readDouble(), buffer.readDouble(), buffer.readDouble(), buffer.readUtf());
        }

        private void write(FriendlyByteBuf buffer) {
            buffer.writeLong(this.requestId);
            buffer.writeInt(this.source);
            buffer.writeDouble(this.x);
            buffer.writeDouble(this.y);
            buffer.writeDouble(this.z);
            buffer.writeUtf(this.dimension);
        }

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public record ServerTeleportAckPayload(long requestId)
        implements CustomPacketPayload {

        public static final CustomPacketPayload.Type<ServerTeleportAckPayload> TYPE =
            new CustomPacketPayload.Type<>(id("server_teleport_ack"));

        public static final StreamCodec<FriendlyByteBuf, ServerTeleportAckPayload> STREAM_CODEC =
            CustomPacketPayload.codec(ServerTeleportAckPayload::write, ServerTeleportAckPayload::new);

        public ServerTeleportAckPayload(FriendlyByteBuf buffer) {
            this(buffer.readLong());
        }

        private void write(FriendlyByteBuf buffer) {
            buffer.writeLong(this.requestId);
        }

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public record BypassNextServerTeleportPayload()
        implements CustomPacketPayload {

        public static final CustomPacketPayload.Type<BypassNextServerTeleportPayload> TYPE =
            new CustomPacketPayload.Type<>(id("bypass_next_server_teleport"));

        public static final StreamCodec<FriendlyByteBuf, BypassNextServerTeleportPayload> STREAM_CODEC =
            CustomPacketPayload.codec(BypassNextServerTeleportPayload::write, BypassNextServerTeleportPayload::new);

        public BypassNextServerTeleportPayload(FriendlyByteBuf buffer) {
            this();
        }

        private void write(FriendlyByteBuf buffer) {
        }

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }
}
