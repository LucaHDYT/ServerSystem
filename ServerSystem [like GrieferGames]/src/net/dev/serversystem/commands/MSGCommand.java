package net.dev.serversystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.serversystem.utils.DataFileUtils;
import net.dev.serversystem.utils.FileUtils;
import net.dev.serversystem.utils.Utils;

public class MSGCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("toggle")) {
					String path = "MessageReceives." + p.getUniqueId();
					
					if(DataFileUtils.cfg.getBoolean(path)) {
						DataFileUtils.cfg.set(path, false);
						DataFileUtils.saveFile();
						
						p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.MSG.Disabled"));
					} else {
						DataFileUtils.cfg.set(path, true);
						DataFileUtils.saveFile();
						
						p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.MSG.Enabled"));
					}
				} else {
					p.sendMessage(FileUtils.getConfigString("Messages.MSG.Help"));
				}
			} else if(args.length >= 2) {
				Player t = Bukkit.getPlayer(args[0]);
				
				if(t != null) {
					if(t != p) {
						if(DataFileUtils.cfg.getBoolean("MessageReceives." + t.getUniqueId())) {
							String msg = "";
							
							for (int i = 1; i < args.length; i++) {
								msg += args[i] + " ";
							}
							
							msg = msg.trim();
							
							p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.MSG.Self").replace("%player%", t.getName()).replace("%message%", msg));
							t.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.MSG.Other").replace("%player%", p.getName()).replace("%message%", msg));
							
							Utils.chats.put(t, p);
						} else {
							p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.MSG.NotGettingMessages").replace("%player%", t.getName()));
						}
					} else {
						p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.MSG.CanNotInteractWithSelf"));
					}
				} else {
					p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messsages.PlayerNotFound").replace("%player%", args[0]));
				}
			} else {
				p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.MSG.Help"));
			}
		} else {
			Utils.sendConsole(FileUtils.getConfigString("Messages.NotPlayer"));
		}
		
		return true;
	}

}
