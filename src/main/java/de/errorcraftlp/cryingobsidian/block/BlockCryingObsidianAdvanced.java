package de.errorcraftlp.cryingobsidian.block;

import java.util.Random;
import java.util.UUID;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import de.errorcraftlp.cryingobsidian.Utils;
import de.errorcraftlp.cryingobsidian.tileentiy.TileEntityCryingObsidianAdvanced;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

// TODO Should this block get its own texture?
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
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {

		return Item.getItemFromBlock(CryingObsidian.cryingObsidianBlockAdvanced);

	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

		final UUID owner = placer.getUniqueID();
		final TileEntity tileEntity = world.getTileEntity(pos);

		if(tileEntity instanceof TileEntityCryingObsidianAdvanced) {

			((TileEntityCryingObsidianAdvanced)tileEntity).setOwner(owner);

		}

	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

		if(!world.isRemote) {

			final ItemStack heldStack = player.getHeldItem(hand);
			final TileEntity tileEntity = world.getTileEntity(pos);
			UUID owner = null;

			if(tileEntity instanceof TileEntityCryingObsidianAdvanced) {

				owner = ((TileEntityCryingObsidianAdvanced)tileEntity).getOwner();
			}

			if(owner == null) {

				return true;

			}

			if(!player.getUniqueID().equals(owner)) {

				player.sendMessage(new TextComponentTranslation(I18n.translateToLocal("message.not_owner")));
				return true;

			}

			if(heldStack.getItem().equals(CryingObsidian.cryingObsidianItem)) {

				final NBTTagCompound entityNBT = heldStack.getSubCompound(Utils.ID);

				if(entityNBT != null && tileEntity instanceof TileEntityCryingObsidianAdvanced) {

					((TileEntityCryingObsidianAdvanced)tileEntity).setStoredEntityNBT(entityNBT);
					heldStack.removeSubCompound(Utils.ID);

					return true;

				}

			}

			// TODO Only for debugging, remove later
			if(heldStack.getItem().equals(Items.STICK)) {

				if(tileEntity instanceof TileEntityCryingObsidianAdvanced) {

					final Entity entity = ((TileEntityCryingObsidianAdvanced)tileEntity).getStoredEntity();
					world.spawnEntity(entity);

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
	public TileEntity createNewTileEntity(World world, int meta) {

		return new TileEntityCryingObsidianAdvanced();

	}

	@Override
	@Deprecated
	public MapColor getMapColor(IBlockState state) {

		return MapColor.BLACK;

	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {

		return EnumBlockRenderType.MODEL;

	}

}