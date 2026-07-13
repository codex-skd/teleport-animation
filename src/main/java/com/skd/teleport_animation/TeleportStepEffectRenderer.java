package com.skd.teleport_animation;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public final class TeleportStepEffectRenderer {
    private static final double DEFAULT_FOV_DEGREES = 70.0;
    private static final double FALLBACK_MASK_CAMERA_HEIGHT = 150.0;
    private static final double CHUNK_SCREEN_MARGIN = 32.0;
    private static final int START_COLOR_CAPTURE_TICK = 30;
    private static int[] capturedStartMaskColor;

    private TeleportStepEffectRenderer() {
    }

    public static void render(GuiGraphicsExtractor context, float tickProgress) {
        float intensity;
        updateStartMaskColorCapture();
        boolean shaderScreenMaskOnly = TeleportTransitionController.shouldUseShaderScreenMaskOnly();
        float maskIntensity = TeleportTransitionController.getShaderScreenMaskIntensity(tickProgress);
        if (maskIntensity > 0.0f) {
            renderChunkScreenMask(context, tickProgress, maskIntensity);
        }
        if (shaderScreenMaskOnly) {
            intensity = TeleportTransitionController.getCameraMotionStepEffectIntensity(tickProgress);
        } else {
            intensity = TeleportTransitionController.getStepEffectIntensity(tickProgress);
            intensity = Math.max(intensity, TeleportTransitionController.getHudFadeOverlayIntensity(tickProgress));
        }
        renderStepFlash(context, intensity);
    }

    private static void renderStepFlash(GuiGraphicsExtractor context, float intensity) {
        if (intensity <= 0.0f) return;
        int flashAlpha = (int) (68.0f * intensity);
        context.fill(0, 0, context.guiWidth(), context.guiHeight(), argb(flashAlpha, 245, 245, 235));
    }

    private static void updateStartMaskColorCapture() {
        if (!TeleportTransitionController.isRunning()) {
            capturedStartMaskColor = null;
            return;
        }
        if (capturedStartMaskColor != null || !TeleportTransitionController.shouldUseShaderScreenMaskOnly() || TeleportTransitionController.getTicks() < 30) {
            return;
        }
        capturedStartMaskColor = getSkyMaskColor();
    }

    private static int[] getSkyMaskColor() {
        return new int[]{188, 197, 202};
    }

    private static void renderChunkScreenMask(GuiGraphicsExtractor context, float tickProgress, float maskIntensity) {
        TeleportTransitionController.CameraFrame frame = TeleportTransitionController.getCameraFrame(tickProgress);
        if (frame == null) return;
        double cameraY = 150.0;
        double worldHalfHeight = Math.tan(Math.toRadians(70.0) * 0.5) * cameraY;
        double worldHalfWidth = worldHalfHeight * (double) context.guiWidth() / Math.max(1.0, (double) context.guiHeight());
        double scaleX = (double) context.guiWidth() / (worldHalfWidth * 2.0);
        double scaleY = (double) context.guiHeight() / (worldHalfHeight * 2.0);
        double yawRadians = Math.toRadians(frame.yaw());
        double rightX = Math.cos(yawRadians);
        double rightZ = Math.sin(yawRadians);
        double forwardX = -Math.sin(yawRadians);
        double forwardZ = Math.cos(yawRadians);
        double minWorldX = Double.POSITIVE_INFINITY;
        double maxWorldX = Double.NEGATIVE_INFINITY;
        double minWorldZ = Double.POSITIVE_INFINITY;
        double maxWorldZ = Double.NEGATIVE_INFINITY;
        double[] screenWorldXs = new double[]{-worldHalfWidth - 32.0, worldHalfWidth + 32.0, worldHalfWidth + 32.0, -worldHalfWidth - 32.0};
        double[] screenWorldYs = new double[]{-worldHalfHeight - 32.0, -worldHalfHeight - 32.0, worldHalfHeight + 32.0, worldHalfHeight + 32.0};
        for (int i = 0; i < 4; i++) {
            double worldX = frame.pos().x + rightX * screenWorldXs[i] + forwardX * screenWorldYs[i];
            double worldZ = frame.pos().z + rightZ * screenWorldXs[i] + forwardZ * screenWorldYs[i];
            minWorldX = Math.min(minWorldX, worldX);
            maxWorldX = Math.max(maxWorldX, worldX);
            minWorldZ = Math.min(minWorldZ, worldZ);
            maxWorldZ = Math.max(maxWorldZ, worldZ);
        }
        int minChunkX = Mth.floor(minWorldX / 16.0) - 1;
        int maxChunkX = Mth.floor(maxWorldX / 16.0) + 1;
        int minChunkZ = Mth.floor(minWorldZ / 16.0) - 1;
        int maxChunkZ = Mth.floor(maxWorldZ / 16.0) + 1;
        int[] color = getSceneMaskColor();
        boolean shaderScreenMaskOnly = TeleportTransitionController.shouldUseShaderScreenMaskOnly();
        for (int chunkX = minChunkX; chunkX <= maxChunkX; chunkX++) {
            for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; chunkZ++) {
                float sectionOpacity;
                double chunkMinWorldX = (double) chunkX * 16.0;
                double chunkMinWorldZ = (double) chunkZ * 16.0;
                if (shaderScreenMaskOnly) {
                    sectionOpacity = TeleportTransitionController.getShaderScreenMaskSectionOpacity(chunkMinWorldX + 8.0, frame.pos().y, chunkMinWorldZ + 8.0);
                } else {
                    float visibility = TeleportTransitionController.getFallbackTerrainSectionVisibility(chunkMinWorldX + 8.0, frame.pos().y, chunkMinWorldZ + 8.0);
                    sectionOpacity = 1.0f - visibility;
                }
                float opacity = sectionOpacity * maskIntensity;
                if (opacity <= 0.001f) continue;
                int alpha = (int) (252.0f * opacity);
                drawProjectedChunkQuad(context, frame.pos(), chunkMinWorldX, chunkMinWorldZ, rightX, rightZ, forwardX, forwardZ, scaleX, scaleY, alpha, color);
            }
        }
    }

    private static void drawProjectedChunkQuad(GuiGraphicsExtractor context, Vec3 cameraPos, double minWorldX, double minWorldZ, double rightX, double rightZ, double forwardX, double forwardZ, double scaleX, double scaleY, int alpha, int[] color) {
        double maxWorldX = minWorldX + 16.0;
        double maxWorldZ = minWorldZ + 16.0;
        double[] worldXs = new double[]{minWorldX, maxWorldX, maxWorldX, minWorldX};
        double[] worldZs = new double[]{minWorldZ, minWorldZ, maxWorldZ, maxWorldZ};
        double[] screenXs = new double[4];
        double[] screenYs = new double[4];
        for (int i = 0; i < 4; i++) {
            double dx = worldXs[i] - cameraPos.x;
            double dz = worldZs[i] - cameraPos.z;
            double screenWorldX = dx * rightX + dz * rightZ;
            double screenWorldY = dx * forwardX + dz * forwardZ;
            screenXs[i] = (double) context.guiWidth() * 0.5 + screenWorldX * scaleX;
            screenYs[i] = (double) context.guiHeight() * 0.5 + screenWorldY * scaleY;
        }
        if (!isScreenBoundsValid(screenXs, screenYs, context.guiWidth(), context.guiHeight())) return;
        drawProjectedChunkScanlines(context, screenXs, screenYs, alpha, color);
    }

    private static boolean isScreenBoundsValid(double[] xs, double[] ys, int screenWidth, int screenHeight) {
        double minScreenX = Double.POSITIVE_INFINITY;
        double maxScreenX = Double.NEGATIVE_INFINITY;
        double minScreenY = Double.POSITIVE_INFINITY;
        double maxScreenY = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < 4; i++) {
            minScreenX = Math.min(minScreenX, xs[i]);
            maxScreenX = Math.max(maxScreenX, xs[i]);
            minScreenY = Math.min(minScreenY, ys[i]);
            maxScreenY = Math.max(maxScreenY, ys[i]);
        }
        int x1 = Math.max(0, Mth.floor(minScreenX));
        int y1 = Math.max(0, Mth.floor(minScreenY));
        int x2 = Math.min(screenWidth, Mth.ceil(maxScreenX));
        int y2 = Math.min(screenHeight, Mth.ceil(maxScreenY));
        return x2 > x1 && y2 > y1;
    }

    private static void drawProjectedChunkScanlines(GuiGraphicsExtractor context, double[] xs, double[] ys, int alpha, int[] color) {
        double minScreenY = Math.min(Math.min(ys[0], ys[1]), Math.min(ys[2], ys[3]));
        double maxScreenY = Math.max(Math.max(ys[0], ys[1]), Math.max(ys[2], ys[3]));
        int y1 = Math.max(0, Mth.floor(minScreenY));
        int y2 = Math.min(context.guiHeight(), Mth.ceil(maxScreenY));
        if (y2 <= y1) return;
        int fillColor = argb(alpha, color[0], color[1], color[2]);
        for (int y = y1; y < y2; y += 2) {
            double scanY = Math.min((double) y2 - 0.5, (double) y + 1.0);
            double[] intersections = new double[4];
            int count = 0;
            for (int i = 0; i < 4; i++) {
                int next = i + 1 & 3;
                double edgeY1 = ys[i];
                double edgeY2 = ys[next];
                if (!(edgeY1 <= scanY && edgeY2 > scanY) && !(edgeY2 <= scanY && edgeY1 > scanY)) continue;
                double t = (scanY - edgeY1) / (edgeY2 - edgeY1);
                intersections[count++] = xs[i] + (xs[next] - xs[i]) * t;
            }
            if (count < 2) continue;
            double left = Math.min(intersections[0], intersections[1]);
            double right = Math.max(intersections[0], intersections[1]);
            int x1 = Math.max(0, Mth.floor(left));
            int x2 = Math.min(context.guiWidth(), Mth.ceil(right));
            if (x2 <= x1) continue;
            context.fill(x1, y, x2, Math.min(y + 2, y2), fillColor);
        }
    }

    private static int[] getSceneMaskColor() {
        return capturedStartMaskColor == null ? getSkyMaskColor() : capturedStartMaskColor;
    }

    private static int argb(int alpha, int red, int green, int blue) {
        return clamp(alpha) << 24 | clamp(red) << 16 | clamp(green) << 8 | clamp(blue);
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}