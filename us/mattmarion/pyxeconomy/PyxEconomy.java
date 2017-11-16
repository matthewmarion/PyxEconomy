package us.mattmarion.pyxeconomy;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import us.mattmarion.pyxeconomy.commands.GiveBalanceCommand;
import us.mattmarion.pyxeconomy.listeners.PlayerKillListener;
import us.mattmarion.pyxeconomy.profile.ProfileListeners;

public class PyxEconomy extends JavaPlugin {
    
    private static PyxEconomy instance;
    
    public static File dataf, configf;
    public static FileConfiguration data, config;
    
    public final void onEnable() {
	instance = this;
	loadConfig();
	registerEvents();
	registerCommands();
    }
    
    private void registerEvents() {
	getServer().getPluginManager().registerEvents(new ProfileListeners(), this);
	getServer().getPluginManager().registerEvents(new PlayerKillListener(), this);
    }
    
    private void registerCommands() {
	getCommand("pyxcoin").setExecutor(new GiveBalanceCommand());
    }
    
    private void loadConfig() {

	dataf = new File(getDataFolder(), "database.yml");
	configf = new File(getDataFolder(), "config.yml");

	if (!dataf.exists()) {
		dataf.getParentFile().mkdirs();
		saveResource("database.yml", false);
	}
	if (!configf.exists()) {
		configf.getParentFile().mkdirs();
		saveResource("config.yml", false);
	}

	data = new YamlConfiguration();
	config = new YamlConfiguration();
	try {
		try {
			data.load(dataf);
		} catch (InvalidConfigurationException e) {

			e.printStackTrace();
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	try {
		try {
			config.load(configf);
		} catch (InvalidConfigurationException e) {

			e.printStackTrace();
		}
	} catch (IOException e) {
		e.printStackTrace();
	}

    }
    
    public static FileConfiguration getDataConfig() {
	return PyxEconomy.data;
    }

    @Override
    public FileConfiguration getConfig() {
	return PyxEconomy.config;
    }
    
    public static PyxEconomy getInstance() {
	return instance;
    }
}
