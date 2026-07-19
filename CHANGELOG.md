# Changelog

## [1.0.0] - 2026-07-19

### Added
- First stable release — all beta features polished and production-ready

### Fix
- Waystones 21.1.37 compatibility: added `tryTeleportAsync` interception
- Removed dead mixins targeting removed Waystones API

### Changed
- Project structure aligned with WORKFLOW_GENERIC conventions
- All Grand Teleport branding removed — fully rebranded to Teleport Animation

## [0.0.0-beta.10] - 2026-07-19

### Fix
- Added tryTeleportAsync interception to WaystonesInternalMethodsMixin for Waystones 21.1.37 compatibility

## [0.0.0-beta.9] - 2026-07-17

### Fix
- Removed all gtalike/GTP/Grand Teleport branding references from code
- Fixed WaystonesInternalMethodsMixin with @Coerce annotation
- Updated lang file keys to teleport_animation prefix
- Added bypass flag to prevent Waystones mixin recursion (ConcurrentHashMap tracking)

## [0.0.0-beta.8] - 2026-07-17

### Added
- Waystones required dependency
- WaystonesInternalMethodsMixin for proper Waystones teleport interception

## [0.0.0-beta.7] - 2026-07-17

### Changed
- Animation now only triggers for Waystones teleports (removed /tp command interception)

## [0.0.0-beta.6] - 2026-07-17

### Fix
- Animation freeze mid-transition: ++ticks was inside command send condition, preventing further animation after command dispatch

## [0.0.0-beta.5] - 2026-07-17

### Changed
- Added debug logs with TA prefix for transition troubleshooting
- Removed all GTP references from code

### Fix
- GuiMixin and LevelRendererMixin signature fixes for Minecraft 1.21.1 DeltaTracker API

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
