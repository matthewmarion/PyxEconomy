package us.mattmarion.pyxeconomy.shop;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import us.mattmarion.pyxeconomy.PyxEconomy;


public class ShopUtils {
    
    private static final HashMap<String, Double> ITEM_PRICES = new HashMap<String, Double>();
    private static final HashMap<String, IShopItem> ITEMS = new HashMap<String, IShopItem>();
    
    public static boolean itemHasName(String name, ItemStack item) {
	if (item == null) return false;
	
	if (!item.hasItemMeta()) return false;
	ItemMeta meta = item.getItemMeta();
	if (meta.getDisplayName() == null) return false;
	  
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
    
    public static void freezeEntity(Entity en) {
	net.minecraft.server.v1_8_R3.Entity nmsEn = ((CraftEntity) en).getHandle();
	NBTTagCompound compound = new NBTTagCompound();
	nmsEn.c(compound);
	compound.setByte("NoAI", (byte) 1);
	nmsEn.f(compound);
    }

    public static void unfreezeEntity(Entity en) {
	net.minecraft.server.v1_8_R3.Entity nmsEn = ((CraftEntity) en).getHandle();
	NBTTagCompound compound = new NBTTagCompound();
	nmsEn.c(compound);
	compound.remove("NoAI");
	nmsEn.f(compound);
    }
    
    public static boolean playerCanPvp(Player player) {
	WorldGuardPlugin wg = PyxEconomy.getInstance().getWorldGuard();
	RegionManager regionManager = wg.getRegionManager(player.getWorld());
	ApplicableRegionSet regionSet = regionManager.getApplicableRegions(player.getLocation());
	
	for (ProtectedRegion region : regionSet) {
	    if (region == null) {
		return true;
	    }
	    
	    if (!regionSet.allows(DefaultFlag.PVP)) {
		return false;
	    }
	}
	return true;
    }
}
