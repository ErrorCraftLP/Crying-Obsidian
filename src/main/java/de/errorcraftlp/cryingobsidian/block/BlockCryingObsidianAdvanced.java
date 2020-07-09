package de.errorcraftlp.cryingobsidian.block;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import de.errorcraftlp.cryingobsidian.misc.CryingObsidianConfig;
import de.errorcraftlp.cryingobsidian.misc.Utils;
import de.errorcraftlp.cryingobsidian.tileentiy.TileEntityCryingObsidianAdvanced;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;

public class BlockCryingObsidianAdvanced extends Block {
	public BlockCryingObsidianAdvanced() {
		super(Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(50.0F, 2000.0F));
		setRegistryName(Utils.ID, "crying_obsidian_block_advanced");
	}

	@Override
	public void onBlockPlacedBy(final World world, final BlockPos pos, final BlockState state, @Nullable final LivingEntity placer, final ItemStack stack) {
		final UUID owner = placer.getUniqueID();
		final TileEntity tileEntity = world.getTileEntity(pos);

		if(tileEntity instanceof TileEntityCryingObsidianAdvanced) {
			((TileEntityCryingObsidianAdvanced)tileEntity).setOwnerUUID(owner);
		}
	}

	@Override
	public ActionResultType onBlockActivated(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult hit) {
		if(!world.isRemote) {
			final ItemStack heldStack = player.getHeldItem(hand);
			final TileEntity tileEntity = world.getTileEntity(pos);
			UUID owner = null;

			if(tileEntity instanceof TileEntityCryingObsidianAdvanced) {
				owner = ((TileEntityCryingObsidianAdvanced)tileEntity).getOwnerUUID();
			}

			if(CryingObsidianConfig.enableOwnerSystem.get() && owner == null) {
				return ActionResultType.FAIL;
			}

			if(CryingObsidianConfig.enableOwnerSystem.get() && !player.getUniqueID().equals(owner)) {
				final TranslationTextComponent message = new TranslationTextComponent("message.not_owner");
				message.getStyle().setColor(TextFormatting.RED);
				player.sendMessage(message);
				return ActionResultType.FAIL;
			}

			if(heldStack.getItem().equals(CryingObsidian.CRYING_OBSIDIAN_ITEM)) {
				final CompoundNBT itemNBT = heldStack.getChildTag(Utils.ID);

				if(itemNBT != null && tileEntity instanceof TileEntityCryingObsidianAdvanced) {
					((TileEntityCryingObsidianAdvanced)tileEntity).setStoredUUID(itemNBT.getUniqueId("EntityUUID"));
					heldStack.removeChildTag(Utils.ID);

					player.sendMessage(new TranslationTextComponent("message.entity_spawn_here"));
				}

				return ActionResultType.SUCCESS;
			}

			if(CryingObsidianConfig.setSpawnPointAtBlock.get()) {
				Utils.setSpawnPointAtBlock(world, player, pos);
			} else {
				Utils.setSpawnPointAtPlayer(world, player);
			}
		}

		return ActionResultType.SUCCESS;
	}

	@Override
	public boolean hasTileEntity(final BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
		return new TileEntityCryingObsidianAdvanced();
	}

	@Override
	@Deprecated
	public void onReplaced(final BlockState state, final World world, final BlockPos pos, final BlockState newState, final boolean isMoving) {
		if(state.getBlock() != newState.getBlock()) {
			MinecraftForge.EVENT_BUS.unregister(world.getTileEntity(pos)); // Don't respawn the entity after the block was broken
		}
		super.onReplaced(state, world, pos, newState, isMoving);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(final ItemStack stack, @Nullable final IBlockReader world, final List<ITextComponent> tooltip, final ITooltipFlag tooltipFlag) {
		tooltip.add(new TranslationTextComponent("desc.crying_obsidian_advanced"));

		if(CryingObsidianConfig.enableRespawnWhitelist.get()) {
			if(CryingObsidianConfig.respawnWhitelist.get().isEmpty()) {
				final TranslationTextComponent tooltipEntry = new TranslationTextComponent("desc.entity_whitelist_empty");
				tooltipEntry.getStyle().setColor(TextFormatting.RED);
				tooltip.add(tooltipEntry);
			} else {
				final TranslationTextComponent tooltipEntry = new TranslationTextComponent("desc.entity_whitelist_enabled");
				tooltipEntry.getStyle().setColor(TextFormatting.RED);
				tooltip.add(tooltipEntry);
			}
		}

		if(!CryingObsidianConfig.enableOwnerSystem.get()) {
			final TranslationTextComponent tooltipEntry = new TranslationTextComponent("desc.owner_system_disabled");
			tooltipEntry.getStyle().setColor(TextFormatting.RED);
			tooltip.add(tooltipEntry);
		}
	}
}