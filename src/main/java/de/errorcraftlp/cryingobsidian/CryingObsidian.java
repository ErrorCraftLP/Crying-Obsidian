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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.apache.logging.log4j.Level;

import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidian;
import de.errorcraftlp.cryingobsidian.command.CommandCryingObsidian;

/**
 * 
 * The main class of the Crying Obsidian Mod.
 * 
 * @author ErrorCraftLP
 * 
 * @since 1.0.0
 * 
 * @version 1.0.2
 *
 */
@Mod(modid = CryingObsidian.MOD_ID, name = CryingObsidian.MOD_NAME, version = CryingObsidian.MOD_VERSION, guiFactory = CryingObsidian.MOD_GUI_FACTORY)
public class CryingObsidian {
	
	/**The mod id of the Crying Obsidian Mod*/
    public static final String MOD_ID = "cryingobsidian";
    
    /**The official name of the Crying Obsidian Mod which is showed in the mcmod.info file*/
    public static final String MOD_NAME = "Crying Obsidian Mod";
    
    /**The version of the Crying Obsidian Mod*/
    public static final String MOD_VERSION = "1.0.2";
    
    /**The path of the gui factory of the Crying Obsidian Mod*/
    public static final String MOD_GUI_FACTORY = "de.errorcraftlp.cryingobsidian.config.GuiFactoryCryingObsidian";
    
    /**The instance of the Crying Obsidian Mod*/
    @Instance(MOD_ID)
    public static CryingObsidian instance;
    
    /**An instance of the {@link Configuration} class*/
    public static Configuration configuration;
    
    /**If this boolean is enabled, the mod will print debug info in the console*/
    public static boolean enableDebugMessages = true;
    
    /**If this boolean is enabled, the mod will print a message into the chat when the Crying Obsidian block successfully sets the spawn point.*/
    public static boolean enableChatMessage = true;
    
    /**The Crying Obsdian block*/
    public static Block cryingObsidian;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	//Show a message when the Crying Obsidian Mod is in the 'preInit' phase and if debug messages are enabled
    	if(this.enableDebugMessages) {
    		
    		this.info(String.format("PreInit phase!"));
    		
    	}
    	
    	//Create an instance of the Crying Obsidian block
    	cryingObsidian = new BlockCryingObsidian();
    	
    	//Register the Crying Obsidian block
    	GameRegistry.registerBlock(cryingObsidian, "cryingObsidian");
    	
		//Create/Load the config file
		configuration = new Configuration(event.getSuggestedConfigurationFile());
		
		//Show a message when the Crying Obsidian Mod is loading the config file and if debug messages are enabled
		if(this.enableDebugMessages) {
			
			this.info(String.format("Loading config file: %s", event.getSuggestedConfigurationFile().getPath()));
			
		}
		
		//Load the config
		this.loadConfig(); 
		
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
  
    	///Show a message when the Crying Obsidian Mod is in the 'init' phase and if debug messages are enabled
    	if(this.enableDebugMessages) {
    		
    		this.info(String.format("Init phase!"));
    		
    	}
    	
    	//Register all event handlers in the event bus of the Forge Mod Loader
    	FMLCommonHandler.instance().bus().register(instance);
    	
    	//Load the crafting recipes
    	loadCraftingRecipes();
    	
    	//Render the Crying Obsidian block
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(cryingObsidian), 0, new ModelResourceLocation(MOD_ID + ":cryingObsidian"));
    	
    	//Initialize Version Checker integration
    	FMLInterModComms.sendRuntimeMessage(CryingObsidian.MOD_ID, "VersionChecker", "addVersionCheck", "https://raw.githubusercontent.com/ErrorCraftLP/Crying-Obsidian-Mod/master/version.json");
    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    	//Show a message when the Crying Obsidian Mod is in the 'postInit' phase and if debug messages are enabled
    	if(this.enableDebugMessages) {
    		
    		this.info(String.format("PostInit phase!"));
    		
    	}
    	
    }
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
    	
    	//Show a message when the Crying Obsidian Mod is registering the commands and if debug messages are enabled
    	if(this.enableDebugMessages) {
    		
    		this.info(String.format("Registering commands!"));
    		
    	}
    	
    	//Register the '/cryingobsidian' command
    	event.registerServerCommand(new CommandCryingObsidian());
    	
    }
    
    /**
     * 
     * This method contains all recipes of the Crying Obsidian Mod.
     * 
     * @author ErrorCraftLP
     * 
     * @since 1.0.0
     * 
     */
    public void loadCraftingRecipes() {
    	
    	//The recipe of the crying obsidian block
    	GameRegistry.addRecipe(new ItemStack(CryingObsidian.cryingObsidian, 1), new Object[] {
    		"xlx",
    		"lol",
    		"xlx",
    		Character.valueOf('l'), new ItemStack(Items.dye, 1, 4),
    		Character.valueOf('o'), Blocks.obsidian
    	});
    	
    }
    
    /**
     * 
     * This method loads the config of the Crying Obsidian Mod.
     * 
     * @author ErrorCraftLP
     * 
     * @since 1.0.0
     * 
     */
    public static void loadConfig() {
    	
		enableDebugMessages = configuration.get(Configuration.CATEGORY_GENERAL, "enableDebugMessages", true, StatCollector.translateToLocal("config.enableDebugMessages")).getBoolean(enableDebugMessages);
		enableChatMessage   = configuration.get(Configuration.CATEGORY_GENERAL, "enableChatMessage", true, StatCollector.translateToLocal("config.enableChatMessage")).getBoolean(enableChatMessage);
		
		if(configuration.hasChanged()) {
			
			configuration.save();
			
		}
		
    }
    
    /**
     * 
     * This method prints a message into the console.
     * 
     * @author ErrorCraftLP
     * 
     * @since 1.0.0
     * 
     */
	public static void info(Object message) {
		
		FMLLog.log(CryingObsidian.MOD_NAME, Level.INFO, String.valueOf(message));
		
	}
}
