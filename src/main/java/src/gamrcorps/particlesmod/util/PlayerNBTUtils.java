package src.gamrcorps.particlesmod.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagInt;

/**
 * Created by matthewmccaskill on 6/12/16.
 */
public class PlayerNBTUtils {
    public static void addToIntTag(EntityPlayer player, String tagName, int amount) {
        player.getEntityData().setTag(tagName, new NBTTagInt(player.getEntityData().getInteger(tagName) + amount));
    }
}
