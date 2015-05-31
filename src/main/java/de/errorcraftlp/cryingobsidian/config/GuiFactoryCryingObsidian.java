package de.errorcraftlp.cryingobsidian.config;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

/**
 *
 * This is the {@link IModGuiFactory GuiFactory} class of the Crying Obsidian Mod.
 *
 * @see IModGuiFactory
 *
 * @author ErrorCraftLP
 *
 * @since 1.0.0
 *
 */
public class GuiFactoryCryingObsidian implements IModGuiFactory {

	@Override
	public void initialize(Minecraft minecraft) {

		//Nothing

	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {

		return GuiConfigCryingObsidian.class;

	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {

		return null;

	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement runtimeOptionCategoryElement) {

		return null;

	}

}
