package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.mattmarion.pyxeconomy.shop.BaseShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class Dice extends BaseShopItem implements Listener {
    
    private static final String name = ChatColor.RED + "Dice"; 
    private static final String configName = "DICE";
    private static final double price = 35;
    private static final String priceLore = ChatColor.GOLD + "35" + ChatColor.GREEN + " coins";
    private static final String description = ChatColor.GREEN + "Roll the dice! Random item.";
    private static ItemStack item; 
    
    public Dice(Inventory inv, int slot) {
	super(inv, slot);
    }
    
    public Dice() {
	
    }
   
    @Override
    public String getName() {
	return name;
    }
    
    @Override
    public String getConfigName() {
	return configName;
    }

    @Override
    public double getPrice() {
	return price;
    }

    @Override
    public void createItem() {
	item = new ItemStack(Material.NETHER_STAR);
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
    public void on(PlayerInteractEvent event) {
	Player player = event.getPlayer();
	int itemSlot = player.getInventory().getHeldItemSlot();
	if (player.getItemInHand() == null) {
	    return;
	}
	ItemStack item = player.getItemInHand();
	boolean isDice = ShopUtils.itemHasName(name, item);
	if (!isDice) {
	    return;
	}
	player.getInventory().removeItem(item);
	Bukkit.dispatchCommand(player, "pyx random " + player.getName());
	
    }
}
