# Graph Report - .  (2026-09-02)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 967 nodes · 2274 edges · 67 communities (60 shown, 7 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 5 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `d2886354`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- ClientModEvents.java
- Minecraft
- TeleportServer
- TeleportTransitionController.java
- StartServerTeleportPayload
- .isRunning
- TeleportConfig
- TeleportTransitionController
- LevelRendererMixin.java
- TeleportStepEffectRenderer
- .getCameraFrame
- WaystonesTeleportHandler
- .handleTeleportCommand
- TeleportDestinationParser
- KeyboardInputMixin.java
- .resolveBodyCameraHeights
- TeleportClient
- CurseForge — Variables del proyecto
- TeleportMixinPlugin
- ZoomDimension
- .interceptOutgoingPacket
- Flujo de trabajo — Teleport Animation (NeoForge)
- Fix-by-Fix Adaptation Details
- FrustumMixin.java
- EntityRendererMixin.java
- .interceptJourneyMapTeleport
- gradlew
- ScreenEffectRendererMixin.java
- MinecraftMixin.java
- TeleportClient.java
- Teleport Animation — NeoForge 1.21.1
- ClientPacketListenerMixin.java
- DistantHorizonsRenderUtilMixin.java
- LeawindThirdPersonLegacyMixin.java
- VoxyClientMixin.java
- [1.1.0] - 2026-09-02
- CLAUDE.md — teleport_animation (1.21.1)
- v0.0.0-beta.1 — Initial NeoForge port
- v0.0.0-beta.2 — Workflow alignment
- TeleportModMenu

## God Nodes (most connected - your core abstractions)
1. `TeleportTransitionController` - 268 edges
2. `TeleportConfig` - 123 edges
3. `TeleportClient` - 34 edges
4. `CameraFrame` - 19 edges
5. `TeleportServer` - 17 edges
6. `TeleportStepEffectRenderer` - 14 edges
7. `WaystonesTeleportHandler` - 14 edges
8. `CurseForge — Variables del proyecto` - 14 edges
9. `ZoomDimension` - 13 edges
10. `StartServerTeleportPayload` - 12 edges

## Surprising Connections (you probably didn't know these)
- `TeleportClient` --references--> `Logger`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportClient.java →   _Bridges community 20 → community 38_
- `TeleportTransitionController` --references--> `CameraType`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 7 → community 0_
- `TeleportTransitionController` --references--> `CameraFrame`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 7 → community 10_
- `TeleportTransitionController` --references--> `FadingTravelSound`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 7 → community 3_
- `TeleportTransitionController` --references--> `Vec3`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 7 → community 1_

## Import Cycles
- None detected.

## Communities (67 total, 7 thin omitted)

### Community 0 - "ClientModEvents.java"
Cohesion: 0.15
Nodes (9): CameraType, CommandDispatcher, CommandSourceStack, EventBusSubscriber, FMLClientSetupEvent, RegisterClientCommandsEvent, ClientModEvents, Post (+1 more)

### Community 1 - "Minecraft"
Cohesion: 0.05
Nodes (7): DimensionIds, ResourceKey, Minecraft, SodiumCompat, LocalPlayer, Minecraft, Vec3

### Community 2 - "TeleportServer"
Cohesion: 0.08
Nodes (26): IEventBus, MinecraftServer, Mod, CallbackInfo, Inject, Mixin, RelativeMovement, ServerLevel (+18 more)

### Community 3 - "TeleportTransitionController.java"
Cohesion: 0.05
Nodes (29): AbstractTickableSoundInstance, Method, BobbyCompat, DistantHorizonsCompat, IrisCompat, CallbackInfo, Entity, Inject (+21 more)

### Community 4 - "StartServerTeleportPayload"
Cohesion: 0.07
Nodes (22): CustomPacketPayload, Override, RegisterPayloadHandlersEvent, Vec3, TeleportClientNetworking, BypassNextServerTeleportPayload, FriendlyByteBuf, Level (+14 more)

### Community 5 - ".isRunning"
Cohesion: 0.08
Nodes (21): BlockGetter, ComputeCameraAngles, Invoker, CameraAccessor, Mixin, Vec3, CameraMixin, CallbackInfo (+13 more)

