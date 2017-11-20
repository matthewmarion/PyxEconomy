package us.mattmarion.pyxeconomy.commands.balance;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.mattmarion.pyxeconomy.commands.PyxCommandExecutor;
import us.mattmarion.pyxeconomy.profile.Profile;
import us.mattmarion.pyxeconomy.utils.MessageUtils;

public class BalanceCommand extends PyxCommandExecutor {

    public BalanceCommand() {
	setSubCommand("balance");
	setPermission("pyxcoin.balance");
	setUsage("/pyxcoin balance (name)");
	setPlayer(true);
	setLength(1);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
	if (args.length == 1) {
	    Player player = (Player) sender;
	    Profile profile = Profile.getByPlayer(player);
	    sender.sendMessage(ChatColor.GREEN + "Balance: " + ChatColor.GOLD + ChatColor.BOLD + profile.getBalance());
	} else {
	    Player target = Bukkit.getPlayerExact(args[1]);
	    if (target == null || !target.isOnline()) {
		sender.sendMessage(MessageUtils.playerNotFoundMessage(args[1]));
		return;
	    }
	    Profile profile = Profile.getByPlayer(target);
	    sender.sendMessage(ChatColor.GREEN + "Balance: " + ChatColor.GOLD + ChatColor.BOLD + profile.getBalance());
	}

    }
}
