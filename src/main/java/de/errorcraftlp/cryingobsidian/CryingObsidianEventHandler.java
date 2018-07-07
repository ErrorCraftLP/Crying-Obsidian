package de.errorcraftlp.cryingobsidian;

import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CryingObsidianEventHandler {

	@SubscribeEvent
	public void preventSleeping(final PlayerSleepInBedEvent event) {

		// Disable sleeping in beds if the config option is enabled
		if(CryingObsidian.disableBeds) {

			event.setResult(SleepResult.OTHER_PROBLEM);
			event.getEntityPlayer().sendMessage(new TextComponentTranslation("message.bed_disabled"));

		}

	}

}
