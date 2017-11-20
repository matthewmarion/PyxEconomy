package us.mattmarion.pyxeconomy.shop;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ShopListeners implements Listener {
    
    @EventHandler
    public void on(PlayerInteractEntityEvent event) {
	if (!(event.getRightClicked() instanceof Villager)) return;
	Villager villager = (Villager) event.getRightClicked();
	if (villager.getCustomName() == null) return;
	ShopInventory shopInv = new ShopInventory();
	event.getPlayer().openInventory(shopInv.getInventory());
	event.setCancelled(true);
    }
    
    

}
