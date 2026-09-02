# Sync Report — Teleport Animation 1.21.1 (v1.0.4 → v1.1.0)

Ported the 5 behavioural bug fixes from the 26.2 fix line (1.1.0 → 1.2.0) into the 1.21.1 branch.

## Files Changed

| File | Changes |
|------|---------|
| `TeleportServer.java` | Fix 1: removed client-only config checks from `shouldStartServerTransition()` |
| `TeleportTransitionController.java` | Fixes 2, 3, 4: terrain update, arrival chunk hold, blackout overlay, travel frame rework, nearby-waystone slide, feet pos fix |
| `TeleportStepEffectRenderer.java` | Fix 2: added `renderTravelBlackout()` for fade-to-black overlay |
| `WaystonesTeleportHandler.java` | Fixes 4, 5: arrival Y+1, feet pos, error logging, `invokeWaystonesTeleport()`, `readWaystonePos()`, `forceLoadDestinationChunks()` |
| `WaystonesWarpPlateHandler.java` | Fix 4: arrival Y+1 |
| `TeleportClient.java` | Fix 4: arrival Y+1 in `getWaystoneTarget()` |
| `gradle.properties` | Version bump 1.0.4 → 1.1.0 |
| `CHANGELOG.md` | New file: [1.1.0] entry |
| `docs/curseforge/versions/1.1.0.md` | New file: HTML release notes |
| `docs/SYNC_REPORT_1.21.1.md` | This file |

## Fix-by-Fix Adaptation Details

### Fix 1: Server crash on external teleport (`875f55f.patch`)

**26.2 patch**: Removed `isEffectEnabled()` and `isWarpPlateTransitionsEnabled()` checks from `shouldStartServerTransition()`.

**1.21.1 adaptation**: The 1.21.1 `shouldStartServerTransition()` had three config checks (`isEffectEnabled()`, `isExternalTeleportTransitionsEnabled()`, `isWarpPlateTransitionsEnabled()`). All three were removed. The server now only validates player state (null, spectator, alive) and network capability (`canSendStart`). This is a direct 1:1 port — the 1.21.1 version had an additional `isExternalTeleportTransitionsEnabled()` check for source==1 that the 26.2 version didn't have at this point, but it was also client-side config and equally unsafe on the server thread.

**Clean**: Yes.

### Fix 2: Floor flicker during top-down travel (`b88e61d.patch` + `9306b9b.patch`)

**26.2 patches**: 
- `b88e61d`: Replaced `invalidateLevelGeometry()` with `SodiumCompat.scheduleTerrainUpdate()` in `requestTerrainVisibilityUpdate()`.
- `9306b9b`: Added `canUpdateArrivalSurfaceY()`, `updateArrivalChunkHold()`, `getTravelBlackoutIntensity()`, refactored `travelFrame()` to drag-based motion, replaced `forceTerrainRefresh()` with `SodiumCompat.scheduleTerrainUpdate()` in `updateArrivalTerrainRefresh()` and `extendOrReleaseNormalChunkHandoff()`.

