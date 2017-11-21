package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class AxeOfPerun implements IShopItem, Listener {
    
    private final String name = ChatColor.DARK_PURPLE + "Axe of Perun"; 
    private final double price = 75;
    private final String priceLore = ChatColor.GOLD + "75" + ChatColor.GREEN + " coins";
    private final String description = ChatColor.GREEN + "Strikes your enemy with lightning upon hitting them.";
    private final List<String> lore = new ArrayList<String>();
    private ItemStack item; 
    
    public AxeOfPerun() {
	createItem();
	ShopUtils.getItems().put(name, price);
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
	item = new ItemStack(Material.DIAMOND_AXE);
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
	Player player = (Player) event.getDamager();
	boolean usedAxeOfPerun = ShopUtils.itemHasName(name, player.getItemInHand());
	if (!usedAxeOfPerun) {
	    return;
	}
	Player attackedPlayer = (Player) event.getEntity();
	attackedPlayer.getLocation().getWorld().strikeLightning(attackedPlayer.getLocation());
	attackedPlayer.setHealth(attackedPlayer.getHealth() - 6);
    }
}
