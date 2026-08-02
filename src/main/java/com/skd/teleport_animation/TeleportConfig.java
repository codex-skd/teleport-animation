package com.skd.teleport_animation;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.neoforged.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

final class TeleportConfig {
    private static final String FILE_NAME = "teleport_animation.properties";
    private static final String LEGACY_FILE_NAME = "gtalike_teleport.properties";
    private static final String PREVIOUS_FILE_NAME = "grand_teleport.properties";
    private static final String EFFECT_ENABLED_KEY = "effectEnabled";
    private static final String PLAYER_FREEZE_ENABLED_KEY = "playerFreezeEnabled";
    private static final String CROSS_DIMENSION_TRAVEL_ENABLED_KEY = "crossDimensionTravelEnabled";
    private static final String ZOOM_HEIGHTS_LINKED_KEY = "zoomHeightsLinked";
    private static final String ZOOM_OUT_STAGE_KEY_PREFIX = "zoomOutStage";
    private static final String ZOOM_IN_STAGE_KEY_PREFIX = "zoomInStage";
    private static final String NETHER_ZOOM_HEIGHTS_LINKED_KEY = "netherZoomHeightsLinked";
    private static final String NETHER_ZOOM_OUT_STAGE_KEY_PREFIX = "netherZoomOutStage";
    private static final String NETHER_ZOOM_IN_STAGE_KEY_PREFIX = "netherZoomInStage";
    private static final String END_ZOOM_HEIGHTS_LINKED_KEY = "endZoomHeightsLinked";
    private static final String END_ZOOM_OUT_STAGE_KEY_PREFIX = "endZoomOutStage";
    private static final String END_ZOOM_IN_STAGE_KEY_PREFIX = "endZoomInStage";
    private static final String ZOOM_OUT_STAGE_TICKS_KEY_PREFIX = "zoomOutStageTicks";
    private static final String ZOOM_IN_STAGE_TICKS_KEY_PREFIX = "zoomInStageTicks";
    private static final String ZOOM_STAGE_GLIDE_HEIGHT_KEY = "zoomStageGlideHeight";
    private static final String ZOOM_STAGE_GLIDE_TICKS_KEY = "zoomStageGlideTicks";
    private static final String BODY_CAMERA_HEIGHT_KEY = "bodyCameraHeight";
    private static final String BODY_GLIDE_HEIGHT_KEY = "bodyGlideHeight";
    private static final String BODY_GLIDE_TICKS_KEY = "bodyGlideTicks";
    private static final String LOCAL_PLAYER_HIDE_TICKS_KEY = "localPlayerHideTicks";
    private static final String CUSTOM_SOUNDS_ENABLED_KEY = "customSoundsEnabled";
    private static final String MINECRAFT_SOUND_VOLUME_KEY = "minecraftSoundVolume";
    private static final String CUSTOM_SOUND_VOLUME_KEY = "customSoundVolume";
    private static final String WARP_PLATE_TRANSITIONS_ENABLED_KEY = "warpPlateTransitionsEnabled";
    private static final String EXTERNAL_TELEPORT_TRANSITIONS_ENABLED_KEY = "externalTeleportTransitionsEnabled";
    private static final String FALLBACK_CHUNK_FADE_ENABLED_KEY = "fallbackChunkFadeEnabled";
    private static final String CONFIG_LAYOUT_EDITOR_BUTTON_VISIBLE_KEY = "configLayoutEditorButtonVisible";
    private static final String CONFIG_LAYOUT_DEBUG_ENABLED_KEY = "configLayoutDebugEnabled";
    private static final String CONFIG_LAYOUT_ASPECT_LOCKED_KEY = "configLayoutAspectLocked";
    private static final String CONFIG_LAYOUT_GRID_ENABLED_KEY = "configLayoutGridEnabled";
    private static final String CONFIG_LAYOUT_SNAP_ENABLED_KEY = "configLayoutSnapEnabled";
    private static final String CONFIG_LAYOUT_CUSTOM_KEY = "configLayoutCustom";
    private static final String CONFIG_LAYOUT_X_KEY = "configLayoutX";
    private static final String CONFIG_LAYOUT_Y_KEY = "configLayoutY";
    private static final String CONFIG_LAYOUT_WIDTH_KEY = "configLayoutWidth";
    private static final String CONFIG_LAYOUT_HEIGHT_KEY = "configLayoutHeight";
    private static final String CONFIG_LAYOUT_BASE_WIDTH_KEY = "configLayoutBaseWidth";
    private static final String CONFIG_LAYOUT_BASE_HEIGHT_KEY = "configLayoutBaseHeight";
    private static final String CONFIG_WIDGET_PREFIX = "configWidget.";
    private static final String CONFIG_TEXT_PREFIX = "configText.";
    private static final String DEFAULT_CONFIG_PROPERTIES = "bodyCameraHeight=6.0\nbodyGlideHeight=0.5\nbodyGlideTicks=10\nconfigLayoutAspectLocked=false\nconfigLayoutBaseHeight=353\nconfigLayoutBaseWidth=640\nconfigLayoutCustom=true\nconfigLayoutDebugEnabled=false\nconfigLayoutEditorButtonVisible=false\nconfigLayoutGridEnabled=true\nconfigLayoutHeight=0.6005665722379604\nconfigLayoutSnapEnabled=false\nconfigLayoutWidth=0.5796875\nconfigLayoutX=0.2109375\nconfigLayoutY=0.23796033994334279\nconfigText.advanced1_title=Teleport Animation Settings (1)\nconfigText.advanced2_description=Set tick lengths for each zoom stage. (1st / 2nd / 3rd)\nconfigText.advanced2_title=ZoomStage Settings (2)\nconfigText.advanced3_title=Teleport Animation Settings (3)\nconfigText.done_button=Close\nconfigText.fallback_chunk_fade_label=Vanilla/Sodium chunk-mask fade\nconfigText.general_title=General Settings\nconfigText.linked_slider=\\    camera_zoom 1st / 2nd / 3rd\nconfigText.others_title=Other Settings\nconfigText.reset_button=Reset\nconfigText.sounds_title=Sound Settings\nconfigText.title=ZoomStage Settings\nconfigText.zoom_out_ticks_label=Zoom-out stage ticks\nconfigWidget.advanced1_description.baseHeight=195\nconfigWidget.advanced1_description.baseWidth=368\nconfigWidget.advanced1_description.height=0.05128205128205128\nconfigWidget.advanced1_description.width=0.5407608695652174\nconfigWidget.advanced1_description.x=0.22826086956521738\nconfigWidget.advanced1_description.y=0.13333333333333333\nconfigWidget.advanced1_title.baseHeight=195\nconfigWidget.advanced1_title.baseWidth=368\nconfigWidget.advanced1_title.height=0.05128205128205128\nconfigWidget.advanced1_title.width=0.24456521739130435\nconfigWidget.advanced1_title.x=0.37771739130434784\nconfigWidget.advanced1_title.y=0.015384615384615385\nconfigWidget.advanced2_description.baseHeight=212\nconfigWidget.advanced2_description.baseWidth=371\nconfigWidget.advanced2_description.height=0.04716981132075472\nconfigWidget.advanced2_description.width... (line truncated to 2000 chars)";
    private static final double[] DEFAULT_STAGE_HEIGHTS = new double[]{20.0, 40.0, 60.0};
    private static final double MIN_STAGE_HEIGHT = 8.0;
    private static final double MAX_STAGE_HEIGHT = 512.0;
    private static final double MIN_STAGE_GAP = 1.0;
    private static final int[] DEFAULT_STAGE_TICKS = new int[]{13, 13, 13};
    private static final int MIN_STAGE_TICKS = 1;
    private static final int MAX_STAGE_TICKS = 200;
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
    private static Path configPath;
    private static boolean effectEnabled;
    private static boolean playerFreezeEnabled;
    private static boolean crossDimensionTravelEnabled;
    private static boolean zoomHeightsLinked;
    private static double[] zoomOutStageHeights;
    private static double[] zoomInStageHeights;
    private static boolean netherZoomHeightsLinked;
    private static double[] netherZoomOutStageHeights;
    private static double[] netherZoomInStageHeights;
    private static boolean endZoomHeightsLinked;
    private static double[] endZoomOutStageHeights;
    private static double[] endZoomInStageHeights;
    private static int[] zoomOutStageTicks;
    private static int[] zoomInStageTicks;
    private static double zoomStageGlideHeight;
    private static int zoomStageGlideTicks;
    private static double bodyCameraHeight;
    private static double bodyGlideHeight;
    private static int bodyGlideTicks;
    private static int localPlayerHideTicks;
    private static boolean customSoundsEnabled;
    private static double minecraftSoundVolume;
    private static double customSoundVolume;
    private static boolean warpPlateTransitionsEnabled;
    private static boolean externalTeleportTransitionsEnabled;
    private static boolean fallbackChunkFadeEnabled;
    private static boolean configLayoutEditorButtonVisible;
    private static boolean configLayoutDebugEnabled;
    private static boolean configLayoutAspectLocked;
    private static boolean configLayoutGridEnabled;
    private static boolean configLayoutSnapEnabled;
    private static boolean configLayoutCustom;
    private static double configLayoutX;
    private static double configLayoutY;
    private static double configLayoutWidth;
    private static double configLayoutHeight;
    private static int configLayoutBaseWidth;
    private static int configLayoutBaseHeight;
    private static final Map<String, double[]> configWidgetLayouts = new HashMap<>();
    private static final Map<String, String> configTexts = new HashMap<>();

