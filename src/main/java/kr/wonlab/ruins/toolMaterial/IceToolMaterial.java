package kr.wonlab.ruins.toolMaterial;

import kr.wonlab.ruins.Ruins;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class IceToolMaterial implements ToolMaterial {

    public static final IceToolMaterial INSTANCE = new IceToolMaterial();

    @Override
    public int getDurability() {
        return 800;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 6;
    }

    @Override
    public float getAttackDamage() {
        return 25;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 45;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Ruins.FLAME_INGOT);
    }

}
