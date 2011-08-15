package me.Perdog.LandMines;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerListener;

public class LandMinesPlayerListener extends PlayerListener{
	private Player player;
	public static LandMines plugin; public LandMinesPlayerListener (LandMines instance) {
		plugin = instance;
	}
	public void onAction (Action event) {
		if (LandMines.Plant.contains(player.getName())) {
			player.getItemInHand().getType();
			if (Material.FLINT != null) {
				if (Action.RIGHT_CLICK_BLOCK != null) {
				player.sendMessage("It worked");
				LandMines.Plant.remove(player.getName());
				}
			}
		}
	}
}
