package me.Perdog.LandMines;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class LandMines extends JavaPlugin {
	private final LandMinesPlayerListener playerListener = new LandMinesPlayerListener (this);
	public static ArrayList<String> plant = new ArrayList<String>();
	public static ArrayList<Location> mine = new ArrayList<Location>();
	public String name;
	public String version;
	public static int Mat1;
	public static Integer Int1;
	public Configuration config;
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
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
		config = getConfiguration();
		config.load();
		Mat1 = config.getInt("Material required", Material.IRON_INGOT.getId());
		Int1 = config.getInt("Amount required", 3);
		config.save();
	}
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] split) {
		Player player = (Player) sender;
		if (!(sender instanceof Player)) {
			return false;
		}
		if (cmd.getName().equalsIgnoreCase("landmines") || (cmd.getName().equalsIgnoreCase("lm"))) {
			if (split.length==1) {
				if (split[0].equalsIgnoreCase("help") || (split[0].equalsIgnoreCase("?"))) {
					player.sendMessage("Try:");
					player.sendMessage("/plant - plant a land mine");
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("plant")) {
			if (player.hasPermission("Landmines.*") || (player.hasPermission("Landmines.Plant"))) {
				player.sendMessage("You may now plant a land mine");
				LandMines.plant.add(player.getName());
				return true;
			}
			else {
				player.sendMessage("You don't have permission to do that!");
			}
		}
		return false;
	}
}