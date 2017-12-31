package us.mattmarion.pyxeconomy.shop;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public interface IShopItem {
    String getName();
    String getConfigName();
    double getPrice();
    void createItem();
    ItemStack getItem();
}
