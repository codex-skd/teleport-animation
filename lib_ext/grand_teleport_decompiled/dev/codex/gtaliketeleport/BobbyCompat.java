/*
 * Decompiled with CFR 0.152.
 */
package dev.codex.gtaliketeleport;

import java.lang.reflect.Method;

final class BobbyCompat {
    private static final String BOBBY_CLASS = "de.johni0702.minecraft.bobby.Bobby";

    private BobbyCompat() {
    }

    static boolean isRenderingEnabled() {
        try {
            Class<?> bobbyClass = Class.forName(BOBBY_CLASS);
            Method getInstance = bobbyClass.getMethod("getInstance", new Class[0]);
            Object instance = getInstance.invoke(null, new Object[0]);
            if (instance == null) {
                return false;
            }
            Method isEnabled = bobbyClass.getMethod("isEnabled", new Class[0]);
            return Boolean.TRUE.equals(isEnabled.invoke(instance, new Object[0]));
        }
        catch (LinkageError | ReflectiveOperationException ignored) {
            return true;
        }
    }
}

