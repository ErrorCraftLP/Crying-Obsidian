package de.errorcraftlp.cryingobsidian.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import de.errorcraftlp.cryingobsidian.CryingObsidian;

/**
 *
 * This is the class of the Crying Obsidian {@link Block block}.
 *
 * @see Block
 *
 * @author ErrorCraftLP
 *
 * @since 1.0.0
 *
 */
public class BlockCryingObsidian extends Block {

	public BlockCryingObsidian() {

		super(Material.rock);

		this.setUnlocalizedName("cryingObsidian");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(50.0F);
		this.setResistance(2000.0F);
		this.setStepSound(soundTypePiston);

	}

	@Override
	public MapColor getMapColor(IBlockState iBlockState) {

		return MapColor.obsidianColor;

	}

	@Override
	public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState iBlockState, EntityPlayer entityPlayer, EnumFacing enumFacing, float hitX, float hitY, float hitZ) {

		if(world.isRemote) {

			return true;

		} else {

			if(iBlockState.getBlock() != this) {

				return true;

			}

			BlockPos playerLocation = entityPlayer.getPosition();

			entityPlayer.setSpawnPoint(playerLocation, true);

			if(CryingObsidian.enableChatMessage) {

				entityPlayer.addChatComponentMessage(new ChatComponentTranslation(StatCollector.translateToLocal("chat.cryingObsidianBlock"), entityPlayer.getDisplayName(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ()));

			}

			return true;

		}

	}

}
