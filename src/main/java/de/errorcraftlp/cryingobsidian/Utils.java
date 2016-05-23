package de.errorcraftlp.cryingobsidian;

import org.apache.logging.log4j.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

public class Utils {

	/* CONSTANTS */
	public static final String ID = "cryingobsidian";
	public static final String NAME = "Crying Obsidian Mod";
	public static final String VERSION = "2.1.0";
	public static final String GUI_FACTORY = "de.errorcraftlp.cryingobsidian.config.ConfigGUI$Factory";
	public static final String CLIENT_PROXY = "de.errorcraftlp.cryingobsidian.proxy.ClientProxy";
	public static final String SERVER_PROXY = "de.errorcraftlp.cryingobsidian.proxy.ServerProxy";

	/* LOGGING */
	public static void log(Level logLevel, String message) {

		FMLLog.log(NAME, logLevel, message);

	}

	/* SPAWN POINT SETTING */
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
