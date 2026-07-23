# Flujo de trabajo — Teleport Animation (NeoForge)

> **Versión del workflow**: 1.2.7 (codex-docs)
> Este archivo pertenece al proyecto **Teleport Animation**. Cada proyecto tiene su propio `WORKFLOW_<MOD_ID>_<MC-VERSION>.md`.
> No es un archivo central ni template compartido. Los cambios aquí solo afectan a este proyecto.
> Para actualizar este workflow, revisar la última versión en `codex-docs/WORKFLOW_GENERIC.md`.

## Convenciones de nomenclatura

| Convención | Uso | Ejemplo |
|---|---|---|
| **snake_case** | `mod_id` en gradle.properties, assets/, packages Java | `teleport_animation` |
| **PascalCase** | Clases Java principales | `TeleportAnimation` |
| **camelCase** | Variables, métodos, config keys | `teleportAnimation` |
| **Title Case** | Display name en README, CHANGELOG, docs, CurseForge | `Teleport Animation` |

Reglas:
- `mod_id` en `gradle.properties` debe coincidir con el nombre del directorio del proyecto
- El display name en `README.md` y `CHANGELOG.md` debe estar en **Title Case**
- Las clases Java principales deben seguir el naming del `mod_id` pero en **PascalCase**:
- Las config keys en camelCase: `teleportAnimation.enableFeature`

## Tipografía

| Ámbito | Fuente |
|---|---|
| Código fuente, logs, nombres técnicos, commits, mensajes de consola | **Monospace** (`Consolas`, `JetBrains Mono`, `Cascadia Code`, `Fira Code`) |
| Documentación interna (README, CHANGELOG, docs/, WORKFLOW) | **Sans-serif** (`Segoe UI`, `Inter`, `Arial`) para cuerpo; **monospace** para código/rutas/comandos |
| CurseForge (descripciones, release notes) | Sans-serif por defecto de la plataforma; usar `<code>` para términos técnicos |

## Estructura del proyecto

```
<mod>/
├── build.gradle                        # Build con net.neoforged.moddev
├── gradle.properties                   # mod_id, mod_version, mod_group_id...
├── settings.gradle
├── src/
│   ├── main/
│   │   ├── java/<package>/             # Código fuente del mod
│   │   ├── resources/
│   │   │   ├── assets/<mod_id>/        # Texturas, shaders, lang, modelos...
│   │   │   │   └── icon.png           # Logo del mod (64x64 píxeles, referenciado en neoforge.mods.toml)
│   │   │   ├── templates/
│   │   │   │   └── META-INF/
│   │   │   │       └── neoforge.mods.toml  # Template con placeholders ${...}
│   │   │   └── <mod_id>.mixins.json
│   │   └── templates/                 # (alternativa legacy, evitar)
│   │       └── META-INF/
│   │           └── neoforge.mods.toml
│   ├── main/java/<package>/...         # Código fuente
├── libs/                               # Dependencias reales del mod (JARs necesarios para compilar). Versionado.
├── lib_ext/                            # Librerías externas para análisis de la sesión. NO versionado (.gitignore).
├── docs/
│   ├── WORKFLOW.md                    # Este documento (copia de WORKFLOW_GENERIC.md adaptada al mod)
│   └── curseforge/                    # Documentación para publicación en CurseForge
│       ├── project_vars.md             # Variables del proyecto (ID, token, versiones)
│       ├── project_description.md      # Descripción del proyecto
│       └── versions/                   # Release notes por versión
│           ├── 0.0.0-beta.1.md
│           └── ...
├── CHANGELOG.md
├── README.md
├── graphify-out/                       # Knowledge Graph (generado por Graphify). Versionado en GitLab, NO va a GitHub (excluido por CI).
│   ├── graph.html
│   ├── GRAPH_REPORT.md
│   └── graph.json
└── .gitlab-ci.yml                      # CI/CD: publica código limpio a main para mirror a GitHub
```

### Archivos de CurseForge

| Archivo | Propósito |
|---|---|
| `docs/curseforge/project_vars.md` | Variables específicas del proyecto (project ID, token, versiones) |
| `docs/curseforge/project_description.md` | Descripción completa del proyecto (qué hace, características, requisitos) |
| `docs/curseforge/versions/<version>.md` | Release notes de cada versión que se sube a CurseForge. Solo se agrega cuando se va a publicar esa versión |

