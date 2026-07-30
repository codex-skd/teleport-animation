# Graph Report - 26.2  (2026-07-31)

## Corpus Check
- 56 files · ~92,662 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 857 nodes · 1990 edges · 49 communities (38 shown, 11 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 5 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `f4de4e1f`
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
- .getCameraFrame
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
- Method
- WaystonesWarpPlateBlockEntityMixin.java
- DistantHorizonsRenderUtilMixin.java
- VoxyClientMixin.java
- IrisCompat
- TeleportSounds.java

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
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 7 → community 10_
- `TeleportTransitionController` --references--> `FadingTravelSound`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 7 → community 15_
- `TeleportTransitionController` --references--> `Vec3`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 7 → community 6_
- `CameraFrame` --references--> `Vec3`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 6 → community 10_

## Import Cycles
- None detected.

## Communities (49 total, 11 thin omitted)

### Community 0 - "TeleportTransitionController.java"
Cohesion: 0.28
Nodes (7): BlockPos, Entity, ItemStack, Level, ResourceKey, ServerPlayer, WaystonesWarpPlateHandler

### Community 1 - "TeleportStepEffectRenderer"
Cohesion: 0.07
Nodes (8): GuiGraphicsExtractor, GameRendererMixin, Mixin, GuiMixin, Mixin, Vec3, TeleportStepEffectRenderer, BlockPos

### Community 3 - "Flujo de trabajo — Teleport Animation (NeoForge)"
Cohesion: 0.04
Nodes (44): 1. Desarrollo, 2. Copiar a instancia de pruebas, 3. Probar en instancia, 4. Preparar versión para CurseForge, 5. Release estable, 6. Actualizar Knowledge Graph (Graphify), Archivos de CurseForge, Archivos que pasan a GitHub (+36 more)

### Community 4 - "TeleportClient"
Cohesion: 0.10
Nodes (16): CameraType, ChatFormatting, CommandDispatcher, CommandSourceStack, Component, EventBusSubscriber, FMLClientSetupEvent, RegisterClientCommandsEvent (+8 more)

### Community 5 - "StartServerTeleportPayload"
Cohesion: 0.07
Nodes (19): CustomPacketPayload, FriendlyByteBuf, Identifier, Override, DimensionIds, ResourceKey, Logger, Vec3 (+11 more)

### Community 8 - "TeleportServer"
Cohesion: 0.09
Nodes (19): IEventBus, MinecraftServer, Mod, RegisterPayloadHandlersEvent, Post, TeleportAnimation, Level, Logger (+11 more)

### Community 12 - ".delayTeleportContext"
Cohesion: 0.16
Nodes (12): CallbackInfoReturnable, Inject, Mixin, Pseudo, WaystonesPlayerWaystoneManagerMixin, Entity, Level, Logger (+4 more)

### Community 13 - "FogRendererMixin.java"
Cohesion: 0.12
Nodes (17): ClientLevel, ClientLevelData, Frustum, LevelHeightAccessor, Redirect, EntityRendererMixin, CallbackInfoReturnable, Inject (+9 more)

### Community 14 - ".teleportAnimation$restoreGtpCameraAfterLeawind"
Cohesion: 0.12
Nodes (15): ComputeCameraAngles, Invoker, CameraAccessor, Mixin, Vec3, CameraMixin, CallbackInfo, DeltaTracker (+7 more)

### Community 15 - ".fromResourceKey"
Cohesion: 0.17
Nodes (7): AbstractTickableSoundInstance, DistantHorizonsCompat, BodyCameraHeights, FadingTravelSound, Level, Logger, ResourceKey

### Community 18 - "MouseHandlerMixin.java"
Cohesion: 0.20
Nodes (9): MouseButtonInfo, CallbackInfo, Inject, Mixin, KeyboardInputMixin, CallbackInfo, Inject, Mixin (+1 more)

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
Cohesion: 0.20
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
Cohesion: 0.50
Nodes (3): 0.0.0-beta.1 (2026-07-30), 0.0.0-beta.2 (2026-07-30), Changelog

### Community 42 - "Method"
Cohesion: 0.24
Nodes (4): Method, Minecraft, SodiumCompat, VoxyCompat

### Community 43 - "WaystonesWarpPlateBlockEntityMixin.java"
Cohesion: 0.39
Nodes (7): CallbackInfo, Entity, Inject, ItemStack, Mixin, Pseudo, WaystonesWarpPlateBlockEntityMixin

### Community 44 - "DistantHorizonsRenderUtilMixin.java"
Cohesion: 0.48
Nodes (5): DistantHorizonsRenderUtilMixin, CallbackInfoReturnable, Inject, Mixin, Pseudo

### Community 45 - "VoxyClientMixin.java"
Cohesion: 0.48
Nodes (5): CallbackInfoReturnable, Inject, Mixin, Pseudo, VoxyClientMixin

## Knowledge Gaps
- **62 isolated node(s):** `OVERWORLD`, `NETHER`, `END`, `Paso 0 obligatorio`, `Prioridad de instrucciones` (+57 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **11 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TeleportTransitionController` connect `TeleportTransitionController` to `TeleportStepEffectRenderer`, `TeleportClient`, `StartServerTeleportPayload`, `Minecraft`, `.getPullEndTick`, `.getCameraFrame`, `.tick`, `FogRendererMixin.java`, `.teleportAnimation$restoreGtpCameraAfterLeawind`, `.fromResourceKey`, `.isRunning`, `MouseHandlerMixin.java`, `LocalPlayer`, `MinecraftMixin.java`, `LeawindThirdPersonImplMixin.java`, `.playStepSound`, `ZoomDimension`, `.clamp`, `ScreenEffectRendererMixin.java`, `.getZoomStageGlideHeight`?**
  _High betweenness centrality (0.373) - this node is a cross-community bridge._
- **Why does `TeleportConfig` connect `TeleportConfig` to `TeleportClient`, `StartServerTeleportPayload`, `.getZoomStageGlideHeight`, `.getCameraFrame`, `.save`, `MouseHandlerMixin.java`, `.applyConfigProperties`, `.playStepSound`, `ZoomDimension`, `.load`?**
  _High betweenness centrality (0.206) - this node is a cross-community bridge._
- **Why does `TeleportClient` connect `TeleportClient` to `StartServerTeleportPayload`?**
  _High betweenness centrality (0.030) - this node is a cross-community bridge._
- **What connects `OVERWORLD`, `NETHER`, `END` to the rest of the system?**
  _62 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `TeleportStepEffectRenderer` be split into smaller, more focused modules?**
  _Cohesion score 0.06623376623376623 - nodes in this community are weakly interconnected._
- **Should `TeleportConfig` be split into smaller, more focused modules?**
  _Cohesion score 0.0425531914893617 - nodes in this community are weakly interconnected._
- **Should `Flujo de trabajo — Teleport Animation (NeoForge)` be split into smaller, more focused modules?**
  _Cohesion score 0.044444444444444446 - nodes in this community are weakly interconnected._