package src.gamrcorps.particlesmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import src.gamrcorps.particlesmod.main.Constants;
import src.gamrcorps.particlesmod.main.ItemRegistry;
import src.gamrcorps.particlesmod.main.ParticlesMod;
import src.gamrcorps.particlesmod.util.PlayerNBTUtils;

/**
 * Created by matthewmccaskill on 6/12/16.
 */
public class BlockEnergyNetworkLoader extends Block {
    private String name = "energyNetworkLoader";

    public BlockEnergyNetworkLoader() {
        super(Material.PISTON, MapColor.STONE);
        this.setUnlocalizedName(this.name);
        this.setRegistryName(this.name);
        this.setCreativeTab(ParticlesMod.particleCreativeTab);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (hand == EnumHand.MAIN_HAND && heldItem != null && heldItem.getItem() == ItemRegistry.getItem("particleEnergy")) {
                PlayerNBTUtils.addToIntTag(playerIn, Constants.PARTICLE_NBT_NAME, heldItem.stackSize);
                playerIn.setHeldItem(EnumHand.MAIN_HAND, null);
                return true;
            }
        }
        return false;
    }
}
