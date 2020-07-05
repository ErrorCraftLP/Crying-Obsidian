package de.errorcraftlp.cryingobsidian.misc;

import java.util.Collections;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;

public class CryingObsidianConfig {
	public static ForgeConfigSpec commonSpec;
	private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

	public static ForgeConfigSpec.BooleanValue enableChatMessage;
	public static ForgeConfigSpec.BooleanValue setSpawnPointAtBlock;
	public static ForgeConfigSpec.BooleanValue enableAdvancedCryingObsidianEntityRespawning;
	public static ForgeConfigSpec.BooleanValue enableAdvancedCryingObsidianOwner;
	public static ForgeConfigSpec.BooleanValue disableBeds;
	public static ForgeConfigSpec.BooleanValue enableRespawnWhitelist;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> respawnWhitelist;

	static {
		COMMON_BUILDER.push("general");

		enableChatMessage = COMMON_BUILDER.comment("Whether a chat message should be shown when you set your spawn point with the Crying Obsidian block/item.").define("Enable chat message", true);
		setSpawnPointAtBlock = COMMON_BUILDER.comment("Whether the spawn point should be set at the location of the Crying Obsidian block (true) or at the player's location (false).").define("Set spawn point at block", false);
		enableAdvancedCryingObsidianEntityRespawning = COMMON_BUILDER.comment("Whether entities can be bound to the advanced Crying Obsidian block.").define("Enable Advanced Crying Obsidian entity re-spawning", true);
		enableAdvancedCryingObsidianOwner = COMMON_BUILDER.comment("If this option is enabled, only the player who placed an advanced Crying Obsidian block can use it.").define("Enable Advanced Crying Obsidian owner system", true);
		disableBeds = COMMON_BUILDER.comment("If this option is enabled, players can no longer sleep in beds.").define("Disable sleeping in beds", false);
		enableRespawnWhitelist = COMMON_BUILDER.comment("If this option is enabled, only entites in the re-spawn whitelist can be bound to the advanced Crying Obsidian block.").define("Enable re-spawn whitelist", false);
		respawnWhitelist = COMMON_BUILDER.comment("A list of entities which can be bound to the advanced Crying Obsidian block. You can use the /summon command to obtain the entity IDs.").defineList("Re-spawn whitelist", Collections.emptyList(), entry -> entry instanceof String);

		COMMON_BUILDER.pop();
		commonSpec = COMMON_BUILDER.build();
	}
}