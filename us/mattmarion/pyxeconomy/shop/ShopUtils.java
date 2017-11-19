package us.mattmarion.pyxeconomy.shop;

import org.bukkit.inventory.ItemStack;

public class ShopUtils {
    public static boolean itemHasName(String name, ItemStack item) {
	if (item == null) return false;
	
	if (!item.hasItemMeta()) return false;
	  
	if (!item.getItemMeta().getDisplayName().equals(name)) return false;
	    
	return true;
    }

}
