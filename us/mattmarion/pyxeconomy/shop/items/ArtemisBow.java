package us.mattmarion.pyxeconomy.shop.items;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class ArtemisBow implements IShopItem, Listener {
    
    private final String name = ChatColor.LIGHT_PURPLE + "Artemis' Bow"; 
    private final String configName = "ARTEMIS";
    private final double price = 75;
    private final String priceString = ChatColor.GOLD + "75" + ChatColor.GREEN + " coins";
    private final String description = ChatColor.GREEN + "25% chance of shooting a homing arrow.";
    private final List<String> lore = new ArrayList<String>();
    private ItemStack item;
    

    public ArtemisBow() {
	createItem();
	ShopUtils.getItemPrices().put(name, price);
	ShopUtils.getItems().put(configName, this);
    }
    
    public String getName() {
	return name;
    }

    public double getPrice() {
	return price;
    }
    
    public void createItem() {
	item = new ItemStack(Material.BOW);
	item.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(name);
	lore.add(priceString);
	lore.add(description);
	meta.setLore(lore);
	item.setItemMeta(meta);
    }

    public ItemStack getItem() {
	return item;
    }
    
    private void shootHomingArrow(Player player) {
	player.sendMessage("shooting homing arrow");
    }
    
    @EventHandler
    public void on(EntityShootBowEvent event) {
	if (!(event.getEntity() instanceof Player)) {
	    return;
	}
	Player player = (Player) event.getEntity();
	
	if (!(event.getProjectile() instanceof Arrow)) {
	    return;
	}
	boolean isArtemisBow = ShopUtils.itemHasName(name, event.getBow());
	if (!isArtemisBow) {
	    return;
	}
	
	boolean willShootHomingArrow = new Random().nextInt(4)==0;
	if (!willShootHomingArrow) {
	    return;
	}
	
	shootHomingArrow(player);
    }
}
