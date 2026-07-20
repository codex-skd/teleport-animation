# Graph Report - teleport_animation-26.1.2  (2026-07-20)

## Corpus Check
- 69 files · ~93,978 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 954 nodes · 2124 edges · 52 communities (47 shown, 5 thin omitted)
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
- Vec3
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
- FrustumMixin.java
- ScreenEffectRendererMixin.java
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

## God Nodes (most connected - your core abstractions)
1. `TeleportTransitionController` - 260 edges
2. `TeleportConfig` - 122 edges
3. `TeleportClient` - 21 edges
4. `Changelog` - 20 edges
5. `CameraFrame` - 18 edges
6. `TeleportServer` - 14 edges
7. `ZoomDimension` - 13 edges
8. `TeleportStepEffectRenderer` - 13 edges
9. `CurseForge — Variables del proyecto` - 13 edges
10. `StartServerTeleportPayload` - 12 edges

## Surprising Connections (you probably didn't know these)
- `TeleportTransitionController` --references--> `CameraType`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 10 → community 8_
- `TeleportTransitionController` --references--> `Logger`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 10 → community 2_
- `TeleportTransitionController` --references--> `CameraFrame`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 10 → community 15_
- `TeleportTransitionController` --references--> `Vec3`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 10 → community 11_
- `CameraFrame` --references--> `Vec3`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 11 → community 15_

## Import Cycles
- None detected.

## Communities (52 total, 5 thin omitted)

### Community 0 - "Minecraft"
Cohesion: 0.06
Nodes (5): DimensionIds, ResourceKey, Minecraft, SodiumCompat, Minecraft

### Community 1 - "TeleportServer"
Cohesion: 0.06
Nodes (37): IEventBus, MinecraftServer, Mod, RegisterPayloadHandlersEvent, EntityMixin, CallbackInfo, CallbackInfoReturnable, Entity (+29 more)

### Community 2 - "TeleportTransitionController.java"
Cohesion: 0.05
Nodes (36): AbstractTickableSoundInstance, ClientLevel, Logger, Screen, DistantHorizonsRenderUtilMixin, CallbackInfoReturnable, Inject, Mixin (+28 more)

### Community 3 - ".isRunning"
Cohesion: 0.08
Nodes (21): BlockGetter, ComputeCameraAngles, Invoker, CameraAccessor, Mixin, Vec3, CameraMixin, CallbackInfo (+13 more)

### Community 5 - "StartServerTeleportPayload"
Cohesion: 0.09
Nodes (16): CustomPacketPayload, FriendlyByteBuf, Identifier, Override, Vec3, TeleportClientNetworking, BypassNextServerTeleportPayload, Level (+8 more)

### Community 6 - ".handleWarpPlateTeleport"
Cohesion: 0.07
Nodes (19): Method, BobbyCompat, DistantHorizonsCompat, IrisCompat, CallbackInfo, Entity, Inject, ItemStack (+11 more)

### Community 7 - "Flujo de trabajo — Teleport Animation (NeoForge)"
Cohesion: 0.05
Nodes (41): 1. Desarrollo, 2. Copiar a instancia de pruebas, 3. Probar en instancia, 4. Preparar versión para CurseForge, 5. Release estable, 6. Actualizar Knowledge Graph (Graphify), Archivos de CurseForge, Archivos que pasan a GitHub (+33 more)

### Community 8 - "TeleportClient"
Cohesion: 0.11
Nodes (15): CameraType, ChatFormatting, CommandDispatcher, CommandSourceStack, Component, EventBusSubscriber, FMLClientSetupEvent, RegisterClientCommandsEvent (+7 more)

### Community 9 - "LocalPlayer"
Cohesion: 0.09
Nodes (7): LocalPlayer, MultiBufferSource, EntityRendererMixin, CallbackInfo, Inject, Mixin, PoseStack

### Community 13 - "Changelog"
Cohesion: 0.08
Nodes (24): 0.0.0-beta.10 (2026-07-13), 0.0.0-beta.11 (2026-07-14), 0.0.0-beta.12 (2026-07-14), 0.0.0-beta.13 (2026-07-14), 0.0.0-beta.14 (2026-07-14), 0.0.0-beta.15 (2026-07-16), 0.0.0-beta.16 (2026-07-19), 0.0.0-beta.17 (2026-07-19) (+16 more)

