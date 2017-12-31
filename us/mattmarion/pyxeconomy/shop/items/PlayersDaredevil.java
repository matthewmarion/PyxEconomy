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
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;

import com.sk89q.worldedit.blocks.BlockType;

import us.mattmarion.pyxeconomy.PyxEconomy;
import us.mattmarion.pyxeconomy.shop.BaseShopItem;
import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;

public class PlayersDaredevil extends BaseShopItem implements Listener {

    private static final String name = ChatColor.DARK_PURPLE + "Daredevil";
    private static final String configName = "DARE";
    private static final double price = 150;
    private static final String priceLore = ChatColor.GOLD + "150" + ChatColor.GREEN + " coins";
    private static final String description = ChatColor.GREEN + "Personal deadly steed.";
    private static ItemStack item; 
    
    public PlayersDaredevil(Inventory inv, int slot) {
	super(inv, slot);
    }
    
    public PlayersDaredevil() {
	
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
	useDare(itemInHand, player);
    }
    
    @EventHandler
    public void on(EntityMountEvent event) {
	if (!(event.getEntity() instanceof Player)) {
	    return;
	}
	Player player = (Player) event.getEntity();
	
	if (!(event.getMount() instanceof Horse)) {
	    return;
	}
	Entity entity = event.getMount();
	
	if (!daredevils.containsKey(player.getUniqueId())) {
	    player.sendMessage(ChatColor.RED + "You cannot mount someone elses daredevil.");
	    event.setCancelled(true);
	    return;
	}
	
	if (daredevils.get(player.getUniqueId()) != entity) {
	    player.sendMessage(ChatColor.RED + "You cannot mount someone elses daredevil.");
	    ShopUtils.freezeEntity(entity);
	    event.setCancelled(true);
	    return;
	}
	Horse horse = (Horse) entity;
	ShopUtils.unfreezeEntity(entity);
	horse.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 10));
    }
    
    @EventHandler
    public void on(EntityDismountEvent event) {
	if (!(event.getEntity() instanceof Player)) {
	    return;
	}
	Player player = (Player) event.getEntity();
	
	if (!(event.getDismounted() instanceof Horse)) {
	    return;
	}
	Entity entity = event.getDismounted();
	
	if (horseInWater(entity)) {
	    ShopUtils.unfreezeEntity(entity);
	    Horse horse = (Horse) entity;
	    horse.removePotionEffect(PotionEffectType.SPEED);
	    return;
	}
	
	ShopUtils.freezeEntity(entity);
    }
    
    private void createDaredevil(Player player, Block block) {
	Location location = block.getLocation();
	location.setY(location.getY() + 1);
	Horse horse = (Horse) player.getWorld().spawnEntity(location, EntityType.HORSE);
	horse.getInventory().setItem(0, new ItemStack(Material.SADDLE));
	horse.setVariant(Variant.SKELETON_HORSE);
	horse.setTamed(true);
	horse.setJumpStrength(.7);
	horse.setMaxHealth(40);
	horse.setHealth(40);
	horse.setAdult();
	horse.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 10));
	horse.setCustomName(ChatColor.DARK_PURPLE + player.getName() + "'s " + name);
	ShopUtils.freezeEntity(horse);
	daredevils.put(player.getUniqueId(), horse);
    }

    @Override
    public void createItem() {
	// TODO Auto-generated method stub
    }
    
    private void useDare(ItemStack dare, Player player) {
	int newAmount = dare.getAmount() - 1;
	if (newAmount == 0) {
	    new BukkitRunnable() {
		@Override
		public void run() {
		    player.getInventory().remove(dare);
		}
	    }.runTask(PyxEconomy.getInstance());
	    return;
	}
	dare.setAmount(newAmount);
    }
    
    private boolean horseInWater(Entity horse) {
	Material block = horse.getLocation().getBlock().getType();
        return block == Material.WATER || (block == Material.STATIONARY_WATER);
    }
}
