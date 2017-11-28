package us.mattmarion.pyxeconomy.shop.items;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.mattmarion.pyxeconomy.shop.BaseShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class Anduril extends BaseShopItem implements Listener {

    private static final String name = ChatColor.DARK_AQUA + "Anduril"; 
    private static final String configName = "ANDURIL";
    private static final double price = 50;
    private static final String priceLore = ChatColor.GOLD + "75" + ChatColor.GREEN + " coins";
    private static final String description = ChatColor.GREEN + "Gives you Speed 1 and Resistance 1, while you hold it.";
    private static ItemStack item; 
    
    public Anduril(Inventory inv, int slot) {
	super(inv, slot);
    }
    
    public Anduril() {
	
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
	item = new ItemStack(Material.IRON_SWORD);
	item.addEnchantment(Enchantment.DAMAGE_ALL, 2);
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
    
    Set<Player> playersUsedAnduril = new HashSet<Player>();
    @EventHandler
    public void on(PlayerItemHeldEvent event) {
	Player player = event.getPlayer();
	int currentSlot = event.getNewSlot();
	if (player.getInventory().getItem(currentSlot) == null) {
	    playerSwitchedOffAnduril(player);
	    return;
	}
	ItemStack holdingItem = player.getInventory().getItem(currentSlot);
	boolean itemIsAnduril = ShopUtils.itemHasName(name, holdingItem);
	if (!itemIsAnduril) {
	    playerSwitchedOffAnduril(player);
	    return;
	}
	giveEffects(player, 50000);
	playersUsedAnduril.add(player);
    }
    
    @EventHandler
    public void on(PlayerPickupItemEvent event) {
	Player player = event.getPlayer();
	if (event.getItem().getItemStack() == null) {
	    return;
	}
	ItemStack pickedUp = event.getItem().getItemStack();
	boolean itemIsAnduril = ShopUtils.itemHasName(name, pickedUp);
	if (!itemIsAnduril) {
	    return;
	}
	
	if (player.getInventory().getHeldItemSlot() != player.getInventory().firstEmpty()) {
	    return;
	}
	
	giveEffects(player, 50000);
	playersUsedAnduril.add(player);
    }
    
    private void playerSwitchedOffAnduril(Player player) {
	if (playersUsedAnduril.contains(player)) {
	    giveEffects(player, 40);
	    playersUsedAnduril.remove(player);
	}
    }
    
    @EventHandler
    public void on(PlayerDeathEvent event) {
	Player player = event.getEntity();
	playersUsedAnduril.remove(player);
    }
    
    @EventHandler
    public void on(PlayerQuitEvent event) {
	Player player = event.getPlayer();
	playersUsedAnduril.remove(player);
	for (PotionEffect effect : player.getActivePotionEffects()) {
	    player.removePotionEffect(effect.getType());
	}
    }
    
    @EventHandler
    public void on(PlayerDropItemEvent event) {
	Player player = event.getPlayer();
	if (event.getItemDrop() == null) {
	    return;
	}
	ItemStack dropped = event.getItemDrop().getItemStack();
	boolean itemIsAnduril = ShopUtils.itemHasName(name, dropped);
	if (!itemIsAnduril) {
	    return;
	}
	
	giveEffects(player, 40);
	playersUsedAnduril.remove(player);
    }
    
    public static void giveEffects(Player player, int duration) {
	player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, 0), true);
	player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, duration, 0), true);
    }
}