Las variables de cada proyecto (project ID, API token, versiones de Minecraft/NeoForge/Java) se documentan en `docs/curseforge/project_vars.md`. No duplicar aquí.

### Formato de descripciones CurseForge

CurseForge admite **Markdown y HTML** en las descripciones y release notes. Usamos ambos porque:

- Se versiona junto al código en el repositorio
- Es portátil (funciona en GitHub, GitLab, etc.)
- El HTML permite control preciso sobre espaciado, alineación y estructura visual
- El Markdown es más limpio para listas, tablas y código

Usamos HTML tanto para la **descripción general del proyecto** (`project_description.md`) como para las **release notes** (`versions/<version>.md`), ya que el contenido de estos archivos se sube directamente a CurseForge, que renderiza HTML correctamente.

#### Estructura de la descripción general

```
Header:    Título principal (h1 centrado) + tagline
           Separador
Cuerpo:    Overview en párrafos (h2)
           Features con h3 + párrafo descriptivo cada una
           Tabla de requisitos
           Lista de uso
           Separador
Footer:    Créditos
           Logo centrado + enlace web + eslogan
```

#### Elementos HTML disponibles

| Elemento | Uso |
|---|---|
| `<h1 align="center">` | Título principal centrado |
| `<h2>` | Secciones del cuerpo |
| `<h3>` | Subsecciones (cada feature) |
| `<p>` | Párrafos con espaciado natural |
| `<br>` | Saltos de línea para separar bloques |
| `<hr>` | Separadores visuales entre secciones |
| `<table>` | Datos estructurados (requisitos) |
| `<ol>` / `<ul>` | Listas ordenadas y sin orden |
| `<img>` | Logos e imágenes |
| `<a>` | Enlaces externos |
| `<code>` | Comandos y rutas técnicas |
| `<blockquote>` | Notas destacadas |
| `<strong>` / `<em>` | Negritas y cursivas |
| `<p align="center">` | Bloques centrados (footer) |

#### Buenas prácticas

- **Respetar la estructura**: Header → Cuerpo → Footer, con separadores visuales
- **Interlineado**: Usar `<br>` entre bloques, no acumular párrafos seguidos
- **Títulos diferenciados**: h1 muy visible (centrado), h2 para secciones, h3 para cada feature
- **Logo en el footer**: Centrado, con enlace a la web y eslogan
- **Sin carácter retroactivo**: Solo aplicamos el formato a nuevas versiones; las existentes no se modifican
- **Idioma**: CurseForge en **inglés** (en-US) — plataforma global

#### Formato del changelog

El changelog se envía en formato **HTML**, no Markdown. Aunque CurseForge acepta ambos, el HTML se renderiza correctamente en el editor WYSIWYG sin escapes ni caracteres rotos.

| Campo | Valor |
|---|---|
| `changelogType` | `html` |
| `changelog` | Código HTML con `<h2>`, `<h3>`, `<ul>/<li>`, `<p>`, `<strong>`, `<code>`, `<blockquote>` |

**Regla importante**: El valor del campo `changelog` en la subida a CurseForge debe ser **exactamente el contenido del archivo** `docs/curseforge/versions/<version>.md`. No resumir, no modificar, no acortar. El archivo ya contiene el HTML que se envía.

#### Ejemplo de estructura HTML para release notes

```html
<h2>v0.0.0-beta.X - Titulo descriptivo</h2>

<h3>Fix</h3>
<ul>
<li><strong>Issue</strong>: description with <code>code</code>.</li>
</ul>

<h3>Technical Changes</h3>
<ul>
<li><code>Class.method()</code> — description.</li>
</ul>
```

#### Elementos HTML permitidos

| Elemento | Uso |
|---|---|
| `<h2>` | Título principal de la versión |
| `<h3>` | Subsecciones (Fix, Technical Changes, Notes) |
| `<ul><li>` | Listas de puntos |
| `<strong>` | Negritas para resaltar |
| `<code>` | Código o nombres técnicos |
| `<blockquote>` | Notas importantes para servidores |
| `<hr>` | Separador |
| `<p>` | Párrafos |

