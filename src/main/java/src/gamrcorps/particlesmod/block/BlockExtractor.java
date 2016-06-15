package src.gamrcorps.particlesmod.block;

import mezz.jei.JustEnoughItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import src.gamrcorps.particlesmod.main.ItemRegistry;
import src.gamrcorps.particlesmod.main.ParticlesMod;
import src.gamrcorps.particlesmod.util.WorldUtils;

/**
 * Created by matthewmccaskill on 6/9/16.
 */
public class BlockExtractor extends Block {
    private String name = "particleFluidExtractor";
    private int clicks = 0;

    public BlockExtractor () {
        super(Material.PISTON, MapColor.STONE);
        this.setUnlocalizedName(this.name);
        this.setRegistryName(this.name);
        this.setCreativeTab(ParticlesMod.particleCreativeTab);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.getBlockState(pos.up(1)).getBlock() == Blocks.WATER || world.getBlockState(pos.up(1)).getBlock() == Blocks.LAVA){
            if (++this.clicks == 20) { //10 clicks due to double call of this method.
                if (WorldUtils.getBlock(world, pos.up(1)) == Blocks.WATER) {
                    WorldUtils.spawnItem(world, new ItemStack(ItemRegistry.getItem("particleWater")), 0.5 + (double) pos.getX(), 0.5 + (double) pos.down().getY(), 0.5 + (double) pos.getZ());
                    world.setBlockToAir(pos.up(1));
                } else {
                    world.setBlockToAir(pos.up(1));
                    WorldUtils.spawnItem(world, new ItemStack(ItemRegistry.getItem("particleFire")), 0.5 + (double) pos.getX(), 0.5 + (double) pos.down().getY(), 0.5 + (double) pos.getZ());
                }
                world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, (double) pos.getX(), (double) pos.up().getY(), (double) pos.getZ(), 0.0D, 0.0D, 0.0D);
                return true;
            }
            return true;
        }
        this.clicks = 0;
        return false;
    }

    public String getName () {
        return this.name;
    }
}
