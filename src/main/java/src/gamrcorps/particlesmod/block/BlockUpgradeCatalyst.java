package src.gamrcorps.particlesmod.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import src.gamrcorps.particlesmod.main.ParticlesMod;
import src.gamrcorps.particlesmod.tileentity.TileEntityUpgradeCatalyst;

/**
 * Created by matthewmccaskill on 6/13/16.
 */
public class BlockUpgradeCatalyst extends BlockContainer {
    private String name = "upgradeHolder";

    public BlockUpgradeCatalyst() {
        super(Material.PISTON, MapColor.STONE);
        this.setUnlocalizedName(this.name);
        this.setRegistryName(this.name);
        this.setCreativeTab(ParticlesMod.particleCreativeTab);
    }

    public String getName() {
        return name;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityUpgradeCatalyst();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityUpgradeCatalyst) {
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityUpgradeCatalyst) tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote)
        {
            playerIn.openGui(ParticlesMod.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }
}
