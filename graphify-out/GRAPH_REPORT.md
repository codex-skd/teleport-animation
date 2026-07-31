# Graph Report - 26.2  (2026-07-31)

## Corpus Check
- 57 files · ~92,692 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 859 nodes · 1991 edges · 46 communities (37 shown, 9 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 5 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `5a79b6ed`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- TeleportTransitionController.java
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
- ServerPlayerMixin.java
- TeleportModMenu
- .getZoomStageGlideHeight
- DistantHorizonsRenderUtilMixin.java
- VoxyClientMixin.java

## God Nodes (most connected - your core abstractions)
1. `TeleportTransitionController` - 263 edges
2. `TeleportConfig` - 122 edges
3. `TeleportClient` - 22 edges
4. `CameraFrame` - 18 edges
5. `TeleportServer` - 15 edges
6. `Flujo de trabajo — Teleport Animation (NeoForge)` - 14 edges
7. `ZoomDimension` - 13 edges
8. `TeleportStepEffectRenderer` - 13 edges
9. `CurseForge — Variables del proyecto` - 13 edges
10. `StartServerTeleportPayload` - 12 edges

## Surprising Connections (you probably didn't know these)
- `TeleportTransitionController` --references--> `CameraType`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 7 → community 4_
- `TeleportTransitionController` --references--> `CameraFrame`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 7 → community 23_
- `TeleportTransitionController` --references--> `FadingTravelSound`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 7 → community 0_
- `TeleportTransitionController` --references--> `Vec3`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 7 → community 27_
- `CameraFrame` --references--> `Vec3`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 27 → community 23_

## Import Cycles
- None detected.

## Communities (46 total, 9 thin omitted)

### Community 0 - "TeleportTransitionController.java"
Cohesion: 0.05
Nodes (28): AbstractTickableSoundInstance, Method, BobbyCompat, DistantHorizonsCompat, IrisCompat, CallbackInfo, Entity, Inject (+20 more)

### Community 3 - "Flujo de trabajo — Teleport Animation (NeoForge)"
Cohesion: 0.04
Nodes (44): 1. Desarrollo, 2. Copiar a instancia de pruebas, 3. Probar en instancia, 4. Preparar versión para CurseForge, 5. Release estable, 6. Actualizar Knowledge Graph (Graphify), Archivos de CurseForge, Archivos que pasan a GitHub (+36 more)

### Community 4 - "TeleportClient"
Cohesion: 0.09
Nodes (16): CameraType, ChatFormatting, CommandDispatcher, CommandSourceStack, Component, EventBusSubscriber, FMLClientSetupEvent, RegisterClientCommandsEvent (+8 more)

### Community 5 - "StartServerTeleportPayload"
Cohesion: 0.06
Nodes (21): CustomPacketPayload, FriendlyByteBuf, Identifier, Override, DimensionIds, ResourceKey, Logger, Vec3 (+13 more)

### Community 8 - "TeleportServer"
Cohesion: 0.09
Nodes (19): IEventBus, MinecraftServer, Mod, RegisterPayloadHandlersEvent, Post, TeleportAnimation, Level, Logger (+11 more)

### Community 11 - ".tick"
Cohesion: 0.17
Nodes (7): GuiGraphicsExtractor, GameRendererMixin, Mixin, GuiMixin, Mixin, Vec3, TeleportStepEffectRenderer

### Community 12 - ".delayTeleportContext"
Cohesion: 0.16
Nodes (12): CallbackInfoReturnable, Inject, Mixin, Pseudo, WaystonesPlayerWaystoneManagerMixin, Entity, Level, Logger (+4 more)

### Community 13 - "FogRendererMixin.java"
Cohesion: 0.20
Nodes (12): ClientLevel, ClientLevelData, LevelHeightAccessor, Redirect, FogRendererMixin, CallbackInfoReturnable, Camera, DeltaTracker (+4 more)

### Community 14 - ".teleportAnimation$restoreGtpCameraAfterLeawind"
Cohesion: 0.09
Nodes (20): ComputeCameraAngles, Frustum, Invoker, CameraAccessor, Mixin, Vec3, CameraMixin, CallbackInfo (+12 more)

### Community 18 - "MouseHandlerMixin.java"
Cohesion: 0.42
Nodes (5): MouseButtonInfo, CallbackInfo, Inject, Mixin, MouseHandlerMixin

### Community 21 - "MinecraftMixin.java"
Cohesion: 0.39
Nodes (5): Screen, CallbackInfo, Inject, Mixin, MinecraftMixin

### Community 22 - "LeawindThirdPersonImplMixin.java"
Cohesion: 0.21
Nodes (10): CallbackInfoReturnable, Inject, Mixin, Pseudo, LeawindThirdPersonImplMixin, CallbackInfoReturnable, Inject, Mixin (+2 more)

### Community 24 - "CurseForge — Variables del proyecto"
Cohesion: 0.14
Nodes (13): Changelog, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, Parámetros del upload, Proyecto, Rama (+5 more)

### Community 25 - "ZoomDimension"
Cohesion: 0.23
Nodes (7): fromLevel(), Level, ResourceKey, ZoomDimension, END, NETHER, OVERWORLD

### Community 26 - "TeleportMixinPlugin"
Cohesion: 0.23
Nodes (4): ClassNode, IMixinConfigPlugin, IMixinInfo, TeleportMixinPlugin

### Community 28 - "Teleport Animation"
Cohesion: 0.18
Nodes (10): Building from source, Configuration, Credits, Features, Installation, Integrations, License, Requirements (+2 more)

### Community 29 - "EntityMixin.java"
Cohesion: 0.42
Nodes (6): EntityMixin, CallbackInfoReturnable, Entity, Inject, Mixin, ServerLevel

### Community 31 - "ScreenEffectRendererMixin.java"
Cohesion: 0.39
Nodes (5): CallbackInfo, Inject, Mixin, ScreenEffectRendererMixin, SubmitNodeCollector

### Community 32 - "CLAUDE.md — teleport_animation (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — teleport_animation (26.2), Paso 0 obligatorio, Prioridad de instrucciones

### Community 33 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 34 - "Changelog"
Cohesion: 0.40
Nodes (4): 0.0.0-beta.1 (2026-07-30), 0.0.0-beta.2 (2026-07-30), 1.0.0 (2026-07-31), Changelog

### Community 41 - ".getZoomStageGlideHeight"
Cohesion: 0.53
Nodes (4): CallbackInfo, Inject, Mixin, KeyboardInputMixin

### Community 44 - "DistantHorizonsRenderUtilMixin.java"
Cohesion: 0.48
Nodes (5): DistantHorizonsRenderUtilMixin, CallbackInfoReturnable, Inject, Mixin, Pseudo

### Community 45 - "VoxyClientMixin.java"
Cohesion: 0.48
Nodes (5): CallbackInfoReturnable, Inject, Mixin, Pseudo, VoxyClientMixin

## Knowledge Gaps
- **63 isolated node(s):** `OVERWORLD`, `NETHER`, `END`, `Paso 0 obligatorio`, `Prioridad de instrucciones` (+58 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **9 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TeleportTransitionController` connect `TeleportTransitionController` to `TeleportTransitionController.java`, `TeleportStepEffectRenderer`, `TeleportClient`, `StartServerTeleportPayload`, `Minecraft`, `.getPullEndTick`, `.getCameraFrame`, `.tick`, `.teleportAnimation$restoreGtpCameraAfterLeawind`, `.fromResourceKey`, `.isRunning`, `MouseHandlerMixin.java`, `LocalPlayer`, `MinecraftMixin.java`, `LeawindThirdPersonImplMixin.java`, `.playStepSound`, `ZoomDimension`, `.clamp`, `ScreenEffectRendererMixin.java`?**
  _High betweenness centrality (0.372) - this node is a cross-community bridge._
- **Why does `TeleportConfig` connect `TeleportConfig` to `TeleportTransitionController.java`, `TeleportClient`, `StartServerTeleportPayload`, `Minecraft`, `.getCameraFrame`, `.save`, `.applyConfigProperties`, `ZoomDimension`, `.load`?**
  _High betweenness centrality (0.205) - this node is a cross-community bridge._
- **Why does `TeleportClient` connect `TeleportClient` to `StartServerTeleportPayload`?**
  _High betweenness centrality (0.030) - this node is a cross-community bridge._
- **What connects `OVERWORLD`, `NETHER`, `END` to the rest of the system?**
  _63 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `TeleportTransitionController.java` be split into smaller, more focused modules?**
  _Cohesion score 0.05323653962492438 - nodes in this community are weakly interconnected._
- **Should `TeleportStepEffectRenderer` be split into smaller, more focused modules?**
  _Cohesion score 0.1206896551724138 - nodes in this community are weakly interconnected._
- **Should `TeleportConfig` be split into smaller, more focused modules?**
  _Cohesion score 0.0425531914893617 - nodes in this community are weakly interconnected._