package com.skd.teleportanimation;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

final class TeleportServerNetworking {
    private TeleportServerNetworking() {
    }

    static void registerReceivers() {
    }

    static boolean canSendStart(ServerPlayer player) {
        return player != null && TeleportNetworkPayloads.canSendToServer();
    }

    static void sendStart(ServerPlayer player, long requestId, int source, Vec3 targetFeet, ResourceKey<Level> targetDimension) {
        TeleportNetworkPayloads.sendStart(player, requestId, source, targetFeet, targetDimension);
    }
}
