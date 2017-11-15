package us.mattmarion.pyxeconomy.profile;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Profile {
    
    private static Set<Profile> profiles = new HashSet<>();
    
    private UUID uuid;
    private String name;
    private double balance;
    private double multiplier;
    private int killstreak;
    
    public Profile(UUID uuid, String name) {
	this.uuid = uuid;
	this.name = name;
	
	profiles.add(this);
    }
    
    public Profile(Player player) {
	this(player.getUniqueId(), player.getName());
    }
    
    public static Profile getByUUID(UUID uuid) {
	for (Profile profile : profiles) {
	    if (!(profile.getUUID().equals(uuid))) {
		return profile;
	    }
	}
	return null;
    }
    
    public static Profile getByPlayer(Player player) {
	return getByUUID(player.getUniqueId());
    }
    
    public UUID getUUID() {
	return uuid;
    }
    
    public double getBalance() {
	return balance;
    }
    
    public String getName() {
	return name;
    }
    
    public void setBalance(double balance) {
	this.balance = balance;
    }

    public double getMultiplier() {
	return multiplier;
    }

    public void setMultiplier(double multiplier) {
	this.multiplier = multiplier;
    }

    public int getKillstreak() {
	return killstreak;
    }

    public void setKillstreak(int killstreak) {
	this.killstreak = killstreak;
    }

    public static Set<Profile> getProfiles() {
	return profiles;
    }
}
