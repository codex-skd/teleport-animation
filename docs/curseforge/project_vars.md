# CurseForge — Variables del proyecto

> Las siguientes variables son leídas automáticamente por `../../codex-docs/scripts/curseforge-upload.ps1`

project_id = 1608291
api_token = ee776b0a-ee95-4850-b554-06be02a8657f
game_versions = 9638, 9639, 10150, 9990
release_type = beta

## Proyecto

| Variable | Valor |
|----------|-------|
| `curseforge_project_id` | `1608291` |
| `mod_id` | `teleport_animation` |
| `display_name` | `Teleport Animation` |

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
| `minecraft_version` | `1.20.1` |
| `framework` | `neoforge` (legacyforge / Forge-compatible) |
| `java_version` | `17` |
| `environment` | `Client`, `Server` |

## Rama

```
minecraft/1.20.1/neoforge-47.1.99/production
```

## Tag

Formato: `<mc-version>-<framework>-<version>`
Ejemplo: `1.20.1-neoforge-0.0.0-beta.1`

## Parámetros del upload

| Campo | Valor | Notas |
|-------|-------|-------|
| `displayName` | `Teleport Animation (<version>)` | Nombre visible: `display_name (version)` |
| `changelog` | HTML (no Markdown) | Ver estructura abajo |
| `changelogType` | `html` | Obligatorio para que se vea bien |
| `releaseType` | `beta` o `release` | `beta` para betas, `release` para estables |
| `gameVersionNames` | `["Client", "Server", "1.20.1", "NeoForge"]` | Entorno + MC + modloader |

## Estructura del changelog (HTML)

```html
<h2>v0.0.0-beta.X - Titulo descriptivo</h2>

<h3>Added</h3>
<ul>
<li><strong>Feature</strong>: descripcion con <code>codigo</code>.</li>
</ul>

<h3>Fix</h3>
<ul>
<li><strong>Issue</strong>: descripcion.</li>
</ul>

<hr>

<p><strong>JAR</strong>: <code>teleport_animation-1.20.1-neoforge-47.1.99-0.0.0-beta.X.jar</code></p>
```

## Subir archivo (JAR) con Python

```python
import json, uuid, urllib.request

boundary = uuid.uuid4().hex
version = "0.0.0-beta.1"

metadata = {
    "displayName": f"Teleport Animation ({version})",
    "changelog": open(f"docs/curseforge/versions/{version}.md", "r", encoding="utf-8").read(),
    "changelogType": "html",
    "gameVersionNames": ["Client", "Server", "1.20.1", "NeoForge"],
    "releaseType": "beta"
}

with open(f"build/libs/teleport_animation-1.20.1-neoforge-47.1.99-{version}.jar", "rb") as f:
    jar_data = f.read()

meta_bytes = json.dumps(metadata, ensure_ascii=False).encode("utf-8")

body = b""
body += f"--{boundary}\r\n".encode()
body += b'Content-Disposition: form-data; name="metadata"\r\n'
body += b"Content-Type: application/json\r\n\r\n"
body += meta_bytes + b"\r\n"
jar_filename = f"teleport_animation-1.20.1-neoforge-47.1.99-{version}.jar"
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
5. `git tag -a 1.20.1-neoforge-<version> -m "vX.Y.Z: descripcion"` + `git push origin <tag>`
6. Subir JAR a CurseForge con Python
7. Verificar con GET que el changelog se vea bien
8. Liberar manualmente desde la web si es necesario
