package us.mattmarion.pyxeconomy.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

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
import us.mattmarion.pyxeconomy.shop.items.HideOfLeviathan;
import us.mattmarion.pyxeconomy.shop.items.KingsRod;
import us.mattmarion.pyxeconomy.shop.items.PlayersDaredevil;
import us.mattmarion.pyxeconomy.shop.items.TabletsBow;
import us.mattmarion.pyxeconomy.shop.items.TabletsSword;


public class ShopInventory {
    
    private Inventory inv;
    
    public ShopInventory(Player player) {
	inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Shop");
	new ArtemisBow(inv, 0);
	new ExodusHelmet(inv, 1);
	new HideOfLeviathan(inv , 2);
	new TabletsSword(inv, 3);
	new TabletsBow(inv, 4);
	new AxeOfPerun(inv, 5);
	new Excalibur(inv, 6);
	new Anduril(inv ,7);
	new Cornucopia(inv, 8);
	new DeusExMachina(inv, 9);
	new KingsRod(inv, 10);
	new DeathsScythe(inv, 11);
	PlayersDaredevil pd = new PlayersDaredevil();
	pd.createNewItem(player);
	inv.setItem(12, pd.getItem());
	new FlaskOfIchor(inv, 13);
	new Dice(inv, 14);
    }
    
    public Inventory getInventory() {
   	return inv;
    }      
}
