# Graph Report - 26.2  (2026-08-06)

## Corpus Check
- 62 files · ~90,245 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 832 nodes · 1737 edges · 135 communities (35 shown, 100 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 5 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `cceae80c`
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
- .tick
- .delayTeleportContext
- FogRendererMixin.java
- .teleportAnimation$restoreGtpCameraAfterLeawind
- .fromResourceKey
- MouseHandlerMixin.java
- LocalPlayer
- MinecraftMixin.java
- LeawindThirdPersonImplMixin.java
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
- KeyboardInputMixin.java
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
- ResourceKey
- ServerPlayer
- Vec3
- SoundEvent
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
1. `TeleportTransitionController` - 270 edges
2. `TeleportConfig` - 81 edges
3. `TeleportClient` - 22 edges
4. `CameraFrame` - 18 edges
5. `Changelog` - 17 edges
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
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 7 → community 11_
- `TeleportTransitionController` --references--> `FadingTravelSound`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 7 → community 0_

## Import Cycles
- None detected.

## Communities (135 total, 100 thin omitted)

### Community 0 - "TeleportTransitionController.java"
Cohesion: 0.17
Nodes (7): AbstractTickableSoundInstance, DistantHorizonsRenderUtilMixin, FadingTravelSound, Camera, Level, Logger, ResourceKey

### Community 1 - "TeleportStepEffectRenderer"
Cohesion: 0.07
Nodes (6): IrisCompat, GuiMixin, GuiGraphicsExtractor, Vec3, TeleportStepEffectRenderer, BlockPos

### Community 3 - "Flujo de trabajo — Teleport Animation (NeoForge)"
Cohesion: 0.17
Nodes (11): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Teleport Animation (NeoForge), Flujo por tarea, Idioma (+3 more)

### Community 4 - "TeleportClient"
Cohesion: 0.07
Nodes (16): CameraType, ChatFormatting, CommandDispatcher, CommandSourceStack, Component, EventBusSubscriber, FMLClientSetupEvent, ClientModEvents (+8 more)

### Community 5 - "StartServerTeleportPayload"
Cohesion: 0.08
Nodes (13): CustomPacketPayload, FriendlyByteBuf, TeleportClientNetworking, BypassNextServerTeleportPayload, ServerTeleportAckPayload, StartServerTeleportPayload, TeleportNetworkPayloads, TeleportServerNetworking (+5 more)

### Community 6 - "Minecraft"
Cohesion: 0.07
Nodes (3): SodiumCompat, Minecraft, SoundEvent

### Community 8 - "TeleportServer"
Cohesion: 0.10
Nodes (16): EntityMixin, IEventBus, MinecraftServer, Mod, ModContainer, RegisterPayloadHandlersEvent, Post, TeleportAnimation (+8 more)

### Community 12 - ".delayTeleportContext"
Cohesion: 0.23
Nodes (3): WaystonesPlayerWaystoneManagerMixin, WaystonesTeleportHandler, SuppressWarnings

### Community 13 - "FogRendererMixin.java"
Cohesion: 0.32
Nodes (5): ClientLevelData, GameRendererMixin, LevelRendererMixin, LevelHeightAccessor, Redirect

### Community 14 - ".teleportAnimation$restoreGtpCameraAfterLeawind"
Cohesion: 0.08
Nodes (11): ComputeCameraAngles, CameraAccessor, CameraMixin, LeawindThirdPersonImplMixin, LeawindThirdPersonLegacyMixin, Invoker, CallbackInfo, Inject (+3 more)

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

### Community 29 - "EntityMixin.java"
Cohesion: 0.08
Nodes (7): BobbyCompat, DistantHorizonsCompat, VoxyClientMixin, WaystonesWarpPlateBlockEntityMixin, VoxyCompat, WaystonesWarpPlateHandler, Method

### Community 32 - "CLAUDE.md — teleport_animation (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — teleport_animation (26.2), Prioridad de instrucciones, Workflow del mod

### Community 33 - "gradlew"
Cohesion: 0.28
Nodes (7): Builder, ConfigValue, ModConfigSpec, DimensionHeights, fromLevel(), Level, ResourceKey

### Community 34 - "Changelog"
Cohesion: 0.05
Nodes (36): 0.0.0-beta.1 (2026-07-30), 0.0.0-beta.1 (2026-07-30), 0.0.0-beta.2 (2026-07-30), 0.0.0-beta.2 (2026-07-30), 1.0.0 (2026-07-31), 1.0.0 (2026-07-31), 1.0.1 (2026-08-02), 1.0.1 (2026-08-02) (+28 more)

### Community 41 - ".getZoomStageGlideHeight"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **65 isolated node(s):** `OVERWORLD`, `NETHER`, `END`, `GameRendererMixin`, `GuiMixin` (+60 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **100 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TeleportTransitionController` connect `TeleportTransitionController` to `TeleportTransitionController.java`, `TeleportStepEffectRenderer`, `TeleportClient`, `build.gradle`, `Minecraft`, `.getPullEndTick`, `.getCameraFrame`, `.tick`, `.teleportAnimation$restoreGtpCameraAfterLeawind`, `.fromResourceKey`, `.isRunning`, `KeyboardInputMixin.java`, `MouseHandlerMixin.java`, `LocalPlayer`, `MinecraftMixin.java`, `LeawindThirdPersonImplMixin.java`, `EntityMixin.java`, `ScreenEffectRendererMixin.java`?**
  _High betweenness centrality (0.337) - this node is a cross-community bridge._
- **Why does `TeleportConfig` connect `TeleportConfig` to `gradlew`, `TeleportClient`, `Minecraft`, `TeleportTransitionController`, `.getCameraFrame`, `KeyboardInputMixin.java`, `.save`, `.applyConfigProperties`, `ZoomDimension`?**
  _High betweenness centrality (0.115) - this node is a cross-community bridge._
- **Why does `StartServerTeleportPayload` connect `StartServerTeleportPayload` to `TeleportClient`?**
  _High betweenness centrality (0.036) - this node is a cross-community bridge._
- **What connects `OVERWORLD`, `NETHER`, `END` to the rest of the system?**
  _65 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `TeleportStepEffectRenderer` be split into smaller, more focused modules?**
  _Cohesion score 0.07184325108853411 - nodes in this community are weakly interconnected._
- **Should `TeleportConfig` be split into smaller, more focused modules?**
  _Cohesion score 0.0625 - nodes in this community are weakly interconnected._
- **Should `TeleportClient` be split into smaller, more focused modules?**
  _Cohesion score 0.07003367003367003 - nodes in this community are weakly interconnected._