package kr.wonlab.ruins.toolMaterial;

import kr.wonlab.ruins.Ruins;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class FlameToolMaterial implements ToolMaterial {

    public static final FlameToolMaterial INSTANCE = new FlameToolMaterial();

    @Override
    public int getDurability() {
        return 800;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 12;
    }

    @Override
    public float getAttackDamage() {
        return 30;
    }

    @Override
    public int getMiningLevel() {
        return 5;
    }

    @Override
    public int getEnchantability() {
        return 50;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Ruins.FLAME_INGOT);
    }
}
