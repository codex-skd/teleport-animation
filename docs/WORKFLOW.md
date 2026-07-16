# Flujo de trabajo — Mods Minecraft (NeoForge)

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

### Archivos obligatorios de CurseForge

| Archivo | Propósito |
|---------|-----------|
| `docs/curseforge/project_description.md` | Descripción completa del proyecto (qué hace, características, requisitos) |
| `docs/curseforge/versions/<version>.md` | Release notes de cada versión que se sube a CurseForge. Solo se agrega cuando se va a publicar esa versión |

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
| `minecraft/26.1.2/neoforge-26.1.2.78/production` | Minecraft 26.1.2, NeoForge 26.1.2.78 |
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
| `teleport_animation-26.1.2-neoforge-0.0.0-beta.14.jar` | NeoForge 26.1.2, beta 14 |

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
| Beta | `<mc-version>-neoforge-beta.X` | `26.1.2-neoforge-beta.15` |
| Release | `<mc-version>-neoforge-X.Y.Z` | `26.1.2-neoforge-1.0.0` |

El prefijo `<mc-version>-neoforge` se adapta según la versión de Minecraft y el framework de la rama actual.

### Ejemplos

```bash
# Beta
git tag -a 26.1.2-neoforge-beta.15 -m "v0.0.0-beta.15: Updated WORKFLOW.md"
git push origin 26.1.2-neoforge-beta.15

# Release estable
git tag -a 26.1.2-neoforge-1.0.0 -m "v1.0.0: First stable release"
git push origin 26.1.2-neoforge-1.0.0
```

---

## Flujo completo (paso a paso)

### 1. Desarrollo

```bash
# Situarse en la rama de la versión correspondiente
git checkout minecraft/26.1.2/neoforge-26.1.2.78/production

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
- **Versionar antes de subir a CurseForge**: el tag debe apuntar al commit exacto del JAR que se sube
- **CHANGELOG.md siempre actualizado**: reflejar todos los cambios de cada versión
- **Siempre hacer `clean build` antes de generar el JAR final**: la caché de Gradle puede dejar artefactos obsoletos o corruptos que no se detectan en compilaciones incrementales; `clean` fuerza una compilación desde cero

## Idioma

| Ámbito | Idioma |
|--------|--------|
| Código fuente, logs, nombres técnicos | **Inglés** (en-US) — estándar de programación |
| Commits, documentación interna, GitLab (README, CHANGELOG) | **Castellano** (es-ES) |
| CurseForge (descripción del proyecto, release notes) | **Inglés** (en-US) — plataforma global |

El código y los logs siguen el estándar internacional de programación en inglés. La documentación interna y el repositorio se mantienen en castellano por ser el idioma del equipo. CurseForge se publica en inglés para llegar a la mayor audiencia posible.