    private TeleportConfig() {
    }

    static void load() {
        configPath = resolveConfigPath();
        migrateLegacyConfig();
        resetToDefaults();
        if (!Files.exists(configPath)) {
            return;
        }
        boolean rewriteConfig = false;
        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(configPath)) {
            properties.load(input);
            rewriteConfig = prepareLoadedProperties(properties);
            applyConfigProperties(properties);
            rewriteConfig = rewriteConfig || !properties.containsKey(CONFIG_LAYOUT_EDITOR_BUTTON_VISIBLE_KEY);
        }
        catch (IOException ignored) {
            resetToDefaults();
        }
        if (rewriteConfig) {
            save();
        }
    }

    static boolean isEffectEnabled() {
        return effectEnabled;
    }

    static boolean setEffectEnabled(boolean enabled) {
        effectEnabled = enabled;
        return save();
    }

    static boolean isPlayerFreezeEnabled() {
        return playerFreezeEnabled;
    }

    static boolean setPlayerFreezeEnabled(boolean enabled) {
        playerFreezeEnabled = enabled;
        return save();
    }

    static boolean isCrossDimensionTravelEnabled() {
        return crossDimensionTravelEnabled;
    }

    static boolean setCrossDimensionTravelEnabled(boolean enabled) {
        crossDimensionTravelEnabled = enabled;
        return save();
    }

    static boolean areZoomHeightsLinked() {
        return areZoomHeightsLinked(ZoomDimension.OVERWORLD);
    }

    static boolean areZoomHeightsLinked(ZoomDimension dimension) {
        return switch (sanitizeZoomDimension(dimension)) {
            case NETHER -> netherZoomHeightsLinked;
            case END -> endZoomHeightsLinked;
            default -> zoomHeightsLinked;
        };
    }

    static double[] getZoomOutStageHeights() {
        return getZoomOutStageHeights(ZoomDimension.OVERWORLD);
    }

    static double[] getZoomOutStageHeights(ZoomDimension dimension) {
        return switch (sanitizeZoomDimension(dimension)) {
            case NETHER -> netherZoomOutStageHeights.clone();
            case END -> endZoomOutStageHeights.clone();
            default -> zoomOutStageHeights.clone();
        };
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
        return switch (sanitizeZoomDimension(dimension)) {
            case NETHER -> netherZoomInStageHeights.clone();
            case END -> endZoomInStageHeights.clone();
            default -> zoomInStageHeights.clone();
        };
    }

    static boolean setZoomStageHeights(boolean linked, double[] zoomOutHeights, double[] zoomInHeights) {
        return setZoomStageHeights(ZoomDimension.OVERWORLD, linked, zoomOutHeights, zoomInHeights);
    }

    static boolean setZoomStageHeights(ZoomDimension dimension, boolean linked, double[] zoomOutHeights, double[] zoomInHeights) {
        double[] sanitizedOut = sanitizeStageHeights(zoomOutHeights);
        double[] sanitizedIn = sanitizeStageHeights(linked ? sanitizedOut : zoomInHeights);
        switch (sanitizeZoomDimension(dimension)) {
            case NETHER -> {
                netherZoomHeightsLinked = linked;
                netherZoomOutStageHeights = sanitizedOut;
                netherZoomInStageHeights = sanitizedIn;
            }
            case END -> {
                endZoomHeightsLinked = linked;
                endZoomOutStageHeights = sanitizedOut;
                endZoomInStageHeights = sanitizedIn;
            }
            default -> {
                zoomHeightsLinked = linked;
                zoomOutStageHeights = sanitizedOut;
                zoomInStageHeights = sanitizedIn;
            }
        }
        return save();
    }

    static int[] getZoomOutStageTicks() {
        return zoomOutStageTicks.clone();
    }

    static int[] getZoomInStageTicks() {
        return zoomInStageTicks.clone();
    }

    static boolean setZoomStageTicks(int[] zoomOutTicks, int[] zoomInTicks) {
        zoomOutStageTicks = sanitizeStageTicks(zoomOutTicks);
        zoomInStageTicks = sanitizeStageTicks(zoomInTicks);
        return save();
    }

    static double getZoomStageGlideHeight() {
        return zoomStageGlideHeight;
    }

    static boolean setZoomStageGlideHeight(double height) {
        zoomStageGlideHeight = sanitizeZoomStageGlideHeight(height);
        return save();
    }

    static int getZoomStageGlideTicks() {
        return zoomStageGlideTicks;
    }

    static boolean setZoomStageGlideTicks(int ticks) {
        zoomStageGlideTicks = sanitizeStageTicksValue(ticks);
        return save();
    }

    static double getBodyCameraHeight() {
        return bodyCameraHeight;
    }

    static boolean setBodyCameraHeight(double height) {
        bodyCameraHeight = sanitizeBodyCameraHeight(height);
        return save();
    }

    static double getBodyGlideHeight() {
        return bodyGlideHeight;
    }

    static boolean setBodyGlideHeight(double height) {
        bodyGlideHeight = sanitizeBodyGlideHeight(height);
        return save();
    }

    static int getBodyGlideTicks() {
        return bodyGlideTicks;
    }

    static boolean setBodyGlideTicks(int ticks) {
        bodyGlideTicks = sanitizeStageTicksValue(ticks);
        return save();
    }

    static int getLocalPlayerHideTicks() {
        return localPlayerHideTicks;
    }

    static boolean setLocalPlayerHideTicks(int ticks) {
        localPlayerHideTicks = sanitizeLocalPlayerHideTicks(ticks);
        return save();
    }

    static boolean isCustomSoundsEnabled() {
        return customSoundsEnabled;
    }

    static boolean setCustomSoundsEnabled(boolean enabled) {
        customSoundsEnabled = enabled;
        return save();
    }

    static double getMinecraftSoundVolume() {
        return minecraftSoundVolume;
    }

    static boolean setMinecraftSoundVolume(double volume) {
        minecraftSoundVolume = sanitizeSoundVolume(volume);
        return save();
    }

    static double getCustomSoundVolume() {
        return customSoundVolume;
    }

    static boolean setCustomSoundVolume(double volume) {
        customSoundVolume = sanitizeSoundVolume(volume);
        return save();
    }

    static boolean isWarpPlateTransitionsEnabled() {
        return warpPlateTransitionsEnabled;
    }

    static boolean setWarpPlateTransitionsEnabled(boolean enabled) {
        warpPlateTransitionsEnabled = enabled;
        return save();
    }

    static boolean isExternalTeleportTransitionsEnabled() {
        return externalTeleportTransitionsEnabled;
    }

    static boolean setExternalTeleportTransitionsEnabled(boolean enabled) {
        externalTeleportTransitionsEnabled = enabled;
        return save();
    }

    static boolean isFallbackChunkFadeEnabled() {
        return fallbackChunkFadeEnabled;
    }

    static boolean setFallbackChunkFadeEnabled(boolean enabled) {
        fallbackChunkFadeEnabled = enabled;
        return save();
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

    static boolean isConfigLayoutEditorButtonVisible() { return configLayoutEditorButtonVisible; }
    static boolean setConfigLayoutEditorButtonVisible(boolean visible) { configLayoutEditorButtonVisible = visible; return save(); }
    static boolean isConfigLayoutDebugEnabled() { return configLayoutDebugEnabled; }
    static boolean setConfigLayoutDebugEnabled(boolean enabled) { configLayoutDebugEnabled = enabled; return save(); }
    static boolean isConfigLayoutAspectLocked() { return configLayoutAspectLocked; }
    static boolean setConfigLayoutAspectLocked(boolean locked) { configLayoutAspectLocked = locked; return save(); }
    static boolean isConfigLayoutGridEnabled() { return configLayoutGridEnabled; }
    static boolean setConfigLayoutGridEnabled(boolean enabled) { configLayoutGridEnabled = enabled; return save(); }
    static boolean isConfigLayoutSnapEnabled() { return configLayoutSnapEnabled; }
    static boolean setConfigLayoutSnapEnabled(boolean enabled) { configLayoutSnapEnabled = enabled; return save(); }
    static boolean hasCustomConfigLayout() { return configLayoutCustom; }

    static double[] getConfigLayout() {
        return new double[]{configLayoutX, configLayoutY, configLayoutWidth, configLayoutHeight};
    }

    static int getConfigLayoutBaseWidth() { return configLayoutBaseWidth; }
    static int getConfigLayoutBaseHeight() { return configLayoutBaseHeight; }

    static boolean setConfigLayout(double x, double y, double width, double height) {
        return setConfigLayout(x, y, width, height, configLayoutBaseWidth, configLayoutBaseHeight);
    }

    static boolean setConfigLayout(double x, double y, double width, double height, int baseWidth, int baseHeight) {
        configLayoutCustom = true;
        configLayoutX = clamp(x, 0.0, 1.0);
        configLayoutY = clamp(y, 0.0, 1.0);
        configLayoutWidth = clamp(width, 0.0, 1.0);
        configLayoutHeight = clamp(height, 0.0, 1.0);
        configLayoutBaseWidth = Math.max(1, baseWidth);
        configLayoutBaseHeight = Math.max(1, baseHeight);
        return save();
    }

    static boolean resetConfigLayout() {
        Properties defaults = createDefaultProperties();
        configLayoutCustom = Boolean.parseBoolean(defaults.getProperty(CONFIG_LAYOUT_CUSTOM_KEY, Boolean.toString(configLayoutCustom)));
        configLayoutX = readUnitDouble(defaults, CONFIG_LAYOUT_X_KEY, configLayoutX);
        configLayoutY = readUnitDouble(defaults, CONFIG_LAYOUT_Y_KEY, configLayoutY);
        configLayoutWidth = readUnitDouble(defaults, CONFIG_LAYOUT_WIDTH_KEY, configLayoutWidth);
        configLayoutHeight = readUnitDouble(defaults, CONFIG_LAYOUT_HEIGHT_KEY, configLayoutHeight);
        configLayoutBaseWidth = readPositiveInt(defaults, CONFIG_LAYOUT_BASE_WIDTH_KEY, configLayoutBaseWidth);
        configLayoutBaseHeight = readPositiveInt(defaults, CONFIG_LAYOUT_BASE_HEIGHT_KEY, configLayoutBaseHeight);
        return save();
    }

    static boolean hasConfigWidgetLayout(String id) {
        return configWidgetLayouts.containsKey(id);
    }

    static double[] getConfigWidgetLayout(String id) {
        double[] values = configWidgetLayouts.get(id);
        if (values == null) {
            return new double[6];
        }
        return values.clone();
    }

    static boolean setConfigWidgetLayout(String id, double x, double y, double width, double height) {
        return setConfigWidgetLayout(id, x, y, width, height, 0, 0);
    }

    static boolean setConfigWidgetLayout(String id, double x, double y, double width, double height, int baseWidth, int baseHeight) {
        if (!isSafeId(id)) return false;
        configWidgetLayouts.put(id, new double[]{clamp(x, -2.0, 3.0), clamp(y, -2.0, 3.0), clamp(width, 0.01, 3.0), clamp(height, 0.01, 3.0), Math.max(0, baseWidth), Math.max(0, baseHeight)});
        return save();
    }

    static boolean resetConfigWidgetLayout(String id) {
        if (!isSafeId(id)) return false;
        Properties defaults = createDefaultProperties();
        String prefix = CONFIG_WIDGET_PREFIX + id;
        double x = readDouble(defaults, prefix + ".x", 0.0);
        double y = readDouble(defaults, prefix + ".y", 0.0);
        double width = readDouble(defaults, prefix + ".width", 0.0);
        double height = readDouble(defaults, prefix + ".height", 0.0);
        int baseWidth = readPositiveInt(defaults, prefix + ".baseWidth", 0);
        int baseHeight = readPositiveInt(defaults, prefix + ".baseHeight", 0);
        if (width > 0.0 && height > 0.0) {
            configWidgetLayouts.put(id, new double[]{clamp(x, -2.0, 3.0), clamp(y, -2.0, 3.0), clamp(width, 0.01, 3.0), clamp(height, 0.01, 3.0), baseWidth, baseHeight});
        } else {
            configWidgetLayouts.remove(id);
        }
        return save();
    }

    static boolean resetConfigWidgetLayouts() {
        readWidgetLayouts(createDefaultProperties());
        return save();
    }

    static String getConfigText(String id, String fallback) {
        String value = configTexts.get(id);
        return value == null ? fallback : value;
    }

    static boolean setConfigText(String id, String text) {
        if (!isSafeId(id)) return false;
        if (text == null || text.isEmpty()) {
            configTexts.remove(id);
        } else {
            configTexts.put(id, text);
        }
        return save();
    }

    static boolean resetConfigText(String id) {
        if (!isSafeId(id)) return false;
        String value = createDefaultProperties().getProperty(CONFIG_TEXT_PREFIX + id);
        if (value == null || value.isEmpty()) {
            configTexts.remove(id);
        } else {
            configTexts.put(id, value);
        }
        return save();
    }

    private static void applyConfigProperties(Properties properties) {
        effectEnabled = Boolean.parseBoolean(properties.getProperty(EFFECT_ENABLED_KEY, Boolean.toString(effectEnabled)));
        playerFreezeEnabled = Boolean.parseBoolean(properties.getProperty(PLAYER_FREEZE_ENABLED_KEY, Boolean.toString(playerFreezeEnabled)));
        crossDimensionTravelEnabled = Boolean.parseBoolean(properties.getProperty(CROSS_DIMENSION_TRAVEL_ENABLED_KEY, Boolean.toString(crossDimensionTravelEnabled)));
        zoomHeightsLinked = Boolean.parseBoolean(properties.getProperty(ZOOM_HEIGHTS_LINKED_KEY, Boolean.toString(zoomHeightsLinked)));
        zoomOutStageHeights = readStageHeights(properties, ZOOM_OUT_STAGE_KEY_PREFIX, DEFAULT_STAGE_HEIGHTS);
        zoomInStageHeights = readStageHeights(properties, ZOOM_IN_STAGE_KEY_PREFIX, DEFAULT_STAGE_HEIGHTS);
        if (zoomHeightsLinked) {
            zoomInStageHeights = zoomOutStageHeights.clone();
        }
        netherZoomHeightsLinked = Boolean.parseBoolean(properties.getProperty(NETHER_ZOOM_HEIGHTS_LINKED_KEY, Boolean.toString(zoomHeightsLinked)));
        netherZoomOutStageHeights = readStageHeights(properties, NETHER_ZOOM_OUT_STAGE_KEY_PREFIX, zoomOutStageHeights);
        netherZoomInStageHeights = readStageHeights(properties, NETHER_ZOOM_IN_STAGE_KEY_PREFIX, zoomInStageHeights);
        if (netherZoomHeightsLinked) {
            netherZoomInStageHeights = netherZoomOutStageHeights.clone();
        }
        endZoomHeightsLinked = Boolean.parseBoolean(properties.getProperty(END_ZOOM_HEIGHTS_LINKED_KEY, Boolean.toString(zoomHeightsLinked)));
        endZoomOutStageHeights = readStageHeights(properties, END_ZOOM_OUT_STAGE_KEY_PREFIX, zoomOutStageHeights);
        endZoomInStageHeights = readStageHeights(properties, END_ZOOM_IN_STAGE_KEY_PREFIX, zoomInStageHeights);
        if (endZoomHeightsLinked) {
            endZoomInStageHeights = endZoomOutStageHeights.clone();
        }
        zoomOutStageTicks = readStageTicks(properties, ZOOM_OUT_STAGE_TICKS_KEY_PREFIX, DEFAULT_STAGE_TICKS);
        zoomInStageTicks = readStageTicks(properties, ZOOM_IN_STAGE_TICKS_KEY_PREFIX, DEFAULT_STAGE_TICKS);
        zoomStageGlideHeight = readClampedDouble(properties, ZOOM_STAGE_GLIDE_HEIGHT_KEY, zoomStageGlideHeight, 0.1, 5.0);
        zoomStageGlideTicks = readClampedInt(properties, ZOOM_STAGE_GLIDE_TICKS_KEY, zoomStageGlideTicks, 1, 200);
        bodyCameraHeight = readClampedDouble(properties, BODY_CAMERA_HEIGHT_KEY, bodyCameraHeight, 0.1, 10.0);
        bodyGlideHeight = readClampedDouble(properties, BODY_GLIDE_HEIGHT_KEY, bodyGlideHeight, 0.1, 5.0);
        bodyGlideTicks = readClampedInt(properties, BODY_GLIDE_TICKS_KEY, bodyGlideTicks, 1, 200);
        localPlayerHideTicks = readClampedInt(properties, LOCAL_PLAYER_HIDE_TICKS_KEY, localPlayerHideTicks, 0, 20);
        customSoundsEnabled = Boolean.parseBoolean(properties.getProperty(CUSTOM_SOUNDS_ENABLED_KEY, Boolean.toString(customSoundsEnabled)));
        minecraftSoundVolume = readClampedDouble(properties, MINECRAFT_SOUND_VOLUME_KEY, minecraftSoundVolume, 0.1, 1.0);
        customSoundVolume = readClampedDouble(properties, CUSTOM_SOUND_VOLUME_KEY, customSoundVolume, 0.1, 1.0);
        warpPlateTransitionsEnabled = Boolean.parseBoolean(properties.getProperty(WARP_PLATE_TRANSITIONS_ENABLED_KEY, Boolean.toString(warpPlateTransitionsEnabled)));
        externalTeleportTransitionsEnabled = Boolean.parseBoolean(properties.getProperty(EXTERNAL_TELEPORT_TRANSITIONS_ENABLED_KEY, Boolean.toString(externalTeleportTransitionsEnabled)));
        fallbackChunkFadeEnabled = Boolean.parseBoolean(properties.getProperty(FALLBACK_CHUNK_FADE_ENABLED_KEY, Boolean.toString(fallbackChunkFadeEnabled)));
        configLayoutEditorButtonVisible = Boolean.parseBoolean(properties.getProperty(CONFIG_LAYOUT_EDITOR_BUTTON_VISIBLE_KEY, Boolean.toString(configLayoutEditorButtonVisible)));
        configLayoutDebugEnabled = Boolean.parseBoolean(properties.getProperty(CONFIG_LAYOUT_DEBUG_ENABLED_KEY, Boolean.toString(configLayoutDebugEnabled)));
        configLayoutAspectLocked = Boolean.parseBoolean(properties.getProperty(CONFIG_LAYOUT_ASPECT_LOCKED_KEY, Boolean.toString(configLayoutAspectLocked)));
        configLayoutGridEnabled = Boolean.parseBoolean(properties.getProperty(CONFIG_LAYOUT_GRID_ENABLED_KEY, Boolean.toString(configLayoutGridEnabled)));
        configLayoutSnapEnabled = Boolean.parseBoolean(properties.getProperty(CONFIG_LAYOUT_SNAP_ENABLED_KEY, Boolean.toString(configLayoutSnapEnabled)));
        configLayoutCustom = Boolean.parseBoolean(properties.getProperty(CONFIG_LAYOUT_CUSTOM_KEY, Boolean.toString(configLayoutCustom)));
        configLayoutX = readUnitDouble(properties, CONFIG_LAYOUT_X_KEY, configLayoutX);
        configLayoutY = readUnitDouble(properties, CONFIG_LAYOUT_Y_KEY, configLayoutY);
        configLayoutWidth = readUnitDouble(properties, CONFIG_LAYOUT_WIDTH_KEY, configLayoutWidth);
        configLayoutHeight = readUnitDouble(properties, CONFIG_LAYOUT_HEIGHT_KEY, configLayoutHeight);
        configLayoutBaseWidth = readPositiveInt(properties, CONFIG_LAYOUT_BASE_WIDTH_KEY, configLayoutBaseWidth);
        configLayoutBaseHeight = readPositiveInt(properties, CONFIG_LAYOUT_BASE_HEIGHT_KEY, configLayoutBaseHeight);
        readWidgetLayouts(properties);
        readConfigTexts(properties);
    }

    private static void resetToDefaults() {
        applyConfigProperties(createDefaultProperties());
    }

    private static Properties createDefaultProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(DEFAULT_CONFIG_PROPERTIES));
        }
        catch (IOException ignored) {
        }
        return properties;
    }

    private static double[] readStageHeights(Properties properties, String prefix, double[] defaults) {
        double[] values = defaults.clone();
        for (int i = 0; i < 3; i++) {
            values[i] = readDouble(properties, prefix + (i + 1), values[i]);
        }
        return sanitizeStageHeights(values);
    }

    private static int[] readStageTicks(Properties properties, String prefix, int[] defaults) {
        int[] values = defaults.clone();
        for (int i = 0; i < 3; i++) {
            values[i] = readClampedInt(properties, prefix + (i + 1), values[i], 1, 200);
        }
        return sanitizeStageTicks(values);
    }

    private static void readWidgetLayouts(Properties properties) {
        configWidgetLayouts.clear();
        for (String key : properties.stringPropertyNames()) {
            if (!key.startsWith(CONFIG_WIDGET_PREFIX) || !key.endsWith(".x")) continue;
            String id = key.substring(CONFIG_WIDGET_PREFIX.length(), key.length() - 2);
            if (!isSafeId(id)) continue;
            String prefix = CONFIG_WIDGET_PREFIX + id;
            double x = readDouble(properties, prefix + ".x", 0.0);
            double y = readDouble(properties, prefix + ".y", 0.0);
            double width = readDouble(properties, prefix + ".width", 0.0);
            double height = readDouble(properties, prefix + ".height", 0.0);
            int baseWidth = readPositiveInt(properties, prefix + ".baseWidth", 0);
            int baseHeight = readPositiveInt(properties, prefix + ".baseHeight", 0);
            if (width > 0.0 && height > 0.0) {
                configWidgetLayouts.put(id, new double[]{clamp(x, -2.0, 3.0), clamp(y, -2.0, 3.0), clamp(width, 0.01, 3.0), clamp(height, 0.01, 3.0), baseWidth, baseHeight});
            }
        }
    }

    private static void readConfigTexts(Properties properties) {
        configTexts.clear();
        for (String key : properties.stringPropertyNames()) {
            if (!key.startsWith(CONFIG_TEXT_PREFIX)) continue;
            String id = key.substring(CONFIG_TEXT_PREFIX.length());
            if (!isSafeId(id)) continue;
            configTexts.put(id, properties.getProperty(key, ""));
        }
    }

    private static boolean prepareLoadedProperties(Properties properties) {
        if (!isLegacyCompactLayoutConfig(properties)) return false;
        restoreDefaultLayoutProperties(properties);
        return true;
    }

    private static boolean isLegacyCompactLayoutConfig(Properties properties) {
        return !hasPropertyWithPrefix(properties, CONFIG_WIDGET_PREFIX)
            && !hasPropertyWithPrefix(properties, CONFIG_TEXT_PREFIX)
            && (properties.containsKey(CONFIG_LAYOUT_CUSTOM_KEY)
                || properties.containsKey(CONFIG_LAYOUT_BASE_WIDTH_KEY)
                || properties.containsKey(CONFIG_LAYOUT_BASE_HEIGHT_KEY)
                || properties.containsKey(CONFIG_LAYOUT_WIDTH_KEY)
                || properties.containsKey(CONFIG_LAYOUT_HEIGHT_KEY));
    }

    private static boolean hasPropertyWithPrefix(Properties properties, String prefix) {
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith(prefix)) return true;
        }
        return false;
    }

    private static void restoreDefaultLayoutProperties(Properties properties) {
        for (String key : properties.stringPropertyNames().stream().filter(k -> k.startsWith("configLayout") || k.startsWith(CONFIG_WIDGET_PREFIX) || k.startsWith(CONFIG_TEXT_PREFIX)).toList()) {
            properties.remove(key);
        }
        Properties defaults = createDefaultProperties();
        for (String key : defaults.stringPropertyNames()) {
            if (!key.startsWith("configLayout") && !key.startsWith(CONFIG_WIDGET_PREFIX) && !key.startsWith(CONFIG_TEXT_PREFIX)) continue;
            properties.setProperty(key, defaults.getProperty(key));
        }
    }

    private static double readDouble(Properties properties, String key, double fallback) {
        try {
            return Double.parseDouble(properties.getProperty(key, Double.toString(fallback)));
        }
        catch (NumberFormatException ignored) {
            return fallback;
        }
    }

    private static double readUnitDouble(Properties properties, String key, double fallback) {
        return clamp(readDouble(properties, key, fallback), 0.0, 1.0);
    }

    private static double readClampedDouble(Properties properties, String key, double fallback, double min, double max) {
        return clamp(readDouble(properties, key, fallback), min, max);
    }

    private static int readClampedInt(Properties properties, String key, int fallback, int min, int max) {
        try {
            return clamp(Integer.parseInt(properties.getProperty(key, Integer.toString(fallback))), min, max);
        }
        catch (NumberFormatException ignored) {
            return fallback;
        }
    }

    private static int readPositiveInt(Properties properties, String key, int fallback) {
        try {
            return Math.max(0, Integer.parseInt(properties.getProperty(key, Integer.toString(fallback))));
        }
        catch (NumberFormatException ignored) {
            return fallback;
        }
    }

    private static double roundStageHeight(double value) {
        return Math.rint(value);
    }

    private static boolean isSafeId(String id) {
        return id != null && id.matches("[a-z0-9_]+") && id.length() <= 64;
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    private static boolean save() {
        if (configPath == null) {
            configPath = resolveConfigPath();
        }
        Properties properties = new Properties();
        properties.setProperty(EFFECT_ENABLED_KEY, Boolean.toString(effectEnabled));
        properties.setProperty(PLAYER_FREEZE_ENABLED_KEY, Boolean.toString(playerFreezeEnabled));
        properties.setProperty(CROSS_DIMENSION_TRAVEL_ENABLED_KEY, Boolean.toString(crossDimensionTravelEnabled));
        properties.setProperty(ZOOM_HEIGHTS_LINKED_KEY, Boolean.toString(zoomHeightsLinked));
        writeStageHeights(properties, ZOOM_OUT_STAGE_KEY_PREFIX, zoomOutStageHeights);
        writeStageHeights(properties, ZOOM_IN_STAGE_KEY_PREFIX, zoomInStageHeights);
        properties.setProperty(NETHER_ZOOM_HEIGHTS_LINKED_KEY, Boolean.toString(netherZoomHeightsLinked));
        writeStageHeights(properties, NETHER_ZOOM_OUT_STAGE_KEY_PREFIX, netherZoomOutStageHeights);
        writeStageHeights(properties, NETHER_ZOOM_IN_STAGE_KEY_PREFIX, netherZoomInStageHeights);
        properties.setProperty(END_ZOOM_HEIGHTS_LINKED_KEY, Boolean.toString(endZoomHeightsLinked));
        writeStageHeights(properties, END_ZOOM_OUT_STAGE_KEY_PREFIX, endZoomOutStageHeights);
        writeStageHeights(properties, END_ZOOM_IN_STAGE_KEY_PREFIX, endZoomInStageHeights);
        writeStageTicks(properties, ZOOM_OUT_STAGE_TICKS_KEY_PREFIX, zoomOutStageTicks);
        writeStageTicks(properties, ZOOM_IN_STAGE_TICKS_KEY_PREFIX, zoomInStageTicks);
        properties.setProperty(ZOOM_STAGE_GLIDE_HEIGHT_KEY, Double.toString(zoomStageGlideHeight));
        properties.setProperty(ZOOM_STAGE_GLIDE_TICKS_KEY, Integer.toString(zoomStageGlideTicks));
        properties.setProperty(BODY_CAMERA_HEIGHT_KEY, Double.toString(bodyCameraHeight));
        properties.setProperty(BODY_GLIDE_HEIGHT_KEY, Double.toString(bodyGlideHeight));
        properties.setProperty(BODY_GLIDE_TICKS_KEY, Integer.toString(bodyGlideTicks));
        properties.setProperty(LOCAL_PLAYER_HIDE_TICKS_KEY, Integer.toString(localPlayerHideTicks));
        properties.setProperty(CUSTOM_SOUNDS_ENABLED_KEY, Boolean.toString(customSoundsEnabled));
        properties.setProperty(MINECRAFT_SOUND_VOLUME_KEY, Double.toString(minecraftSoundVolume));
        properties.setProperty(CUSTOM_SOUND_VOLUME_KEY, Double.toString(customSoundVolume));
        properties.setProperty(WARP_PLATE_TRANSITIONS_ENABLED_KEY, Boolean.toString(warpPlateTransitionsEnabled));
        properties.setProperty(EXTERNAL_TELEPORT_TRANSITIONS_ENABLED_KEY, Boolean.toString(externalTeleportTransitionsEnabled));
        properties.setProperty(FALLBACK_CHUNK_FADE_ENABLED_KEY, Boolean.toString(fallbackChunkFadeEnabled));
        properties.setProperty(CONFIG_LAYOUT_EDITOR_BUTTON_VISIBLE_KEY, Boolean.toString(configLayoutEditorButtonVisible));
        properties.setProperty(CONFIG_LAYOUT_DEBUG_ENABLED_KEY, Boolean.toString(configLayoutDebugEnabled));
        properties.setProperty(CONFIG_LAYOUT_ASPECT_LOCKED_KEY, Boolean.toString(configLayoutAspectLocked));
        properties.setProperty(CONFIG_LAYOUT_GRID_ENABLED_KEY, Boolean.toString(configLayoutGridEnabled));
        properties.setProperty(CONFIG_LAYOUT_SNAP_ENABLED_KEY, Boolean.toString(configLayoutSnapEnabled));
        properties.setProperty(CONFIG_LAYOUT_CUSTOM_KEY, Boolean.toString(configLayoutCustom));
        properties.setProperty(CONFIG_LAYOUT_X_KEY, Double.toString(configLayoutX));
        properties.setProperty(CONFIG_LAYOUT_Y_KEY, Double.toString(configLayoutY));
        properties.setProperty(CONFIG_LAYOUT_WIDTH_KEY, Double.toString(configLayoutWidth));
        properties.setProperty(CONFIG_LAYOUT_HEIGHT_KEY, Double.toString(configLayoutHeight));
        properties.setProperty(CONFIG_LAYOUT_BASE_WIDTH_KEY, Integer.toString(configLayoutBaseWidth));
        properties.setProperty(CONFIG_LAYOUT_BASE_HEIGHT_KEY, Integer.toString(configLayoutBaseHeight));
        for (Map.Entry<String, double[]> entry : configWidgetLayouts.entrySet()) {
            double[] values = entry.getValue();
            String prefix = CONFIG_WIDGET_PREFIX + entry.getKey();
            properties.setProperty(prefix + ".x", Double.toString(values[0]));
            properties.setProperty(prefix + ".y", Double.toString(values[1]));
            properties.setProperty(prefix + ".width", Double.toString(values[2]));
            properties.setProperty(prefix + ".height", Double.toString(values[3]));
            if (values.length > 5) {
                properties.setProperty(prefix + ".baseWidth", Integer.toString((int) Math.round(values[4])));
                properties.setProperty(prefix + ".baseHeight", Integer.toString((int) Math.round(values[5])));
            }
        }
        for (Map.Entry<String, String> entry : configTexts.entrySet()) {
            properties.setProperty(CONFIG_TEXT_PREFIX + entry.getKey(), entry.getValue());
        }
        try {
            Files.createDirectories(configPath.getParent());
            try (OutputStream output = Files.newOutputStream(configPath)) {
                properties.store(output, "Teleport Animation client settings");
            }
            return true;
        }
        catch (IOException ignored) {
            return false;
        }
    }

    private static Path resolveConfigPath() {
        return FMLPaths.CONFIGDIR.get().resolve(FILE_NAME);
    }

    private static Path resolveLegacyConfigPath() {
        return FMLPaths.CONFIGDIR.get().resolve(LEGACY_FILE_NAME);
    }

    private static Path resolvePreviousConfigPath() {
        return FMLPaths.CONFIGDIR.get().resolve(PREVIOUS_FILE_NAME);
    }

    private static void migrateLegacyConfig() {
        if (Files.exists(configPath)) {
            return;
        }
        Path previousPath = resolvePreviousConfigPath();
        if (Files.exists(previousPath)) {
            try {
                Files.createDirectories(configPath.getParent());
                Files.copy(previousPath, configPath);
                return;
            }
            catch (IOException ignored) {
                configPath = previousPath;
                return;
            }
        }
        Path legacyPath = resolveLegacyConfigPath();
        if (!Files.exists(legacyPath)) {
            return;
        }
        try {
            Files.createDirectories(configPath.getParent());
            Files.copy(legacyPath, configPath);
        }
        catch (IOException ignored) {
            configPath = legacyPath;
        }
    }

    private static ZoomDimension sanitizeZoomDimension(ZoomDimension dimension) {
        return dimension == null ? ZoomDimension.OVERWORLD : dimension;
    }

    private static void writeStageHeights(Properties properties, String prefix, double[] values) {
        double[] sanitized = sanitizeStageHeights(values);
        for (int i = 0; i < sanitized.length; i++) {
            properties.setProperty(prefix + (i + 1), Integer.toString((int) sanitized[i]));
        }
    }

    private static void writeStageTicks(Properties properties, String prefix, int[] values) {
        int[] sanitized = sanitizeStageTicks(values);
        for (int i = 0; i < sanitized.length; i++) {
            properties.setProperty(prefix + (i + 1), Integer.toString(sanitized[i]));
        }
    }

    static {
        effectEnabled = true;
        playerFreezeEnabled = true;
        crossDimensionTravelEnabled = false;
        zoomHeightsLinked = true;
        zoomOutStageHeights = DEFAULT_STAGE_HEIGHTS.clone();
        zoomInStageHeights = DEFAULT_STAGE_HEIGHTS.clone();
        netherZoomHeightsLinked = true;
        netherZoomOutStageHeights = DEFAULT_STAGE_HEIGHTS.clone();
        netherZoomInStageHeights = DEFAULT_STAGE_HEIGHTS.clone();
        endZoomHeightsLinked = true;
        endZoomOutStageHeights = DEFAULT_STAGE_HEIGHTS.clone();
        endZoomInStageHeights = DEFAULT_STAGE_HEIGHTS.clone();
        zoomOutStageTicks = DEFAULT_STAGE_TICKS.clone();
        zoomInStageTicks = DEFAULT_STAGE_TICKS.clone();
        zoomStageGlideHeight = 0.5;
        zoomStageGlideTicks = 13;
        bodyCameraHeight = 6.0;
        bodyGlideHeight = 0.5;
        bodyGlideTicks = 10;
        localPlayerHideTicks = 2;
        customSoundsEnabled = false;
        minecraftSoundVolume = 1.0;
        customSoundVolume = 0.3;
        warpPlateTransitionsEnabled = true;
        externalTeleportTransitionsEnabled = true;
        fallbackChunkFadeEnabled = false;
        configLayoutEditorButtonVisible = false;
        configLayoutDebugEnabled = false;
        configLayoutAspectLocked = true;
        configLayoutGridEnabled = true;
        configLayoutSnapEnabled = true;
        configLayoutCustom = false;
        configLayoutX = 0.0;
        configLayoutY = 0.0;
        configLayoutWidth = 0.0;
        configLayoutHeight = 0.0;
        configLayoutBaseWidth = 0;
        configLayoutBaseHeight = 0;
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
}
