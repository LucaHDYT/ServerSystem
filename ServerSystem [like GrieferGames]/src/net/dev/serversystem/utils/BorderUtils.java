package net.dev.serversystem.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.config.Configuration;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotBlock;
import com.intellectualcrafters.plot.object.PlotManager;

import net.dev.serversystem.main.Main;

public class BorderUtils {

	private static PlotAPI api = new PlotAPI();
	
	public static void openMenu(Player p) {
		
	}
	
	@SuppressWarnings("deprecation")
	public static void setBorder(Player p, String id) {
		Plot plot = api.getPlot(p.getLocation());

		if(plot.isOwner(p.getUniqueId()) || p.hasPermission("plots.admin")) {
			PlotManager plotManager = api.getPlotManager(p.getWorld());
			PlotBlock[] plotBlocks = Configuration.BLOCKLIST.parseString(id);
			
			if(plot.getConnectedPlots().size() > 1) {
				plot.getConnectedPlots().forEach(allPlot -> plotManager.setComponent(allPlot.getArea(), allPlot.getId(), "border", plotBlocks));
			} else {
				plotManager.setComponent(plot.getArea(), plot.getId(), "border", plotBlocks);
			}
			
			Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable() {
				
				@Override
				public void run() {
					plot.setSign();
					
					p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Rand.Changed"));
				}
			}, 15);
		} else {
			p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Rand.NotYourPlot"));
		}
	}

}
