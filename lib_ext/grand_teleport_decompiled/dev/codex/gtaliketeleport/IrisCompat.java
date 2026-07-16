/*
 * Decompiled with CFR 0.152.
 */
package dev.codex.gtaliketeleport;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class IrisCompat {
    private IrisCompat() {
    }

    static boolean shouldUseHardTerrainCut() {
        return IrisCompat.isShaderPackInUse();
    }

    private static boolean isShaderPackInUse() {
        try {
            Class<?> apiClass = Class.forName("net.irisshaders.iris.api.v0.IrisApi");
            Method getInstance = apiClass.getMethod("getInstance", new Class[0]);
            Object api = getInstance.invoke(null, new Object[0]);
            Method isShaderPackInUse = apiClass.getMethod("isShaderPackInUse", new Class[0]);
            return Boolean.TRUE.equals(isShaderPackInUse.invoke(api, new Object[0]));
        }
        catch (ClassNotFoundException | IllegalAccessException | LinkageError | NoSuchMethodException | InvocationTargetException ignored) {
            return false;
        }
    }
}

