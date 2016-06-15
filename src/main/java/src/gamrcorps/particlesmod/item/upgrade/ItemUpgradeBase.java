package src.gamrcorps.particlesmod.item.upgrade;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import src.gamrcorps.particlesmod.main.ParticlesMod;

/**
 * Created by matthewmccaskill on 6/8/16.
 */
public class ItemUpgradeBase extends Item {
    private final String name;

    public ItemUpgradeBase (String type) {
        this.name = "upgrade" + type;
        this.setUnlocalizedName(this.name);
        this.setRegistryName(this.name);
        this.setCreativeTab(ParticlesMod.particleCreativeTab);
        this.setMaxStackSize(1);
    }

    public String getName() {
        return this.name;
    }
}
