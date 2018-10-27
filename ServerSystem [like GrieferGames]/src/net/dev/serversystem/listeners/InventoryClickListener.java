package net.dev.serversystem.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.dev.serversystem.utils.BorderUtils;
import net.dev.serversystem.utils.FileUtils;
import net.dev.serversystem.utils.Utils;

public class InventoryClickListener implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();

			if (e.getInventory().getName().equalsIgnoreCase(FileUtils.getConfigString("Settings.RandInventory.Title"))) {
				e.setCancelled(true);

				if ((e.getCurrentItem() != null) && (e.getCurrentItem().getType() != Material.AIR)
						&& (e.getCurrentItem().getItemMeta() != null)
						&& (e.getCurrentItem().getItemMeta().getDisplayName() != null)) {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aDiamond block")) {
						BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aEmerald block")) {
						if (p.hasPermission("system.border.*")) {
							BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
						} else {
							p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Rand.NoPerm"));
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aGlowstone")) {
						BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aGold block")) {
						BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSea lantern")) {
						if (p.hasPermission("system.border.*")) {
							BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
						} else {
							p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Rand.NoPerm"));
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aIron block")) {
						BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aFence")) {
						BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBeacon")) {
						if (p.hasPermission("system.border.*")) {
							BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
						} else {
							p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Rand.NoPerm"));
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAnvil")) {
						if (p.hasPermission("system.border.*")) {
							BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
						} else {
							p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Rand.NoPerm"));
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aLog")) {
						BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aStone")) {
						BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aPlanks")) {
						BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aCarpet")) {
						BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBarrier")) {
						BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase("§aEnchantment table")) {
						if (p.hasPermission("system.border.*")) {
							BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
						} else {
							p.sendMessage(Utils.prefix + FileUtils.getConfigString("Messages.Rand.NoPerm"));
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aStone slab")) {
						BorderUtils.setBorder(p, e.getCurrentItem().getType().toString());
					}
				}
			}
		}
	}

}
