/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.RelativeMovement
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 */
package dev.codex.gtaliketeleport;

import dev.codex.gtaliketeleport.GtaLikeTeleportConfig;
import dev.codex.gtaliketeleport.GtaLikeTeleportNetworkPayloads;
import dev.codex.gtaliketeleport.GtaLikeTeleportServerNetworking;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public final class GtaLikeTeleportServer {
    private static final int BYPASS_TICKS = 20;
    private static final int ACK_TIMEOUT_TICKS = 200;
    private static final AtomicLong NEXT_REQUEST_ID = new AtomicLong(1L);
    private static final Map<UUID, Integer> bypassNextTeleportTicks = new HashMap<UUID, Integer>();
    private static final Map<Long, PendingTeleport> pendingTeleports = new HashMap<Long, PendingTeleport>();
    private static final Set<UUID> pendingPlayers = new HashSet<UUID>();
    private static final ThreadLocal<Boolean> executingDelayedTeleport = ThreadLocal.withInitial(() -> false);

    static void initialize() {
        GtaLikeTeleportConfig.load();
        GtaLikeTeleportNetworkPayloads.register();
        GtaLikeTeleportServerNetworking.registerReceivers();
    }

    public static boolean tryDelayExternalTeleport(ServerPlayer player, ServerLevel level, double x, double y, double z, Set<RelativeMovement> relatives, float yaw, float pitch, boolean resetCamera) {
        if (GtaLikeTeleportServer.consumeServerTeleportBypass(player)) {
            return false;
        }
        if (pendingPlayers.contains(player.m_20148_())) {
            return true;
        }
        if (!GtaLikeTeleportServer.shouldStartServerTransition(player, 1)) {
            return false;
        }
        Vec3 targetFeet = GtaLikeTeleportServer.resolveTargetFeet(player, x, y, z, relatives);
        return GtaLikeTeleportServer.scheduleServerTransition(player, 1, targetFeet, (ResourceKey<Level>)level.m_46472_(), () -> player.m_264318_(level, x, y, z, relatives, yaw, pitch));
    }

    private static Vec3 resolveTargetFeet(ServerPlayer player, double x, double y, double z, Set<RelativeMovement> relatives) {
        return new Vec3(x, y, z);
    }

    static boolean scheduleServerTransition(ServerPlayer player, int source, Vec3 targetFeet, Runnable action) {
        return GtaLikeTeleportServer.scheduleServerTransition(player, source, targetFeet, (ResourceKey<Level>)(player == null ? null : player.m_9236_().m_46472_()), action);
    }

    static boolean scheduleServerTransition(ServerPlayer player, int source, Vec3 targetFeet, ResourceKey<Level> targetDimension, Runnable action) {
        if (GtaLikeTeleportServer.consumeServerTeleportBypass(player)) {
            return false;
        }
        if (pendingPlayers.contains(player.m_20148_())) {
            return true;
        }
        if (!GtaLikeTeleportServer.shouldStartServerTransition(player, source)) {
            return false;
        }
        long requestId = NEXT_REQUEST_ID.getAndIncrement();
        PendingTeleport pending = new PendingTeleport(requestId, player, source, action);
        pendingTeleports.put(requestId, pending);
        pendingPlayers.add(player.m_20148_());
        GtaLikeTeleportServerNetworking.sendStart(player, requestId, source, targetFeet, targetDimension);
        return true;
    }

    static void handleTeleportAck(ServerPlayer player, long requestId) {
        PendingTeleport pending = pendingTeleports.get(requestId);
        if (pending == null || !pending.player.m_20148_().equals(player.m_20148_())) {
            return;
        }
        pendingTeleports.remove(requestId);
        pendingPlayers.remove(player.m_20148_());
        GtaLikeTeleportServer.runPendingTeleport(pending);
    }

    static void markNextServerTeleportBypassed(ServerPlayer player) {
        if (player != null) {
            bypassNextTeleportTicks.put(player.m_20148_(), 20);
        }
    }

    static void runWithServerTeleportBypass(ServerPlayer player, Runnable action) {
        GtaLikeTeleportServer.markNextServerTeleportBypassed(player);
        executingDelayedTeleport.set(true);
        try {
            action.run();
        }
        finally {
            executingDelayedTeleport.set(false);
        }
    }

    private static boolean shouldStartServerTransition(ServerPlayer player, int source) {
        if (player == null || player.m_9232_() || !player.m_6084_()) {
            return false;
        }
        if (!GtaLikeTeleportConfig.isEffectEnabled()) {
            return false;
        }
        if (source == 1 && !GtaLikeTeleportConfig.isExternalTeleportTransitionsEnabled()) {
            return false;
        }
        if (source == 2 && !GtaLikeTeleportConfig.isWarpPlateTransitionsEnabled()) {
            return false;
        }
        return GtaLikeTeleportServerNetworking.canSendStart(player);
    }

    private static boolean consumeServerTeleportBypass(ServerPlayer player) {
        if (Boolean.TRUE.equals(executingDelayedTeleport.get())) {
            return true;
        }
        if (player == null) {
            return false;
        }
        Integer ticks = bypassNextTeleportTicks.remove(player.m_20148_());
        return ticks != null && ticks > 0;
    }

    public static void tick(MinecraftServer server) {
        GtaLikeTeleportServer.tickBypassEntries();
        GtaLikeTeleportServer.tickPendingTeleports();
    }

    private static void tickBypassEntries() {
        Iterator<Map.Entry<UUID, Integer>> iterator = bypassNextTeleportTicks.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, Integer> entry = iterator.next();
            int ticks = entry.getValue() - 1;
            if (ticks <= 0) {
                iterator.remove();
                continue;
            }
            entry.setValue(ticks);
        }
    }

    private static void tickPendingTeleports() {
        Iterator<Map.Entry<Long, PendingTeleport>> iterator = pendingTeleports.entrySet().iterator();
        while (iterator.hasNext()) {
            PendingTeleport pending = iterator.next().getValue();
            ++pending.age;
            if (pending.age <= 200) continue;
            iterator.remove();
            pendingPlayers.remove(pending.player.m_20148_());
            GtaLikeTeleportServer.runPendingTeleport(pending);
        }
    }

    private static void runPendingTeleport(PendingTeleport pending) {
        if (pending.player.m_9232_() || !pending.player.m_6084_()) {
            return;
        }
        GtaLikeTeleportServer.runWithServerTeleportBypass(pending.player, pending.action);
    }

    private static final class PendingTeleport {
        private final long requestId;
        private final ServerPlayer player;
        private final int source;
        private final Runnable action;
        private int age;

        private PendingTeleport(long requestId, ServerPlayer player, int source, Runnable action) {
            this.requestId = requestId;
            this.player = player;
            this.source = source;
            this.action = action;
        }
    }
}

