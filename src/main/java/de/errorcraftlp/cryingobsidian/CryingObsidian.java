package de.errorcraftlp.cryingobsidian;

import org.apache.logging.log4j.Level;

import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidian;
import de.errorcraftlp.cryingobsidian.config.ConfigEventHandler;
import de.errorcraftlp.cryingobsidian.item.ItemCryingObsidian;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid = CryingObsidian.MOD_ID, name = CryingObsidian.MOD_NAME, version = CryingObsidian.MOD_VERSION, guiFactory = CryingObsidian.MOD_GUI_FACTORY)
public class CryingObsidian
{
	/* GENERAL CONSTANTS */
	public static final String MOD_ID = "cryingobsidian";
	public static final String MOD_NAME = "Crying Obsidian Mod";
	public static final String MOD_VERSION = "2.1.0";
	public static final String MOD_GUI_FACTORY = "de.errorcraftlp.cryingobsidian.config.ConfigGUI$Factory";

	/* CONFIG-RELATED VARIABLES */
	public static Configuration config;
	public static boolean enableChatMessage = true;

	/* BLOCK/ITEM-RELATED VARIABLES */
	public static Block cryingObsidianBlock;
	public static Item cryingObsidianItem;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		this.log(Level.DEBUG, String.format("Crying Obsidian Mod v%s for Minecraft %s loading...", MOD_VERSION, MinecraftForge.MC_VERSION));

		/* REGISTER CRYING OBSIDIAN BLOCK */
		cryingObsidianBlock = new BlockCryingObsidian();
		GameRegistry.registerBlock(cryingObsidianBlock);

		/* REGISTER CRYING OBSIDIAN ITEM */
		cryingObsidianItem = new ItemCryingObsidian();
		GameRegistry.registerItem(cryingObsidianItem);

		/* INIT CONFIG */
		config = new Configuration(event.getSuggestedConfigurationFile());
		this.log(Level.DEBUG, String.format("Loading config file: %s", event.getSuggestedConfigurationFile().getPath()));
		CryingObsidian.initConfig();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		/* REGISTER EVENT HANDLER */
		MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());

		/* REGISTER CRAFTING RECIPES */
		this.registerRecipes();

		/* REGISTER MODELS - TODO: Move to a ClientProxy */
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(cryingObsidianBlock), 0, new ModelResourceLocation(MOD_ID + ":crying_obsidian_block"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(cryingObsidianItem, 0, new ModelResourceLocation(MOD_ID + ":crying_obsidian_item"));

		/* INIT MOD INTEGRATION */
		FMLInterModComms.sendRuntimeMessage(CryingObsidian.MOD_ID, "VersionChecker", "addVersionCheck", "http://errorcraftlp.github.io/download/cryingobsidian/versionchecker/version.json");

		this.log(Level.DEBUG, "Finished loading!");

	}

	public void registerRecipes() {

		GameRegistry.addRecipe(new ShapedOreRecipe(CryingObsidian.cryingObsidianBlock, new Object[] {
				"xlx",
				"lol",
				"xlx",
				'l', "gemLapis",
				'o', Blocks.obsidian
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(CryingObsidian.cryingObsidianItem, new Object[] {
				"xsx",
				"sos",
				"xsx",
				'o', CryingObsidian.cryingObsidianBlock,
				's', "stickWood"
		}));

	}

	public static void initConfig() {

		enableChatMessage = config.get(Configuration.CATEGORY_GENERAL, "enableChatMessage", true, I18n.translateToLocal("config.enableChatMessage")).getBoolean(enableChatMessage);

		if(config.hasChanged()) {

			config.save();

		}

	}

	public void log(Level logLevel, String message) {

		FMLLog.log(CryingObsidian.MOD_NAME, logLevel, message);

	}
}
