# Changelog

## 0.0.0-beta.32 (2026-07-23)

- fix: correct totalTicks diagnostic log location (was in recordActualTargetFeet instead of start)

## 0.0.0-beta.31 (2026-07-22)

- chore: add totalTicks diagnostic log on transition start

## 0.0.0-beta.30 (2026-07-22)

- fix: use reflection for ViewArea.repositionCamera(double, double) instead of removed SectionPos overload

## 0.0.0-beta.29 (2026-07-22)

- chore: add client-side logging for TeleportClient.handleServerTeleportRequest

## 0.0.0-beta.28 (2026-07-22)

- fix: also intercept tryTeleportAsync and forceTeleportAsync on WaystoneTeleportManager

## 0.0.0-beta.27 (2026-07-22)

- chore: rename log prefix from GTP to TA

## 0.0.0-beta.26 (2026-07-22)

- chore: add diagnostic logging to WaystonesTeleportHandler and TeleportServer
- chore: update mod_description to reflect Waystones-only scope

## 0.0.0-beta.25 (2026-07-21)

- fix: @Pseudo mixin method matching without explicit descriptors for WaystoneTeleportManager

## 0.0.0-beta.24 (2026-07-21)

- fix: update Waystones integration for new API (WaystoneTeleportManager.tryTeleport)
- fix: move template to src/main/resources/templates/ per WORKFLOW_GENERIC
- chore: remove nul, create temp/, update .gitignore
- chore: rename mod_name from Grand Teleport to Teleport Animation
- chore: update workflow version to 1.0.0

## 0.0.0-beta.23 (2026-07-21)

- fix: remove ServerPlayerMixin teleportTo/changeDimension injects (handled by EntityMixin) to fix startup crash

## 0.0.0-beta.22 (2026-07-21)

- fix: correct EntityMixin/ServerPlayerMixin teleportTo descriptor (8 params, returns boolean) to fix startup crash

## 0.0.0-beta.21 (2026-07-21)

- fix: update all client mixins for MC 26.1.2 rendering pipeline (Camera.setup → extractRenderState, etc.)
- fix: use remap=false on all @Inject targets to fix Mixin AP obfuscation mapping errors
- fix: Camera override now writes to CameraRenderState directly for render pipeline compatibility
- fix: EntityRenderer uses shouldRender() instead of removed render() on EntityRenderDispatcher
- fix: LevelRenderer.renderLevel signature updated to 9-param MC 26.1.2 version
- fix: ScreenEffectRenderer, KeyboardInput, MouseHandler signatures updated for MC 26.1.2
- fix: EntityMixin and ServerPlayerMixin dead code paths cleaned (imports fixed, bodies removed)

## 0.0.0-beta.20 (2026-07-19)

- change: animation now only plays for Waystones teleports (no /tp or other teleports)
- fix: restore all client mixins (CameraMixin, KeyboardInputMixin, MouseHandlerMixin, etc.)
- fix: register Waystones mixins in mixins.json so they actually load
- fix: disable generic server-side teleport interception (tryDelayExternalTeleport returns false)
- chore: remove CommandMatcher, DestinationParser, ClientPacketListenerMixin, ConnectionMixin

## 0.0.0-beta.19 (2026-07-19)

- fix: restore template to src/main/templates/ so generateModMetadata expands version correctly

## 0.0.0-beta.18 (2026-07-19)

- fix: declare Waystones as project-level embedded dependency on CurseForge

## 0.0.0-beta.17 (2026-07-19)

- fix: declare Waystones as requiredDependency in CurseForge upload metadata

## 0.0.0-beta.16 (2026-07-19)

- feat: add Waystones JAR as compile dependency
- chore: version waystones JAR in libs/ for reproducible builds

## 0.0.0-beta.15 (2026-07-16)

- docs: update WORKFLOW.md with new branching strategy and JAR naming conventions
- chore: restructure repository for multi-version support (main empty, per-version branches)

## 0.0.0-beta.14 (2026-07-14)

- fix: forzar cámara a primera persona y HUD visible directamente en onClientSetup
- fix: añadir logging de diagnóstico en tryDelayExternalTeleport

## 0.0.0-beta.13 (2026-07-14)

