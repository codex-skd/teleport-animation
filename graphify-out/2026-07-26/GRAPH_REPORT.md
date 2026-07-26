# Graph Report - teleport_animation-26.1.2  (2026-07-26)

## Corpus Check
- 84 files · ~95,569 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 985 nodes · 2130 edges · 77 communities (66 shown, 11 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 5 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

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
- TeleportTransitionController
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
- .getTravelEndTick
- FrustumMixin.java
- MinecraftMixin.java
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
- ScreenEffectRendererMixin.java
- ServerPlayerMixin.java
- LeawindThirdPersonImplMixin.java
- VoxyClientMixin.java
- FogRendererMixin.java
- DistantHorizonsRenderUtilMixin.java
- FogRendererMixin.java
- KeyboardInputMixin.java
- .scheduleTerrainUpdate

## God Nodes (most connected - your core abstractions)
1. `TeleportTransitionController` - 261 edges
2. `TeleportConfig` - 122 edges
3. `Changelog` - 37 edges
4. `TeleportClient` - 22 edges
5. `CameraFrame` - 18 edges
6. `TeleportServer` - 15 edges
7. `ZoomDimension` - 13 edges
8. `TeleportStepEffectRenderer` - 13 edges
9. `CurseForge — Variables del proyecto` - 13 edges
10. `StartServerTeleportPayload` - 12 edges

## Surprising Connections (you probably didn't know these)
- `TeleportTransitionController` --references--> `CameraType`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 28 → community 8_
- `TeleportTransitionController` --references--> `CameraFrame`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 28 → community 15_
- `TeleportTransitionController` --references--> `FadingTravelSound`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 28 → community 53_

## Import Cycles
- None detected.

## Communities (77 total, 11 thin omitted)

### Community 1 - "TeleportServer"
Cohesion: 0.09
Nodes (18): IEventBus, MinecraftServer, Mod, Post, TeleportAnimation, Level, Logger, ResourceKey (+10 more)

### Community 3 - ".isRunning"
Cohesion: 0.15
Nodes (15): ComputeCameraAngles, Invoker, CameraAccessor, Mixin, Vec3, CameraMixin, CallbackInfo, CameraRenderState (+7 more)

### Community 5 - "StartServerTeleportPayload"
Cohesion: 0.07
Nodes (18): CustomPacketPayload, FriendlyByteBuf, Identifier, Override, RegisterPayloadHandlersEvent, Logger, Vec3, TeleportClientNetworking (+10 more)

### Community 6 - ".handleWarpPlateTeleport"
Cohesion: 0.21
Nodes (10): CallbackInfoReturnable, Inject, Mixin, Pseudo, LeawindThirdPersonImplMixin, CallbackInfoReturnable, Inject, Mixin (+2 more)

### Community 7 - "Flujo de trabajo — Teleport Animation (NeoForge)"
Cohesion: 0.05
Nodes (42): 1. Desarrollo, 2. Copiar a instancia de pruebas, 3. Probar en instancia, 4. Preparar versión para CurseForge, 5. Release estable, 6. Actualizar Knowledge Graph (Graphify), Archivos de CurseForge, Archivos que pasan a GitHub (+34 more)

### Community 8 - "TeleportClient"
Cohesion: 0.08
Nodes (18): CameraType, ChatFormatting, CommandDispatcher, CommandSourceStack, Component, EventBusSubscriber, FMLClientSetupEvent, RegisterClientCommandsEvent (+10 more)

### Community 13 - "Changelog"
Cohesion: 0.05
Nodes (41): 0.0.0-beta.10 (2026-07-13), 0.0.0-beta.11 (2026-07-14), 0.0.0-beta.12 (2026-07-14), 0.0.0-beta.13 (2026-07-14), 0.0.0-beta.14 (2026-07-14), 0.0.0-beta.15 (2026-07-16), 0.0.0-beta.16 (2026-07-19), 0.0.0-beta.17 (2026-07-19) (+33 more)

### Community 14 - "LevelRendererMixin.java"
Cohesion: 0.10
Nodes (26): ChunkSectionsToRender, ClientLevel, ClientLevelData, GpuBufferSlice, GraphicsResourceAllocator, LevelHeightAccessor, LevelRenderer, Matrix4fc (+18 more)

### Community 16 - "WaystonesTeleportHandler"
Cohesion: 0.16
Nodes (12): CallbackInfoReturnable, Inject, Mixin, Pseudo, WaystonesPlayerWaystoneManagerMixin, Entity, Level, Logger (+4 more)

### Community 18 - "TeleportStepEffectRenderer"
Cohesion: 0.07
Nodes (9): GuiGraphicsExtractor, IrisCompat, GameRendererMixin, Mixin, GuiMixin, Mixin, Vec3, TeleportStepEffectRenderer (+1 more)

### Community 20 - "KeyboardInputMixin.java"
Cohesion: 0.20
Nodes (9): MouseButtonInfo, CallbackInfo, Inject, Mixin, KeyboardInputMixin, CallbackInfo, Inject, Mixin (+1 more)

### Community 22 - "CurseForge — Variables del proyecto"
Cohesion: 0.14
Nodes (13): Changelog, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, Parámetros del upload, Proyecto, Rama (+5 more)

### Community 23 - "ZoomDimension"
Cohesion: 0.20
Nodes (7): fromLevel(), Level, ResourceKey, ZoomDimension, END, NETHER, OVERWORLD

### Community 24 - "TeleportMixinPlugin"
Cohesion: 0.23
Nodes (4): ClassNode, IMixinConfigPlugin, IMixinInfo, TeleportMixinPlugin

### Community 26 - "Teleport Animation"
Cohesion: 0.18
Nodes (10): Building from source, Configuration, Credits, Features, Installation, Integrations, License, Requirements (+2 more)

### Community 29 - "FrustumMixin.java"
Cohesion: 0.19
Nodes (12): Frustum, EntityRendererMixin, CallbackInfoReturnable, Inject, Logger, Mixin, FrustumMixin, CallbackInfo (+4 more)

### Community 30 - "MinecraftMixin.java"
Cohesion: 0.39
Nodes (5): Screen, CallbackInfo, Inject, Mixin, MinecraftMixin

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

### Community 53 - ".tick"
Cohesion: 0.12
Nodes (10): AbstractTickableSoundInstance, Method, BobbyCompat, DistantHorizonsCompat, FadingTravelSound, Camera, Level, Logger (+2 more)

### Community 55 - "ScreenEffectRendererMixin.java"
Cohesion: 0.39
Nodes (5): CallbackInfo, Inject, Mixin, ScreenEffectRendererMixin, SubmitNodeCollector

### Community 59 - "LeawindThirdPersonImplMixin.java"
Cohesion: 0.42
Nodes (6): EntityMixin, CallbackInfoReturnable, Entity, Inject, Mixin, ServerLevel

### Community 60 - "VoxyClientMixin.java"
Cohesion: 0.48
Nodes (5): CallbackInfoReturnable, Inject, Mixin, Pseudo, VoxyClientMixin

### Community 66 - "DistantHorizonsRenderUtilMixin.java"
Cohesion: 0.48
Nodes (5): DistantHorizonsRenderUtilMixin, CallbackInfoReturnable, Inject, Mixin, Pseudo

### Community 67 - "FogRendererMixin.java"
Cohesion: 0.28
Nodes (7): BlockPos, Entity, ItemStack, Level, ResourceKey, ServerPlayer, WaystonesWarpPlateHandler

### Community 68 - "KeyboardInputMixin.java"
Cohesion: 0.39
Nodes (7): CallbackInfo, Entity, Inject, ItemStack, Mixin, Pseudo, WaystonesWarpPlateBlockEntityMixin

## Knowledge Gaps
- **124 isolated node(s):** `OVERWORLD`, `NETHER`, `END`, `0.0.0-beta.37 (2026-07-26)`, `0.0.0-beta.36 (2026-07-25)` (+119 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **11 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TeleportTransitionController` connect `.getTravelEndTick` to `Minecraft`, `TeleportTransitionController.java`, `.isRunning`, `StartServerTeleportPayload`, `.handleWarpPlateTeleport`, `TeleportClient`, `LocalPlayer`, `TeleportTransitionController`, `LevelRendererMixin.java`, `.getCameraFrame`, `TeleportStepEffectRenderer`, `.getPullEndTick`, `KeyboardInputMixin.java`, `ZoomDimension`, `.enterBodyFrame`, `FrustumMixin.java`, `MinecraftMixin.java`, `.tick`, `ScreenEffectRendererMixin.java`, `.shouldPreferVoxyOnlyTerrain`?**
  _High betweenness centrality (0.296) - this node is a cross-community bridge._
- **Why does `TeleportConfig` connect `TeleportConfig` to `TeleportServer`, `StartServerTeleportPayload`, `BobbyCompat.java`, `TeleportClient`, `TeleportTransitionController`, `.save`, `.getPullEndTick`, `KeyboardInputMixin.java`, `.applyConfigProperties`, `ZoomDimension`, `.clamp`, `.load`?**
  _High betweenness centrality (0.162) - this node is a cross-community bridge._
- **Why does `TeleportClient` connect `TeleportClient` to `StartServerTeleportPayload`?**
  _High betweenness centrality (0.023) - this node is a cross-community bridge._
- **What connects `OVERWORLD`, `NETHER`, `END` to the rest of the system?**
  _124 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `TeleportServer` be split into smaller, more focused modules?**
  _Cohesion score 0.09146341463414634 - nodes in this community are weakly interconnected._
- **Should `TeleportConfig` be split into smaller, more focused modules?**
  _Cohesion score 0.0407843137254902 - nodes in this community are weakly interconnected._
- **Should `StartServerTeleportPayload` be split into smaller, more focused modules?**
  _Cohesion score 0.07372549019607844 - nodes in this community are weakly interconnected._