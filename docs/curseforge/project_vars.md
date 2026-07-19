# CurseForge — Variables del proyecto

## Proyecto

| Variable | Valor |
|----------|-------|
| `curseforge_project_id` | `1608291` |
| `mod_id` | `teleport_animation` |
| `display_name` | `Teleport Animation` (separado, no junto) |

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
| `minecraft_version` | `26.1.2` |
| `framework` | `neoforge` |
| `java_version` | `25` |
| `environment` | `Client`, `Server` |

## Rama

```
minecraft/26.1.2/neoforge-26.1.2.78/production
```

## Tag

Formato: `<mc-version>-<framework>-<version>`
Ejemplo: `26.1.2-neoforge-0.0.0-beta.16`

## Parámetros del upload

| Campo | Valor | Notas |
|-------|-------|-------|
| `displayName` | `Teleport Animation (<version>)` | Nombre visible: `display_name (version)` |
| `changelog` | HTML (no Markdown) | Ver estructura abajo |
| `changelogType` | `html` | Obligatorio para que se vea bien |
| `releaseType` | `beta` o `release` | `beta` para betas, `release` para estables |
| `gameVersionNames` | `["Client", "Server", "26.1.2", "NeoForge"]` | Entorno + MC + modloader |

## Estructura del changelog (HTML)

```html
<h2>v0.0.0-beta.X - Titulo descriptivo</h2>

<h3>Fix</h3>
<ul>
<li><strong>Problema</strong>: descripcion con <code>codigo</code>.</li>
<li><strong>Otro</strong>: descripcion.</li>
</ul>

<h3>Technical Changes</h3>
<ul>
<li><code>Clase/metodo()</code> — descripcion.</li>
</ul>

<h3>Notes</h3>
<blockquote>Nota importante para servidores.</blockquote>

<hr>

<p><strong>JAR</strong>: <code>teleport_animation-26.1.2-neoforge-&lt;version&gt;.jar</code></p>
```

## Subir archivo (JAR) con Python

```python
import json, uuid, urllib.request

boundary = uuid.uuid4().hex
version = "0.0.0-beta.16"
release_type = "beta"

metadata = {
    "displayName": f"Teleport Animation ({version})",
    "changelog": open(f"docs/curseforge/versions/{version}.md", "r", encoding="utf-8").read(),
    "changelogType": "html",
    "gameVersionNames": ["Client", "Server", "26.1.2", "NeoForge"],
    "releaseType": release_type
}

with open(f"build/libs/teleport_animation-26.1.2-neoforge-{version}.jar", "rb") as f:
    jar_data = f.read()

meta_bytes = json.dumps(metadata, ensure_ascii=False).encode("utf-8")

body = b""
body += f"--{boundary}\r\n".encode()
body += b'Content-Disposition: form-data; name="metadata"\r\n'
body += b"Content-Type: application/json\r\n\r\n"
body += meta_bytes + b"\r\n"
body += f"--{boundary}\r\n".encode()
body += f'Content-Disposition: form-data; name="file"; filename="teleport_animation-26.1.2-neoforge-{version}.jar"\r\n'.encode()
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

1. `./gradlew clean build`
2. Actualizar `docs/curseforge/versions/<version>.md` con HTML
3. Actualizar `CHANGELOG.md`
4. `git commit -m "fix: descripcion\n\nvX.Y.Z"` + `git push`
5. `git tag -a 26.1.2-neoforge-<version> -m "vX.Y.Z: descripcion"` + `git push origin <tag>`
6. Subir JAR a CurseForge con Python
7. Verificar con GET que el changelog se vea bien
8. Liberar manualmente desde la web si es necesario
