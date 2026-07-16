/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.loading.LoadingModList
 *  org.objectweb.asm.tree.ClassNode
 *  org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin
 *  org.spongepowered.asm.mixin.extensibility.IMixinInfo
 */
package dev.codex.gtaliketeleport.mixin;

import java.util.List;
import java.util.Set;
import net.minecraftforge.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public final class GtaLikeTeleportMixinPlugin
implements IMixinConfigPlugin {
    private static final String JOURNEYMAP_MIXIN = "dev.codex.gtaliketeleport.mixin.JourneyMapClientNetworkDispatcherMixin";
    private static final String VOXY_CLIENT_MIXIN = "dev.codex.gtaliketeleport.mixin.VoxyClientMixin";
    private static final String DISTANT_HORIZONS_RENDER_UTIL_MIXIN = "dev.codex.gtaliketeleport.mixin.DistantHorizonsRenderUtilMixin";
    private static final String WAYSTONES_WARP_PLATE_MIXIN = "dev.codex.gtaliketeleport.mixin.WaystonesWarpPlateBlockEntityMixin";
    private static final String WAYSTONES_TELEPORT_MIXIN = "dev.codex.gtaliketeleport.mixin.WaystonesPlayerWaystoneManagerMixin";
    private static final String LEAWIND_THIRD_PERSON_FORGE_EVENTS_MIXIN = "dev.codex.gtaliketeleport.mixin.LeawindThirdPersonEventsForgeMixin";
    private static final String LEAWIND_THIRD_PERSON_LEGACY_MIXIN = "dev.codex.gtaliketeleport.mixin.LeawindThirdPersonLegacyMixin";
    private static final String LEAWIND_THIRD_PERSON_IMPL_MIXIN = "dev.codex.gtaliketeleport.mixin.LeawindThirdPersonImplMixin";

    public void onLoad(String mixinPackage) {
    }

    public String getRefMapperConfig() {
        return null;
    }

    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.equals(JOURNEYMAP_MIXIN)) {
            return GtaLikeTeleportMixinPlugin.isModLoaded("journeymap");
        }
        if (mixinClassName.equals(VOXY_CLIENT_MIXIN)) {
            return GtaLikeTeleportMixinPlugin.isModLoaded("voxy");
        }
        if (mixinClassName.equals(DISTANT_HORIZONS_RENDER_UTIL_MIXIN)) {
            return GtaLikeTeleportMixinPlugin.isModLoaded("distanthorizons");
        }
        if (mixinClassName.equals(WAYSTONES_WARP_PLATE_MIXIN) || mixinClassName.equals(WAYSTONES_TELEPORT_MIXIN)) {
            return GtaLikeTeleportMixinPlugin.isModLoaded("waystones");
        }
        if (mixinClassName.equals(LEAWIND_THIRD_PERSON_FORGE_EVENTS_MIXIN) || mixinClassName.equals(LEAWIND_THIRD_PERSON_LEGACY_MIXIN) || mixinClassName.equals(LEAWIND_THIRD_PERSON_IMPL_MIXIN)) {
            return GtaLikeTeleportMixinPlugin.isModLoaded("leawind_third_person");
        }
        return true;
    }

    private static boolean isModLoaded(String modId) {
        LoadingModList loadingModList = LoadingModList.get();
        return loadingModList != null && loadingModList.getModFileById(modId) != null;
    }

    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    public List<String> getMixins() {
        return null;
    }

    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}

