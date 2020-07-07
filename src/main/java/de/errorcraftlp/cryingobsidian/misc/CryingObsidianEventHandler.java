package de.errorcraftlp.cryingobsidian.misc;

import net.minecraft.entity.player.PlayerEntity.SleepResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Utils.ID)
public class CryingObsidianEventHandler {
	@SubscribeEvent
	public static void preventSleeping(final PlayerSleepInBedEvent event) {
		// Disable sleeping in beds if the config option is enabled
		if(CryingObsidianConfig.disableSleepingInBeds.get()) {
			event.setResult(SleepResult.OTHER_PROBLEM);
			final TranslationTextComponent message = new TranslationTextComponent("message.bed_disabled");
			message.getStyle().setColor(TextFormatting.RED);
			event.getPlayer().sendMessage(message);
		}
	}
}
