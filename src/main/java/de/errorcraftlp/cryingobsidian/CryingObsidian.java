package de.errorcraftlp.cryingobsidian;

import org.apache.logging.log4j.Level;

import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidian;
import de.errorcraftlp.cryingobsidian.config.ConfigEventHandler;
import de.errorcraftlp.cryingobsidian.item.ItemCryingObsidian;
import de.errorcraftlp.cryingobsidian.proxy.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid = Utils.ID, name = Utils.NAME, version = Utils.VERSION, guiFactory = Utils.GUI_FACTORY)
public class CryingObsidian {

	/* PROXY */
	@SidedProxy(clientSide = Utils.CLIENT_PROXY, serverSide = Utils.SERVER_PROXY)
	public static ServerProxy proxy;

	/* CONFIG-RELATED VARIABLES */
	public static Configuration config;
	public static boolean enableChatMessage = true;

	/* BLOCK/ITEM-RELATED VARIABLES */
	public static Block cryingObsidianBlock;
	public static Item cryingObsidianItem;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		Utils.log(Level.DEBUG, String.format("Crying Obsidian Mod v%s for Minecraft %s loading ...", Utils.VERSION, MinecraftForge.MC_VERSION));

		/* REGISTER CRYING OBSIDIAN BLOCK */
		cryingObsidianBlock = new BlockCryingObsidian();
		GameRegistry.register(cryingObsidianBlock);
		GameRegistry.register(new ItemBlock(cryingObsidianBlock).setRegistryName("crying_obsidian_block"));

		/* REGISTER CRYING OBSIDIAN ITEM */
		cryingObsidianItem = new ItemCryingObsidian();
		GameRegistry.register(cryingObsidianItem);

		/* INIT CONFIG */
		config = new Configuration(event.getSuggestedConfigurationFile());
		Utils.log(Level.DEBUG, String.format("Loading config file: %s", event.getSuggestedConfigurationFile().getPath()));
		CryingObsidian.initConfig();

	}

	@SuppressWarnings("unused")
	@EventHandler
	public void init(FMLInitializationEvent event) {

		/* REGISTER EVENT HANDLER */
		MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());

		/* REGISTER CRAFTING RECIPES */
		this.registerRecipes();

		/* REGISTER MODELS */
		proxy.registerModels();

		/* MOD INTEGRATION */
		FMLInterModComms.sendRuntimeMessage(Utils.ID, "VersionChecker", "addVersionCheck", "http://errorcraftlp.github.io/download/cryingobsidian/versionchecker/version.json");

		Utils.log(Level.DEBUG, "Finished loading!");

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

		enableChatMessage = config.get(Configuration.CATEGORY_GENERAL, "enableChatMessage", true, I18n.translateToLocal("config.enableChatMessage")).getBoolean(enableChatMessage);

		if(config.hasChanged()) {
			config.save();
		}

	}

}
