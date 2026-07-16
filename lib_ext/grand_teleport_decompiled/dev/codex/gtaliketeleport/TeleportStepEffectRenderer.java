/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.navigation.ScreenRectangle
 *  net.minecraft.util.Mth
 *  net.minecraft.world.phys.Vec3
 */
package dev.codex.gtaliketeleport;

import dev.codex.gtaliketeleport.TeleportTransitionController;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenRectangle;
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

    public static void render(GuiGraphics context, float tickProgress) {
        float intensity;
        TeleportStepEffectRenderer.updateStartMaskColorCapture();
        boolean shaderScreenMaskOnly = TeleportTransitionController.shouldUseShaderScreenMaskOnly();
        float maskIntensity = TeleportTransitionController.getShaderScreenMaskIntensity(tickProgress);
        if (maskIntensity > 0.0f) {
            TeleportStepEffectRenderer.renderChunkScreenMask(context, tickProgress, maskIntensity);
        }
        if (shaderScreenMaskOnly) {
            intensity = TeleportTransitionController.getCameraMotionStepEffectIntensity(tickProgress);
        } else {
            intensity = TeleportTransitionController.getStepEffectIntensity(tickProgress);
            intensity = Math.max(intensity, TeleportTransitionController.getHudFadeOverlayIntensity(tickProgress));
        }
        TeleportStepEffectRenderer.renderStepFlash(context, intensity);
    }

    private static void renderStepFlash(GuiGraphics context, float intensity) {
        if (intensity <= 0.0f) {
            return;
        }
        int flashAlpha = (int)(68.0f * intensity);
        context.m_280509_(0, 0, context.m_280182_(), context.m_280206_(), TeleportStepEffectRenderer.argb(flashAlpha, 245, 245, 235));
    }

    private static void updateStartMaskColorCapture() {
        if (!TeleportTransitionController.isRunning()) {
            capturedStartMaskColor = null;
            return;
        }
        if (capturedStartMaskColor != null || !TeleportTransitionController.shouldUseShaderScreenMaskOnly() || TeleportTransitionController.getTicks() < 30) {
            return;
        }
        capturedStartMaskColor = TeleportStepEffectRenderer.getSkyMaskColor();
    }

    private static int[] getSkyMaskColor() {
        return new int[]{188, 197, 202};
    }

    private static void renderChunkScreenMask(GuiGraphics context, float tickProgress, float maskIntensity) {
        TeleportTransitionController.CameraFrame frame = TeleportTransitionController.getCameraFrame(tickProgress);
        if (frame == null) {
            return;
        }
        double cameraY = 150.0;
        double worldHalfHeight = Math.tan(Math.toRadians(70.0) * 0.5) * cameraY;
        double worldHalfWidth = worldHalfHeight * (double)context.m_280182_() / Math.max(1.0, (double)context.m_280206_());
        double scaleX = (double)context.m_280182_() / (worldHalfWidth * 2.0);
        double scaleY = (double)context.m_280206_() / (worldHalfHeight * 2.0);
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
        for (int i = 0; i < 4; ++i) {
            double worldX = frame.pos().f_82479_ + rightX * screenWorldXs[i] + forwardX * screenWorldYs[i];
            double worldZ = frame.pos().f_82481_ + rightZ * screenWorldXs[i] + forwardZ * screenWorldYs[i];
            minWorldX = Math.min(minWorldX, worldX);
            maxWorldX = Math.max(maxWorldX, worldX);
            minWorldZ = Math.min(minWorldZ, worldZ);
            maxWorldZ = Math.max(maxWorldZ, worldZ);
        }
        int minChunkX = Mth.m_14107_((double)(minWorldX / 16.0)) - 1;
        int maxChunkX = Mth.m_14107_((double)(maxWorldX / 16.0)) + 1;
        int minChunkZ = Mth.m_14107_((double)(minWorldZ / 16.0)) - 1;
        int maxChunkZ = Mth.m_14107_((double)(maxWorldZ / 16.0)) + 1;
        int[] color = TeleportStepEffectRenderer.getSceneMaskColor();
        boolean shaderScreenMaskOnly = TeleportTransitionController.shouldUseShaderScreenMaskOnly();
        for (int chunkX = minChunkX; chunkX <= maxChunkX; ++chunkX) {
            for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; ++chunkZ) {
                float sectionOpacity;
                double chunkMinWorldX = (double)chunkX * 16.0;
                double chunkMinWorldZ = (double)chunkZ * 16.0;
                if (shaderScreenMaskOnly) {
                    sectionOpacity = TeleportTransitionController.getShaderScreenMaskSectionOpacity(chunkMinWorldX + 8.0, frame.pos().f_82480_, chunkMinWorldZ + 8.0);
                } else {
                    float visibility = TeleportTransitionController.getFallbackTerrainSectionVisibility(chunkMinWorldX + 8.0, frame.pos().f_82480_, chunkMinWorldZ + 8.0);
                    sectionOpacity = 1.0f - visibility;
                }
                float opacity = sectionOpacity * maskIntensity;
                if (opacity <= 0.001f) continue;
                int alpha = (int)(252.0f * opacity);
                TeleportStepEffectRenderer.drawProjectedChunkQuad(context, frame.pos(), chunkMinWorldX, chunkMinWorldZ, rightX, rightZ, forwardX, forwardZ, scaleX, scaleY, alpha, color);
            }
        }
    }

    private static void drawProjectedChunkQuad(GuiGraphics context, Vec3 cameraPos, double minWorldX, double minWorldZ, double rightX, double rightZ, double forwardX, double forwardZ, double scaleX, double scaleY, int alpha, int[] color) {
        double maxWorldX = minWorldX + 16.0;
        double maxWorldZ = minWorldZ + 16.0;
        double[] worldXs = new double[]{minWorldX, maxWorldX, maxWorldX, minWorldX};
        double[] worldZs = new double[]{minWorldZ, minWorldZ, maxWorldZ, maxWorldZ};
        double[] screenXs = new double[4];
        double[] screenYs = new double[4];
        for (int i = 0; i < 4; ++i) {
            double dx = worldXs[i] - cameraPos.f_82479_;
            double dz = worldZs[i] - cameraPos.f_82481_;
            double screenWorldX = dx * rightX + dz * rightZ;
            double screenWorldY = dx * forwardX + dz * forwardZ;
            screenXs[i] = (double)context.m_280182_() * 0.5 + screenWorldX * scaleX;
            screenYs[i] = (double)context.m_280206_() * 0.5 + screenWorldY * scaleY;
        }
        if (TeleportStepEffectRenderer.getScreenBounds(screenXs, screenYs, context.m_280182_(), context.m_280206_()) == null) {
            return;
        }
        TeleportStepEffectRenderer.drawProjectedChunkScanlines(context, screenXs, screenYs, alpha, color);
    }

    private static ScreenRectangle getScreenBounds(double[] xs, double[] ys, int screenWidth, int screenHeight) {
        double minScreenX = Double.POSITIVE_INFINITY;
        double maxScreenX = Double.NEGATIVE_INFINITY;
        double minScreenY = Double.POSITIVE_INFINITY;
        double maxScreenY = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < 4; ++i) {
            minScreenX = Math.min(minScreenX, xs[i]);
            maxScreenX = Math.max(maxScreenX, xs[i]);
            minScreenY = Math.min(minScreenY, ys[i]);
            maxScreenY = Math.max(maxScreenY, ys[i]);
        }
        int x1 = Math.max(0, Mth.m_14107_((double)minScreenX));
        int y1 = Math.max(0, Mth.m_14107_((double)minScreenY));
        int x2 = Math.min(screenWidth, Mth.m_14165_((double)maxScreenX));
        int y2 = Math.min(screenHeight, Mth.m_14165_((double)maxScreenY));
        if (x2 <= x1 || y2 <= y1) {
            return null;
        }
        return new ScreenRectangle(x1, y1, x2 - x1, y2 - y1);
    }

    private static void drawProjectedChunkScanlines(GuiGraphics context, double[] xs, double[] ys, int alpha, int[] color) {
        double minScreenY = Math.min(Math.min(ys[0], ys[1]), Math.min(ys[2], ys[3]));
        double maxScreenY = Math.max(Math.max(ys[0], ys[1]), Math.max(ys[2], ys[3]));
        int y1 = Math.max(0, Mth.m_14107_((double)minScreenY));
        int y2 = Math.min(context.m_280206_(), Mth.m_14165_((double)maxScreenY));
        if (y2 <= y1) {
            return;
        }
        int fillColor = TeleportStepEffectRenderer.argb(alpha, color[0], color[1], color[2]);
        for (int y = y1; y < y2; y += 2) {
            double scanY = Math.min((double)y2 - 0.5, (double)y + 1.0);
            double[] intersections = new double[4];
            int count = 0;
            for (int i = 0; i < 4; ++i) {
                int next = i + 1 & 3;
                double edgeY1 = ys[i];
                double edgeY2 = ys[next];
                if (!(edgeY1 <= scanY && edgeY2 > scanY) && (!(edgeY2 <= scanY) || !(edgeY1 > scanY))) continue;
                double t = (scanY - edgeY1) / (edgeY2 - edgeY1);
                intersections[count++] = xs[i] + (xs[next] - xs[i]) * t;
            }
            if (count < 2) continue;
            double left = Math.min(intersections[0], intersections[1]);
            double right = Math.max(intersections[0], intersections[1]);
            int x1 = Math.max(0, Mth.m_14107_((double)left));
            int x2 = Math.min(context.m_280182_(), Mth.m_14165_((double)right));
            if (x2 <= x1) continue;
            context.m_280509_(x1, y, x2, Math.min(y + 2, y2), fillColor);
        }
    }

    private static int[] getSceneMaskColor() {
        return capturedStartMaskColor == null ? TeleportStepEffectRenderer.getSkyMaskColor() : capturedStartMaskColor;
    }

    private static int argb(int alpha, int red, int green, int blue) {
        return TeleportStepEffectRenderer.clamp(alpha) << 24 | TeleportStepEffectRenderer.clamp(red) << 16 | TeleportStepEffectRenderer.clamp(green) << 8 | TeleportStepEffectRenderer.clamp(blue);
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}

