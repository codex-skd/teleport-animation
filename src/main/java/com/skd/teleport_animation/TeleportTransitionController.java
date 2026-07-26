/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.logging.LogUtils
 *  net.minecraft.client.Camera
 *  net.minecraft.client.CameraType
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.screens.ReceivingLevelScreen
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.client.resources.sounds.AbstractTickableSoundInstance
 *  net.minecraft.client.resources.sounds.SimpleSoundInstance
 *  net.minecraft.client.resources.sounds.SoundInstance
 *  net.minecraft.client.resources.sounds.SoundInstance$Attenuation
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Position
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.neoforged.fml.ModList
 *  org.slf4j.Logger
 */
package com.skd.teleport_animation;

import com.mojang.logging.LogUtils;
import com.skd.teleport_animation.BobbyCompat;
import com.skd.teleport_animation.DimensionIds;
import com.skd.teleport_animation.DistantHorizonsCompat;
import com.skd.teleport_animation.TeleportClient;
import com.skd.teleport_animation.TeleportConfig;
import com.skd.teleport_animation.IrisCompat;
import com.skd.teleport_animation.SodiumCompat;
import com.skd.teleport_animation.TeleportSounds;
import com.skd.teleport_animation.VoxyCompat;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.Camera;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.ModList;
import org.slf4j.Logger;

public final class TeleportTransitionController {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int EXIT_BODY_TICKS = 12;
    private static final int PRE_TRAVEL_WAIT_TICKS = 10;
    private static final int DEFAULT_TRAVEL_TICKS = 40;
    private static final int MIN_TRAVEL_TICKS = 20;
    private static final int MAX_TRAVEL_TICKS = 60;
    private static final int TRAVEL_EASE_EDGE_TICKS = 10;
    private static final double TRAVEL_BLOCKS_PER_TICK = 30.0;
    private static final int PRE_PUSH_WAIT_TICKS = 10;
    private static final int CROSS_DIMENSION_WAIT_TICKS = 20;
    private static final int CROSS_DIMENSION_PLANNED_TARGET_FALLBACK_TICKS = 24;
    private static final int ENTER_BODY_TICKS = 12;
    private static final int POST_RELEASE_EFFECT_TICKS = 8;
    private static final int CAMERA_RELEASE_HOLD_TICKS = 2;
    private static final int POST_RELEASE_CAMERA_OVERRIDE_MIN_FRAMES = 8;
    private static final int POST_RELEASE_CAMERA_OVERRIDE_MAX_FRAMES = 90;
    private static final int POST_RELEASE_CAMERA_OVERRIDE_SETTLED_FRAMES = 5;
    private static final double POST_RELEASE_CAMERA_SETTLED_DISTANCE_SQ = 0.01;
    private static final float POST_RELEASE_CAMERA_SETTLED_ROTATION_DEGREES = 1.5f;
    private static final int POST_RELEASE_CAMERA_OVERRIDE_MAX_TICKS = 30;
    private static final int POST_RELEASE_LEAWIND_BLEND_TICKS = 16;
    private static final int HUD_FADE_TICKS = 8;
    private static final int CUSTOM_TRAVEL_SOUND_FADE_TICKS = 30;
    private static final int ARRIVAL_TERRAIN_REFRESH_TICKS = 40;
    private static final double UNRESOLVED_SURFACE_SEA_LEVEL_PADDING = 16.0;
    private static final int NORMAL_CHUNK_HANDOFF_MIN_READY_TICKS = 8;
    private static final int NORMAL_CHUNK_HANDOFF_MAX_DELAY_TICKS = 80;
    private static final int NORMAL_CHUNK_HANDOFF_MIN_READY_RADIUS = 2;
    private static final int NORMAL_CHUNK_HANDOFF_MAX_READY_RADIUS = 4;
    private static final int NORMAL_CHUNK_HANDOFF_RETAIN_RADIUS_PADDING = 2;
    private static final float NORMAL_CHUNK_HANDOFF_READY_RATIO = 0.82f;
    private static final int FALLBACK_TERRAIN_SWEEP_TICKS = 10;
    private static final int FALLBACK_TERRAIN_FADE_OUT_DELAY_TICKS = 4;
    private static final float FALLBACK_TERRAIN_SWEEP_BAND = 0.22f;
    private static final float SHADER_SCREEN_MASK_SWEEP_BAND = 0.22f;
    private static final int TERRAIN_VISIBILITY_REFRESH_MIN_SECTION_STEP = 4;
    private static final boolean SHADER_SCREEN_MASK_ENABLED = false;
    private static final boolean VOXY_LOADED = ModList.get().isLoaded("voxy");
    private static final boolean DISTANT_HORIZONS_LOADED = ModList.get().isLoaded("distanthorizons");
    private static final boolean BOBBY_LOADED = ModList.get().isLoaded("bobby");
    private static final boolean LEAWIND_THIRD_PERSON_LOADED = ModList.get().isLoaded("leawind_third_person");
    private static final float TRAVEL_EASE_CONTROL_X1 = 0.55f;
    private static final float TRAVEL_EASE_CONTROL_X2 = 0.55f;
    private static final double ZOOM_LOOK_SHAKE_PITCH_AMPLITUDE = 1.05;
    private static final double ZOOM_LOOK_SHAKE_YAW_AMPLITUDE = 0.15;
    private static final float BODY_LOOK_SPEED_MULTIPLIER = 1.5f;
    private static final float TOP_DOWN_PITCH = 90.0f;
    private static final float MINECRAFT_STEP_SOUND_VOLUME = 0.86f;
    private static final float MINECRAFT_STEP_SOUND_PITCH = 1.0f;
    private static final float MINECRAFT_TRAVEL_SOUND_VOLUME = 0.48f;
    private static final float MINECRAFT_TRAVEL_SOUND_PITCH = 1.0f;
    private static final float MINECRAFT_BODY_SOUND_VOLUME = 1.0f;
    private static final float MINECRAFT_BODY_SOUND_PITCH = 0.8f;
    private static final int MINECRAFT_BODY_SOUND_LAYERS = 3;
    private static final int TRANSITION_SOURCE_CLIENT = 0;
    private static Runnable pendingAction;
    private static Vec3 startFeet;
    private static Vec3 startEye;
    private static Vec3 startCameraEyeOffset;
    private static Vec3 plannedTargetFeet;
    private static boolean plannedTargetFeetStable;
    private static Vec3 actualTargetFeet;
    private static Vec3 arrivalCameraFeet;
    private static Vec3 lastObservedPlayerFeet;
    private static Vec3 enterBodyTargetEye;
    private static String plannedTargetDimensionId;
    private static ResourceKey<Level> startDimension;
    private static CameraType previousCameraType;
    private static boolean previousHudHidden;
    
    private static float startYaw;
    private static float startPitch;
    private static float startPlayerYaw;
    private static int exitBodyTicks;
    private static int enterBodyTicks;
    private static int ticks;
    private static float frameTick;
    private static int travelTicks;
    private static int totalTicks;
    private static long lastTickTimeMs;
    private static boolean commandSent;
    private static boolean cameraReleasePrepared;
    private static boolean cameraReleased;
    private static boolean cameraTypeRestoredForEnter;
    private static boolean hudSuppressed;
    private static boolean previousHardCutCullingActive;
    private static int transitionSource;
    private static boolean skipTravel;
    private static int skipTravelArrivalDelayTicks;
    private static int skipTravelTargetDimensionTicks;
    private static int arrivalTerrainRefreshTicks;
    private static boolean normalChunkHandoffEnabled;
    private static boolean normalChunkHandoffReady;
    private static int normalChunkHandoffDelayTicks;
    private static int normalChunkHandoffArrivalTick;
    private static float enterBodyTargetYaw;
    private static float enterBodyTargetPitch;
    private static int lastVisibilitySectionX;
    private static int lastVisibilitySectionY;
    private static int lastVisibilitySectionZ;
    private static final Set<Long> retainedDepartingChunks;
    private static FadingTravelSound activeTravelSound;
    private static boolean travelSoundStarted;
    private static boolean travelSoundFaded;
    private static boolean leawindCameraResetWarningLogged;
    private static double startSurfaceY;
    private static double arrivalSurfaceY;
    private static double startBodyCameraHeight;
    private static double startBodyGlideHeight;
    private static double arrivalBodyCameraHeight;
    private static double arrivalBodyGlideHeight;
    private static boolean arrivalBodyCameraHeightsLocked;
    private static Vec3 currentCameraPos;
    private static CameraFrame postReleaseCameraFrame;
    private static CameraFrame postReleaseCameraStartFrame;
    private static CameraFrame postReleaseCameraEndFrame;
    private static int postReleaseCameraBlendStartTick;
    private static int postReleaseCameraBlendTicks;
    private static int postReleaseCameraOverrideFrames;
    private static int postReleaseCameraOverrideStableFrames;
    private static boolean needsStateReset = true;

    private TeleportTransitionController() {
    }

    static void start(Minecraft client, Vec3 plannedTarget, Runnable action) {
        TeleportTransitionController.start(client, plannedTarget, null, action, true);
    }

    static void start(Minecraft client, Vec3 plannedTarget, Runnable action, boolean closeOpenScreen) {
        TeleportTransitionController.start(client, plannedTarget, null, action, closeOpenScreen);
    }

    static void start(Minecraft client, Vec3 plannedTarget, String targetDimensionId, Runnable action) {
        TeleportTransitionController.start(client, plannedTarget, targetDimensionId, action, true);
    }

    static void start(Minecraft client, Vec3 plannedTarget, String targetDimensionId, Runnable action, boolean closeOpenScreen) {
        TeleportTransitionController.start(client, plannedTarget, targetDimensionId, action, closeOpenScreen, true, 0);
    }

    static void start(Minecraft client, Vec3 plannedTarget, String targetDimensionId, Runnable action, int source) {
        TeleportTransitionController.start(client, plannedTarget, targetDimensionId, action, true, true, source);
    }

    private static void start(Minecraft client, Vec3 plannedTarget, String targetDimensionId, Runnable action, boolean closeOpenScreen, boolean stablePlannedTarget, int source) {
        LocalPlayer player = client.player;
        if (player == null) {
            return;
        }
        TeleportTransitionController.stopCustomTravelSound(client);
        pendingAction = action;
        startFeet = TeleportTransitionController.getFeetPos(player);
        Vec3 playerEye = player.getEyePosition();
        startEye = TeleportTransitionController.getInitialTransitionCameraPosition(client, playerEye);
        startCameraEyeOffset = startEye.subtract(playerEye);
        plannedTargetFeet = plannedTarget;
        plannedTargetFeetStable = stablePlannedTarget;
        actualTargetFeet = null;
        arrivalCameraFeet = stablePlannedTarget ? plannedTarget : null;
        lastObservedPlayerFeet = startFeet;
        enterBodyTargetEye = null;
        startDimension = client.level == null ? Level.OVERWORLD : client.level.dimension();
        plannedTargetDimensionId = TeleportTransitionController.normalizeTargetDimensionId(targetDimensionId);
        previousCameraType = client.options.getCameraType();
        previousHudHidden = client.options.hideGui;
        
        startPlayerYaw = player.getYRot();
        startYaw = TeleportTransitionController.getInitialTransitionYaw(client, player);
        startPitch = TeleportTransitionController.getInitialTransitionPitch(client, player);
        ticks = 0;
        frameTick = 0.0f;
        skipTravel = TeleportTransitionController.shouldSkipTravelForTargetDimension(plannedTargetDimensionId);
        skipTravelArrivalDelayTicks = 0;
        skipTravelTargetDimensionTicks = 0;
        arrivalTerrainRefreshTicks = 0;
        normalChunkHandoffDelayTicks = 0;
        normalChunkHandoffReady = false;
        normalChunkHandoffArrivalTick = Integer.MIN_VALUE;
        retainedDepartingChunks.clear();
        normalChunkHandoffEnabled = TeleportTransitionController.shouldStartNormalChunkHandoff();
        travelSoundStarted = false;
        travelSoundFaded = false;
        enterBodyTargetYaw = 0.0f;
        enterBodyTargetPitch = 0.0f;
        travelTicks = skipTravel ? 0 : TeleportTransitionController.calculateTravelTicks(startFeet, plannedTargetFeet);
        startSurfaceY = TeleportTransitionController.getSkyFacingSurfaceY(client, startFeet, TeleportTransitionController.startFeet.y);
        arrivalSurfaceY = skipTravel ? Double.NaN : TeleportTransitionController.resolveSkyFacingSurfaceY(client, plannedTargetFeet, plannedTargetFeet == null ? TeleportTransitionController.startFeet.y : TeleportTransitionController.plannedTargetFeet.y);
        TeleportTransitionController.updateStartBodyCameraHeights(client);
        TeleportTransitionController.updateBodyTransitionTicks(client);
        TeleportTransitionController.resetArrivalBodyCameraHeights();
        currentCameraPos = startEye;
        if (!skipTravel && plannedTargetFeet != null) {
            TeleportTransitionController.updateArrivalBodyCameraHeights(client, plannedTargetFeet);
        }
        totalTicks = TeleportTransitionController.getFixedTotalTicks() + travelTicks;
        lastTickTimeMs = System.currentTimeMillis();
        LOGGER.info("TA start: totalTicks={} travelTicks={} fixedTicks={} skipTravel={}", totalTicks, travelTicks, TeleportTransitionController.getFixedTotalTicks(), skipTravel);
        transitionSource = source;
        if (skipTravel) {
            LOGGER.info("TA cross-dimension transition start: source={} startDim={} targetDim={} startFeet={} plannedTargetFeet={}", new Object[]{transitionSource, DimensionIds.fromResourceKey(startDimension), plannedTargetDimensionId, startFeet, plannedTargetFeet});
        }
        commandSent = false;
        cameraReleasePrepared = false;
        cameraReleased = false;
        cameraTypeRestoredForEnter = false;
        hudSuppressed = false;
        previousHardCutCullingActive = false;
        TeleportTransitionController.resetVisibilityRefreshState();
        if (client.screen != null) {
            if (closeOpenScreen) {
                client.setScreen(null);
            } else {
                client.screen = null;
            }
        }
        client.options.setCameraType(CameraType.THIRD_PERSON_BACK);
        
        client.levelRenderer.allChanged();
        SodiumCompat.beginTransition(client, TeleportTransitionController.isFallbackTerrainMode());
    }

    private static void advanceTickTime() {
        long now = System.currentTimeMillis();
        long elapsed = now - lastTickTimeMs;
        lastTickTimeMs = now;
        float delta = Math.min((float)(elapsed / 50.0), 10.0f);
        frameTick += delta;
        ticks = (int)frameTick;
    }

