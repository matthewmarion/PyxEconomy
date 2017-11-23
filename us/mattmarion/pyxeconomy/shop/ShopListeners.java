package us.mattmarion.pyxeconomy.shop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import us.mattmarion.pyxeconomy.profile.Profile;

public class ShopListeners implements Listener {

    private HashMap<UUID, Integer> playerPurchases = new HashMap<>();

    @EventHandler
    public void on(PlayerInteractEntityEvent event) {
	if (!(event.getRightClicked() instanceof Villager)) {
	    return;
	}
	Villager villager = (Villager) event.getRightClicked();
	if (villager.getCustomName() == null) {
	    return;
	}
	ShopInventory shopInv = new ShopInventory(event.getPlayer());
	event.getPlayer().openInventory(shopInv.getInventory());
	event.setCancelled(true);
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
	if (!event.getInventory().getName().equals(ChatColor.GREEN + "Shop")) {
	    return;
	}
	if (event.getRawSlot() >= event.getInventory().getSize()) {
	    event.setCancelled(true);
	    return;
	}
	if(event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
	    return;
	}

	Player player = (Player) event.getWhoClicked();
	Profile profile = Profile.getByPlayer(player);
	
	if (!canPlayerPurchase(player)) {
	    player.sendMessage(ChatColor.RED + "You are out of purchases! You must die in combat or relog to get more.");
	    player.closeInventory();
	    return;
	}

	ItemStack item = event.getCurrentItem();
	double price = ShopUtils.getItemPriceFromName(item.getItemMeta().getDisplayName());
	//Price only should be 0 when its a daredevils egg.
	if (price == 0) {
	    price = 150;
	}
	if (price > profile.getBalance()) {
	    player.closeInventory();
	    player.sendMessage(ChatColor.RED + "Insufficient funds");
	    return;
	}

	player.getInventory().addItem(item);
	profile.removeBalance(price);
	addPlayerPurchase(player.getUniqueId());
	profile.save();
	player.closeInventory();
	player.sendMessage(ChatColor.GREEN + "You have purchased " + ChatColor.GOLD + "[1] " + ChatColor.GREEN + item.getItemMeta().getDisplayName());
    }
    
    @EventHandler
    public void on(PlayerLoginEvent event) {
	playerPurchases.put(event.getPlayer().getUniqueId(), 0);
    }
    
    @EventHandler
    public void on(PlayerDeathEvent event) {
	playerPurchases.put(event.getEntity().getUniqueId(), 0);
    }
    
    @EventHandler
    public void on(EntityDamageEvent event) {
	if (!(event.getEntity() instanceof Villager))  {
	    return;
	}
	
	Villager npc = (Villager) event.getEntity();
	if (npc.getCustomName() == null) {
	    return;
	}
	event.setCancelled(true);
    }

    public void addPlayerPurchase(UUID uuid) {
	if (!playerPurchases.containsKey(uuid)) {
	    playerPurchases.put(uuid, 1);
	} else {
	    playerPurchases.put(uuid, playerPurchases.get(uuid) + 1);
	}
    }

    public boolean canPlayerPurchase(Player player) {
	int purchases = playerPurchases.get(player.getUniqueId());
	for (int i = 4; i > 0; i--) {
	    if (player.hasPermission("pyxshop." + i)) {
		if (i <= purchases) {
		    return false;
		}
		return true;
	    }
	}
	return false;
    }
}
