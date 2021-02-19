package kr.wonlab.ruins.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class IngameScreenMixin extends DrawableHelper {

    Identifier POLLUTED_WORLD_DEFEND_TEXTURE = new Identifier("textures/item/barrier.png");

    @Inject(method = "renderStatusBars", at = @At("RETURN"))
    public void renderStatusBars(MatrixStack matrices, CallbackInfo callbackInfo) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity playerEntity = client.player;
        if(playerEntity != null) {
            System.out.println("Rendering");
            client.getTextureManager().bindTexture(POLLUTED_WORLD_DEFEND_TEXTURE);
            this.drawTexture(matrices, 1, 1, 1, 1, 8, 8);
        }
    }

}
