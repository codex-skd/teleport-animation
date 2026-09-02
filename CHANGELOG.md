# Changelog

## [1.1.0] - 2026-09-02
### Bug Fixes (ported from 26.2 fix line)

- **Server crash on external teleport**: Removed client-only config checks (`isEffectEnabled()`, `isWarpPlateTransitionsEnabled()`) from the server-side `shouldStartServerTransition()` path. The server now only validates player state and networking, preventing `IllegalStateException: Cannot get config value before config is loaded` when Waystones triggers teleport on external events.

- **Floor flicker during top-down travel**: Replaced full `invalidateLevelGeometry()` terrain rebuilds mid-flight with lightweight `SodiumCompat.scheduleTerrainUpdate()` calls. Added fade-to-black overlay during the travel phase (`getTravelBlackoutIntensity()`), arrival chunk hold (`updateArrivalChunkHold()`) to wait for destination chunks before descent, and refactored `travelFrame()` to use short camera drags at entry/exit instead of interpolating the full path.

- **Nearby-waystone slide + flicker elimination**: Added `travelToNearbyWaystone` detection for waystones within render distance. Nearby waystones now use a smooth horizontal `slideFrame()` at constant altitude instead of the full travel animation. Faster travel ticks (12-20) for nearby destinations. Suppressed HUD fade overlay during travel phase. Disabled zoom shake in the pre-travel hold phase to prevent Sodium terrain update conflicts.

- **Player falls through floor at destination**: Fixed arrival position to target the walk surface (`pos.getY() + 1.0`) instead of the raw block Y in `WaystonesTeleportHandler`, `WaystonesWarpPlateHandler`, and `TeleportClient`. Fixed `getFeetPos()` to return true feet position (`player.getY() - player.getEyeHeight()`) instead of eye Y, correcting a ~1.62-block bias in all distance/arrival/camera-sync calculations. Added `invokeWaystonesTeleport()` helper with error logging when all 4 Waystones API signatures fail.

- **Player spawns underground on long trips**: Added `readWaystonePos()` and `forceLoadDestinationChunks()` to `WaystonesTeleportHandler`. The destination waystone's chunk and 4 cardinal neighbours are now force-loaded at `ChunkStatus.FULL` before any reflection-based teleport call, ensuring real block data is available for Waystones' free-space check.

### Internal

- Version bumped to 1.1.0 to align with the 26.2 fix line.
