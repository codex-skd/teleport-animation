package com.skd.teleport_animation;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Client-side configuration for the teleport transition, backed by a native NeoForge
 * {@link ModConfigSpec} (TOML). The spec is registered as a {@code ModConfig.Type.CLIENT}
 * config in {@link TeleportAnimation}, so the file is auto-created on first launch and
 * the mod appears as editable in Configured.
 */
public final class TeleportConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final double MIN_STAGE_HEIGHT = 8.0;
    private static final double MAX_STAGE_HEIGHT = 512.0;
    private static final double MIN_STAGE_GAP = 1.0;
    private static final double[] DEFAULT_STAGE_HEIGHTS = new double[]{20.0, 40.0, 60.0};
    private static final int MIN_STAGE_TICKS = 1;
    private static final int MAX_STAGE_TICKS = 200;
    private static final int[] DEFAULT_STAGE_TICKS = new int[]{13, 13, 13};
    private static final double DEFAULT_ZOOM_STAGE_GLIDE_HEIGHT = 0.5;
    private static final double MIN_ZOOM_STAGE_GLIDE_HEIGHT = 0.1;
    private static final double MAX_ZOOM_STAGE_GLIDE_HEIGHT = 5.0;
    private static final int DEFAULT_ZOOM_STAGE_GLIDE_TICKS = 13;
    private static final double DEFAULT_BODY_CAMERA_HEIGHT = 6.0;
    private static final double MIN_BODY_CAMERA_HEIGHT = 0.1;
    private static final double MAX_BODY_CAMERA_HEIGHT = 10.0;
    private static final double DEFAULT_BODY_GLIDE_HEIGHT = 0.5;
    private static final double MIN_BODY_GLIDE_HEIGHT = 0.1;
    private static final double MAX_BODY_GLIDE_HEIGHT = 5.0;
    private static final int DEFAULT_BODY_GLIDE_TICKS = 10;
    private static final int MIN_LOCAL_PLAYER_HIDE_TICKS = 0;
    private static final int MAX_LOCAL_PLAYER_HIDE_TICKS = 20;
    private static final int DEFAULT_LOCAL_PLAYER_HIDE_TICKS = 2;
    private static final double DEFAULT_MINECRAFT_SOUND_VOLUME = 1.0;
    private static final double DEFAULT_CUSTOM_SOUND_VOLUME = 0.3;
    private static final double MIN_SOUND_VOLUME = 0.1;
    private static final double MAX_SOUND_VOLUME = 1.0;

    private static final ModConfigSpec.ConfigValue<Boolean> EFFECT_ENABLED =
        BUILDER.comment("Master switch for the teleport transition effect.").define("effectEnabled", true);
    private static final ModConfigSpec.ConfigValue<Boolean> PLAYER_FREEZE_ENABLED =
        BUILDER.comment("Freezes the player in place while the transition plays.").define("playerFreezeEnabled", true);
    private static final ModConfigSpec.ConfigValue<Boolean> CROSS_DIMENSION_TRAVEL_ENABLED =
        BUILDER.comment("Allows the transition to play when the destination is in a different dimension.")
            .define("crossDimensionTravelEnabled", false);

    private static final DimensionHeights OVERWORLD_HEIGHTS = new DimensionHeights(BUILDER, "overworldZoomStage");
    private static final DimensionHeights NETHER_HEIGHTS = new DimensionHeights(BUILDER, "netherZoomStage");
    private static final DimensionHeights END_HEIGHTS = new DimensionHeights(BUILDER, "endZoomStage");

    private static final ModConfigSpec.ConfigValue<Integer> ZOOM_OUT_STAGE_TICKS_1 =
        BUILDER.push("zoomStageTicks").comment("Tick length of each zoom stage (1st / 2nd / 3rd).")
            .defineInRange("zoomOutStage1", DEFAULT_STAGE_TICKS[0], MIN_STAGE_TICKS, MAX_STAGE_TICKS);
    private static final ModConfigSpec.ConfigValue<Integer> ZOOM_OUT_STAGE_TICKS_2 =
        BUILDER.defineInRange("zoomOutStage2", DEFAULT_STAGE_TICKS[1], MIN_STAGE_TICKS, MAX_STAGE_TICKS);
    private static final ModConfigSpec.ConfigValue<Integer> ZOOM_OUT_STAGE_TICKS_3 =
        BUILDER.defineInRange("zoomOutStage3", DEFAULT_STAGE_TICKS[2], MIN_STAGE_TICKS, MAX_STAGE_TICKS);
    private static final ModConfigSpec.ConfigValue<Integer> ZOOM_IN_STAGE_TICKS_1 =
        BUILDER.defineInRange("zoomInStage1", DEFAULT_STAGE_TICKS[0], MIN_STAGE_TICKS, MAX_STAGE_TICKS);
    private static final ModConfigSpec.ConfigValue<Integer> ZOOM_IN_STAGE_TICKS_2 =
        BUILDER.defineInRange("zoomInStage2", DEFAULT_STAGE_TICKS[1], MIN_STAGE_TICKS, MAX_STAGE_TICKS);
    private static final ModConfigSpec.ConfigValue<Integer> ZOOM_IN_STAGE_TICKS_3 =
        BUILDER.defineInRange("zoomInStage3", DEFAULT_STAGE_TICKS[2], MIN_STAGE_TICKS, MAX_STAGE_TICKS);

    private static final ModConfigSpec.ConfigValue<Double> ZOOM_STAGE_GLIDE_HEIGHT =
        BUILDER.pop().push("glide").comment("Extra upward glide applied between zoom stages.")
            .defineInRange("zoomStageGlideHeight", DEFAULT_ZOOM_STAGE_GLIDE_HEIGHT, MIN_ZOOM_STAGE_GLIDE_HEIGHT, MAX_ZOOM_STAGE_GLIDE_HEIGHT);
    private static final ModConfigSpec.ConfigValue<Integer> ZOOM_STAGE_GLIDE_TICKS =
        BUILDER.defineInRange("zoomStageGlideTicks", DEFAULT_ZOOM_STAGE_GLIDE_TICKS, MIN_STAGE_TICKS, MAX_STAGE_TICKS);

    private static final ModConfigSpec.ConfigValue<Double> BODY_CAMERA_HEIGHT =
        BUILDER.pop().push("body").comment("Camera height above the player's feet during the body transition.")
            .defineInRange("bodyCameraHeight", DEFAULT_BODY_CAMERA_HEIGHT, MIN_BODY_CAMERA_HEIGHT, MAX_BODY_CAMERA_HEIGHT);
    private static final ModConfigSpec.ConfigValue<Double> BODY_GLIDE_HEIGHT =
        BUILDER.comment("Upward glide at the end of the body transition.")
            .defineInRange("bodyGlideHeight", DEFAULT_BODY_GLIDE_HEIGHT, MIN_BODY_GLIDE_HEIGHT, MAX_BODY_GLIDE_HEIGHT);
    private static final ModConfigSpec.ConfigValue<Integer> BODY_GLIDE_TICKS =
        BUILDER.defineInRange("bodyGlideTicks", DEFAULT_BODY_GLIDE_TICKS, MIN_STAGE_TICKS, MAX_STAGE_TICKS);
    private static final ModConfigSpec.ConfigValue<Integer> LOCAL_PLAYER_HIDE_TICKS =
        BUILDER.defineInRange("localPlayerHideTicks", DEFAULT_LOCAL_PLAYER_HIDE_TICKS, MIN_LOCAL_PLAYER_HIDE_TICKS, MAX_LOCAL_PLAYER_HIDE_TICKS);

    private static final ModConfigSpec.ConfigValue<Boolean> CUSTOM_SOUNDS_ENABLED =
        BUILDER.pop().push("sounds").comment("Use the mod's custom camera and step sounds during the transition.")
            .define("customSoundsEnabled", false);
    private static final ModConfigSpec.ConfigValue<Double> MINECRAFT_SOUND_VOLUME =
        BUILDER.comment("Volume multiplier applied to Minecraft's default teleport sounds.")
            .defineInRange("minecraftSoundVolume", DEFAULT_MINECRAFT_SOUND_VOLUME, MIN_SOUND_VOLUME, MAX_SOUND_VOLUME);
    private static final ModConfigSpec.ConfigValue<Double> CUSTOM_SOUND_VOLUME =
        BUILDER.comment("Volume multiplier applied to the mod's custom sounds.")
            .defineInRange("customSoundVolume", DEFAULT_CUSTOM_SOUND_VOLUME, MIN_SOUND_VOLUME, MAX_SOUND_VOLUME);

    private static final ModConfigSpec.ConfigValue<Boolean> WARP_PLATE_TRANSITIONS_ENABLED =
        BUILDER.pop().push("transitions").comment("Play the transition when teleporting through a Warp Plate.")
            .define("warpPlateTransitionsEnabled", true);
    private static final ModConfigSpec.ConfigValue<Boolean> EXTERNAL_TELEPORT_TRANSITIONS_ENABLED =
        BUILDER.comment("Play the transition for teleports triggered by other mods.")
            .define("externalTeleportTransitionsEnabled", true);
    private static final ModConfigSpec.ConfigValue<Boolean> FALLBACK_CHUNK_FADE_ENABLED =
        BUILDER.comment("Use a vanilla/Sodium chunk-mask fade as a fallback for chunk rendering.")
            .define("fallbackChunkFadeEnabled", false);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private TeleportConfig() {
    }

    /**
     * No-op kept for compatibility: the configuration is loaded and managed by NeoForge
     * (see {@link TeleportAnimation}).
     */
    static void load() {
    }

    static boolean isEffectEnabled() {
        return EFFECT_ENABLED.get();
    }

    static boolean setEffectEnabled(boolean enabled) {
        return commit(() -> EFFECT_ENABLED.set(enabled));
    }

    static boolean isPlayerFreezeEnabled() {
        return PLAYER_FREEZE_ENABLED.get();
    }

    static boolean setPlayerFreezeEnabled(boolean enabled) {
        return commit(() -> PLAYER_FREEZE_ENABLED.set(enabled));
    }

    static boolean isCrossDimensionTravelEnabled() {
        return CROSS_DIMENSION_TRAVEL_ENABLED.get();
    }

    static boolean setCrossDimensionTravelEnabled(boolean enabled) {
        return commit(() -> CROSS_DIMENSION_TRAVEL_ENABLED.set(enabled));
    }

    static boolean areZoomHeightsLinked() {
        return areZoomHeightsLinked(ZoomDimension.OVERWORLD);
    }

    static boolean areZoomHeightsLinked(ZoomDimension dimension) {
        return heightsFor(dimension).linked.get();
    }

    static double[] getZoomOutStageHeights() {
        return getZoomOutStageHeights(ZoomDimension.OVERWORLD);
    }

    static double[] getZoomOutStageHeights(ZoomDimension dimension) {
        DimensionHeights cfg = heightsFor(dimension);
        return sanitizeStageHeights(new double[]{cfg.zoomOutStage1.get(), cfg.zoomOutStage2.get(), cfg.zoomOutStage3.get()});
    }

    static double[] getZoomInStageHeights() {
        return getZoomInStageHeights(ZoomDimension.OVERWORLD);
    }

    static double[] getZoomInStageHeights(ZoomDimension dimension) {
        ZoomDimension safeDimension = sanitizeZoomDimension(dimension);
        return areZoomHeightsLinked(safeDimension) ? getZoomOutStageHeights(safeDimension) : getRawZoomInStageHeights(safeDimension);
    }

    static double[] getRawZoomInStageHeights() {
        return getRawZoomInStageHeights(ZoomDimension.OVERWORLD);
    }

    static double[] getRawZoomInStageHeights(ZoomDimension dimension) {
        DimensionHeights cfg = heightsFor(dimension);
        return sanitizeStageHeights(new double[]{cfg.zoomInStage1.get(), cfg.zoomInStage2.get(), cfg.zoomInStage3.get()});
    }

    static boolean setZoomStageHeights(boolean linked, double[] zoomOutHeights, double[] zoomInHeights) {
        return setZoomStageHeights(ZoomDimension.OVERWORLD, linked, zoomOutHeights, zoomInHeights);
    }

    static boolean setZoomStageHeights(ZoomDimension dimension, boolean linked, double[] zoomOutHeights, double[] zoomInHeights) {
        double[] sanitizedOut = sanitizeStageHeights(zoomOutHeights);
        double[] sanitizedIn = sanitizeStageHeights(linked ? sanitizedOut : zoomInHeights);
        DimensionHeights cfg = heightsFor(dimension);
        return commit(() -> {
            cfg.linked.set(linked);
            cfg.zoomOutStage1.set(sanitizedOut[0]);
            cfg.zoomOutStage2.set(sanitizedOut[1]);
            cfg.zoomOutStage3.set(sanitizedOut[2]);
            cfg.zoomInStage1.set(sanitizedIn[0]);
            cfg.zoomInStage2.set(sanitizedIn[1]);
            cfg.zoomInStage3.set(sanitizedIn[2]);
        });
    }

    static int[] getZoomOutStageTicks() {
        return sanitizeStageTicks(new int[]{ZOOM_OUT_STAGE_TICKS_1.get(), ZOOM_OUT_STAGE_TICKS_2.get(), ZOOM_OUT_STAGE_TICKS_3.get()});
    }

    static int[] getZoomInStageTicks() {
        return sanitizeStageTicks(new int[]{ZOOM_IN_STAGE_TICKS_1.get(), ZOOM_IN_STAGE_TICKS_2.get(), ZOOM_IN_STAGE_TICKS_3.get()});
    }

    static boolean setZoomStageTicks(int[] zoomOutTicks, int[] zoomInTicks) {
        int[] sanitizedOut = sanitizeStageTicks(zoomOutTicks);
        int[] sanitizedIn = sanitizeStageTicks(zoomInTicks);
        return commit(() -> {
            ZOOM_OUT_STAGE_TICKS_1.set(sanitizedOut[0]);
            ZOOM_OUT_STAGE_TICKS_2.set(sanitizedOut[1]);
            ZOOM_OUT_STAGE_TICKS_3.set(sanitizedOut[2]);
            ZOOM_IN_STAGE_TICKS_1.set(sanitizedIn[0]);
            ZOOM_IN_STAGE_TICKS_2.set(sanitizedIn[1]);
            ZOOM_IN_STAGE_TICKS_3.set(sanitizedIn[2]);
        });
    }

    static double getZoomStageGlideHeight() {
        return ZOOM_STAGE_GLIDE_HEIGHT.get();
    }

    static boolean setZoomStageGlideHeight(double height) {
        return commit(() -> ZOOM_STAGE_GLIDE_HEIGHT.set(sanitizeZoomStageGlideHeight(height)));
    }

    static int getZoomStageGlideTicks() {
        return ZOOM_STAGE_GLIDE_TICKS.get();
    }

    static boolean setZoomStageGlideTicks(int ticks) {
        return commit(() -> ZOOM_STAGE_GLIDE_TICKS.set(sanitizeStageTicksValue(ticks)));
    }

    static double getBodyCameraHeight() {
        return BODY_CAMERA_HEIGHT.get();
    }

    static boolean setBodyCameraHeight(double height) {
        return commit(() -> BODY_CAMERA_HEIGHT.set(sanitizeBodyCameraHeight(height)));
    }

    static double getBodyGlideHeight() {
        return BODY_GLIDE_HEIGHT.get();
    }

    static boolean setBodyGlideHeight(double height) {
        return commit(() -> BODY_GLIDE_HEIGHT.set(sanitizeBodyGlideHeight(height)));
    }

    static int getBodyGlideTicks() {
        return BODY_GLIDE_TICKS.get();
    }

    static boolean setBodyGlideTicks(int ticks) {
        return commit(() -> BODY_GLIDE_TICKS.set(sanitizeStageTicksValue(ticks)));
    }

    static int getLocalPlayerHideTicks() {
        return LOCAL_PLAYER_HIDE_TICKS.get();
    }

    static boolean setLocalPlayerHideTicks(int ticks) {
        return commit(() -> LOCAL_PLAYER_HIDE_TICKS.set(sanitizeLocalPlayerHideTicks(ticks)));
    }

    static boolean isCustomSoundsEnabled() {
        return CUSTOM_SOUNDS_ENABLED.get();
    }

    static boolean setCustomSoundsEnabled(boolean enabled) {
        return commit(() -> CUSTOM_SOUNDS_ENABLED.set(enabled));
    }

    static double getMinecraftSoundVolume() {
        return MINECRAFT_SOUND_VOLUME.get();
    }

    static boolean setMinecraftSoundVolume(double volume) {
        return commit(() -> MINECRAFT_SOUND_VOLUME.set(sanitizeSoundVolume(volume)));
    }

    static double getCustomSoundVolume() {
        return CUSTOM_SOUND_VOLUME.get();
    }

    static boolean setCustomSoundVolume(double volume) {
        return commit(() -> CUSTOM_SOUND_VOLUME.set(sanitizeSoundVolume(volume)));
    }

    static boolean isWarpPlateTransitionsEnabled() {
        return WARP_PLATE_TRANSITIONS_ENABLED.get();
    }

    static boolean setWarpPlateTransitionsEnabled(boolean enabled) {
        return commit(() -> WARP_PLATE_TRANSITIONS_ENABLED.set(enabled));
    }

    static boolean isExternalTeleportTransitionsEnabled() {
        return EXTERNAL_TELEPORT_TRANSITIONS_ENABLED.get();
    }

    static boolean setExternalTeleportTransitionsEnabled(boolean enabled) {
        return commit(() -> EXTERNAL_TELEPORT_TRANSITIONS_ENABLED.set(enabled));
    }

    static boolean isFallbackChunkFadeEnabled() {
        return FALLBACK_CHUNK_FADE_ENABLED.get();
    }

    static boolean setFallbackChunkFadeEnabled(boolean enabled) {
        return commit(() -> FALLBACK_CHUNK_FADE_ENABLED.set(enabled));
    }

    static double[] sanitizeStageHeights(double[] values) {
        double[] source = values == null || values.length < 3 ? DEFAULT_STAGE_HEIGHTS : values;
        double[] sanitized = new double[3];
        sanitized[0] = clamp(roundStageHeight(source[0]), 8.0, 510.0);
        sanitized[1] = clamp(roundStageHeight(source[1]), sanitized[0] + 1.0, 511.0);
        sanitized[2] = clamp(roundStageHeight(source[2]), sanitized[1] + 1.0, 512.0);
        return sanitized;
    }

    static int[] sanitizeStageTicks(int[] values) {
        int[] source = values == null || values.length < 3 ? DEFAULT_STAGE_TICKS : values;
        int[] sanitized = new int[3];
        for (int i = 0; i < 3; i++) {
            sanitized[i] = sanitizeStageTicksValue(source[i]);
        }
        return sanitized;
    }

    static double sanitizeZoomStageGlideHeight(double value) {
        return Math.round(clamp(value, 0.1, 5.0) * 10.0) / 10.0;
    }

    static double sanitizeBodyCameraHeight(double value) {
        return Math.round(clamp(value, 0.1, 10.0) * 10.0) / 10.0;
    }

    static double sanitizeBodyGlideHeight(double value) {
        return Math.round(clamp(value, 0.1, 5.0) * 10.0) / 10.0;
    }

    static int sanitizeStageTicksValue(int value) {
        return clamp(value, 1, 200);
    }

    static int sanitizeLocalPlayerHideTicks(int value) {
        return clamp(value, 0, 20);
    }

    static double sanitizeSoundVolume(double value) {
        return Math.round(clamp(value, 0.1, 1.0) * 10.0) / 10.0;
    }

    static double getMinStageHeight() { return 8.0; }
    static double getMaxStageHeight() { return 512.0; }
    static double getMinStageGap() { return 1.0; }
    static double[] getDefaultStageHeights() { return DEFAULT_STAGE_HEIGHTS.clone(); }
    static int[] getDefaultStageTicks() { return DEFAULT_STAGE_TICKS.clone(); }
    static int getMinStageTicks() { return 1; }
    static int getMaxStageTicks() { return 200; }
    static double getDefaultZoomStageGlideHeight() { return 0.5; }
    static double getMinZoomStageGlideHeight() { return 0.1; }
    static double getMaxZoomStageGlideHeight() { return 5.0; }
    static int getDefaultZoomStageGlideTicks() { return 13; }
    static double getDefaultBodyCameraHeight() { return 6.0; }
    static double getMinBodyCameraHeight() { return 0.1; }
    static double getMaxBodyCameraHeight() { return 10.0; }
    static double getDefaultBodyGlideHeight() { return 0.5; }
    static double getDefaultBodyGlideTicks() { return 10; }
    static int getDefaultLocalPlayerHideTicks() { return 2; }
    static int getMinLocalPlayerHideTicks() { return 0; }
    static int getMaxLocalPlayerHideTicks() { return 20; }
    static double getDefaultMinecraftSoundVolume() { return 1.0; }
    static double getDefaultCustomSoundVolume() { return 0.3; }
    static double getMinSoundVolume() { return 0.1; }
    static double getMaxSoundVolume() { return 1.0; }

    private static DimensionHeights heightsFor(ZoomDimension dimension) {
        return switch (sanitizeZoomDimension(dimension)) {
            case NETHER -> NETHER_HEIGHTS;
            case END -> END_HEIGHTS;
            default -> OVERWORLD_HEIGHTS;
        };
    }

    private static boolean commit(Runnable action) {
        try {
            action.run();
            return true;
        }
        catch (RuntimeException ignored) {
            return false;
        }
    }

    private static double roundStageHeight(double value) {
        return Math.rint(value);
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    private static ZoomDimension sanitizeZoomDimension(ZoomDimension dimension) {
        return dimension == null ? ZoomDimension.OVERWORLD : dimension;
    }

    enum ZoomDimension {
        OVERWORLD,
        NETHER,
        END;

        static ZoomDimension fromLevel(ResourceKey<Level> dimension) {
            if (Level.NETHER.equals(dimension)) {
                return NETHER;
            }
            if (Level.END.equals(dimension)) {
                return END;
            }
            return OVERWORLD;
        }
    }

    private static final class DimensionHeights {
        private final ModConfigSpec.ConfigValue<Boolean> linked;
        private final ModConfigSpec.ConfigValue<Double> zoomOutStage1;
        private final ModConfigSpec.ConfigValue<Double> zoomOutStage2;
        private final ModConfigSpec.ConfigValue<Double> zoomOutStage3;
        private final ModConfigSpec.ConfigValue<Double> zoomInStage1;
        private final ModConfigSpec.ConfigValue<Double> zoomInStage2;
        private final ModConfigSpec.ConfigValue<Double> zoomInStage3;

        private DimensionHeights(ModConfigSpec.Builder builder, String section) {
            builder.push(section)
                .comment("Camera height above the player's feet at each zoom stage (1st / 2nd / 3rd).",
                    "Values are clamped to " + MIN_STAGE_HEIGHT + "-" + MAX_STAGE_HEIGHT
                        + " and kept at least " + MIN_STAGE_GAP + " block apart.");
            this.linked = builder.define("linked", true);
            this.zoomOutStage1 = builder.defineInRange("zoomOutStage1", DEFAULT_STAGE_HEIGHTS[0], MIN_STAGE_HEIGHT, MAX_STAGE_HEIGHT - 2.0);
            this.zoomOutStage2 = builder.defineInRange("zoomOutStage2", DEFAULT_STAGE_HEIGHTS[1], MIN_STAGE_HEIGHT + 1.0, MAX_STAGE_HEIGHT - 1.0);
            this.zoomOutStage3 = builder.defineInRange("zoomOutStage3", DEFAULT_STAGE_HEIGHTS[2], MIN_STAGE_HEIGHT + 2.0, MAX_STAGE_HEIGHT);
            this.zoomInStage1 = builder.defineInRange("zoomInStage1", DEFAULT_STAGE_HEIGHTS[0], MIN_STAGE_HEIGHT, MAX_STAGE_HEIGHT - 2.0);
            this.zoomInStage2 = builder.defineInRange("zoomInStage2", DEFAULT_STAGE_HEIGHTS[1], MIN_STAGE_HEIGHT + 1.0, MAX_STAGE_HEIGHT - 1.0);
            this.zoomInStage3 = builder.defineInRange("zoomInStage3", DEFAULT_STAGE_HEIGHTS[2], MIN_STAGE_HEIGHT + 2.0, MAX_STAGE_HEIGHT);
            builder.pop();
        }
    }
}
