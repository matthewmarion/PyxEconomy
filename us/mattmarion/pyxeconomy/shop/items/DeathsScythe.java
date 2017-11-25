package us.mattmarion.pyxeconomy.shop.items;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.mattmarion.pyxeconomy.shop.BaseShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class DeathsScythe extends BaseShopItem implements Listener {
    
    private static final String name = ChatColor.DARK_RED + "Death's Sycthe"; 
    private static final String configName = "SCYTHE";
    private static final double price = 50;
    private static final String priceLore = ChatColor.GOLD + "50" + ChatColor.GREEN + " coins";
    private static final String description = ChatColor.GREEN + "Removes 20% of the enemies current health.";
    private static ItemStack item; 
    
    public DeathsScythe(Inventory inv, int slot) {
	super(inv, slot);
    }
    
    public DeathsScythe() {
	
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
    
    private HashMap<UUID, Integer> scytheUses = new HashMap<UUID, Integer>();
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
	incrementUsage(player);
    }
    
    private void incrementUsage(Player player) {
	if (scytheUses.get(player.getUniqueId()) == null) {
	    scytheUses.put(player.getUniqueId(), 1);
	} else {
	    int uses = scytheUses.get(player.getUniqueId());
	    if (uses >= 30) {
		breakScythe(player);
	    }
	    scytheUses.put(player.getUniqueId(), uses++);
	}
    }
    
    private void breakScythe(Player player) {
	if (player.getInventory().getItemInHand() == null) {
	    return;
	}
	ItemStack scythe = player.getInventory().getItemInHand();
	player.getInventory().remove(scythe);
	player.getLocation().getWorld().playEffect(player.getLocation(), Effect.ITEM_BREAK, 1);
	player.sendMessage(ChatColor.RED + "You have used your " + scythe.getItemMeta().getDisplayName() + "'s use limit and it has broken!");
    }
}
