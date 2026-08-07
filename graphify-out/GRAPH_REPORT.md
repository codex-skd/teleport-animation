# Graph Report - 26.2  (2026-08-07)

## Corpus Check
- 68 files · ~91,933 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 858 nodes · 1769 edges · 156 communities (54 shown, 102 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 5 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `d70f3007`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- TeleportTransitionController.java
- TeleportStepEffectRenderer
- TeleportConfig
- Flujo de trabajo — Teleport Animation (NeoForge)
- TeleportClient
- StartServerTeleportPayload
- Minecraft
- TeleportTransitionController
- TeleportServer
- .getPullEndTick
- .getCameraFrame
- .tick
- .delayTeleportContext
- FogRendererMixin.java
- .teleportAnimation$restoreGtpCameraAfterLeawind
- MouseHandlerMixin.java
- .applyConfigProperties
- LocalPlayer
- MinecraftMixin.java
- CurseForge — Variables del proyecto
- ZoomDimension
- TeleportMixinPlugin
- Teleport Animation
- EntityMixin.java
- ScreenEffectRendererMixin.java
- CLAUDE.md — teleport_animation (26.2)
- gradlew
- Changelog
- build.gradle
- settings.gradle
- .getZoomStageGlideHeight
- 1.1.1 (2026-08-05)
- 1.1.1 (2026-08-05)
- TeleportModMenu
- ServerPlayerMixin.java
- 1.1.4 (2026-08-06)
- Post
- ResourceKey
- Mixin
- Vec3
- CallbackInfo
- DeltaTracker
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
- CallbackInfoReturnable
- Inject
- Mixin
- Pseudo
- CallbackInfoReturnable
- Inject
- Mixin
- Pseudo
- Mixin
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
- Logger
- Vec3
- Level
- ResourceKey
- ServerPlayer
- Vec3
- 1.1.2 (2026-08-05)
- 1.1.2 (2026-08-05)
- 1.1.5 (2026-08-06)
- 1.1.6 (2026-08-06)
- 1.1.7 (2026-08-06)
- 1.1.8 (2026-08-07)
- Level
- ResourceKey
- ServerPlayer
- Vec3
- SoundEvent
- VoxyClientMixin.java
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
- 1.0.1 (2026-08-02)
- .getZoomInStageTicks
- 1.0.1 (2026-08-02)

## God Nodes (most connected - your core abstractions)
1. `TeleportTransitionController` - 271 edges
2. `TeleportConfig` - 81 edges
3. `Changelog` - 23 edges
4. `TeleportClient` - 22 edges
5. `CameraFrame` - 19 edges
6. `TeleportServer` - 15 edges
7. `ZoomDimension` - 14 edges
8. `TeleportStepEffectRenderer` - 14 edges
9. `CurseForge — Variables del proyecto` - 13 edges
10. `Flujo de trabajo — Teleport Animation (NeoForge)` - 11 edges

## Surprising Connections (you probably didn't know these)
- `TeleportConfig` --references--> `Builder`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportConfig.java →   _Bridges community 2 → community 33_
- `fromLevel()` --references--> `ZoomDimension`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportConfig.java → src/main/java/com/skd/teleport_animation/TeleportConfig.java  _Bridges community 25 → community 33_
- `TeleportTransitionController` --references--> `CameraType`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 7 → community 4_
- `TeleportTransitionController` --references--> `CameraFrame`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 7 → community 9_
- `TeleportTransitionController` --references--> `FadingTravelSound`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 7 → community 19_

## Import Cycles
- None detected.

## Communities (156 total, 102 thin omitted)

### Community 1 - "TeleportStepEffectRenderer"
Cohesion: 0.07
Nodes (5): GuiMixin, GuiGraphicsExtractor, Vec3, TeleportStepEffectRenderer, BlockPos

### Community 3 - "Flujo de trabajo — Teleport Animation (NeoForge)"
Cohesion: 0.17
Nodes (11): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Teleport Animation (NeoForge), Flujo por tarea, Idioma (+3 more)

### Community 4 - "TeleportClient"
Cohesion: 0.12
Nodes (15): CameraType, ChatFormatting, CommandDispatcher, CommandSourceStack, Component, EventBusSubscriber, FMLClientSetupEvent, ClientModEvents (+7 more)

### Community 5 - "StartServerTeleportPayload"
Cohesion: 0.08
Nodes (12): CustomPacketPayload, FriendlyByteBuf, TeleportClientNetworking, BypassNextServerTeleportPayload, ServerTeleportAckPayload, StartServerTeleportPayload, TeleportNetworkPayloads, TeleportSounds (+4 more)

### Community 8 - "TeleportServer"
Cohesion: 0.07
Nodes (18): DimensionIds, EntityMixin, TeleportServerNetworking, IEventBus, MinecraftServer, Mod, ModContainer, RegisterPayloadHandlersEvent (+10 more)

### Community 12 - ".delayTeleportContext"
Cohesion: 0.23
Nodes (3): WaystonesPlayerWaystoneManagerMixin, WaystonesTeleportHandler, SuppressWarnings

### Community 14 - ".teleportAnimation$restoreGtpCameraAfterLeawind"
Cohesion: 0.17
Nodes (9): ComputeCameraAngles, CameraAccessor, CameraMixin, Invoker, CallbackInfo, Inject, Mixin, Pseudo (+1 more)

### Community 19 - ".applyConfigProperties"
Cohesion: 0.20
Nodes (7): AbstractTickableSoundInstance, FadingTravelSound, Camera, Level, Logger, ResourceKey, SoundEvent

### Community 20 - "LocalPlayer"
Cohesion: 0.32
Nodes (5): ClientLevelData, GameRendererMixin, LevelRendererMixin, LevelHeightAccessor, Redirect

### Community 24 - "CurseForge — Variables del proyecto"
Cohesion: 0.14
Nodes (13): Changelog, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, Parámetros del upload, Proyecto, Rama (+5 more)

### Community 25 - "ZoomDimension"
Cohesion: 0.21
Nodes (4): ZoomDimension, END, NETHER, OVERWORLD

### Community 26 - "TeleportMixinPlugin"
Cohesion: 0.23
Nodes (4): ClassNode, TeleportMixinPlugin, IMixinConfigPlugin, IMixinInfo

### Community 28 - "Teleport Animation"
Cohesion: 0.18
Nodes (10): Building from source, Configuration, Credits, Features, Installation, Integrations, License, Requirements (+2 more)

### Community 29 - "EntityMixin.java"
Cohesion: 0.05
Nodes (10): BobbyCompat, DistantHorizonsCompat, IrisCompat, DistantHorizonsRenderUtilMixin, VoxyClientMixin, WaystonesWarpPlateBlockEntityMixin, SodiumCompat, VoxyCompat (+2 more)

### Community 32 - "CLAUDE.md — teleport_animation (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — teleport_animation (26.2), Prioridad de instrucciones, Workflow del mod

### Community 33 - "gradlew"
Cohesion: 0.28
Nodes (7): Builder, ConfigValue, ModConfigSpec, DimensionHeights, fromLevel(), Level, ResourceKey

### Community 34 - "Changelog"
Cohesion: 0.15
Nodes (12): 0.0.0-beta.1 (2026-07-30), 0.0.0-beta.1 (2026-07-30), 0.0.0-beta.2 (2026-07-30), 0.0.0-beta.2 (2026-07-30), 1.0.0 (2026-07-31), 1.0.0 (2026-07-31), 1.0.1 (2026-08-02), [1.1.3] - 2026-08-05 (+4 more)

### Community 40 - "settings.gradle"
Cohesion: 0.50
Nodes (4): 1.1.0 (2026-08-05), Feature, Fix, Refactor

### Community 41 - ".getZoomStageGlideHeight"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 47 - "1.1.1 (2026-08-05)"
Cohesion: 0.50
Nodes (4): 1.1.1 (2026-08-05), Fix, Notes, Technical

### Community 48 - "1.1.1 (2026-08-05)"
Cohesion: 0.50
Nodes (4): 1.1.0 (2026-08-05), Feature, Fix, Refactor

### Community 52 - "1.1.4 (2026-08-06)"
Cohesion: 0.50
Nodes (4): 1.1.4 (2026-08-06), Feature, Fix, Technical

### Community 127 - "1.1.2 (2026-08-05)"
Cohesion: 0.67
Nodes (3): 1.1.2 (2026-08-05), Fix, Technical

### Community 128 - "1.1.2 (2026-08-05)"
Cohesion: 0.50
Nodes (4): 1.1.1 (2026-08-05), Fix, Notes, Technical

### Community 129 - "1.1.5 (2026-08-06)"
Cohesion: 0.67
Nodes (3): 1.1.5 (2026-08-06), Feature, Technical

### Community 130 - "1.1.6 (2026-08-06)"
Cohesion: 0.67
Nodes (3): 1.1.6 (2026-08-06), Fix, Technical

### Community 131 - "1.1.7 (2026-08-06)"
Cohesion: 0.67
Nodes (3): 1.1.8 (2026-08-07), Fix, Technical

### Community 132 - "1.1.8 (2026-08-07)"
Cohesion: 0.67
Nodes (3): 1.1.9 (2026-08-07), Fix, Technical

### Community 150 - "1.0.1 (2026-08-02)"
Cohesion: 0.67
Nodes (3): 1.1.2 (2026-08-05), Fix, Technical

### Community 153 - ".getZoomInStageTicks"
Cohesion: 0.67
Nodes (3): 1.1.7 (2026-08-06), Fix, Technical

## Knowledge Gaps
- **78 isolated node(s):** `OVERWORLD`, `NETHER`, `END`, `GameRendererMixin`, `GuiMixin` (+73 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **102 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TeleportTransitionController` connect `TeleportTransitionController` to `TeleportTransitionController.java`, `TeleportStepEffectRenderer`, `TeleportClient`, `StartServerTeleportPayload`, `Minecraft`, `.getPullEndTick`, `.getCameraFrame`, `.tick`, `FogRendererMixin.java`, `.teleportAnimation$restoreGtpCameraAfterLeawind`, `.fromResourceKey`, `.isRunning`, `MouseHandlerMixin.java`, `.applyConfigProperties`, `MinecraftMixin.java`, `LeawindThirdPersonImplMixin.java`, `ZoomDimension`, `EntityMixin.java`, `ScreenEffectRendererMixin.java`, `build.gradle`, `.requestTerrainVisibilityUpdate`, `.getZoomInStageTicks`, `FadingTravelSound`?**
  _High betweenness centrality (0.318) - this node is a cross-community bridge._
- **Why does `TeleportConfig` connect `TeleportConfig` to `TeleportTransitionController.java`, `gradlew`, `StartServerTeleportPayload`, `TeleportServer`, `.getPullEndTick`, `.getCameraFrame`, `.requestTerrainVisibilityUpdate`, `.isRunning`, `.save`, `ZoomDimension`, `EntityMixin.java`?**
  _High betweenness centrality (0.108) - this node is a cross-community bridge._
- **What connects `OVERWORLD`, `NETHER`, `END` to the rest of the system?**
  _78 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `TeleportStepEffectRenderer` be split into smaller, more focused modules?**
  _Cohesion score 0.07058001397624039 - nodes in this community are weakly interconnected._
- **Should `TeleportConfig` be split into smaller, more focused modules?**
  _Cohesion score 0.0625 - nodes in this community are weakly interconnected._
- **Should `TeleportClient` be split into smaller, more focused modules?**
  _Cohesion score 0.11561561561561562 - nodes in this community are weakly interconnected._
- **Should `StartServerTeleportPayload` be split into smaller, more focused modules?**
  _Cohesion score 0.07770582793709528 - nodes in this community are weakly interconnected._