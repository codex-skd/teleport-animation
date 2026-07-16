/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package dev.codex.gtaliketeleport;

import dev.codex.gtaliketeleport.GtaLikeTeleportNetworkPayloads;
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
        return player != null && GtaLikeTeleportNetworkPayloads.canSendToServer();
    }

    static void sendStart(ServerPlayer player, long requestId, int source, Vec3 targetFeet, ResourceKey<Level> targetDimension) {
        GtaLikeTeleportNetworkPayloads.sendStart(player, requestId, source, targetFeet, targetDimension);
    }
}

