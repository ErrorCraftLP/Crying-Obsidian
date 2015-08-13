package de.errorcraftlp.cryingobsidian.config;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import de.errorcraftlp.cryingobsidian.CryingObsidian;

public class ConfigGUI {

	public class Factory implements IModGuiFactory {

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
		public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {

			return null;

		}

	}

	public class GUI extends GuiConfig {

		public GUI(GuiScreen gui) {

			super(gui, new ConfigElement(CryingObsidian.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), CryingObsidian.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(CryingObsidian.configuration.toString()));

		}

	}

}
