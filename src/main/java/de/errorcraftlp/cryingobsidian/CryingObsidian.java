package de.errorcraftlp.cryingobsidian;

import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidian;
import de.errorcraftlp.cryingobsidian.item.ItemCryingObsidian;
import de.errorcraftlp.cryingobsidian.proxy.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid = Utils.ID, name = Utils.NAME, version = Utils.VERSION)
public class CryingObsidian {

	// Proxy
	@SidedProxy(clientSide = Utils.CLIENT_PROXY, serverSide = Utils.SERVER_PROXY)
	public static ServerProxy proxy;

	// Config-related variables
	public static Configuration config;
	public static boolean enableChatMessage = true;

	// Block/Item-related variables
	public static Block cryingObsidianBlock;
	public static Item cryingObsidianItem;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		// Register crying obsidian block
		cryingObsidianBlock = new BlockCryingObsidian();
		GameRegistry.register(cryingObsidianBlock);
		GameRegistry.register(new ItemBlock(cryingObsidianBlock).setRegistryName("crying_obsidian_block"));

		// Register crying obsidian item
		cryingObsidianItem = new ItemCryingObsidian();
		GameRegistry.register(cryingObsidianItem);

		// Init config
		config = new Configuration(event.getSuggestedConfigurationFile());
		CryingObsidian.initConfig();

	}

	@EventHandler
	public void init(@SuppressWarnings("unused") FMLInitializationEvent event) {

		// Register crafting recipes
		registerRecipes();

		// Register models
		proxy.registerModels();

	}

	public void registerRecipes() {

		GameRegistry.addRecipe(new ShapedOreRecipe(CryingObsidian.cryingObsidianBlock,
				"xlx", "lol", "xlx",
				'l', "gemLapis",
				'o', "obsidian"
				));

		GameRegistry.addRecipe(new ShapedOreRecipe(CryingObsidian.cryingObsidianItem,
				"xsx", "sos", "xsx",
				'o', CryingObsidian.cryingObsidianBlock,
				's', "stickWood"
				));

	}

	public static void initConfig() {

		enableChatMessage = config.get(Configuration.CATEGORY_GENERAL, "enableChatMessage", true, "Wether a chat message should be shown when you set your spawn point with the Crying Obsidian block/item.").getBoolean(enableChatMessage);

		if(config.hasChanged()) {

			config.save();

		}

	}

}
