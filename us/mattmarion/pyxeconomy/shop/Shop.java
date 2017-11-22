package us.mattmarion.pyxeconomy.shop;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import us.mattmarion.pyxeconomy.PyxEconomy;

public class Shop {
    
    private String name;
    private Location location;
    
    public Shop(String name, Location location) {
	this.name = name;
	this.location = location;
    }
    
    public String getName() {
	return name;
    }
    
    public void setName(String name) {
	this.name = name;
    }
    
    public Location getLocation() {
	return location;
    }
    
    public void setLocation(Location location) {
	this.location = location;
    }
    
    public static boolean locationExists(String name) {
	if (PyxEconomy.shopdata.getConfigurationSection(name) == null) {
	    return false;
	}
	return true;
    }
    
    public void save() {
	double[] coords = ShopUtils.parseLocation(location);
	PyxEconomy.shopdata.set(name, name);
	PyxEconomy.shopdata.set(name + ".location" + ".x", coords[0]);
	PyxEconomy.shopdata.set(name + ".location" + ".y", coords[1]);
	PyxEconomy.shopdata.set(name + ".location" + ".z", coords[2]);
	PyxEconomy.shopdata.set(name + ".location" + ".world", location.getWorld().getName());
	try {
	    PyxEconomy.shopdata.save(PyxEconomy.shopdataf);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public void create() {
	Villager npc = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
	npc.getLocation().setDirection(location.getDirection());
	npc.setCustomName(ChatColor.GREEN + name + " Shop");
	npc.setAI(false);
	npc.setCollidable(false);
	npc.setProfession(Profession.BLACKSMITH);
    }
}
