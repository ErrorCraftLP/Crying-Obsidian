package de.errorcraftlp.cryingobsidian;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CryingObsidianConfig {

	public static class Factory implements IModGuiFactory {

		@Override
		public void initialize(final Minecraft mc) {}

		@Override
		public Class<? extends GuiScreen> mainConfigGuiClass() {

			return GUI.class;

		}

		@Override
		public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {

			return null;

		}

		@Override
		@Deprecated
		public RuntimeOptionGuiHandler getHandlerFor(final RuntimeOptionCategoryElement element){

			return null;

		}

	}

	public static class GUI extends GuiConfig {

		public GUI(final GuiScreen gui) {

			super(gui, new ConfigElement(CryingObsidian.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Utils.ID, false, false, GuiConfig.getAbridgedConfigPath(CryingObsidian.config.toString()));

		}

	}

	public static class EventHandler {

		@SubscribeEvent
		public void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {

			if(event.getModID().equals(Utils.ID)) {

				CryingObsidian.initConfig();

			}

		}

	}

}