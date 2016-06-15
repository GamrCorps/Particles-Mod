package src.gamrcorps.particlesmod.item.binding;

import net.minecraft.item.Item;
import src.gamrcorps.particlesmod.main.ParticlesMod;

/**
 * Created by matthewmccaskill on 6/8/16.
 */
public class ItemBindingAgentBase extends Item {
    private final String name;

    public ItemBindingAgentBase (String type) {
        this.name = "binding" + type;
        this.setUnlocalizedName(this.name);
        this.setRegistryName(this.name);
        this.setCreativeTab(ParticlesMod.particleCreativeTab);
    }

    public String getName() {
        return this.name;
    }
}
