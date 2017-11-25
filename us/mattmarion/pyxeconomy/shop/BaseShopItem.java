package us.mattmarion.pyxeconomy.shop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.Inventory;

public abstract class BaseShopItem implements IShopItem {
    
    protected List<String> lore = new ArrayList<String>();
    
    public BaseShopItem(Inventory inv, int slot) {
	createItem();
	ShopUtils.getItemPrices().put(getName(), getPrice());
	ShopUtils.getItems().put(getConfigName(), this);
	inv.setItem(slot, getItem());
    }
    
    public BaseShopItem() {
	
    }

}
