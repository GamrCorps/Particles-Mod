package src.gamrcorps.particlesmod.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import src.gamrcorps.particlesmod.tileentity.TileEntityUpgradeCatalyst;

/**
 * Created by matthewmccaskill on 6/13/16.
 */
public class ContainerUpgradeCatalyst extends Container {

    private final IInventory tileUpgradeHolder;
    private final int sizeInventory;

    public ContainerUpgradeCatalyst(InventoryPlayer parInventoryPlayer, IInventory parIInventory)
    {
        // DEBUG
        System.out.println("ContainerGrinder constructor()");

        tileUpgradeHolder = parIInventory;
        sizeInventory = tileUpgradeHolder.getSizeInventory();

        int i = 51;

        for (int j = 0; j < parIInventory.getSizeInventory(); ++j)
        {
            this.addSlotToContainer(new Slot(parIInventory, j, 44 + j * 18, 20));
        }

        for (int l = 0; l < 3; ++l)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(parIInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + i));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(parIInventory, i1, 8 + i1 * 18, 58 + i));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return tileUpgradeHolder.isUseableByPlayer(playerIn);
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.tileUpgradeHolder.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1, this.tileUpgradeHolder.getSizeInventory(), this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.tileUpgradeHolder.getSizeInventory(), false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
