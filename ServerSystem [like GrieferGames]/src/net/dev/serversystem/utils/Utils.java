package net.dev.serversystem.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import net.dev.serversystem.main.Main;

public class Utils {

	public static String prefix;
	public static ArrayList<Player> vanished = new ArrayList<>();
	public static HashMap<Player, Player> chats = new HashMap<>();
	
	public static void sendConsole(String msg) {
		Bukkit.getConsoleSender().sendMessage(prefix + msg);
	}

	public static PluginDescriptionFile getDescription() {
		return Main.getPlugin(Main.class).getDescription();
	}

}
