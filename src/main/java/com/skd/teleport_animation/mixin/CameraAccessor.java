package com.skd.teleport_animation.mixin;

import net.minecraft.client.Camera;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Camera.class)
public interface CameraAccessor {
    @Invoker("setPosition")
    void teleportAnimation$setPosition(Vec3 pos);

    @Invoker("setRotation")
    void teleportAnimation$setRotation(float yaw, float pitch);
}
