# CurseForge — Variables del proyecto

> Variables en formato `key = value` para el script compartido `codex-docs/scripts/curseforge-upload.ps1`

project_id = 1608291
api_token = ee776b0a-ee95-4850-b554-06be02a8657f
game_versions = 9638, 9639, 11779, 10150
release_type = release

## Proyecto

| Variable | Valor |
|----------|-------|
| `curseforge_project_id` | `1608291` |
| `mod_id` | `teleport_animation` |
| `display_name` | `Grand Teleport` |

## Tokens

| API | Token | Uso |
|-----|-------|-----|
| Upload | `ee776b0a-ee95-4850-b554-06be02a8657f` | Subir archivos JAR |
| Core (GET) | `$2a$10$yGwryAfmRkS9ZJsJUDf5YOKZpOIsmHB8Fji2D8JVCKBSZEKYlwmaO` | Consultar datos del mod |

Autenticación Upload: cabecera `X-Api-Token`
Autenticación Core: cabecera `x-api-key`

## Versión actual

| Variable | Valor |
|----------|-------|
| `minecraft_version` | `1.21.1` |
| `framework` | `neoforge` |
| `java_version` | `21` |
| `environment` | `Client`, `Server` |

## Rama

```
minecraft/1.21.1/neoforge-21.1.235/production
```

## Tag

Formato: `<mc-version>-<framework>-<version>`
Ejemplo: `1.21.1-neoforge-0.0.0-beta.2`

## Parámetros del upload

| Campo | Valor | Notas |
|-------|-------|-------|
| `displayName` | `Teleport Animation (0.0.0-beta.X)` | Nombre visible: `display_name (version)` |
| `changelog` | HTML (no Markdown) | Ver estructura abajo |
| `changelogType` | `html` | Obligatorio para que se vea bien |
| `releaseType` | `beta` o `release` | Según el tipo de versión |
| `gameVersionNames` | `["Client", "Server", "1.21.1", "NeoForge"]` | Entorno + MC + modloader |

## Estructura del changelog (HTML)

```html
<h2>v0.0.0-beta.X - Titulo descriptivo</h2>

<h3>Added</h3>
<ul>
<li><strong>Feature</strong>: descripcion con <code>codigo</code>.</li>
</ul>

<h3>Changed</h3>
<ul>
<li><strong>Item</strong>: descripcion.</li>
</ul>

<h3>Fix</h3>
<ul>
<li><strong>Issue</strong>: descripcion.</li>
</ul>

<hr>

<p><strong>JAR</strong>: <code>teleport_animation-1.21.1-neoforge-0.0.0-beta.X.jar</code></p>
```

## Subir archivo (JAR) con Python

```python
import json, uuid, urllib.request

boundary = uuid.uuid4().hex
version = "0.0.0-beta.X"

metadata = {
    "displayName": f"Teleport Animation ({version})",
    "changelog": "<h2>v{version} - Titulo</h2>",
    "changelogType": "html",
    "gameVersionNames": ["Client", "Server", "1.21.1", "NeoForge"],
    "releaseType": "beta"
}

with open(f"build/libs/teleport_animation-1.21.1-neoforge-{version}.jar", "rb") as f:
    jar_data = f.read()

meta_bytes = json.dumps(metadata, ensure_ascii=False).encode("utf-8")

body = b""
body += f"--{boundary}\r\n".encode()
body += b'Content-Disposition: form-data; name="metadata"\r\n'
body += b"Content-Type: application/json\r\n\r\n"
body += meta_bytes + b"\r\n"
jar_filename = f"teleport_animation-1.21.1-neoforge-{version}.jar"
body += f"--{boundary}\r\n".encode()
body += f'Content-Disposition: form-data; name="file"; filename="{jar_filename}"\r\n'.encode()
body += b"Content-Type: application/java-archive\r\n\r\n"
body += jar_data + b"\r\n"
body += f"--{boundary}--\r\n".encode()

req = urllib.request.Request(
    f"https://minecraft.curseforge.com/api/projects/1608291/upload-file",
    data=body,
    headers={
        "X-Api-Token": "ee776b0a-ee95-4850-b554-06be02a8657f",
        "Content-Type": f"multipart/form-data; boundary={boundary}"
    },
    method="POST"
)

resp = urllib.request.urlopen(req)
print(resp.read().decode())
```

## Verificar con GET

```bash
curl -s "https://api.curseforge.com/v1/mods/1608291/files/<FILE_ID>" \
  -H "x-api-key: $2a$10$yGwryAfmRkS9ZJsJUDf5YOKZpOIsmHB8Fji2D8JVCKBSZEKYlwmaO"
```

## Changelog

```bash
curl -s "https://api.curseforge.com/v1/mods/1608291/files/<FILE_ID>/changelog" \
  -H "x-api-key: $2a$10$yGwryAfmRkS9ZJsJUDf5YOKZpOIsmHB8Fji2D8JVCKBSZEKYlwmaO"
```

## Descripcion del proyecto

No hay endpoint API para actualizar la descripcion. Se edita manualmente desde la web de CurseForge pegando el HTML de `docs/curseforge/project_description.md`.

## Flujo completo

1. `./gradlew.bat clean build`
2. Actualizar `docs/curseforge/versions/<version>.md` con HTML
3. Actualizar `CHANGELOG.md`
4. `git commit -m "fix: descripcion\n\nvX.Y.Z"` + `git push`
5. `git tag -a 1.21.1-neoforge-<version> -m "vX.Y.Z: descripcion"` + `git push origin <tag>`
6. Subir JAR a CurseForge con Python
7. Verificar con GET que el changelog se vea bien
8. Liberar manualmente desde la web si es necesario

## Historial

- **1.2.0** (2026-09-03) — CurseForge file `8801533` (project `1608291`, `release`). Port del backend
  de config de la rama 26.2: se sustituye el `config/teleport_animation.properties` hecho a mano por
  `ModConfigSpec` nativo de NeoForge (`ModConfig.Type.CLIENT`), generando
  `config/teleport_animation-client.toml` al primer arranque y visible en Configured. Eliminada la capa
  muerta `configLayout*`/`configWidget*`/`configText*` (~490 líneas, sin llamadas) y la migración de
  `.properties` legacy. API pública de `TeleportConfig` sin cambios → comportamiento de teleport
  idéntico a 1.1.0. Reescritura delegada en OpenCode (`mimo-v2.5`), verificada con `./gradlew build` +
  grep de callers. No arrancado en juego (dev env sin Balm/Waystones). Tag `1.21.1-neoforge-1.2.0`.

- **1.1.0** (2026-09-02) — CurseForge file `8791079` (project `1608291`, `release`). Sync de los 5 fixes
  de comportamiento que la línea 26.2 recibió en `1.1.0`→`1.2.0` y a esta rama 1.21.1 le faltaban
  (crash de servidor en teleport externo; flicker de suelo en travel top-down; slide para waystone
  cercano; llegada un bloque abajo / atravesando el suelo; spawn bajo tierra en viajes largos por no
  forzar los chunks destino). Feature set sin cambios (solo-Waystones, config `.properties`, Waystones
  required). `compileJava` + `build` OK; el rework de cámara/flicker no revisado en juego. `runServer`
  no ejecutado aquí (el entorno de dev no tiene Balm, dependencia de Waystones). Tag
  `1.21.1-neoforge-1.1.0`.