---

## Ramas

### Estructura

| Rama | Propósito |
|---|---|
| `main` | ~~Eliminar.~~ Ya no existe. La default ahora es `*/production` |
| `minecraft/<mc-version>/neoforge-<neo-version>/production` | **Rama por defecto**. Rama de trabajo con todo el proyecto: código, docs/, lib_ext/, graphify-out/, tokens reales |
| `minecraft/<mc-version>/neoforge-<neo-version>/main` | **Rama protegida**. Recibe el mirror a GitHub. Solo contiene código fuente compilable. Se actualiza vía CI/CD con force push |

### Ejemplos

| Rama | Versión |
|---|---|
| `minecraft/1.21.1/neoforge-21.1.235/production` | Trabajo diario en Minecraft 1.21.1 |
| `minecraft/1.21.1/neoforge-21.1.235/main` | Código público para GitHub (misma versión) |

### Esquema de publicación

```
GitLab (privado)                         GitHub (público)
─────────────────────                    ──────────────────────
minecraft/X/N/production
  (código + docs/ + lib_ext/             minecraft/X/N/main
   + graphify-out/ + tokens)              (solo código + libs/
       │                                   + README + placeholders)
       │  CI/CD: filtra, sanitiza,
       │  commitea con force push
       ▼  a la rama */main hermana
  minecraft/X/N/main ──────────────────→ minecraft/X/N/main
       │         (mirror push automático)
       ▼
    GitHub: minecraft/X/N/main
    (espejo exacto de GitLab)
```

Cada versión de Minecraft/NeoForge tiene su propio par `production` ↔ `main`. El mirror de GitLab replica **todas** las ramas `*/main` a GitHub automáticamente.

### Inicialización única de cada rama `*/main`

Cada vez que se crea una rama `production` para una nueva versión, la agente (sesión) debe crear su hermana `main` inmediatamente después. Sin este paso, el CI/CD fallará (ya no la crea automáticamente).

> La rama `main` raíz (vacía) puede y debe eliminarse. La rama por defecto del repositorio debe ser `*/production`. Si GitLab no permite borrar la rama por defecto, cámbiala primero a `*/production` en Settings → Repository → Default branch.

**Responsabilidades:**

| Rol | Acción |
|---|---|
| **Agente (sesión)** | Crear la rama `*/main` desde `*/production` y pushearla |
| **Operador (desarrollador)** | Cambiar rama por defecto a `*/production` y eliminar `main` raíz. También proteger ramas `*/main` y configurar mirror a GitHub |

**1. La agente crea la rama `*/main`** (al crear `production`):

```bash
# Ejemplo: para minecraft/1.21.1/neoforge-21.1/production
git checkout minecraft/1.21.1/neoforge-21.1/production
git checkout -b minecraft/1.21.1/neoforge-21.1/main
git push origin minecraft/1.21.1/neoforge-21.1/main
git checkout minecraft/1.21.1/neoforge-21.1/production
```

Esto solo se hace **una vez por versión**. A partir de ahí el CI/CD mantiene `*/main` actualizada con force push automático.

**2. El operador configura el repositorio** (una sola vez por repo):

1. **Settings → Repository → Default branch**: cambiar a `minecraft/*/neoforge-*/production` (la rama de trabajo, la que se ve al clonar)
2. **Settings → Repository → Branches**: eliminar `main` raíz (si existe)
3. **Settings → Repository → Protected branches**: proteger `minecraft/*/neoforge-*/main` con force push permitido (es la rama del mirror, necesita protección)
4. **Settings → Repository → Mirroring repositories**: configurar mirror a GitHub

> ⚠️  Las ramas `*/main` nunca se tocan manualmente después de creadas. Solo el CI/CD escribe en ellas con force push.

---

## Versionado

### Esquema

| Estado | Formato | Ejemplos |
|---|---|---|
| Beta / desarrollo | `0.0.0-beta.X` | `0.0.0-beta.1`, `0.0.0-beta.2` |
| Release estable | `X.Y.Z` (SemVer) | `1.0.0`, `1.2.3`, `2.0.0` |

