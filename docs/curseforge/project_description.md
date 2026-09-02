<h1 align="center">&#127744; Teleport Animation</h1>

<p align="center"><strong>Cinematic, GTA-style teleport transitions for Minecraft.</strong></p>

<p align="center">
<img src="https://img.shields.io/badge/loader-Forge%20%2F%20NeoForge-orange?style=plastic&logo=curseforge" alt="Forge / NeoForge">
<img src="https://img.shields.io/badge/minecraft-1.20.1%20%7C%201.21.1%20%7C%2026.1.2%20%7C%2026.2-blue?style=plastic" alt="Minecraft 1.20.1, 1.21.1, 26.1.2, 26.2">
<img src="https://img.shields.io/badge/side-client%20%2B%20server-brightgreen?style=plastic" alt="Client and Server">
<img src="https://img.shields.io/badge/license-Custom%20(MIT%20code)-lightgrey?style=plastic" alt="Custom license, MIT code">
</p>

<br>

---

<br>

<h2>&#10024; Overview</h2>

<table>
<tr>
<td width="65%">
<p>Teleport Animation replaces the instant cut of a teleport with a cinematic camera move: the camera pulls up off the player through several zoom stages, glides across the world toward the destination, and descends back down as you arrive. It hooks <code>/tp</code> and <code>/teleport</code>, and integrates with Waystones and JourneyMap so their teleports get the same treatment. Terrain fades chunk-by-chunk along the camera's path, custom sounds mark each stage, and every timing / height / volume is configurable.</p>

<p>A fork of <a href="https://www.curseforge.com/minecraft/mc-mods/grand-teleport-gtp"><strong>Grand Teleport</strong></a> by <em>Codex</em> (Forge 1.20.1), with contributions to the original by <em>hookuru_</em>. Ported and reworked for NeoForge / current Forge by <strong>Stalking Dragons</strong>. The original Grand Teleport code is MIT licensed. Not affiliated with or endorsed by the original authors.</p>
</td>
<td width="35%" align="center">
<a href="https://codex.skdragons.com/" target="_blank"><img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/logo_codex_stalking_dragons.png" alt="Codex Stalking Dragons" width="160"></a>
</td>
</tr>
</table>

<br>

<h2>&#127919; Features</h2>

<h3>&#127916; Cinematic Camera Animation</h3>
<p>The camera zooms out, travels above the terrain, and zooms back in on arrival &mdash; fully animated and configurable.</p>

<h3>&#128208; 3-Stage Zoom Heights</h3>
<p>Configurable pull and push stages for the zoom, with per-dimension settings for the Overworld, Nether and End.</p>

<h3>&#128266; Custom Sounds</h3>
<p>Distinctive sound effects for each animation step: camera zoom, step flash, and travel wind.</p>

<h3>&#127912; Step Flash Overlay</h3>
<p>A subtle screen flash at each zoom stage to enhance the transition.</p>

<h3>&#129482;&#65039; Chunk-by-Chunk Mask Fade</h3>
<p>Terrain fades out and in following the camera's direction of travel, avoiding abrupt visual cuts. Nearby destinations use a smooth slide; distant ones mask the mid-flight chunk load with a short fade.</p>

<h3>&#9208;&#65039; Player Freeze</h3>
<p>Optionally freeze movement and look controls during the transition.</p>

<h3>&#128268; Broad Mod Compatibility</h3>
<p>Integrates with Waystones and JourneyMap teleports; plays nicely with Sodium, Iris, Distant Horizons, Bobby, Voxy and Leawind's Third Person.</p>

<h3>&#127760; Cross-Dimension Travel</h3>
<p>Full animation support for dimension changes, with a fallback for when destination chunks are still loading.</p>

<h3>&#9881;&#65039; Fully Configurable</h3>
<p>Every parameter &mdash; heights, tick lengths, volumes, toggles &mdash; is adjustable from the in-game config screen or the config file.</p>

<br>

<h2>&#129521; Mod Structure</h2>

