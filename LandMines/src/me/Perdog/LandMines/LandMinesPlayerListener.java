package me.Perdog.LandMines;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class LandMinesPlayerListener extends PlayerListener{
	private Player player;
	public static LandMines plugin; public LandMinesPlayerListener (LandMines instance) {
		plugin = instance;
	}
	public void onPlayerInteract (PlayerInteractEvent event) {
		if (LandMines.Plant.contains(player.getName())) {
			if (player.getItemInHand().getType() == Material.FLINT); {
				if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				player.sendMessage("It worked");
				LandMines.Plant.remove(player.getName());
				}
			}
		}
	}
}
