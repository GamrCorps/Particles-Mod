package src.gamrcorps.particlesmod.block;

import net.minecraft.block.BlockHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import src.gamrcorps.particlesmod.tileentity.TileEntityAdvancedHopper;

/**
 * Created by matthewmccaskill on 6/7/16.
 */
public class BlockDistributionHopper extends BlockHopper {
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityAdvancedHopper();
    }
}
