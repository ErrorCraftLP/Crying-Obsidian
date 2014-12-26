package de.errorcraftlp.cryingobsidian.config;

import net.minecraft.client.gui.GuiScreen;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

import de.errorcraftlp.cryingobsidian.CryingObsidian;

/**
 * 
 * This is the {@link GuiConfig} class of the Crying Obsidian-Mod.
 * 
 * @see GuiConfig
 * 
 * @author ErrorCraftLP
 * 
 * @since 1.0.0
 *
 */
public class CryingObsidianConfigGui extends GuiConfig {

	public CryingObsidianConfigGui(GuiScreen guiScreen) {
		
		super(guiScreen, new ConfigElement(CryingObsidian.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), CryingObsidian.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(CryingObsidian.configuration.toString()));
		
	}
	
}
