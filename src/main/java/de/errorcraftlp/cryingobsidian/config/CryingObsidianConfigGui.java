package de.errorcraftlp.cryingobsidian.config;

import java.util.List;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class CryingObsidianConfigGui extends GuiConfig {

	public CryingObsidianConfigGui(GuiScreen guiScreen) {
		super(guiScreen, new ConfigElement(CryingObsidian.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), CryingObsidian.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(CryingObsidian.configuration.toString()));
		
	}
	
}
