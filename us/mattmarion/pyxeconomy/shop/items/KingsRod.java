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
import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class KingsRod extends BaseShopItem {
    
    private static final String name = ChatColor.GREEN + "King's Rod"; 
    private static final String configName = "KINGSROD";
    private static final double price = 25;
    private static final String priceLore = ChatColor.GOLD + "25" + ChatColor.GREEN + " coins";
    private static final String description = ChatColor.GREEN + "Your majesty's rod.";
    private static ItemStack item; 
    
    public KingsRod(Inventory inv, int slot) {
	super(inv, slot);
    }
    
    public KingsRod() {
	
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
	item = new ItemStack(Material.FISHING_ROD);
	item.addUnsafeEnchantment(Enchantment.LURE, 5);
	item.addUnsafeEnchantment(Enchantment.LUCK, 10);
	item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
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
