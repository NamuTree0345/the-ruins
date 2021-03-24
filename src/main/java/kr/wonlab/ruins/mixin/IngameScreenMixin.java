package kr.wonlab.ruins.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class IngameScreenMixin extends DrawableHelper {

    Identifier POLLUTED_WORLD_DEFEND_TEXTURE = new Identifier("ruins", "textures/gui/polluted_durability.png");
    private static final Identifier WIDGETS_TEXTURE = new Identifier("textures/gui/widgets.png");

    @Inject(method = "renderStatusBars", at = @At("RETURN"))
    public void renderStatusBars(MatrixStack matrices, CallbackInfo callbackInfo) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity playerEntity = client.player;
        int scaledWidth = client.getWindow().getScaledWidth();
        int scaledHeight = client.getWindow().getScaledHeight();
        if(playerEntity != null) {
            //client.getTextureManager().bindTexture(POLLUTED_WORLD_DEFEND_TEXTURE);
            client.getTextureManager().bindTexture(POLLUTED_WORLD_DEFEND_TEXTURE);
            int f = 5;
            int a = 10;
            for(int i = 0; i < f; i++) {
                if(i == 0) {
                    this.drawTexture(matrices, i, 1, 1, 1, 32, 32);
                } else {
                    this.drawTexture(matrices, i + 25, 1, 1, 1, 32, 32);
                }
            }
        }
    }

}
