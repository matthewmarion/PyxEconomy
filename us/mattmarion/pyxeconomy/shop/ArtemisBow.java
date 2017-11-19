package us.mattmarion.pyxeconomy.shop;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArtemisBow implements IShopItem, Listener {
    
    private final String name = "Artemis' Bow"; 
    private final double price = 75;

    public String getName() {
	return name;
    }

    public double getPrice() {
	return price;
    }

    public ItemStack getItem() {
	ItemStack item = new ItemStack(Material.BOW);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(name);
	item.setItemMeta(meta);
	return item;
    }
    
    private void shootHomingArrow(Player player) {
	player.sendMessage("shooting homing arrow");
    }
    
    @EventHandler
    public void on(EntityShootBowEvent event) {
	if (!(event.getEntity() instanceof Player)) {
	    return;
	}
	Player player = (Player) event.getEntity();
	
	if (!(event.getProjectile() instanceof Arrow)) {
	    return;
	}
	boolean isArtemisBow = ShopUtils.itemHasName(name, event.getBow());
	if (!isArtemisBow) {
	    return;
	}
	
	boolean willShootHomingArrow = new Random().nextInt(4)==0;
	if (!willShootHomingArrow) {
	    return;
	}
	
	shootHomingArrow(player);
    }
}
