package me.Perdog.LandMines;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LandMines extends JavaPlugin {
	private final LandMinesPlayerListener playerListener = new LandMinesPlayerListener (this);
	public static ArrayList<String> Plant = new ArrayList<String>();
	public static ArrayList<String> Defuse = new ArrayList<String>();
	public String name;
	public String version;
	private Player player;
	Logger log = Logger.getLogger("Minecraft");
	@Override
	public void onDisable() {
		log.info(name + " version: " + version + " by Perdog has been Disabled!");
		
	}
	@Override
	public void onEnable() {
		name = getDescription().getName();
		version = getDescription().getVersion();
		log.info(name + " version: " + version + " by Perdog has been Enabled!");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_ITEM_HELD, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("landmines")); {
			if (args[0].equalsIgnoreCase("help") || (args[0].equalsIgnoreCase("?"))); {
				player.sendMessage("Use these commands:");
				player.sendMessage("/plant | /p - plant a land mine");
				player.sendMessage("/defuse | /d - defuse an active land mine.");
				return true;
			}
		}
	}
}
	


