package com.skd.teleport_animation;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

final class GtaLikeTeleportServerNetworking {
    private GtaLikeTeleportServerNetworking() {
    }

    static void registerReceivers() {
    }

    static boolean canSendStart(ServerPlayer player) {
        return player != null;
    }

    static void sendStart(ServerPlayer player, long requestId, int source, Vec3 targetFeet, ResourceKey<Level> targetDimension) {
        GtaLikeTeleportNetworkPayloads.sendStart(player, requestId, source, targetFeet, targetDimension);
    }
}