<table>
<tr><th align="left">Area</th><th align="left">What it provides</th></tr>
<tr><td>transition controller</td><td>Drives the pull / travel / push phases, camera frames, arrival detection and chunk handoff.</td></tr>
<tr><td>step effects</td><td>The step flash, travel blackout and HUD fade overlays.</td></tr>
<tr><td>command hooks</td><td>Intercepts <code>/tp</code>, <code>/teleport</code> and <code>/execute&hellip;run tp</code>, plus the <code>/ta</code> command tree.</td></tr>
<tr><td>Waystones / JourneyMap bridges</td><td>Reflection bridges that route those mods' teleports through the animation.</td></tr>
<tr><td>compat</td><td>Sodium / Iris / Distant Horizons / Bobby / Voxy / Leawind terrain-refresh and camera hooks.</td></tr>
<tr><td>config</td><td>The persistent config and the in-game config screen.</td></tr>
</table>

<br>

<h2>&#128203; Requirements</h2>

<table>
<tr><td><strong>Minecraft / loader / Java</strong></td><td>see <em>Available Versions</em> below</td></tr>
<tr><td><strong>Dependencies</strong></td><td>None required. Optional integration with Waystones and JourneyMap.</td></tr>
<tr><td><strong>Side</strong></td><td>Client and Server</td></tr>
</table>

<br>

<h2>&#128230; Available Versions</h2>

<table>
<tr><th align="left">Minecraft</th><th align="left">Loader</th><th align="left">Java</th><th align="left">Latest build</th><th align="left">Status</th></tr>
<tr><td>1.20.1</td><td>Forge 47.1.99+</td><td>17</td><td><code>0.0.0-beta.2</code></td><td>Beta</td></tr>
<tr><td>1.21.1</td><td>NeoForge 21.1.235+</td><td>21</td><td><code>1.0.4</code></td><td>Stable &mdash; behind the 26.2 fix line (see below)</td></tr>
<tr><td>26.1.2</td><td>NeoForge 26.1.2.78+</td><td>21</td><td><code>1.0.3</code></td><td>Stable</td></tr>
<tr><td>26.2</td><td>NeoForge 26.2.0.57+</td><td>25</td><td><code>1.2.0</code></td><td>Stable &mdash; most up to date</td></tr>
</table>

<p><em>All versions share this CurseForge project. Pick the file that matches your Minecraft version. The 26.2 line has received camera / arrival-position / crash fixes (1.1.x&ndash;1.2.0) that the 1.21.1 line has not yet been synced up to.</em></p>

<br>

<h2>&#127918; How to Use</h2>

<ol>
<li>Use <code>/ta on|off</code> to toggle the teleport effect.</li>
<li>Use <code>/ta status</code> to check whether it is enabled.</li>
<li>Use <code>/ta player_freeze on|off</code> to toggle player freeze during transitions.</li>
<li>Use <code>/ta</code> to open the configuration screen.</li>
</ol>

<p>The mod intercepts <code>/tp</code>, <code>/teleport</code> and <code>/execute&hellip;run tp</code>, plus teleport packets from Waystones and JourneyMap.</p>

<br>

---

<br>

<h2>&#128591; Credits &amp; License</h2>

<p>Teleport Animation is a fork of <a href="https://www.curseforge.com/minecraft/mc-mods/grand-teleport-gtp">Grand Teleport</a> by <strong>Codex</strong> (Forge 1.20.1), with contributions to the original by <strong>hookuru_</strong>. Ported and reworked for NeoForge / current Forge by <strong>Stalking Dragons</strong>.</p>

<p>The <strong>code</strong> is under the <strong>MIT</strong> license (see the <code>LICENSE</code> file). The <strong>bundled sound effects</strong> are third-party audio carried over from Grand Teleport &mdash; they are not original to this project, not covered by the MIT grant, and are included only for non-commercial in-game use as part of this mod. Do not extract or redistribute the sound files separately.</p>

<br>
<br>

<p align="center">
  <a href="https://codex.skdragons.com/" target="_blank">
    <img src="https://node-files.skdragons.com/uploads/MINECRAFT/Codex/logo_codex_stalking_dragons.png" alt="Codex Stalking Dragons" width="200">
  </a>
  <br>
  <a href="https://codex.skdragons.com/">https://codex.skdragons.com/</a>
  <br>
  <em>Codex Stalking Dragons &mdash; Minecraft Modding</em>
</p>
