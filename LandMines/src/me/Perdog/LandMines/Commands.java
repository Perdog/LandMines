package me.Perdog.LandMines;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	@SuppressWarnings("unused")
	private Player player;
	public static LandMines plugin; public Commands (LandMines instance) {
	plugin = instance;
	
	}
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	
}