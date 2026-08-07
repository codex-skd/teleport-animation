# Changelog

## 1.1.8 (2026-08-07)

### Fix
- Eliminate visual flicker in nearby waystone transitions by disabling camera shake during ascent phases.
- Remove inconsistency in distant waystone fade-in approach by smoothing the final drag without visual noise.
- Ensure Sodium terrain updates don't interfere with camera movement for seamless transitions.

### Technical
- `pullFrame()` and `topDownFrame()` now use conditional shake envelope (0.0 for nearby, 1.0 for distant waystones).
- `prePushTopDownFrame()` fade-in drag now returns clean `CameraFrame` without shake, maintaining smooth approach during screen fade-in.
- Interaction with Sodium optimized: cheap terrain updates no longer conflict with camera movement noise during nearby waystone slides.

## 1.1.7 (2026-08-06)

### Fix
- Verified stability of camera pre-displacement flicker fix for nearby waystones.
- Confirmed smooth `slideFrame()` transitions for waystones within render distance.
- Validated elegant fade-to-black drags for distant waystones.

### Technical
- Terrain chunk handoff delay mechanism active and stable.
- Automatic nearby waystone detection based on render distance.
- Optimized terrain refresh with Sodium cheap updates.

## 1.1.6 (2026-08-06)

### Fix
- Eliminated transition flicker in Opción 1 (nearby waystone slide). Removed zoom shake from slideFrame() to ensure smooth, glitch-free interpolation between Phase 1 and Phase 2.

### Technical
- `slideFrame()` now returns clean `CameraFrame` without `applyZoomShake()` effects, maintaining visual continuity during nearby waystone transitions.
- Shake effects preserved in other phases (pull, distant travel, push) to maintain cinematic feel for long-distance teleports.

## 1.1.5 (2026-08-06)

### Feature
- Opción 1 implementation: smooth slide animation for nearby waystones. When teleporting to a waystone within render distance, the camera now smoothly interpolates XZ position in a single horizontal slide instead of complex travel animation.

### Technical
- New `slideFrame()` method handles horizontal interpolation for nearby waystone teleports.
- `getCameraFrame()` now routes to `slideFrame()` when `travelToNearbyWaystone` is true, maintaining constant altitude and top-down view angle (pitch 90°).
- Phase 1 (ascenso) and Phase 3 (descenso) remain unchanged, providing smooth vertical transitions before and after the slide.

## 1.1.4 (2026-08-06)

### Feature
- Smart distance-based rendering in phase 2 (travel): detects if waystones are within render distance and optimizes camera animation accordingly.

### Fix
- Double flicker at start of phase 2 — HUD fade overlay no longer renders simultaneously with travel blackout effect.
- Nearby waystones now skip fade-to-black transition for smoother short-distance teleports.

### Technical
- New static variable `travelToNearbyWaystone` tracks distance-based rendering mode.
- `calculateTravelTicks()` now detects nearby waystones (distance < renderDistance × 16 blocks) and returns faster travel ticks (12-20).
- `getTravelBlackoutIntensity()` returns 0.0f for nearby waystones.
- `getHudFadeOverlayIntensity()` suppresses HUD overlay during travel phase (between `getTravelStartTick()` and `getPushMotionStartTick()`).

## 1.1.2 (2026-08-05)

### Fix
- Server crash on external teleport events (e.g., from Waystones) — server was trying to access client-side config values during network packet processing, causing `IllegalStateException: Cannot get config value before config is loaded`. Config validation moved to client-side; server now only validates player state and networking capabilities.

### Technical
- Removed `isEffectEnabled()` and `isWarpPlateTransitionsEnabled()` checks from `TeleportServer.shouldStartServerTransition()` — these are client-side config validations that should not run on the server.

## 1.1.1 (2026-08-05)

