package net.dev.serversystem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.dev.serversystem.utils.Utils;

public class VanishListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if(!(p.hasPermission("system.vanish.bypass")))
			Utils.vanished.forEach(vanished -> p.hidePlayer(vanished));
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		if(Utils.vanished.contains(p))
			Utils.vanished.remove(p);
	}
	
}
