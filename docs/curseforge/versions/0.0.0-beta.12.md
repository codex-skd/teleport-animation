## Teleport Animation 0.0.0-beta.12

### Changes since beta.11

- Added mixin for `teleport(TeleportTransition)` — the new teleport method in Minecraft 26.1.2
- Kept existing `teleportTo` mixins as fallback
- Updated WORKFLOW.md with language conventions and clean build policy

### Known issues

- Teleport animation may not trigger for all teleport sources
- Client-side mixins still disabled
