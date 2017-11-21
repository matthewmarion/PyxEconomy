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

public class KingsRod implements IShopItem {
    
    private final String name = ChatColor.GREEN + "King's Rod"; 
    private final double price = 25;
    private final String priceLore = ChatColor.GOLD + "25" + ChatColor.GREEN + " coins";
    private final String description = ChatColor.GREEN + "Your majesty's rod.";
    private final List<String> lore = new ArrayList<String>();
    private ItemStack item; 
    
    public KingsRod() {
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
