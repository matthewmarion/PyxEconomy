package us.mattmarion.pyxeconomy.shop;

import org.bukkit.inventory.ItemStack;

public interface IShopItem {
    public String getName();
    public double getPrice();
    void createItem();
    public ItemStack getItem();
}