- fix: añadir EntityMixin para interceptar teleport en Entity (clase padre)
- fix: restaurar cámara y HUD forzosamente en el primer tick del juego
- fix: añadir mixin para teleport(TeleportTransition) compatibilidad MC 26.1.2
- fix: resetState() para limpiar estado de la animación al inicio

## 0.0.0-beta.12 (2026-07-14)

- feat: add mixin para `teleport(TeleportTransition)` (nuevo método en MC 26.1.2)
- refactor: mantener mixins legacy de `teleportTo` como respaldo
- docs: actualizar WORKFLOW.md con convenciones de idioma

## 0.0.0-beta.11 (2026-07-14)

- fix: restore ServerPlayerMixin with correct 7-param teleportTo descriptor
- docs: add WORKFLOW.md with versioning and commit conventions
- chore: restructure curseforge files into curseforge/ directory

## 0.0.0-beta.10 (2026-07-13)

- Removed ALL mixins to ensure stable game loading
- Pure event-based server-side teleport interception via EntityTeleportEvent
- Clean slate for incremental mixin restoration

## 0.0.0-beta.9 (2026-07-13)

- Restored ServerPlayerMixin with dual 7-param and 8-param teleportTo interceptors (require=0)
- Restored ConnectionMixin and ClientPacketListenerMixin for client-side interception (require=0)
- Server-side EntityTeleportEvent handler kept as fallback

## 0.0.0-beta.8 (2026-07-13)

- Removed all remaining problematic client mixins (MouseHandler, KeyboardInput, Gui, GameRenderer, LevelRenderer, Frustum, etc.)
- Game now loads without mixin-related crashes

## 0.0.0-beta.7 (2026-07-13)

- Removed failing mixins (CameraMixin, ConnectionMixin, ClientPacketListenerMixin, FogRendererMixin, etc.) causing crashes at startup
- Retained core mixins for terrain, frustum, input blocking, and rendering effects

## 0.0.0-beta.6 (2026-07-13)

- Replaced ServerPlayerMixin with EntityTeleportEvent (NeoForge event system)
- Removed problematic mixin that couldn't resolve teleportTo method signature

## 0.0.0-beta.5 (2026-07-13)

- Fixed ServerPlayerMixin method descriptor (removed explicit signature, let Mixin resolve)

## 0.0.0-beta.4 (2026-07-13)

- Fixed ServerPlayerMixin teleportTo method descriptor (void + boolean resetCamera)

## 0.0.0-beta.3 (2026-07-13)

- Refactored all GtaLikeTeleport* classes to clean names (TeleportClient, TeleportConfig, etc.)
- Renamed commands from /gtp /grandtp to /ta /tpanimation
- Renamed mixin prefixes from gtalikeTeleport$ to teleportAnimation$

## 0.0.0-beta.2 (2026-07-13)

Initial release. Port of Grand Teleport from Forge 1.20.1 to NeoForge 26.1.2.

### Added
- Full cinematic teleport animation engine (TeleportTransitionController)
- 3-stage configurable zoom-out and zoom-in heights per dimension
- Client-side teleport command interception (`/tp`, `/teleport`, `/execute`)
- Server-side delayed teleport system with ACK/bypass mechanism
- Properties-based configuration (`grand_teleport.properties`)
- In-game configuration GUI (basic functional version)
- Custom sound effects (7 sounds: camera in/out, teleport, zoom in/out)
- Chunk-fade screen mask rendering
- Step flash overlay effect
- Post-release camera override with smooth blending
- Cross-dimension travel support with loading screen handling
- Per-dimension zoom settings (Overworld, Nether, End)
- Commands: `/gtp`, `/grandtp` with on/off/status/player_freeze subcommands

### Compatibility
- Sodium terrain update scheduling via reflection
- Iris Shaders hard terrain cut detection
- Bobby chunk rendering check
- Distant Horizons near-clip adjustment
- Voxy terrain preference
- Waystones teleport and warp plate interception
- JourneyMap teleport packet interception
- Leawind's Third Person camera management

### Changed
- Migrated from Forge `SimpleChannel` to NeoForge `CustomPacketPayload` networking
- Updated all obfuscated method references to Mojang mapped names
- Removed legacy `Minecraft.smartCull` references
- Adapted event system to NeoForge event bus

### Removed
- MDK boilerplate example blocks, items, tabs, and config
