package src.gamrcorps.particlesmod.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import src.gamrcorps.particlesmod.main.ItemRegistry;
import src.gamrcorps.particlesmod.util.WorldUtils;

/**
 * Created by matthewmccaskill on 6/10/16.
 */
public class TileEntitySolarCollector extends TileEntity implements ITickable {
    public int energy = 0, energyLevel = 0;

    //TODO: Add NBT

    @Override
    public void update() {
        if (!this.worldObj.canBlockSeeSky(pos) || this.worldObj.isRaining() || this.worldObj.getWorldTime() % 24000 > 12000) {
            energyLevel = 0;
            return;
        }
        //super.updateTick();
        //0-1000:1
        //1000-2000:2
        //2000-3000:3
        //3000-4000:4
        //4000-5000:5
        //5000-7000:6
        //7000-8000:5
        //8000-9000:4
        //9000-10000:3
        //10000-11000:2
        //11000-12000:1
        double time = this.worldObj.getWorldTime() % 24000 / 1000.0;
        int level = (int) Math.ceil(time);
        switch (level){
            case 1:
                energyLevel = 1;
                break;
            case 2:
                energyLevel = 2;
                break;
            case 3:
                energyLevel = 3;
                break;
            case 4:
                energyLevel = 4;
                break;
            case 5:
                energyLevel = 5;
                break;
            case 6:
                energyLevel = 6;
                break;
            case 7:
                energyLevel = 6;
                break;
            case 8:
                energyLevel = 5;
                break;
            case 9:
                energyLevel = 4;
                break;
            case 10:
                energyLevel = 3;
                break;
            case 11:
                energyLevel = 2;
                break;
            case 12:
                energyLevel = 1;
                break;
            default:
                energyLevel = 0;
                break;
        }
        energy += energyLevel;
        if (energy >= 2400) {
            energy = 0;
            WorldUtils.spawnItem(this.worldObj, new ItemStack(ItemRegistry.getItem("particleLight"), 1), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        }
    }
}
