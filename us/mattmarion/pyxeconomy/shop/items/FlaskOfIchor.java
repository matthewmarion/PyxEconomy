package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class FlaskOfIchor implements IShopItem, Listener {
    
    private final String name = ChatColor.YELLOW + "Flask of Ichor"; 
    private final double price = 10;
    private final String priceLore = ChatColor.GOLD + "10" + ChatColor.GREEN + " coins";
    private final String description = ChatColor.GREEN + "Deadly splash DAMAGE 3 potion.";
    private final List<String> lore = new ArrayList<String>();
    private ItemStack item; 
    
    public FlaskOfIchor() {
	createItem();
	ShopUtils.getItems().put(name, price);
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
	item = new ItemStack(Material.SPLASH_POTION);
	PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
	potionMeta.setBasePotionData(new PotionData(PotionType.INSTANT_DAMAGE));
	item.setData(potionMeta);
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
    public void on(PotionSplashEvent event) {
	if (!(event.getEntity() instanceof Player)) {
	    return;
	}
	Potion potion = (Potion) event.getPotion();
	ItemStack potionItem = potion.toItemStack(1);
	boolean isFlaskOfIchor = ShopUtils.itemHasName(name, potionItem);
	if (!isFlaskOfIchor) {
	    return;
	}
	
	Collection<LivingEntity> affected = event.getAffectedEntities();
	for (LivingEntity entity : affected) {
	    event.setIntensity(entity, 1.5);
	}
    }
}
