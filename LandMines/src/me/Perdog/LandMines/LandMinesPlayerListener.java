package me.Perdog.LandMines;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class LandMinesPlayerListener extends PlayerListener {
	public static LandMines plugin;
	public LandMinesPlayerListener (LandMines instance) {
		plugin = instance;
	}
	public void onPlayerInteract (PlayerInteractEvent event) {
		Player player = event.getPlayer();
		int Mat1 = LandMines.Mat1;
		Integer Int1 = LandMines.Int1;
		Action action = event.getAction();
		ItemStack held = player.getItemInHand();
		if (LandMines.plant.contains(player.getName())) {
			if (action == Action.RIGHT_CLICK_BLOCK && held.getType() == Material.FLINT) {
				PlayerInventory inv = event.getPlayer().getInventory();
				if (inv.contains(Mat1, Int1)) {
					player.sendMessage("It worked!");
					LandMines.plant.remove(player.getName());
					event.getClickedBlock().setType(Material.TNT);
					LandMines.mine.add(event.getClickedBlock().getLocation());
				}
				else {
					player.sendMessage("You do not have enough " + Mat1);
					player.sendMessage("You need at least " + Int1);
					return;
				}
			}
			else {
				player.sendMessage("You must right click the desired block with flint!");
				return;
			}
		}
		if (player.hasPermission("Landmines.*") || player.hasPermission("Landmines.Defuse") || player.isOp()) {
			if (action == Action.RIGHT_CLICK_BLOCK && held.getType() == Material.SHEARS) {
				if (LandMines.mine.contains(event.getClickedBlock().getLocation())) {
					BlockFace face = BlockFace.DOWN;
					event.getClickedBlock().setType(event.getClickedBlock().getRelative(face).getType());
					LandMines.mine.remove(event.getClickedBlock().getLocation());
				}
			}
		}
	}
}