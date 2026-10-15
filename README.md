# Teleport Animation — NeoForge 1.21.1
> This mod is a fork of **Grand Teleport** (Forge 1.20.1) by Codex, with contributions by hookuru_. Ported to NeoForge by Stalking Dragons. Not affiliated with the original authors.

Adds a cinematic camera transition when the player teleports via **Waystones** or **Create: Kinetic Waypoints**.

## Features

- Smooth camera transition with configurable zoom-in/out stages
- Custom sound effects (7 OGG sounds)
- Player freeze during transition (configurable)
- Compatible with Sodium, Iris, Bobby, Distant Horizons, Voxy
- Commands: `/ta on`, `/ta off`, `/ta status`, `/ta player_freeze`

## Requirements

- Minecraft 1.21.1
- NeoForge 21.1.235+
- At least one of these (both optional, each enables its own integration):
  - [Waystones](https://curseforge.com/minecraft/mc-mods/waystones)
  - [Create: Kinetic Waypoints](https://www.curseforge.com/minecraft/mc-mods/create-kinetic-waypoints) 0.1.0+

## Installation

1. Download the latest JAR from [CurseForge](https://curseforge.com/minecraft/mc-mods/teleport-animation)
2. Place it in the `mods/` folder of your client/server
3. Install Waystones and/or Create: Kinetic Waypoints

## Usage

- `/ta on` — Enable the effect
- `/ta off` — Disable the effect
- `/ta status` — Show current status
- `/ta player_freeze on/off` — Freeze player during transition

The animation triggers automatically when teleporting via Waystones (warp plates, warp stones, or scrolls) or via a Kinetic Waypoint. Vanilla `/tp` is not animated.

## Credits & License

- **Codex** — original [Grand Teleport](https://www.curseforge.com/minecraft/mc-mods/grand-teleport-gtp) mod for Forge 1.20.1 (MIT)
- **hookuru_** — contributions to the original mod
- **Stalking Dragons** — NeoForge / current-Forge port and rework

The **code** is under the **MIT** license — see [LICENSE](LICENSE). The **bundled sound
effects** are third-party audio carried over from Grand Teleport: not original to this
project, not covered by the MIT grant, included only for non-commercial in-game use as
part of this mod. Do not extract or redistribute the sound files separately. The mod's
declared license is therefore "Custom" (MIT code + restricted bundled sounds).
