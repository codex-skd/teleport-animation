package com.skd.teleport_animation.mixin;

import com.mojang.logging.LogUtils;
import com.skd.teleport_animation.TeleportTransitionController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRendererMixin {
    @Unique
    private static final Logger LOGGER = LogUtils.getLogger();

    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true, require = 0, remap = false)
    private <E extends Entity> void teleportAnimation$hideLocalPlayerDuringBodyCamera(E entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && entity.getId() == player.getId() && TeleportTransitionController.shouldHideLocalPlayerModel()) {
            cir.setReturnValue(false);
        }
    }
}
