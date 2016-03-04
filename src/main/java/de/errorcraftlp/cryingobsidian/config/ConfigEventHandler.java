package de.errorcraftlp.cryingobsidian.config;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigEventHandler {

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {

		if(event.modID.equals(CryingObsidian.MOD_ID)) {

			CryingObsidian.initConfig();

		}

	}

}
