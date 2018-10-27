package net.dev.serversystem.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

	public static ItemStack createItem(Material mat, int amount, String displayName) {
		ItemStack is = new ItemStack(mat, amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(displayName);
		is.setItemMeta(im);
		
		return is;
	}
	
}
