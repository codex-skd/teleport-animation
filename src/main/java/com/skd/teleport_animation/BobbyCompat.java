package com.skd.teleport_animation;

import java.lang.reflect.Method;

final class BobbyCompat {
    private static final String BOBBY_CLASS = "de.johni0702.minecraft.bobby.Bobby";

    private BobbyCompat() {
    }

    static boolean isRenderingEnabled() {
        try {
            Class<?> bobbyClass = Class.forName(BOBBY_CLASS);
            Method getInstance = bobbyClass.getMethod("getInstance");
            Object instance = getInstance.invoke(null);
            if (instance == null) {
                return false;
            }
            Method isEnabled = bobbyClass.getMethod("isEnabled");
            return Boolean.TRUE.equals(isEnabled.invoke(instance));
        }
        catch (LinkageError | ReflectiveOperationException ignored) {
            return true;
        }
    }
}