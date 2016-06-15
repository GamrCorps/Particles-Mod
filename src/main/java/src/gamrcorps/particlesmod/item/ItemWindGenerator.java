package src.gamrcorps.particlesmod.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import src.gamrcorps.particlesmod.main.ItemRegistry;
import src.gamrcorps.particlesmod.main.ParticlesMod;
import src.gamrcorps.particlesmod.util.WorldUtils;

/**
 * Created by matthewmccaskill on 6/12/16.
 */
public class ItemWindGenerator extends Item {
    private String name;
    public double speedCount = 0.0;

    public ItemWindGenerator() {
        this.name = "windGenerator";
        this.setUnlocalizedName(this.name);
        this.setRegistryName(this.name);
        this.setCreativeTab(ParticlesMod.particleCreativeTab);
        this.setMaxStackSize(1);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (isSelected) {
            Entity entity = entityIn;
            double max = 50.0;
            if (entity.isRiding()) {
                entity = entityIn.getLowestRidingEntity();
                max = 1000.0;
            }
            if (Math.abs(entity.motionX) > 2.0) speedCount += Math.abs(entity.motionX);
            if (Math.abs(entity.motionY) > 2.0) speedCount += Math.abs(entity.motionY);
            if (Math.abs(entity.motionZ) > 2.0) speedCount += Math.abs(entity.motionZ);
            if (speedCount >= max) {
                WorldUtils.spawnItem(worldIn, new ItemStack(ItemRegistry.getItem("particleAir"), 1), entityIn.posX, entityIn.posY, entityIn.posZ);
                speedCount = 0.0;
            }
        }
    }
}