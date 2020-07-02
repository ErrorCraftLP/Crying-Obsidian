package de.errorcraftlp.cryingobsidian.misc;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT, modid = Utils.ID)
public class CryingObsidianModels {
	@SubscribeEvent
	public static void registerModels(@SuppressWarnings("unused") final ModelRegistryEvent event) {
		register(CryingObsidian.cryingObsidianBlock, "crying_obsidian_block");
		register(CryingObsidian.cryingObsidianBlockDecoration, "crying_obsidian_block_decoration");
		register(CryingObsidian.cryingObsidianBlockAdvanced, "crying_obsidian_block_advanced");
		register(CryingObsidian.cryingObsidianItem, "crying_obsidian_item");
	}

	private static void register(final Item item, final String name) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Utils.ID + ":" + name, "inventory"));
	}

	private static void register(final Block block, final String name) {
		register(Item.getItemFromBlock(block), name);
	}
}
