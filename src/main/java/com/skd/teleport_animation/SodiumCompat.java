package com.skd.teleport_animation;

import net.minecraft.client.Minecraft;
import java.lang.reflect.Method;

final class SodiumCompat {
    private static final String[] RENDERER_CLASS_NAMES = new String[]{
        "net.caffeinemc.mods.sodium.client.render.SodiumWorldRenderer",
        "me.jellysquid.mods.sodium.client.render.SodiumWorldRenderer",
        "org.embeddedt.embeddium.impl.render.EmbeddiumWorldRenderer"
    };

    private static boolean active;
    private static boolean rendererResolved;
    private static Class<?> rendererClass;
    private static Method instanceNullableMethod;
    private static Method scheduleTerrainUpdateMethod;

    private SodiumCompat() {
    }

    static void beginTransition(Minecraft client, boolean fallbackTerrainMode) {
        if (active) {
            return;
        }
        active = true;
        scheduleTerrainUpdate();
    }

    static void endTransition() {
        if (!active) {
            return;
        }
        active = false;
        scheduleTerrainUpdate();
    }

    static void scheduleTerrainUpdate() {
        if (!resolveRenderer()) {
            return;
        }
        try {
            Object renderer = instanceNullableMethod.invoke(null);
            if (renderer == null) {
                return;
            }
            scheduleTerrainUpdateMethod.invoke(renderer);
        }
        catch (LinkageError | ReflectiveOperationException ignored) {
        }
    }

    /**
     * Resolves the Sodium/Embeddium world renderer class and its methods once, since this is called
     * very often during a transition. When no compatible renderer is installed the lookup is not
     * retried again.
     */
    private static boolean resolveRenderer() {
        if (rendererResolved) {
            return rendererClass != null;
        }
        rendererResolved = true;
        for (String className : RENDERER_CLASS_NAMES) {
            try {
                Class<?> candidate = Class.forName(className);
                Method instanceNullable = candidate.getMethod("instanceNullable");
                Method scheduleTerrainUpdate = candidate.getMethod("scheduleTerrainUpdate");
                rendererClass = candidate;
                instanceNullableMethod = instanceNullable;
                scheduleTerrainUpdateMethod = scheduleTerrainUpdate;
                return true;
            }
            catch (LinkageError | ReflectiveOperationException ignored) {
            }
        }
        rendererClass = null;
        instanceNullableMethod = null;
        scheduleTerrainUpdateMethod = null;
        return false;
    }
}