    static void tick(Minecraft client) {
        if (needsStateReset) {
            needsStateReset = false;
            TeleportTransitionController.resetState(client);
        }
        if (!TeleportTransitionController.isRunning()) {
            return;
        }
        if (client.player == null || client.level == null || client.getConnection() == null) {
            TeleportTransitionController.clear(client);
            return;
        }
        if (client.player.isDeadOrDying()) {
            TeleportTransitionController.clear(client);
            return;
        }
        TeleportTransitionController.advanceTickTime();
        TeleportTransitionController.updateHardCutTerrainState(client);
        TeleportTransitionController.updateHudVisibility(client);
        if (!commandSent && ticks >= TeleportTransitionController.getCommandSendTick()) {
            if (skipTravel) {
                LOGGER.info("TA cross-dimension command send: source={} tick={} travelStart={} pullStart={} pullEnd={}", new Object[]{transitionSource, ticks, TeleportTransitionController.getTravelStartTick(), TeleportTransitionController.getPullStartTick(), TeleportTransitionController.getPullEndTick()});
            }
            commandSent = true;
            TeleportTransitionController.runPendingAction();
        }
        Vec3 playerFeet = TeleportTransitionController.getFeetPos(client.player);
        TeleportTransitionController.updateSkipTravelTargetDimensionTicks(client);
        if (commandSent && ticks > TeleportTransitionController.getPullEndTick() + 2 && actualTargetFeet == null && TeleportTransitionController.hasArrivedAtTeleportTarget(client, playerFeet)) {
            TeleportTransitionController.recordActualTargetFeet(playerFeet);
            TeleportTransitionController.beginArrivalTerrainRefresh(client);
            if (skipTravel) {
                skipTravelArrivalDelayTicks = Math.max(0, ticks - TeleportTransitionController.getTravelEndTick());
                totalTicks += skipTravelArrivalDelayTicks;
            }
        }
        TeleportTransitionController.closeSkipTravelLoadingTerrainScreen(client);
        TeleportTransitionController.updateNormalChunkHandoff(client);
        TeleportTransitionController.restoreCameraTypeForEnterBody(client);
        if (ticks == 1) {
            TeleportTransitionController.playBodyTransitionSound(client, false);
        }
        if (ticks == TeleportTransitionController.getEnterStartTick()) {
            TeleportTransitionController.playBodyTransitionSound(client, true);
        }
        TeleportTransitionController.playStepSound(client, ticks);
        if (!skipTravel && !travelSoundStarted && ticks >= TeleportTransitionController.getTravelStartTick()) {
            travelSoundStarted = true;
            TeleportTransitionController.playTravelSound(client);
        }
        if (!skipTravel && travelSoundStarted && !travelSoundFaded && ticks >= TeleportTransitionController.getTravelEndTick()) {
            travelSoundFaded = true;
            TeleportTransitionController.fadeCustomTravelSound();
        }
        TeleportTransitionController.updateArrivalTerrainRefresh(client);
        lastObservedPlayerFeet = playerFeet;
        if (!cameraReleasePrepared && ticks > totalTicks) {
            if (!commandSent) {
                commandSent = true;
                TeleportTransitionController.runPendingAction();
            }
            TeleportTransitionController.syncCameraStateBeforeRelease(client);
            TeleportTransitionController.restoreCameraType(client);
            TeleportTransitionController.resetLeawindThirdPersonCamera(client);
            TeleportTransitionController.syncPlayerInterpolationState(client.player);
            cameraReleasePrepared = true;
        }
        if (cameraReleasePrepared && !cameraReleased && ticks > totalTicks + TeleportTransitionController.getCameraReleaseHoldTicks()) {
            TeleportTransitionController.syncPlayerInterpolationState(client.player);
            TeleportTransitionController.resetLeawindThirdPersonCamera(client);
            TeleportTransitionController.preparePostReleaseCameraOverride(client);
            cameraReleased = true;
            
            SodiumCompat.endTransition();
        }
        if (!(ticks < totalTicks + 8 || TeleportTransitionController.hasPostReleaseCameraOverride() && ticks < totalTicks + 30)) {
            TeleportTransitionController.clear(client);
        }
    }

    static boolean isRunning() {
        return pendingAction != null;
    }

    static float getProgress() {
        if (!TeleportTransitionController.isRunning()) {
            return 0.0f;
        }
        return Mth.clamp((float)((float)ticks / (float)totalTicks), (float)0.0f, (float)1.0f);
    }

    public static int getTicks() {
        return ticks;
    }

    public static boolean shouldBlockPlayerInput() {
        return TeleportConfig.isPlayerFreezeEnabled() && TeleportTransitionController.isRunning() && !cameraReleased;
    }

    public static boolean shouldBlockGameplayInput() {
        if (!TeleportTransitionController.shouldBlockPlayerInput()) {
            return false;
        }
        Minecraft client = Minecraft.getInstance();
        return client == null || client.screen == null;
    }

    public static boolean shouldSuppressGameMenu() {
        return false;
    }

    public static boolean shouldHideLocalPlayerModel() {
        boolean enteringPlayer;
        if (!TeleportTransitionController.isRunning() || cameraReleased) {
            return false;
        }
        int hideTicks = TeleportConfig.getLocalPlayerHideTicks();
        if (hideTicks <= 0) {
            return false;
        }
        boolean bl = enteringPlayer = ticks >= TeleportTransitionController.getEnterStartTick() && ticks >= totalTicks - hideTicks;
        if (enteringPlayer) {
            return true;
        }
        return !TeleportTransitionController.shouldRespectPreviousCameraPosition() && ticks <= hideTicks;
    }

    public static boolean shouldSuppressScreenEffects() {
        return TeleportTransitionController.isRunning() && !cameraReleased;
    }

