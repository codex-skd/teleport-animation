# Graph Report - .  (2026-07-19)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 875 nodes · 2150 edges · 34 communities (26 shown, 8 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 5 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `2f24f907`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- TeleportClient
- TeleportStepEffectRenderer
- TeleportServer
- TeleportTransitionController.java
- StartServerTeleportPayload
- .isRunning
- TeleportConfig
- TeleportTransitionController
- LevelRendererMixin.java
- Minecraft
- Method
- TeleportDestinationParser
- .getCameraFrame
- Vec3
- LocalPlayer
- WaystonesTeleportHandler
- KeyboardInputMixin.java
- .resolveBodyCameraHeights
- ZoomDimension
- TeleportMixinPlugin
- FrustumMixin.java
- ScreenEffectRendererMixin.java
- EntityRendererMixin.java
- DistantHorizonsRenderUtilMixin.java
- gradlew
- TeleportModMenu

## God Nodes (most connected - your core abstractions)
1. `TeleportTransitionController` - 260 edges
2. `TeleportConfig` - 122 edges
3. `TeleportClient` - 34 edges
4. `CameraFrame` - 18 edges
5. `TeleportServer` - 17 edges
6. `ZoomDimension` - 13 edges
7. `TeleportStepEffectRenderer` - 13 edges
8. `StartServerTeleportPayload` - 12 edges
9. `TeleportDestinationParser` - 11 edges
10. `TeleportClientNetworking` - 10 edges

## Surprising Connections (you probably didn't know these)
- `TeleportTransitionController` --references--> `CameraType`  [EXTRACTED]
  src/main/java/com/skd/teleportanimation/TeleportTransitionController.java →   _Bridges community 7 → community 0_
- `TeleportTransitionController` --references--> `CameraFrame`  [EXTRACTED]
  src/main/java/com/skd/teleportanimation/TeleportTransitionController.java → src/main/java/com/skd/teleportanimation/TeleportTransitionController.java  _Bridges community 7 → community 12_
- `TeleportTransitionController` --references--> `FadingTravelSound`  [EXTRACTED]
  src/main/java/com/skd/teleportanimation/TeleportTransitionController.java → src/main/java/com/skd/teleportanimation/TeleportTransitionController.java  _Bridges community 7 → community 3_
- `TeleportTransitionController` --references--> `Vec3`  [EXTRACTED]
  src/main/java/com/skd/teleportanimation/TeleportTransitionController.java →   _Bridges community 7 → community 13_
- `CameraFrame` --references--> `Vec3`  [EXTRACTED]
  src/main/java/com/skd/teleportanimation/TeleportTransitionController.java →   _Bridges community 13 → community 12_

## Import Cycles
- None detected.

## Communities (34 total, 8 thin omitted)

### Community 0 - "TeleportClient"
Cohesion: 0.05
Nodes (37): CameraType, ChatFormatting, CommandContextBuilder, CommandDispatcher, CommandSourceStack, Component, Connection, EventBusSubscriber (+29 more)

### Community 1 - "TeleportStepEffectRenderer"
Cohesion: 0.05
Nodes (15): IrisCompat, GameRendererMixin, GuiGraphics, Mixin, Redirect, GuiMixin, CallbackInfo, DeltaTracker (+7 more)

### Community 2 - "TeleportServer"
Cohesion: 0.07
Nodes (27): IEventBus, MinecraftServer, Mod, RegisterPayloadHandlersEvent, CallbackInfo, Inject, Mixin, RelativeMovement (+19 more)

### Community 3 - "TeleportTransitionController.java"
Cohesion: 0.05
Nodes (26): AbstractTickableSoundInstance, Screen, BobbyCompat, DistantHorizonsCompat, CallbackInfoReturnable, Inject, Mixin, Pseudo (+18 more)

### Community 4 - "StartServerTeleportPayload"
Cohesion: 0.07
Nodes (21): CustomPacketPayload, Override, Vec3, TeleportClientNetworking, BypassNextServerTeleportPayload, FriendlyByteBuf, Level, ResourceKey (+13 more)

### Community 5 - ".isRunning"
Cohesion: 0.08
Nodes (21): BlockGetter, ComputeCameraAngles, Invoker, CameraAccessor, Mixin, Vec3, CameraMixin, CallbackInfo (+13 more)

