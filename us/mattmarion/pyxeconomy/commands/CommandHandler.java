package us.mattmarion.pyxeconomy.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import us.mattmarion.pyxeconomy.commands.balance.BalanceCommand;
import us.mattmarion.pyxeconomy.commands.balance.GiveBalanceCommand;
import us.mattmarion.pyxeconomy.commands.balance.RemoveBalanceCommand;
import us.mattmarion.pyxeconomy.commands.balance.ResetBalanceCommand;
import us.mattmarion.pyxeconomy.commands.shop.AddItemCommand;
import us.mattmarion.pyxeconomy.commands.shop.CreateShopCommand;
import us.mattmarion.pyxeconomy.utils.MessageUtils;

public class CommandHandler implements CommandExecutor {

    private HashMap<String, PyxCommandExecutor> commands = new HashMap<String, PyxCommandExecutor>();

    public CommandHandler() {
	commands.put("reset", new ResetBalanceCommand());
	commands.put("give", new GiveBalanceCommand());
	commands.put("remove", new RemoveBalanceCommand());
	commands.put("balance", new BalanceCommand());
	commands.put("shop", new CreateShopCommand());
	commands.put("add", new AddItemCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if (cmd.getName().equalsIgnoreCase("pyx")) {
	    String name = args[0].toLowerCase();
	    if (commands.containsKey(name)) {
		final PyxCommandExecutor command = commands.get(name);

		if (command.getPermission() != null && !sender.hasPermission(command.getPermission())) {
		    sender.sendMessage(MessageUtils.NO_PERMISSION_MESSAGE);
		    return false;
		}

		if (!command.isBoth()) {
		    if (command.isConsole() && sender instanceof Player) {
			sender.sendMessage(MessageUtils.ONLY_CONSOLE_MESSAGE);
			return false;
		    }

		    if (command.isPlayer() && sender instanceof ConsoleCommandSender) {
			sender.sendMessage(MessageUtils.ONLY_PLAYER_MESSAGE);
			return false;
		    }
		}

		if (command.getLength() > args.length) {
		    sender.sendMessage(ChatColor.RED + command.getUsage());
		    return false;
		}
		command.execute(sender, args);
	    }
	    return false;
	}
	
	
	

	return false;
    }
}
