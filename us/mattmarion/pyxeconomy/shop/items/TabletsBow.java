package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.mattmarion.pyxeconomy.shop.BaseShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class TabletsBow extends BaseShopItem {
    
    private static final String name = ChatColor.RED + "Tablet's Bow"; 
    private static final String configName = "TABLETS_BOW";
    private static final double price = 50;
    private static final String priceLore = ChatColor.GOLD + "50" + ChatColor.GREEN + " coins";
    private static final String description = ChatColor.GRAY + "Tablet's deadly bow.";
    private static ItemStack item; 
    
    public TabletsBow(Inventory inv, int slot) {
	super(inv, slot);
    }
    
    public TabletsBow() {

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
	item = new ItemStack(Material.BOW);
	item.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
	item.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
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
}
