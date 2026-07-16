/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Camera
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package dev.codex.gtaliketeleport.mixin;

import net.minecraft.client.Camera;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={Camera.class})
public interface CameraAccessor {
    @Invoker(value="setPosition")
    public void gtalikeTeleport$setPosition(Vec3 var1);

    @Invoker(value="setRotation")
    public void gtalikeTeleport$setRotation(float var1, float var2);
}

