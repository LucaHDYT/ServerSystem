package net.dev.serversystem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if(p.hasPermission("system.coloredchat"))
			e.setMessage(e.getMessage().replace("&", "§"));
		
		e.setFormat(p.getDisplayName() + "§7: §r" + e.getMessage());
	}
	
}
