package us.mattmarion.pyxeconomy.shop;

import org.bukkit.inventory.Inventory;

public abstract class BaseShopItem implements IShopItem {
    
    public BaseShopItem(Inventory inv, int slot) {
	createItem();
	ShopUtils.getItemPrices().put(getName(), getPrice());
	ShopUtils.getItems().put(getConfigName(), this);
	inv.setItem(slot, getItem());
    }
    
    public BaseShopItem() {
	
    }

}
