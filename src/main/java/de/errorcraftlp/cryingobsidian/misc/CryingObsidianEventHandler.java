package de.errorcraftlp.cryingobsidian.misc;

import net.minecraft.entity.player.PlayerEntity.SleepResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Utils.ID)
public class CryingObsidianEventHandler {
	@SubscribeEvent
	public static void preventSleeping(final PlayerSleepInBedEvent event) {
		// Disable sleeping in beds if the config option is enabled
		if(CryingObsidianConfig.disableBeds) {
			event.setResult(SleepResult.OTHER_PROBLEM);
			event.getPlayer().sendMessage(new TranslationTextComponent("message.bed_disabled"));
		}
	}
}
