# Flujo de trabajo — Grand Teleport (NeoForge)

> Este archivo pertenece al proyecto **Grand Teleport**. Cada proyecto tiene su propio `WORKFLOW.md`.
> No es un archivo central ni template compartido. Los cambios aquí solo afectan a este proyecto.

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
│   │   │   ├── META-INF/
│   │   │   │   └── accesstransformer.cfg
│   │   │   ├── <mod_id>.mixins.json
│   │   │   └── <mod_id>.png           # Logo del mod
│   │   └── templates/
│   │       └── META-INF/
│   │           └── neoforge.mods.toml  # Template con placeholders ${...}
│   ├── main/java/<package>/...         # Código fuente
├── docs/
│   ├── WORKFLOW.md                    # Este documento
│   └── curseforge/                    # Documentación para publicación en CurseForge
│       ├── project_description.md      # Descripción del proyecto
│       └── versions/                   # Release notes por versión
│           ├── 0.0.0-beta.1.md
│           └── ...
├── CHANGELOG.md
└── README.md
```

### Archivos de CurseForge

| Archivo | Propósito |
|---------|-----------|
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

Usamos HTML para la **descripción general del proyecto** (`project_description.md`), donde el control visual es más importante. Para las **release notes** (`versions/<version>.md`) usamos Markdown con emojis, que es más ligero y rápido de escribir.

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
|----------|-----|
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

#### Formato del changelog

El changelog se envía en formato **HTML**, no Markdown. Aunque CurseForge acepta ambos, el HTML se renderiza correctamente en el editor WYSIWYG sin escapes ni caracteres rotos.

| Campo | Valor |
|-------|-------|
| `changelogType` | `html` |
| `changelog` | Código HTML con `<h2>`, `<h3>`, `<ul>/<li>`, `<p>`, `<strong>`, `<code>`, `<blockquote>` |

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
|----------|-----|
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
|------|-----------|
| `main` | Vacía. Solo contiene un commit inicial. No se usa para desarrollo |
| `minecraft/<mc-version>/neoforge-<neo-version>/production` | Rama de trabajo para una versión específica de Minecraft/NeoForge |

### Ejemplos

| Rama | Versión |
|------|---------|
| `minecraft/1.21.1/neoforge-1.21.1.78/production` | Minecraft 1.21.1, NeoForge 1.21.1.78 |
| `minecraft/1.21.1/neoforge-21.1.141/production` | Minecraft 1.21.1, NeoForge 21.1.141 |

---

## Versionado

### Esquema

| Estado | Formato | Ejemplos |
|--------|---------|----------|
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
|---------|-------------|
| `teleport_animation-1.21.1-neoforge-0.0.0-beta.2.jar` | NeoForge 1.21.1, beta 2 |
| `teleport_animation-1.21.1-neoforge-1.0.0.jar` | NeoForge 1.21.1, release 1.0.0 |
| `teleport_animation-1.21.1-neoforge-0.0.0-beta.14.jar` | NeoForge 1.21.1, beta 14 |

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
|------|-----|
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
|--------|---------|---------|
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

## Flujo completo (paso a paso)

### 1. Desarrollo

```bash
# Situarse en la rama de la versión correspondiente
git checkout minecraft/1.21.1/neoforge-1.21.1.78/production

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

### 2. Preparar versión para CurseForge

```bash
# 1. Compilar con clean para evitar caché corrupta
./gradlew.bat clean build

# 2. Actualizar versión en gradle.properties
#    mod_version=0.0.0-beta.3

# 3. Crear release notes
#    docs/curseforge/versions/0.0.0-beta.3.md

# 4. Actualizar CHANGELOG.md

# 5. Commit del bump de versión
git add -A
git commit -m "chore: bump version to 0.0.0-beta.3"

# 6. Tag para CurseForge
git tag -a curseforge-beta.3 -m "v0.0.0-beta.3: Bugfix release"
git push origin curseforge-beta.3

# 7. Subir JAR a CurseForge manualmente
#    El JAR está en build/libs/<mod_id>-<minecraft_version>-<framework>-<version>.jar
```

### 3. Release estable

```bash
# gradle.properties → mod_version=1.0.0
git commit -m "chore: bump version to 1.0.0"
git tag -a curseforge-1.0.0 -m "v1.0.0: First stable release"
git push origin curseforge-1.0.0
```

---

## Buenas prácticas

- **Un commit por cambio lógico**: no acumular múltiples cambios en un solo commit
- **Commit y push después de cada cambio funcional**: no esperar a tener todo terminado
- **Cualquier cambio en documentación debe committearse y pushearse inmediatamente**: los archivos de `docs/` deben reflejar siempre el estado actual del proyecto
- **Versionar antes de subir a CurseForge**: el tag debe apuntar al commit exacto del JAR que se sube
- **CHANGELOG.md siempre actualizado**: reflejar todos los cambios de cada versión
- **Siempre hacer `clean build` antes de generar el JAR final**: la caché de Gradle puede dejar artefactos obsoletos o corruptos que no se detectan en compilaciones incrementales; `clean` fuerza una compilación desde cero

## Idioma

| Ámbito | Idioma |
|--------|--------|
| Código fuente, logs, nombres técnicos, commits | **Inglés** (en-US) — estándar de programación |
| Documentación interna, GitLab (README, CHANGELOG) | **Castellano** (es-ES) |
| CurseForge (descripción del proyecto, release notes) | **Inglés** (en-US) — plataforma global |

El código, los logs y los commits siguen el estándar internacional de programación en inglés. La documentación interna y el repositorio se mantienen en castellano por ser el idioma del equipo. CurseForge se publica en inglés para llegar a la mayor audiencia posible.
