# Graph Report - 26.1.2  (2026-07-29)

## Corpus Check
- 95 files · ~96,758 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 1003 nodes · 1912 edges · 171 communities (68 shown, 103 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 5 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `f8725317`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- Minecraft
- TeleportServer
- TeleportTransitionController.java
- .isRunning
- TeleportConfig
- StartServerTeleportPayload
- .handleWarpPlateTeleport
- Flujo de trabajo — Teleport Animation (NeoForge)
- TeleportClient
- LocalPlayer
- Changelog
- LevelRendererMixin.java
- .getCameraFrame
- WaystonesTeleportHandler
- TeleportStepEffectRenderer
- KeyboardInputMixin.java
- CurseForge — Variables del proyecto
- ZoomDimension
- TeleportMixinPlugin
- Teleport Animation
- .enterBodyFrame
- .getTravelEndTick
- Teleport Animation 0.0.0-beta.2
- Teleport Animation 0.0.0-beta.3
- Teleport Animation 0.0.0-beta.4
- Teleport Animation 0.0.0-beta.5
- Teleport Animation 0.0.0-beta.9
- Teleport Animation 0.0.0-beta.10
- Teleport Animation 0.0.0-beta.12
- Teleport Animation 0.0.0-beta.13
- Teleport Animation 0.0.0-beta.14
- gradlew
- TeleportModMenu
- .tick
- ServerPlayerMixin.java
- Post
- ResourceKey
- FogRendererMixin.java
- .scheduleTerrainUpdate
- IrisCompat
- DistantHorizonsCompat.java
- VoxyCompat.java
- Inject
- Mixin
- CallbackInfoReturnable
- Inject
- Mixin
- Pseudo
- CallbackInfoReturnable
- Entity
- Inject
- Mixin
- ServerLevel
- CallbackInfoReturnable
- Inject
- Logger
- Mixin
- CallbackInfoReturnable
- Camera
- DeltaTracker
- Inject
- Mixin
- Mixin
- Mixin
- CallbackInfo
- Inject
- Mixin
- CallbackInfo
- Inject
- Mixin
- Pseudo
- CallbackInfoReturnable
- Inject
- Mixin
- Pseudo
- CallbackInfoReturnable
- Inject
- Mixin
- Pseudo
- CallbackInfo
- Inject
- Mixin
- CallbackInfo
- Inject
- Mixin
- CallbackInfo
- Inject
- Mixin
- Mixin
- CallbackInfoReturnable
- Inject
- Mixin
- Pseudo
- CallbackInfoReturnable
- Inject
- Mixin
- Pseudo
- CallbackInfo
- Entity
- Inject
- ItemStack
- Mixin
- Pseudo
- Minecraft
- Post
- Logger
- Vec3
- Level
- ResourceKey
- Level
- ResourceKey
- ServerPlayer
- Vec3
- Level
- ResourceKey
- ServerPlayer
- Vec3
- SoundEvent
- Vec3
- Entity
- Level
- Logger
- ResourceKey
- Vec3
- BlockPos
- Entity
- ItemStack
- Level
- ResourceKey
- ServerPlayer

## God Nodes (most connected - your core abstractions)
1. `TeleportTransitionController` - 261 edges
2. `TeleportConfig` - 122 edges
3. `Changelog` - 46 edges
4. `TeleportClient` - 22 edges
5. `CameraFrame` - 18 edges
6. `TeleportServer` - 15 edges
7. `Flujo de trabajo — Teleport Animation (NeoForge)` - 14 edges
8. `TeleportStepEffectRenderer` - 13 edges
9. `CurseForge — Variables del proyecto` - 13 edges
10. `TeleportClientNetworking` - 10 edges

## Surprising Connections (you probably didn't know these)
- `TeleportTransitionController` --references--> `CameraType`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 28 → community 8_
- `TeleportTransitionController` --references--> `CameraFrame`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 28 → community 15_
- `TeleportTransitionController` --references--> `FadingTravelSound`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 28 → community 14_
- `TeleportTransitionController` --references--> `Vec3`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 28 → community 2_
- `CameraFrame` --references--> `Vec3`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 2 → community 15_

## Import Cycles
- None detected.

## Communities (171 total, 103 thin omitted)

### Community 1 - "TeleportServer"
Cohesion: 0.06
Nodes (17): IEventBus, MinecraftServer, Mod, RegisterPayloadHandlersEvent, EntityMixin, WaystonesWarpPlateBlockEntityMixin, TeleportAnimation, Level (+9 more)

### Community 3 - ".isRunning"
Cohesion: 0.07
Nodes (10): ComputeCameraAngles, Invoker, CameraAccessor, CameraMixin, LeawindThirdPersonEventsNeoForgeMixin, LeawindThirdPersonImplMixin, LeawindThirdPersonLegacyMixin, ScreenEffectRendererMixin (+2 more)

### Community 5 - "StartServerTeleportPayload"
Cohesion: 0.07
Nodes (13): CustomPacketPayload, FriendlyByteBuf, Identifier, Override, DimensionIds, TeleportClientNetworking, BypassNextServerTeleportPayload, ServerTeleportAckPayload (+5 more)

### Community 6 - ".handleWarpPlateTeleport"
Cohesion: 0.40
Nodes (5): 0.0.0-beta.2 (2026-07-13), Added, Changed, Compatibility, Removed

### Community 7 - "Flujo de trabajo — Teleport Animation (NeoForge)"
Cohesion: 0.04
Nodes (44): 1. Desarrollo, 2. Copiar a instancia de pruebas, 3. Probar en instancia, 4. Preparar versión para CurseForge, 5. Release estable, 6. Actualizar Knowledge Graph (Graphify), Archivos de CurseForge, Archivos que pasan a GitHub (+36 more)

### Community 8 - "TeleportClient"
Cohesion: 0.10
Nodes (15): CameraType, ChatFormatting, CommandDispatcher, CommandSourceStack, Component, EventBusSubscriber, FMLClientSetupEvent, RegisterClientCommandsEvent (+7 more)

### Community 13 - "Changelog"
Cohesion: 0.04
Nodes (45): 0.0.0-beta.10 (2026-07-13), 0.0.0-beta.11 (2026-07-14), 0.0.0-beta.12 (2026-07-14), 0.0.0-beta.13 (2026-07-14), 0.0.0-beta.14 (2026-07-14), 0.0.0-beta.15 (2026-07-16), 0.0.0-beta.16 (2026-07-19), 0.0.0-beta.17 (2026-07-19) (+37 more)

### Community 14 - "LevelRendererMixin.java"
Cohesion: 0.05
Nodes (35): AbstractTickableSoundInstance, ChunkSectionsToRender, ClientLevel, ClientLevelData, Field, Frustum, GpuBufferSlice, GraphicsResourceAllocator (+27 more)

### Community 16 - "WaystonesTeleportHandler"
Cohesion: 0.21
Nodes (3): WaystonesPlayerWaystoneManagerMixin, WaystonesTeleportHandler, SuppressWarnings

### Community 18 - "TeleportStepEffectRenderer"
Cohesion: 0.07
Nodes (5): GuiGraphicsExtractor, IrisCompat, GuiMixin, TeleportStepEffectRenderer, BlockPos

### Community 20 - "KeyboardInputMixin.java"
Cohesion: 0.21
Nodes (3): MouseButtonInfo, KeyboardInputMixin, MouseHandlerMixin

### Community 22 - "CurseForge — Variables del proyecto"
Cohesion: 0.14
Nodes (13): Changelog, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, Parámetros del upload, Proyecto, Rama (+5 more)

### Community 23 - "ZoomDimension"
Cohesion: 0.29
Nodes (5): fromLevel(), ZoomDimension, END, NETHER, OVERWORLD

### Community 24 - "TeleportMixinPlugin"
Cohesion: 0.23
Nodes (4): ClassNode, IMixinConfigPlugin, IMixinInfo, TeleportMixinPlugin

### Community 26 - "Teleport Animation"
Cohesion: 0.18
Nodes (10): Building from source, Configuration, Credits, Features, Installation, Integrations, License, Requirements (+2 more)

### Community 27 - ".enterBodyFrame"
Cohesion: 0.50
Nodes (3): CLAUDE.md — teleport_animation (26.1.2), Paso 0 obligatorio, Prioridad de instrucciones

### Community 32 - "Teleport Animation 0.0.0-beta.2"
Cohesion: 0.33
Nodes (5): Compatible mods, Notes, Teleport Animation 0.0.0-beta.2, Testing, What's included

### Community 33 - "Teleport Animation 0.0.0-beta.3"
Cohesion: 0.33
Nodes (5): Changes since beta.2, Compatible mods, Notes, Teleport Animation 0.0.0-beta.3, What's included

### Community 34 - "Teleport Animation 0.0.0-beta.4"
Cohesion: 0.33
Nodes (5): Changes since beta.3, Compatible mods, Notes, Teleport Animation 0.0.0-beta.4, What's included

### Community 35 - "Teleport Animation 0.0.0-beta.5"
Cohesion: 0.33
Nodes (5): Changes since beta.4, Compatible mods, Notes, Teleport Animation 0.0.0-beta.5, What's included

### Community 36 - "Teleport Animation 0.0.0-beta.9"
Cohesion: 0.33
Nodes (5): Changes since beta.8, Compatible mods, Notes, Teleport Animation 0.0.0-beta.9, What's included

### Community 37 - "Teleport Animation 0.0.0-beta.10"
Cohesion: 0.40
Nodes (4): Changes since beta.9, Notes, Teleport Animation 0.0.0-beta.10, What's included

### Community 38 - "Teleport Animation 0.0.0-beta.12"
Cohesion: 0.50
Nodes (3): Changes since beta.11, Known issues, Teleport Animation 0.0.0-beta.12

### Community 39 - "Teleport Animation 0.0.0-beta.13"
Cohesion: 0.50
Nodes (3): Changes since beta.12, Known issues, Teleport Animation 0.0.0-beta.13

### Community 40 - "Teleport Animation 0.0.0-beta.14"
Cohesion: 0.50
Nodes (3): Changes since beta.13, Known issues, Teleport Animation 0.0.0-beta.14

### Community 41 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 77 - "VoxyCompat.java"
Cohesion: 0.09
Nodes (5): BobbyCompat, DistantHorizonsCompat, DistantHorizonsRenderUtilMixin, VoxyClientMixin, VoxyCompat

## Knowledge Gaps
- **140 isolated node(s):** `OVERWORLD`, `NETHER`, `END`, `GameRendererMixin`, `GuiMixin` (+135 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **103 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TeleportTransitionController` connect `.getTravelEndTick` to `Minecraft`, `TeleportTransitionController.java`, `.isRunning`, `StartServerTeleportPayload`, `TeleportClient`, `LocalPlayer`, `VoxyCompat.java`, `LevelRendererMixin.java`, `.getCameraFrame`, `TeleportStepEffectRenderer`, `.getPullEndTick`, `KeyboardInputMixin.java`, `.tick`, `ZoomDimension`?**
  _High betweenness centrality (0.226) - this node is a cross-community bridge._
- **Why does `TeleportConfig` connect `TeleportConfig` to `TeleportServer`, `StartServerTeleportPayload`, `TeleportClient`, `.save`, `.getPullEndTick`, `KeyboardInputMixin.java`, `.tick`, `.applyConfigProperties`, `ZoomDimension`, `.clamp`, `FrustumMixin.java`, `MinecraftMixin.java`, `.load`?**
  _High betweenness centrality (0.134) - this node is a cross-community bridge._
- **What connects `OVERWORLD`, `NETHER`, `END` to the rest of the system?**
  _140 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Minecraft` be split into smaller, more focused modules?**
  _Cohesion score 0.14814814814814814 - nodes in this community are weakly interconnected._
- **Should `TeleportServer` be split into smaller, more focused modules?**
  _Cohesion score 0.06464646464646465 - nodes in this community are weakly interconnected._
- **Should `.isRunning` be split into smaller, more focused modules?**
  _Cohesion score 0.06666666666666667 - nodes in this community are weakly interconnected._
- **Should `TeleportConfig` be split into smaller, more focused modules?**
  _Cohesion score 0.043478260869565216 - nodes in this community are weakly interconnected._