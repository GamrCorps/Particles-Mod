package src.gamrcorps.particlesmod.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import src.gamrcorps.particlesmod.item.ItemEnergyAmountViewer;
import src.gamrcorps.particlesmod.item.ItemWindGenerator;
import src.gamrcorps.particlesmod.item.binding.ItemBindingAgentBase;
import src.gamrcorps.particlesmod.item.particle.ItemParticleBase;
import src.gamrcorps.particlesmod.item.upgrade.ItemUpgradeBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by matthewmccaskill on 6/8/16.
 */
public class ItemRegistry {
    private static HashMap<String, Item> items = new HashMap<String, Item>();
    private static List<Item> texturedItemList = new ArrayList<Item>();

    public static void registerItems() {
        registerItem(new ItemParticleBase("CreativeTabItem").setCreativeTab(null), false);

        registerItem(new ItemParticleBase("Air"));
        registerItem(new ItemParticleBase("Water"));
        registerItem(new ItemParticleBase("Fire"));
        registerItem(new ItemParticleBase("Earth"));
        registerItem(new ItemParticleBase("Light"));
        registerItem(new ItemParticleBase("Energy"));

        registerItem(new ItemBindingAgentBase("Level1"));
        registerItem(new ItemBindingAgentBase("Level2"));
        registerItem(new ItemBindingAgentBase("Level3"));
        registerItem(new ItemBindingAgentBase("Level4"));
        registerItem(new ItemBindingAgentBase("Level5"));

        registerItem(new ItemWindGenerator());
        registerItem(new ItemEnergyAmountViewer());

        registerItem(new ItemUpgradeBase("Ore"));
    }

    public static void registerItemTextures() {
        for (Item item : texturedItemList) {
            registerItemTexture(item);
        }
    }

    public static void registerItem(Item item) {
        registerItem(item, true);
    }

    public static void registerItem(Item item, boolean inCreativeTab) {
        GameRegistry.register(item);
        items.put(item.getUnlocalizedName().substring(5), item);
        texturedItemList.add(item);
        if (inCreativeTab)
            ParticlesMod.instance.registry.add(item);
    }

    public static void registerItemTexture(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Constants.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

    public static Item getItem(String unlocalizedName) {
        return items.get(unlocalizedName);
    }
}
