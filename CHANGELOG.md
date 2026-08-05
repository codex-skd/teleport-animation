# Changelog

## 1.1.0 (2026-08-05)

### Fix
- Floor flicker during the top-down travel flight — `requestTerrainVisibilityUpdate` no longer forces a full `invalidateCompiledGeometry()` rebuild of all terrain on every chunk section crossed while the camera flies; only a lightweight Sodium terrain update runs during travel now.

### Feature
- Config migrated from a custom `.properties` file to a native NeoForge `ModConfigSpec` (`config/teleport_animation-client.toml`), auto-created on first launch and editable from the Configured mod. Public `TeleportConfig` API unchanged.

### Refactor
- Removed dead code from a removed custom config-screen (`configLayout*`/`configWidget.*`/`configText.*` fields) and dropped the legacy `.properties` migration from the old "Grand Teleport" fork, which no longer applies to the new format.

## 1.0.1 (2026-08-02)

### Refactor
- Config renombrado a `config/teleport_animation.properties` con migración automática desde `grand_teleport.properties`.
- Eliminados residuos de fork "Grand Teleport"/"GTP" (strings de config, lang, pack.mcmeta, métodos `restoreGtpCameraAfterLeawind` y `handleGtaTeleportCommand`).

## 1.0.0 (2026-07-31)

- First stable release. Teleport animation confirmed working on Minecraft 26.2.

## 0.0.0-beta.2 (2026-07-30)

- fix: crash on startup — `MinecraftMixin` targeted `Minecraft.setScreen`, which moved to `Gui.setScreen` in this MC version (screen management now lives on `Minecraft.gui`, not `Minecraft` itself). Retargeted the mixin to `Gui.class`.

## 0.0.0-beta.1 (2026-07-30)

- feat: initial port of Teleport Animation to Minecraft 26.2 / NeoForge 26.2.0.32-beta, based on the 26.1.2 codebase
