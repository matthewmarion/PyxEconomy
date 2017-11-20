package us.mattmarion.pyxeconomy.commands.balance;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.mattmarion.pyxeconomy.commands.PyxCommandExecutor;
import us.mattmarion.pyxeconomy.profile.Profile;
import us.mattmarion.pyxeconomy.utils.MessageUtils;

public class RemoveBalanceCommand extends PyxCommandExecutor {
    
    public RemoveBalanceCommand() {
	setSubCommand("remove");
	setPermission("pyxcoin.remove");
	setUsage("/pyxcoin remove <player> <amount>");
	setBoth();
	setLength(3);
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
	Player target = Bukkit.getPlayerExact(args[1]);
	if (target == null || !target.isOnline()) {
	    sender.sendMessage(MessageUtils.playerNotFoundMessage(target.getName()));
	    return;
	}

	try {
	    double amount = Double.parseDouble(args[2]);
	    Profile targetProfile = Profile.getByPlayer(target);
	    targetProfile.removeBalance(amount);

	    targetProfile.save();
	    target.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + amount + ChatColor.GREEN + " coins have been removed from your balance! Your current coin balance is now: " + ChatColor.GOLD + ChatColor.BOLD + targetProfile.getBalance());
	} catch (NumberFormatException e) {
	    sender.sendMessage(ChatColor.RED + "Amount must be numeric.");
	}
    }

}
