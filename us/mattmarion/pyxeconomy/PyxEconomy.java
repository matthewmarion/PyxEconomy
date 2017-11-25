package us.mattmarion.pyxeconomy;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import us.mattmarion.pyxeconomy.commands.CommandHandler;
import us.mattmarion.pyxeconomy.listeners.PlayerKillListener;
import us.mattmarion.pyxeconomy.profile.ProfileListeners;
import us.mattmarion.pyxeconomy.shop.ShopListeners;
import us.mattmarion.pyxeconomy.shop.items.Anduril;
import us.mattmarion.pyxeconomy.shop.items.ArtemisBow;
import us.mattmarion.pyxeconomy.shop.items.AxeOfPerun;
import us.mattmarion.pyxeconomy.shop.items.Cornucopia;
import us.mattmarion.pyxeconomy.shop.items.DeathsScythe;
import us.mattmarion.pyxeconomy.shop.items.DeusExMachina;
import us.mattmarion.pyxeconomy.shop.items.Dice;
import us.mattmarion.pyxeconomy.shop.items.Excalibur;
import us.mattmarion.pyxeconomy.shop.items.ExodusHelmet;
import us.mattmarion.pyxeconomy.shop.items.FlaskOfIchor;
import us.mattmarion.pyxeconomy.shop.items.PlayersDaredevil;

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
	registerListener(new ProfileListeners());
	registerListener(new PlayerKillListener());
	registerListener(new ArtemisBow());
	registerListener(new AxeOfPerun());
	registerListener(new Excalibur());
	registerListener(new Anduril());
	registerListener(new ExodusHelmet());
	registerListener(new ShopListeners());
	registerListener(new Cornucopia());
	registerListener(new DeusExMachina());
	registerListener(new DeathsScythe());
	registerListener(new PlayersDaredevil());
	registerListener(new FlaskOfIchor());
	registerListener(new Dice());
    }
    
    private void registerListener(Listener listener) {
	getServer().getPluginManager().registerEvents(listener, this);
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
