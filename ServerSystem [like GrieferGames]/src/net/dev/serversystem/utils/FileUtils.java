package net.dev.serversystem.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class FileUtils {

	private static File folder = new File("plugins/" + Utils.getDescription().getName() + "/");
	private static File file = new File("plugins/" + Utils.getDescription().getName() + "/setup.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static void saveFile() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Utils.prefix = getConfigString("Messages.Prefix");
	}
	
	public static void setupFiles() {
		if(!(folder.exists()))
			folder.mkdir();
		
		if(!(file.exists())) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		cfg.addDefault("Messages.Prefix", "&8[&6System&8] &7");
		cfg.addDefault("Messages.NoPerm", "&cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
		cfg.addDefault("Messages.NotPlayer", "&cOnly players can perform this command");
		cfg.addDefault("Messages.Vanish.Enabled", "&7Vanish for the player &e%player% &7has been &aenabled");
		cfg.addDefault("Messages.Vanish.Disabled", "&7Vanish for the player &e%player% &7has been &cdisabled");
		cfg.addDefault("Messages.Vanish.Self.Enabled", "&7You are now in vanish");
		cfg.addDefault("Messages.Vanish.Self.Disabled", "&7You are no longer in vanish");
		cfg.options().copyDefaults(true);
		saveFile();
	}
	
	public static String getConfigString(String path) {
		return cfg.getString(path).replace("&", "§");
	}
	
}
