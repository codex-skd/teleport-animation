# Changelog

## [0.0.0-beta.4] - 2026-07-17

### Fix
- GuiMixin crash on startup: fixed `Gui.render` mixin signature for Minecraft 1.21.1 (DeltaTracker)

## [0.0.0-beta.3] - 2026-07-17

### Fix
- LevelRenderer crash on startup: fixed `renderLevel` mixin signature for Minecraft 1.21.1 (DeltaTracker)

## [0.0.0-beta.2] - 2026-07-16

### Changed
- WORKFLOW.md actualizado con nueva sección de Ramas y formato de tags `<mc-version>-neoforge-beta.X`
- Formato de JAR cambiado a `<mod_id>-<minecraft_version>-<framework>-<version>.jar`
- Tag corregido al nuevo formato (`1.21.1-neoforge-beta.1`)
- `.gitignore` ahora excluye archivos decompilados temporales

## [0.0.0-beta.1] - 2026-07-16

### Added
- Port completo de Grand Teleport desde Forge 1.20.1 a NeoForge 1.21.1 (21.1.238)
- Sistema de transición cinematográfica tipo GTA para teletransportes
- Animación de cámara con 3 etapas de zoom (in/out) configurables
- Sistema de sonidos personalizados (7 efectos OGG)
- Comandos `/ta` y `/tpanimation` con subcomandos on/off/status/player_freeze
- Integración con servidor: sistema de delayed teleport con ACK
- Integración con Waystones (teleport y warp plates)
- Compatibilidad con Sodium, Iris, Bobby, Distant Horizons, Voxy
- Pantalla de configuración con editor de layout
- Sistema de propiedades para configuración persistente
- Mixin condicional para ServerPlayer (teleport interception)
- Documentación de flujo de trabajo (WORKFLOW.md)
