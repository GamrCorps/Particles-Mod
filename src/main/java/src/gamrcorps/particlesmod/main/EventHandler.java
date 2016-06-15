package src.gamrcorps.particlesmod.main;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import src.gamrcorps.particlesmod.util.WorldUtils;

/**
 * Created by matthewmccaskill on 6/11/16.
 */
public class EventHandler {
    @SubscribeEvent
    public void onRightClick (PlayerInteractEvent event) {
        if (event.getHand() == EnumHand.MAIN_HAND && WorldUtils.getBlock(event.getWorld(), event.getPos()) == BlockRegistry.getBlock("sifter")) {
            if (event.getItemStack() != null && (event.getItemStack().getItem() == Item.getItemFromBlock(Blocks.DIRT) || event.getItemStack().getItem() == Item.getItemFromBlock(Blocks.SAND) || event.getItemStack().getItem() == Item.getItemFromBlock(Blocks.GRAVEL))) {
                event.setCanceled(true);
            }
        }
    }
}
