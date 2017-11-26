package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import us.mattmarion.pyxeconomy.PyxEconomy;
import us.mattmarion.pyxeconomy.shop.BaseShopItem;
import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class Cornucopia extends BaseShopItem implements Listener {

    private static final String name = ChatColor.GOLD + "Cornucopia"; 
    private static final String configName = "CORN";
    private static final double price = 20;
    private static final String priceLore = ChatColor.GOLD + "20" + ChatColor.GREEN + " coins";
    private static final String description = ChatColor.GREEN + "7 seconds of Regeneration 1 and 10 minutes of Saturation.";
    private static ItemStack item; 
    
    public Cornucopia(Inventory inv, int slot) {
	super(inv, slot);
    }
    
    public Cornucopia() {
	
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
	item = new ItemStack(Material.GOLDEN_CARROT, 3);
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
	if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
	    Player player = event.getPlayer();
	    if (player.getItemInHand() == null) {
		return;
	    }
	    
	    ItemStack corn = player.getItemInHand();
	    boolean isCorn = ShopUtils.itemHasName(name, item);
	    if (!isCorn) {
		return;
	    }
	    giveCornEffects(player);
	    useCorn(corn, player);
	}
    }
    
    private void giveCornEffects(Player player) {
	player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 280, 1), true);
	player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 12000, 1), true);
    }
    
    private void useCorn(ItemStack corn, Player player) {
	int newAmount = corn.getAmount() - 1;
	if (newAmount == 0) {
	    new BukkitRunnable() {
		@Override
		public void run() {
		    player.getInventory().remove(corn);
		}
	    }.runTask(PyxEconomy.getInstance());
	    return;
	}
	corn.setAmount(newAmount);
    }
}
