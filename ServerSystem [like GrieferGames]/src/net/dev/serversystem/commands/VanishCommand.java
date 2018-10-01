package net.dev.serversystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.serversystem.utils.FileUtils;
import net.dev.serversystem.utils.Utils;

public class VanishCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(p.hasPermission("system.vanish")) {
				if(args.length == 0) {
					if(Utils.vanished.contains(p)) {
						Utils.vanished.remove(p);
						
						Bukkit.getOnlinePlayers().forEach(all -> all.showPlayer(p));
						
						p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Vanish.Self.Disabled"));
					} else {
						Utils.vanished.add(p);
						
						Bukkit.getOnlinePlayers().forEach(all -> {
							if(all == p)
								return;
							
							if(!(all.hasPermission("system.vanish.bypass")))
								all.hidePlayer(p);
						});
						
						p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Vanish.Self.Enabled"));
					}
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					
					if(t != null) {
						if(Utils.vanished.contains(t)) {
							Utils.vanished.remove(t);
							
							Bukkit.getOnlinePlayers().forEach(all -> all.showPlayer(t));
							
							p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Vanish.Disabled").replace("%player%", t.getName()));
							t.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Vanish.Self.Disabled"));
						} else {
							Utils.vanished.add(t);
							
							Bukkit.getOnlinePlayers().forEach(all -> {
								if(all == t)
									return;
								
								if(!(all.hasPermission("system.vanish.bypass")))
									all.hidePlayer(t);
							});
							
							p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Vanish.Enabled").replace("%player%", t.getName()));
							t.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Vanish.Self.Enabled"));
						}
					} else {
						p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messsages.PlayerNotFound").replace("%player%", args[0]));
					}
				}
			} else {
				p.sendMessage(FileUtils.getConfigString("Messages.NoPerm"));
			}
		} else {
			Utils.sendConsole(FileUtils.getConfigString("Messages.NotPlayer"));
		}
		
		return true;
	}

}
