package us.mattmarion.pyxeconomy;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import us.mattmarion.pyxeconomy.commands.CommandHandler;
import us.mattmarion.pyxeconomy.commands.balance.BalanceCommand;
import us.mattmarion.pyxeconomy.commands.balance.GiveBalanceCommand;
import us.mattmarion.pyxeconomy.commands.balance.RemoveBalanceCommand;
import us.mattmarion.pyxeconomy.commands.balance.ResetBalanceCommand;
import us.mattmarion.pyxeconomy.listeners.PlayerKillListener;
import us.mattmarion.pyxeconomy.profile.ProfileListeners;
import us.mattmarion.pyxeconomy.shop.ShopListeners;
import us.mattmarion.pyxeconomy.shop.items.ArtemisBow;

public class PyxEconomy extends JavaPlugin {
    
    private static PyxEconomy instance;
    
    public static File dataf, configf, shopdataf;
    public static FileConfiguration data, config, shopdata;
    
    public final void onEnable() {
	instance = this;
	loadConfig();
	registerEvents();
	registerCommands();
    }
    
    private void registerEvents() {
	getServer().getPluginManager().registerEvents(new ProfileListeners(), this);
	getServer().getPluginManager().registerEvents(new PlayerKillListener(), this);
	getServer().getPluginManager().registerEvents(new ArtemisBow(), this);
	getServer().getPluginManager().registerEvents(new ShopListeners(),this);
    }
    
    private void registerCommands() {
	getCommand("pyx").setExecutor(new CommandHandler());
    }
    
    private void loadConfig() {

	dataf = new File(getDataFolder(), "database.yml");
	configf = new File(getDataFolder(), "config.yml");
	shopdataf = new File(getDataFolder(), "shopdata.yml");

	if (!dataf.exists()) {
		dataf.getParentFile().mkdirs();
		saveResource("database.yml", false);
	}
	if (!configf.exists()) {
		configf.getParentFile().mkdirs();
		saveResource("config.yml", false);
	}
	if (!shopdataf.exists()) {
		shopdataf.getParentFile().mkdirs();
		saveResource("shopdata.yml", false);
	}

	data = new YamlConfiguration();
	config = new YamlConfiguration();
	shopdata = new YamlConfiguration();
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
	try {
		try {
			shopdata.load(shopdataf);
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
    
    public static FileConfiguration getShopDataConfig() {
	return PyxEconomy.shopdata;
    }

    @Override
    public FileConfiguration getConfig() {
	return PyxEconomy.config;
    }
    
    public static PyxEconomy getInstance() {
	return instance;
    }
}
