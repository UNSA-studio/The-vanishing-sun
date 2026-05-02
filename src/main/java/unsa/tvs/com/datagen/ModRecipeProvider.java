package unsa.tvs.com.datagen;

import unsa.tvs.com.TheVanishingSun;
import unsa.tvs.com.block.ModBlocks;
import unsa.tvs.com.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.RADAR.get())
                .pattern("IRI")
                .pattern("IGI")
                .pattern("IRI")
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('G', Items.GLASS)
                .unlockedBy("impossible", new Criterion<>(CriteriaTriggers.IMPOSSIBLE, new ImpossibleTrigger.TriggerInstance()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.LOCATOR.get())
                .pattern(" G ")
                .pattern("GEG")
                .pattern("GRG")
                .define('G', Items.GOLD_INGOT)
                .define('E', Items.ENDER_PEARL)
                .define('R', Items.REDSTONE)
                .unlockedBy("impossible", new Criterion<>(CriteriaTriggers.IMPOSSIBLE, new ImpossibleTrigger.TriggerInstance()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BLUEPRINT_PARSING_STATION.get())
                .pattern("III")
                .pattern("IEI")
                .pattern("IRI")
                .define('I', Items.IRON_INGOT)
                .define('E', Items.ENCHANTING_TABLE)
                .define('R', Items.REDSTONE_BLOCK)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BIOLOGICAL_CHAMBER.get())
                .pattern("IGI")
                .pattern("GPG")
                .pattern("IGI")
                .define('I', Items.IRON_INGOT)
                .define('G', Items.GLASS)
                .define('P', Items.PISTON)
                .unlockedBy("impossible", new Criterion<>(CriteriaTriggers.IMPOSSIBLE, new ImpossibleTrigger.TriggerInstance()))
                .save(recipeOutput);
    }
}