    public static Vec3 stabilizeCameraInsideBlock(Vec3 pos) {
        if (pos == null) {
            return null;
        }
        Minecraft client = Minecraft.getInstance();
        if (client == null || client.level == null || !TeleportTransitionController.isCameraViewBlocked(client, pos)) {
            return pos;
        }
        BlockPos blockPos = BlockPos.containing((Position)pos);
        return new Vec3((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5);
    }

    private static boolean isCameraViewBlocked(Minecraft client, Vec3 pos) {
        if (client == null || client.level == null || pos == null) {
            return false;
        }
        BlockPos blockPos = BlockPos.containing((Position)pos);
        BlockState state = client.level.getBlockState(blockPos);
        return state.isViewBlocking((BlockGetter)client.level, blockPos);
    }

    private static double getSkyFacingSurfaceY(Minecraft client, Vec3 feet, double fallbackY) {
        double resolvedSurfaceY = TeleportTransitionController.resolveSkyFacingSurfaceY(client, feet, fallbackY);
        if (!Double.isNaN(resolvedSurfaceY)) {
            return resolvedSurfaceY;
        }
        return TeleportTransitionController.getUnresolvedSkyFacingSurfaceFallbackY(client, fallbackY);
    }

    private static double resolveSkyFacingSurfaceY(Minecraft client, Vec3 feet, double fallbackY) {
        double surfaceY = TeleportTransitionController.tryGetSkyFacingSurfaceY(client, feet);
        if (Double.isNaN(surfaceY)) {
            return Double.NaN;
        }
        double safeFallback = Double.isFinite(fallbackY) ? fallbackY : 0.0;
        return Math.max(surfaceY, safeFallback);
    }

    private static double tryGetSkyFacingSurfaceY(Minecraft client, Vec3 feet) {
        int z;
        if (client == null || client.level == null || feet == null) {
            return Double.NaN;
        }
        int x = Mth.floor((double)feet.x);
        if (!client.level.hasChunk(x >> 4, (z = Mth.floor((double)feet.z)) >> 4)) {
            return Double.NaN;
        }
        int surfaceY = client.level.getHeight(Heightmap.Types.WORLD_SURFACE, x, z);
        if (surfaceY <= client.level.getMinY()) {
            return Double.NaN;
        }
        return surfaceY;
    }

    private static double getUnresolvedSkyFacingSurfaceFallbackY(Minecraft client, double fallbackY) {
        double safeFallback;
        double d = safeFallback = Double.isFinite(fallbackY) ? fallbackY : 0.0;
        if (TeleportTransitionController.isOpenSkyDimension(client)) {
            safeFallback = Math.max(safeFallback, (double)client.level.getSeaLevel() + 16.0);
            if (!Double.isNaN(startSurfaceY) && TeleportTransitionController.isSameDimensionTarget()) {
                safeFallback = Math.max(safeFallback, startSurfaceY);
            }
        }
        return safeFallback;
    }

    private static boolean isOpenSkyDimension(Minecraft client) {
        return client != null && client.level != null && client.level.dimensionType().hasSkyLight() && !client.level.dimensionType().hasCeiling();
    }

    private static double getStartSurfaceY() {
        return Double.isNaN(startSurfaceY) ? TeleportTransitionController.startFeet.y : startSurfaceY;
    }

    private static double getArrivalSurfaceY() {
        double resolvedSurfaceY;
        Vec3 feet = TeleportTransitionController.getArrivalCameraFeet();
        Minecraft client = Minecraft.getInstance();
        if (feet != null && !Double.isNaN(resolvedSurfaceY = TeleportTransitionController.resolveSkyFacingSurfaceY(client, feet, feet.y))) {
            arrivalSurfaceY = resolvedSurfaceY;
        }
        if (!Double.isNaN(arrivalSurfaceY)) {
            return arrivalSurfaceY;
        }
        if (feet != null) {
            return TeleportTransitionController.getUnresolvedSkyFacingSurfaceFallbackY(client, feet.y);
        }
        return TeleportTransitionController.getBestTargetFeet().y;
    }

    private static Vec3 topDownPos(Vec3 feet, double surfaceY, double altitude) {
        return new Vec3(feet.x, surfaceY + altitude, feet.z);
    }

    private static void updateStartBodyCameraHeights(Minecraft client) {
        BodyCameraHeights heights = TeleportTransitionController.resolveBodyCameraHeights(client, startFeet);
        startBodyCameraHeight = heights.cameraHeight();
        startBodyGlideHeight = heights.glideHeight();
    }

    private static void updateArrivalBodyCameraHeights(Minecraft client, Vec3 feet) {
        BodyCameraHeights heights = TeleportTransitionController.resolveBodyCameraHeights(client, feet);
        TeleportTransitionController.setArrivalBodyCameraHeights(heights);
        arrivalBodyCameraHeightsLocked = false;
    }

    private static void lockArrivalBodyCameraHeightsForEnter(Minecraft client) {
        if (arrivalBodyCameraHeightsLocked) {
            return;
        }
        TeleportTransitionController.setArrivalBodyCameraHeights(TeleportTransitionController.resolveBodyCameraHeights(client, TeleportTransitionController.getArrivalCameraFeet()));
        arrivalBodyCameraHeightsLocked = true;
    }

    private static void setArrivalBodyCameraHeights(BodyCameraHeights heights) {
        arrivalBodyCameraHeight = heights.cameraHeight();
        arrivalBodyGlideHeight = heights.glideHeight();
    }

    private static void resetArrivalBodyCameraHeights() {
        arrivalBodyCameraHeight = Double.NaN;
        arrivalBodyGlideHeight = Double.NaN;
        arrivalBodyCameraHeightsLocked = false;
    }

    private static double getStartBodyCameraHeight() {
        if (Double.isNaN(startBodyCameraHeight)) {
            TeleportTransitionController.updateStartBodyCameraHeights(Minecraft.getInstance());
        }
        return Double.isNaN(startBodyCameraHeight) ? TeleportTransitionController.getBodyCameraHeight() : startBodyCameraHeight;
    }

    private static double getStartBodyGlideHeight() {
        if (Double.isNaN(startBodyGlideHeight)) {
            TeleportTransitionController.updateStartBodyCameraHeights(Minecraft.getInstance());
        }
        return Double.isNaN(startBodyGlideHeight) ? TeleportTransitionController.getBodyGlideHeight() : startBodyGlideHeight;
    }

    private static double getArrivalBodyCameraHeight() {
        if (Double.isNaN(arrivalBodyCameraHeight)) {
            TeleportTransitionController.updateArrivalBodyCameraHeights(Minecraft.getInstance(), TeleportTransitionController.getArrivalCameraFeet());
        }
        return Double.isNaN(arrivalBodyCameraHeight) ? TeleportTransitionController.getBodyCameraHeight() : arrivalBodyCameraHeight;
    }

    private static double getArrivalBodyGlideHeight() {
        if (Double.isNaN(arrivalBodyGlideHeight)) {
            TeleportTransitionController.updateArrivalBodyCameraHeights(Minecraft.getInstance(), TeleportTransitionController.getArrivalCameraFeet());
        }
        return Double.isNaN(arrivalBodyGlideHeight) ? TeleportTransitionController.getBodyGlideHeight() : arrivalBodyGlideHeight;
    }

    private static BodyCameraHeights resolveBodyCameraHeights(Minecraft client, Vec3 feet) {
        double requestedCameraHeight = TeleportTransitionController.getBodyCameraHeight();
        double requestedGlideHeight = TeleportTransitionController.getBodyGlideHeight();
        double requestedTopHeight = requestedCameraHeight + requestedGlideHeight;
        double maxClearHeight = TeleportTransitionController.getMaxClearBodyCameraHeight(client, feet, requestedTopHeight);
        double safeMaxClearHeight = Math.max(0.0, maxClearHeight);
        double minimumCameraHeight = Math.min(Math.min(TeleportTransitionController.getCameraEyeHeight(client), requestedCameraHeight), safeMaxClearHeight);
        double cameraHeight = Math.min(requestedCameraHeight, safeMaxClearHeight - requestedGlideHeight);
        cameraHeight = Mth.clamp((double)cameraHeight, (double)minimumCameraHeight, (double)requestedCameraHeight);
        double glideHeight = Math.min(requestedGlideHeight, Math.max(0.0, safeMaxClearHeight - cameraHeight));
        return new BodyCameraHeights(cameraHeight, glideHeight);
    }

    private static double getMaxClearBodyCameraHeight(Minecraft client, Vec3 feet, double requestedTopHeight) {
        if (client == null || client.level == null || feet == null) {
            return requestedTopHeight;
        }
        double minHeight = Math.min(TeleportTransitionController.getCameraEyeHeight(client), requestedTopHeight);
        double minY = feet.y + minHeight;
        double maxY = feet.y + requestedTopHeight;
        double lastClearY = minY;
        for (double y = minY; y <= maxY; y += 0.5) {
            Vec3 candidate = new Vec3(feet.x, y, feet.z);
            if (TeleportTransitionController.isCameraViewBlocked(client, candidate)) {
                return Math.max(0.0, lastClearY - feet.y);
            }
            lastClearY = y;
        }
        Vec3 top = new Vec3(feet.x, maxY, feet.z);
        if (!TeleportTransitionController.isCameraViewBlocked(client, top)) {
            return requestedTopHeight;
        }
        return Math.max(0.0, lastClearY - feet.y);
    }

    private static double getCameraEyeHeight(Minecraft client) {
        return client == null || client.player == null ? 1.62 : (double)client.player.getEyeHeight();
    }

    public static CameraFrame getCameraFrame(float tickProgress) {
        int exitTicks;
        if (!TeleportTransitionController.isRunning()) {
            return null;
        }
        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client.player;
        if (player == null) {
            return null;
        }
        if (cameraReleased) {
            return TeleportTransitionController.getPostReleaseCameraFrame(tickProgress);
        }
        float frameTick = Math.min(TeleportTransitionController.frameTick, (float)totalTicks);
        if (frameTick <= (float)(exitTicks = TeleportTransitionController.getExitBodyTicks())) {
            return TeleportTransitionController.exitBodyFrame(frameTick / (float)exitTicks);
        }
        if (frameTick <= (float)TeleportTransitionController.getPullStartTick()) {
            return TeleportTransitionController.startBodyHoldFrame(frameTick);
        }
        int pullMotionEnd = TeleportTransitionController.getPullMotionEndTick();
        if (frameTick <= (float)pullMotionEnd) {
            return TeleportTransitionController.pullFrame((frameTick - (float)TeleportTransitionController.getPullStartTick()) / Math.max(1.0f, (float)(pullMotionEnd - TeleportTransitionController.getPullStartTick())), frameTick);
        }
        if (frameTick <= (float)TeleportTransitionController.getTravelStartTick()) {
            return TeleportTransitionController.topDownFrame(startFeet, TeleportTransitionController.getStartSurfaceY(), startYaw, frameTick, TeleportTransitionController.getStartTravelAltitude());
        }
        int travelStart = TeleportTransitionController.getTravelStartTick();
        if (!skipTravel && frameTick <= (float)(travelStart + travelTicks)) {
            return TeleportTransitionController.travelFrame((frameTick - (float)travelStart) / (float)travelTicks, frameTick);
        }
        if (skipTravel && actualTargetFeet == null && !TeleportTransitionController.isInPlannedTargetDimension(client)) {
            return TeleportTransitionController.topDownFrame(startFeet, TeleportTransitionController.getStartSurfaceY(), startYaw, frameTick, TeleportTransitionController.getStartTravelAltitude());
        }
        int pushMotionStart = TeleportTransitionController.getPushMotionStartTick();
        if (frameTick <= (float)pushMotionStart) {
            return TeleportTransitionController.prePushTopDownFrame(TeleportTransitionController.getArrivalCameraFeet(), startYaw, frameTick);
        }
        if (frameTick < (float)TeleportTransitionController.getEnterHoldStartTick()) {
            return TeleportTransitionController.pushFrame(player, (frameTick - (float)pushMotionStart) / Math.max(1.0f, (float)(TeleportTransitionController.getEnterHoldStartTick() - pushMotionStart)), frameTick);
        }
        if (frameTick <= (float)TeleportTransitionController.getEnterStartTick()) {
            return TeleportTransitionController.enterBodyHoldFrame(frameTick);
        }
        return TeleportTransitionController.enterBodyFrame(player, (frameTick - (float)TeleportTransitionController.getEnterStartTick()) / (float)TeleportTransitionController.getEnterBodyTicks());
    }

    public static boolean shouldPreemptLeawindThirdPersonCamera() {
        return LEAWIND_THIRD_PERSON_LOADED && TeleportTransitionController.isRunning() && !cameraReleased;
    }

    public static boolean shouldApplyPostReleaseCameraOverrideAfterLeawind() {
        return LEAWIND_THIRD_PERSON_LOADED && TeleportTransitionController.isRunning() && cameraReleased && TeleportTransitionController.hasPostReleaseCameraOverride();
    }

    public static boolean shouldPreservePostReleaseCameraRotation() {
        return TeleportTransitionController.isRunning() && cameraReleased && TeleportTransitionController.hasPostReleaseCameraOverride() && TeleportTransitionController.shouldRespectPreviousCameraPosition() && !TeleportTransitionController.hasPostReleaseLeawindBlend();
    }

    private static int getCameraReleaseHoldTicks() {
        return TeleportTransitionController.shouldRespectPreviousCameraPosition() ? 2 : 0;
    }

    private static boolean hasPostReleaseCameraOverride() {
        return cameraReleased && postReleaseCameraFrame != null && postReleaseCameraOverrideFrames > 0;
    }

    private static boolean hasPostReleaseLeawindBlend() {
        return postReleaseCameraStartFrame != null && postReleaseCameraEndFrame != null && postReleaseCameraBlendTicks > 0;
    }

    private static boolean isPostReleaseLeawindBlendActive() {
        return TeleportTransitionController.hasPostReleaseLeawindBlend() && ticks <= postReleaseCameraBlendStartTick + postReleaseCameraBlendTicks;
    }

    private static int getPostReleaseCameraOverrideMinFrames() {
        if (postReleaseCameraBlendTicks <= 0) {
            return 8;
        }
        return Math.max(8, postReleaseCameraBlendTicks + 5);
    }

    private static CameraFrame getPostReleaseCameraFrame(float tickProgress) {
        if (!TeleportTransitionController.hasPostReleaseCameraOverride()) {
            return null;
        }
        if (postReleaseCameraStartFrame == null || postReleaseCameraEndFrame == null || postReleaseCameraBlendTicks <= 0) {
            return postReleaseCameraFrame;
        }
        float frameTick = (float)ticks + tickProgress;
        float progress = Mth.clamp((float)((frameTick - (float)postReleaseCameraBlendStartTick) / Math.max(1.0f, (float)postReleaseCameraBlendTicks)), (float)0.0f, (float)1.0f);
        float verticalProgress = TeleportTransitionController.smoothStep(progress);
        float horizontalProgress = TeleportTransitionController.perspectiveHorizontalProgress(progress);
        Vec3 pos = TeleportTransitionController.lerpPerspectiveCameraPosition(postReleaseCameraStartFrame.pos(), postReleaseCameraEndFrame.pos(), verticalProgress, horizontalProgress);
        float yaw = TeleportTransitionController.lerpDegrees(postReleaseCameraStartFrame.yaw(), postReleaseCameraEndFrame.yaw(), horizontalProgress);
        float pitch = TeleportTransitionController.lerpFloat(postReleaseCameraStartFrame.pitch(), postReleaseCameraEndFrame.pitch(), horizontalProgress);
        return new CameraFrame(pos, yaw, pitch);
    }

    private static boolean shouldSampleExternalPerspectiveCamera() {
        return TeleportTransitionController.shouldRespectPreviousCameraPosition() && cameraTypeRestoredForEnter && ticks >= TeleportTransitionController.getEnterStartTick();
    }

    private static void preparePostReleaseCameraOverride(Minecraft client) {
        CameraFrame resolvedLeawindFrame;
        postReleaseCameraFrame = null;
        postReleaseCameraStartFrame = null;
        postReleaseCameraEndFrame = null;
        postReleaseCameraBlendStartTick = 0;
        postReleaseCameraBlendTicks = 0;
        postReleaseCameraOverrideFrames = 0;
        postReleaseCameraOverrideStableFrames = 0;
        if (!TeleportTransitionController.shouldRespectPreviousCameraPosition() || client == null || client.player == null) {
            return;
        }
        if (LEAWIND_THIRD_PERSON_LOADED && client.options != null && client.options.getCameraType() != CameraType.FIRST_PERSON && (resolvedLeawindFrame = TeleportTransitionController.resolveLeawindReleaseCameraFrame(client, client.player)) != null) {
            CameraFrame playerFrame;
            postReleaseCameraStartFrame = playerFrame = new CameraFrame(client.player.getEyePosition(), client.player.getYRot(), client.player.getXRot());
            postReleaseCameraEndFrame = resolvedLeawindFrame;
            postReleaseCameraBlendStartTick = ticks;
            postReleaseCameraBlendTicks = 16;
            postReleaseCameraFrame = resolvedLeawindFrame;
            postReleaseCameraOverrideFrames = 90;
            currentCameraPos = playerFrame.pos();
            return;
        }
        TeleportTransitionController.captureEnterBodyTarget(client.player);
        Vec3 pos = enterBodyTargetEye == null ? client.player.getEyePosition() : enterBodyTargetEye;
        postReleaseCameraFrame = new CameraFrame(pos, enterBodyTargetYaw, enterBodyTargetPitch);
        postReleaseCameraOverrideFrames = 90;
        currentCameraPos = pos;
    }

    private static void resetLeawindThirdPersonCamera(Minecraft client) {
        boolean restoreThirdPersonCamera;
        LocalPlayer player;
        if (!LEAWIND_THIRD_PERSON_LOADED) {
            return;
        }
        LocalPlayer localPlayer = player = client == null ? null : client.player;
        float releaseYaw = player == null ? (enterBodyTargetEye == null ? startYaw : enterBodyTargetYaw) : player.getYRot();
        float f = releaseYaw;
        float releasePitch = player == null ? (enterBodyTargetEye == null ? startPitch : enterBodyTargetPitch) : player.getXRot();
        boolean bl = restoreThirdPersonCamera = client != null && client.options != null && client.options.getCameraType() != CameraType.FIRST_PERSON;
        if (!(TeleportTransitionController.resetLeawindCameraAgent("com.github.leawind.thirdperson.ThirdPerson", releasePitch, releaseYaw, restoreThirdPersonCamera) || TeleportTransitionController.resetLeawindCameraAgent("com.github.leawind.thirdperson.impl.ThirdPersonImpl", releasePitch, releaseYaw, restoreThirdPersonCamera) || leawindCameraResetWarningLogged)) {
            leawindCameraResetWarningLogged = true;
            LOGGER.warn("Leawind's Third Person is loaded, but TA could not reset its CAMERA_AGENT.");
        }
    }

    private static CameraFrame resolveLeawindReleaseCameraFrame(Minecraft client, LocalPlayer player) {
        if (!LEAWIND_THIRD_PERSON_LOADED || client == null || player == null || client.options == null || client.options.getCameraType() == CameraType.FIRST_PERSON) {
            return null;
        }
        float releaseYaw = player.getYRot();
        float releasePitch = player.getXRot();
        CameraFrame frame = TeleportTransitionController.resolveLeawindReleaseCameraFrame("com.github.leawind.thirdperson.ThirdPerson", releasePitch, releaseYaw);
        if (frame != null) {
            return frame;
        }
        return TeleportTransitionController.resolveLeawindReleaseCameraFrame("com.github.leawind.thirdperson.impl.ThirdPersonImpl", releasePitch, releaseYaw);
    }

    private static CameraFrame resolveLeawindReleaseCameraFrame(String className, float pitch, float yaw) {
        try {
            Camera camera;
            if (!TeleportTransitionController.resetLeawindCameraAgent(className, pitch, yaw, true)) {
                return null;
            }
            Class<?> thirdPersonClass = Class.forName(className);
            Object cameraAgent = thirdPersonClass.getField("CAMERA_AGENT").get(null);
            if (cameraAgent == null) {
                return null;
            }
            Method updateTempCameraMethod = cameraAgent.getClass().getDeclaredMethod("updateTempCameraRotationPosition", Float.TYPE);
            updateTempCameraMethod.setAccessible(true);
            updateTempCameraMethod.invoke(cameraAgent, Float.valueOf(1.0f));
            Object rawCamera = cameraAgent.getClass().getMethod("getRawCamera", new Class[0]).invoke(cameraAgent, new Object[0]);
            if (!(rawCamera instanceof Camera) || (camera = (Camera)rawCamera).position() == null) {
                return null;
            }
            return new CameraFrame(camera.position(), camera.yRot(), camera.xRot());
        }
        catch (LinkageError | ReflectiveOperationException | RuntimeException exception) {
            return null;
        }
    }

    private static boolean resetLeawindCameraAgent(String className, float pitch, float yaw, boolean restoreThirdPersonCamera) {
        try {
            Class<?> thirdPersonClass = Class.forName(className);
            Field cameraAgentField = thirdPersonClass.getField("CAMERA_AGENT");
            Object cameraAgent = cameraAgentField.get(null);
            if (cameraAgent == null) {
                return false;
            }
            Method resetMethod = cameraAgent.getClass().getMethod("reset", new Class[0]);
            resetMethod.invoke(cameraAgent, new Object[0]);
            if (restoreThirdPersonCamera) {
                TeleportTransitionController.syncLeawindThirdPersonCameraOffset(thirdPersonClass, cameraAgent);
            }
            TeleportTransitionController.syncLeawindEntityRotation(thirdPersonClass, pitch, yaw);
            TeleportTransitionController.syncLeawindCameraRotation(cameraAgent, pitch, yaw);
            TeleportTransitionController.syncLeawindRotateCenter(cameraAgent);
            return true;
        }
        catch (LinkageError | ReflectiveOperationException exception) {
            return false;
        }
    }

    private static void syncLeawindCameraRotation(Object cameraAgent, float pitch, float yaw) throws ReflectiveOperationException {
        Class<?> vector2dClass = Class.forName("org.joml.Vector2d");
        Object rotation = vector2dClass.getConstructor(Double.TYPE, Double.TYPE).newInstance(pitch, yaw);
        Method setRotationMethod = cameraAgent.getClass().getMethod("setRotation", vector2dClass);
        setRotationMethod.invoke(cameraAgent, rotation);
    }

    private static void syncLeawindRotateCenter(Object cameraAgent) {
        try {
            Method getTargetMethod = cameraAgent.getClass().getMethod("getRotateCenterTarget", Float.TYPE);
            Object target = getTargetMethod.invoke(cameraAgent, Float.valueOf(1.0f));
            Class<?> vector3dClass = Class.forName("org.joml.Vector3d");
            Field smoothRotateCenterField = cameraAgent.getClass().getDeclaredField("smoothRotateCenter");
            smoothRotateCenterField.setAccessible(true);
            Object smoothRotateCenter = smoothRotateCenterField.get(cameraAgent);
            smoothRotateCenter.getClass().getMethod("set", vector3dClass).invoke(smoothRotateCenter, target);
            smoothRotateCenter.getClass().getMethod("setTarget", vector3dClass).invoke(smoothRotateCenter, target);
            smoothRotateCenter.getClass().getMethod("setValue", vector3dClass).invoke(smoothRotateCenter, target);
        }
        catch (LinkageError | ReflectiveOperationException | RuntimeException throwable) {
            // empty catch block
        }
    }

    private static void syncLeawindEntityRotation(Class<?> thirdPersonClass, float pitch, float yaw) {
        try {
            Object entityAgent = thirdPersonClass.getField("ENTITY_AGENT").get(null);
            if (entityAgent == null) {
                return;
            }
            Class<?> vector2dClass = Class.forName("org.joml.Vector2d");
            Object rotation = vector2dClass.getConstructor(Double.TYPE, Double.TYPE).newInstance(pitch, yaw);
            entityAgent.getClass().getMethod("reset", new Class[0]).invoke(entityAgent, new Object[0]);
            entityAgent.getClass().getMethod("setRawRotation", vector2dClass).invoke(entityAgent, rotation);
            TeleportTransitionController.syncLeawindEntitySmoothRotation(entityAgent, vector2dClass, rotation);
        }
        catch (LinkageError | ReflectiveOperationException | RuntimeException throwable) {
            // empty catch block
        }
    }

    private static void syncLeawindEntitySmoothRotation(Object entityAgent, Class<?> vector2dClass, Object rotation) throws ReflectiveOperationException {
        Field smoothRotationField = entityAgent.getClass().getDeclaredField("smoothRotation");
        smoothRotationField.setAccessible(true);
        Object smoothRotation = smoothRotationField.get(entityAgent);
        smoothRotation.getClass().getMethod("set", vector2dClass).invoke(smoothRotation, rotation);
        smoothRotation.getClass().getMethod("setTarget", vector2dClass).invoke(smoothRotation, rotation);
        smoothRotation.getClass().getMethod("setValue", vector2dClass).invoke(smoothRotation, rotation);
    }

    private static void syncLeawindThirdPersonCameraOffset(Class<?> thirdPersonClass, Object cameraAgent) {
        try {
            Object config = thirdPersonClass.getMethod("getConfig", new Class[0]).invoke(null, new Object[0]);
            Object scheme = config.getClass().getMethod("getCameraOffsetScheme", new Class[0]).invoke(config, new Object[0]);
            Object mode = scheme.getClass().getMethod("getMode", new Class[0]).invoke(scheme, new Object[0]);
            double distance = TeleportTransitionController.getLeawindThirdPersonDistance(thirdPersonClass, mode, cameraAgent);
            Object offsetRatio = mode.getClass().getMethod("getOffsetRatio", new Class[0]).invoke(mode, new Object[0]);
            Field smoothDistanceField = cameraAgent.getClass().getDeclaredField("smoothDistance");
            smoothDistanceField.setAccessible(true);
            Object smoothDistance = smoothDistanceField.get(cameraAgent);
            smoothDistance.getClass().getMethod("setTarget", Double.TYPE).invoke(smoothDistance, distance);
            smoothDistance.getClass().getMethod("setValue", Double.TYPE).invoke(smoothDistance, distance);
            Field smoothOffsetRatioField = cameraAgent.getClass().getDeclaredField("smoothOffsetRatio");
            smoothOffsetRatioField.setAccessible(true);
            Object smoothOffsetRatio = smoothOffsetRatioField.get(cameraAgent);
            Class<?> vector2dClass = Class.forName("org.joml.Vector2d");
            smoothOffsetRatio.getClass().getMethod("setTarget", vector2dClass).invoke(smoothOffsetRatio, offsetRatio);
            smoothOffsetRatio.getClass().getMethod("setValue", vector2dClass).invoke(smoothOffsetRatio, offsetRatio);
        }
        catch (LinkageError | ReflectiveOperationException | RuntimeException throwable) {
            // empty catch block
        }
    }

    private static double getLeawindReleaseDistance() {
        double distance = TeleportTransitionController.getLeawindReleaseDistance("com.github.leawind.thirdperson.ThirdPerson");
        if (Double.isFinite(distance) && distance > 0.1) {
            return distance;
        }
        return TeleportTransitionController.getLeawindReleaseDistance("com.github.leawind.thirdperson.impl.ThirdPersonImpl");
    }

    private static double getLeawindReleaseDistance(String className) {
        try {
            Class<?> thirdPersonClass = Class.forName(className);
            Object cameraAgent = thirdPersonClass.getField("CAMERA_AGENT").get(null);
            if (cameraAgent == null) {
                return Double.NaN;
            }
            Object config = thirdPersonClass.getMethod("getConfig", new Class[0]).invoke(null, new Object[0]);
            Object scheme = config.getClass().getMethod("getCameraOffsetScheme", new Class[0]).invoke(config, new Object[0]);
            Object mode = scheme.getClass().getMethod("getMode", new Class[0]).invoke(scheme, new Object[0]);
            double distance = TeleportTransitionController.getLeawindThirdPersonDistance(thirdPersonClass, mode, cameraAgent);
            Object entityAgent = TeleportTransitionController.getLeawindEntityAgent(thirdPersonClass);
            if (entityAgent != null) {
                distance += ((Number)entityAgent.getClass().getMethod("getBodyRadius", new Class[0]).invoke(entityAgent, new Object[0])).doubleValue();
            }
            return distance;
        }
        catch (LinkageError | ReflectiveOperationException | RuntimeException exception) {
            return Double.NaN;
        }
    }

    private static Object getLeawindEntityAgent(Class<?> thirdPersonClass) throws ReflectiveOperationException {
        return thirdPersonClass.getField("ENTITY_AGENT").get(null);
    }

    private static double getLeawindThirdPersonDistance(Class<?> thirdPersonClass, Object mode, Object cameraAgent) throws ReflectiveOperationException {
        double fovDivisor;
        Object entityAgent = TeleportTransitionController.getLeawindEntityAgent(thirdPersonClass);
        if (entityAgent == null) {
            return 0.0;
        }
        double distanceLimit = ((Number)mode.getClass().getMethod("getDistanceLimit", new Class[0]).invoke(mode, new Object[0])).doubleValue();
        double bodyRadius = ((Number)entityAgent.getClass().getMethod("getBodyRadius", new Class[0]).invoke(entityAgent, new Object[0])).doubleValue();
        double vehicleTotalSize = entityAgent.getClass().getField("vehicleTotalSizeCached").getDouble(entityAgent);
        double distance = (distanceLimit * vehicleTotalSize + bodyRadius) * (fovDivisor = ((Number)cameraAgent.getClass().getMethod("getSmoothFovDivisor", new Class[0]).invoke(cameraAgent, new Object[0])).doubleValue()) - bodyRadius;
        return Double.isFinite(distance) ? distance : 0.0;
    }

    public static Vec3 followPostReleaseCameraPosition(Vec3 protectedPos, Vec3 liveCameraPos) {
        return protectedPos;
    }

    public static void rememberTransitionCameraPosition(Vec3 cameraPos, Vec3 vanillaCameraPos) {
        TeleportTransitionController.rememberTransitionCameraPosition(cameraPos, vanillaCameraPos, Float.NaN, Float.NaN);
    }

    public static void rememberTransitionCameraPosition(Vec3 cameraPos, Vec3 vanillaCameraPos, float vanillaYaw, float vanillaPitch) {
        if (!TeleportTransitionController.isRunning() || cameraReleased && !TeleportTransitionController.hasPostReleaseCameraOverride()) {
            return;
        }
        currentCameraPos = cameraPos;
        if (cameraReleased && postReleaseCameraOverrideFrames > 0) {
            postReleaseCameraOverrideStableFrames = TeleportTransitionController.isPostReleaseCameraSettled(vanillaCameraPos, vanillaYaw, vanillaPitch) ? ++postReleaseCameraOverrideStableFrames : 0;
            int appliedFrames = 90 - --postReleaseCameraOverrideFrames;
            if (appliedFrames >= TeleportTransitionController.getPostReleaseCameraOverrideMinFrames() && postReleaseCameraOverrideStableFrames >= 5) {
                TeleportTransitionController.clearPostReleaseCameraOverride();
            } else if (postReleaseCameraOverrideFrames <= 0) {
                TeleportTransitionController.clearPostReleaseCameraOverride();
            }
        }
    }

    private static boolean isPostReleaseCameraSettled(Vec3 vanillaCameraPos, float vanillaYaw, float vanillaPitch) {
        if (vanillaCameraPos == null || postReleaseCameraFrame == null || vanillaCameraPos.distanceToSqr(postReleaseCameraFrame.pos()) > 0.01) {
            return false;
        }
        if (Float.isNaN(vanillaYaw) || Float.isNaN(vanillaPitch)) {
            return true;
        }
        return Math.abs(TeleportTransitionController.wrapDegrees(vanillaYaw - postReleaseCameraFrame.yaw())) <= 1.5f && Math.abs(vanillaPitch - postReleaseCameraFrame.pitch()) <= 1.5f;
    }

    private static void clearPostReleaseCameraOverride() {
        postReleaseCameraFrame = null;
        postReleaseCameraStartFrame = null;
        postReleaseCameraEndFrame = null;
        postReleaseCameraBlendStartTick = 0;
        postReleaseCameraBlendTicks = 0;
        postReleaseCameraOverrideFrames = 0;
        postReleaseCameraOverrideStableFrames = 0;
    }

    public static Vec3 getTransitionCameraPositionForRendering() {
        if (!TeleportTransitionController.isRunning()) {
            return null;
        }
        if (!cameraReleased) {
            return currentCameraPos;
        }
        CameraFrame frame = TeleportTransitionController.getPostReleaseCameraFrame(0.0f);
        return frame == null ? null : frame.pos();
    }

    public static void requestTerrainVisibilityUpdate(Vec3 cameraPos) {
        if (!TeleportTransitionController.isRunning() || cameraReleased && !TeleportTransitionController.hasPostReleaseCameraOverride()) {
            return;
        }
        Minecraft client = Minecraft.getInstance();
        if (client == null || client.level == null || client.levelRenderer == null) {
            return;
        }
        int sectionX = Mth.floor((double)(cameraPos.x / 16.0));
        int sectionY = Mth.floor((double)(cameraPos.y / 16.0));
        int sectionZ = Mth.floor((double)(cameraPos.z / 16.0));
        if (lastVisibilitySectionX == Integer.MIN_VALUE || lastVisibilitySectionY == Integer.MIN_VALUE || lastVisibilitySectionZ == Integer.MIN_VALUE) {
            lastVisibilitySectionX = sectionX;
            lastVisibilitySectionY = sectionY;
            lastVisibilitySectionZ = sectionZ;
            client.levelRenderer.allChanged();
            SodiumCompat.scheduleTerrainUpdate();
            return;
        }
        int sectionStep = TeleportTransitionController.getTerrainVisibilityRefreshSectionStep(client);
        if (Math.abs(sectionX - lastVisibilitySectionX) < sectionStep && sectionY == lastVisibilitySectionY && Math.abs(sectionZ - lastVisibilitySectionZ) < sectionStep) {
            return;
        }
        lastVisibilitySectionX = sectionX;
        lastVisibilitySectionY = sectionY;
        lastVisibilitySectionZ = sectionZ;
        client.levelRenderer.allChanged();
        SodiumCompat.scheduleTerrainUpdate();
    }

    private static void beginArrivalTerrainRefresh(Minecraft client) {
        arrivalTerrainRefreshTicks = Math.max(arrivalTerrainRefreshTicks, 40);
        TeleportTransitionController.forceTerrainRefresh(client);
    }

    private static void updateArrivalTerrainRefresh(Minecraft client) {
        if (arrivalTerrainRefreshTicks <= 0 || cameraReleased) {
            return;
        }
        --arrivalTerrainRefreshTicks;
        TeleportTransitionController.forceTerrainRefresh(client);
    }

    private static void forceTerrainRefresh(Minecraft client) {
        TeleportTransitionController.resetVisibilityRefreshState();
        if (client.levelRenderer != null) {
            client.levelRenderer.allChanged();
        }
        SodiumCompat.scheduleTerrainUpdate();
    }

    public static boolean shouldForceTerrainFrustumApply() {
        return TeleportTransitionController.isRunning() && (!cameraReleased || TeleportTransitionController.hasPostReleaseCameraOverride());
    }

    public static boolean shouldUseGroundSkyBackground() {
        Minecraft client = Minecraft.getInstance();
        return TeleportTransitionController.shouldForceTerrainFrustumApply() && client != null && client.level != null && client.level.dimensionType().hasSkyLight() && !client.level.dimensionType().hasCeiling();
    }

    public static boolean shouldPreferVoxyOnlyTerrain() {
        if (!TeleportTransitionController.isVoxyTerrainAvailable() || TeleportTransitionController.isDistantHorizonsTerrainAvailable() || TeleportTransitionController.isBobbyTerrainAvailable() || !TeleportTransitionController.isRunning() || cameraReleased) {
            return false;
        }
        return ticks >= TeleportTransitionController.getPullEndTick() && ticks < TeleportTransitionController.getPushStartTick();
    }

    public static boolean shouldOverrideDistantHorizonsNearClip() {
        if (!TeleportTransitionController.isDistantHorizonsTerrainAvailable() || !TeleportTransitionController.isRunning() || cameraReleased) {
            return false;
        }
        return ticks >= TeleportTransitionController.getPullEndTick() && ticks < TeleportTransitionController.getPushStartTick();
    }

    public static boolean shouldRetainDepartingChunk(int chunkX, int chunkZ) {
        if (!TeleportTransitionController.shouldUseNormalChunkHandoff() || !commandSent || cameraReleased || startFeet == null || ticks >= TeleportTransitionController.getTravelStartTick()) {
            return false;
        }
        int startChunkX = Mth.floor((double)TeleportTransitionController.startFeet.x) >> 4;
        int startChunkZ = Mth.floor((double)TeleportTransitionController.startFeet.z) >> 4;
        int radius = TeleportTransitionController.getRetainedDepartingChunkRadius(Minecraft.getInstance());
        if (Math.abs(chunkX - startChunkX) > radius || Math.abs(chunkZ - startChunkZ) > radius) {
            return false;
        }
        retainedDepartingChunks.add(TeleportTransitionController.chunkKey(chunkX, chunkZ));
        return true;
    }

    public static boolean shouldHideFallbackTerrain() {
        if (!TeleportTransitionController.isFallbackTerrainMode() || cameraReleased) {
            return false;
        }
        return ticks >= TeleportTransitionController.getFallbackTerrainHideTick() && ticks < TeleportTransitionController.getFallbackTerrainShowTick();
    }

    public static boolean shouldApplyFallbackTerrainVisibility() {
        return TeleportTransitionController.isFallbackTerrainMode() && !cameraReleased;
    }

    public static boolean shouldHardCutFallbackTerrain() {
        return TeleportTransitionController.shouldApplyFallbackTerrainVisibility() && IrisCompat.shouldUseHardTerrainCut();
    }

    public static boolean shouldHideHardCutFallbackTerrain() {
        return TeleportTransitionController.shouldHardCutFallbackTerrain() && TeleportTransitionController.shouldHideFallbackTerrain();
    }

    public static boolean shouldUseHardCutFallbackTerrainCulling() {
        return TeleportTransitionController.shouldHardCutFallbackTerrain() && !TeleportTransitionController.shouldUseShaderScreenMaskOnly() && (TeleportTransitionController.isFallbackTerrainFadeOutActive() || TeleportTransitionController.shouldHideFallbackTerrain());
    }

    public static boolean shouldRecoverHardCutFallbackTerrain() {
        return TeleportTransitionController.shouldHardCutFallbackTerrain() && !TeleportTransitionController.shouldUseShaderScreenMaskOnly() && ticks >= TeleportTransitionController.getFallbackTerrainShowTick();
    }

    public static boolean shouldUseShaderScreenMaskOnly() {
        return false;
    }

    private static boolean shouldUseScreenMaskFallbackTerrain() {
        return false;
    }

    public static boolean shouldApplyVanillaFallbackTerrainVisibility() {
        return TeleportTransitionController.shouldApplyFallbackTerrainVisibility() && !TeleportTransitionController.shouldUseShaderScreenMaskOnly() && ticks >= TeleportTransitionController.getVanillaTerrainFadeOutStartTick() && ticks < TeleportTransitionController.getVanillaTerrainFadeInEndTick();
    }

    public static boolean shouldHideFallbackSkyCelestials() {
        return TeleportTransitionController.isFallbackTerrainMode() && !cameraReleased;
    }

    public static float getFallbackTerrainSectionVisibility(BlockPos renderOrigin) {
        return TeleportTransitionController.getFallbackTerrainSectionVisibility((double)renderOrigin.getX() + 8.0, (double)renderOrigin.getY() + 8.0, (double)renderOrigin.getZ() + 8.0);
    }

    public static float getFallbackTerrainSectionVisibility(double centerX, double centerY, double centerZ) {
        if (!TeleportTransitionController.shouldApplyFallbackTerrainVisibility()) {
            return 1.0f;
        }
        float frameTick = ticks;
        int fadeOutStart = TeleportTransitionController.getVanillaTerrainFadeOutStartTick();
        int fadeOutEnd = TeleportTransitionController.getVanillaTerrainFadeOutEndTick();
        int fadeInStart = TeleportTransitionController.getVanillaTerrainFadeInStartTick();
        int fadeInEnd = TeleportTransitionController.getVanillaTerrainFadeInEndTick();
        if (frameTick >= (float)fadeOutEnd && frameTick < (float)fadeInStart) {
            return 0.0f;
        }
        if (frameTick >= (float)fadeOutStart && frameTick < (float)fadeOutEnd) {
            float progress = TeleportTransitionController.smoothStep((frameTick - (float)fadeOutStart) / 10.0f);
            float threshold = 1.0f - TeleportTransitionController.getDistanceOrderTowardPoint(centerX, centerZ, startFeet, TeleportTransitionController.getBestTargetFeet());
            return 1.0f - TeleportTransitionController.smoothStep((progress - threshold) / 0.22f);
        }
        if (frameTick >= (float)fadeInStart && frameTick < (float)fadeInEnd) {
            float progress = TeleportTransitionController.smoothStep((frameTick - (float)fadeInStart) / 10.0f);
            float threshold = TeleportTransitionController.getDistanceOrderTowardPoint(centerX, centerZ, TeleportTransitionController.getBestTargetFeet(), startFeet);
            return TeleportTransitionController.smoothStep((progress - threshold) / 0.22f);
        }
        return 1.0f;
    }

    public static float getShaderScreenMaskSectionOpacity(double centerX, double centerY, double centerZ) {
        if (!TeleportTransitionController.shouldUseShaderScreenMaskOnly()) {
            return 0.0f;
        }
        float frameTick = ticks;
        int fadeOutStart = TeleportTransitionController.getFallbackTerrainFadeOutStartTick();
        int fadeOutEnd = TeleportTransitionController.getFallbackTerrainFadeOutEndTick();
        int fadeInStart = TeleportTransitionController.getFallbackTerrainFadeInStartTick();
        int fadeInEnd = TeleportTransitionController.getFallbackTerrainFadeInEndTick();
        if (frameTick >= (float)fadeOutEnd && frameTick < (float)fadeInStart) {
            return 1.0f;
        }
        if (frameTick >= (float)fadeOutStart && frameTick < (float)fadeOutEnd) {
            float progress = TeleportTransitionController.smoothStep((frameTick - (float)fadeOutStart) / 10.0f);
            float threshold = TeleportTransitionController.getShaderMaskSweepThreshold(TeleportTransitionController.getDistanceOrderTowardPoint(centerX, centerZ, startFeet, TeleportTransitionController.getBestTargetFeet()));
            return TeleportTransitionController.smoothStep((progress - threshold) / 0.22f);
        }
        if (frameTick >= (float)fadeInStart && frameTick < (float)fadeInEnd) {
            float progress = TeleportTransitionController.smoothStep((frameTick - (float)fadeInStart) / 10.0f);
            float threshold = TeleportTransitionController.getShaderMaskSweepThreshold(1.0f - TeleportTransitionController.getDistanceOrderTowardPoint(centerX, centerZ, TeleportTransitionController.getBestTargetFeet(), startFeet));
            return 1.0f - TeleportTransitionController.smoothStep((progress - threshold) / 0.22f);
        }
        return 0.0f;
    }

    public static boolean shouldRenderFallbackTerrainSection(double centerX, double centerY, double centerZ) {
        return TeleportTransitionController.getFallbackTerrainSectionVisibility(centerX, centerY, centerZ) >= 0.5f;
    }

    public static boolean shouldCullFallbackTerrainSection(double centerX, double centerY, double centerZ) {
        if (!TeleportTransitionController.shouldUseHardCutFallbackTerrainCulling()) {
            return false;
        }
        if (TeleportTransitionController.shouldHideFallbackTerrain()) {
            return true;
        }
        return TeleportTransitionController.getFallbackTerrainSectionVisibility(centerX, centerY, centerZ) < 0.5f;
    }

    public static float getStepEffectIntensity(float tickProgress) {
        return TeleportTransitionController.getStepEffectIntensity(tickProgress, true);
    }

    public static float getCameraMotionStepEffectIntensity(float tickProgress) {
        return TeleportTransitionController.getStepEffectIntensity(tickProgress, false);
    }

    private static float getStepEffectIntensity(float tickProgress, boolean includeReleasePulse) {
        if (!TeleportTransitionController.isRunning()) {
            return 0.0f;
        }
        float frameTick = (float)ticks + tickProgress;
        float intensity = 0.0f;
        intensity = Math.max(intensity, TeleportTransitionController.pulseAfter(frameTick, TeleportTransitionController.getPullStageTick(0)));
        intensity = Math.max(intensity, TeleportTransitionController.pulseAfter(frameTick, TeleportTransitionController.getPullStageTick(1)));
        intensity = Math.max(intensity, TeleportTransitionController.pulseAfter(frameTick, TeleportTransitionController.getPullStageTick(2)));
        intensity = Math.max(intensity, TeleportTransitionController.pulseAfter(frameTick, TeleportTransitionController.getPushStageTick(1)));
        intensity = Math.max(intensity, TeleportTransitionController.pulseAfter(frameTick, TeleportTransitionController.getPushStageTick(2)));
        if (includeReleasePulse) {
            intensity = Math.max(intensity, TeleportTransitionController.pulseAfter(frameTick, TeleportTransitionController.getEnterHoldStartTick()));
        }
        return intensity;
    }

    public static float getHudFadeOverlayIntensity(float tickProgress) {
        if (!TeleportTransitionController.isRunning()) {
            return 0.0f;
        }
        float frameTick = (float)ticks + tickProgress;
        float releaseTick = totalTicks;
        if (frameTick < 8.0f) {
            return TeleportTransitionController.smoothStep(frameTick / 8.0f) * 0.46f;
        }
        if (frameTick < 16.0f) {
            return (1.0f - TeleportTransitionController.smoothStep((frameTick - 8.0f) / 8.0f)) * 0.46f;
        }
        if (frameTick >= releaseTick && frameTick <= releaseTick + 8.0f) {
            return (1.0f - TeleportTransitionController.smoothStep((frameTick - releaseTick) / 8.0f)) * 0.46f;
        }
        return 0.0f;
    }

    public static float getShaderScreenMaskIntensity(float tickProgress) {
        if (!TeleportTransitionController.shouldUseScreenMaskFallbackTerrain() || cameraReleased) {
            return 0.0f;
        }
        float frameTick = (float)ticks + tickProgress;
        int fadeOutStart = TeleportTransitionController.getFallbackTerrainFadeOutStartTick();
        int fadeOutEnd = TeleportTransitionController.getFallbackTerrainFadeOutEndTick();
        int fadeInStart = TeleportTransitionController.getFallbackTerrainFadeInStartTick();
        int fadeInEnd = TeleportTransitionController.getFallbackTerrainFadeInEndTick();
        float maxIntensity = 1.0f;
        if (TeleportTransitionController.shouldUseShaderScreenMaskOnly()) {
            return frameTick >= (float)fadeOutStart && frameTick < (float)fadeInEnd ? maxIntensity : 0.0f;
        }
        if (frameTick >= (float)fadeOutEnd && frameTick < (float)fadeInStart) {
            return maxIntensity;
        }
        if (frameTick >= (float)fadeOutStart && frameTick < (float)fadeOutEnd) {
            return TeleportTransitionController.smoothStep((frameTick - (float)fadeOutStart) / 10.0f) * maxIntensity;
        }
        if (frameTick >= (float)fadeInStart && frameTick < (float)fadeInEnd) {
            return (1.0f - TeleportTransitionController.smoothStep((frameTick - (float)fadeInStart) / 10.0f)) * maxIntensity;
        }
        return 0.0f;
    }

    private static String normalizeTargetDimensionId(String targetDimensionId) {
        return DimensionIds.normalize(targetDimensionId);
    }

    private static boolean shouldSkipTravelForTargetDimension(String targetDimensionId) {
        return targetDimensionId != null && startDimension != null && !TeleportConfig.isCrossDimensionTravelEnabled() && !targetDimensionId.equals(DimensionIds.fromResourceKey(startDimension));
    }

    private static boolean hasArrivedAtTeleportTarget(Minecraft client, Vec3 playerFeet) {
        if (skipTravel && plannedTargetDimensionId != null) {
            if (!TeleportTransitionController.isInPlannedTargetDimension(client) || skipTravelTargetDimensionTicks < 3) {
                return false;
            }
            if (plannedTargetFeetStable && plannedTargetFeet != null) {
                return TeleportTransitionController.isNearPlannedTarget(playerFeet) || skipTravelTargetDimensionTicks >= 24;
            }
            return TeleportTransitionController.isNearPlannedTarget(playerFeet) || TeleportTransitionController.hasTeleportedSinceLastObservation(playerFeet) || skipTravelTargetDimensionTicks >= 6;
        }
        return TeleportTransitionController.isNearPlannedTarget(playerFeet) || TeleportTransitionController.hasTeleportedSinceLastObservation(playerFeet);
    }

    private static void updateSkipTravelTargetDimensionTicks(Minecraft client) {
        if (!skipTravel) {
            skipTravelTargetDimensionTicks = 0;
            return;
        }
        if (TeleportTransitionController.isInPlannedTargetDimension(client)) {
            if (++skipTravelTargetDimensionTicks == 1 && client.player != null) {
                LOGGER.info("TA cross-dimension target level visible: targetDim={} playerFeet={} plannedTargetFeet={}", new Object[]{plannedTargetDimensionId, TeleportTransitionController.getFeetPos(client.player), plannedTargetFeet});
            }
        } else {
            skipTravelTargetDimensionTicks = 0;
        }
    }

    private static void closeSkipTravelLoadingTerrainScreen(Minecraft client) {
        if (!skipTravel || client == null || !(client.screen instanceof LevelLoadingScreen)) {
            return;
        }
        if (!TeleportTransitionController.canRevealSkipTravelTarget(client)) {
            return;
        }
        LOGGER.info("TA closing cross-dimension loading terrain screen: targetDim={} targetDimTicks={} actualTargetFeet={} plannedTargetFeet={}", new Object[]{plannedTargetDimensionId, skipTravelTargetDimensionTicks, actualTargetFeet, plannedTargetFeet});
        client.setScreen(null);
    }

    private static boolean canRevealSkipTravelTarget(Minecraft client) {
        if (actualTargetFeet != null) {
            return TeleportTransitionController.isArrivalSurfaceReady(client);
        }
        return plannedTargetFeetStable && plannedTargetFeet != null && TeleportTransitionController.isInPlannedTargetDimension(client) && skipTravelTargetDimensionTicks >= 3 && TeleportTransitionController.isArrivalSurfaceReady(client);
    }

    private static boolean hasUsableSkipTravelArrivalFeet(Minecraft client) {
        return skipTravelTargetDimensionTicks >= 3 && TeleportTransitionController.isInPlannedTargetDimension(client) && client != null && client.player != null;
    }

    private static boolean isArrivalSurfaceReady(Minecraft client) {
        if (!TeleportTransitionController.isOpenSkyDimension(client)) {
            return true;
        }
        Vec3 feet = TeleportTransitionController.getArrivalCameraFeet();
        return feet != null && !Double.isNaN(TeleportTransitionController.resolveSkyFacingSurfaceY(client, feet, feet.y));
    }

    private static boolean isInPlannedTargetDimension(Minecraft client) {
        return plannedTargetDimensionId != null && client != null && client.level != null && plannedTargetDimensionId.equals(DimensionIds.fromResourceKey(client.level.dimension()));
    }

    private static boolean isNearPlannedTarget(Vec3 playerFeet) {
        if (!plannedTargetFeetStable || plannedTargetFeet == null) {
            return false;
        }
        double dx = playerFeet.x - TeleportTransitionController.plannedTargetFeet.x;
        double dz = playerFeet.z - TeleportTransitionController.plannedTargetFeet.z;
        double dy = Math.abs(playerFeet.y - TeleportTransitionController.plannedTargetFeet.y);
        return dx * dx + dz * dz <= 16.0 && dy <= 8.0;
    }

    private static boolean hasTeleportedSinceLastObservation(Vec3 playerFeet) {
        return lastObservedPlayerFeet != null && playerFeet.distanceToSqr(lastObservedPlayerFeet) > 9.0;
    }

    private static int calculateTravelTicks(Vec3 fromFeet, Vec3 toFeet) {
        if (fromFeet == null || toFeet == null) {
            return 40;
        }
        double dx = toFeet.x - fromFeet.x;
        double dz = toFeet.z - fromFeet.z;
        double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
        int scaledTicks = Mth.ceil((double)(horizontalDistance / 30.0));
        return Mth.clamp((int)scaledTicks, (int)20, (int)60);
    }

    private static void recordActualTargetFeet(Vec3 playerFeet) {
        double resolvedArrivalSurfaceY;
        actualTargetFeet = playerFeet;
        if (TeleportTransitionController.shouldUseNormalChunkHandoff() && normalChunkHandoffArrivalTick == Integer.MIN_VALUE) {
            normalChunkHandoffArrivalTick = ticks;
        }
        if (!Double.isNaN(resolvedArrivalSurfaceY = TeleportTransitionController.resolveSkyFacingSurfaceY(Minecraft.getInstance(), playerFeet, playerFeet.y))) {
            arrivalSurfaceY = resolvedArrivalSurfaceY;
        }
        TeleportTransitionController.updateArrivalBodyCameraHeights(Minecraft.getInstance(), playerFeet);
        if (skipTravel) {
            LOGGER.info("TA cross-dimension arrival recorded: actualTargetFeet={} plannedTargetFeet={} targetDimTicks={}", new Object[]{actualTargetFeet, plannedTargetFeet, skipTravelTargetDimensionTicks});
        }
        if (!skipTravel && !plannedTargetFeetStable && ticks <= TeleportTransitionController.getTravelStartTick()) {
            travelTicks = TeleportTransitionController.calculateTravelTicks(startFeet, actualTargetFeet);
            totalTicks = TeleportTransitionController.getFixedTotalTicks() + travelTicks;
        }
        if (skipTravel || ticks < TeleportTransitionController.getTravelEndTick() || arrivalCameraFeet == null) {
            arrivalCameraFeet = playerFeet;
        }
    }

    private static CameraFrame exitBodyFrame(float progress) {
        float heightProgress = TeleportTransitionController.smoothStep(progress);
        float horizontalProgress = TeleportTransitionController.shouldRespectPreviousCameraPosition() ? TeleportTransitionController.perspectiveHorizontalProgress(progress) : heightProgress;
        float lookProgress = TeleportTransitionController.bodyLookProgress(progress);
        Vec3 source = startEye == null ? startFeet.add(0.0, 1.6, 0.0) : startEye;
        Vec3 target = startFeet.add(0.0, TeleportTransitionController.getStartBodyCameraHeight(), 0.0);
        return new CameraFrame(TeleportTransitionController.lerpPerspectiveCameraPosition(source, target, heightProgress, horizontalProgress), startYaw, TeleportTransitionController.lerpFloat(startPitch, 90.0f, lookProgress));
    }

    private static CameraFrame startBodyHoldFrame(float frameTick) {
        float progress = (frameTick - (float)TeleportTransitionController.getExitBodyTicks()) / (float)TeleportTransitionController.getBodyHoldTicks();
        double altitude = TeleportTransitionController.getStartBodyCameraHeight() + TeleportTransitionController.glideProgress(progress) * TeleportTransitionController.getStartBodyGlideHeight();
        return TeleportTransitionController.bodyHoldFrame(startFeet, startYaw, altitude);
    }

    private static CameraFrame enterBodyHoldFrame(float frameTick) {
        TeleportTransitionController.lockArrivalBodyCameraHeightsForEnter(Minecraft.getInstance());
        float progress = (frameTick - (float)TeleportTransitionController.getEnterHoldStartTick()) / (float)TeleportTransitionController.getBodyHoldTicks();
        double altitude = TeleportTransitionController.getArrivalBodyCameraHeight() + (1.0 - TeleportTransitionController.glideProgress(progress)) * TeleportTransitionController.getArrivalBodyGlideHeight();
        return TeleportTransitionController.bodyHoldFrame(TeleportTransitionController.getArrivalCameraFeet(), startYaw, altitude);
    }

    private static CameraFrame bodyHoldFrame(Vec3 feet, float yaw, double altitude) {
        return new CameraFrame(feet.add(0.0, altitude, 0.0), yaw, 90.0f);
    }

    private static CameraFrame pullFrame(float progress, float frameTick) {
        Vec3 pos = TeleportTransitionController.topDownPos(startFeet, TeleportTransitionController.getStartSurfaceY(), TeleportTransitionController.pullAltitude(frameTick));
        return TeleportTransitionController.applyZoomShake(pos, startYaw, 90.0f, frameTick, 1.0f);
    }

    private static CameraFrame topDownFrame(Vec3 feet, double surfaceY, float yaw, float frameTick, double altitude) {
        Vec3 pos = TeleportTransitionController.topDownPos(feet, surfaceY, altitude);
        return TeleportTransitionController.applyZoomShake(pos, yaw, 90.0f, frameTick, 1.0f);
    }

    private static CameraFrame prePushTopDownFrame(Vec3 feet, float yaw, float frameTick) {
        return TeleportTransitionController.topDownFrame(feet, TeleportTransitionController.getArrivalSurfaceY(), yaw, frameTick, TeleportTransitionController.getTargetTravelAltitude());
    }

    private static CameraFrame travelFrame(float progress, float frameTick) {
        Vec3 source = TeleportTransitionController.topDownPos(startFeet, TeleportTransitionController.getStartSurfaceY(), TeleportTransitionController.getStartTravelAltitude());
        Vec3 target = TeleportTransitionController.topDownPos(TeleportTransitionController.getTravelTargetFeet(), TeleportTransitionController.getArrivalSurfaceY(), TeleportTransitionController.getTargetTravelAltitude());
        Vec3 pos = source.lerp(target, (double)TeleportTransitionController.travelEaseProgress(progress));
        return TeleportTransitionController.applyZoomShake(pos, startYaw, 90.0f, frameTick, 1.0f);
    }

    private static float travelEaseProgress(float progress) {
        double total = Math.max(1.0, (double)travelTicks);
        double edge = Math.min(10.0, total * 0.5);
        double elapsed = (double)Mth.clamp((float)progress, (float)0.0f, (float)1.0f) * total;
        double weightedTotal = Math.max(1.0, total - edge);
        if (elapsed < edge) {
            return (float)(edge * TeleportTransitionController.integratedSmoothStep(elapsed / edge) / weightedTotal);
        }
        double steadyEnd = total - edge;
        if (elapsed <= steadyEnd) {
            return (float)((edge * 0.5 + elapsed - edge) / weightedTotal);
        }
        double decelElapsed = elapsed - steadyEnd;
        double beforeDecel = edge * 0.5 + Math.max(0.0, total - edge * 2.0);
        return (float)((beforeDecel + edge * TeleportTransitionController.integratedSmoothStepDown(decelElapsed / edge)) / weightedTotal);
    }

    private static double integratedSmoothStep(double value) {
        double x = Mth.clamp((double)value, (double)0.0, (double)1.0);
        return x * x * x - 0.5 * x * x * x * x;
    }

    private static double integratedSmoothStepDown(double value) {
        double x = Mth.clamp((double)value, (double)0.0, (double)1.0);
        return x - TeleportTransitionController.integratedSmoothStep(x);
    }

    private static CameraFrame pushFrame(LocalPlayer player, float progress, float frameTick) {
        Vec3 pos = TeleportTransitionController.topDownPos(TeleportTransitionController.getArrivalCameraFeet(), TeleportTransitionController.getArrivalSurfaceY(), TeleportTransitionController.pushAltitude(frameTick));
        return TeleportTransitionController.applyZoomShake(pos, startYaw, 90.0f, frameTick, 1.0f);
    }

    private static CameraFrame enterBodyFrame(LocalPlayer player, float progress) {
        TeleportTransitionController.lockArrivalBodyCameraHeightsForEnter(Minecraft.getInstance());
        TeleportTransitionController.captureEnterBodyTarget(player);
        float heightProgress = TeleportTransitionController.shouldRespectPreviousCameraPosition() ? TeleportTransitionController.smoothStep(progress) : TeleportTransitionController.firstPersonEnterBodyHeightProgress(progress);
        float horizontalProgress = TeleportTransitionController.shouldRespectPreviousCameraPosition() ? TeleportTransitionController.perspectiveHorizontalProgress(progress) : heightProgress;
        float lookProgress = TeleportTransitionController.bodyLookProgress(progress);
        Vec3 source = TeleportTransitionController.getArrivalCameraFeet().add(0.0, TeleportTransitionController.getArrivalBodyCameraHeight(), 0.0);
        Vec3 target = enterBodyTargetEye == null ? player.getEyePosition() : enterBodyTargetEye;
        float yaw = TeleportTransitionController.lerpDegrees(startYaw, enterBodyTargetYaw, lookProgress);
        float pitch = TeleportTransitionController.lerpFloat(90.0f, enterBodyTargetPitch, lookProgress);
        return new CameraFrame(TeleportTransitionController.lerpPerspectiveCameraPosition(source, target, heightProgress, horizontalProgress), yaw, pitch);
    }

    private static void captureEnterBodyTarget(LocalPlayer player) {
        if (enterBodyTargetEye != null && !TeleportTransitionController.shouldRefreshEnterBodyTarget()) {
            return;
        }
        Vec3 playerEye = player.getEyePosition();
        if (TeleportTransitionController.shouldRespectPreviousCameraPosition() && LEAWIND_THIRD_PERSON_LOADED) {
            enterBodyTargetEye = playerEye;
            enterBodyTargetYaw = player.getYRot();
            enterBodyTargetPitch = player.getXRot();
            return;
        }
        enterBodyTargetEye = TeleportTransitionController.getEnterBodyTargetCameraPosition(player, playerEye);
        if (TeleportTransitionController.shouldRespectPreviousCameraPosition()) {
            if (LEAWIND_THIRD_PERSON_LOADED) {
                enterBodyTargetYaw = player.getYRot();
                enterBodyTargetPitch = player.getXRot();
                return;
            }
            Camera camera = TeleportTransitionController.getCurrentCamera();
            Vec3 liveCameraPos = TeleportTransitionController.getCurrentCameraPosition(playerEye);
            if (camera != null && liveCameraPos != null) {
                enterBodyTargetYaw = camera.yRot();
                enterBodyTargetPitch = camera.xRot();
                return;
            }
            enterBodyTargetYaw = TeleportTransitionController.getVanillaPreviousCameraYaw(player);
            enterBodyTargetPitch = TeleportTransitionController.getVanillaPreviousCameraPitch(player);
            return;
        }
        enterBodyTargetYaw = player.getYRot();
        enterBodyTargetPitch = player.getXRot();
    }

    private static boolean shouldRefreshEnterBodyTarget() {
        return !LEAWIND_THIRD_PERSON_LOADED && TeleportTransitionController.shouldSampleExternalPerspectiveCamera() && !cameraReleased;
    }

    private static Vec3 getEnterBodyTargetCameraPosition(LocalPlayer player, Vec3 playerEye) {
        if (!TeleportTransitionController.shouldRespectPreviousCameraPosition()) {
            return playerEye;
        }
        if (LEAWIND_THIRD_PERSON_LOADED) {
            return TeleportTransitionController.getLeawindPreviousCameraPosition(player, playerEye);
        }
        Vec3 liveCameraPos = TeleportTransitionController.getCurrentCameraPosition(playerEye);
        if (liveCameraPos != null) {
            return liveCameraPos;
        }
        return TeleportTransitionController.getVanillaPreviousCameraPosition(player, playerEye);
    }

    private static Vec3 getLeawindPreviousCameraPosition(LocalPlayer player, Vec3 playerEye) {
        double distance = TeleportTransitionController.getLeawindReleaseDistance();
        if (!Double.isFinite(distance) || distance <= 0.1) {
            distance = startCameraEyeOffset == null ? 4.0 : Mth.clamp((double)startCameraEyeOffset.length(), (double)1.0, (double)12.0);
        }
        Vec3 forward = TeleportTransitionController.getCameraForwardVector(player.getYRot(), player.getXRot());
        return TeleportTransitionController.clipCameraOffset(Minecraft.getInstance(), player, playerEye, forward.scale(-distance));
    }

    private static Vec3 getPreservedPerspectiveCameraOffset(LocalPlayer player) {
        if (player == null || startCameraEyeOffset == null) {
            return null;
        }
        double offsetLengthSqr = startCameraEyeOffset.lengthSqr();
        if (!Double.isFinite(offsetLengthSqr) || offsetLengthSqr < 0.01 || offsetLengthSqr > 576.0) {
            return null;
        }
        return TeleportTransitionController.rotateHorizontalOffset(startCameraEyeOffset, player.getYRot() - startPlayerYaw);
    }

    private static Vec3 rotateHorizontalOffset(Vec3 offset, float yawDegrees) {
        if (offset == null || Math.abs(yawDegrees) < 1.0E-4f) {
            return offset;
        }
        double radians = yawDegrees * ((float)Math.PI / 180);
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);
        double x = offset.x * cos - offset.z * sin;
        double z = offset.x * sin + offset.z * cos;
        return new Vec3(x, offset.y, z);
    }

