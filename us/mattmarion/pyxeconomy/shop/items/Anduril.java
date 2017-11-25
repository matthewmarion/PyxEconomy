package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.mattmarion.pyxeconomy.shop.BaseShopItem;
import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class Anduril extends BaseShopItem implements Listener {

    private final String name = ChatColor.DARK_AQUA + "Anduril"; 
    private final String configName = "ANDURIL";
    private final double price = 50;
    private final String priceLore = ChatColor.GOLD + "75" + ChatColor.GREEN + " coins";
    private final String description = ChatColor.GREEN + "Gives you Speed 1 and Resistance 1, while you hold it.";
    private final List<String> lore = new ArrayList<String>();
    private ItemStack item; 
    
    public Anduril(Inventory inv, int slot) {
	super(inv, slot);
	createItem();
	ShopUtils.getItemPrices().put(name, price);
	ShopUtils.getItems().put(configName, this);
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
    
    @EventHandler
    public void on(PlayerItemHeldEvent event) {
	Player player = event.getPlayer();
	int currentSlot = event.getNewSlot();
	int oldSlot = event.getPreviousSlot();
	if (player.getInventory().getItem(currentSlot) == null) {
	    return;
	}
	ItemStack holdingItem = player.getInventory().getItem(currentSlot);
	if (player.getInventory().getItem(oldSlot) == null) {
	    return;
	}
	ItemStack oldItem = player.getInventory().getItem(oldSlot);
	boolean oldItemIsAnduril = ShopUtils.itemHasName(name, oldItem);
	if (oldItemIsAnduril) {
	    giveEffects(player, 40);
	    return;
	}
	boolean itemIsAnduril = ShopUtils.itemHasName(name, holdingItem);
	if (!itemIsAnduril) {
	    return;
	}
	giveEffects(player, 50000);
    }
    
    private void giveEffects(Player player, int duration) {
	player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, 0), true);
	player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, duration, 0), true);
    }
}
