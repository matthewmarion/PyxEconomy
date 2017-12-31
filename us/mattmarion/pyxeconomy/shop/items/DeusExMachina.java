package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import us.mattmarion.pyxeconomy.PyxEconomy;
import us.mattmarion.pyxeconomy.shop.BaseShopItem;
import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class DeusExMachina extends BaseShopItem implements Listener {
    
    private static final String name = ChatColor.LIGHT_PURPLE + "Deus Ex Machina";
    private static final String configName = "DEUS";
    private static final double price = 10;
    private static final String priceLore = ChatColor.GOLD + "10" + ChatColor.GREEN + " coins";
    private static final String description = ChatColor.GREEN + "Gives you Resistance 5 (Invincibility) for 10 seconds.";
    private static ItemStack item; 
    private Set<UUID> invinciblePlayers = new HashSet<>();
    
    public DeusExMachina(Inventory inv, int slot) {
	super(inv, slot);
    }
    
    public DeusExMachina() {
	
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
	item = new ItemStack(Material.POTION);
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
    public void on(PlayerItemConsumeEvent event) {
	Player player = event.getPlayer();
	ItemStack foodConsumed = event.getItem();
	boolean drankDeus = ShopUtils.itemHasName(name, foodConsumed);
	if (!drankDeus) {
	    return;
	}
	player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 5));
	invinciblePlayers.add(player.getUniqueId());
	removeFromInvincible(player);
	
    }
    
    @EventHandler
    public void on(EntityDamageEvent event) {
	if (event.getEntity() instanceof Player) {
	    Player player = (Player) event.getEntity();
	    if (invinciblePlayers.contains(player.getUniqueId())) {
		event.setCancelled(true);
	    }
	}
    }
    
    private void removeFromInvincible(Player player) {
	new BukkitRunnable() {
		@Override
		public void run() {
		    invinciblePlayers.remove(player.getUniqueId());
		}
	    }.runTaskLater(PyxEconomy.getInstance(), 200);
    }
}
