# Graph Report - 26.2  (2026-08-05)

## Corpus Check
- 58 files · ~87,863 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 790 nodes · 1642 edges · 150 communities (35 shown, 115 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 5 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `5317dfd8`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- TeleportTransitionController.java
- TeleportConfig
- Flujo de trabajo — Teleport Animation (NeoForge)
- TeleportClient
- StartServerTeleportPayload
- Minecraft
- TeleportServer
- .tick
- .delayTeleportContext
- FogRendererMixin.java
- .teleportAnimation$restoreGtpCameraAfterLeawind
- .fromResourceKey
- MouseHandlerMixin.java
- LocalPlayer
- MinecraftMixin.java
- LeawindThirdPersonImplMixin.java
- .playStepSound
- CurseForge — Variables del proyecto
- ZoomDimension
- TeleportMixinPlugin
- .clamp
- Teleport Animation
- EntityMixin.java
- ScreenEffectRendererMixin.java
- CLAUDE.md — teleport_animation (26.2)
- gradlew
- Changelog
- TeleportModMenu
- build.gradle
- 0.0.0-beta.1.md
- settings.gradle
- .getZoomStageGlideHeight
- Method
- DistantHorizonsCompat
- DistantHorizonsRenderUtilMixin.java
- VoxyClientMixin.java
- KeyboardInputMixin.java
- VoxyClientMixin.java
- 0.0.0-beta.2.md
- TeleportModMenu
- ServerPlayerMixin.java
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
- Level
- Logger
- ResourceKey
- ServerLevel
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
1. `TeleportTransitionController` - 263 edges
2. `TeleportConfig` - 81 edges
3. `TeleportClient` - 22 edges
4. `CameraFrame` - 18 edges
5. `ZoomDimension` - 14 edges
6. `TeleportServer` - 14 edges
7. `TeleportStepEffectRenderer` - 13 edges
8. `CurseForge — Variables del proyecto` - 13 edges
9. `Flujo de trabajo — Teleport Animation (NeoForge)` - 11 edges
10. `TeleportClientNetworking` - 10 edges

## Surprising Connections (you probably didn't know these)
- `TeleportConfig` --references--> `Builder`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportConfig.java →   _Bridges community 2 → community 33_
- `fromLevel()` --references--> `ZoomDimension`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportConfig.java → src/main/java/com/skd/teleport_animation/TeleportConfig.java  _Bridges community 25 → community 33_
- `TeleportTransitionController` --references--> `CameraType`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 27 → community 4_
- `TeleportTransitionController` --references--> `CameraFrame`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 27 → community 15_
- `TeleportTransitionController` --references--> `FadingTravelSound`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 27 → community 36_

## Import Cycles
- None detected.

## Communities (150 total, 115 thin omitted)

### Community 0 - "TeleportTransitionController.java"
Cohesion: 0.20
Nodes (7): DistantHorizonsRenderUtilMixin, BlockPos, Camera, Level, Logger, ResourceKey, SoundEvent

### Community 3 - "Flujo de trabajo — Teleport Animation (NeoForge)"
Cohesion: 0.17
Nodes (11): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Teleport Animation (NeoForge), Flujo por tarea, Idioma (+3 more)

### Community 4 - "TeleportClient"
Cohesion: 0.10
Nodes (15): CameraType, ChatFormatting, CommandDispatcher, CommandSourceStack, Component, EventBusSubscriber, FMLClientSetupEvent, ClientModEvents (+7 more)

### Community 5 - "StartServerTeleportPayload"
Cohesion: 0.08
Nodes (13): CustomPacketPayload, FriendlyByteBuf, TeleportClientNetworking, BypassNextServerTeleportPayload, ServerTeleportAckPayload, StartServerTeleportPayload, TeleportNetworkPayloads, TeleportServerNetworking (+5 more)

### Community 8 - "TeleportServer"
Cohesion: 0.29
Nodes (5): IEventBus, Mod, ModContainer, RegisterPayloadHandlersEvent, TeleportAnimation

### Community 11 - ".tick"
Cohesion: 0.22
Nodes (3): GuiMixin, TeleportStepEffectRenderer, GuiGraphicsExtractor

### Community 12 - ".delayTeleportContext"
Cohesion: 0.12
Nodes (4): DimensionIds, WaystonesPlayerWaystoneManagerMixin, WaystonesTeleportHandler, SuppressWarnings

### Community 13 - "FogRendererMixin.java"
Cohesion: 0.32
Nodes (5): ClientLevelData, GameRendererMixin, LevelRendererMixin, LevelHeightAccessor, Redirect

### Community 14 - ".teleportAnimation$restoreGtpCameraAfterLeawind"
Cohesion: 0.12
Nodes (9): ComputeCameraAngles, CameraAccessor, CameraMixin, Invoker, CallbackInfo, Inject, Mixin, Pseudo (+1 more)

### Community 15 - ".fromResourceKey"
Cohesion: 0.08
Nodes (3): BodyCameraHeights, CameraFrame, Vec3

### Community 23 - ".playStepSound"
Cohesion: 0.18
Nodes (4): PendingTeleport, TeleportServer, MinecraftServer, Post

### Community 24 - "CurseForge — Variables del proyecto"
Cohesion: 0.14
Nodes (13): Changelog, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, Parámetros del upload, Proyecto, Rama (+5 more)

### Community 25 - "ZoomDimension"
Cohesion: 0.29
Nodes (4): ZoomDimension, END, NETHER, OVERWORLD

### Community 26 - "TeleportMixinPlugin"
Cohesion: 0.23
Nodes (4): ClassNode, TeleportMixinPlugin, IMixinConfigPlugin, IMixinInfo

### Community 28 - "Teleport Animation"
Cohesion: 0.18
Nodes (10): Building from source, Configuration, Credits, Features, Installation, Integrations, License, Requirements (+2 more)

### Community 32 - "CLAUDE.md — teleport_animation (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — teleport_animation (26.2), Prioridad de instrucciones, Workflow del mod

### Community 33 - "gradlew"
Cohesion: 0.28
Nodes (7): Builder, ConfigValue, ModConfigSpec, DimensionHeights, fromLevel(), Level, ResourceKey

### Community 34 - "Changelog"
Cohesion: 0.29
Nodes (6): 0.0.0-beta.1 (2026-07-30), 0.0.0-beta.2 (2026-07-30), 1.0.0 (2026-07-31), 1.0.1 (2026-08-02), Changelog, Refactor

### Community 41 - ".getZoomStageGlideHeight"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **43 isolated node(s):** `OVERWORLD`, `NETHER`, `END`, `GameRendererMixin`, `GuiMixin` (+38 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **115 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TeleportTransitionController` connect `.clamp` to `TeleportTransitionController.java`, `TeleportStepEffectRenderer`, `TeleportClient`, `Minecraft`, `TeleportTransitionController`, `.getPullEndTick`, `.getCameraFrame`, `.tick`, `.delayTeleportContext`, `.teleportAnimation$restoreGtpCameraAfterLeawind`, `.fromResourceKey`, `.isRunning`, `MouseHandlerMixin.java`, `LocalPlayer`, `MinecraftMixin.java`, `LeawindThirdPersonImplMixin.java`, `ScreenEffectRendererMixin.java`, `ServerPlayerMixin.java`, `TeleportModMenu`, `build.gradle`?**
  _High betweenness centrality (0.352) - this node is a cross-community bridge._
- **Why does `TeleportConfig` connect `TeleportConfig` to `gradlew`, `TeleportClient`, `Minecraft`, `.getCameraFrame`, `.delayTeleportContext`, `.fromResourceKey`, `.save`, `.applyConfigProperties`, `.playStepSound`, `ZoomDimension`, `.load`?**
  _High betweenness centrality (0.124) - this node is a cross-community bridge._
- **Why does `StartServerTeleportPayload` connect `StartServerTeleportPayload` to `TeleportClient`?**
  _High betweenness centrality (0.039) - this node is a cross-community bridge._
- **What connects `OVERWORLD`, `NETHER`, `END` to the rest of the system?**
  _43 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `TeleportConfig` be split into smaller, more focused modules?**
  _Cohesion score 0.06451612903225806 - nodes in this community are weakly interconnected._
- **Should `TeleportClient` be split into smaller, more focused modules?**
  _Cohesion score 0.09513742071881606 - nodes in this community are weakly interconnected._
- **Should `StartServerTeleportPayload` be split into smaller, more focused modules?**
  _Cohesion score 0.07536231884057971 - nodes in this community are weakly interconnected._