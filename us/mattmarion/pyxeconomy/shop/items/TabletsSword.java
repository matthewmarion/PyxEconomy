package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class TabletsSword implements IShopItem {
    
    private final String name = ChatColor.RED + "Tablet's Sword"; 
    private final double price = 50;
    private final String priceLore = ChatColor.GOLD + "50" + ChatColor.GREEN + " coins";
    private final String description = ChatColor.GREEN + "Tablet's mighty sword.";
    private final List<String> lore = new ArrayList<String>();
    private ItemStack item; 
    
    public TabletsSword() {
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
	item = new ItemStack(Material.IRON_SWORD);
	item.addEnchantment(Enchantment.DAMAGE_ALL, 2);
	item.addEnchantment(Enchantment.FIRE_ASPECT, 1);
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
