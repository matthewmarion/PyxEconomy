package us.mattmarion.pyxeconomy.listeners;

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
	if (Profile.getByPlayer(killer) != null) {
	    Profile killerProfile = Profile.getByPlayer(killer);
	    killerProfile.setKillstreak(killerProfile.getKillstreak() + 1);
	    giveCoins(killerProfile);
	} else {
	    Profile killerProfile = new Profile(killer);
	    killerProfile.setKillstreak(killerProfile.getKillstreak() + 1);
	    giveCoins(killerProfile);
	}
	resetKillstreak(deadPlayer);
    }
    
    private void resetKillstreak(Player deadPlayer) {
	if (Profile.getByPlayer(deadPlayer) != null) {
	    Profile deadProfile = Profile.getByPlayer(deadPlayer);
	    deadProfile.setKillstreak(0);
	} else {
	    Profile deadProfile = new Profile(deadPlayer);
	    deadProfile.setKillstreak(0);
	}
    }
    
    private void giveCoins(Profile profile) {
	double balance = profile.getBalance();
	int killstreak = profile.getKillstreak();
	double multiplier = profile.getMultiplier();
	
	if (killstreak % 10 == 0) {
	    profile.setBalance(balance + (1 * multiplier) + 3);
	} else {
	    profile.setBalance(balance + (1 * multiplier));
	}
    }

}
