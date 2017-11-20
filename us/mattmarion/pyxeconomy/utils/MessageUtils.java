package us.mattmarion.pyxeconomy.utils;

import org.bukkit.ChatColor;

public class MessageUtils {
    
    public static final String NO_PERMISSION_MESSAGE = ChatColor.RED + "You do not have permission to perform this action.";
    public static final String ONLY_CONSOLE_MESSAGE = ChatColor.RED + "This command can only be ran by the console.";
    public static final String ONLY_PLAYER_MESSAGE = ChatColor.RED + "This command can only be ran by a player.";
    public static String playerNotFoundMessage(String name) {
	return ChatColor.RED + name + " could not be found.";
    }

}
