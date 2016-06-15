package src.gamrcorps.particlesmod.util;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by matthewmccaskill on 6/9/16.
 */
public class WorldUtils {
    public static void spawnItem(World world, ItemStack item, double x, double y, double z) {
        if (!world.isRemote) world.spawnEntityInWorld(new EntityItem(world, x, y, z, item));
    }

    public static Block getBlock(World world, BlockPos pos) {
        return world.getBlockState(pos).getBlock();
    }

    public static Block getBlock(World world, int x, int y, int z) {
        return getBlock(world, new BlockPos(x, y, z));
    }
}
