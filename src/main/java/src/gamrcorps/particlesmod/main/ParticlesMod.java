package src.gamrcorps.particlesmod.main;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import src.gamrcorps.particlesmod.command.CommandEnergyAmountChanger;
import src.gamrcorps.particlesmod.proxy.CommonProxy;
import src.gamrcorps.particlesmod.recipe.RecipeRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Mod(modid = Constants.MOD_ID, version = Constants.VERSION, name = Constants.MOD_NAME)
public class ParticlesMod
{
    public static Comparator<ItemStack> comparator;
    public static final CreativeTabs particleCreativeTab = new CreativeTabs("particlesCreativeTab") {

        @Override public Item getTabIconItem() {
            return ItemRegistry.getItem("particleCreativeTabItem");
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }

        @Override
        public void displayAllRelevantItems(List<ItemStack> items) {
            super.displayAllRelevantItems(items);
            Collections.sort(items, comparator);
        }


    }.setBackgroundImageName("item_search.png");
    @Mod.Instance(Constants.MOD_ID)
    public static ParticlesMod instance;
    @SidedProxy(clientSide=Constants.PROXY_LOCATION_CLIENT, serverSide=Constants.PROXY_LOCATION_SERVER)
    public static CommonProxy proxy;
    public List<Item> registry = new ArrayList<Item>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
        BlockRegistry.registerBlocks();
        ItemRegistry.registerItems();
        MinecraftForge.EVENT_BUS.register(new src.gamrcorps.particlesmod.main.EventHandler());
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        comparator = Ordering.explicit(registry).onResultOf(new Function<ItemStack, Item>() {
            @Override
            public Item apply(ItemStack input) {
                return input.getItem();
            }
        });
        RecipeRegistry.registerRecipes();
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandEnergyAmountChanger());
    }
}
