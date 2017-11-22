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
    
    private static Inventory inv;
    
    public ShopInventory(Player player) {
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
	Excalibur ex = new Excalibur();
	inv.setItem(6, ex.getItem());
	Anduril an = new Anduril();
	inv.setItem(7, an.getItem());
	Cornucopia cn = new Cornucopia();
	inv.setItem(8, cn.getItem());
	DeusExMachina dex = new DeusExMachina();
	inv.setItem(9, dex.getItem());
	KingsRod kd = new KingsRod();
	inv.setItem(10, kd.getItem());
	DeathsScythe ds = new DeathsScythe();
	inv.setItem(11, ds.getItem());
	PlayersDaredevil pd = new PlayersDaredevil();
	pd.createNewItem(player);
	inv.setItem(12, pd.getItem());
	FlaskOfIchor fi = new FlaskOfIchor();
	inv.setItem(13, fi.getItem());
	Dice dice = new Dice();
	inv.setItem(14, dice.getItem());
    }
    
    public static Inventory getInventory() {
   	return inv;
    }      
}
