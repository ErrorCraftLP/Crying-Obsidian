package de.errorcraftlp.cryingobsidian;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import de.errorcraftlp.cryingobsidian.block.CryingObsidianBlock;
import de.errorcraftlp.cryingobsidian.commands.CryingObsidianCommand;
import de.errorcraftlp.cryingobsidian.utils.LogUtils;

/**
 * 
 * This is the main class of the Crying Obsidian Mod.
 * 
 * @author ErrorCraftLP
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0-SNAPSHOT
 *
 */
@Mod(modid = CryingObsidian.MOD_ID, name = CryingObsidian.MOD_NAME, version = CryingObsidian.MOD_VERSION)
public class CryingObsidian {
	
	/**The id of the Crying Obsidian Mod*/
    public static final String MOD_ID = "cryingobsidian";
    
    /**The official name of the Crying Obsidian Mod which is showed in the mcmod.info file*/
    public static final String MOD_NAME = "Crying Obsidian Mod";
    
    /**The version of the Crying Obsidian Mod*/
    public static final String MOD_VERSION = "1.0.0-SNAPSHOT";
    
    /**The instance of the Crying Obsidian Mod*/
    @Instance(MOD_ID)
    public static CryingObsidian instance;
    
    /**An instance of the {@link Configuration} class*/
    public static Configuration configuration;
    
    /**The creative tab of the Crying Obsidian Mod*/
    public static CreativeTabs tabCryingObsidian = new CreativeTabs(CreativeTabs.getNextID(), "tabCryingObsidian") {
    	
		public Item getTabIconItem() {
			
			return Item.getItemFromBlock(CryingObsidianBlock);
			
		}
    	
    };
    
    /**If this boolean is enabled, the mod will print debug info in the console*/
    public static boolean enableDebugMessages = true;
    
    /**The Crying Obsdian Block*/
    public static Block CryingObsidianBlock;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	//Show a message when the Crying Obsidian Mod is going to load
    	if(enableDebugMessages) {
    		
    		LogUtils.info(String.format("Getting ready to load the Crying Obsidian Mod!"));
    		
    	}
    	
    	//Set the material and the unlocalized name of the crying obsidian block
    	CryingObsidianBlock = new CryingObsidianBlock(Material.rock).setUnlocalizedName("CryingObsidianBlock");
    	
    	//Register the crying obsidian block
    	GameRegistry.registerBlock(CryingObsidianBlock, "CryingObsidianBlock");
    	
		//Create/Load the config file
		configuration = new Configuration(event.getSuggestedConfigurationFile());
		
		//Show a message when the Crying Obsidian Mod is loading the config
		if(enableDebugMessages) {
			
			LogUtils.info("Loading config file: " + event.getSuggestedConfigurationFile());
			
		}
		
		//Load the config
		loadConfig(); 
		
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
  
    	//Show a message when the Crying Obsidian Mod is loading
    	if(enableDebugMessages) {
    		
    		LogUtils.info(String.format("Loading the Crying Obsidian Mod!"));
    		
    	}
    	
    	//HACK: Register all event handlers in the event bus of the Forge Mod Loader
    	FMLCommonHandler.instance().bus().register(instance);
    	
    	//Load the crafting recipes
    	loadCraftingRecipes();
    	
    	//Render the crying obsidian block
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(CryingObsidianBlock), 0, new ModelResourceLocation(MOD_ID + ":CryingObsidianBlock"));
    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    	//Show a message when the Crying Obsidian Mod was initialized sucessfully
    	if(enableDebugMessages) {
    		
    		LogUtils.info(String.format("Intialized the Crying Obsidian Mod sucessfully!"));
    		
    	}
    	
    }
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
    	
    	//Register the /cryingobsidian command
    	event.registerServerCommand(new CryingObsidianCommand());
    	
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
    	GameRegistry.addRecipe(new ItemStack(CryingObsidian.CryingObsidianBlock, 1), new Object[]{
    		"xlx",
    		"lol",
    		"xlx",
    		Character.valueOf('l'), Blocks.lapis_block,
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
    	
		enableDebugMessages = configuration.get(Configuration.CATEGORY_GENERAL, "enableDebugMessages", true).getBoolean(enableDebugMessages);
		
		if(configuration.hasChanged()) {
			
			configuration.save();
			
		}
		
    }
}
