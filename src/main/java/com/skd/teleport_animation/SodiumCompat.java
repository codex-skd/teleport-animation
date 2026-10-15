package com.skd.teleport_animation;

import net.minecraft.client.Minecraft;
import java.lang.reflect.Method;

final class SodiumCompat {
    private static final String[] WORLD_RENDERER_CLASSES = new String[]{
        "net.caffeinemc.mods.sodium.client.render.SodiumWorldRenderer",
        "me.jellysquid.mods.sodium.client.render.SodiumWorldRenderer",
        "org.embeddedt.embeddium.impl.render.EmbeddiumWorldRenderer"
    };

    private static boolean active;
    private static boolean resolved;
    private static Method instanceNullable;
    private static Method scheduleTerrainUpdate;

    private SodiumCompat() {
    }

    static void beginTransition(Minecraft client, boolean fallbackTerrainMode) {
        if (active) return;
        active = true;
        scheduleTerrainUpdate();
    }

    static void endTransition() {
        if (!active) return;
        active = false;
        scheduleTerrainUpdate();
    }

    static void scheduleTerrainUpdate() {
        if (!resolved) {
            SodiumCompat.resolve();
        }
        if (instanceNullable == null || scheduleTerrainUpdate == null) {
            return;
        }
        try {
            Object renderer = instanceNullable.invoke(null);
            if (renderer == null) return;
            scheduleTerrainUpdate.invoke(renderer);
        } catch (LinkageError | ReflectiveOperationException ignored) {
        }
    }

    private static synchronized void resolve() {
        if (resolved) {
            return;
        }
        resolved = true;
        for (String className : WORLD_RENDERER_CLASSES) {
            try {
                Class<?> rendererClass = Class.forName(className);
                instanceNullable = rendererClass.getMethod("instanceNullable");
                scheduleTerrainUpdate = rendererClass.getMethod("scheduleTerrainUpdate");
                return;
            } catch (LinkageError | ReflectiveOperationException ignored) {
                instanceNullable = null;
                scheduleTerrainUpdate = null;
            }
        }
    }
}
