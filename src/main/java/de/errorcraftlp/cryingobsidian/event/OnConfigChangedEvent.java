package de.errorcraftlp.cryingobsidian.event;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import de.errorcraftlp.cryingobsidian.CryingObsidian;

/**
 * 
 * This event fires when you change a config option in the config gui.
 * 
 * @see ConfigChangedEvent.OnConfigChangedEvent
 * 
 * @author ErrorCraftLP
 * 
 * @since 1.0.0
 *
 */
public class OnConfigChangedEvent {
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent onConfigChangedEvent) {
		
		if(onConfigChangedEvent.modID.equals(CryingObsidian.MOD_ID)) {
			
			CryingObsidian.loadConfig();
			
		}
		
	}
	
}