### Community 14 - "LevelRendererMixin.java"
Cohesion: 0.16
Nodes (15): ClientLevelData, DeltaTracker, LevelHeightAccessor, LevelRenderer, Redirect, GameRendererMixin, Mixin, CallbackInfo (+7 more)

### Community 16 - "WaystonesTeleportHandler"
Cohesion: 0.19
Nodes (10): CallbackInfoReturnable, Inject, Mixin, Pseudo, WaystonesPlayerWaystoneManagerMixin, Entity, Level, ResourceKey (+2 more)

### Community 18 - "TeleportStepEffectRenderer"
Cohesion: 0.21
Nodes (5): GuiGraphicsExtractor, GuiMixin, Mixin, Vec3, TeleportStepEffectRenderer

### Community 20 - "KeyboardInputMixin.java"
Cohesion: 0.20
Nodes (9): Input, CallbackInfo, Inject, Mixin, KeyboardInputMixin, CallbackInfo, Inject, Mixin (+1 more)

### Community 22 - "CurseForge — Variables del proyecto"
Cohesion: 0.14
Nodes (13): Changelog, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, Parámetros del upload, Proyecto, Rama (+5 more)

### Community 23 - "ZoomDimension"
Cohesion: 0.26
Nodes (7): fromLevel(), Level, ResourceKey, ZoomDimension, END, NETHER, OVERWORLD

### Community 24 - "TeleportMixinPlugin"
Cohesion: 0.23
Nodes (4): ClassNode, IMixinConfigPlugin, IMixinInfo, TeleportMixinPlugin

### Community 26 - "Teleport Animation"
Cohesion: 0.18
Nodes (10): Building from source, Configuration, Credits, Features, Installation, Integrations, License, Requirements (+2 more)

### Community 29 - "FrustumMixin.java"
Cohesion: 0.40
Nodes (6): FrustumMixin, CallbackInfo, Field, Inject, Mixin, Unique

### Community 30 - "ScreenEffectRendererMixin.java"
Cohesion: 0.36
Nodes (6): CallbackInfo, Inject, Minecraft, Mixin, PoseStack, ScreenEffectRendererMixin

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

## Knowledge Gaps
- **106 isolated node(s):** `OVERWORLD`, `NETHER`, `END`, `0.0.0-beta.20 (2026-07-19)`, `0.0.0-beta.19 (2026-07-19)` (+101 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **5 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TeleportTransitionController` connect `TeleportTransitionController` to `Minecraft`, `TeleportTransitionController.java`, `.isRunning`, `TeleportClient`, `LocalPlayer`, `Vec3`, `.getFallbackTerrainSectionVisibility`, `LevelRendererMixin.java`, `.getCameraFrame`, `TeleportStepEffectRenderer`, `.getPullEndTick`, `KeyboardInputMixin.java`, `.enterBodyFrame`, `.getTravelEndTick`, `ScreenEffectRendererMixin.java`?**
  _High betweenness centrality (0.312) - this node is a cross-community bridge._
- **Why does `TeleportConfig` connect `TeleportConfig` to `Minecraft`, `.handleWarpPlateTeleport`, `TeleportClient`, `LocalPlayer`, `TeleportTransitionController`, `.save`, `.getPullEndTick`, `KeyboardInputMixin.java`, `.applyConfigProperties`, `ZoomDimension`, `.clamp`, `.load`?**
  _High betweenness centrality (0.177) - this node is a cross-community bridge._
- **What connects `OVERWORLD`, `NETHER`, `END` to the rest of the system?**
  _106 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Minecraft` be split into smaller, more focused modules?**
  _Cohesion score 0.06180733162830349 - nodes in this community are weakly interconnected._
- **Should `TeleportServer` be split into smaller, more focused modules?**
  _Cohesion score 0.0601404741000878 - nodes in this community are weakly interconnected._
- **Should `TeleportTransitionController.java` be split into smaller, more focused modules?**
  _Cohesion score 0.052525252525252523 - nodes in this community are weakly interconnected._
- **Should `.isRunning` be split into smaller, more focused modules?**
  _Cohesion score 0.0797872340425532 - nodes in this community are weakly interconnected._