## Teleport Animation 0.0.0-beta.9

### Changes since beta.8

- Restored `ServerPlayerMixin` with dual method descriptors for `teleportTo` (7-param and 8-param versions, both with `require=0` to prevent crashes)
- Restored `ConnectionMixin` and `ClientPacketListenerMixin` for client-side teleport packet interception
- `EntityTeleportEvent` kept as server-side fallback handler

### What's included

- Cinematic teleport animation with 3-stage configurable zoom
- In-game config via `/ta` command with GUI
- Works with `/tp`, `/teleport`, `/execute ... run tp`
- Cross-dimension travel with loading screen handling
- Chunk fade masking and terrain visibility management
- Custom sound effects (7 unique sounds)
- Player freeze during transitions (configurable)
- Server-side delayed teleport system with ACK/bypass protocol
- Per-dimension zoom settings (Overworld, Nether, End)
- Multi-path interception: EntityTeleportEvent + mixin + fallback

### Compatible mods

- **Waystones** — delayed teleports with animation (server-side mixins)
- **Sodium** — terrain update scheduling
- **Iris Shaders** — hard terrain cut support

### Notes

- Java 25+ required (NeoForge 26.1.2 requirement)
- Client mixins use `require=0` for compatibility — may not intercept all teleport sources
- Waystones mixins are server-side with `require=0`