    private static Vec3 getVanillaPreviousCameraPosition(LocalPlayer player, Vec3 playerEye) {
        Vec3 liveCameraPos = TeleportTransitionController.getCurrentCameraPosition(playerEye);
        if (liveCameraPos != null) {
            return liveCameraPos;
        }
        float yaw = TeleportTransitionController.getVanillaPreviousCameraYaw(player);
        float pitch = TeleportTransitionController.getVanillaPreviousCameraPitch(player);
        Vec3 forward = TeleportTransitionController.getCameraForwardVector(yaw, pitch);
        double zoom = TeleportTransitionController.getMaxThirdPersonZoom(Minecraft.getInstance(), player, playerEye, forward, 4.0);
        return playerEye.subtract(forward.scale(zoom));
    }

    private static Vec3 getCameraForwardVector(float yaw, float pitch) {
        float yawRadians = yaw * ((float)Math.PI / 180);
        float pitchRadians = pitch * ((float)Math.PI / 180);
        float cosPitch = Mth.cos((float)pitchRadians);
        return new Vec3((double)(-Mth.sin((float)yawRadians) * cosPitch), (double)(-Mth.sin((float)pitchRadians)), (double)(Mth.cos((float)yawRadians) * cosPitch));
    }

    private static double getMaxThirdPersonZoom(Minecraft client, LocalPlayer player, Vec3 playerEye, Vec3 forward, double requestedZoom) {
        if (client == null || client.level == null || player == null || playerEye == null || forward == null) {
            return requestedZoom;
        }
        double zoom = requestedZoom;
        for (int i = 0; i < 8; ++i) {
            double hitDistance;
            Vec3 to;
            double xOffset = (double)((i & 1) * 2 - 1) * 0.1;
            double yOffset = (double)((i >> 1 & 1) * 2 - 1) * 0.1;
            double zOffset = (double)((i >> 2 & 1) * 2 - 1) * 0.1;
            Vec3 from = playerEye.add(xOffset, yOffset, zOffset);
            BlockHitResult hit = client.level.clip(new ClipContext(from, to = from.subtract(forward.scale(requestedZoom)), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, (Entity)player));
            if (hit.getType() == HitResult.Type.MISS || !((hitDistance = hit.getLocation().distanceTo(playerEye)) < zoom)) continue;
            zoom = hitDistance;
        }
        return Mth.clamp((double)zoom, (double)0.0, (double)requestedZoom);
    }

