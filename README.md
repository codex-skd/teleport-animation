# Teleport Animation
> This mod is a fork of **Grand Teleport** (Forge 1.20.1) by Codex, with contributions by hookuru_. Ported to NeoForge by Stalking Dragons. Not affiliated with the original authors.

[![NeoForge](https://img.shields.io/badge/NeoForge-26.1.2-blue)](https://neoforged.net/)
[![Minecraft](https://img.shields.io/badge/Minecraft-26.1.2-green)](https://www.minecraft.net/)
[![License](https://img.shields.io/badge/License-Custom-red)](LICENSE)

A Minecraft mod that adds cinematic GTA-style teleport transitions. When you use `/tp` or `/teleport`, instead of an instant cut, the camera smoothly zooms into the sky, glides across the world, and descends back to your destination — creating a seamless and immersive traveling experience.

## Features

- **Cinematic camera animation** — zoom out, travel, and zoom back in on arrival
- **3-stage zoom heights** — configurable pull and push stages per dimension
- **Custom sound effects** — distinctive audio for each animation step
- **Step flash overlay** — subtle screen flash at each zoom stage
- **Chunk-by-chunk mask fade** — terrain fades in/out following the camera
- **Player freeze** — optionally freeze movement during transitions
- **Full configuration** — adjustable via in-game GUI or `.properties` file
- **Post-release camera override** — smooth blend back to third-person
- **Cross-dimension support** — works across the Overworld, Nether, and End

## Integrations

Seamless compatibility with popular mods:

| Mod | Integration |
|-----|-------------|
| Waystones | Delayed teleports with full animation |
| JourneyMap | Intercepts teleport requests |
| Sodium | Terrain update scheduling |
| Iris Shaders | Hard terrain cut for shaders |
| Distant Horizons | Near-clip adjustment during travel |
| Bobby | Chunk rendering integration |
| Voxy | Terrain preference during travel |
| Leawind's Third Person | Preempts and restores camera |

## Requirements

- Minecraft 26.1.2
- NeoForge 26.1.2.78
- Java 25+

## Installation

1. Install NeoForge 26.1.2.78
2. Download the latest JAR from [CurseForge](https://www.curseforge.com/minecraft/mc-mods/teleport-animation)
3. Place the JAR in your `mods/` folder
4. Launch Minecraft

## Usage

| Command | Description |
|---------|-------------|
| `/ta on\|off` | Toggle the teleport effect |
| `/ta status` | Check if the effect is enabled |
| `/ta player_freeze on\|off` | Toggle player freeze during transitions |
| `/ta` | Open the configuration screen |

The mod automatically intercepts `/tp`, `/teleport`, and `/execute ... run tp` commands.

## Configuration

Edit `config/teleport_animation.properties` or use the in-game GUI to adjust:

- Zoom heights for each dimension (Overworld, Nether, End)
- Stage tick durations
- Glide settings (camera body height, glide height, tick duration)
- Sound volumes (custom and Minecraft)
- Transition toggles (external teleports, warp plates)
- GUI layout (position, size)
- Player freeze and model hide settings

## Building from source

```bash
./gradlew.bat clean build
```

The JAR will be in `build/libs/teleport_animation-26.1.2-neoforge-<version>.jar`.

## Credits & License

- **Codex** — original [Grand Teleport](https://www.curseforge.com/minecraft/mc-mods/grand-teleport-gtp) mod for Forge 1.20.1 (MIT)
- **hookuru_** — contributions to the original mod
- **Stalking Dragons** — NeoForge / current-Forge port and rework

The **code** is under the **MIT** license — see [LICENSE](LICENSE). The **bundled sound
effects** are third-party audio carried over from Grand Teleport: not original to this
project, not covered by the MIT grant, included only for non-commercial in-game use as
part of this mod. Do not extract or redistribute the sound files separately. The mod's
declared license is therefore "Custom" (MIT code + restricted bundled sounds).
