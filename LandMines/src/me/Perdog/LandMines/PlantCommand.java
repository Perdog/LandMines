package me.Perdog.LandMines;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlantCommand implements CommandExecutor {
	private Player player;
	public static LandMines plugin; public PlantCommand (LandMines instance) {
	plugin = instance;
	}
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
		player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("plant") || (cmd.getName().equalsIgnoreCase("p"))) {
			player.sendMessage("You may now plant a land mine");
			LandMines.Plant.add(player.getName());
			return true;
			}
		return false;
		}
	}
