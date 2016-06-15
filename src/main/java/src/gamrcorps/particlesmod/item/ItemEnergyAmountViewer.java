package src.gamrcorps.particlesmod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import src.gamrcorps.particlesmod.main.Constants;
import src.gamrcorps.particlesmod.main.ParticlesMod;

/**
 * Created by matthewmccaskill on 6/12/16.
 */
public class ItemEnergyAmountViewer extends Item {
    private String name;

    public String getName() {
        return name;
    }

    public ItemEnergyAmountViewer() {
        this.name = "energyAmountViewer";
        this.setUnlocalizedName(this.name);
        this.setRegistryName(this.name);
        this.setCreativeTab(ParticlesMod.particleCreativeTab);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (!worldIn.isRemote) playerIn.addChatComponentMessage(new TextComponentString("Energy Stored: " + playerIn.getEntityData().getInteger(Constants.PARTICLE_NBT_NAME)));
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }
}
