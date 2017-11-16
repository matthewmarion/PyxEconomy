package us.mattmarion.pyxeconomy.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.mattmarion.pyxeconomy.profile.Profile;

public class GiveBalanceCommand implements CommandExecutor {

    private final String usage = ChatColor.RED + "/pyxcoin give <name> <amount>";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if (args.length != 3) {
	    sender.sendMessage(usage);
	    return false;
	}

	if (args[0].equalsIgnoreCase("give")) {
	    Player target = Bukkit.getPlayerExact(args[1]);
	    if (target == null || !target.isOnline()) {
		sender.sendMessage(ChatColor.RED + target.getName() + " could not be found.");
		return false;
	    }
	    
	    try {
		double amount = Double.parseDouble(args[2]);
		Profile targetProfile = Profile.getByPlayer(target);
		targetProfile.addBalance(amount);
		
		targetProfile.save();
		target.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + amount + ChatColor.GREEN + " coins have been added to your balance!");
		
		return true;
	    } catch (NumberFormatException e) {
		sender.sendMessage(ChatColor.RED + "Amount must be numeric.");
		return false;
	    }
	}
	return false;
    }
}