### Fix
- Floor flicker during top-down travel (all distances) — refined terrain refresh logic. The camera now stabilizes its height before changes become visible, and destructive full geometry rebuilds replaced with cheap Sodium updates.
- Double flicker on arrival (near waystone teleports) — the blackout fade now covers the server-side teleport command window, hiding chunk retention/handoff changes.
- Blank screen during long travel (far waystone teleports) — added short visible camera drags at entry and exit of the travel phase, with fade-to-black masking only the unknown middle (chunk load wait). Preserves the sense of camera movement instead of a long empty blackout.

### Technical
- `getArrivalSurfaceY()` now reuses `areArrivalChunksReady()` to avoid writing unresolved heightmap values during flight.
- `updateArrivalTerrainRefresh()` and `extendOrReleaseNormalChunkHandoff()` replaced full `invalidateLevelGeometry()` rebuilds with `SodiumCompat.scheduleTerrainUpdate()` (lightweight). Full rebuilds now only at transition start and arrival.
- New `updateArrivalChunkHold()` holds top-down camera over destination until chunks load, extending pre-push wait to avoid revealing partial terrain.
- New `getTravelBlackoutIntensity()` manages full-screen fade-to-black overlay (9-tick fade out, opaque, 9-tick fade in). New `renderTravelBlackout()` in `TeleportStepEffectRenderer`.
- `travelFrame()` now uses two short camera drags (20 blocks each, clamped to half actual distance on short teleports) instead of interpolating full flight path.

### Notes
**Unverified**: this build has not been tested in-game. Changes concentrated in camera positioning and terrain refresh during travel phase only; pull and push animations unchanged.

## 1.1.0 (2026-08-05)

### Fix
- Floor flicker during the top-down travel flight — `requestTerrainVisibilityUpdate` no longer forces a full `invalidateCompiledGeometry()` rebuild of all terrain on every chunk section crossed while the camera flies; only a lightweight Sodium terrain update runs during travel now.

### Feature
- Config migrated from a custom `.properties` file to a native NeoForge `ModConfigSpec` (`config/teleport_animation-client.toml`), auto-created on first launch and editable from the Configured mod. Public `TeleportConfig` API unchanged.

### Refactor
- Removed dead code from a removed custom config-screen (`configLayout*`/`configWidget.*`/`configText.*` fields) and dropped the legacy `.properties` migration from the old "Grand Teleport" fork, which no longer applies to the new format.

## 1.0.1 (2026-08-02)

### Refactor
- Config renombrado a `config/teleport_animation.properties` con migración automática desde `grand_teleport.properties`.
- Eliminados residuos de fork "Grand Teleport"/"GTP" (strings de config, lang, pack.mcmeta, métodos `restoreGtpCameraAfterLeawind` y `handleGtaTeleportCommand`).

## 1.0.0 (2026-07-31)

- First stable release. Teleport animation confirmed working on Minecraft 26.2.

## 0.0.0-beta.2 (2026-07-30)

- fix: crash on startup — `MinecraftMixin` targeted `Minecraft.setScreen`, which moved to `Gui.setScreen` in this MC version (screen management now lives on `Minecraft.gui`, not `Minecraft` itself). Retargeted the mixin to `Gui.class`.

## 0.0.0-beta.1 (2026-07-30)

- feat: initial port of Teleport Animation to Minecraft 26.2 / NeoForge 26.2.0.32-beta, based on the 26.1.2 codebase---

## [1.1.3] - 2026-08-05

### Change

- **Recompilado contra NeoForge `26.2.0.37-beta`**: bump de `neo_version` en `gradle.properties` (`26.2.0.32-beta` -> `26.2.0.37-beta`). Verificado con `runServer` (arranque sin errores).
- **Waystones changed from implementation to compileOnly dependency (optional integration guarded by the mixin plugin), so the dev environment no longer loads Waystones and its balm/shogi requirements.**

## [angelog

## 1.1.2 (2026-08-05)

