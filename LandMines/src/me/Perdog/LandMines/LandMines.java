package me.Perdog.LandMines;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class LandMines extends JavaPlugin {
	public static ArrayList<String> plant = new ArrayList<String>();
	public static ArrayList<Location> mine = new ArrayList<Location>();
	public static List<String> worlds;
	public static ArrayList<String> author;
	public static String name;
	public static int Mat1;
	public static int Int1;
	public static int Exp;
	public static Set<Location> mines;
	public Configuration config;
	Logger log = Logger.getLogger("Minecraft");
	@Override
	public void onDisable() {
		log.info(name + " has been Disabled!");
		mine.clear();
		plant.clear();
	}
	@Override
	public void onEnable() {
		author = getDescription().getAuthors();
		name = getDescription().getFullName();
		log.info(name + " by: " + author + " has been Enabled!");
		registerListeners();
		config = getConfiguration();
		config.load();
		config.getInt("Material required", Mat1 = 265);
		config.getInt("Amount required", Int1 = 3);
		worlds = config.getStringList("Worlds", null);
		config.setProperty("Worlds", worlds.toArray(new String[0]));
		config.save();
	}
	private void registerListeners() {
		PluginManager pm = getServer().getPluginManager();
		final LandMinesPlayerListener playerListener = new LandMinesPlayerListener (this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
	}
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] split) {
		Player player = (Player) sender;
		if (!(sender instanceof Player)) {
			return false;
		}
		if (cmd.getName().equalsIgnoreCase("landmines") || (cmd.getName().equalsIgnoreCase("lm"))) {
			if (split.length == 1) {
				if (split[0].equalsIgnoreCase("help") || (split[0].equalsIgnoreCase("?"))) {
					player.sendMessage("Try:");
					player.sendMessage("/plant - plant a land mine");
					return true;
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("plant")) {
			if (player.hasPermission("Landmines.*") || (player.hasPermission("Landmines.Plant")) || (player.isOp())) {
				if (worlds.contains(player.getWorld().getName()) || worlds.isEmpty()) {
					player.sendMessage(ChatColor.GREEN + "You may now plant a land mine!");
					LandMines.plant.add(player.getName());
					return true;
				}
				else {
					player.sendMessage(ChatColor.RED + "You cannot use LandMines in this world.");
					player.sendMessage(ChatColor.RED + "You may use them in the following worlds only");
					player.sendMessage(ChatColor.GREEN + "-" + LandMines.worlds);
				}
			}
			else {
				player.sendMessage(ChatColor.RED + "You don't have permission to plant LandMines!");
			}
		}
		return false;
	}

}