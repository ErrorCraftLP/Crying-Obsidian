package de.errorcraftlp.cryingobsidian;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import de.errorcraftlp.cryingobsidian.block.*;

/**
 * 
 * This is the main class of the Crying Obsidian-Mod.
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 * 
 * @author ErrorCraftLP
 *
 */
@Mod(modid = CryingObsidian.MOD_ID, name = CryingObsidian.MOD_NAME, version = CryingObsidian.MOD_VERSION)
public class CryingObsidian {
	
	/**The id of the Crying Obsidian-Mod*/
    public static final String MOD_ID = "cryingobsidian";
    
    /**The official name of the Crying Obsidian-Mod which is showed in the mcmod.info file*/
    public static final String MOD_NAME = "Crying Obsidian-Mod";
    
    /**The version of the Crying Obsidian-Mod*/
    public static final String MOD_VERSION = "1.0.0";
    
    /**The creative tab of the Sugar+ Mod*/
    public static CreativeTabs tabCryingObsidian = new CreativeTabs(CreativeTabs.getNextID(), "tabCryingObsidian") {
    	
		public Item getTabIconItem() {
			
			return Item.getItemFromBlock(null);
			
		}
    	
    };
    
    /**The Crying Obsdian Block**/
    public static Block CryingObsidianBlock;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	CryingObsidianBlock = new CryingObsidianBlock(Material.rock).setUnlocalizedName("CryingObsidianBlock");
    	
    	GameRegistry.registerBlock(CryingObsidianBlock, "CryingObsidianBlock");
    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    	
    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    	
    	
    }
}
