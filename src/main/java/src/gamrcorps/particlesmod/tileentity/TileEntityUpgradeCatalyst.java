package src.gamrcorps.particlesmod.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import src.gamrcorps.particlesmod.container.ContainerUpgradeCatalyst;
import src.gamrcorps.particlesmod.item.upgrade.ItemUpgradeBase;
import src.gamrcorps.particlesmod.main.Constants;

import javax.annotation.Nullable;

/**
 * Created by matthewmccaskill on 6/13/16.
 */
public class TileEntityUpgradeCatalyst extends TileEntityLockable implements ITickable, ISidedInventory{
    public enum slotEnum
    {
        SLOT_0, SLOT_1, SLOT_2, SLOT_3, SLOT_4
    }
    private static final int[] slotsTop = new int[] {0, 1, 2, 3, 4};
    private static final int[] slotsBottom = new int[] {};
    private static final int[] slotsSides = new int[] {};
    private ItemStack[] itemStackArray = new ItemStack[5];
    private String customName;

    @Override
    public boolean shouldRefresh(World parWorld, BlockPos parPos, IBlockState parOldState, IBlockState parNewState)
    {
        return false;
    }

    @Override
    public int getSizeInventory()
    {
        return itemStackArray.length;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return itemStackArray[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (itemStackArray[index] != null)
        {
            ItemStack itemstack;

            if (itemStackArray[index].stackSize <= count)
            {
                itemstack = itemStackArray[index];
                itemStackArray[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = itemStackArray[index].splitStack(count);

                if (itemStackArray[index].stackSize == 0)
                {
                    itemStackArray[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack result = itemStackArray[index].copy();
        itemStackArray[index] = null;
        return result;
    }

    /***
    /**
     * When some containers are closed they call this on each slot, then
     * drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     #/
    @Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (itemStackArray[index] != null)
        {
            ItemStack itemstack = itemStackArray[index];
            itemStackArray[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }
    */

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        boolean isSameItemStackAlreadyInSlot = stack != null && stack.isItemEqual(itemStackArray[index]) && ItemStack.areItemStackTagsEqual(stack, itemStackArray[index]);
        itemStackArray[index] = stack;

        if (stack != null && stack.stackSize > getInventoryStackLimit())
        {
            stack.stackSize = getInventoryStackLimit();
        }

    }

    @Override
    public String getName()
    {
        return hasCustomName() ? customName : "container.upgradeHolder";
    }

    @Override
    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }

    public void setCustomInventoryName(String parCustomName)
    {
        customName = parCustomName;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        itemStackArray = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbtTagCompound = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbtTagCompound.getByte("Slot");

            if (b0 >= 0 && b0 < itemStackArray.length)
            {
                itemStackArray[b0] = ItemStack.loadItemStackFromNBT(
                        nbtTagCompound);
            }
        }

        if (compound.hasKey("CustomName", 8))
        {
            customName = compound.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < itemStackArray.length; ++i)
        {
            if (itemStackArray[i] != null)
            {
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setByte("Slot", (byte)i);
                itemStackArray[i].writeToNBT(nbtTagCompound);
                nbttaglist.appendTag(nbtTagCompound);
            }
        }

        compound.setTag("Items", nbttaglist);

        if (hasCustomName())
        {
            compound.setString("CustomName", customName);
        }

        return compound;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    // this function indicates whether container texture should be drawn
    @SideOnly(Side.CLIENT)
    public static boolean func_174903_a(IInventory parIInventory)
    {
        return true ;
    }

    @Override
    public void update()
    {
        //TODO: ADD CODE

        /*
        boolean hasBeenGrinding = grindingSomething();
        boolean changedGrindingState = false;

        if (grindingSomething())
        {
            --timeCanGrind;
        }

        if (!worldObj.isRemote)
        {
            // if something in input slot
            if (itemStackArray[slotEnum.INPUT_SLOT.ordinal()] !=
                    null)
            {
                // start grinding
                if (!grindingSomething() && canGrind())
                {
                    timeCanGrind = 150;

                    if (grindingSomething())
                    {
                        changedGrindingState = true;
                    }
                }

                // continue grinding
                if (grindingSomething() && canGrind())
                {
                    ++ticksGrindingItemSoFar;

                    // check if completed grinding an item
                    if (ticksGrindingItemSoFar == ticksPerItem)
                    {
                        ticksGrindingItemSoFar = 0;
                        ticksPerItem = timeToGrindOneItem(
                                itemStackArray[0]);
                        grindItem();
                        changedGrindingState = true;
                    }
                }
                else
                {
                    ticksGrindingItemSoFar = 0;
                }
            }

            // started or stopped grinding, update block to change to active
            // or inactive model
            if (hasBeenGrinding != grindingSomething())
            {
                changedGrindingState = true;
                BlockGrinder.changeBlockBasedOnGrindingStatus(
                        grindingSomething(), worldObj, pos);
            }
        }

        if (changedGrindingState)
        {
            markDirty();
        }
        */
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer playerIn)
    {
        return worldObj.getTileEntity(pos) != this ? false :
                playerIn.getDistanceSq(pos.getX()+0.5D, pos.getY()+0.5D,
                        pos.getZ()+0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer playerIn) {}

    @Override
    public void closeInventory(EntityPlayer playerIn) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return stack.getItem() instanceof ItemUpgradeBase;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return slotsTop;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn,
                                 EnumFacing direction)
    {
        return isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int parSlotIndex, ItemStack parStack,
                                  EnumFacing parFacing)
    {
        return true;
    }

    @Override
    public String getGuiID()
    {
        return Constants.MOD_ID + ":upgradeHolder";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        // DEBUG
        //System.out.println("TileEntityGrinder createContainer()");
        //return new ContainerUpgradeCatalyst(playerInventory, this);
        return new ContainerHopper(playerInventory, this, playerIn);
    }

    @Override
    public int getField(int id)
    {
        /*
        switch (id)
        {
            case 0:
                return timeCanGrind;
            case 1:
                return currentItemGrindTime;
            case 2:
                return ticksGrindingItemSoFar;
            case 3:
                return ticksPerItem;
            default:
                return 0;
        }
        */
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {
        /*
        switch (id)
        {
            case 0:
                timeCanGrind = value;
                break;
            case 1:
                currentItemGrindTime = value;
                break;
            case 2:
                ticksGrindingItemSoFar = value;
                break;
            case 3:
                ticksPerItem = value;
                break;
            default:
                break;
        }
        */
    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < itemStackArray.length; ++i)
        {
            itemStackArray[i] = null;
        }
    }
}