### Fix
- Server crash on external teleport events (e.g., from Waystones) — server was trying to access client-side config values during network packet processing, causing `IllegalStateException: Cannot get config value before config is loaded`. Config validation moved to client-side; server now only validates player state and networking capabilities.

### Technical
- Removed `isEffectEnabled()` and `isWarpPlateTransitionsEnabled()` checks from `TeleportServer.shouldStartServerTransition()` — these are client-side config validations that should not run on the server.

## 1.1.1 (2026-08-05)

### Fix
- Floor flicker during top-down travel (all distances) — refined terrain refresh logic. The camera now stabilizes its height before changes become visible, and destructive full geometry rebuilds replaced with cheap Sodium updates.
- Double flicker on arrival (near waystone teleports) — the blackout fade now covers the server-side teleport command window, hiding chunk retention/handoff changes.
- Blank screen during long travel (far waystone teleports) — added short visible camera drags at entry and exit of the travel phase, with fade-to-black masking only the unknown middle (chunk load wait). Preserves the sense of camera movement instead of a long empty blackout.

### Technical
- `getArrivalSurfaceY()` now reuses `areArrivalChunksReady()` to avoid writing unresolved heightmap values during flight.
- `updateArrivalTerrainRefresh()` and `extendOrReleaseNormalChunkHandoff()` replaced full `invalidateLevelGeometry()` rebuilds with `SodiumCompat.scheduleTerrainUpdate()` (lightweight). Full rebuilds now only at transition start and arrival.
- New `updateArrivalChunkHold()` holds top-down camera over destination until chunks load, extending pre-push wait to avoid revealing partial terrain.
- New `getTravelBlackoutIntensity()` manages full-screen fade-to-black overlay (9-tick fade out, opaque, 9-tick fade in). New `renderTravelBlackout()` in `TeleportStepEffectRenderer`.
- `travelFrame()` now uses two short camera drags (20 blocks each, clamped to half actual distance on short teleports) instead of interpolating full flight path.

### Notes
**Unverified**: this build has not been tested in-game. Changes concentrated in camera positioning and terrain refresh during travel phase only; pull and push animations unchanged.

## 1.1.0 (2026-08-05)

### Fix
- Floor flicker during the top-down travel flight — `requestTerrainVisibilityUpdate` no longer forces a full `invalidateCompiledGeometry()` rebuild of all terrain on every chunk section crossed while the camera flies; only a lightweight Sodium terrain update runs during travel now.

### Feature
- Config migrated from a custom `.properties` file to a native NeoForge `ModConfigSpec` (`config/teleport_animation-client.toml`), auto-created on first launch and editable from the Configured mod. Public `TeleportConfig` API unchanged.

### Refactor
- Removed dead code from a removed custom config-screen (`configLayout*`/`configWidget.*`/`configText.*` fields) and dropped the legacy `.properties` migration from the old "Grand Teleport" fork, which no longer applies to the new format.

## 1.0.1 (2026-08-02)

### Refactor
- Config renombrado a `config/teleport_animation.properties` con migración automática desde `grand_teleport.properties`.
- Eliminados residuos de fork "Grand Teleport"/"GTP" (strings de config, lang, pack.mcmeta, métodos `restoreGtpCameraAfterLeawind` y `handleGtaTeleportCommand`).

## 1.0.0 (2026-07-31)

- First stable release. Teleport animation confirmed working on Minecraft 26.2.

## 0.0.0-beta.2 (2026-07-30)

- fix: crash on startup — `MinecraftMixin` targeted `Minecraft.setScreen`, which moved to `Gui.setScreen` in this MC version (screen management now lives on `Minecraft.gui`, not `Minecraft` itself). Retargeted the mixin to `Gui.class`.

## 0.0.0-beta.1 (2026-07-30)

- feat: initial port of Teleport Animation to Minecraft 26.2 / NeoForge 26.2.0.32-beta, based on the 26.1.2 codebase
