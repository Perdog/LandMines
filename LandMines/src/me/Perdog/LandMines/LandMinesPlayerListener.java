package me.Perdog.LandMines;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
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
		int Exp = LandMines.Exp;
		int minemat = LandMines.minemat;
		int defmat = LandMines.defmat;
		int heldmat = LandMines.heldmat;
		int dur = LandMines.dur;
		ArrayList<String> plant = LandMines.plant;
		ArrayList<Location> mine = LandMines.mine;
		List<String> worlds = LandMines.worlds;
		Block block = event.getClickedBlock();
		Action action = event.getAction();
		ItemStack held = player.getItemInHand();
		PlayerInventory inv = event.getPlayer().getInventory();
		if (plant.contains(player.getName())) {
			if (action == Action.RIGHT_CLICK_BLOCK && held.getTypeId() == heldmat) {
				if (worlds.contains(player.getWorld().getName()) || worlds.isEmpty()) {
					if (inv.contains(Mat1, Int1)) {
						if (mine.contains(block.getLocation())) {
							player.sendMessage("There is already a mine planted there.");
						}
						else {
							block.setTypeId(minemat);
							held.setDurability((short) (held.getDurability() - dur));
							player.playEffect(player.getLocation(), Effect.CLICK2, 200);
							player.sendMessage(ChatColor.BLUE + "It worked!");
							Bukkit.broadcastMessage(ChatColor.GREEN + "Watch out! " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + " just planted a mine!");
							plant.remove(player.getName());
							mine.add(block.getLocation());
						}
					}
					else {
						player.sendMessage("You do not have enough " + ChatColor.LIGHT_PURPLE +  Mat1);
						player.sendMessage("You need at least " + ChatColor.LIGHT_PURPLE + Int1);
						return;
					}
				}
				else {
					player.sendMessage("You must be in one of the following worlds to plant a mine: " + ChatColor.DARK_GREEN + worlds);
				}
			}
		}
		if (player.hasPermission("Landmines.*") || player.hasPermission("Landmines.Defuse") || player.isOp()) {
			if (action == Action.RIGHT_CLICK_BLOCK && held.getType() == Material.SHEARS) {
				if (mine.contains(block.getLocation())) {
					if (worlds.contains(player.getWorld().getName()) || worlds.isEmpty()) {
						if (held.getDurability() >= Material.SHEARS.getMaxDurability()) {
							inv.remove(Material.SHEARS);
						}
						else {
							held.setDurability((short)(held.getDurability() + dur));
							block.setTypeId(defmat);
							player.setExperience(player.getExperience() + Exp);
							player.playEffect(player.getLocation(), Effect.CLICK1, 200);
							player.sendMessage(ChatColor.GREEN + "You successfully defused the mine! Whew!");
							mine.remove(block.getLocation());
						}
					}
				}
			}
		}
	}
	public void onPlayerMove (PlayerMoveEvent event) {
		Player player = event.getPlayer();
		int Tnt = LandMines.Tnt;
		ArrayList<Location> mine = LandMines.mine;
		for (Location Mloc : mine) {
			Location Ploc = player.getLocation();
			if (Ploc.getBlockX() == Mloc.getX() && Ploc.getBlockZ() == Mloc.getZ() && Ploc.getBlockY() == Mloc.getY()+1) {
				Mloc.getWorld().createExplosion(Ploc, Tnt);
				mine.remove(Mloc);
			}
		}
	}

}