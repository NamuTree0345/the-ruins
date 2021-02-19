package kr.wonlab.ruins.blocks;

import kr.wonlab.ruins.Ruins;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;

public class PollutedFurnaceScreenHandler extends AbstractFurnaceScreenHandler {
    public PollutedFurnaceScreenHandler(int i, PlayerInventory playerInventory) {
        super(Ruins.POLLUTED_FURNACE_SCREEN_HANDLER, Ruins.RUINS_RECIPE_TYPE, RecipeBookCategory.FURNACE, i, playerInventory);
    }

    public PollutedFurnaceScreenHandler(int i, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Ruins.POLLUTED_FURNACE_SCREEN_HANDLER, Ruins.RUINS_RECIPE_TYPE, RecipeBookCategory.FURNACE, i, playerInventory, inventory, propertyDelegate);
    }
}
