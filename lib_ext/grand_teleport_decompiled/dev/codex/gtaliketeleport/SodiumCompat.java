/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package dev.codex.gtaliketeleport;

import java.lang.reflect.Method;
import net.minecraft.client.Minecraft;

final class SodiumCompat {
    private static boolean active;

    private SodiumCompat() {
    }

    static void beginTransition(Minecraft client, boolean fallbackTerrainMode) {
        if (active) {
            return;
        }
        active = true;
        SodiumCompat.scheduleTerrainUpdate();
    }

    static void endTransition() {
        if (!active) {
            return;
        }
        active = false;
        SodiumCompat.scheduleTerrainUpdate();
    }

    static void scheduleTerrainUpdate() {
        try {
            Class<?> rendererClass = Class.forName("me.jellysquid.mods.sodium.client.render.SodiumWorldRenderer");
            Method instanceNullable = rendererClass.getMethod("instanceNullable", new Class[0]);
            Object renderer = instanceNullable.invoke(null, new Object[0]);
            if (renderer == null) {
                return;
            }
            rendererClass.getMethod("scheduleTerrainUpdate", new Class[0]).invoke(renderer, new Object[0]);
        }
        catch (LinkageError | ReflectiveOperationException throwable) {
            // empty catch block
        }
    }
}