    private static Vec3 clipCameraOffset(Minecraft client, LocalPlayer player, Vec3 playerEye, Vec3 offset) {
        Vec3 target;
        if (client == null || client.level == null || player == null || playerEye == null || offset == null || offset.lengthSqr() < 1.0E-6) {
            return playerEye.add(offset == null ? Vec3.ZERO : offset);
        }
        Vec3 clipped = target = playerEye.add(offset);
        for (int i = 0; i < 8; ++i) {
            Vec3 to;
            double xOffset = (double)((i & 1) * 2 - 1) * 0.1;
            double yOffset = (double)((i >> 1 & 1) * 2 - 1) * 0.1;
            double zOffset = (double)((i >> 2 & 1) * 2 - 1) * 0.1;
            Vec3 from = playerEye.add(xOffset, yOffset, zOffset);
            BlockHitResult hit = client.level.clip(new ClipContext(from, to = target.add(xOffset, yOffset, zOffset), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, (Entity)player));
            if (hit.getType() == HitResult.Type.MISS || !(hit.getLocation().distanceToSqr(playerEye) < clipped.distanceToSqr(playerEye))) continue;
            clipped = hit.getLocation();
        }
        return clipped;
    }

    private static Camera getCurrentCamera() {
        Minecraft client = Minecraft.getInstance();
        return client == null || client.gameRenderer == null ? null : client.gameRenderer.getMainCamera();
    }

