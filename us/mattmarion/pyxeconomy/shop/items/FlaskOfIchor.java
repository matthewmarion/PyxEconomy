package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import us.mattmarion.pyxeconomy.shop.BaseShopItem;

public class FlaskOfIchor extends BaseShopItem implements Listener {
    
    private static final String name = ChatColor.YELLOW + "Flask of Ichor";
    private static final String configName = "ICHOR";
    private static final double price = 10;
    private static final String priceLore = ChatColor.GOLD + "10" + ChatColor.GREEN + " coins";
    private static final String description = ChatColor.GREEN + "Deadly splash " + ChatColor.RED + "DAMAGE 3 " + ChatColor.GREEN + "potion.";
    private static ItemStack item; 
    
    public FlaskOfIchor(Inventory inv, int slot) {
	super(inv, slot);
    }
    
    public FlaskOfIchor() {
	
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
