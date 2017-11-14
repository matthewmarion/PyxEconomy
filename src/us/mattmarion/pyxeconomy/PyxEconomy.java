package us.mattmarion.pyxeconomy;

import org.bukkit.plugin.java.JavaPlugin;

public class PyxEconomy extends JavaPlugin {
    
    private static PyxEconomy instance;
    
    public final void onEnable() {
	instance = this;
	
    }
    
    public static PyxEconomy getInstance() {
	return instance;
    }
    

}