    private static Vec3 getCurrentCameraPosition(Vec3 playerEye) {
        Camera camera = TeleportTransitionController.getCurrentCamera();
        if (camera == null || camera.position() == null || playerEye == null) {
            return null;
        }
        Vec3 cameraPos = camera.position();
        return cameraPos.distanceToSqr(playerEye) <= 64.0 ? cameraPos : null;
    }

    private static float getVanillaPreviousCameraYaw(LocalPlayer player) {
        float yaw = player.getYRot();
        return previousCameraType == CameraType.THIRD_PERSON_FRONT ? yaw + 180.0f : yaw;
    }

    private static float getVanillaPreviousCameraPitch(LocalPlayer player) {
        float pitch = player.getXRot();
        return previousCameraType == CameraType.THIRD_PERSON_FRONT ? -pitch : pitch;
    }

    private static Vec3 getInitialTransitionCameraPosition(Minecraft client, Vec3 fallback) {
        if (client == null || client.gameRenderer == null) {
            return fallback;
        }
        Camera camera = client.gameRenderer.getMainCamera();
        return camera == null || camera.position() == null ? fallback : camera.position();
    }

    private static float getInitialTransitionYaw(Minecraft client, LocalPlayer player) {
        if (!TeleportTransitionController.shouldUseStoredPerspectiveCamera() || client == null || client.gameRenderer == null) {
            return player.getYRot();
        }
        Camera camera = client.gameRenderer.getMainCamera();
        return camera == null ? player.getYRot() : camera.yRot();
    }

    private static float getInitialTransitionPitch(Minecraft client, LocalPlayer player) {
        if (!TeleportTransitionController.shouldUseStoredPerspectiveCamera() || client == null || client.gameRenderer == null) {
            return player.getXRot();
        }
        Camera camera = client.gameRenderer.getMainCamera();
        return camera == null ? player.getXRot() : camera.xRot();
    }

    private static boolean shouldUseStoredPerspectiveCamera() {
        return LEAWIND_THIRD_PERSON_LOADED || TeleportTransitionController.shouldRespectPreviousCameraPosition();
    }

    private static boolean shouldRespectPreviousCameraPosition() {
        return previousCameraType != null && previousCameraType != CameraType.FIRST_PERSON;
    }

    private static Vec3 getBestTargetFeet() {
        if (actualTargetFeet != null) {
            return actualTargetFeet;
        }
        if (plannedTargetFeetStable && plannedTargetFeet != null) {
            return plannedTargetFeet;
        }
        return startFeet;
    }

    private static Vec3 getArrivalCameraFeet() {
        if (skipTravel) {
            if (actualTargetFeet != null) {
                return actualTargetFeet;
            }
            if (plannedTargetFeetStable && plannedTargetFeet != null) {
                return plannedTargetFeet;
            }
            Vec3 liveArrivalFeet = TeleportTransitionController.getLiveSkipTravelArrivalFeet();
            if (liveArrivalFeet != null) {
                return liveArrivalFeet;
            }
            if (plannedTargetFeetStable && plannedTargetFeet != null) {
                return plannedTargetFeet;
            }
        }
        if (arrivalCameraFeet != null) {
            return arrivalCameraFeet;
        }
        return TeleportTransitionController.getBestTargetFeet();
    }

    private static Vec3 getLiveSkipTravelArrivalFeet() {
        Minecraft client = Minecraft.getInstance();
        if (!TeleportTransitionController.hasUsableSkipTravelArrivalFeet(client)) {
            return null;
        }
        return TeleportTransitionController.getFeetPos(client.player);
    }

    private static Vec3 getTravelTargetFeet() {
        return TeleportTransitionController.getArrivalCameraFeet();
    }

    private static CameraFrame applyZoomShake(Vec3 pos, float yaw, float pitch, float frameTick, float envelope) {
        double easedEnvelope = Mth.clamp((float)envelope, (float)0.0f, (float)1.0f);
        double verticalWave = Math.sin((double)frameTick * 0.105) * 0.74 + Math.sin((double)frameTick * 0.052 + 1.6) * 0.26;
        double horizontalWave = Math.sin((double)frameTick * 0.105 + 2.1) * 0.78 + Math.sin((double)frameTick * 0.15 + 0.35) * 0.22;
        double pitchShake = (Mth.clamp((double)verticalWave, (double)-1.0, (double)1.0) - 1.0) * 0.5 * 1.05 * easedEnvelope;
        double yawShake = horizontalWave * 0.15 * easedEnvelope;
        float shakenYaw = yaw + (float)yawShake;
        float shakenPitch = Mth.clamp((float)(pitch + (float)pitchShake), (float)-90.0f, (float)90.0f);
        return new CameraFrame(pos, shakenYaw, shakenPitch);
    }

    private static double getStartTravelAltitude() {
        return TeleportTransitionController.getZoomOutStageAltitude(2) + TeleportTransitionController.getZoomStageGlideHeight();
    }

    private static double getTargetTravelAltitude() {
        return TeleportTransitionController.getZoomInStageAltitude(2) + TeleportTransitionController.getZoomStageGlideHeight();
    }

    private static double getZoomOutStageAltitude(int index) {
        return TeleportConfig.getZoomOutStageHeights(TeleportTransitionController.getStartZoomDimension())[index];
    }

    private static double getZoomInStageAltitude(int index) {
        return TeleportConfig.getZoomInStageHeights(TeleportTransitionController.getCurrentZoomDimension())[index];
    }

    private static TeleportConfig.ZoomDimension getStartZoomDimension() {
        return TeleportConfig.ZoomDimension.fromLevel(startDimension);
    }

    private static TeleportConfig.ZoomDimension getCurrentZoomDimension() {
        Minecraft client = Minecraft.getInstance();
        ResourceKey dimension = client.level == null ? startDimension : client.level.dimension();
        return TeleportConfig.ZoomDimension.fromLevel((ResourceKey<Level>)dimension);
    }

    private static double pullAltitude(float frameTick) {
        int firstStage = TeleportTransitionController.getPullStageTick(0);
        int secondStage = TeleportTransitionController.getPullStageTick(1);
        int thirdStage = TeleportTransitionController.getPullStageTick(2);
        int pullEnd = TeleportTransitionController.getPullMotionEndTick();
        if (frameTick < (float)secondStage) {
            return TeleportTransitionController.departingAltitude(TeleportTransitionController.getZoomOutStageAltitude(0), frameTick, firstStage, secondStage);
        }
        if (frameTick < (float)thirdStage) {
            return TeleportTransitionController.departingAltitude(TeleportTransitionController.getZoomOutStageAltitude(1), frameTick, secondStage, thirdStage);
        }
        return TeleportTransitionController.departingFinalAltitude(TeleportTransitionController.getZoomOutStageAltitude(2), frameTick, thirdStage, pullEnd);
    }

    private static double pushAltitude(float frameTick) {
        int pushMotionStart = TeleportTransitionController.getPushMotionStartTick();
        int firstStage = TeleportTransitionController.getPushStageTick(1);
        int secondStage = TeleportTransitionController.getPushStageTick(2);
        int pushEnd = TeleportTransitionController.getEnterHoldStartTick();
        if (frameTick < (float)firstStage) {
            return TeleportTransitionController.arrivingAltitude(TeleportTransitionController.getZoomInStageAltitude(2), frameTick, pushMotionStart, pushMotionStart + TeleportTransitionController.getZoomInStageTickLength(0));
        }
        if (frameTick < (float)secondStage) {
            return TeleportTransitionController.arrivingAltitude(TeleportTransitionController.getZoomInStageAltitude(1), frameTick, firstStage, secondStage);
        }
        return TeleportTransitionController.arrivingAltitude(TeleportTransitionController.getZoomInStageAltitude(0), frameTick, secondStage, pushEnd);
    }

    private static double departingAltitude(double altitude, float frameTick, int startTick, int endTick) {
        return altitude + TeleportTransitionController.zoomStageGlideProgressBetween(frameTick, startTick, endTick) * TeleportTransitionController.getZoomStageGlideHeight();
    }

    private static double arrivingAltitude(double altitude, float frameTick, int startTick, int endTick) {
        return altitude + (1.0 - TeleportTransitionController.zoomStageGlideProgressBetween(frameTick, startTick, endTick)) * TeleportTransitionController.getZoomStageGlideHeight();
    }

    private static double departingFinalAltitude(double altitude, float frameTick, int startTick, int endTick) {
        return altitude + TeleportTransitionController.finalZoomOutGlideProgressBetween(frameTick, startTick, endTick) * TeleportTransitionController.getZoomStageGlideHeight();
    }

    private static double zoomStageGlideProgressBetween(float frameTick, int startTick, int endTick) {
        float progress = (frameTick - (float)startTick) / Math.max(1.0f, (float)(endTick - startTick));
        return TeleportTransitionController.linearThenEaseOut(progress);
    }

    private static double finalZoomOutGlideProgressBetween(float frameTick, int startTick, int endTick) {
        float progress = (frameTick - (float)startTick) / Math.max(1.0f, (float)(endTick - startTick));
        return TeleportTransitionController.longEaseOut(progress);
    }

