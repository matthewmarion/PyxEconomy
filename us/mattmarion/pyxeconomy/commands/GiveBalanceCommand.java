package us.mattmarion.pyxeconomy.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.mattmarion.pyxeconomy.profile.Profile;
import us.mattmarion.pyxeconomy.utils.MessageUtils;

public class GiveBalanceCommand extends PyxCommandExecutor {

    public GiveBalanceCommand() {
	setSubCommand("give");
	setPermission("pyxcoin.give");
	setUsage("/pyxcoin give <name> <amount>");
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
	    targetProfile.addBalance(amount);

	    targetProfile.save();
	    target.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + amount + ChatColor.GREEN + " coins have been added to your balance! Your current coin balance is now: " + ChatColor.GOLD + ChatColor.BOLD + targetProfile.getBalance());
	} catch (NumberFormatException e) {
	    sender.sendMessage(ChatColor.RED + "Amount must be numeric.");
	}
    }
}
