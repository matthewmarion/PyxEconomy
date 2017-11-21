package us.mattmarion.pyxeconomy.shop;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;


public class ShopUtils {
    
    private static final HashMap<String, Double> ITEMS = new HashMap<String, Double>();
    
    public static boolean itemHasName(String name, ItemStack item) {
	if (item == null) return false;
	
	if (!item.hasItemMeta()) return false;
	  
	if (!item.getItemMeta().getDisplayName().equals(name)) return false;
	    
	return true;
    }
    
    public static double getItemPriceFromName(String name) {
	if (!ITEMS.containsKey(name)) {
	    return 0;
	}
	return ITEMS.get(name);
    }
    
    public static double[] parseLocation(Location location) {
	double[] coords = new double[3];
	coords[0] = location.getX();
	coords[1] = location.getY();
	coords[2] = location.getZ();
	return coords;
    }

    public static HashMap<String, Double> getItems() {
	return ITEMS;
    }
}
