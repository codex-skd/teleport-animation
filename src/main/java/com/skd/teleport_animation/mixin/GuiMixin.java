package com.skd.teleport_animation.mixin;

import com.skd.teleport_animation.TeleportStepEffectRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
abstract class GuiMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void ta$renderEffectAfterHud(GuiGraphics graphics, float partialTick, CallbackInfo ci) {
        TeleportStepEffectRenderer.render(graphics, partialTick);
    }
}