    private static double linearThenEaseOut(float value) {
        double x = Mth.clamp((float)value, (float)0.0f, (float)1.0f);
        return TeleportTransitionController.hermiteEaseOutFromLinear(x, 0.64, 1.08);
    }

    private static double longEaseOut(float value) {
        double x = Mth.clamp((float)value, (float)0.0f, (float)1.0f);
        return TeleportTransitionController.hermiteEaseOutFromLinear(x, 0.36, 1.0);
    }

    private static float perspectiveHorizontalProgress(float progress) {
        return TeleportTransitionController.easeOutCubic(progress);
    }

    private static float firstPersonEnterBodyHeightProgress(float progress) {
        return (float)TeleportTransitionController.linearThenEaseOut(progress);
    }

    private static Vec3 lerpPerspectiveCameraPosition(Vec3 source, Vec3 target, float verticalProgress, float horizontalProgress) {
        double x = Mth.lerp((double)Mth.clamp((float)horizontalProgress, (float)0.0f, (float)1.0f), (double)source.x, (double)target.x);
        double y = Mth.lerp((double)Mth.clamp((float)verticalProgress, (float)0.0f, (float)1.0f), (double)source.y, (double)target.y);
        double z = Mth.lerp((double)Mth.clamp((float)horizontalProgress, (float)0.0f, (float)1.0f), (double)source.z, (double)target.z);
        return new Vec3(x, y, z);
    }

    private static double hermiteEaseOutFromLinear(double x, double decelStart, double linearSpeed) {
        if (x <= decelStart) {
            return x * linearSpeed;
        }
        double t = (x - decelStart) / (1.0 - decelStart);
        double t2 = t * t;
        double t3 = t2 * t;
        double y0 = decelStart * linearSpeed;
        double m0 = linearSpeed * (1.0 - decelStart);
        double h00 = 2.0 * t3 - 3.0 * t2 + 1.0;
        double h10 = t3 - 2.0 * t2 + t;
        double h01 = -2.0 * t3 + 3.0 * t2;
        return Mth.clamp((double)(h00 * y0 + h10 * m0 + h01), (double)0.0, (double)1.0);
    }

    private static double glideProgress(float value) {
        return Mth.clamp((float)value, (float)0.0f, (float)1.0f);
    }

    private static float pulseAfter(float tick, float startTick) {
        float age = tick - startTick;
        if (age < 0.0f || age > 7.0f) {
            return 0.0f;
        }
        float x = age / 7.0f;
        return (1.0f - x) * (1.0f - x);
    }

    private static float smoothStep(float value) {
        float x = Mth.clamp((float)value, (float)0.0f, (float)1.0f);
        return x * x * (3.0f - 2.0f * x);
    }

    private static float lerpFloat(float from, float to, float progress) {
        float x = Mth.clamp((float)progress, (float)0.0f, (float)1.0f);
        return from + (to - from) * x;
    }

    private static float bodyLookProgress(float progress) {
        return TeleportTransitionController.easeOutCubic(Mth.clamp((float)(progress * 1.5f), (float)0.0f, (float)1.0f));
    }

    private static float easeOutCubic(float progress) {
        float x = Mth.clamp((float)progress, (float)0.0f, (float)1.0f);
        float inverse = 1.0f - x;
        return 1.0f - inverse * inverse * inverse;
    }

    private static float lerpDegrees(float from, float to, float progress) {
        return from + TeleportTransitionController.wrapDegrees(to - from) * Mth.clamp((float)progress, (float)0.0f, (float)1.0f);
    }

    private static float wrapDegrees(float value) {
        float wrapped = value % 360.0f;
        if (wrapped >= 180.0f) {
            wrapped -= 360.0f;
        }
        if (wrapped < -180.0f) {
            wrapped += 360.0f;
        }
        return wrapped;
    }

    private static boolean isCameraStepSoundTick(int tick) {
        int firstPullStep = TeleportTransitionController.getPullStageTick(0);
        int secondPullStep = TeleportTransitionController.getPullStageTick(1);
        int thirdPullStep = TeleportTransitionController.getPullStageTick(2);
        int firstPushStep = TeleportTransitionController.getPushStageTick(1);
        int secondPushStep = TeleportTransitionController.getPushStageTick(2);
        int thirdPushStep = TeleportTransitionController.getEnterHoldStartTick();
        return tick == firstPullStep || tick == secondPullStep || tick == thirdPullStep || tick == firstPushStep || tick == secondPushStep || tick == thirdPushStep;
    }

    private static int getPullStageTick(int stageIndex) {
        int[] stageTicks = TeleportConfig.getZoomOutStageTicks();
        int offset = 0;
        for (int i = 0; i < stageIndex && i < stageTicks.length; ++i) {
            offset += stageTicks[i];
        }
        return TeleportTransitionController.getPullStartTick() + offset;
    }

    private static int getPushStageTick(int stageIndex) {
        int[] stageTicks = TeleportConfig.getZoomInStageTicks();
        int offset = 0;
        for (int i = 0; i < stageIndex && i < stageTicks.length; ++i) {
            offset += stageTicks[i];
        }
        return TeleportTransitionController.getPushStartTick() + offset;
    }

    private static int getZoomInStageTickLength(int stageIndex) {
        int[] stageTicks = TeleportConfig.getZoomInStageTicks();
        if (stageIndex >= 0 && stageIndex < stageTicks.length) {
            return Math.max(1, stageTicks[stageIndex]);
        }
        return 1;
    }

    private static void playStepSound(Minecraft client, int tick) {
        if (!TeleportTransitionController.isCameraStepSoundTick(tick)) {
            return;
        }
        if (TeleportConfig.isCustomSoundsEnabled()) {
            TeleportTransitionController.playUiSound(client, TeleportTransitionController.getCustomStepSound(tick), (float)TeleportConfig.getCustomSoundVolume(), 1.0f);
            return;
        }
        TeleportTransitionController.playUiSound(client, SoundEvents.UI_BUTTON_CLICK.value(), TeleportTransitionController.minecraftSoundVolume(0.86f), 1.0f);
    }

    private static SoundEvent getCustomStepSound(int tick) {
        if (tick == TeleportTransitionController.getPullStageTick(2)) {
            return TeleportSounds.ZOOM_OUT_LONG;
        }
        if (tick == TeleportTransitionController.getPushStageTick(1) || tick == TeleportTransitionController.getPushStageTick(2)) {
            return TeleportSounds.ZOOM_IN_SHORT;
        }
        if (tick == TeleportTransitionController.getEnterHoldStartTick()) {
            return TeleportSounds.ZOOM_IN_LONG;
        }
        return TeleportSounds.ZOOM_OUT_SHORT;
    }

    private static void playTravelSound(Minecraft client) {
        if (TeleportConfig.isCustomSoundsEnabled()) {
            activeTravelSound = new FadingTravelSound((float)TeleportConfig.getCustomSoundVolume());
            client.getSoundManager().play((SoundInstance)activeTravelSound);
            return;
        }
        TeleportTransitionController.playUiSound(client, SoundEvents.AMBIENT_UNDERWATER_ENTER, TeleportTransitionController.minecraftSoundVolume(0.48f), 1.0f);
    }

    private static void playBodyTransitionSound(Minecraft client, boolean enteringPlayer) {
        if (TeleportConfig.isCustomSoundsEnabled()) {
            TeleportTransitionController.playUiSound(client, enteringPlayer ? TeleportSounds.CAMERA_IN : TeleportSounds.CAMERA_OUT, (float)TeleportConfig.getCustomSoundVolume(), 1.0f);
            return;
        }
        for (int i = 0; i < 3; ++i) {
            TeleportTransitionController.playUiSound(client, SoundEvents.ARMOR_EQUIP_LEATHER.value(), TeleportTransitionController.minecraftSoundVolume(1.0f), 0.8f);
        }
    }

    private static float minecraftSoundVolume(float baseVolume) {
        return baseVolume * (float)TeleportConfig.getMinecraftSoundVolume();
    }

    private static void playUiSound(Minecraft client, SoundEvent sound, float volume, float pitch) {
        client.getSoundManager().play((SoundInstance)SimpleSoundInstance.forUI((SoundEvent)sound, (float)pitch, (float)volume));
    }

    private static void fadeCustomTravelSound() {
        if (activeTravelSound != null) {
            activeTravelSound.fadeOut(30);
        }
    }

    private static void stopCustomTravelSound(Minecraft client) {
        if (activeTravelSound != null) {
            client.getSoundManager().stop((SoundInstance)activeTravelSound);
            activeTravelSound = null;
        }
    }

    private static float cubicBezierEase(float progress) {
        float x;
        float t = x = Mth.clamp((float)progress, (float)0.0f, (float)1.0f);
        for (int i = 0; i < 5; ++i) {
            float currentX = TeleportTransitionController.cubicBezier(t, 0.55f, 0.55f);
            float derivative = TeleportTransitionController.cubicBezierDerivative(t, 0.55f, 0.55f);
            if (Math.abs(derivative) < 1.0E-4f) break;
            t = Mth.clamp((float)(t - (currentX - x) / derivative), (float)0.0f, (float)1.0f);
        }
        return TeleportTransitionController.cubicBezier(t, 0.0f, 1.0f);
    }

    private static float cubicBezier(float t, float control1, float control2) {
        float oneMinusT = 1.0f - t;
        return 3.0f * oneMinusT * oneMinusT * t * control1 + 3.0f * oneMinusT * t * t * control2 + t * t * t;
    }

    private static float cubicBezierDerivative(float t, float control1, float control2) {
        float oneMinusT = 1.0f - t;
        return 3.0f * oneMinusT * oneMinusT * control1 + 6.0f * oneMinusT * t * (control2 - control1) + 3.0f * t * t * (1.0f - control2);
    }

    private static Vec3 getFeetPos(LocalPlayer player) {
        return new Vec3(player.getX(), player.getY(), player.getZ());
    }

    private static int getCommandSendTick() {
        if (skipTravel) {
            if (TeleportTransitionController.shouldSendExternalSkipTravelAckEarly()) {
                return TeleportTransitionController.getPullEndTick();
            }
            return TeleportTransitionController.getTravelStartTick();
        }
        if (TeleportTransitionController.shouldUseNormalChunkHandoff()) {
            return TeleportTransitionController.getPullEndTick();
        }
        if (plannedTargetFeetStable && plannedTargetFeet != null) {
            return TeleportTransitionController.getTravelStartTick() + TeleportTransitionController.getCommandSendTravelHoldTicks();
        }
        return TeleportTransitionController.getPullEndTick();
    }

    private static int getCommandSendTravelHoldTicks() {
        return Mth.clamp((int)(travelTicks / 2), (int)10, (int)24);
    }

    private static boolean shouldSendExternalSkipTravelAckEarly() {
        return transitionSource == 1 && plannedTargetFeetStable && plannedTargetFeet != null;
    }

    private static boolean shouldStartNormalChunkHandoff() {
        return !skipTravel && plannedTargetFeetStable && plannedTargetFeet != null && TeleportTransitionController.isSameDimensionTarget() && !TeleportTransitionController.isDistantTerrainAvailable();
    }

    private static boolean shouldUseNormalChunkHandoff() {
        return normalChunkHandoffEnabled;
    }

    private static boolean isSameDimensionTarget() {
        return plannedTargetDimensionId == null || startDimension != null && plannedTargetDimensionId.equals(DimensionIds.fromResourceKey(startDimension));
    }

    private static void updateNormalChunkHandoff(Minecraft client) {
        boolean minimumWaitElapsed;
        if (!TeleportTransitionController.shouldUseNormalChunkHandoff() || normalChunkHandoffReady || !commandSent || ticks < TeleportTransitionController.getBaseTravelStartTick()) {
            return;
        }
        if (actualTargetFeet == null) {
            TeleportTransitionController.extendOrReleaseNormalChunkHandoff(client);
            return;
        }
        if (normalChunkHandoffArrivalTick == Integer.MIN_VALUE) {
            normalChunkHandoffArrivalTick = ticks;
        }
        boolean bl = minimumWaitElapsed = ticks - normalChunkHandoffArrivalTick >= 8;
        if (minimumWaitElapsed && TeleportTransitionController.areArrivalChunksReady(client)) {
            normalChunkHandoffReady = true;
            TeleportTransitionController.forceTerrainRefresh(client);
            LOGGER.info("TA normal chunk handoff ready: delayTicks={} targetFeet={}", (Object)normalChunkHandoffDelayTicks, (Object)actualTargetFeet);
            return;
        }
        TeleportTransitionController.extendOrReleaseNormalChunkHandoff(client);
    }

    private static void extendOrReleaseNormalChunkHandoff(Minecraft client) {
        if (normalChunkHandoffDelayTicks >= 80) {
            normalChunkHandoffReady = true;
            TeleportTransitionController.forceTerrainRefresh(client);
            LOGGER.info("TA normal chunk handoff timeout: delayTicks={} targetFeet={}", (Object)normalChunkHandoffDelayTicks, (Object)TeleportTransitionController.getBestTargetFeet());
            return;
        }
        ++totalTicks;
        if (++normalChunkHandoffDelayTicks == 1 || normalChunkHandoffDelayTicks % 4 == 0) {
            TeleportTransitionController.forceTerrainRefresh(client);
        }
    }

    private static boolean areArrivalChunksReady(Minecraft client) {
        int centerChunkZ;
        Vec3 feet = TeleportTransitionController.getArrivalCameraFeet();
        if (client == null || client.level == null || feet == null) {
            return false;
        }
        int centerChunkX = Mth.floor((double)feet.x) >> 4;
        if (!client.level.hasChunk(centerChunkX, centerChunkZ = Mth.floor((double)feet.z) >> 4)) {
            return false;
        }
        if (!TeleportTransitionController.isArrivalSurfaceReady(client)) {
            return false;
        }
        int radius = TeleportTransitionController.getNormalChunkHandoffReadyRadius(client);
        int loaded = 0;
        int total = 0;
        for (int dz = -radius; dz <= radius; ++dz) {
            for (int dx = -radius; dx <= radius; ++dx) {
                ++total;
                if (!client.level.hasChunk(centerChunkX + dx, centerChunkZ + dz)) continue;
                ++loaded;
            }
        }
        int required = Math.max(1, (int)Math.ceil((double)total * (double)0.82f));
        return loaded >= required;
    }

    private static int getNormalChunkHandoffReadyRadius(Minecraft client) {
        int renderDistance = client.options == null ? 12 : client.options.getEffectiveRenderDistance();
        return Math.min(4, Math.max(2, renderDistance / 3));
    }

    private static int getRetainedDepartingChunkRadius(Minecraft client) {
        int renderDistance = client == null || client.options == null ? 12 : client.options.getEffectiveRenderDistance();
        return Math.max(2, renderDistance + 2);
    }

    private static long chunkKey(int chunkX, int chunkZ) {
        return (long)chunkX << 32 ^ (long)chunkZ & 0xFFFFFFFFL;
    }

    private static int chunkKeyX(long key) {
        return (int)(key >> 32);
    }

    private static int chunkKeyZ(long key) {
        return (int)key;
    }

    private static boolean isFallbackTerrainMode() {
        return false;
    }

    private static boolean isDistantTerrainAvailable() {
        return TeleportTransitionController.isVoxyTerrainAvailable() || TeleportTransitionController.isDistantHorizonsTerrainAvailable() || TeleportTransitionController.isBobbyTerrainAvailable();
    }

    private static boolean isVoxyTerrainAvailable() {
        return VOXY_LOADED && VoxyCompat.isRenderingEnabled();
    }

    private static boolean isDistantHorizonsTerrainAvailable() {
        return DISTANT_HORIZONS_LOADED && DistantHorizonsCompat.isRenderingEnabled();
    }

    private static boolean isBobbyTerrainAvailable() {
        return BOBBY_LOADED && BobbyCompat.isRenderingEnabled();
    }

    private static int getFallbackTerrainHideTick() {
        return TeleportTransitionController.getFallbackTerrainFadeOutEndTick();
    }

    private static int getFallbackTerrainShowTick() {
        return TeleportTransitionController.getFallbackTerrainFadeInStartTick();
    }

    private static boolean isFallbackTerrainFadeOutActive() {
        return ticks >= TeleportTransitionController.getFallbackTerrainFadeOutStartTick() && ticks < TeleportTransitionController.getFallbackTerrainFadeOutEndTick();
    }

    private static int getBodyHoldTicks() {
        return TeleportConfig.getBodyGlideTicks();
    }

