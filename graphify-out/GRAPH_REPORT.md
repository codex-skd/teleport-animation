# Graph Report - 1.21.1  (2026-09-03)

## Corpus Check
- 78 files · ~93,683 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 932 nodes · 2181 edges · 66 communities (55 shown, 11 thin omitted)
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS · INFERRED: 5 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `1b53f4dc`
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
- .getFallbackTerrainSectionVisibility
- .handleTeleportCommand
- TeleportDestinationParser
- .clamp
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
- .load
- ScreenEffectRendererMixin.java
- MinecraftMixin.java
- TeleportClient.java
- Teleport Animation — NeoForge 1.21.1
- ClientPacketListenerMixin.java
- DistantHorizonsRenderUtilMixin.java
- VoxyClientMixin.java
- [1.1.0] - 2026-09-02
- CLAUDE.md — teleport_animation (1.21.1)
- v0.0.0-beta.1 — Initial NeoForge port
- v0.0.0-beta.2 — Workflow alignment
- TeleportModMenu

## God Nodes (most connected - your core abstractions)
1. `TeleportTransitionController` - 268 edges
2. `TeleportConfig` - 81 edges
3. `TeleportClient` - 34 edges
4. `CameraFrame` - 19 edges
5. `TeleportServer` - 17 edges
6. `ZoomDimension` - 14 edges
7. `TeleportStepEffectRenderer` - 14 edges
8. `WaystonesTeleportHandler` - 14 edges
9. `CurseForge — Variables del proyecto` - 14 edges
10. `StartServerTeleportPayload` - 12 edges

## Surprising Connections (you probably didn't know these)
- `TeleportClient` --references--> `Logger`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportClient.java →   _Bridges community 20 → community 0_
- `TeleportConfig` --references--> `Builder`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportConfig.java →   _Bridges community 6 → community 33_
- `fromLevel()` --references--> `ZoomDimension`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportConfig.java → src/main/java/com/skd/teleport_animation/TeleportConfig.java  _Bridges community 24 → community 33_
- `TeleportTransitionController` --references--> `CameraType`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java →   _Bridges community 7 → community 0_
- `TeleportTransitionController` --references--> `CameraFrame`  [EXTRACTED]
  src/main/java/com/skd/teleport_animation/TeleportTransitionController.java → src/main/java/com/skd/teleport_animation/TeleportTransitionController.java  _Bridges community 7 → community 10_

## Import Cycles
- None detected.

## Communities (66 total, 11 thin omitted)

### Community 0 - "ClientModEvents.java"
Cohesion: 0.20
Nodes (9): CameraType, CommandDispatcher, CommandSourceStack, EventBusSubscriber, FMLClientSetupEvent, RegisterClientCommandsEvent, ClientModEvents, Logger (+1 more)

### Community 1 - "Minecraft"
Cohesion: 0.07
Nodes (3): Minecraft, SodiumCompat, Minecraft

### Community 2 - "TeleportServer"
Cohesion: 0.08
Nodes (28): IEventBus, MinecraftServer, Mod, ModContainer, RegisterPayloadHandlersEvent, CallbackInfo, Inject, Mixin (+20 more)

### Community 3 - "TeleportTransitionController.java"
Cohesion: 0.08
Nodes (19): Method, BobbyCompat, DistantHorizonsCompat, IrisCompat, CallbackInfo, Entity, Inject, ItemStack (+11 more)

### Community 4 - "StartServerTeleportPayload"
Cohesion: 0.07
Nodes (21): CustomPacketPayload, Override, Vec3, TeleportClientNetworking, BypassNextServerTeleportPayload, FriendlyByteBuf, Level, ResourceKey (+13 more)

### Community 5 - ".isRunning"
Cohesion: 0.05
Nodes (39): AbstractTickableSoundInstance, BlockGetter, ComputeCameraAngles, CameraAccessor, Mixin, Vec3, CameraMixin, CallbackInfo (+31 more)

### Community 8 - "LevelRendererMixin.java"
Cohesion: 0.12
Nodes (22): ClientLevel, ClientLevelData, GameRenderer, LevelHeightAccessor, LevelRenderer, LightTexture, Matrix4f, FogRendererMixin (+14 more)

### Community 9 - "TeleportStepEffectRenderer"
Cohesion: 0.06
Nodes (14): GameRendererMixin, GuiGraphics, Mixin, Redirect, GuiMixin, CallbackInfo, DeltaTracker, GuiGraphics (+6 more)

### Community 11 - "WaystonesTeleportHandler"
Cohesion: 0.16
Nodes (14): CallbackInfoReturnable, Inject, Mixin, Pseudo, WaystonesPlayerWaystoneManagerMixin, BlockPos, Entity, Level (+6 more)

### Community 15 - "TeleportDestinationParser"
Cohesion: 0.18
Nodes (4): TeleportCommandMatcher, LocalPlayer, Vec3, TeleportDestinationParser

### Community 16 - ".clamp"
Cohesion: 0.17
Nodes (3): CommandContextBuilder, Post, Minecraft

### Community 17 - "KeyboardInputMixin.java"
Cohesion: 0.20
Nodes (9): Input, CallbackInfo, Inject, Mixin, KeyboardInputMixin, CallbackInfo, Inject, Mixin (+1 more)

