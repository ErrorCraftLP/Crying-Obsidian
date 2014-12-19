package de.errorcraftlp.cryingobsidian.event;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnConfigChangedEvent {
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent onConfigChangedEvent) {
		
		if(onConfigChangedEvent.modID.equals(CryingObsidian.MOD_ID)) {
			
			CryingObsidian.loadConfig();
			
		}
		
	}
	
}
