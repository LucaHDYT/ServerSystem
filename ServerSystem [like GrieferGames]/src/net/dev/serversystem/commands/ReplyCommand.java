package net.dev.serversystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.serversystem.utils.DataFileUtils;
import net.dev.serversystem.utils.FileUtils;
import net.dev.serversystem.utils.Utils;

public class ReplyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(args.length >= 1) {
				if(Utils.chats.containsKey(p)) {
					Player t = Utils.chats.get(p);
					
					if(t != null) {
						if(DataFileUtils.cfg.getBoolean("MessageReceives." + t.getUniqueId())) {
							String msg = "";
							
							for (int i = 0; i < args.length; i++) {
								msg += args[i] + " ";
							}
							
							msg = msg.trim();
							
							p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.MSG.Self").replace("%player%", t.getName()).replace("%message%", msg));
							t.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.MSG.Other").replace("%player%", p.getName()).replace("%message%", msg));
						} else {
							p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.MSG.NotGettingMessages").replace("%player%", t.getName()));
						}
					} else {
						p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messsages.PlayerNotFound").replace("%player%", args[0]));
					}
				} else {
					p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Reply.GotNoMessages"));
				}
			} else {
				p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Reply.Help"));
			}
		} else {
			Utils.sendConsole(FileUtils.getConfigString("Messages.NotPlayer"));
		}
		
		return true;
	}

}