    private static int getPullTicks() {
        int total = 0;
        for (int stageTick : TeleportConfig.getZoomOutStageTicks()) {
            total += stageTick;
        }
        return Math.max(1, total);
    }

    private static int getPushTicks() {
        int total = 0;
        for (int stageTick : TeleportConfig.getZoomInStageTicks()) {
            total += stageTick;
        }
        return Math.max(1, total);
    }

    private static int getFixedTotalTicks() {
        return TeleportTransitionController.getExitBodyTicks() + TeleportTransitionController.getBodyHoldTicks() + TeleportTransitionController.getPullTicks() + TeleportTransitionController.getPreTravelWaitTicks() + TeleportTransitionController.getPrePushWaitTicks() + TeleportTransitionController.getPushTicks() + TeleportTransitionController.getBodyHoldTicks() + TeleportTransitionController.getEnterBodyTicks();
    }

    private static int getExitBodyTicks() {
        return Math.max(1, exitBodyTicks);
    }

    private static int getEnterBodyTicks() {
        return Math.max(1, enterBodyTicks);
    }

    private static void updateBodyTransitionTicks(Minecraft client) {
        int ticks;
        Vec3 exitTarget = startFeet == null ? null : startFeet.add(0.0, TeleportTransitionController.getStartBodyCameraHeight(), 0.0);
        exitBodyTicks = ticks = TeleportTransitionController.calculateBodyTransitionTicks(client, startEye, exitTarget);
        enterBodyTicks = ticks;
    }

    private static int calculateBodyTransitionTicks(Minecraft client, Vec3 source, Vec3 target) {
        if (!TeleportTransitionController.shouldRespectPreviousCameraPosition() || source == null || target == null) {
            return 12;
        }
        double verticalDistance = Math.abs(target.y - source.y);
        double horizontalDistance = Math.hypot(target.x - source.x, target.z - source.z);
        double distance = verticalDistance + horizontalDistance;
        double baseDistance = Math.abs(TeleportTransitionController.getStartBodyCameraHeight() - TeleportTransitionController.getCameraEyeHeight(client));
        if (!Double.isFinite(baseDistance) || baseDistance < 0.5) {
            baseDistance = Math.max(0.5, TeleportTransitionController.getStartBodyCameraHeight());
        }
        if (!Double.isFinite(distance) || distance <= baseDistance) {
            return 12;
        }
        int scaledTicks = Mth.ceil((double)(12.0 * distance / baseDistance));
        return Mth.clamp((int)scaledTicks, (int)12, (int)24);
    }

    private static double getBodyCameraHeight() {
        return TeleportConfig.getBodyCameraHeight();
    }

    private static double getBodyGlideHeight() {
        return TeleportConfig.getBodyGlideHeight();
    }

    private static double getZoomStageGlideHeight() {
        return TeleportConfig.getZoomStageGlideHeight();
    }

    private static int getPullStartTick() {
        return TeleportTransitionController.getExitBodyTicks() + TeleportTransitionController.getBodyHoldTicks();
    }

    private static int getPullEndTick() {
        return TeleportTransitionController.getPullStartTick() + TeleportTransitionController.getPullTicks();
    }

    private static int getPreTravelWaitTicks() {
        if (skipTravel) {
            return 20;
        }
        return 10 + (TeleportTransitionController.shouldUseNormalChunkHandoff() ? normalChunkHandoffDelayTicks : 0);
    }

    private static int getBasePreTravelWaitTicks() {
        return skipTravel ? 20 : 10;
    }

    private static int getPrePushWaitTicks() {
        return skipTravel ? 20 : 10;
    }

    private static int getTravelStartTick() {
        return TeleportTransitionController.getPullEndTick() + TeleportTransitionController.getPreTravelWaitTicks();
    }

    private static int getBaseTravelStartTick() {
        return TeleportTransitionController.getPullEndTick() + TeleportTransitionController.getBasePreTravelWaitTicks();
    }

    private static int getTravelEndTick() {
        return TeleportTransitionController.getTravelStartTick() + travelTicks;
    }

    private static int getPushStartTick() {
        return TeleportTransitionController.getTravelEndTick() + TeleportTransitionController.getPrePushWaitTicks() + (skipTravel ? skipTravelArrivalDelayTicks : 0);
    }

    private static int getPullMotionEndTick() {
        if (skipTravel) {
            return TeleportTransitionController.getPullEndTick();
        }
        return TeleportTransitionController.shouldUseNormalChunkHandoff() ? TeleportTransitionController.getBaseTravelStartTick() : TeleportTransitionController.getTravelStartTick();
    }

    private static int getPushMotionStartTick() {
        return skipTravel ? TeleportTransitionController.getPushStartTick() : TeleportTransitionController.getTravelEndTick();
    }

    private static int getEnterHoldStartTick() {
        return TeleportTransitionController.getPushStartTick() + TeleportTransitionController.getPushTicks();
    }

    private static int getEnterStartTick() {
        return TeleportTransitionController.getEnterHoldStartTick() + TeleportTransitionController.getBodyHoldTicks();
    }

    private static int getFallbackTerrainFadeOutStartTick() {
        return TeleportTransitionController.getTravelStartTick() + 4;
    }

    private static int getFallbackTerrainFadeOutEndTick() {
        return TeleportTransitionController.getFallbackTerrainFadeOutStartTick() + 10;
    }

    private static int getFallbackTerrainFadeInStartTick() {
        return TeleportTransitionController.getTravelEndTick() - 10;
    }

    private static int getFallbackTerrainFadeInEndTick() {
        return TeleportTransitionController.getTravelEndTick();
    }

    private static int getVanillaTerrainFadeOutStartTick() {
        return TeleportTransitionController.getPullEndTick();
    }

    private static int getVanillaTerrainFadeOutEndTick() {
        return TeleportTransitionController.getPullEndTick() + 10;
    }

    private static int getVanillaTerrainFadeInStartTick() {
        return TeleportTransitionController.getTravelEndTick();
    }

    private static int getVanillaTerrainFadeInEndTick() {
        return TeleportTransitionController.getVanillaTerrainFadeInStartTick() + 10;
    }

    private static float getDistanceOrderTowardPoint(double sectionCenterX, double sectionCenterZ, Vec3 centerFeet, Vec3 directionFeet) {
        double radius = TeleportTransitionController.getFallbackTerrainRenderRadius();
        double centerDistance = TeleportTransitionController.horizontalDistance(centerFeet, directionFeet);
        double distance = TeleportTransitionController.horizontalDistance(sectionCenterX, sectionCenterZ, directionFeet);
        double min = Math.max(0.0, centerDistance - radius);
        double max = centerDistance + radius;
        return (float)Mth.clamp((double)((distance - min) / Math.max(1.0, max - min)), (double)0.0, (double)1.0);
    }

    private static float getShaderMaskSweepThreshold(float order) {
        return Mth.clamp((float)order, (float)0.0f, (float)1.0f) * 0.78f;
    }

    private static double horizontalDistance(Vec3 from, Vec3 to) {
        if (from == null || to == null) {
            return 0.0;
        }
        double dx = from.x - to.x;
        double dz = from.z - to.z;
        return Math.sqrt(dx * dx + dz * dz);
    }

    private static double horizontalDistance(double x, double z, Vec3 to) {
        if (to == null) {
            return 0.0;
        }
        double dx = x - to.x;
        double dz = z - to.z;
        return Math.sqrt(dx * dx + dz * dz);
    }

    private static double getFallbackTerrainRenderRadius() {
        Minecraft client = Minecraft.getInstance();
        int renderDistance = client.options == null ? 12 : client.options.getEffectiveRenderDistance();
        return Math.max(32.0, (double)renderDistance * 16.0);
    }

    private static int getTerrainVisibilityRefreshSectionStep(Minecraft client) {
        int renderDistance = client.options == null ? 12 : client.options.getEffectiveRenderDistance();
        return Math.max(4, renderDistance / 2);
    }

    private static void syncCameraStateBeforeRelease(Minecraft client) {
        if (client == null || client.player == null) {
            return;
        }
        TeleportTransitionController.captureEnterBodyTarget(client.player);
        if (!TeleportTransitionController.shouldRespectPreviousCameraPosition()) {
            TeleportTransitionController.syncPlayerLook(client.player, enterBodyTargetYaw, enterBodyTargetPitch);
        }
        TeleportTransitionController.syncPlayerInterpolationState(client.player);
    }

    private static void syncPlayerInterpolationState(LocalPlayer player) {
        if (player != null) {
            player.resetPos();
        }
    }

    private static void syncPlayerLook(LocalPlayer player, float yaw, float pitch) {
        float clampedPitch = Mth.clamp((float)pitch, (float)-90.0f, (float)90.0f);
        player.setYRot(yaw);
        player.setXRot(clampedPitch);
        player.setYHeadRot(yaw);
        player.setYBodyRot(yaw);
    }

    private static void restoreCameraTypeForEnterBody(Minecraft client) {
        if (cameraTypeRestoredForEnter || !TeleportTransitionController.shouldRespectPreviousCameraPosition() || ticks < TeleportTransitionController.getEnterStartTick()) {
            return;
        }
        TeleportTransitionController.restoreCameraType(client);
        cameraTypeRestoredForEnter = true;
    }

    private static void restoreCameraType(Minecraft client) {
        if (previousCameraType != null) {
            client.options.setCameraType(previousCameraType);
        }
    }

    private static void runPendingAction() {
        if (pendingAction != null) {
            pendingAction.run();
        }
    }

    private static void updateHudVisibility(Minecraft client) {
        if (!hudSuppressed && ticks >= 8) {
            client.options.hideGui = true;
            hudSuppressed = true;
        }
        if (hudSuppressed && cameraReleased) {
            client.options.hideGui = previousHudHidden;
            hudSuppressed = false;
        }
    }

    private static void updateHardCutTerrainState(Minecraft client) {
        boolean active = TeleportTransitionController.shouldUseHardCutFallbackTerrainCulling();
        if (active == previousHardCutCullingActive) {
            return;
        }
        previousHardCutCullingActive = active;
        TeleportTransitionController.resetVisibilityRefreshState();
        if (client.levelRenderer != null) {
            client.levelRenderer.allChanged();
        }
        SodiumCompat.scheduleTerrainUpdate();
    }

    static void resetState(Minecraft client) {
        if (previousCameraType != null) {
            client.options.setCameraType(previousCameraType);
        } else {
            client.options.setCameraType(CameraType.FIRST_PERSON);
        }
        client.options.hideGui = previousHudHidden;
        pendingAction = null;
        previousCameraType = null;
        previousHudHidden = false;
        hudSuppressed = false;
        ticks = 0;
        totalTicks = 0;
        cameraReleased = false;
        cameraTypeRestoredForEnter = false;
    }

    private static void clear(Minecraft client) {
        TeleportTransitionController.releaseRetainedDepartingChunks(client);
        TeleportTransitionController.restoreCameraType(client);
        
        SodiumCompat.endTransition();
        TeleportTransitionController.stopCustomTravelSound(client);
        client.options.hideGui = previousHudHidden;
        pendingAction = null;
        startFeet = null;
        startEye = null;
        startCameraEyeOffset = null;
        plannedTargetFeet = null;
        plannedTargetFeetStable = true;
        actualTargetFeet = null;
        arrivalCameraFeet = null;
        lastObservedPlayerFeet = null;
        enterBodyTargetEye = null;
        plannedTargetDimensionId = null;
        startDimension = null;
        previousCameraType = null;
        previousHudHidden = false;
        
        startYaw = 0.0f;
        startPitch = 0.0f;
        startPlayerYaw = 0.0f;
        exitBodyTicks = 12;
        enterBodyTicks = 12;
        enterBodyTargetYaw = 0.0f;
        enterBodyTargetPitch = 0.0f;
        ticks = 0;
        travelTicks = 40;
        transitionSource = 0;
        skipTravel = false;
        normalChunkHandoffEnabled = false;
        normalChunkHandoffReady = false;
        normalChunkHandoffDelayTicks = 0;
        normalChunkHandoffArrivalTick = Integer.MIN_VALUE;
        totalTicks = TeleportTransitionController.getFixedTotalTicks() + 40;
        commandSent = false;
        cameraReleasePrepared = false;
        cameraReleased = false;
        cameraTypeRestoredForEnter = false;
        travelSoundStarted = false;
        travelSoundFaded = false;
        hudSuppressed = false;
        previousHardCutCullingActive = false;
        arrivalTerrainRefreshTicks = 0;
        startSurfaceY = Double.NaN;
        arrivalSurfaceY = Double.NaN;
        startBodyCameraHeight = Double.NaN;
        startBodyGlideHeight = Double.NaN;
        currentCameraPos = null;
        postReleaseCameraFrame = null;
        postReleaseCameraStartFrame = null;
        postReleaseCameraEndFrame = null;
        postReleaseCameraBlendStartTick = 0;
        postReleaseCameraBlendTicks = 0;
        postReleaseCameraOverrideFrames = 0;
        postReleaseCameraOverrideStableFrames = 0;
        TeleportTransitionController.resetArrivalBodyCameraHeights();
        TeleportTransitionController.resetVisibilityRefreshState();
    }

    private static void releaseRetainedDepartingChunks(Minecraft client) {
        if (retainedDepartingChunks.isEmpty()) {
            return;
        }
        if (client != null && client.level != null) {
            int playerChunkX = client.player == null ? Integer.MIN_VALUE : Mth.floor((double)client.player.getX()) >> 4;
            int playerChunkZ = client.player == null ? Integer.MIN_VALUE : Mth.floor((double)client.player.getZ()) >> 4;
            int keepRadius = client.options == null ? 12 : client.options.getEffectiveRenderDistance() + 1;
            for (long key : retainedDepartingChunks) {
                int chunkX = TeleportTransitionController.chunkKeyX(key);
                int chunkZ = TeleportTransitionController.chunkKeyZ(key);
                if (playerChunkX != Integer.MIN_VALUE && Math.abs(chunkX - playerChunkX) <= keepRadius && Math.abs(chunkZ - playerChunkZ) <= keepRadius) continue;
                // Light chunk release not available in this API version
            }
        }
        retainedDepartingChunks.clear();
    }

    private static void resetVisibilityRefreshState() {
        lastVisibilitySectionX = Integer.MIN_VALUE;
        lastVisibilitySectionY = Integer.MIN_VALUE;
        lastVisibilitySectionZ = Integer.MIN_VALUE;
    }

    static {
        plannedTargetFeetStable = true;
        exitBodyTicks = 12;
        enterBodyTicks = 12;
        travelTicks = 40;
        totalTicks = TeleportTransitionController.getFixedTotalTicks() + 40;
        transitionSource = 0;
        normalChunkHandoffArrivalTick = Integer.MIN_VALUE;
        lastVisibilitySectionX = Integer.MIN_VALUE;
        lastVisibilitySectionY = Integer.MIN_VALUE;
        lastVisibilitySectionZ = Integer.MIN_VALUE;
        retainedDepartingChunks = new HashSet<Long>();
        startSurfaceY = Double.NaN;
        arrivalSurfaceY = Double.NaN;
        startBodyCameraHeight = Double.NaN;
        startBodyGlideHeight = Double.NaN;
        arrivalBodyCameraHeight = Double.NaN;
        arrivalBodyGlideHeight = Double.NaN;
    }

    private record BodyCameraHeights(double cameraHeight, double glideHeight) {
    }

    public record CameraFrame(Vec3 pos, float yaw, float pitch) {
    }

    private static final class FadingTravelSound
    extends AbstractTickableSoundInstance {
        private final float baseVolume;
        private int fadeTicks;
        private int fadeTicksRemaining;

        private FadingTravelSound(float volume) {
            super(TeleportSounds.TELEPORT, SoundSource.PLAYERS, SoundInstance.createUnseededRandom());
            this.baseVolume = volume;
            this.volume = volume;
            this.pitch = 1.0f;
            this.looping = true;
            this.attenuation = SoundInstance.Attenuation.NONE;
        }

        private void fadeOut(int ticks) {
            this.fadeTicksRemaining = this.fadeTicks = Math.max(1, ticks);
        }

        public void tick() {
            if (this.fadeTicksRemaining <= 0) {
                return;
            }
            this.volume = this.baseVolume * ((float)this.fadeTicksRemaining / (float)this.fadeTicks);
            --this.fadeTicksRemaining;
            if (this.fadeTicksRemaining <= 0) {
                this.volume = 0.0f;
                this.stop();
                if (activeTravelSound == this) {
                    activeTravelSound = null;
                }
            }
        }
    }
}
