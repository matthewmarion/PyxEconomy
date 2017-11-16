package us.mattmarion.pyxeconomy.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.mattmarion.pyxeconomy.profile.Profile;
import us.mattmarion.pyxeconomy.utils.MessageUtils;

public class ResetBalanceCommand implements CommandExecutor {
    
    private final String usage = ChatColor.RED + "/pyxcoin reset <name>";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if (args[0].equalsIgnoreCase("reset")) {
	    if (!sender.hasPermission("pyxcoin.reset")) {
		sender.sendMessage(MessageUtils.NO_PERMISSION_MESSAGE);
		}
		
	    if (args.length != 2) {
		sender.sendMessage(usage);
		return false;
	    }
	    
	    Player target = Bukkit.getPlayerExact(args[1]);
	    if (target == null || !target.isOnline()) {
		sender.sendMessage(MessageUtils.playerNotFoundMessage(target.getName()));
		return false;
	    }
	    
	    Profile profile = Profile.getByPlayer(target);
	    profile.setBalance(0);
	    profile.save();
	    
	    target.sendMessage(ChatColor.GREEN + "You're balance has been reset. You current coin balance is now: " + ChatColor.GOLD + ChatColor.BOLD + profile.getBalance());
	    
	    return true;
	}
	
	return false;
    }

}