**SemVer** (Semantic Versioning):
- `MAJOR`: cambios incompatibles en API o funcionalidad breaking
- `MINOR`: nuevas funcionalidades compatibles hacia atrás
- `PATCH`: bug fixes compatibles hacia atrás

### ¿Cuándo incrementar versión?

- Cada vez que se hace un commit con cambios funcionales (no solo documentación)
- Al preparar una subida a CurseForge

La versión se define en `gradle.properties`:

```properties
mod_version=0.0.0-beta.1
```

### Nombre del JAR

El JAR generado sigue el formato `<mod_id>-<minecraft_version>-<framework>-<mod_version>.jar`:

| Ejemplo | Significado |
|---|---|
| `teleport_animation-1.21.1-neoforge-0.0.0-beta.2.jar` | NeoForge 1.21.1, beta 2 |
| `teleport_animation-1.21.1-neoforge-1.0.0.jar` | NeoForge 1.21.1, release 1.0.0 |

El framework puede ser `neoforge`, `forge` o `fabric` según corresponda. Se configura en `build.gradle`:

```groovy
base {
    archivesName = "${mod_id}-${minecraft_version}-neoforge"
}
```

---

## Commits (Conventional Commits)

Usamos [Conventional Commits](https://www.conventionalcommits.org/) para todos los mensajes:

```
<tipo>[<ámbito>]: <descripción>

[body opcional]
```

### Tipos

| Tipo | Uso |
|---|---|
| `feat` | Nueva funcionalidad |
| `fix` | Corrección de bug |
| `refactor` | Refactorización sin cambio funcional |
| `docs` | Documentación |
| `chore` | Tareas de mantenimiento (build, CI, etc.) |
| `style` | Cambios de formato (espacios, commas, etc.) |
| `perf` | Mejora de rendimiento |
| `test` | Añadir o modificar tests |

### Ejemplos

```
feat: add player idle detection with particle indicator
fix: resolve crash on world load due to null config
refactor: extract networking logic into separate class
docs: update curseforge project description
chore: bump version to 0.0.0-beta.3
```

El mensaje del commit **debe incluir la versión** en el formato `v<version>`:

```
git commit -m "feat: add player idle detection

v0.0.0-beta.1"
```

---

## Tags (GitLab)

Cada vez que se sube una versión a CurseForge se debe crear un tag en GitLab.

### Formato del tag

| Estado | Formato | Ejemplo |
|---|---|---|
| Beta | `<mc-version>-neoforge-beta.X` | `1.21.1-neoforge-beta.15` |
| Release | `<mc-version>-neoforge-X.Y.Z` | `1.21.1-neoforge-1.0.0` |

El prefijo `<mc-version>-neoforge` se adapta según la versión de Minecraft y el framework de la rama actual.

### Ejemplos

```bash
# Beta
git tag -a 1.21.1-neoforge-beta.15 -m "v0.0.0-beta.15: Updated WORKFLOW.md"
git push origin 1.21.1-neoforge-beta.15

# Release estable
git tag -a 1.21.1-neoforge-1.0.0 -m "v1.0.0: First stable release"
git push origin 1.21.1-neoforge-1.0.0
```

---

## Publicación a GitHub (CI/CD)

Cada vez que se hace push a una rama `production`, GitLab CI ejecuta automáticamente un pipeline que:
1. Detecta desde qué rama `production` se disparó
2. Deriva la rama `main` hermana: `minecraft/X/N/production` → `minecraft/X/N/main`
3. Filtra solo los archivos públicos (`src/`, `build.gradle`, `settings.gradle`, `libs/`, etc.)
4. Sanitiza `gradle.properties` (reemplaza tokens reales con placeholders)
5. Commitea con force push a la rama `*/main` hermana
6. El mirror de GitLab replica esa rama a GitHub automáticamente

### Variables de CI/CD (grupo GitLab)

Estas variables se configuran en **Settings → CI/CD → Variables** a nivel de grupo `stalking-dragons/minecraft`. Así todos los proyectos del grupo tienen acceso automático sin repetirlas:

| Variable | Propósito |
|---|---|
| `GITLAB_PUSH_TOKEN` | Token de GitLab con permisos de API y push. Usado por el CI para hacer force push a `*/main` |
| `GH_USERNAME` | Usuario de GitHub (`santiagolosadaborrajo`) |
| `GH_TOKEN` | Token de GitHub con permisos de push a repos. Usado para autenticar el mirror |

> Los tokens personales del desarrollador se almacenan localmente en `codex-docs/secrets.md` (excluido vía `.gitignore`). No se suben al repositorio.

### Requisito previo

Antes de que el CI/CD funcione, la rama `main` hermana debe existir al menos una vez en el remoto. Ver [Inicialización única de cada rama `*/main`](#inicialización-única-de-cada-rama-main).

### .gitlab-ci.yml

Ubicado en la raíz del proyecto:

```yaml
image: alpine:latest

variables:
  GIT_DEPTH: 0

stages:
  - publish

publish-public:
  stage: publish
  only:
    - /^minecraft\/.*\/.*\/production$/
  except:
    - main
  script:
    - apk add --no-cache git
    - git config user.email "ci@mods-minecraft.dev"
    - git config user.name "Mods Minecraft CI"

    # Derivar la rama main: minecraft/X/N/production → minecraft/X/N/main
    - MAIN_BRANCH=$(echo "$CI_COMMIT_BRANCH" | sed 's|/production$|/main|')
    - echo "Publishing to $MAIN_BRANCH"

    # Obtener la rama main hermana. Si no existe, falla — el agente debe crearla manualmente.
    - |
      if ! git fetch origin "$MAIN_BRANCH" 2>/dev/null; then
        echo "ERROR: $MAIN_BRANCH no existe. Créala desde production primero."
        exit 1
      fi
    - git checkout "$MAIN_BRANCH"

    # Limpiar y copiar solo archivos públicos desde production
    - git rm -rf --ignore-unmatch --quiet . 2>/dev/null || true

    # Archivos obligatorios (deben existir en todos los mods)
    - git checkout "$CI_COMMIT_SHA" -- src/ build.gradle settings.gradle gradle.properties gradlew gradlew.bat .gitignore README.md CHANGELOG.md

    # Archivos opcionales (pueden no existir en algunos mods)
    - git checkout "$CI_COMMIT_SHA" -- libs/ 2>/dev/null || true

    # Sanitizar secrets en gradle.properties
    - sed -i 's/^mod_version=.*/mod_version=0.0.0/' gradle.properties
    - sed -i 's/^mod_group_id=.*/mod_group_id=com\.skd\.placeholder/' gradle.properties
    - sed -i 's/^mod_curseforge_project_id=.*/mod_curseforge_project_id=/' gradle.properties
    # Nota: el API token de CurseForge está en docs/curseforge/project_vars.md,
    # no en gradle.properties. No se sanitiza aquí porque GitLab es privado.

    # Commit y push (force push a la rama main hermana)
    - git add -A
    - |
      if ! git diff --cached --quiet; then
        git commit -m "chore: sync public code from ${CI_COMMIT_SHORT_SHA}"
        git push --force "https://oauth2:${GITLAB_PUSH_TOKEN}@${CI_SERVER_HOST}/${CI_PROJECT_PATH}.git" HEAD:"$MAIN_BRANCH"
      else
        echo "No changes to publish"
      fi
```

### Archivos que pasan a GitHub

| Archivo/Carpeta | GitLab production | GitLab */main → GitHub |
|---|---|---|
| `src/` | ✅ | ✅ |
| `build.gradle`, `settings.gradle` | ✅ | ✅ |
| `gradle.properties` | ✅ (tokens reales) | ✅ (placeholders) |
| `gradlew`, `gradlew.bat` | ✅ | ✅ |
| `README.md` | ✅ | ✅ |
| `CHANGELOG.md` | ✅ | ✅ |
| `libs/` | ✅ | ✅ |
| `.gitignore` | ✅ | ✅ |
| `docs/` | ✅ | ❌ |
| `lib_ext/` | ✅ | ❌ |
| `graphify-out/` | ✅ | ❌ (excluido por CI) |
| `build/` | ❌ (.gitignore) | ❌ |

---

## Flujo completo (paso a paso)

### 1. Desarrollo

```bash
# Situarse en la rama de la versión correspondiente
git checkout minecraft/1.21.1/neoforge-21.1.235/production

# Hacer cambios en el código
# Compilar para verificar
./gradlew.bat build

# Commit con Conventional Commits
git add -A
git commit -m "feat: add typing indicator particles

v0.0.0-beta.2"

# Push
git push
```

### 2. Copiar a instancia de pruebas

```bash
# 1. Compilar con clean
./gradlew.bat clean build

# 2. Copiar JAR a la instancia de CurseForge, reemplazando el anterior
#    PREGUNTAR: "¿Copiar el JAR a la instancia de pruebas?"
#    Solo hacer si el usuario confirma.

# 3. Si el usuario confirma:
#    cp build/libs/teleport_animation-1.21.1-neoforge-<version>.jar /ruta/a/la/instancia/mods/
#    rm /ruta/a/la/instancia/mods/teleport_animation-1.21.1-neoforge-<version-anterior>.jar
```

### 3. Probar en instancia

- El usuario abre Minecraft y verifica que funcione
- Si hay errores, se vuelve a Desarrollo (paso 1)
- Si funciona, se continúa

### 4. Preparar versión para CurseForge

```bash
# 1. PREGUNTAR: "¿Subir esta versión a CurseForge?"
#    Solo continuar si el usuario confirma.

# 2. Actualizar versión en gradle.properties
#    mod_version=0.0.0-beta.3

# 3. Compilar con clean
./gradlew.bat clean build

# 4. Crear release notes
#    docs/curseforge/versions/0.0.0-beta.3.md

# 5. Actualizar CHANGELOG.md

# 6. Commit del bump de versión
git add -A
git commit -m "chore: bump version to 0.0.0-beta.3"

# 7. Tag para CurseForge
git tag -a 1.21.1-neoforge-beta.3 -m "v0.0.0-beta.3: Bugfix release"
git push origin 1.21.1-neoforge-beta.3

# 8. PREGUNTAR: "¿Subir JAR a CurseForge ahora?"
#    Solo subir si el usuario confirma.
#    El JAR está en build/libs/teleport_animation-1.21.1-neoforge-<version>.jar

# 9. Subir a CurseForge usando el script compartido
#    powershell -File ../codex-docs/scripts/curseforge-upload.ps1
#
#    Este script lee project_vars.md (project_id, api_token) y gradle.properties
#    (mod_id, mod_name, mod_version) y sube el JAR automáticamente.
#    Es el mismo script para todos los mods, vive en codex-docs.
```

### 5. Release estable

```bash
# gradle.properties -> mod_version=1.0.0
git commit -m "chore: bump version to 1.0.0"
git tag -a 1.21.1-neoforge-1.0.0 -m "v1.0.0: First stable release"
git push origin 1.21.1-neoforge-1.0.0
```

### 6. Actualizar Knowledge Graph (Graphify)

Después de cada push a remoto, actualizar el grafo de conocimiento:

```bash
# 1. Extraer AST del código
#    Ruta al ejecutable (Windows):
"C:\Users\llagu\AppData\Local\Packages\PythonSoftwareFoundation.Python.3.13_qbz5n2kfra8p0\LocalCache\local-packages\Python313\Scripts\graphify.exe" extract . --code-only

# 2. Generar reporte y clusterizar
"C:\Users\llagu\AppData\Local\Packages\PythonSoftwareFoundation.Python.3.13_qbz5n2kfra8p0\LocalCache\local-packages\Python313\Scripts\graphify.exe" cluster-only .

# 3. Commit del grafo actualizado
git add graphify-out/
git commit -m "chore: update knowledge graph"

# 4. Push
git push
```

> **Nota**: El grafo permite a los asistentes de IA entender la arquitectura del mod sin leer todo el código fuente, reduciendo el consumo de tokens hasta 71×.

## Historial de versiones del workflow

| Versión | Fecha | Cambios |
|---|---|---|
| 1.2.7 | 2026-07-23 | Sync: CI y ramas actualizados desde GENERIC v1.2.7 |
| 1.2.6 | 2026-07-23 | Fix YAML en CI: `|| (&&)` reemplazado por bloque `if` para evitar error de sintaxis |
| 1.2.5 | 2026-07-23 | `*/main` es ahora la rama por defecto, `main` raíz eliminada |
| 1.2.4 | 2026-07-23 | Roles clarificados: agente crea `*/main`, operador elimina `main` raíz + protege + mirror |
| 1.2.3 | 2026-07-23 | CI: elimina creación automática de `*/main` (orphan). Si no existe, falla. Rama `main` raíz marcada para eliminar |
| 1.2.2 | 2026-07-23 | CI: `libs/` separado como opcional en checkout para no fallar si el mod no lo tiene |
| 1.2.1 | 2026-07-21 | Limpieza de tabla de ramas (eliminadas filas duplicadas) |
| 1.2.0 | 2026-07-21 | Separación clara de roles: agente crea `*/main`, operador protege + mirror. Sección reescrita con tabla de responsabilidades |
| 1.1.0 | 2026-07-21 | CI: eliminado `mod_curseforge_token` (nunca en gradle.properties). Script: displayName usa `mod_name`. Workflow: añadido paso de subida con el script compartido |
| 1.0.0 | 2026-07-21 | Versión inicial: estructura, naming, tipografía, CI/CD, Graphify, fork attribution, temp/, README en inglés |

---

## Buenas prácticas

- **Un commit por cambio lógico**: no acumular múltiples cambios en un solo commit
- **Commit y push después de cada cambio funcional**: no esperar a tener todo terminado
- **Cualquier cambio en documentación debe committearse y pushearse inmediatamente**: los archivos de `docs/` deben reflejar siempre el estado actual del proyecto
- **Versionar antes de subir a CurseForge**: el tag debe apuntar al commit exacto del JAR que se sube
- **CHANGELOG.md siempre actualizado**: reflejar todos los cambios de cada versión
- **Siempre hacer `clean build` antes de generar el JAR final**: la caché de Gradle puede dejar artefactos obsoletos o corruptos que no se detectan en compilaciones incrementales; `clean` fuerza una compilación desde cero
- **Graphify**: mantener el knowledge graph actualizado tras cada release para que los asistentes de IA tengan contexto preciso del proyecto
- **Nomenclatura consistente**: no mezclar snake_case, PascalCase, camelCase o Title Case en contextos donde no corresponde
- **Sin archivos basura en el repositorio**: eliminar `nul`, `TEMPLATE_LICENSE.txt`, `errors.txt`, `compile_errors.txt`, `build_errors.txt` y otros artefactos temporales antes de commitear
- **README.md actualizado y en inglés**: el README debe reflejar siempre el estado actual del mod, con descripción, requisitos, instalación y enlaces. Debe estar escrito en **inglés** (en-US) por ser la puerta de entrada al proyecto desde GitHub
- **Sin residuos de mod original**: si el mod está basado en otro mod existente (fork/referencia), no debe quedar ningún rastro accidental del mod original. Revisar:
  - Nombres de paquetes (`com/oldauthor/oldmod/` → `com/skd/nuevomod/`)
  - Nombres de clases, métodos y variables
  - Referencias en `neoforge.mods.toml` (modid, description, credits)
  - Textos en lang/ (en_us.json, etc.)
  - Texturas, modelos y assets que no correspondan al mod actual
- **Atribución de fork**: si el mod es un fork de otro proyecto, debe indicarse explícitamente:
  - En `README.md`: "This mod is a fork of [Original Mod] by [Author]"
  - En `docs/curseforge/project_description.md`: misma atribución
  - En `neoforge.mods.toml` en el campo `credits` si aplica
  - La atribución no justifica mantener código muerto, clases renombradas mal o assets huérfanos

## Idioma

| Ámbito | Idioma |
|---|---|
| Código fuente, logs, nombres técnicos, commits | **Inglés** (en-US) — estándar de programación |
| README.md | **Inglés** (en-US) — puerta de entrada pública del proyecto (GitHub) |
| Documentación interna (docs/, CHANGELOG, WORKFLOW) | **Castellano** (es-ES) |
| CurseForge (descripción del proyecto, release notes) | **Inglés** (en-US) — plataforma global |

El código, los logs y los commits siguen el estándar internacional de programación en inglés. El README debe estar en inglés por ser la primera impresión del proyecto en GitHub. La documentación interna se mantiene en castellano por ser el idioma del equipo. CurseForge se publica en inglés para llegar a la mayor audiencia posible.
