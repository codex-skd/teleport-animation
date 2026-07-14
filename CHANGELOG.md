# Changelog

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
