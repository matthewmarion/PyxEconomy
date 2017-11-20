package us.mattmarion.pyxeconomy.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

import us.mattmarion.pyxeconomy.shop.items.ArtemisBow;


public class ShopInventory {
    
    private Inventory inv;
    
    public ShopInventory() {
	inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Shop");
	ArtemisBow artemis = new ArtemisBow();
	inv.setItem(0, artemis.getItem());
    }
    
    public Inventory getInventory() {
   	return inv;
    }      
}