### Community 20 - "TeleportClient"
Cohesion: 0.31
Nodes (6): FriendlyByteBuf, ResourceLocation, Vec3, PacketTeleportTarget, TeleportClient, WaystoneTarget

### Community 21 - "CurseForge — Variables del proyecto"
Cohesion: 0.13
Nodes (14): Changelog, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, Historial, Parámetros del upload, Proyecto (+6 more)

### Community 22 - "TeleportMixinPlugin"
Cohesion: 0.23
Nodes (4): ClassNode, IMixinConfigPlugin, IMixinInfo, TeleportMixinPlugin

### Community 24 - "ZoomDimension"
Cohesion: 0.25
Nodes (4): ZoomDimension, END, NETHER, OVERWORLD

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
Cohesion: 0.40
Nodes (6): FrustumMixin, CallbackInfo, Field, Inject, Mixin, Unique

### Community 30 - "EntityRendererMixin.java"
Cohesion: 0.43
Nodes (6): MultiBufferSource, EntityRendererMixin, CallbackInfo, Inject, Mixin, PoseStack

### Community 31 - ".interceptJourneyMapTeleport"
Cohesion: 0.40
Nodes (5): CallbackInfo, Inject, Mixin, Pseudo, JourneyMapClientNetworkDispatcherMixin

### Community 32 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 33 - ".load"
Cohesion: 0.28
Nodes (7): Builder, ConfigValue, ModConfigSpec, DimensionHeights, fromLevel(), Level, ResourceKey

### Community 37 - "MinecraftMixin.java"
Cohesion: 0.39
Nodes (5): Screen, CallbackInfo, Inject, Mixin, MinecraftMixin

### Community 40 - "Teleport Animation — NeoForge 1.21.1"
Cohesion: 0.29
Nodes (6): Credits & License, Features, Installation, Requirements, Teleport Animation — NeoForge 1.21.1, Usage

### Community 41 - "ClientPacketListenerMixin.java"
Cohesion: 0.43
Nodes (4): ClientPacketListenerMixin, CallbackInfo, Inject, Mixin

### Community 42 - "DistantHorizonsRenderUtilMixin.java"
Cohesion: 0.48
Nodes (5): DistantHorizonsRenderUtilMixin, CallbackInfoReturnable, Inject, Mixin, Pseudo

### Community 44 - "VoxyClientMixin.java"
Cohesion: 0.48
Nodes (5): CallbackInfoReturnable, Inject, Mixin, Pseudo, VoxyClientMixin

### Community 45 - "[1.1.0] - 2026-09-02"
Cohesion: 0.22
Nodes (8): [1.1.0] - 2026-09-02, [1.2.0] - 2026-09-03, Bug Fixes (ported from 26.2 fix line), Changed, Changelog, Internal, Internal, Removed

### Community 46 - "CLAUDE.md — teleport_animation (1.21.1)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — teleport_animation (1.21.1), Prioridad de instrucciones, Workflow del mod

## Knowledge Gaps
- **48 isolated node(s):** `OVERWORLD`, `NETHER`, `END`, `Workflow del mod`, `Prioridad de instrucciones` (+43 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **11 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TeleportTransitionController` connect `TeleportTransitionController` to `ClientModEvents.java`, `Minecraft`, `ScreenEffectRendererMixin.java`, `.isRunning`, `TeleportClient.java`, `.shouldPreferVoxyOnlyTerrain`, `MinecraftMixin.java`, `TeleportStepEffectRenderer`, `.getCameraFrame`, `LeawindThirdPersonLegacyMixin.java`, `.getFallbackTerrainSectionVisibility`, `KeyboardInputMixin.java`, `.getFixedTotalTicks`, `.resolveBodyCameraHeights`, `.getTravelStartTick`, `ZoomDimension`, `.enterBodyFrame`?**
  _High betweenness centrality (0.376) - this node is a cross-community bridge._
- **Why does `TeleportConfig` connect `TeleportConfig` to `.load`, `Minecraft`, `TeleportServer`, `LeawindThirdPersonLegacyMixin.java`, `.save`, `.handleTeleportCommand`, `.clamp`, `KeyboardInputMixin.java`, `.getFixedTotalTicks`, `.resolveBodyCameraHeights`, `ZoomDimension`?**
  _High betweenness centrality (0.123) - this node is a cross-community bridge._
- **Why does `TeleportClient` connect `TeleportClient` to `ClientModEvents.java`, `TeleportServer`, `StartServerTeleportPayload`, `TeleportClient.java`, `ClientPacketListenerMixin.java`, `.handleTeleportCommand`, `.clamp`, `.interceptOutgoingPacket`, `.interceptJourneyMapTeleport`?**
  _High betweenness centrality (0.047) - this node is a cross-community bridge._
- **What connects `OVERWORLD`, `NETHER`, `END` to the rest of the system?**
  _48 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Minecraft` be split into smaller, more focused modules?**
  _Cohesion score 0.0701344243132671 - nodes in this community are weakly interconnected._
- **Should `TeleportServer` be split into smaller, more focused modules?**
  _Cohesion score 0.07532467532467532 - nodes in this community are weakly interconnected._
- **Should `TeleportTransitionController.java` be split into smaller, more focused modules?**
  _Cohesion score 0.07665505226480836 - nodes in this community are weakly interconnected._