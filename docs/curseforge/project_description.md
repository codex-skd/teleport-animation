<h2>About</h2>

<p><strong>Grand Teleport</strong> brings cinematic, GTA-style teleport transitions to Minecraft. Every time you use <code>/tp</code> or <code>/teleport</code>, instead of an instant cut, the camera smoothly zooms up into the sky, glides across the world, and descends back to your destination — creating a seamless and immersive traveling experience.</p>

<p>Whether you're using commands, Waystones teleports, JourneyMap waypoints, or cross‑dimension travel, the mod intercepts the teleport and plays a full animation before you arrive.</p>

<h2>Features</h2>

<ul>
<li><strong>Cinematic camera animation</strong> — the camera zooms out, travels above the terrain, and zooms back in on arrival</li>
<li><strong>3‑stage zoom heights</strong> — fully configurable pull and push stages for the zoom effect</li>
<li><strong>Per‑dimension settings</strong> — separate zoom heights for the Overworld, Nether, and End</li>
<li><strong>Custom sounds</strong> — distinctive sound effects for each animation step (camera zoom, step flash, travel wind)</li>
<li><strong>Step flash overlay</strong> — subtle screen flash at each zoom stage to enhance the transition feel</li>
<li><strong>Chunk‑by‑chunk mask fade</strong> — terrain fades out and in following the camera's direction of travel</li>
<li><strong>Player freeze</strong> — optionally freeze movement and look controls during the transition</li>
<li><strong>Full configuration</strong> — every parameter (heights, tick lengths, volumes, enabled/disabled) is adjustable via the in‑game config screen or a <code>.properties</code> file</li>
<li><strong>Post‑release camera override</strong> — smooth blend back to third‑person camera after the transition</li>
<li><strong>Cross‑dimension support</strong> — configurable, with a special fallback for dimension changes when chunks are still loading</li>
</ul>

<h2>Integration</h2>

<p>The mod is designed to work seamlessly with popular teleport‑related mods:</p>

<ul>
<li><strong>Waystones</strong> — delayed teleports with full animation (select waystone, inventory button, warp plates)</li>
<li><strong>JourneyMap</strong> — intercepts teleport requests and plays the animation</li>
<li><strong>Sodium</strong> — schedules terrain updates to reduce visual glitches during transitions</li>
<li><strong>Iris Shaders</strong> — automatically uses hard terrain cut when shaders are active</li>
<li><strong>Distant Horizons</strong> — adjusts near‑clip plane and terrain rendering during travel</li>
<li><strong>Bobby</strong> — integrates with Bobby's rendering for smoother chunk fade</li>
<li><strong>Voxy</strong> — prefers Voxy‑only terrain during the travel phase</li>
<li><strong>Leawind's Third Person</strong> — preempts and restores third‑person camera automatically</li>
</ul>

<h2>How to use</h2>

<ul>
<li><code>/ta on|off</code> — toggle the teleport effect on/off</li>
<li><code>/ta status</code> — check if the effect is enabled</li>
<li><code>/ta player_freeze on|off</code> — toggle player freeze during transitions</li>
<li><code>/ta</code> — show usage info</li>
</ul>

<p>The mod intercepts any <code>/tp</code>, <code>/teleport</code>, or <code>/execute ... run tp</code> command as well as teleport packets from Waystones and JourneyMap.</p>

<h2>Configuration</h2>

<p>The configuration file is located at <code>config/teleport_animation.properties</code> and can be edited manually or through the in‑game GUI.</p>

<h3>Settings include:</h3>

<table>
<tr><td>Zoom heights</td><td>3 configurable heights for zoom‑out and zoom‑in stages (per dimension)</td></tr>
<tr><td>Stage ticks</td><td>Duration in ticks for each of the 3 zoom‑out and zoom‑in stages</td></tr>
<tr><td>Glide settings</td><td>Camera body height, glide height, and glide tick duration</td></tr>
<tr><td>Sounds</td><td>Toggle custom sounds, set Minecraft/custom sound volume</td></tr>
<tr><td>Transitions</td><td>Enable/disable external teleport transitions and warp plate transitions</td></tr>
<tr><td>Layout</td><td>Position and size of the configuration GUI elements</td></tr>
<tr><td>Freeze</td><td>Player freeze toggle, local player model hide ticks</td></tr>
<tr><td>Cross‑dimension</td><td>Enable/disable cross‑dimension animation travel</td></tr>
</table>

<h2>Requirements</h2>

<ul>
<li>Minecraft: 1.20.1</li>
<li>NeoForge: 47.4.22+</li>
<li>Java 17+</li>
</ul>

<h2>Credits</h2>

<ul>
<li><strong>Codex</strong> — original Grand Teleport mod for Forge 1.20.1</li>
<li><strong>hookuru_</strong> — contributions to the original mod</li>
<li><strong>SKD</strong> — NeoForge port</li>
</ul>
