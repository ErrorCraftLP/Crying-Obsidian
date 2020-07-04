package de.errorcraftlp.cryingobsidian;

import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidian;
import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidianAdvanced;
import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidianDecoration;
import de.errorcraftlp.cryingobsidian.item.ItemCryingObsidian;
import de.errorcraftlp.cryingobsidian.misc.Utils;
import de.errorcraftlp.cryingobsidian.tileentiy.TileEntityCryingObsidianAdvanced;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(Utils.ID)
public class CryingObsidian {
	// Blocks/Items/Tile Entities
	public static final Block CRYING_OBSIDIAN_BLOCK = new BlockCryingObsidian();
	public static final Block CRYING_OBSIDIAN_BLOCK_ADVANCED = new BlockCryingObsidianAdvanced();
	public static final Block CRYING_OBSIDIAN_BLOCK_DECORATION = new BlockCryingObsidianDecoration();
	public static final Item CRYING_OBSIDIAN_ITEM = new ItemCryingObsidian();
	public static final TileEntityType<?> CRYING_OBSIDIAN_ADVANCED_TILE_ENTITY = TileEntityType.Builder.create(TileEntityCryingObsidianAdvanced::new).build(null).setRegistryName(Utils.ID, "crying_obsidian_advanced_tile_entity");

	public CryingObsidian() {
        // ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
	}

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();
		registry.registerAll(CRYING_OBSIDIAN_BLOCK, CRYING_OBSIDIAN_BLOCK_ADVANCED, CRYING_OBSIDIAN_BLOCK_DECORATION);
	}

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		// Block Items
		registry.register(new BlockItem(CRYING_OBSIDIAN_BLOCK, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(CRYING_OBSIDIAN_BLOCK.getRegistryName()));
		registry.register(new BlockItem(CRYING_OBSIDIAN_BLOCK_ADVANCED, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(CRYING_OBSIDIAN_BLOCK_ADVANCED.getRegistryName()));
		registry.register(new BlockItem(CRYING_OBSIDIAN_BLOCK_DECORATION, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(CRYING_OBSIDIAN_BLOCK_DECORATION.getRegistryName()));

		// Item
		registry.register(CRYING_OBSIDIAN_ITEM);
	}

	@SubscribeEvent
	public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
		final IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
		registry.register(CRYING_OBSIDIAN_ADVANCED_TILE_ENTITY);
	}

	/*@EventHandler
	public void init(@SuppressWarnings("unused") final FMLInitializationEvent event) {
		// Register crying obsidian blocks in ore dictionary
		OreDictionary.registerOre("obsidian", CRYING_OBSIDIAN_BLOCK);
		OreDictionary.registerOre("obsidian", CRYING_OBSIDIAN_BLOCK_DECORATION);
		OreDictionary.registerOre("obsidian", CRYING_OBSIDIAN_BLOCK_ADVANCED);
	}*/
}
