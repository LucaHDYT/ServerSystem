package net.dev.serversystem.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.serversystem.commands.MSGCommand;
import net.dev.serversystem.commands.RandCommand;
import net.dev.serversystem.commands.ReplyCommand;
import net.dev.serversystem.commands.VanishCommand;
import net.dev.serversystem.listeners.ChatListener;
import net.dev.serversystem.listeners.InventoryClickListener;
import net.dev.serversystem.listeners.JoinQuitListener;
import net.dev.serversystem.utils.DataFileUtils;
import net.dev.serversystem.utils.FileUtils;
import net.dev.serversystem.utils.Utils;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		FileUtils.setupFiles();
		DataFileUtils.setupFiles();
		
		getCommand("vanish").setExecutor(new VanishCommand());
		getCommand("msg").setExecutor(new MSGCommand());
		getCommand("r").setExecutor(new ReplyCommand());
		getCommand("rand").setExecutor(new RandCommand());
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinQuitListener(), this);
		pm.registerEvents(new ChatListener(), this);
		pm.registerEvents(new InventoryClickListener(), this);
		
		Utils.sendConsole("§7The system has been§8: §aENABLED");
	}
	
	@Override
	public void onDisable() {
		Utils.sendConsole("§7The system has been§8: §cDISABLED");
	}
	
}
