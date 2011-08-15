package me.Perdog.LandMines;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockListener;

public class LandMinesBlockListener extends BlockListener {
	public static LandMines plugin; public LandMinesBlockListener (LandMines instance) {
		plugin = instance;
	}
	@SuppressWarnings("unused")
	private Block block;

}