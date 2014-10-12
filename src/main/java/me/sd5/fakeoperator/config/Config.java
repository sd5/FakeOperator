package me.sd5.fakeoperator.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class Config  {

	private JavaPlugin plugin;
	private String configName;
	
    private FileConfiguration config = null;
    private File configFile = null;

    public Config(JavaPlugin plugin, String configName) {
    	this.plugin = plugin;
    	this.configName = configName;
    }
    
    public void reload() {
        if (configFile == null) {
        configFile = new File(plugin.getDataFolder(), configName);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
     
        // Look for defaults in the jar
        InputStreamReader defConfigStreamReader = null;
		try {
			defConfigStreamReader = new InputStreamReader(plugin.getResource(configName), "UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        if (defConfigStreamReader != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStreamReader);
            config.setDefaults(defConfig);
        }
    }
    
    public FileConfiguration get() {
        if (config == null) {
            reload();
        }
        return config;
    }
    
    public void save() {
        if (config == null || configFile == null) {
            return;
        }
        try {
            get().save(configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, e);
        }
    }
	
}
