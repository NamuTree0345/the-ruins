package kr.wonlab.ruins.recipes;

import kr.wonlab.ruins.Ruins;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class RuinsRecipe extends AbstractCookingRecipe {
    //Same for the BlockEntity, we don't need some of the parameters in the constructor since we already now the type
    public RuinsRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(Ruins.RUINS_RECIPE_TYPE, id, group, input, output, experience, cookTime);
    }

    @Override
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(Ruins.PARTICLE_ACCELERATOR_ITEM);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        //The Serializer will be created later
        return Ruins.RUINS_RECIPE_SERIALIZER;
    }
}
