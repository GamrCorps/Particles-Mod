package src.gamrcorps.particlesmod.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import src.gamrcorps.particlesmod.main.BlockRegistry;
import src.gamrcorps.particlesmod.main.ItemRegistry;

/**
 * Created by matthewmccaskill on 6/8/16.
 */
public class RecipeRegistry {
    public static void registerRecipes(){
        registerShapedRecipes();
        registerShapelessRecipes();
    }

    public static void registerShapedRecipes(){
        registerShapedRecipe(new ItemStack(ItemRegistry.getItem("particleEnergy"), 3),
                "ESA", "SLS", "FSW",
                'E', ItemRegistry.getItem("particleEarth"),
                'A', ItemRegistry.getItem("particleAir"),
                'L', ItemRegistry.getItem("particleLight"),
                'F', ItemRegistry.getItem("particleFire"),
                'W', ItemRegistry.getItem("particleWater"),
                'S', ItemRegistry.getItem("bindingLevel2")
        );
        registerShapedRecipe(new ItemStack(ItemRegistry.getItem("bindingLevel1"), 4),
                "C G", " B ", "# R",
                'C', Items.CLAY_BALL,
                'G', Items.GUNPOWDER,
                'B', Items.BLAZE_POWDER,
                '#', Items.GLOWSTONE_DUST,
                'R', Items.REDSTONE
        );
        registerShapedRecipe(new ItemStack(ItemRegistry.getItem("bindingLevel2"), 4),
                "#C#", "G#P", "#R#",
                '#', ItemRegistry.getItem("bindingLevel1"),
                'C', Items.CLAY_BALL,
                'G', Items.GLOWSTONE_DUST,
                'P', Items.GUNPOWDER,
                'R', Items.REDSTONE
        );
        registerShapedRecipe(new ItemStack(ItemRegistry.getItem("bindingLevel3"), 4),
                "#C#", "G#P", "#R#",
                '#', ItemRegistry.getItem("bindingLevel2"),
                'C', Items.CLAY_BALL,
                'G', Items.GLOWSTONE_DUST,
                'P', Items.GUNPOWDER,
                'R', Items.REDSTONE
        );
        registerShapedRecipe(new ItemStack(ItemRegistry.getItem("bindingLevel4"), 4),
                "#C#", "G#P", "#R#",
                '#', ItemRegistry.getItem("bindingLevel3"),
                'C', Items.CLAY_BALL,
                'G', Items.GLOWSTONE_DUST,
                'P', Items.GUNPOWDER,
                'R', Items.REDSTONE
        );
        registerShapedRecipe(new ItemStack(ItemRegistry.getItem("bindingLevel5"), 4),
                "#C#", "G#P", "#R#",
                '#', ItemRegistry.getItem("bindingLevel4"),
                'C', Items.CLAY_BALL,
                'G', Items.GLOWSTONE_DUST,
                'P', Items.GUNPOWDER,
                'R', Items.REDSTONE
        );
        registerShapedRecipe(new ItemStack(BlockRegistry.getBlock("solarCollector"), 1),
                "IDI", "ILI", "III",
                'I', Blocks.IRON_BLOCK,
                'D', Blocks.DAYLIGHT_DETECTOR,
                'L', Blocks.LAPIS_BLOCK
        );
        registerShapedRecipe(new ItemStack(BlockRegistry.getBlock("particleFluidExtractor"), 1),
                "IBI", "I I", "IDI",
                'I', Blocks.IRON_BLOCK,
                'B', Items.BUCKET,
                'D', Blocks.DISPENSER
        );
    }

    public static void registerShapelessRecipes(){

    }

    public static void registerShapedRecipe(ItemStack output, Object...components){
        GameRegistry.addRecipe(output, components);
    }
}
