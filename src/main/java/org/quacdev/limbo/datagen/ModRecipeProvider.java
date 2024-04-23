package org.quacdev.limbo.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.quacdev.limbo.Limbo;
import org.quacdev.limbo.item.ModItems;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    //private static final List<ItemLike> ###_SMELTABLES = List.of(ModItems.###.get(),
                // ModBlocks.###.get());


    public ModRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        //oreSmelting(consumer, ###_SMELTABLES, RecipeCategory.MISC, ModItems.###.get(), #.##f, ###, ###);
        //oreBlasting(consumer, ###_SMELTABLES, RecipeCategory.MISC, ModItems.###.get(), #.##f, ###, ###);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LIMBO_EYE.get())
                .pattern("CAC")
                .pattern("ABA")
                .pattern("CAC")
                .define('A', ModItems.STABILIZED_LIMBO_FRAGMENT.get())
                .define('B', Items.ENDER_EYE)
                .define('C', Items.SCULK)
                .unlockedBy(getHasName(ModItems.STABILIZED_LIMBO_FRAGMENT.get()), has(ModItems.STABILIZED_LIMBO_FRAGMENT.get()))
                .save(consumer);

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Mod###.###.get(), #)
                //.requires(Mod###.###.get())
                //.unlockedBy(getHasName(Mod###.###.get()), has(Mod###.###.get()))
                //.save(consumer);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> finishedRecipeConsumer, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(finishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, ingredients, category, result, experience, cookingTime, group, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> finishedRecipeConsumer, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(finishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, ingredients, category, result, experience, cookingTime, group, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> cookingSerializer, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingtime, String group, String p_249236_) {
        Iterator var9 = ingredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(
                    Ingredient.of(new ItemLike[]{itemlike}),
                    category, result, experience, cookingtime, cookingSerializer)
                    .group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(finishedRecipeConsumer, Limbo.MODID + getItemName(result) + p_249236_ + "_" + getItemName(itemlike));
        }

    }
}
