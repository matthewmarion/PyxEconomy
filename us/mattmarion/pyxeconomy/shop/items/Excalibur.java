package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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

public class Excalibur implements IShopItem, Listener {

    private final String name = ChatColor.DARK_GREEN + "Excalibur";
    private final String configName = "EXCALIBUR";
    private final double price = 75;
    private final String priceLore = ChatColor.GOLD + "75" + ChatColor.GREEN + " coins";
    private final String description = ChatColor.GREEN + "TNT explosion on the enemy.";
    private final List<String> lore = new ArrayList<String>();
    private ItemStack item; 
    
    public Excalibur(Inventory inv, int slot) {
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
	item = new ItemStack(Material.DIAMOND_SWORD);
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
    
    public HashMap<UUID, Long> playersOnCooldown = new HashMap<>();
    private int cooldown = 8;
    @EventHandler
    public void on(EntityDamageByEntityEvent event) {
	if (!(event.getDamager() instanceof Player)) {
	    return;
	}
	
	if (!(event.getEntity() instanceof Player)) {
	    return;
	}
	
	Player player = (Player) event.getDamager();
	boolean usedExcalibur = ShopUtils.itemHasName(name, player.getItemInHand());
	if (!usedExcalibur) {
	    return;
	}
	boolean playerIsOnCooldown = ShopUtils.playerIsOnCooldown(player, playersOnCooldown, cooldown);
	if (playerIsOnCooldown) {
	    return;
	}
	Player attackedPlayer = (Player) event.getEntity();
	attackedPlayer.getLocation().getWorld().createExplosion(attackedPlayer.getLocation(), 0, false);
	if (attackedPlayer.getHealth() <= 6) {
	    event.setDamage(20);
	    putPlayerOnCooldown(player, 8);
	    return;
	}
	attackedPlayer.setHealth(attackedPlayer.getHealth() - 6);
	putPlayerOnCooldown(player, 8);
    }
    
    private void putPlayerOnCooldown(Player player, int duration) {
	long currentTime = System.currentTimeMillis();
	playersOnCooldown.put(player.getUniqueId(), currentTime);
    }

}
