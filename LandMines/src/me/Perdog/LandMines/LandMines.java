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
import org.bukkit.event.Event.Priority;
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
	public static int minemat;
	public static int defmat;
	public static int heldmat;
	public static int Tnt;
	public static int dur;
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
		config.setHeader("How to setup Config",
						"# Material required: Data value of the material that players need in their inventory (Default iron ignots)",
						"# Amount required: How many of the above item they need",
						"# Held material: Data value of the material in hand to plant mines (Default flint)",
						"# Experience earned: How much experience the player gets for defusing a mine",
						"# Mine material: Data value of what the block turns into when it becomes a mine (Default gravel)",
						"# Defuse material: Data value of what the block turns into what it's defused (Default dirt)",
						"# Explosion size: Size of explosion, 1=1 tnt, 7=7 tnt, ect. Recommend not setting too high, and 1 also leaves players alive if they have full health",
						"# Shear durability: Durability to take from the shears when mines are defused",
						"# Worlds: Worlds that mines are allowed to be used on, leave empty for all worlds");
		config.getInt("Material required", Mat1 = 265);
		config.getInt("Amount required", Int1 = 3);
		config.getInt("Held material", heldmat = 318);
		config.getInt("Experience earned", Exp = 5);
		config.getInt("Mine material", minemat = 13);
		config.getInt("Defuse material", defmat = 3);
		config.getInt("Explosion size", Tnt = 1);
		config.getInt("Shear durability", dur = 10);
		worlds = config.getStringList("Worlds", null);
		config.setProperty("Worlds", worlds.toArray(new String[0]));
		config.save();
	}
	private void registerListeners() {
		PluginManager pm = getServer().getPluginManager();
		final LandMinesPlayerListener playerListener = new LandMinesPlayerListener (this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Priority.Normal, this);
	}
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] split) {
		Player player = (Player) sender;
		if (!(sender instanceof Player)) {
			return false;
		}
		if (cmd.getName().equalsIgnoreCase("landmines") || (cmd.getName().equalsIgnoreCase("lm"))) {
			if (split.length == 1) {
				if (split[0].equalsIgnoreCase("help") || (split[0].equalsIgnoreCase("?"))) {
					player.sendMessage("To plant a Land Mine you need ");
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