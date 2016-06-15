package src.gamrcorps.particlesmod.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import src.gamrcorps.particlesmod.main.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthewmccaskill on 6/12/16.
 */
public class CommandEnergyAmountChanger implements ICommand {
    private List aliases;
    public CommandEnergyAmountChanger()
    {
        this.aliases = new ArrayList();
        this.aliases.add("energy");
    }

    @Override
    public String getCommandName() {
        return "energy";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "energy <player> <value>";
    }

    @Override
    public List<String> getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 2) {
            sender.addChatMessage(new TextComponentString(getCommandUsage(sender)));
            return;
        }
        try {
            server.getPlayerList().getPlayerByUsername(args[0]).getEntityData().setInteger(Constants.PARTICLE_NBT_NAME, new Integer(args[1]));
        } catch (NullPointerException e) {
            sender.addChatMessage(new TextComponentString("Player is not online."));
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
