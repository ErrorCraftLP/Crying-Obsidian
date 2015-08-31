package de.errorcraftlp.cryingobsidian;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.apache.logging.log4j.Level;

import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidian;
import de.errorcraftlp.cryingobsidian.config.ConfigEventHandler;
import de.errorcraftlp.cryingobsidian.config.ConfigGUI;

@Mod(modid = CryingObsidian.MOD_ID, name = CryingObsidian.MOD_NAME, version = CryingObsidian.MOD_VERSION, guiFactory = CryingObsidian.MOD_GUI_FACTORY)
public class CryingObsidian
{
	/* GENERAL CONSTANTS */
	public static final String MOD_ID = "cryingobsidian";
	public static final String MOD_NAME = "Crying Obsidian Mod";
	public static final String MOD_VERSION = "1.0.2";
	public static final String MOD_GUI_FACTORY = "de.errorcraftlp.cryingobsidian.config.ConfigGUI$Factory";

	/* CONFIG-RELATED VARIABLES */
	public static Configuration configuration;
	public static boolean enableChatMessage = true;

	/* BLOCK-RELATED VARIABLES */
	public static Block cryingObsidian;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		this.logDebug(String.format("Crying Obsidian Mod v%s for Minecraft %s loading...", MOD_VERSION, Loader.MC_VERSION));
		this.logDebug("Registering Crying Obsidian block and initializing config file! (PreInit phase)");

		/* REGISTER CRYING OBSIDIAN BLOCK */
		cryingObsidian = new BlockCryingObsidian();
		GameRegistry.registerBlock(cryingObsidian, "cryingObsidian");

		/* INIT CONFIG */
		configuration = new Configuration(event.getSuggestedConfigurationFile());
		this.logDebug(String.format("Loading config file: %s", event.getSuggestedConfigurationFile().getPath()));
		this.initConfig();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		this.logDebug("Registering recipes, models and initializing VersionChecker integration! (Init phase)");

		/* REGISTER EVENT HANDLER */
		FMLCommonHandler.instance().bus().register(new ConfigEventHandler());

		/* INIT CRAFTING RECIPES */
		this.initRecipes();

		/* REGISTER CRYING OBSIDIAN MODEL */
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(cryingObsidian), 0, new ModelResourceLocation(MOD_ID + ":cryingObsidian"));

		/* INIT MOD INTEGRATION */
		FMLInterModComms.sendRuntimeMessage(CryingObsidian.MOD_ID, "VersionChecker", "addVersionCheck", "https://raw.githubusercontent.com/ErrorCraftLP/Crying-Obsidian-Mod/master/version.json");
	}

	public void initRecipes()
	{
		// @formatter:off
		GameRegistry.addRecipe(new ItemStack(CryingObsidian.cryingObsidian, 1), new Object[] {
			"xlx",
			"lol",
			"xlx",
			Character.valueOf('l'), new ItemStack(Items.dye, 1, 4),
			Character.valueOf('o'), Blocks.obsidian
		});
		// @formatter:on
	}

	public static void initConfig()
	{
		enableChatMessage = configuration.get(Configuration.CATEGORY_GENERAL, "enableChatMessage", true, StatCollector.translateToLocal("config.enableChatMessage")).getBoolean(enableChatMessage);

		if (configuration.hasChanged())
		{
			configuration.save();
		}
	}

	public static void logDebug(Object message)
	{
		FMLLog.log(CryingObsidian.MOD_NAME, Level.DEBUG, String.valueOf(message));
	}
}
