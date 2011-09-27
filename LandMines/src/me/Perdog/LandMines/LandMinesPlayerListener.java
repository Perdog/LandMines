package me.Perdog.LandMines;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
		int Int1 = LandMines.Int1;
		Action action = event.getAction();
		ItemStack held = player.getItemInHand();
		PlayerInventory inv = event.getPlayer().getInventory();
		Block block = event.getClickedBlock();
		event.getBlockFace();
		if (LandMines.plant.contains(player.getName())) {
			if (LandMines.worlds.contains(player.getWorld().getName()) || LandMines.worlds.isEmpty()) {
				if (action.equals(Action.RIGHT_CLICK_BLOCK) && held.getType().equals(Material.FLINT)) {
					if (inv.contains(Mat1, Int1)) {
						if (block.getRelative(BlockFace.DOWN).equals(Material.AIR)) {
							player.sendMessage("You can't place a mine above nothing!");
							if (block.getRelative(BlockFace.NORTH).equals(Material.AIR)){
								
							}
						}
						if (block.getRelative(BlockFace.DOWN).equals(Material.LAVA)) {
							player.sendMessage("You can't place a mine above lava!");
						}
						if (block.getRelative(BlockFace.DOWN).equals(Material.WATER)) {
							player.sendMessage("You can't place a mine above water!");
						}
						else {
							inv.getContents();
							int amount = inv.getItem(Mat1).getAmount() - Int1;
							inv.getItem(Mat1).setAmount(amount);
							player.playEffect(player.getLocation(), Effect.CLICK2, 200);
							Bukkit.broadcastMessage("Watch out! " + player.getName() + " just planted a mine!");
							player.sendMessage("It worked!");
							LandMines.plant.remove(player.getName());
							event.getClickedBlock().setType(Material.GRAVEL);
							LandMines.mine.add(block.getRelative(BlockFace.UP).getLocation());
							LandMines.unbreakable.add(block.getLocation());
						}
					}
					else {
						player.sendMessage("You do not have enough " + Mat1);
						player.sendMessage("You need at least " + Int1);
						return;
					}
				}
			}
		}
		if (player.hasPermission("Landmines.*") || player.hasPermission("Landmines.Defuse") || player.isOp()) {
			if (action.equals(Action.RIGHT_CLICK_BLOCK) && held.getType().equals(Material.SHEARS)) {
				if (LandMines.mine.contains(block.getRelative(BlockFace.UP).getLocation())) {
					if (LandMines.worlds.contains(player.getWorld().getName()) || LandMines.worlds.isEmpty()) {
						player.playEffect(player.getLocation(), Effect.CLICK1, 200);
						player.sendMessage("You successfully defused the mine! Whew!");
						event.getClickedBlock().setType(Material.GRASS);
						LandMines.mine.remove(block.getRelative(BlockFace.UP).getLocation());
						LandMines.unbreakable.remove(block.getLocation());
					}
				}
			}
		}
		if (player.getLocation().equals(LandMines.mine)) {
			player.playEffect(player.getLocation(), Effect.DOOR_TOGGLE, 200);
			player.sendMessage("*CLICK*");
		}
	}
}