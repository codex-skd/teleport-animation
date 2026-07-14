## Teleport Animation 0.0.0-beta.10

### Changes since beta.9

- Removed ALL mixins for stable game loading — no more startup crashes
- Server-side teleport interception uses only NeoForge EntityTeleportEvent
- Mixins will be restored incrementally in future releases, one at a time

### What's included

- Cinematic teleport animation engine with 3-stage configurable zoom
- In-game config via `/ta` command with GUI
- Custom sound effects (7 unique sounds)
- Player freeze during transitions (configurable)
- Server-side delayed teleport system with ACK/bypass protocol
- Per-dimension zoom settings (Overworld, Nether, End)
- Client-server networking via NeoForge CustomPacketPayload

### Notes

- Java 25+ required (NeoForge 26.1.2 requirement)
- Beta release — mixin-based features (command interception, Waystones/JourneyMap integration) temporarily disabled
- EntityTeleportEvent provides basic server-side hook
- Mixins will be re-enabled in upcoming releases as they are verified stable