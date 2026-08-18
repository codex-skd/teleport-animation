# Graph Report - .  (2026-08-18)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 868 nodes · 2130 edges · 34 communities (25 shown, 9 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 7 edges (avg confidence: 0.8)
- Token cost: 2,108 input · 313 output

## Graph Freshness
- Built from commit: `f7885872`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- Client Events
- Server Events
- Mixin Camera
- Renderer Mixin
- Client Networking
- Camera Mixin
- Config Properties
- Compat Mixin
- Transition Controller
- Minecraft Core
- Client Level
- Local Player
- Waystones Handler
- Camera Frame
- Input Mixin
- Teleport Parser
- Zoom Dimension
- Body Camera
- Mixin Plugin
- Vector Math
- Frustum Mixin
- Screen Effect
- Entity Renderer
- Build Scripts
- Mod Menu
- Mod Icon

## God Nodes (most connected - your core abstractions)
1. `TeleportTransitionController` - 260 edges
2. `TeleportConfig` - 124 edges
3. `TeleportClient` - 34 edges
4. `CameraFrame` - 18 edges
5. `TeleportServer` - 17 edges
6. `ZoomDimension` - 13 edges
7. `TeleportStepEffectRenderer` - 13 edges
8. `TeleportDestinationParser` - 11 edges
9. `TeleportClientNetworking` - 10 edges
10. `WaystonesTeleportHandler` - 10 edges

## Surprising Connections (you probably didn't know these)
- `TeleportTransitionController` --references--> `CameraType`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 8 → community 0_
- `TeleportTransitionController` --references--> `CameraFrame`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 8 → community 14_
- `TeleportTransitionController` --references--> `FadingTravelSound`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 8 → community 2_
- `TeleportTransitionController` --references--> `Vec3`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 8 → community 24_
- `CameraFrame` --references--> `Vec3`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 24 → community 14_

## Import Cycles
- None detected.

## Communities (34 total, 9 thin omitted)

### Community 0 - "Client Events"
Cohesion: 0.05
Nodes (37): CameraType, ChatFormatting, ClientTickEvent, CommandContextBuilder, CommandDispatcher, CommandSourceStack, Component, Connection (+29 more)

### Community 1 - "Server Events"
Cohesion: 0.07
Nodes (28): FMLCommonSetupEvent, MinecraftServer, Mod, ServerTickEvent, SimpleChannel, CallbackInfoReturnable, Inject, Mixin (+20 more)

### Community 2 - "Mixin Camera"
Cohesion: 0.06
Nodes (30): AbstractTickableSoundInstance, Screen, DistantHorizonsRenderUtilMixin, CallbackInfoReturnable, Inject, Mixin, Pseudo, CallbackInfoReturnable (+22 more)

### Community 3 - "Renderer Mixin"
Cohesion: 0.07
Nodes (13): IrisCompat, GameRendererMixin, GuiGraphics, Mixin, Redirect, GuiMixin, CallbackInfo, GuiGraphics (+5 more)

### Community 4 - "Client Networking"
Cohesion: 0.07
Nodes (18): DimensionIds, ResourceKey, Vec3, TeleportClientNetworking, BypassNextServerTeleportPayload, FriendlyByteBuf, Level, ResourceKey (+10 more)

### Community 5 - "Camera Mixin"
Cohesion: 0.08
Nodes (21): BlockGetter, ComputeCameraAngles, Invoker, CameraAccessor, Mixin, Vec3, CameraMixin, CallbackInfo (+13 more)

### Community 7 - "Compat Mixin"
Cohesion: 0.07
Nodes (20): Method, BobbyCompat, DistantHorizonsCompat, CallbackInfo, Entity, Inject, ItemStack, Mixin (+12 more)

### Community 10 - "Client Level"
Cohesion: 0.12
Nodes (22): ClientLevel, ClientLevelData, GameRenderer, LevelHeightAccessor, LevelRenderer, LightTexture, Matrix4f, FogRendererMixin (+14 more)

### Community 12 - "Waystones Handler"
Cohesion: 0.19
Nodes (10): CallbackInfoReturnable, Inject, Mixin, Pseudo, WaystonesPlayerWaystoneManagerMixin, Entity, Level, ResourceKey (+2 more)

### Community 17 - "Input Mixin"
Cohesion: 0.20
Nodes (9): Input, CallbackInfo, Inject, Mixin, KeyboardInputMixin, CallbackInfo, Inject, Mixin (+1 more)

### Community 18 - "Teleport Parser"
Cohesion: 0.20
Nodes (4): TeleportCommandMatcher, LocalPlayer, Vec3, TeleportDestinationParser

### Community 20 - "Zoom Dimension"
Cohesion: 0.21
Nodes (7): fromLevel(), Level, ResourceKey, ZoomDimension, END, NETHER, OVERWORLD

### Community 23 - "Mixin Plugin"
Cohesion: 0.23
Nodes (4): ClassNode, IMixinConfigPlugin, IMixinInfo, TeleportMixinPlugin

### Community 25 - "Frustum Mixin"
Cohesion: 0.35
Nodes (6): FrustumMixin, CallbackInfo, Field, Inject, Mixin, Unique

### Community 27 - "Screen Effect"
Cohesion: 0.36
Nodes (6): CallbackInfo, Inject, Minecraft, Mixin, PoseStack, ScreenEffectRendererMixin

### Community 28 - "Entity Renderer"
Cohesion: 0.43
Nodes (6): MultiBufferSource, EntityRendererMixin, CallbackInfo, Inject, Mixin, PoseStack

### Community 29 - "Build Scripts"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **4 isolated node(s):** `OVERWORLD`, `NETHER`, `END`, `Mod Icon`
  These have ≤1 connection - possible missing edges or undocumented components.
- **9 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TeleportTransitionController` connect `Transition Controller` to `Client Events`, `Mixin Camera`, `Renderer Mixin`, `Camera Mixin`, `Minecraft Core`, `Client Level`, `Local Player`, `Camera Frame`, `Camera Motion`, `Input Mixin`, `Zoom Stage`, `Zoom Dimension`, `Body Camera`, `Travel Calculation`, `Vector Math`, `Frustum Mixin`, `Screen Effect`?**
  _High betweenness centrality (0.423) - this node is a cross-community bridge._
- **Why does `TeleportConfig` connect `Config Properties` to `Client Events`, `Server Events`, `Mixin Camera`, `Config Sanitizer`, `Config Reader`, `Input Mixin`, `Zoom Stage`, `Zoom Dimension`, `Config Manager`?**
  _High betweenness centrality (0.230) - this node is a cross-community bridge._
- **Why does `TeleportClient` connect `Client Events` to `Server Events`, `Client Networking`?**
  _High betweenness centrality (0.050) - this node is a cross-community bridge._
- **What connects `OVERWORLD`, `NETHER`, `END` to the rest of the system?**
  _4 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Client Events` be split into smaller, more focused modules?**
  _Cohesion score 0.050543637966500146 - nodes in this community are weakly interconnected._
- **Should `Server Events` be split into smaller, more focused modules?**
  _Cohesion score 0.07142857142857142 - nodes in this community are weakly interconnected._
- **Should `Mixin Camera` be split into smaller, more focused modules?**
  _Cohesion score 0.05520614954577219 - nodes in this community are weakly interconnected._