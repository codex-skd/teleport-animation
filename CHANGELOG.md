# Changelog

## [0.0.0-beta.1] - 2026-08-18

### Added
- Port completo de Teleport Animation desde NeoForge 1.21.1 a NeoForge 1.20.1 (47.4.22, legacyforge)
- Todos los mixins, config, y compatibilidades preservados (Waystones, JourneyMap, Sodium, Iris, Distant Horizons, Bobby, Voxy, Leawind's Third Person)

### Technical
- Networking reescrito de la API moderna `StreamCodec`/`RegisterPayloadHandlersEvent` (1.20.4+) a la API legacy `SimpleChannel`/`NetworkRegistry` de NeoForge 1.20.1
- Sustituido `DeltaTracker` (introducido en 1.21) por `float partialTick` en los mixins de render
- Namespace de API del mod loader ajustado de `net.neoforged.*` a `net.minecraftforge.*` (legacyforge)
