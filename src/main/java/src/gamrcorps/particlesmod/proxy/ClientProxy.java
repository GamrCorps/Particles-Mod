package src.gamrcorps.particlesmod.proxy;

import src.gamrcorps.particlesmod.main.BlockRegistry;
import src.gamrcorps.particlesmod.main.ItemRegistry;

/**
 * Created by matthewmccaskill on 6/7/16.
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();
        ItemRegistry.registerItemTextures();
        BlockRegistry.registerBlockTextures();
    }
}