### Community 8 - "LevelRendererMixin.java"
Cohesion: 0.12
Nodes (22): ClientLevel, ClientLevelData, GameRenderer, LevelHeightAccessor, LevelRenderer, LightTexture, Matrix4f, FogRendererMixin (+14 more)

### Community 10 - "Method"
Cohesion: 0.12
Nodes (17): Method, CallbackInfo, Entity, Inject, ItemStack, Mixin, Pseudo, WaystonesWarpPlateBlockEntityMixin (+9 more)

### Community 11 - "TeleportDestinationParser"
Cohesion: 0.11
Nodes (6): DimensionIds, ResourceKey, TeleportCommandMatcher, LocalPlayer, Vec3, TeleportDestinationParser

### Community 15 - "WaystonesTeleportHandler"
Cohesion: 0.19
Nodes (10): CallbackInfoReturnable, Inject, Mixin, Pseudo, WaystonesPlayerWaystoneManagerMixin, Entity, Level, ResourceKey (+2 more)

### Community 17 - "KeyboardInputMixin.java"
Cohesion: 0.20
Nodes (9): Input, CallbackInfo, Inject, Mixin, KeyboardInputMixin, CallbackInfo, Inject, Mixin (+1 more)

### Community 20 - "ZoomDimension"
Cohesion: 0.26
Nodes (7): fromLevel(), Level, ResourceKey, ZoomDimension, END, NETHER, OVERWORLD

### Community 22 - "TeleportMixinPlugin"
Cohesion: 0.23
Nodes (4): ClassNode, IMixinConfigPlugin, IMixinInfo, TeleportMixinPlugin

### Community 27 - "FrustumMixin.java"
Cohesion: 0.40
Nodes (6): FrustumMixin, CallbackInfo, Field, Inject, Mixin, Unique

### Community 28 - "ScreenEffectRendererMixin.java"
Cohesion: 0.36
Nodes (6): CallbackInfo, Inject, Minecraft, Mixin, PoseStack, ScreenEffectRendererMixin

### Community 30 - "EntityRendererMixin.java"
Cohesion: 0.43
Nodes (6): MultiBufferSource, EntityRendererMixin, CallbackInfo, Inject, Mixin, PoseStack

### Community 31 - "DistantHorizonsRenderUtilMixin.java"
Cohesion: 0.48
Nodes (5): DistantHorizonsRenderUtilMixin, CallbackInfoReturnable, Inject, Mixin, Pseudo

### Community 32 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **3 isolated node(s):** `OVERWORLD`, `NETHER`, `END`
  These have ≤1 connection - possible missing edges or undocumented components.
- **8 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TeleportTransitionController` connect `TeleportTransitionController` to `TeleportClient`, `TeleportStepEffectRenderer`, `TeleportTransitionController.java`, `.isRunning`, `LevelRendererMixin.java`, `Minecraft`, `TeleportDestinationParser`, `.getCameraFrame`, `Vec3`, `LocalPlayer`, `KeyboardInputMixin.java`, `.resolveBodyCameraHeights`, `.getPullEndTick`, `.enterBodyFrame`, `ScreenEffectRendererMixin.java`?**
  _High betweenness centrality (0.422) - this node is a cross-community bridge._
- **Why does `TeleportConfig` connect `TeleportConfig` to `TeleportClient`, `TeleportServer`, `Minecraft`, `TeleportDestinationParser`, `.getCameraFrame`, `.save`, `KeyboardInputMixin.java`, `.applyConfigProperties`, `ZoomDimension`, `.getPullEndTick`, `.clamp`, `.load`?**
  _High betweenness centrality (0.227) - this node is a cross-community bridge._
- **Why does `TeleportClient` connect `TeleportClient` to `TeleportServer`, `TeleportDestinationParser`, `StartServerTeleportPayload`?**
  _High betweenness centrality (0.052) - this node is a cross-community bridge._
- **What connects `OVERWORLD`, `NETHER`, `END` to the rest of the system?**
  _3 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `TeleportClient` be split into smaller, more focused modules?**
  _Cohesion score 0.052160493827160495 - nodes in this community are weakly interconnected._
- **Should `TeleportStepEffectRenderer` be split into smaller, more focused modules?**
  _Cohesion score 0.05328218243819267 - nodes in this community are weakly interconnected._
- **Should `TeleportServer` be split into smaller, more focused modules?**
  _Cohesion score 0.07393483709273183 - nodes in this community are weakly interconnected._