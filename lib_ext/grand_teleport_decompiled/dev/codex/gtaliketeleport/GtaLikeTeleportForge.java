/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.event.TickEvent$ServerTickEvent
 *  net.minecraftforge.fml.common.Mod
 */
package dev.codex.gtaliketeleport;

import dev.codex.gtaliketeleport.GtaLikeTeleportServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(value="gtalike_teleport")
public final class GtaLikeTeleportForge {
    public static final String MOD_ID = "gtalike_teleport";

    public GtaLikeTeleportForge() {
        GtaLikeTeleportServer.initialize();
        MinecraftForge.EVENT_BUS.addListener(GtaLikeTeleportForge::onServerTick);
    }

    private static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            GtaLikeTeleportServer.tick(event.getServer());
        }
    }
}

