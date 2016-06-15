package src.gamrcorps.particlesmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import src.gamrcorps.particlesmod.main.BlockRegistry;
import src.gamrcorps.particlesmod.main.ItemRegistry;
import src.gamrcorps.particlesmod.main.ParticlesMod;
import src.gamrcorps.particlesmod.tileentity.TileEntityUpgradeCatalyst;
import src.gamrcorps.particlesmod.util.WorldUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by matthewmccaskill on 6/11/16.
 */
public class BlockSifter extends Block {
    private String name = "sifter";
    public static List<Block> oreDrops = new ArrayList<Block>();

    public BlockSifter() {
        super(Material.PISTON, MapColor.WOOD);
        this.setUnlocalizedName(this.name);
        this.setRegistryName(this.name);
        this.setCreativeTab(ParticlesMod.particleCreativeTab);
        for (int i = 0; i < 5; i++) {
            oreDrops.add(Blocks.GRAVEL);
            oreDrops.add(Blocks.GRAVEL);
            oreDrops.add(Blocks.GRAVEL);
            oreDrops.add(Blocks.GRAVEL);
            oreDrops.add(Blocks.GRAVEL);
            oreDrops.add(Blocks.IRON_ORE);
            oreDrops.add(Blocks.IRON_ORE);
            oreDrops.add(Blocks.IRON_ORE);
            oreDrops.add(Blocks.IRON_ORE);
            oreDrops.add(Blocks.COAL_ORE);
            oreDrops.add(Blocks.COAL_ORE);
            oreDrops.add(Blocks.COAL_ORE);
            oreDrops.add(Blocks.GOLD_ORE);
            oreDrops.add(Blocks.GOLD_ORE);
            oreDrops.add(Blocks.LAPIS_ORE);
        }
        oreDrops.add(Blocks.DIAMOND_ORE);
        oreDrops.add(Blocks.EMERALD_ORE);
    }

    public String getName() {
        return name;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote && player.getHeldItemMainhand() != null) {
            if (player.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.DIRT) || player.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.SAND)) {
                if (--player.getHeldItemMainhand().stackSize == 0) player.setHeldItem(EnumHand.MAIN_HAND, null);
                if (world.rand.nextDouble() < 0.05) {
                    WorldUtils.spawnItem(world, new ItemStack(ItemRegistry.getItem("particleEarth"), 1), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                }
                return true;
            } else if (WorldUtils.getBlock(world, pos.down()) == BlockRegistry.getBlock("upgradeHolder")) {
                TileEntityUpgradeCatalyst upgradeHolder = (TileEntityUpgradeCatalyst) world.getTileEntity(pos.down());
                if (upgradeHolder != null) {
                    for (int i = 0; i < upgradeHolder.getSizeInventory(); i++) {
                        if (upgradeHolder.getStackInSlot(i) != null && upgradeHolder.getStackInSlot(i).getItem() == ItemRegistry.getItem("upgradeOre")) {
                            if (player.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.GRAVEL)) {
                                if (--player.getHeldItemMainhand().stackSize == 0) player.setHeldItem(EnumHand.MAIN_HAND, null);
                                if (world.rand.nextDouble() < 0.04) {
                                    Collections.shuffle(oreDrops, world.rand);
                                    WorldUtils.spawnItem(world, new ItemStack(oreDrops.get(3), 1), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                                }
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