### Community 8 - "LevelRendererMixin.java"
Cohesion: 0.12
Nodes (22): ClientLevel, ClientLevelData, GameRenderer, LevelHeightAccessor, LevelRenderer, LightTexture, Matrix4f, FogRendererMixin (+14 more)

### Community 9 - "TeleportStepEffectRenderer"
Cohesion: 0.12
Nodes (13): GameRendererMixin, GuiGraphics, Mixin, Redirect, GuiMixin, CallbackInfo, DeltaTracker, GuiGraphics (+5 more)

### Community 11 - "WaystonesTeleportHandler"
Cohesion: 0.16
Nodes (14): CallbackInfoReturnable, Inject, Mixin, Pseudo, WaystonesPlayerWaystoneManagerMixin, BlockPos, Entity, Level (+6 more)

### Community 14 - ".handleTeleportCommand"
Cohesion: 0.21
Nodes (3): ChatFormatting, Component, Minecraft

### Community 15 - "TeleportDestinationParser"
Cohesion: 0.20
Nodes (4): TeleportCommandMatcher, LocalPlayer, Vec3, TeleportDestinationParser

### Community 17 - "KeyboardInputMixin.java"
Cohesion: 0.20
Nodes (9): Input, CallbackInfo, Inject, Mixin, KeyboardInputMixin, CallbackInfo, Inject, Mixin (+1 more)

### Community 20 - "TeleportClient"
Cohesion: 0.22
Nodes (3): CommandContextBuilder, TeleportClient, WaystoneTarget

### Community 21 - "CurseForge — Variables del proyecto"
Cohesion: 0.13
Nodes (14): Changelog, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, Historial, Parámetros del upload, Proyecto (+6 more)

### Community 22 - "TeleportMixinPlugin"
Cohesion: 0.23
Nodes (4): ClassNode, IMixinConfigPlugin, IMixinInfo, TeleportMixinPlugin

### Community 24 - "ZoomDimension"
Cohesion: 0.26
Nodes (7): fromLevel(), Level, ResourceKey, ZoomDimension, END, NETHER, OVERWORLD

### Community 25 - ".interceptOutgoingPacket"
Cohesion: 0.27
Nodes (8): Connection, PacketSendListener, ConnectionMixin, CallbackInfo, Inject, Mixin, Packet, Packet

### Community 26 - "Flujo de trabajo — Teleport Animation (NeoForge)"
Cohesion: 0.17
Nodes (11): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Teleport Animation (NeoForge), Flujo por tarea, Idioma (+3 more)

### Community 28 - "Fix-by-Fix Adaptation Details"
Cohesion: 0.18
Nodes (10): Compilation Assessment, Files Changed, Fix 1: Server crash on external teleport (`875f55f.patch`), Fix 2: Floor flicker during top-down travel (`b88e61d.patch` + `9306b9b.patch`), Fix 3: Nearby-waystone slide + flicker elimination (`a469680.patch` + `8d9f55d.patch` + `442b9a3.patch` + `99f51fc.patch` + `88d2f7f.patch`), Fix 4: Arrival position / feet pos (`4e08905.patch`), Fix 5: Underground spawn on long trips (`795ff50.patch`), Fix-by-Fix Adaptation Details (+2 more)

### Community 29 - "FrustumMixin.java"
Cohesion: 0.35
Nodes (6): FrustumMixin, CallbackInfo, Field, Inject, Mixin, Unique

### Community 30 - "EntityRendererMixin.java"
Cohesion: 0.31
Nodes (6): MultiBufferSource, EntityRendererMixin, CallbackInfo, Inject, Mixin, PoseStack

### Community 31 - ".interceptJourneyMapTeleport"
Cohesion: 0.40
Nodes (5): CallbackInfo, Inject, Mixin, Pseudo, JourneyMapClientNetworkDispatcherMixin

### Community 32 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 36 - "ScreenEffectRendererMixin.java"
Cohesion: 0.36
Nodes (6): CallbackInfo, Inject, Minecraft, Mixin, PoseStack, ScreenEffectRendererMixin

