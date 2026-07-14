## Teleport Animation 0.0.0-beta.2

Second beta release — port of Grand Teleport from Forge 1.20.1 to NeoForge 26.1.2. Basic server-client teleport functionality tested and working.

### What's included

- Complete cinematic teleport animation engine with 3-stage zoom-out/zoom-in
- In-game config via `/gtp` command with full GUI
- Works with `/tp`, `/teleport`, `/execute ... run tp`
- Cross-dimension travel with loading screen handling
- Chunk fade masking and terrain visibility management
- Custom sound effects (7 unique sounds)
- Player freeze during transitions (configurable)
- Server-side delayed teleport system with ACK/bypass protocol
- Client-server networking via NeoForge CustomPacketPayload
- Per-dimension zoom settings (Overworld, Nether, End)
- Commands: `/gtp`, `/grandtp` with on/off/status/player_freeze subcommands

### Compatible mods

- **Waystones** — delayed teleports with animation (select waystone, inventory button, warp plates)
- **JourneyMap** — teleport interception with animation
- **Sodium** — terrain update scheduling
- **Iris Shaders** — hard terrain cut support
- **Distant Horizons** — near-clip adjustment
- **Bobby** — chunk rendering compatibility
- **Voxy** — terrain preference support
- **Leawind's Third Person** — automatic camera management

### Testing

- Basic teleport command interception verified (client-side)
- Server-client teleport protocol verified (ACK/bypass cycle)
- Compilation verified with NeoForge 26.1.2.78 and Java 25 toolchain

### Notes

- This is a beta release — some edge cases may still need polish
- The config screen GUI widgets are functional but the layout editor will be improved in a future update
- Java 25+ required (NeoForge 26.1.2 requirement)
