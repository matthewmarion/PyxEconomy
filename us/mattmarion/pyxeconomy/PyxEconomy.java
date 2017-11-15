package us.mattmarion.pyxeconomy;

import org.bukkit.plugin.java.JavaPlugin;

import us.mattmarion.pyxeconomy.listeners.PlayerKillListener;
import us.mattmarion.pyxeconomy.profile.ProfileListeners;

public class PyxEconomy extends JavaPlugin {
    
    private static PyxEconomy instance;
    
    public final void onEnable() {
	instance = this;
	registerEvents();
    }
    
    private void registerEvents() {
	getServer().getPluginManager().registerEvents(new ProfileListeners(), this);
	getServer().getPluginManager().registerEvents(new PlayerKillListener(), this);
    }
    
    public static PyxEconomy getInstance() {
	return instance;
    }
}
