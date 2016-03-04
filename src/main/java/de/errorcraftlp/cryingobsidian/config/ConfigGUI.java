package de.errorcraftlp.cryingobsidian.config;

import java.util.Set;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ConfigGUI {

	public static class Factory implements IModGuiFactory {

		@Override
		public void initialize(Minecraft mc) {}

		@Override
		public Class<? extends GuiScreen> mainConfigGuiClass() {

			return GUI.class;

		}

		@Override
		public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {

			return null;

		}

		@Override
		public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element){

			return null;

		}

	}

	public static class GUI extends GuiConfig {

		public GUI(GuiScreen gui) {

			super(gui, new ConfigElement(CryingObsidian.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), CryingObsidian.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(CryingObsidian.config.toString()));

		}

	}

}
