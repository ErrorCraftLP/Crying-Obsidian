package de.errorcraftlp.cryingobsidian;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class Utils {

	// Constants
	public static final String ID = "cryingobsidian";
	public static final String NAME = "Crying Obsidian Mod";
	public static final String VERSION = "1.0.0";
	public static final String CLIENT_PROXY = "de.errorcraftlp.cryingobsidian.proxy.ClientProxy";
	public static final String SERVER_PROXY = "de.errorcraftlp.cryingobsidian.proxy.ServerProxy";

	// Util method for setting spawn point
	public static void setSpawnPoint(World world, EntityPlayer player) {

		if(!world.isRemote) {

			final BlockPos playerPos = player.getPosition();
			player.setSpawnPoint(playerPos, true);

			if(CryingObsidian.enableChatMessage) {

				player.addChatComponentMessage(new TextComponentTranslation(I18n.translateToLocal("message.spawnpoint_set"), player.getDisplayName(), playerPos.getX(), playerPos.getY(), playerPos.getZ()));

			}

		}

	}

}
