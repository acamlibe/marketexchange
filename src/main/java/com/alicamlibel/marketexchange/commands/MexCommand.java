package com.alicamlibel.marketexchange.commands;

import com.alicamlibel.marketexchange.gui.MexGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MexCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (args.length == 0) {
            MexGui gui = new MexGui();
            player.openInventory(gui.getInventory());
            return true;
        }
        else {
            player.sendMessage("Invalid command: /mex");
            return false;
        }
    }
}
