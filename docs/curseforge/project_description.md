## About

**Teleport Animation** brings cinematic, GTA-style teleport transitions to Minecraft. Every time you use `/tp` or `/teleport`, instead of an instant cut, the camera smoothly zooms up into the sky, glides across the world, and descends back to your destination — creating a seamless and immersive traveling experience.

Whether you're using commands, Waystones teleports, JourneyMap waypoints, or cross‑dimension travel, the mod intercepts the teleport and plays a full animation before you arrive.

## Features

- **Cinematic camera animation** — the camera zooms out, travels above the terrain, and zooms back in on arrival
- **3‑stage zoom heights** — fully configurable pull and push stages for the zoom effect
- **Per‑dimension settings** — separate zoom heights for the Overworld, Nether, and End
- **Custom sounds** — distinctive sound effects for each animation step (camera zoom, step flash, travel wind)
- **Step flash overlay** — subtle screen flash at each zoom stage to enhance the transition feel
- **Chunk‑by‑chunk mask fade** — terrain fades out and in following the camera's direction of travel
- **Player freeze** — optionally freeze movement and look controls during the transition
- **Full configuration** — every parameter (heights, tick lengths, volumes, enabled/disabled) is adjustable via the in‑game config screen or a `.properties` file
- **Post‑release camera override** — smooth blend back to third‑person camera after the transition
- **Cross‑dimension support** — configurable, with a special fallback for dimension changes when chunks are still loading

## Integration

The mod is designed to work seamlessly with popular teleport‑related mods:

- **Waystones** — delayed teleports with full animation (select waystone, inventory button, warp plates)
- **JourneyMap** — intercepts teleport requests and plays the animation
- **Sodium** — schedules terrain updates to reduce visual glitches during transitions
- **Iris Shaders** — automatically uses hard terrain cut when shaders are active
- **Distant Horizons** — adjusts near‑clip plane and terrain rendering during travel
- **Bobby** — integrates with Bobby's rendering for smoother chunk fade
- **Voxy** — prefers Voxy‑only terrain during the travel phase
- **Leawind's Third Person** — preempts and restores third‑person camera automatically

## How to use

- **`/gtp on|off`** — toggle the teleport effect on/off
- **`/gtp status`** — check if the effect is enabled
- **`/gtp player_freeze on|off`** — toggle player freeze during transitions
- **`/gtp`** — open the configuration screen

The mod intercepts any `/tp`, `/teleport`, or `/execute ... run tp` command as well as teleport packets from Waystones and JourneyMap.

## Configuration

The configuration file is located at `config/grand_teleport.properties` and can be edited manually or through the in‑game GUI.

### Settings include:

| Group | Options |
|-------|---------|
| Zoom heights | 3 configurable heights for zoom‑out and zoom‑in stages (per dimension) |
| Stage ticks | Duration in ticks for each of the 3 zoom‑out and zoom‑in stages |
| Glide settings | Camera body height, glide height, and glide tick duration |
| Sounds | Toggle custom sounds, set Minecraft/custom sound volume |
| Transitions | Enable/disable external teleport transitions and warp plate transitions |
| Layout | Position and size of the configuration GUI elements |
| Freeze | Player freeze toggle, local player model hide ticks |
| Cross‑dimension | Enable/disable cross‑dimension animation travel |

## Requirements

- Minecraft: 26.1.2
- NeoForge: 26.1.2.78
- Java 25+

## Credits

- **Codex** — original Grand Teleport mod for Forge 1.20.1
- **hookuru_** — contributions to the original mod
- **SKD** — NeoForge port
