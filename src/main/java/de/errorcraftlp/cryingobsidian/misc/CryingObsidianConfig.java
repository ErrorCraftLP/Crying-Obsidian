package de.errorcraftlp.cryingobsidian.misc;

import java.util.Collections;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;

public class CryingObsidianConfig {
	public static ForgeConfigSpec commonSpec;
	private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

	public static ForgeConfigSpec.BooleanValue setSpawnPointAtBlock;
	public static ForgeConfigSpec.BooleanValue disableSleepingInBeds;
	public static ForgeConfigSpec.BooleanValue enableOwnerSystem;
	public static ForgeConfigSpec.BooleanValue enableRespawnWhitelist;
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> respawnWhitelist;

	static {
		COMMON_BUILDER.push("general");

		setSpawnPointAtBlock = COMMON_BUILDER.comment("Whether the spawn point should be set at the location of the Crying Obsidian block (true) or at the player's location (false).").define("setSpawnPointAtBlock", false);
		disableSleepingInBeds = COMMON_BUILDER.comment("If this option is enabled, players can no longer sleep in beds.").define("disableSleepingInBeds", false);
		enableOwnerSystem = COMMON_BUILDER.comment("If this option is enabled, only the player who placed an advanced Crying Obsidian block can use it.").define("enableOwnerSystem", true);
		enableRespawnWhitelist = COMMON_BUILDER.comment("If this option is enabled, only entites in the re-spawn whitelist can be bound to the advanced Crying Obsidian block. If you enable this option and leave the whitelist empty, no entity can be bound to the advanced Crying Obsidian block.").define("enableRespawnWhitelist", false);
		respawnWhitelist = COMMON_BUILDER.comment("A list of entities which can be bound to the advanced Crying Obsidian block. You can use the /summon command to obtain the entity IDs.").defineList("respawnWhitelist", Collections.emptyList(), entry -> entry instanceof String);

		COMMON_BUILDER.pop();
		commonSpec = COMMON_BUILDER.build();
	}
}