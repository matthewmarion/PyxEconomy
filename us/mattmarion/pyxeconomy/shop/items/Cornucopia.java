package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.mattmarion.pyxeconomy.shop.BaseShopItem;
import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class Cornucopia extends BaseShopItem implements Listener {

    private final String name = ChatColor.GOLD + "Cornucopia"; 
    private final String configName = "CORN";
    private final double price = 20;
    private final String priceLore = ChatColor.GOLD + "20" + ChatColor.GREEN + " coins";
    private final String description = ChatColor.GREEN + "7 seconds of Regeneration 1 and 10 minutes of Saturation.";
    private final List<String> lore = new ArrayList<String>();
    private ItemStack item; 
    
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
    public void on(PlayerItemConsumeEvent event) {
	Player player = event.getPlayer();
	ItemStack foodAte = event.getItem();
	boolean ateCornucopia = ShopUtils.itemHasName(name, foodAte);
	if (!ateCornucopia) {
	    return;
	}
	player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 280, 1));
	player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 12000, 1));
    }
}
