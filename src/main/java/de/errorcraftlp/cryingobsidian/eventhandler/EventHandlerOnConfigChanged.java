package de.errorcraftlp.cryingobsidian.eventhandler;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import de.errorcraftlp.cryingobsidian.CryingObsidian;

/**
 * 
 * This is the event handler of the '{@link ConfigChangedEvent.OnConfigChangedEvent OnConfigChanged}' event.
 * 
 * @see ConfigChangedEvent.OnConfigChangedEvent
 * 
 * @author ErrorCraftLP
 * 
 * @since 1.0.0
 *
 */
public class EventHandlerOnConfigChanged {
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent onConfigChangedEvent) {
		
		if(onConfigChangedEvent.modID.equals(CryingObsidian.MOD_ID)) {
			
			CryingObsidian.loadConfig();
			
		}
		
	}
	
}
