package de.errorcraftlp.cryingobsidian.block;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import de.errorcraftlp.cryingobsidian.Utils;
import de.errorcraftlp.cryingobsidian.tileentiy.TileEntityCryingObsidianAdvanced;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// TODO This block should get its own texture
public class BlockCryingObsidianAdvanced extends BlockContainer {

	public BlockCryingObsidianAdvanced() {

		super(Material.ROCK);
		setUnlocalizedName("crying_obsidian_block_advanced");
		setRegistryName("crying_obsidian_block_advanced");
		setHardness(50.0F);
		setResistance(2000.0F);
		setCreativeTab(CreativeTabs.MISC);

	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {

		return Item.getItemFromBlock(CryingObsidian.cryingObsidianBlockAdvanced);

	}

	@Override
	public void onBlockPlacedBy(final World world, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {

		final UUID owner = placer.getUniqueID();
		final TileEntity tileEntity = world.getTileEntity(pos);

		if(tileEntity instanceof TileEntityCryingObsidianAdvanced) {

			((TileEntityCryingObsidianAdvanced)tileEntity).setOwnerUUID(owner);

		}

	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final ItemStack heldItem, final EnumFacing side, final float hitX, final float hitY, final float hitZ) {

		if(!world.isRemote) {

			final ItemStack heldStack = player.getHeldItem(hand);
			final TileEntity tileEntity = world.getTileEntity(pos);
			UUID owner = null;

			if(tileEntity instanceof TileEntityCryingObsidianAdvanced) {

				owner = ((TileEntityCryingObsidianAdvanced)tileEntity).getOwnerUUID();
			}

			if(CryingObsidian.enableAdvancedCryingObsidianOwner && owner == null) {

				return true;

			}

			if(CryingObsidian.enableAdvancedCryingObsidianOwner && !player.getUniqueID().equals(owner)) {

				player.sendMessage(new TextComponentTranslation("message.not_owner"));
				return true;

			}

			if(heldStack != null && heldStack.getItem().equals(CryingObsidian.cryingObsidianItem)) {

				final NBTTagCompound itemNBT = heldStack.getSubCompound(Utils.ID, false);

				if(itemNBT != null && tileEntity instanceof TileEntityCryingObsidianAdvanced) {

					((TileEntityCryingObsidianAdvanced)tileEntity).setStoredUUID(itemNBT.getUniqueId("EntityUUID"));

					if(heldStack.getTagCompound() != null) {

						heldStack.getTagCompound().removeTag(Utils.ID);

					}

					player.sendMessage(new TextComponentTranslation("message.entity_spawn_here"));

					return true;

				}

			}

			if(CryingObsidian.setSpawnPointAtBlock) {

				Utils.setSpawnPointAtBlock(world, player, pos);

			} else {

				Utils.setSpawnPointAtPlayer(world, player);

			}

		}

		return true;

	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {

		return new TileEntityCryingObsidianAdvanced();

	}

	@Override
	@Deprecated
	public MapColor getMapColor(final IBlockState state) {

		return MapColor.BLACK;

	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {

		return EnumBlockRenderType.MODEL;

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List<String> tooltip, final boolean advanced) {

		tooltip.add(I18n.format("desc.crying_obsidian_advanced"));

	}

}