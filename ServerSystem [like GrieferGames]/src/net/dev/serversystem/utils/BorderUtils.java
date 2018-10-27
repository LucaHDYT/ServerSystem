package net.dev.serversystem.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.config.Configuration;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotBlock;
import com.intellectualcrafters.plot.object.PlotManager;

import net.dev.serversystem.main.Main;

public class BorderUtils {

	private static PlotAPI api = new PlotAPI();

	public static void openMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, FileUtils.getConfigString("Settings.RandInventory.Title"));

		for (int i = 0; i < inv.getSize(); i++)
			inv.setItem(i, ItemUtils.createItem(Material.STAINED_GLASS_PANE, 1, "§r"));

		inv.setItem(0, ItemUtils.createItem(Material.DIAMOND_BLOCK, 1, "§aDiamond block"));
		inv.setItem(1, ItemUtils.createItem(Material.EMERALD_BLOCK, 1, "§aEmerald block"));
		inv.setItem(2, ItemUtils.createItem(Material.GLOWSTONE, 1, "§aGlowstone"));
		inv.setItem(3, ItemUtils.createItem(Material.GOLD_BLOCK, 1, "§aGold block"));
		inv.setItem(4, ItemUtils.createItem(Material.SEA_LANTERN, 1, "§aSea lantern"));
		inv.setItem(5, ItemUtils.createItem(Material.IRON_BLOCK, 1, "§aIron block"));
		inv.setItem(6, ItemUtils.createItem(Material.FENCE, 1, "§aFence"));
		inv.setItem(7, ItemUtils.createItem(Material.BEACON, 1, "§aBeacon"));
		inv.setItem(8, ItemUtils.createItem(Material.ANVIL, 1, "§aAnvil"));
		inv.setItem(9, ItemUtils.createItem(Material.LOG, 1, "§aLog"));
		inv.setItem(10, ItemUtils.createItem(Material.STONE, 1, "§aStone"));
		inv.setItem(11, ItemUtils.createItem(Material.WOOD, 1, "§aPlanks"));
		inv.setItem(12, ItemUtils.createItem(Material.CARPET, 1, "§aCarpet"));
		inv.setItem(13, ItemUtils.createItem(Material.BARRIER, 1, "§aBarrier"));
		inv.setItem(14, ItemUtils.createItem(Material.ENCHANTMENT_TABLE, 1, "§aEnchantment table"));
		inv.setItem(15, ItemUtils.createItem(Material.STONE_SLAB2, 1, "§aStone slab"));

		p.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	public static void setBorder(Player p, String id) {
		p.closeInventory();
		
		Plot plot = api.getPlot(p.getLocation());

		if (plot != null) {
			if (plot.isOwner(p.getUniqueId()) || p.hasPermission("plots.admin")) {
				PlotManager plotManager = api.getPlotManager(p.getWorld());
				PlotBlock[] plotBlocks = Configuration.BLOCKLIST.parseString(id);

				if (plot.getConnectedPlots().size() > 1) {
					plot.getConnectedPlots().forEach(allPlot -> plotManager.setComponent(allPlot.getArea(),
							allPlot.getId(), "border", plotBlocks));
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

}
