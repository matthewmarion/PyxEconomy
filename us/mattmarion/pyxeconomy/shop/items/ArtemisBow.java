package us.mattmarion.pyxeconomy.shop.items;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import us.mattmarion.pyxeconomy.PyxEconomy;
import us.mattmarion.pyxeconomy.shop.BaseShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class ArtemisBow extends BaseShopItem implements Listener {
    
    private static final String name = ChatColor.LIGHT_PURPLE + "Artemis' Bow"; 
    private static final String configName = "ARTEMIS";
    private static final double price = 75;
    private static final String priceString = ChatColor.GOLD + "75" + ChatColor.GREEN + " coins";
    private static final String description = ChatColor.GREEN + "25% chance of shooting a homing arrow.";
    private static ItemStack item;
    
    public ArtemisBow(Inventory inv, int slot) {
	super(inv, slot);
    }
    
    public ArtemisBow() {
	
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
	item = new ItemStack(Material.BOW);
	item.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(name);
	lore.add(priceString);
	lore.add(description);
	meta.setLore(lore);
	item.setItemMeta(meta);
    }
    
    @Override
    public ItemStack getItem() {
	return item;
    }
    
    private void shootHomingArrow(EntityShootBowEvent event) {
	LivingEntity player = event.getEntity();
	double minAngle = 6.283185307179586D;
	Entity minEntity = null;
	for (Entity entity : player.getNearbyEntities(64.0D, 64.0D, 64.0D)) {
	    if ((player.hasLineOfSight(entity)) && ((entity instanceof LivingEntity)) && (!entity.isDead())) {
		Vector toTarget = entity.getLocation().toVector().clone().subtract(player.getLocation().toVector());
		double angle = event.getProjectile().getVelocity().angle(toTarget);
		if (angle < minAngle) {
		    minAngle = angle;
		    minEntity = entity;
		}
	    }
	}
	if (minEntity != null) {
	    new HomingTask((Arrow) event.getProjectile(), (LivingEntity) minEntity, PyxEconomy.getInstance());
	}
    }
    
    @EventHandler
    public void on(EntityShootBowEvent event) {
	if (!(event.getEntity() instanceof Player)) {
	    return;
	}
	
	if (!(event.getProjectile() instanceof Arrow)) {
	    return;
	}
	boolean isArtemisBow = ShopUtils.itemHasName(name, event.getBow());
	if (!isArtemisBow) {
	    return;
	}
	
	boolean willShootHomingArrow = new Random().nextInt(4)==0;
	if (!willShootHomingArrow) {
	    return;
	}
	shootHomingArrow(event);
    }
}
