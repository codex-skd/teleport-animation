package com.skd.teleport_animation.mixin;

import net.neoforged.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public final class GtaLikeTeleportMixinPlugin implements IMixinConfigPlugin {
    private static final String JOURNEYMAP_MIXIN = "com.skd.teleport_animation.mixin.JourneyMapClientNetworkDispatcherMixin";
    private static final String VOXY_CLIENT_MIXIN = "com.skd.teleport_animation.mixin.VoxyClientMixin";
    private static final String DISTANT_HORIZONS_RENDER_UTIL_MIXIN = "com.skd.teleport_animation.mixin.DistantHorizonsRenderUtilMixin";
    private static final String WAYSTONES_WARP_PLATE_MIXIN = "com.skd.teleport_animation.mixin.WaystonesWarpPlateBlockEntityMixin";
    private static final String WAYSTONES_TELEPORT_MIXIN = "com.skd.teleport_animation.mixin.WaystonesPlayerWaystoneManagerMixin";
    private static final String LEAWIND_THIRD_PERSON_FORGE_EVENTS_MIXIN = "com.skd.teleport_animation.mixin.LeawindThirdPersonEventsNeoForgeMixin";
    private static final String LEAWIND_THIRD_PERSON_LEGACY_MIXIN = "com.skd.teleport_animation.mixin.LeawindThirdPersonLegacyMixin";
    private static final String LEAWIND_THIRD_PERSON_IMPL_MIXIN = "com.skd.teleport_animation.mixin.LeawindThirdPersonImplMixin";

    public void onLoad(String mixinPackage) {
    }

    public String getRefMapperConfig() {
        return null;
    }

    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.equals(JOURNEYMAP_MIXIN)) {
            return isModLoaded("journeymap");
        }
        if (mixinClassName.equals(VOXY_CLIENT_MIXIN)) {
            return isModLoaded("voxy");
        }
        if (mixinClassName.equals(DISTANT_HORIZONS_RENDER_UTIL_MIXIN)) {
            return isModLoaded("distanthorizons");
        }
        if (mixinClassName.equals(WAYSTONES_WARP_PLATE_MIXIN) || mixinClassName.equals(WAYSTONES_TELEPORT_MIXIN)) {
            return isModLoaded("waystones");
        }
        if (mixinClassName.equals(LEAWIND_THIRD_PERSON_FORGE_EVENTS_MIXIN) || mixinClassName.equals(LEAWIND_THIRD_PERSON_LEGACY_MIXIN) || mixinClassName.equals(LEAWIND_THIRD_PERSON_IMPL_MIXIN)) {
            return isModLoaded("leawind_third_person");
        }
        return true;
    }

    private static boolean isModLoaded(String modId) {
        return LoadingModList.get().getModFileById(modId) != null;
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