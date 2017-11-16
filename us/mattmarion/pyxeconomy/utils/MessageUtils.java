package us.mattmarion.pyxeconomy.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtils {
    
    public static final String NO_PERMISSION_MESSAGE = ChatColor.RED + "You do not have permission to perform this action.";
    public static String playerNotFoundMessage(String name) {
	return ChatColor.RED + name + " could not be found.";
    }

}
