package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.mattmarion.pyxeconomy.shop.BaseShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class HideOfLeviathan extends BaseShopItem implements Listener {

    private final String name = ChatColor.BLUE + "Hide of Leviathan";
    private final String configName = "HIDES";
    private final double price = 30;
    private final String priceLore = ChatColor.GOLD + "30" + ChatColor.GREEN + " coins";
    private final String description = ChatColor.GREEN + "Extracted from the mythical Leviathan.";
    private final List<String> lore = new ArrayList<String>();
    private ItemStack item; 
    
    public HideOfLeviathan(Inventory inv, int slot) {
	super(inv, slot);
    }
    
    public HideOfLeviathan() {
	
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
	item = new ItemStack(Material.DIAMOND_LEGGINGS);
	item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
	item.addUnsafeEnchantment(Enchantment.OXYGEN, 5);
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
