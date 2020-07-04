/*package de.errorcraftlp.cryingobsidian.misc;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT, modid = Utils.ID)
public class CryingObsidianModels {
	@SubscribeEvent
	public static void registerModels(@SuppressWarnings("unused") final ModelRegistryEvent event) {
		register(CryingObsidian.CRYING_OBSIDIAN_BLOCK, "crying_obsidian_block");
		register(CryingObsidian.CRYING_OBSIDIAN_BLOCK_DECORATION, "crying_obsidian_block_decoration");
		register(CryingObsidian.CRYING_OBSIDIAN_BLOCK_ADVANCED, "crying_obsidian_block_advanced");
		register(CryingObsidian.CRYING_OBSIDIAN_ITEM, "crying_obsidian_item");
	}

	private static void register(final Item item, final String name) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Utils.ID + ":" + name, "inventory"));
	}

	private static void register(final Block block, final String name) {
		register(Item.getItemFromBlock(block), name);
	}
}*/
