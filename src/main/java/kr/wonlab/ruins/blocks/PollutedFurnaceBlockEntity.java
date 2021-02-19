package kr.wonlab.ruins.blocks;

import kr.wonlab.ruins.Ruins;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class PollutedFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    //Since we already now the BlockEntityType and RecipeType we don't need them in the constructor's parameters
    public PollutedFurnaceBlockEntity() {
        super(Ruins.POLLUTED_FURNACE_BLOCK_ENTITY, Ruins.RUINS_RECIPE_TYPE);
    }

    @Override
    public Text getContainerName() {
        //you should use a translation key instead but this is easier
        return new TranslatableText("text.polluted_furnace_block");
    }

    @Override
    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new PollutedFurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}


