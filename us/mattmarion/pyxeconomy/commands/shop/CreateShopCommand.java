package us.mattmarion.pyxeconomy.commands.shop;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.mattmarion.pyxeconomy.commands.PyxCommandExecutor;
import us.mattmarion.pyxeconomy.shop.Shop;

public class CreateShopCommand extends PyxCommandExecutor {
    
    public CreateShopCommand() {
	setSubCommand("shop");
	setPermission("pyxcoin.shop.create");
	setUsage("/pyx shop create <name>");
	setPlayer(true);
	setLength(3);
    }

    private final String locationExists = ChatColor.RED + "This shop name has already been used!";
    
    @Override
    public void execute(CommandSender sender, String[] args) {
	Player player = (Player) sender;
	if (!args[1].equalsIgnoreCase("create")) {
	    player.sendMessage(ChatColor.RED + getUsage());
	    return;
	}
	
	String name = args[2];
	if (Shop.locationExists(name)) {
	    player.sendMessage(locationExists);
	    return;
	}
	
	Shop shop = new Shop(name, player.getLocation());
	shop.create();
	player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + name + ChatColor.GREEN + " shop has been created.");
	shop.save();
	System.out.println("Successfully saved shop: " + name + " at " + shop.getLocation());
    }

}
