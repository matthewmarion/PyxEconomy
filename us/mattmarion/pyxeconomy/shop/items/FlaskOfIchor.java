package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class FlaskOfIchor implements IShopItem, Listener {
    
    private final String name = ChatColor.YELLOW + "Flask of Ichor";
    private final String configName = "ICHOR";
    private final double price = 10;
    private final String priceLore = ChatColor.GOLD + "10" + ChatColor.GREEN + " coins";
    private final String description = ChatColor.GREEN + "Deadly splash " + ChatColor.RED + "DAMAGE 3 " + ChatColor.GREEN + "potion.";
    private final List<String> lore = new ArrayList<String>();
    private ItemStack item; 
    
    public FlaskOfIchor() {
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
	item = new Potion(PotionType.INSTANT_DAMAGE, 1, true).toItemStack(1);
	PotionMeta pMeta = (PotionMeta) item.getItemMeta();
	pMeta.setDisplayName(name);
	lore.add(priceLore);
	lore.add(description);
	pMeta.setLore(lore);
	item.setItemMeta(pMeta);
    }

    @Override
    public ItemStack getItem() {
	return item;
    }
}
