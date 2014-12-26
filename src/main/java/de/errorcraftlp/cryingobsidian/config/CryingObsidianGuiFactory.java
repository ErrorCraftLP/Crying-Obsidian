package de.errorcraftlp.cryingobsidian.config;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import net.minecraftforge.fml.client.IModGuiFactory;

/**
 * 
 * This is the {@link IModGuiFactory GuiFactory} class of the Crying Obsidian-Mod.
 * 
 * @see IModGuiFactory
 * 
 * @author ErrorCraftLP
 * 
 * @since 1.0.0
 *
 */
public class CryingObsidianGuiFactory implements IModGuiFactory {

	public void initialize(Minecraft minecraft) {
		
		//Nothing
		
	}
	
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		
		return CryingObsidianConfigGui.class;
		
	}
	
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		
		return null;
		
	}
	
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement runtimeOptionCategoryElement) {
		
		return null;
		
	}
	
}
