package net.dev.serversystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.serversystem.utils.BorderUtils;
import net.dev.serversystem.utils.FileUtils;
import net.dev.serversystem.utils.Utils;

public class RandCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(p.hasPermission("system.border")) {
				BorderUtils.openMenu(p);
			} else {
				p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.NoPerm"));
			}
		} else {
			Utils.sendConsole(FileUtils.getConfigString("Messages.NotPlayer"));
		}
		
		return true;
	}

}
