package src.gamrcorps.particlesmod.item.particle;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import src.gamrcorps.particlesmod.main.ParticlesMod;

/**
 * Created by matthewmccaskill on 6/8/16.
 */
public class ItemParticleBase extends Item {
    private final String name;

    public ItemParticleBase (String type) {
        this.name = "particle" + type;
        this.setUnlocalizedName(this.name);
        this.setRegistryName(this.name);
        this.setCreativeTab(ParticlesMod.particleCreativeTab);
    }

    public String getName() {
        return this.name;
    }
}