### Community 37 - "MinecraftMixin.java"
Cohesion: 0.39
Nodes (5): Screen, CallbackInfo, Inject, Mixin, MinecraftMixin

### Community 38 - "TeleportClient.java"
Cohesion: 0.46
Nodes (5): FriendlyByteBuf, Logger, ResourceLocation, Vec3, PacketTeleportTarget

### Community 40 - "Teleport Animation — NeoForge 1.21.1"
Cohesion: 0.29
Nodes (6): Credits & License, Features, Installation, Requirements, Teleport Animation — NeoForge 1.21.1, Usage

### Community 41 - "ClientPacketListenerMixin.java"
Cohesion: 0.43
Nodes (4): ClientPacketListenerMixin, CallbackInfo, Inject, Mixin

### Community 42 - "DistantHorizonsRenderUtilMixin.java"
Cohesion: 0.48
Nodes (5): DistantHorizonsRenderUtilMixin, CallbackInfoReturnable, Inject, Mixin, Pseudo

### Community 43 - "LeawindThirdPersonLegacyMixin.java"
Cohesion: 0.48
Nodes (5): CallbackInfoReturnable, Inject, Mixin, Pseudo, LeawindThirdPersonLegacyMixin

### Community 44 - "VoxyClientMixin.java"
Cohesion: 0.48
Nodes (5): CallbackInfoReturnable, Inject, Mixin, Pseudo, VoxyClientMixin

### Community 45 - "[1.1.0] - 2026-09-02"
Cohesion: 0.40
Nodes (4): [1.1.0] - 2026-09-02, Bug Fixes (ported from 26.2 fix line), Changelog, Internal

### Community 46 - "CLAUDE.md — teleport_animation (1.21.1)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — teleport_animation (1.21.1), Prioridad de instrucciones, Workflow del mod

## Knowledge Gaps
- **45 isolated node(s):** `OVERWORLD`, `NETHER`, `END`, `Workflow del mod`, `Prioridad de instrucciones` (+40 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **7 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TeleportTransitionController` connect `TeleportTransitionController` to `ClientModEvents.java`, `Minecraft`, `TeleportTransitionController.java`, `ScreenEffectRendererMixin.java`, `.isRunning`, `MinecraftMixin.java`, `.shouldPreferVoxyOnlyTerrain`, `LevelRendererMixin.java`, `TeleportStepEffectRenderer`, `.getCameraFrame`, `.getFallbackTerrainSectionVisibility`, `KeyboardInputMixin.java`, `.getFixedTotalTicks`, `.resolveBodyCameraHeights`, `.getTravelStartTick`, `.enterBodyFrame`, `FrustumMixin.java`, `EntityRendererMixin.java`?**
  _High betweenness centrality (0.365) - this node is a cross-community bridge._
- **Why does `TeleportConfig` connect `TeleportConfig` to `Minecraft`, `.load`, `.getCameraFrame`, `.save`, `.handleTeleportCommand`, `.clamp`, `KeyboardInputMixin.java`, `.getFixedTotalTicks`, `.sanitizeStageHeights`, `.sanitizeZoomStageGlideHeight`, `ZoomDimension`, `EntityRendererMixin.java`?**
  _High betweenness centrality (0.189) - this node is a cross-community bridge._
- **Why does `TeleportClient` connect `TeleportClient` to `ClientModEvents.java`, `StartServerTeleportPayload`, `TeleportClient.java`, `ClientPacketListenerMixin.java`, `.handleTeleportCommand`, `.interceptOutgoingPacket`, `.interceptJourneyMapTeleport`?**
  _High betweenness centrality (0.045) - this node is a cross-community bridge._
- **What connects `OVERWORLD`, `NETHER`, `END` to the rest of the system?**
  _45 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Minecraft` be split into smaller, more focused modules?**
  _Cohesion score 0.05083986562150056 - nodes in this community are weakly interconnected._
- **Should `TeleportServer` be split into smaller, more focused modules?**
  _Cohesion score 0.08392156862745098 - nodes in this community are weakly interconnected._
- **Should `TeleportTransitionController.java` be split into smaller, more focused modules?**
  _Cohesion score 0.05141242937853107 - nodes in this community are weakly interconnected._