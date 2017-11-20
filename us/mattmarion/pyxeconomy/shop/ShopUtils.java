package us.mattmarion.pyxeconomy.shop;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_11_R1.NBTTagCompound;

public class ShopUtils {
    public static boolean itemHasName(String name, ItemStack item) {
	if (item == null) return false;
	
	if (!item.hasItemMeta()) return false;
	  
	if (!item.getItemMeta().getDisplayName().equals(name)) return false;
	    
	return true;
    }
    
    public static double[] parseLocation(Location location) {
	double[] coords = new double[3];
	coords[0] = location.getX();
	coords[1] = location.getY();
	coords[2] = location.getZ();
	return coords;
    }
}
