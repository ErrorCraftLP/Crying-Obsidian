package de.errorcraftlp.cryingobsidian;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class Utils {

	// Constants
	public static final String ID = "cryingobsidian";
	public static final String NAME = "Crying Obsidian";
	public static final String VERSION = "GRADLE_VERSION"; // This is set by the buildscript
	public static final String CLIENT_PROXY = "de.errorcraftlp.cryingobsidian.proxy.ClientProxy";
	public static final String SERVER_PROXY = "de.errorcraftlp.cryingobsidian.proxy.ServerProxy";
	public static final String UPDATE_JSON = "https://raw.githubusercontent.com/ErrorCraftLP/Crying-Obsidian/master/version.json";

	// Util method for setting spawn point
	public static void setSpawnPoint(World world, EntityPlayer player) {

		if(!world.isRemote) {

			final BlockPos playerPos = player.getPosition();

			if(playerPos.getY() < world.getSeaLevel()) {

				final BlockPos correctedPos = new BlockPos(playerPos.getX(), world.getSeaLevel(), playerPos.getZ());
				player.setSpawnPoint(correctedPos, true);

			} else {

				player.setSpawnPoint(playerPos, true);

			}

			if(CryingObsidian.enableChatMessage) {

				player.sendStatusMessage(new TextComponentTranslation(I18n.translateToLocal("message.spawnpoint_set"), player.getDisplayName(), playerPos.getX(), playerPos.getY(), playerPos.getZ()), false);

			}

		}

	}

}
