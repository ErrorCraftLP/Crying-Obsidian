package de.errorcraftlp.cryingobsidian.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class Utils {
	// Mod ID
	public static final String ID = "cryingobsidian";

	// Util method for setting the spawn point at the player's location
	public static void setSpawnPointAtPlayer(final World world, final PlayerEntity player) {
		if(!world.isRemote) {
			final BlockPos playerPos = player.getPosition();

			if(player.isInWater()) {
				final BlockPos correctedPos = new BlockPos(playerPos.getX(), world.getSeaLevel(), playerPos.getZ());
				player.setSpawnPoint(correctedPos, true, player.dimension);
			} else {
				player.setSpawnPoint(playerPos, true, player.dimension);
			}

			player.sendMessage(new TranslationTextComponent("message.spawnpoint_set", player.getDisplayName(), playerPos.getX(), playerPos.getY(), playerPos.getZ()));
		}
	}

	// Util method for setting the spawn point at the block's location
	public static void setSpawnPointAtBlock(final World world, final PlayerEntity player, final BlockPos pos) {
		if(!world.isRemote) {
			BlockPos correctedPos1 = pos.up();
			BlockPos correctedPos2 = pos.up(2);
			BlockPos correctedPos3 = pos.down();
			BlockState stateAtCorrectedPos1 = world.getBlockState(correctedPos1);
			BlockState stateAtCorrectedPos2 = world.getBlockState(correctedPos2);
			BlockState stateAtCorrectedPos3 = world.getBlockState(correctedPos3);

			// If there isn't enough place for the spawn point, try it one block higher
			while(hasNoPlace(correctedPos1, stateAtCorrectedPos1, world) || hasNoPlace(correctedPos2, stateAtCorrectedPos2, world) || isDangerous(stateAtCorrectedPos3)) {
				correctedPos1 = correctedPos1.up();
				correctedPos2 = correctedPos1.up();
				correctedPos3 = correctedPos1.down();
				stateAtCorrectedPos1 = world.getBlockState(correctedPos1);
				stateAtCorrectedPos2 = world.getBlockState(correctedPos2);
				stateAtCorrectedPos3 = world.getBlockState(correctedPos3);
				continue;
			}

			player.setSpawnPoint(correctedPos1, true, player.dimension);
			player.sendMessage(new TranslationTextComponent("message.spawnpoint_set", player.getDisplayName(), correctedPos1.getX(), correctedPos1.getY(), correctedPos1.getZ()));
		}
	}

	// Util method that checks if there is enough place for a spawn point
	public static boolean hasNoPlace(final BlockPos pos, final BlockState state, final World world) {
		final Block block = state.getBlock();
		return !block.isAir(state, world, pos);
	}

	// Util method that checks if the block is dangerous (lava, fire)
	public static boolean isDangerous(final BlockState state) {
		final Block block = state.getBlock();
		return block.equals(Blocks.LAVA) || block.equals(Blocks.FIRE);
	}
}
