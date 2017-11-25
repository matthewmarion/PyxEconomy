package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.mattmarion.pyxeconomy.shop.BaseShopItem;
import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class ExodusHelmet extends BaseShopItem implements Listener {
    
    private static final String name = ChatColor.AQUA + "Exodus Helmet";
    private static final String configName = "EXODUS";
    private static final double price = 100;
    private static final String priceLore = ChatColor.GOLD + "100" + ChatColor.GREEN + " coins";
    private static final String description = ChatColor.GREEN + "Regeneration when you land a bow shot!";
    private static ItemStack item;

    public ExodusHelmet(Inventory inv, int slot) {
	super(inv, slot);
    }
    
    public ExodusHelmet() {
	
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
	item = new ItemStack(Material.DIAMOND_HELMET);
	item.addEnchantment(Enchantment.DURABILITY, 1);
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
	if (!(event.getDamager() instanceof Arrow)) {
	    return;
	}
	Arrow arrow = (Arrow) event.getDamager();
	if (!(arrow.getShooter() instanceof Player)) {
	    return;
	}
	
	if (event.getEntity() == arrow.getShooter()) {
	    return;
	}
	
	Player player = (Player) arrow.getShooter();
	boolean hasExodusHelmetOn = ShopUtils.itemHasName(name, player.getInventory().getHelmet());
	if (!hasExodusHelmetOn) {
	    return;
	}
	PotionEffect effect = new PotionEffect(PotionEffectType.REGENERATION, 40, 0);
	player.addPotionEffect(effect);
    }

}
