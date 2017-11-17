package us.mattmarion.pyxeconomy.commands;

import org.bukkit.command.CommandSender;

public abstract class PyxCommandExecutor {
    
    private String subCommand;
    private String permission;
    private String usage;
    private boolean console;
    private boolean player;
    private int length;
    
    public abstract void execute(CommandSender sender, String[] args);
    
    public void setBoth() {
	this.player = true;
	this.console = true;
    }
    
    public boolean isBoth() {
	return player && console;
    }
    
    public String getSubCommand() {
	return subCommand;
    }
    
    public void setSubCommand(String command) {
	this.subCommand = command;
    }
    
    public String getPermission() {
	return permission;
    }
    
    public void setPermission(String permission) {
	this.permission = permission;
    }
    
    public boolean isConsole() {
	return console;
    }
    
    public void setConsole(boolean console) {
	this.console = console;
    }
    
    public boolean isPlayer() {
	return player;
    }
    
    public void setPlayer(boolean player) {
	this.player = player;
    }
    
    public int getLength() {
	return length;
    }
    
    public void setLength(int length) {
	this.length = length;
    }

    public String getUsage() {
	return usage;
    }

    public void setUsage(String usage) {
	this.usage = usage;
    }
}
