# Changelog

## [0.0.0-beta.2] - 2026-08-18

### Fix
- **Versión de NeoForge mal resuelta en build**: `legacyForge { version = "..." }` resolvía `net.minecraftforge:forge` (proyecto Forge original, no relacionado, con numeración propia hasta 47.4.x) en vez de `net.neoforged:forge` (rama legacy real de NeoForge para 1.20.1, que se quedó en 47.1.x). El JAR de beta.1 exigía Forge 47.4.22, versión inexistente para instalaciones reales de NeoForge — el mod no cargaba ("requires forge 47.4.22 or above").
- `ResourceLocation.fromNamespaceAndPath` no existe en 1.20.1 (API de 1.21+). Sustituido por el constructor `new ResourceLocation(namespace, path)` en `TeleportSounds`.

### Technical
- `build.gradle`: `legacyForge { version = "..." }` → `legacyForge { enable { neoForgeVersion = "..." } }`
- `gradle.properties`: `forge_version`/`loader_version` corregidos a `47.1.99` (rama legacy real de NeoForge 1.20.1)

## [0.0.0-beta.1] - 2026-08-18

### Added
- Port completo de Teleport Animation desde NeoForge 1.21.1 a NeoForge 1.20.1 (legacyforge)
- Todos los mixins, config, y compatibilidades preservados (Waystones, JourneyMap, Sodium, Iris, Distant Horizons, Bobby, Voxy, Leawind's Third Person)

### Technical
- Networking reescrito de la API moderna `StreamCodec`/`RegisterPayloadHandlersEvent` (1.20.4+) a la API legacy `SimpleChannel`/`NetworkRegistry` de NeoForge 1.20.1
- Sustituido `DeltaTracker` (introducido en 1.21) por `float partialTick` en los mixins de render
- Namespace de API del mod loader ajustado de `net.neoforged.*` a `net.minecraftforge.*` (legacyforge)
