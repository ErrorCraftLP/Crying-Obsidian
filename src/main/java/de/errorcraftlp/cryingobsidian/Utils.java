package de.errorcraftlp.cryingobsidian;

import org.apache.logging.log4j.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

public class Utils {

	public static void log(Level logLevel, String message) {

		FMLLog.log(CryingObsidian.MOD_NAME, logLevel, message);

	}

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
