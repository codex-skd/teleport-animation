# Changelog

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
