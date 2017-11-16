package us.mattmarion.pyxeconomy.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.mattmarion.pyxeconomy.profile.Profile;
import us.mattmarion.pyxeconomy.utils.MessageUtils;

public class BalanceCommand implements CommandExecutor {

    private final String usage = ChatColor.RED + "/pyxcoin balance (name)";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if (args[0].equalsIgnoreCase("balance")) {
	    if (args.length == 1) {
		if (!(sender instanceof Player)) {
		    sender.sendMessage(ChatColor.RED + "Must be a player to check your own balance.");
		    return false;
		}
		
		Player player = (Player) sender;
		tellPlayerBalance(player, player, "own");
		return true;
	    }
	    
	    if (args.length == 2) {
		if (sender instanceof Player) {
		    Player player = (Player) sender;
		    if (player.getName().equalsIgnoreCase(args[1])) {
			tellPlayerBalance(player, player, "own");
			return true;
		    }
		}
		
		if (!sender.hasPermission("pyxcoin.balance.other")) {
		    sender.sendMessage(MessageUtils.NO_PERMISSION_MESSAGE);
		    return false;
		}
		
		Player target = Bukkit.getPlayerExact(args[1]);
		if (target == null || !target.isOnline()) {
		    sender.sendMessage(MessageUtils.playerNotFoundMessage(target.getName()));
		    return false;
		}
		
		tellPlayerBalance((Player) sender, target, "other");
		return true;
	    }
	    
	}
	return false;
    }
    
    private void tellPlayerBalance(Player player, Player targetBalance, String type) {
	if (!player.hasPermission("pyxcoin.balance." + type)) {
	    player.sendMessage(MessageUtils.NO_PERMISSION_MESSAGE);
	    return;
	}
	Profile profile = Profile.getByPlayer(targetBalance);
	player.sendMessage(ChatColor.GREEN + "Balance: " + ChatColor.GOLD + ChatColor.BOLD + profile.getBalance());
    }
    
}
