package de.errorcraftlp.cryingobsidian.misc;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

// @Config(modid = Utils.ID)
public class CryingObsidianConfig {
	// @Config.Name("Enable chat message")
	// @Config.Comment("Whether a chat message should be shown when you set your spawn point with the Crying Obsidian block/item.")
	public static boolean enableChatMessage = true;

	// @Config.Name("Set spawn point at block")
	// @Config.Comment("Whether the spawn point should be set at the location of the Crying Obsidian block (true) or at the player's location (false).")
	public static boolean setSpawnPointAtBlock = false;

	// @Config.Name("Enable Advanced Crying Obsidian entity re-spawning")
	// @Config.Comment("Whether entities can be bound to the advanced Crying Obsidian block.")
	public static boolean enableAdvancedCryingObsidianEntityRespawning = true;

	// @Config.Name("Enable Advanced Crying Obsidian owner system")
	// @Config.Comment("If this option is enabled, only the player who placed an advanced Crying Obsidian block can use it.")
	public static boolean enableAdvancedCryingObsidianOwner = true;

	// @Config.Name("Disable sleeping in beds")
	// @Config.Comment("If this option is enabled, players can no longer sleep in beds.")
	public static boolean disableBeds = false;

	// @Config.Name("Enable re-spawn whitelist")
	// @Config.Comment("If this option is enabled, only entites in the re-spawn whitelist can be bound to the advanced Crying Obsidian block.")
	public static boolean enableRespawnWhitelist = false;

	// @Config.Name("Re-spawn whitelist")
	// @Config.Comment("A list of entities which can be bound to the advanced Crying Obsidian block. You can use the /summon command to obtain the entity IDs.")
	public static String[] respawnWhitelist = {};

	@EventBusSubscriber(modid = Utils.ID)
	private static class ConfigGui {
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(Utils.ID)) {
				//ConfigManager.sync(Utils.ID, Config.Type.INSTANCE);
			}
		}
	}
}
