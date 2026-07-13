# Changelog

## 0.0.0-beta.2 (2026-07-13)

Second beta. Initial port verified working.

- Basic server-client teleport functionality tested
- Corrected versioning and release metadata

## 0.0.0-beta.1 (2026-07-13)

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
