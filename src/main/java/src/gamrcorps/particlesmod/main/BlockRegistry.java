package src.gamrcorps.particlesmod.main;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import src.gamrcorps.particlesmod.block.*;
import src.gamrcorps.particlesmod.tileentity.TileEntitySolarCollector;
import src.gamrcorps.particlesmod.tileentity.TileEntityUpgradeCatalyst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by matthewmccaskill on 6/8/16.
 */
public class BlockRegistry {
    private static HashMap<String, Block> blocks = new HashMap<String, Block>();
    private static HashMap<String, ItemBlock> itemBlocks = new HashMap<String, ItemBlock>();
    private static List<Block> texturedBlockList = new ArrayList<Block>();

    public static void registerBlocks() {
        registerBlock(new BlockExtractor());
        registerTEBlock(new BlockSolarCollector(), TileEntitySolarCollector.class);
        registerBlock(new BlockSifter());
        registerBlock(new BlockEnergyNetworkLoader());
        registerTEBlock(new BlockUpgradeCatalyst(), TileEntityUpgradeCatalyst.class);
    }


    public static void registerBlockTextures() {
        for (Block block : texturedBlockList) {
            registerBlockTexture(block);
        }
    }

    public static void registerBlock(Block block) {
        registerBlock(block, true);
    }

    public static void registerBlock(Block block, boolean inCreativeTab) {
        GameRegistry.register(block);
        ItemBlock ib = (ItemBlock) new ItemBlock(block).setRegistryName(block.getUnlocalizedName().substring(5));
        GameRegistry.register(ib);
        blocks.put(block.getUnlocalizedName().substring(5), block);
        itemBlocks.put(block.getUnlocalizedName().substring(5), ib);
        texturedBlockList.add(block);
        if (inCreativeTab)
            ParticlesMod.instance.registry.add(ib);
    }

    public static void registerTEBlock(Block block, Class<? extends TileEntity> tileEntityClass) {
        registerTEBlock(block, tileEntityClass, true);
    }

    public static void registerTEBlock(Block block, Class<? extends TileEntity> tileEntityClass, boolean inCreativeTab) {
        GameRegistry.register(block);
        ItemBlock ib = (ItemBlock) new ItemBlock(block).setRegistryName(block.getUnlocalizedName().substring(5));
        GameRegistry.register(ib);
        GameRegistry.registerTileEntity(tileEntityClass, "t" + tileEntityClass.getName().substring(1));
        blocks.put(block.getUnlocalizedName().substring(5), block);
        itemBlocks.put(block.getUnlocalizedName().substring(5), ib);
        texturedBlockList.add(block);
        if (inCreativeTab)
            ParticlesMod.instance.registry.add(ib);
    }

    public static void registerBlockTexture(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Constants.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }


    public static Block getBlock(String unlocalizedName) {
        return blocks.get(unlocalizedName);
    }

    public static ItemBlock getItemBlock(String unlocalizedName) {
        return itemBlocks.get(unlocalizedName);
    }
}
