# Changelog

## 0.0.0-beta.2 (2026-07-30)

- fix: crash on startup — `MinecraftMixin` targeted `Minecraft.setScreen`, which moved to `Gui.setScreen` in this MC version (screen management now lives on `Minecraft.gui`, not `Minecraft` itself). Retargeted the mixin to `Gui.class`.

## 0.0.0-beta.1 (2026-07-30)

- feat: initial port of Teleport Animation to Minecraft 26.2 / NeoForge 26.2.0.32-beta, based on the 26.1.2 codebase
