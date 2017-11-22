package us.mattmarion.pyxeconomy.shop.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.spigotmc.event.entity.EntityMountEvent;

import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class PlayersDaredevil implements Listener, IShopItem {

    private final String name = ChatColor.DARK_PURPLE + "Daredevil";
    private final String configName = "DARE";
    private final double price = 150;
    private final String priceLore = ChatColor.GOLD + "150" + ChatColor.GREEN + " coins";
    private final String description = ChatColor.GREEN + "Personal deadly steed.";
    private final List<String> lore = new ArrayList<String>();
    private ItemStack item; 
    
    public PlayersDaredevil() {
	ShopUtils.getItemPrices().put(name, price);
	ShopUtils.getItems().put(configName, this);
    }
   
    public String getName() {
	return name;
    }

    public double getPrice() {
	return price;
    }

    public void createNewItem(Player player) {
	item = new ItemStack(Material.MONSTER_EGG);
	ItemMeta meta = item.getItemMeta();
	meta.setDisplayName(ChatColor.DARK_PURPLE + player.getName() + "'s " + name);
	lore.add(priceLore);
	lore.add(description);
	meta.setLore(lore);
	item.setItemMeta(meta);
    }

    public ItemStack getItem() {
	return item;
    }
    
    private HashMap<UUID, Entity> daredevils = new HashMap<>();
    @EventHandler
    public void on(PlayerInteractEvent event) {
	if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
	    return;
	}
	
	Player player = event.getPlayer();
	if (player.getItemInHand() == null) {
	    return;
	}
	ItemStack itemInHand = player.getItemInHand();
	boolean usedDaredevilEgg = ShopUtils.itemHasName(ChatColor.DARK_PURPLE + player.getName() + "'s " + name, itemInHand);
	if (!usedDaredevilEgg) {
	    return;
	}
	Block blockClicked = event.getClickedBlock();
	createDaredevil(player, blockClicked);
    }
    
    @EventHandler
    public void on(EntityMountEvent event) {
	if (!(event.getEntity() instanceof Player)) {
	    return;
	}
	Player player = (Player) event.getEntity();
	
	if (!(event.getMount() instanceof SkeletonHorse)) {
	    return;
	}
	Entity horse = event.getMount();
	
	if (!daredevils.containsKey(player.getUniqueId())) {
	    player.sendMessage(ChatColor.RED + "You cannot mount someone elses daredevil.");
	    event.setCancelled(true);
	    return;
	}
	
	if (daredevils.get(player.getUniqueId()) != horse) {
	    player.sendMessage(ChatColor.RED + "You cannot mount someone elses daredevil.");
	    event.setCancelled(true);
	}
    }
    
    private void createDaredevil(Player player, Block block) {
	Location location = block.getLocation();
	location.setY(location.getY() + 1);
	SkeletonHorse horse = (SkeletonHorse) player.getWorld().spawnEntity(location, EntityType.SKELETON_HORSE);
	horse.getInventory().setItem(0, new ItemStack(Material.SADDLE));
	horse.setTamed(true);
	horse.setJumpStrength(3);
	horse.setMaxHealth(40);
	horse.setHealth(40);
	horse.setAdult();
	horse.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 75));
	horse.setCustomName(ChatColor.DARK_PURPLE + player.getName() + "'s " + name);
	horse.setAI(false);
	daredevils.put(player.getUniqueId(), horse);
    }

    @Override
    public void createItem() {
	// TODO Auto-generated method stub
    }
}
