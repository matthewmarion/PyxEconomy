package us.mattmarion.pyxeconomy.shop;

import org.bukkit.inventory.Inventory;

public abstract class BaseShopItem implements IShopItem {
    
    public BaseShopItem(Inventory inv, int slot) {
	inv.setItem(slot, getItem());
    }

}
