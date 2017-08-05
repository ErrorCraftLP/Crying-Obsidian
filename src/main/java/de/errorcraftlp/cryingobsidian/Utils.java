package de.errorcraftlp.cryingobsidian;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class Utils {

	// Constants
	public static final String ID = "cryingobsidian";
	public static final String NAME = "Crying Obsidian";
	public static final String VERSION = "GRADLE_VERSION"; // This is set by the buildscript
	public static final String CLIENT_PROXY = "de.errorcraftlp.cryingobsidian.proxy.ClientProxy";
	public static final String SERVER_PROXY = "de.errorcraftlp.cryingobsidian.proxy.ServerProxy";
	public static final String UPDATE_JSON = "https://raw.githubusercontent.com/ErrorCraftLP/Crying-Obsidian/master/version.json";
	public static final String ACCEPTED_VERSIONS = "[1.12,1.12.1]";
	public static final String DEPENDENCIES = "required-after:forge@[14.21.1.2387,);";

	// Util method for setting the spawn point at the player's location
	public static void setSpawnPointAtPlayer(final World world, final EntityPlayer player) {

		if(!world.isRemote) {

			final BlockPos playerPos = player.getPosition();

			if(player.isInWater()) {

				final BlockPos correctedPos = new BlockPos(playerPos.getX(), world.getSeaLevel(), playerPos.getZ());
				player.setSpawnPoint(correctedPos, true);
				player.setSpawnDimension(player.dimension);


			} else {

				player.setSpawnPoint(playerPos, true);
				player.setSpawnDimension(player.dimension);

			}

			if(CryingObsidianConfig.enableChatMessage) {

				player.sendMessage(new TextComponentTranslation("message.spawnpoint_set", player.getDisplayName(), playerPos.getX(), playerPos.getY(), playerPos.getZ()));

			}

		}

	}

	// Util method for setting the spawn point at the block's location
	public static void setSpawnPointAtBlock(final World world, final EntityPlayer player, final BlockPos pos) {

		if(!world.isRemote) {

			BlockPos correctedPos1 = pos.up();
			BlockPos correctedPos2 = pos.up(2);
			BlockPos correctedPos3 = pos.down();
			IBlockState stateAtCorrectedPos1 = world.getBlockState(correctedPos1);
			IBlockState stateAtCorrectedPos2 = world.getBlockState(correctedPos2);
			IBlockState stateAtCorrectedPos3 = world.getBlockState(correctedPos3);

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

			player.setSpawnPoint(correctedPos1, true);
			player.setSpawnDimension(player.dimension);

			if(CryingObsidianConfig.enableChatMessage) {

				player.sendMessage(new TextComponentTranslation("message.spawnpoint_set", player.getDisplayName(), correctedPos1.getX(), correctedPos1.getY(), correctedPos1.getZ()));

			}

		}

	}

	// Util method that checks if there is enough place for a spawn point
	public static boolean hasNoPlace(final BlockPos pos, final IBlockState state, final World world) {

		final Block block = state.getBlock();
		return !block.isAir(state, world, pos);

	}

	// Util method that checks if the block is dangerous (lava, fire)
	public static boolean isDangerous(final IBlockState state) {

		final Block block = state.getBlock();
		return block.equals(Blocks.LAVA) || block.equals(Blocks.FLOWING_LAVA) || block.equals(Blocks.FIRE);

	}

}