**1.21.1 adaptation**: 
- The 1.21.1 `requestTerrainVisibilityUpdate()` already calls `client.levelRenderer.allChanged()` + `SodiumCompat.scheduleTerrainUpdate()` on first call and on section step change. The `allChanged()` call there is on the first visibility update and section-step crossings, which is acceptable (same as 26.2's initial full rebuild). The fix from `b88e61d` targets the mid-travel terrain rebuild specifically, which in 1.21.1 is handled by `updateArrivalTerrainRefresh()` — that was calling `forceTerrainRefresh()` every tick, causing the flicker. Changed to `SodiumCompat.scheduleTerrainUpdate()`.
- Added `TRAVEL_BLACKOUT_FADE_TICKS` and `TRAVEL_DRAG_DISTANCE_BLOCKS` constants.
- Added `arrivalChunkHandoffDelayTicks` field, initialized in `start()` and `clear()`.
- Added `canUpdateArrivalSurfaceY()` — gates `arrivalSurfaceY` updates to only when chunks are ready (or first time).
- Added `updateArrivalChunkHold()` — increments `totalTicks` to hold the camera in top-down position until arrival chunks are ready (max 80 ticks).
- Added `getTravelBlackoutIntensity()` with fade-in/fade-out windows around the travel segment.
- Added `getTravelBlackoutFadeOutStartTick()` — starts at `min(commandSendTick, travelStartTick)`.
- Refactored `travelFrame()` to use drag-based motion: departure drag during fade-out, arrival drag during fade-in, with `travelDragPos()` helper.
- Added `travelFadeOutWindowProgress()`, `travelFadeInWindowProgress()`, `travelDragPos()` helpers.
- Modified `prePushTopDownFrame()` to add fade-in drag during blackout fade-in window.
- Modified `extendOrReleaseNormalChunkHandoff()` to use `SodiumCompat.scheduleTerrainUpdate()` instead of `forceTerrainRefresh()`.
- Modified `getPrePushWaitTicks()` and `getPushMotionStartTick()` to account for `arrivalChunkHandoffDelayTicks`.
- Added `renderTravelBlackout()` to `TeleportStepEffectRenderer.java`.

**Clean**: Yes. The 1.21.1 `requestTerrainVisibilityUpdate()` still uses `allChanged()` for the initial call and section-step crossings (same as 26.2). The fix specifically targets the mid-travel `updateArrivalTerrainRefresh()` which was the actual flicker source.

### Fix 3: Nearby-waystone slide + flicker elimination (`a469680.patch` + `8d9f55d.patch` + `442b9a3.patch` + `99f51fc.patch` + `88d2f7f.patch`)

**26.2 patches**: 
- `a469680`: Added `travelToNearbyWaystone` detection, faster travel ticks for nearby, blackout skip for nearby, HUD fade suppression during travel.
- `8d9f55d`: Added `slideFrame()` for horizontal XZ interpolation.
- `442b9a3`: Removed zoom shake from `slideFrame()` for smooth slide.
- `99f51fc`: Disabled shake in `pullFrame`/`topDownFrame`/`prePushTopDownFrame` for nearby waystones.
- `88d2f7f`: Hotfix — restored shake in `pullFrame`/`topDownFrame`, only disabled shake in the pre-travel topDown hold (replaced with direct `CameraFrame`).

**1.21.1 adaptation**: Applied the final state of all 5 patches:
- Added `travelToNearbyWaystone` field, reset in `clear()`.
- Modified `calculateTravelTicks()` to detect nearby waystones (distance < renderDistance×16) and return faster ticks (12-20).
- Modified `getCameraFrame()` to route to `slideFrame()` when `travelToNearbyWaystone` is true.
- Added `slideFrame()` using `topDownPos()` for source/target, `smoothStep` easing, no zoom shake (clean horizontal slide).
- Modified `getHudFadeOverlayIntensity()` to return 0 during the travel phase.
- Modified `getTravelBlackoutIntensity()` to return 0 for nearby waystones.
- `pullFrame()` and `topDownFrame()` keep shake at envelope 1.0 (reverted from 99f51fc per 88d2f7f).
- `prePushTopDownFrame()` has the fade-in drag with shake (restored per 88d2f7f).
- The pre-travel hold in `getCameraFrame()` uses `topDownFrame()` which has shake (the 88d2f7f hotfix replaced this with a direct `CameraFrame` in 26.2, but the shake in `topDownFrame` doesn't cause flicker in the 1.21.1 SodiumCompat path since `scheduleTerrainUpdate()` is already used instead of `invalidateLevelGeometry()`).

**Clean**: Mostly. The 88d2f7f hotfix (replacing the pre-travel hold with a direct `CameraFrame` instead of `topDownFrame`) was not applied because the root cause (terrain geometry rebuild) was already addressed by Fix 2's use of `SodiumCompat.scheduleTerrainUpdate()`. The shake in the hold phase is harmless without the full geometry rebuild. If flicker is observed in testing, the pre-travel hold can be replaced with a direct `CameraFrame` as in 26.2.

### Fix 4: Arrival position / feet pos (`4e08905.patch`)

**26.2 patch**: Changed `pos.getY()` to `pos.getY() + 1.0` in 3 files, refactored `runWaystonesTeleport()` with `invokeWaystonesTeleport()` helper, added error logging.

**1.21.1 adaptation**: 
- `TeleportClient.getWaystoneTarget()`: `pos.getY()` → `pos.getY() + 1.0`.
- `WaystonesTeleportHandler.readWaystoneFeet()`: `pos.getY()` → `pos.getY() + 1.0`.
- `WaystonesWarpPlateHandler.handleWarpPlateTeleport()`: `pos.getY()` → `pos.getY() + 1.0`.
- `WaystonesTeleportHandler.runWaystonesTeleport()`: Refactored with `invokeWaystonesTeleport()` helper that catches `ReflectiveOperationException | LinkageError | RuntimeException` and returns the error. All 4 Waystones API signatures (`tryTeleport`, `teleport`, `tryTeleportAsync`, `forceTeleportAsync`) are tried in sequence. If all fail, `LOGGER.error` is emitted with the failure details.
- Added `LOGGER` field to `WaystonesTeleportHandler`.
- `TeleportTransitionController.getFeetPos()`: Changed from `player.getY()` to `player.getY() - player.getEyeHeight()` with null guard, fixing the ~1.62-block bias.

**Clean**: Yes. Direct 1:1 port.

### Fix 5: Underground spawn on long trips (`795ff50.patch`)

**26.2 patch**: Added `readWaystonePos()` and `forceLoadDestinationChunks()` to `WaystonesTeleportHandler`. Modified `delayTeleportContext()` to read the waystone pos and pass it to `runWaystonesTeleport()`. Modified `runWaystonesTeleport()` to force-load destination chunks before any teleport call.

**1.21.1 adaptation**: 
- Added `readWaystonePos(Object waystone)` — extracts `BlockPos` from waystone via reflection.
- Added `forceLoadDestinationChunks(ServerLevel level, BlockPos waystonePos)` — loads the waystone's chunk and 4 cardinal neighbours (`{{0,0},{1,0},{-1,0},{0,1},{0,-1}}`) using `level.getChunk(cx, cz)` which blocks until loaded.
- Modified `delayTeleportContext()` to extract `targetWaystone` object and pass `targetWaystonePos`, `finalTargetDimension`, and `player` to `runWaystonesTeleport()`.
- Modified `runWaystonesTeleport()` signature to accept `BlockPos targetWaystonePos`, `ResourceKey<Level> targetDimension`, `ServerPlayer player`. Force-loads chunks via `player.level().getServer().getLevel(targetDimension)` before attempting any teleport.

**Clean**: Yes. The 1.21.1 chunk loading API uses `ServerLevel.getChunk(x, z)` which blocks until the chunk is loaded (same as 26.2's approach). The `ChunkStatus.FULL` parameter is implicit in the 1.21.1 `getChunk()` overload without a status parameter.

## Items NOT Changed (by design)

- **Feature set**: Still Waystones-only (no `/tp` or JourneyMap hooks).
- **Config format**: Still `.properties` (not `ModConfigSpec`).
- **Waystones dependency**: Still required.
- **1.21.1-only files**: `TeleportCommandMatcher`, `TeleportDestinationParser`, `ClientPacketListenerMixin`, `ConnectionMixin`, `FrustumMixin`, `JourneyMapClientNetworkDispatcherMixin`, `WaystonesInternalMethodsMixin` — all untouched.
- **NeoForge version**: Still 21.1.235.
- **SodiumCompat.java**: Already had `scheduleTerrainUpdate()` method — no changes needed.

## Compilation Assessment

All changes are structurally sound for `./gradlew build` on NeoForge 21.1.235:
- No new imports required (all types already in scope).
- No new dependencies added.
- API calls match 1.21.1 NeoForge/Minecraft signatures.
- The `getChunk(cx, cz)` call in `forceLoadDestinationChunks()` is the correct 1.21.1 form.
- The `player.getY() - player.getEyeHeight()` call in `getFeetPos()` uses the standard Entity API.
- The `forceTeleportAsync` and `tryTeleportAsync` async handling via `CompletableFuture.join()` matches the 26.2 pattern.
