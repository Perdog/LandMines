package me.Perdog.LandMines;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DefuseCommand implements CommandExecutor {
	private Player player;
	public static LandMines plugin; public DefuseCommand (LandMines instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("defuse") || (cmd.getName().equalsIgnoreCase("d"))) {
				player.sendMessage("You defused the mine, Phew!");
				LandMines.Defuse.add(player.getName());
				return true;
				}
		return false;
		}
	}
