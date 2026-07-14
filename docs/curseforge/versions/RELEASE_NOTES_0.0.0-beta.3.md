## Teleport Animation 0.0.0-beta.3

### Changes since beta.2

- Refactored all internal class names (removed GtaLikeTeleport prefix)
- Changed commands: `/gtp` → `/ta`, `/grandtp` → `/tpanimation`
- Renamed mixin prefixes for consistency
- Cleaned up all remaining references to the original Forge mod

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

### Compatible mods

- **Waystones** — delayed teleports with animation
- **JourneyMap** — teleport interception with animation
- **Sodium** — terrain update scheduling
- **Iris Shaders** — hard terrain cut support
- **Distant Horizons** — near-clip adjustment
- **Bobby** — chunk rendering compatibility
- **Voxy** — terrain preference support
- **Leawind's Third Person** — automatic camera management

### Notes

- Java 25+ required (NeoForge 26.1.2 requirement)
