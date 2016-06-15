package src.gamrcorps.particlesmod.proxy;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import src.gamrcorps.particlesmod.handler.GuiHandler;
import src.gamrcorps.particlesmod.main.ParticlesMod;
import src.gamrcorps.particlesmod.recipe.RecipeRegistry;

/**
 * Created by matthewmccaskill on 6/7/16.
 */
public class CommonProxy {
    public void preInit() {

    }

    public void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(ParticlesMod.instance, new GuiHandler());
    }
}
