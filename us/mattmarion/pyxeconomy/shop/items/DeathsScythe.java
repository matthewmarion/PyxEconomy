package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class DeathsScythe implements IShopItem, Listener {
    
    private final String name = ChatColor.DARK_RED + "Death's Sycthe"; 
    private final String configName = "SCYTHE";
    private final double price = 50;
    private final String priceLore = ChatColor.GOLD + "50" + ChatColor.GREEN + " coins";
    private final String description = ChatColor.GREEN + "Removes 20% of the enemies current health.";
    private final List<String> lore = new ArrayList<String>();
    private ItemStack item; 
    
    public DeathsScythe(Inventory inv, int slot) {
	createItem();
	ShopUtils.getItemPrices().put(name, price);
	ShopUtils.getItems().put(configName, this);
	inv.setItem(slot, getItem());
    }
   
    @Override
    public String getName() {
	return name;
    }

    @Override
    public double getPrice() {
	return price;
    }

    @Override
    public void createItem() {
	item = new ItemStack(Material.IRON_HOE);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(name);
	lore.add(priceLore);
	lore.add(description);
	meta.setLore(lore);
	item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItem() {
	return item;
    }
    
    @EventHandler
    public void on(EntityDamageByEntityEvent event) {
	if (!(event.getDamager() instanceof Player)) {
	    return;
	}
	
	if (!(event.getEntity() instanceof Player)) {
	    return;
	}
	
	Player player = (Player) event.getDamager();
	boolean usedSycthe = ShopUtils.itemHasName(name, player.getItemInHand());
	if (!usedSycthe) {
	    return;
	}
	
	Player attackedPlayer = (Player) event.getEntity();
	double health = attackedPlayer.getHealth();
	if (health - health *.20 < 0) {
	    event.setDamage(5000);
	}
	attackedPlayer.setHealth(health - health * .20);
    }
}
