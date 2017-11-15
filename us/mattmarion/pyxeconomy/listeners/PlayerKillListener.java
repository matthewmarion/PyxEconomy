package us.mattmarion.pyxeconomy.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import us.mattmarion.pyxeconomy.profile.Profile;

public class PlayerKillListener implements Listener {
    
    @EventHandler
    public void on(PlayerDeathEvent event) {
	Player deadPlayer = event.getEntity();
	if (!(deadPlayer.getKiller() instanceof Player)) {
	    return;
	}
	Player killer = deadPlayer.getKiller();
	System.out.println("KILLER " + killer);
	Profile killerProfile = Profile.getByPlayer(killer);
	System.out.println("Killer profile: " + killerProfile.getPlayer().getName());
	killerProfile.setKillstreak(killerProfile.getKillstreak() + 1);
	killer.sendMessage(ChatColor.GREEN + "You killed " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + deadPlayer.getName() + ". "+ ChatColor.GREEN + "You are now on a " + ChatColor.GOLD + killerProfile.getKillstreak() + " killstreak.");
	giveCoins(killerProfile);
	    
	resetKillstreak(deadPlayer);
	deadPlayer.sendMessage(ChatColor.RED + "You died to " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + killer.getName() + ChatColor.RED + " and your killstreak is now " + ChatColor.GOLD + "" + ChatColor.BOLD + 0);
    }
    
    private void resetKillstreak(Player deadPlayer) {
	Profile deadProfile = Profile.getByPlayer(deadPlayer);
	deadProfile.setKillstreak(0);
    }
    
    private void giveCoins(Profile profile) {
	double balance = profile.getBalance();
	int killstreak = profile.getKillstreak();
	checkMultiplier(profile);
	double multiplier = profile.getMultiplier();
	System.out.println("Multiplier here is: " + multiplier);
	if (killstreak % 10 == 0) {
	    profile.setBalance(balance + (4 * multiplier));
	    profile.getPlayer().sendMessage(ChatColor.GREEN + "You earned " + ChatColor.GOLD + 4 * multiplier + " coins");
	} else {
	    profile.setBalance(balance + (1 * multiplier));
	    profile.getPlayer().sendMessage(ChatColor.GREEN + "You earned " + ChatColor.GOLD + 4 * multiplier + " coins");
	    System.out.println(multiplier);
	}
    }
    
    private void checkMultiplier(Profile profile) {
	Player player = profile.getPlayer();
	for (double i = 1.0; i < 9.0; i+= .5) {
	    if (player.hasPermission("pyxeco." + i)) {
		profile.setMultiplier(i);
	    } else {
		profile.setMultiplier(1);
	    }
	}
    }
}
