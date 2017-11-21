package us.mattmarion.pyxeconomy.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

import us.mattmarion.pyxeconomy.shop.items.ArtemisBow;
import us.mattmarion.pyxeconomy.shop.items.AxeOfPerun;
import us.mattmarion.pyxeconomy.shop.items.ExodusHelmet;
import us.mattmarion.pyxeconomy.shop.items.HideOfLeviathan;
import us.mattmarion.pyxeconomy.shop.items.TabletsBow;
import us.mattmarion.pyxeconomy.shop.items.TabletsSword;


public class ShopInventory {
    
    private static Inventory inv;
    
    public ShopInventory() {
	inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Shop");
	ArtemisBow artemis = new ArtemisBow();
	inv.setItem(0, artemis.getItem());
	ExodusHelmet exodus = new ExodusHelmet();
	inv.setItem(1, exodus.getItem());
	HideOfLeviathan hod = new HideOfLeviathan();
	inv.setItem(2, hod.getItem());
	TabletsSword ts = new TabletsSword();
	inv.setItem(3, ts.getItem());
	TabletsBow tb = new TabletsBow();
	inv.setItem(4, tb.getItem());
	AxeOfPerun ap = new AxeOfPerun();
	inv.setItem(5, ap.getItem());
    }
    
    public static Inventory getInventory() {
   	return inv;
    }      
}
