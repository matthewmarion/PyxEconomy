package us.mattmarion.pyxeconomy.shop;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class ShopUtils {
    
    private static final HashMap<String, Double> ITEM_PRICES = new HashMap<String, Double>();
    private static final HashMap<String, IShopItem> ITEMS = new HashMap<String, IShopItem>();
    
    public static boolean itemHasName(String name, ItemStack item) {
	if (item == null) return false;
	
	if (!item.hasItemMeta()) return false;
	  
	if (!item.getItemMeta().getDisplayName().equals(name)) return false;
	    
	return true;
    }
    
    public static double getItemPriceFromName(String name) {
	if (!ITEM_PRICES.containsKey(name)) {
	    return 0;
	}
	return ITEM_PRICES.get(name);
    }
    
    public static ItemStack getItemFromName(String name) {
	if (!ITEMS.containsKey(name)) {
	    return null;
	}
	IShopItem shopItem = ITEMS.get(name);
	ItemStack itemStack = shopItem.getItem();
	return itemStack;
    }
    
    public static double[] parseLocation(Location location) {
	double[] coords = new double[3];
	coords[0] = location.getX();
	coords[1] = location.getY();
	coords[2] = location.getZ();
	return coords;
    }

    public static HashMap<String, Double> getItemPrices() {
	return ITEM_PRICES;
    }
    
    public static HashMap<String, IShopItem> getItems() {
	return ITEMS;
    }
    
    
    public static boolean playerIsOnCooldown(Player player, HashMap<UUID, Long> cooldownPlayers, int cooldownTime) {
	if (!cooldownPlayers.containsKey(player.getUniqueId())) {
	    return false;
	}
	long startTime = cooldownPlayers.get(player.getUniqueId()) / 1000;
	long currentTime = System.currentTimeMillis() / 1000;
	if ((currentTime - startTime) >= cooldownTime) {
	    return false;
	}
	return true;
    }
}
