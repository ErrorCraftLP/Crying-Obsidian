package de.errorcraftlp.cryingobsidian;

import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidian;
import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidianAdvanced;
import de.errorcraftlp.cryingobsidian.block.BlockCryingObsidianDecoration;
import de.errorcraftlp.cryingobsidian.item.ItemCryingObsidian;
import de.errorcraftlp.cryingobsidian.misc.CryingObsidianConfig;
import de.errorcraftlp.cryingobsidian.misc.Utils;
import de.errorcraftlp.cryingobsidian.tileentiy.TileEntityCryingObsidianAdvanced;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(Utils.ID)
public class CryingObsidian {
	// Blocks/Items/Tile Entities
	@ObjectHolder(Utils.ID + ":crying_obsidian_block")
	public static final Block CRYING_OBSIDIAN_BLOCK = null;
	@ObjectHolder(Utils.ID + ":crying_obsidian_block_advanced")
	public static final Block CRYING_OBSIDIAN_BLOCK_ADVANCED = null;
	@ObjectHolder(Utils.ID + ":crying_obsidian_block_decoration")
	public static final Block CRYING_OBSIDIAN_BLOCK_DECORATION = null;
	@ObjectHolder(Utils.ID + ":crying_obsidian_item")
	public static final Item CRYING_OBSIDIAN_ITEM = null;
	@ObjectHolder(Utils.ID + ":crying_obsidian_advanced_tile_entity")
	public static final TileEntityType<?> CRYING_OBSIDIAN_ADVANCED_TILE_ENTITY = null;

	public CryingObsidian() {
		// Register configuration
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CryingObsidianConfig.commonSpec);
	}

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();
		registry.registerAll(new BlockCryingObsidian(), new BlockCryingObsidianAdvanced(), new BlockCryingObsidianDecoration());
	}

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		// Block Items
		registry.register(new BlockItem(CRYING_OBSIDIAN_BLOCK, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(CRYING_OBSIDIAN_BLOCK.getRegistryName()));
		registry.register(new BlockItem(CRYING_OBSIDIAN_BLOCK_ADVANCED, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(CRYING_OBSIDIAN_BLOCK_ADVANCED.getRegistryName()));
		registry.register(new BlockItem(CRYING_OBSIDIAN_BLOCK_DECORATION, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(CRYING_OBSIDIAN_BLOCK_DECORATION.getRegistryName()));

		// Item
		registry.register(new ItemCryingObsidian());
	}

	@SubscribeEvent
	public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
		final IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
		registry.register(TileEntityType.Builder.create(TileEntityCryingObsidianAdvanced::new, CRYING_OBSIDIAN_BLOCK_ADVANCED).build(null).setRegistryName(Utils.ID, "crying_obsidian_advanced_tile_entity"));
	}
}
