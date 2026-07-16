/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.network.NetworkDirection
 *  net.minecraftforge.network.NetworkEvent$Context
 *  net.minecraftforge.network.NetworkRegistry$ChannelBuilder
 *  net.minecraftforge.network.PacketDistributor
 *  net.minecraftforge.network.simple.SimpleChannel
 */
package dev.codex.gtaliketeleport;

import dev.codex.gtaliketeleport.DimensionIds;
import dev.codex.gtaliketeleport.GtaLikeTeleportClientNetworking;
import dev.codex.gtaliketeleport.GtaLikeTeleportServer;
import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

final class GtaLikeTeleportNetworkPayloads {
    static final int SOURCE_EXTERNAL = 1;
    static final int SOURCE_WARP_PLATE = 2;
    private static final String PROTOCOL_VERSION = "1";
    private static SimpleChannel channel;
    private static int nextMessageId;

    private GtaLikeTeleportNetworkPayloads() {
    }

    static synchronized void register() {
        if (channel != null) {
            return;
        }
        channel = NetworkRegistry.ChannelBuilder.named((ResourceLocation)GtaLikeTeleportNetworkPayloads.id("main")).networkProtocolVersion(() -> PROTOCOL_VERSION).clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals).simpleChannel();
        channel.registerMessage(nextMessageId++, StartServerTeleportPayload.class, StartServerTeleportPayload::write, StartServerTeleportPayload::read, GtaLikeTeleportNetworkPayloads::handleStartServerTeleport, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        channel.registerMessage(nextMessageId++, ServerTeleportAckPayload.class, ServerTeleportAckPayload::write, ServerTeleportAckPayload::read, GtaLikeTeleportNetworkPayloads::handleServerTeleportAck, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        channel.registerMessage(nextMessageId++, BypassNextServerTeleportPayload.class, BypassNextServerTeleportPayload::write, BypassNextServerTeleportPayload::read, GtaLikeTeleportNetworkPayloads::handleBypassNextServerTeleport, Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

    private static ResourceLocation id(String path) {
        return new ResourceLocation("gtalike_teleport", path);
    }

    static boolean canSendToServer() {
        return channel != null;
    }

    static void sendToServer(Object payload) {
        if (channel != null) {
            channel.sendToServer(payload);
        }
    }

    static void sendStart(ServerPlayer player, long requestId, int source, Vec3 targetFeet, ResourceKey<Level> targetDimension) {
        if (channel == null) {
            return;
        }
        channel.send(PacketDistributor.PLAYER.with(() -> player), (Object)new StartServerTeleportPayload(requestId, source, targetFeet.m_7096_(), targetFeet.m_7098_(), targetFeet.m_7094_(), GtaLikeTeleportNetworkPayloads.dimensionId(targetDimension)));
    }

    private static void handleStartServerTeleport(StartServerTeleportPayload payload, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> GtaLikeTeleportClientNetworking.handleStart(payload));
        context.setPacketHandled(true);
    }

    private static void handleServerTeleportAck(ServerTeleportAckPayload payload, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                GtaLikeTeleportServer.handleTeleportAck(player, payload.requestId());
            }
        });
        context.setPacketHandled(true);
    }

    private static void handleBypassNextServerTeleport(BypassNextServerTeleportPayload payload, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                GtaLikeTeleportServer.markNextServerTeleportBypassed(player);
            }
        });
        context.setPacketHandled(true);
    }

    private static String dimensionId(ResourceKey<Level> targetDimension) {
        String dimension = DimensionIds.fromResourceKey(targetDimension);
        return dimension == null ? "" : dimension;
    }

    record StartServerTeleportPayload(long requestId, int source, double x, double y, double z, String dimension) {
        private void write(FriendlyByteBuf buffer) {
            buffer.writeLong(this.requestId);
            buffer.writeInt(this.source);
            buffer.writeDouble(this.x);
            buffer.writeDouble(this.y);
            buffer.writeDouble(this.z);
            buffer.m_130070_(this.dimension);
        }

        private static StartServerTeleportPayload read(FriendlyByteBuf buffer) {
            return new StartServerTeleportPayload(buffer.readLong(), buffer.readInt(), buffer.readDouble(), buffer.readDouble(), buffer.readDouble(), buffer.m_130277_());
        }
    }

    record ServerTeleportAckPayload(long requestId) {
        private void write(FriendlyByteBuf buffer) {
            buffer.writeLong(this.requestId);
        }

        private static ServerTeleportAckPayload read(FriendlyByteBuf buffer) {
            return new ServerTeleportAckPayload(buffer.readLong());
        }
    }

    record BypassNextServerTeleportPayload() {
        private void write(FriendlyByteBuf buffer) {
        }

        private static BypassNextServerTeleportPayload read(FriendlyByteBuf buffer) {
            return new BypassNextServerTeleportPayload();
        }
    }
}

