package us.mattmarion.pyxeconomy.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.mattmarion.pyxeconomy.profile.Profile;
import us.mattmarion.pyxeconomy.utils.MessageUtils;

public class RemoveBalanceCommand implements CommandExecutor {
    
    private final String usage = ChatColor.RED + "/pyxcoin remove <name> <amount>";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if (args[0].equalsIgnoreCase("remove")) {
	    if (!sender.hasPermission("pyxcoin.remove")) {
		sender.sendMessage(MessageUtils.NO_PERMISSION_MESSAGE);
	    }
		
	    if (args.length != 3) {
		sender.sendMessage(usage);
		return false;
	    }
	    
	    Player target = Bukkit.getPlayerExact(args[1]);
	    if (target == null || !target.isOnline()) {
		sender.sendMessage(MessageUtils.playerNotFoundMessage(target.getName()));
		return false;
	    }
	    
	    try {
		double amount = Double.parseDouble(args[2]);
		Profile targetProfile = Profile.getByPlayer(target);
		targetProfile.removeBalance(amount);
		
		targetProfile.save();
		target.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + amount + ChatColor.GREEN + " coins have been removed from your balance!");
		
		return true;
	    } catch (NumberFormatException e) {
		sender.sendMessage(ChatColor.RED + "Amount must be numeric.");
		return false;
	    }
	}
	return false;
    }

}
