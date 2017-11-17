package us.mattmarion.pyxeconomy.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.mattmarion.pyxeconomy.profile.Profile;
import us.mattmarion.pyxeconomy.utils.MessageUtils;

public class ResetBalanceCommand extends PyxCommandExecutor {
    
    public ResetBalanceCommand() {
	setSubCommand("reset");
	setPermission("pyxcoin.reset");
	setUsage("/pyxcoin reset <name>");
	setBoth();
	setLength(2);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
	Player target = Bukkit.getPlayerExact(args[1]);
	if (target == null || !target.isOnline()) {
	    sender.sendMessage(MessageUtils.playerNotFoundMessage(target.getName()));
	    return;
	}
	
	Profile profile = Profile.getByPlayer(target);
	profile.setBalance(0);
	profile.save();
	    
	target.sendMessage(ChatColor.GREEN + "You're balance has been reset. Your current coin balance is now: " + ChatColor.GOLD + ChatColor.BOLD + profile.getBalance());
    }

}
