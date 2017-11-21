package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class DeusExMachina implements IShopItem, Listener {
    
    private final String name = ChatColor.LIGHT_PURPLE + "Deus Ex Machina";
    private final String configName = "DEUS";
    private final double price = 10;
    private final String priceLore = ChatColor.GOLD + "10" + ChatColor.GREEN + " coins";
    private final String description = ChatColor.GREEN + "Gives you Resistance 5 (Invincibility) for 10 seconds.";
    private final List<String> lore = new ArrayList<String>();
    private ItemStack item; 
    
    public DeusExMachina() {
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
    }
}
