package net.dev.serversystem.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.serversystem.commands.VanishCommand;
import net.dev.serversystem.listeners.VanishListener;
import net.dev.serversystem.utils.FileUtils;
import net.dev.serversystem.utils.Utils;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		FileUtils.setupFiles();
		
		getCommand("vanish").setExecutor(new VanishCommand());
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new VanishListener(), this);
		
		Utils.sendConsole("§7The system has been§8: §aENABLED");
	}
	
	@Override
	public void onDisable() {
		Utils.sendConsole("§7The system has been§8: §cDISABLED");
	}
	
}
