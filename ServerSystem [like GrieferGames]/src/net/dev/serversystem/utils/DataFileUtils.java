package net.dev.serversystem.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class DataFileUtils {

	private static File folder = new File("plugins/" + Utils.getDescription().getName() + "/");
	private static File file = new File("plugins/" + Utils.getDescription().getName() + "/data.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static void saveFile() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	}
	
}
