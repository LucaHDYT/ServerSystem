package net.dev.serversystem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.dev.serversystem.utils.DataFileUtils;
import net.dev.serversystem.utils.ScoreboardUtils;
import net.dev.serversystem.utils.Utils;

public class JoinQuitListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String path = "MessageReceives." + p.getUniqueId();
		
		if(!(p.hasPermission("system.vanish.bypass")))
			Utils.vanished.forEach(vanished -> p.hidePlayer(vanished));
		
		if(!(DataFileUtils.cfg.contains(path))) {
			DataFileUtils.cfg.set(path, true);
			DataFileUtils.saveFile();
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		if(Utils.vanished.contains(p))
			Utils.vanished.remove(p);
		
		if(ScoreboardUtils.scoreboards.containsKey(p.getName()))
			ScoreboardUtils.scoreboards.remove(p.getName());
	}
	
}
