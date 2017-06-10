package de.errorcraftlp.cryingobsidian;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Utils.ID)
public class CryingObsidianConfig {

	@Config.Comment("Whether a chat message should be shown when you set your spawn point with the Crying Obsidian block/item.")
	public static boolean enableChatMessage = true;

	@Config.Comment("Whether the spawn point should be set at the Crying Obsidian Block's location (true) or at the player's location (false).")
	public static boolean setSpawnPointAtBlock = false;

	@Config.Comment("Whether the Advanced Crying Obsidian Block can be crafted.")
	public static boolean enableAdvancedCryingObsidianRecipe = true;

	@Config.Comment("If this option is enabled, only the one who placed an Advanced Crying Obsidian Block can use it.")
	public static boolean enableAdvancedCryingObsidianOwner = true;

	@EventBusSubscriber
	private static class ConfigGui {

		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {

			if(event.getModID().equals(Utils.ID)) {

				ConfigManager.sync(Utils.ID, Config.Type.INSTANCE);

			}

		}

	}

}
