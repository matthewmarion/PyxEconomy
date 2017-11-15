package us.mattmarion.pyxeconomy.profile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

import us.mattmarion.pyxeconomy.PyxEconomy;

public class Profile {
    
    private static Set<Profile> profiles = new HashSet<>();
    
    private UUID uuid;
    private Player player;
    private double balance;
    private double multiplier;
    private int killstreak;
    
    public Profile(UUID uuid, Player player) {
	this.uuid = uuid;
	this.player = player;
	
	profiles.add(this);
    }
    
    public Profile(Player player) {
	this(player.getUniqueId(), player);
    }
    
    public static Profile getByUUID(UUID uuid) {
	for (Profile profile : profiles) {
	    if (profile.getUUID().equals(uuid)) {
		return profile;
	    }
	}
	return null;
    }
    
    public static Profile getByPlayer(Player player) {
	return getByUUID(player.getUniqueId());
    }
    
    public void save() {
	PyxEconomy.data.set(uuid + ".name", player.getName());
	PyxEconomy.data.set(uuid + ".balance", balance);
	PyxEconomy.data.set(uuid + ".killstreak", killstreak);
	
	try {
	    PyxEconomy.data.save(PyxEconomy.dataf);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public UUID getUUID() {
	return uuid;
    }
    
    public void setBalance(double balance) {
	this.balance = balance;
    }
    
    public double getBalance() {
	return balance;
    }
    
    public void addBalance(double balance) {
	this.balance += balance;
    }
    
    public Player getPlayer() {
	return player;
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
