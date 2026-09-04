# Changelog

## [1.2.1] - 2026-09-04
### Fixed

- **Server-side mixin log spam**: 13 client-only mixins (`CameraMixin`, `GameRendererMixin`, `GuiMixin`, `LevelRendererMixin`, `MinecraftMixin`, `MouseHandlerMixin`, `ClientPacketListenerMixin`, `EntityRendererMixin`, `FogRendererMixin`, `FrustumMixin`, `ScreenEffectRendererMixin`, `KeyboardInputMixin`, `CameraAccessor`) were registered in the common `"mixins"` array of `teleport_animation.mixins.json` instead of `"client"`. On a dedicated server, NeoForge's `RuntimeDistCleaner` blocked every attempt to load those vanilla client classes — harmless (`TeleportMixinPlugin` already prevented real application), but it logged 13 `ERROR` + 13 `WARN` lines on *every* server boot. Moved to the `"client"` array; no behaviour change on either side. Found via log analysis of the Mystical Realms Develop instance.

## [1.2.0] - 2026-09-03
### Changed

- **Native config system**: Replaced the hand-rolled `config/teleport_animation.properties` reader/writer with a native NeoForge `ModConfigSpec`, registered as `ModConfig.Type.CLIENT` in `TeleportAnimation`. `config/teleport_animation-client.toml` is now auto-generated on first launch and the mod is editable in Configured. Ported from the 26.2 branch's config backend. The public `TeleportConfig` API is unchanged, so all teleport/zoom/waystone behaviour is identical to 1.1.0.
- Sound settings (`customSoundsEnabled`, `minecraftSoundVolume`, `customSoundVolume`) now live in the `[sounds]` section of the generated TOML.

### Removed

- Orphaned config-GUI-layout persistence layer (`configLayout*` / `configWidget*` / `configText*`, ~490 lines) carried over from Grand Teleport — it had no callers on this branch.
- Legacy `.properties` migration (`grand_teleport.properties` / `gtalike_teleport.properties`). Old `.properties` values are not migrated to the new TOML.

### Internal

- `TeleportConfig.java` rebuilt against `ModConfigSpec` (940 → 452 lines). `TeleportAnimation` constructor now takes `(IEventBus, ModContainer)`. Config rewrite delegated to OpenCode (`mimo-v2.5`), verified by `./gradlew build` + caller grep.

## [1.1.0] - 2026-09-02
### Bug Fixes (ported from 26.2 fix line)

- **Server crash on external teleport**: Removed client-only config checks (`isEffectEnabled()`, `isWarpPlateTransitionsEnabled()`) from the server-side `shouldStartServerTransition()` path. The server now only validates player state and networking, preventing `IllegalStateException: Cannot get config value before config is loaded` when Waystones triggers teleport on external events.

- **Floor flicker during top-down travel**: Replaced full `invalidateLevelGeometry()` terrain rebuilds mid-flight with lightweight `SodiumCompat.scheduleTerrainUpdate()` calls. Added fade-to-black overlay during the travel phase (`getTravelBlackoutIntensity()`), arrival chunk hold (`updateArrivalChunkHold()`) to wait for destination chunks before descent, and refactored `travelFrame()` to use short camera drags at entry/exit instead of interpolating the full path.

- **Nearby-waystone slide + flicker elimination**: Added `travelToNearbyWaystone` detection for waystones within render distance. Nearby waystones now use a smooth horizontal `slideFrame()` at constant altitude instead of the full travel animation. Faster travel ticks (12-20) for nearby destinations. Suppressed HUD fade overlay during travel phase. Disabled zoom shake in the pre-travel hold phase to prevent Sodium terrain update conflicts.

- **Player falls through floor at destination**: Fixed arrival position to target the walk surface (`pos.getY() + 1.0`) instead of the raw block Y in `WaystonesTeleportHandler`, `WaystonesWarpPlateHandler`, and `TeleportClient`. Fixed `getFeetPos()` to return true feet position (`player.getY() - player.getEyeHeight()`) instead of eye Y, correcting a ~1.62-block bias in all distance/arrival/camera-sync calculations. Added `invokeWaystonesTeleport()` helper with error logging when all 4 Waystones API signatures fail.

- **Player spawns underground on long trips**: Added `readWaystonePos()` and `forceLoadDestinationChunks()` to `WaystonesTeleportHandler`. The destination waystone's chunk and 4 cardinal neighbours are now force-loaded at `ChunkStatus.FULL` before any reflection-based teleport call, ensuring real block data is available for Waystones' free-space check.

### Internal

- Version bumped to 1.1.0 to align with the 26.2 fix line.
