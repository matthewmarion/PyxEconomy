package us.mattmarion.pyxeconomy.commands.shop;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import us.mattmarion.pyxeconomy.commands.PyxCommandExecutor;
import us.mattmarion.pyxeconomy.shop.IShopItem;
import us.mattmarion.pyxeconomy.shop.ShopUtils;
import us.mattmarion.pyxeconomy.utils.MessageUtils;

public class AddItemCommand extends PyxCommandExecutor {
    public AddItemCommand() {
	setSubCommand("add");
	setPermission("pyxcoin.shop.add");
	setUsage("/pyxcoin add <player> item <item>");
	setBoth();
	setLength(4);
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
	
	if (!args[2].equalsIgnoreCase("item")) {
	    sender.sendMessage(ChatColor.RED + getUsage());
	    return;
	}
	
	String itemName = args[3];
	ItemStack item = ShopUtils.getItemFromName(itemName);
	if (item == null) {
	    sender.sendMessage(ChatColor.RED + itemNotFound);
	    sender.sendMessage(ChatColor.RED + listItems());
	    return;
	}
	
	giveItem(sender, target, item);
    }
    
    private void giveItem(CommandSender sender, Player player, ItemStack item) {
	if (player.getInventory().firstEmpty() == -1) {
	    player.sendMessage(ChatColor.RED + "You were given a " + ChatColor.BOLD + item.getItemMeta().getDisplayName() + ChatColor.RED + ", but you are out of space. Dropping item on ground.");
	    player.getWorld().dropItemNaturally(player.getLocation(), item);
	    sender.sendMessage(ChatColor.GREEN + "Gave " + ChatColor.LIGHT_PURPLE + player.getName() + " a " + ChatColor.BOLD + item.getItemMeta().getDisplayName());
	    return;
	}
	
	player.getInventory().addItem(item);
	player.sendMessage(ChatColor.GREEN + "You were given a " + ChatColor.BOLD + item.getItemMeta().getDisplayName() + ChatColor.GREEN + ".");
	sender.sendMessage(ChatColor.GREEN + "Gave " + ChatColor.LIGHT_PURPLE + player.getName() + " a " + ChatColor.BOLD + item.getItemMeta().getDisplayName());
    }
    
    private String listItems() {
	String itemList = ChatColor.YELLOW + "Available items: " + ChatColor.GRAY;
	HashMap<String, IShopItem> items = ShopUtils.getItems();
	for (String name : items.keySet()) {
	    itemList += name +", ";
	}
	return itemList;
    }

}
