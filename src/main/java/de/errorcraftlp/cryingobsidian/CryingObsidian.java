package de.errorcraftlp.cryingobsidian;

import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidian;
import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidianAdvanced;
import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidianDecoration;
import de.errorcraftlp.cryingobsidian.item.ItemCryingObsidian;
import de.errorcraftlp.cryingobsidian.proxy.ServerProxy;
import de.errorcraftlp.cryingobsidian.tileentiy.TileEntityCryingObsidianAdvanced;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

@Mod(modid = Utils.ID, name = Utils.NAME, version = Utils.VERSION, updateJSON = Utils.UPDATE_JSON)
public class CryingObsidian {

	// Proxy
	@SidedProxy(clientSide = Utils.CLIENT_PROXY, serverSide = Utils.SERVER_PROXY)
	public static ServerProxy proxy;

	// Config-related variables
	public static Configuration config;
	public static boolean enableChatMessage = true;
	public static boolean setSpawnPointAtBlock = false;
	public static boolean enableAdvancedCryingObsidianRecipe = true;

	// Block/Item-related variables
	public static Block cryingObsidianBlock;
	public static Block cryingObsidianBlockAdvanced;
	public static Block cryingObsidianBlockDecoration;
	public static Item cryingObsidianItem;

	@EventHandler
	public void preInit(final FMLPreInitializationEvent event) {

		// Register crying obsidian block
		cryingObsidianBlock = new BlockCryingObsidian();
		GameRegistry.register(cryingObsidianBlock);
		GameRegistry.register(new ItemBlock(cryingObsidianBlock).setRegistryName("crying_obsidian_block"));

		// Register crying obsidian block (decoration variant)
		cryingObsidianBlockDecoration = new BlockCryingObsidianDecoration();
		GameRegistry.register(cryingObsidianBlockDecoration);
		GameRegistry.register(new ItemBlock(cryingObsidianBlockDecoration).setRegistryName("crying_obsidian_block_decoration"));

		// Register crying obsidian block (advanced variant)
		cryingObsidianBlockAdvanced = new BlockCryingObsidianAdvanced();
		GameRegistry.register(cryingObsidianBlockAdvanced);
		GameRegistry.register(new ItemBlock(cryingObsidianBlockAdvanced).setRegistryName("crying_obsidian_block_advanced"));

		// Register crying obsidian item
		cryingObsidianItem = new ItemCryingObsidian();
		GameRegistry.register(cryingObsidianItem);

		// Init config
		config = new Configuration(event.getSuggestedConfigurationFile());
		CryingObsidian.initConfig();

		// Register tile entities
		GameRegistry.registerTileEntity(TileEntityCryingObsidianAdvanced.class, "crying_obsidian_advanced_tile_entity");

	}

	@EventHandler
	public void init(@SuppressWarnings("unused") final FMLInitializationEvent event) {

		// Register crafting recipes
		registerRecipes();

		// Register models
		proxy.registerModels();

	}

	public void registerRecipes() {

		GameRegistry.addRecipe(new ShapelessOreRecipe(cryingObsidianBlock,
				"gemLapis", "gemLapis", "gemLapis", "gemLapis", "obsidian"
				));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(cryingObsidianBlockDecoration, 4),
				cryingObsidianBlock
				));

		GameRegistry.addRecipe(new ShapelessOreRecipe(cryingObsidianBlock,
				cryingObsidianBlockDecoration, cryingObsidianBlockDecoration, cryingObsidianBlockDecoration, cryingObsidianBlockDecoration
				));

		if(enableAdvancedCryingObsidianRecipe) {

			GameRegistry.addRecipe(new ShapelessOreRecipe(cryingObsidianBlockAdvanced,
					"dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", cryingObsidianBlock
					));

		}

		GameRegistry.addRecipe(new ShapedOreRecipe(cryingObsidianItem,
				"xsx", "sos", "xsx",
				'o', cryingObsidianBlock,
				's', "stickWood"
				));

	}

	public static void initConfig() {

		enableChatMessage = config.get(Configuration.CATEGORY_GENERAL, "enableChatMessage", true, "Whether a chat message should be shown when you set your spawn point with the Crying Obsidian block/item.").getBoolean(enableChatMessage);
		setSpawnPointAtBlock = config.get(Configuration.CATEGORY_GENERAL, "setSpawnPointAtBlock", false, "Whether the spawn point should be set at the Crying Obsidian Block's location (true) or at the player's location (false).").getBoolean(setSpawnPointAtBlock);
		enableAdvancedCryingObsidianRecipe = config.get(Configuration.CATEGORY_GENERAL, "enableAdvancedCryingObsidianRecipe", true, "Whether the Advanced Crying Obsidian Block can be crafted.").getBoolean(enableAdvancedCryingObsidianRecipe);

		if(config.hasChanged()) {

			config.save();

		}

	}

}
