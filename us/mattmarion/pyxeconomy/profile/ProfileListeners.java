package us.mattmarion.pyxeconomy.profile;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ProfileListeners implements Listener {
    
    @EventHandler
    public void on(PlayerJoinEvent event) {
	new Profile(event.getPlayer());
    }
    
    @EventHandler
    public void on(PlayerQuitEvent event) {
	Profile quitProfile = Profile.getByPlayer(event.getPlayer());
	if (quitProfile == null) {
	    return;
	}
	Profile.getProfiles().remove(quitProfile);
    }

}
