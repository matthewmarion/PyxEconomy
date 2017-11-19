package us.mattmarion.pyxeconomy.shop;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class FlaskOfIchor implements IShopItem, Listener {

    private final String name = "Flask Of Ichor";
    private final double price = 10;
    
    @Override
    public String getName() {
	return name;
    }

    @Override
    public double getPrice() {
	return price;
    }

    @Override
    public ItemStack getItem() {
	Potion potion = new Potion(PotionType.INSTANT_DAMAGE, 2);
	ItemStack item = potion.toItemStack(1);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(name);
	item.setItemMeta(meta);
	return item;
    }
    

}
