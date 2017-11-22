package us.mattmarion.pyxeconomy.commands.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import us.mattmarion.pyxeconomy.commands.PyxCommandExecutor;
import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopInventory;
import us.mattmarion.pyxeconomy.shop.ShopUtils;
import us.mattmarion.pyxeconomy.utils.MessageUtils;

public class RandomItemCommand extends PyxCommandExecutor {
    public RandomItemCommand() {
	setSubCommand("random");
	setPermission("pyxcoin.shop.random");
	setUsage("/pyxcoin random <player>");
	setBoth();
	setLength(2);
    }
    
    private final String itemNotFound = ChatColor.RED + "This item could not be found!";
    
    @Override
    public void execute(CommandSender sender, String[] args) {
	String name = args[1];
	Player target = Bukkit.getPlayerExact(name);
	if (target == null || !target.isOnline()) {
	    sender.sendMessage(MessageUtils.playerNotFoundMessage(args[1]));
	    return;
	}
	ShopInventory shopInv = new ShopInventory(target);
	ItemStack item = getRandomItem();
	if (item == null) {
	    sender.sendMessage(ChatColor.RED + itemNotFound);
	    return;
	}
	
	giveItem(sender, target, item);
    }
    
    private void giveItem(CommandSender sender, Player player, ItemStack item) {
	if (player.getInventory().firstEmpty() == -1) {
	    player.sendMessage(ChatColor.RED + "You were given a " + ChatColor.BOLD + item.getItemMeta().getDisplayName() + ChatColor.RED + ", but you are out of space. Dropping item on ground.");
	    player.getWorld().dropItemNaturally(player.getLocation(), item);
	    sender.sendMessage(ChatColor.GREEN + "Gave " + player.getName() + " a " + item.getItemMeta().getDisplayName());
	    return;
	}
	
	player.getInventory().addItem(item);
	player.sendMessage(ChatColor.GREEN + "You were given a " + ChatColor.BOLD + item.getItemMeta().getDisplayName() + ChatColor.GREEN + ".");
	sender.sendMessage(ChatColor.GREEN + "Gave "  + player.getName() + " a " + item.getItemMeta().getDisplayName());
    }
    
    private List<String> getNameList() {
	List<String> names = new ArrayList<>();
	HashMap<String, IShopItem> items = ShopUtils.getItems();
	for (String name : items.keySet()) {
	    names.add(name);
	}
	return names;
    }
    
    private ItemStack getRandomItem() {
	List<String> itemNames = getNameList();
	System.out.println(itemNames);
	Random rand = new Random();
	int random = rand.nextInt(itemNames.size());
	System.out.println("generated number " + random);
	String itemName = itemNames.get(random);
	ItemStack item = ShopUtils.getItemFromName(itemName);
	return item;
    }
}
