package us.mattmarion.pyxeconomy.profile;

import java.util.UUID;

import org.bukkit.entity.Player;

public class Profile {
    
    private UUID uuid;
    private String name;
    private double balance;
    
    public Profile(UUID uuid, String name, double balance) {
	this.uuid = uuid;
	this.name = name;
	this.balance = balance;
    }
    
    public Profile(Player player) {
	this(player.getUniqueId(), player.getName(), 0);
    }
    
    public UUID getUUID() {
	return uuid;
    }
    
    public String getName() {
	return name;
    }
    
    public void setBalance(double balance) {
	this.balance = balance;
    }
    
    public double getBalance() {
	return balance;
    }

}
