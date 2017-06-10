package de.errorcraftlp.cryingobsidian.block;

import java.util.Random;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import de.errorcraftlp.cryingobsidian.CryingObsidianConfig;
import de.errorcraftlp.cryingobsidian.Utils;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCryingObsidian extends BlockObsidian {

	public BlockCryingObsidian() {

		super();
		setUnlocalizedName("crying_obsidian_block");
		setRegistryName("crying_obsidian_block");
		setHardness(50.0F);
		setResistance(2000.0F);
		setCreativeTab(CreativeTabs.MISC);

	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {

		return Item.getItemFromBlock(CryingObsidian.cryingObsidianBlock);

	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing side, final float hitX, final float hitY, final float hitZ) {

		if(CryingObsidianConfig.setSpawnPointAtBlock) {

			Utils.setSpawnPointAtBlock(world, player, pos);

		} else {

			Utils.setSpawnPointAtPlayer(world, player);

		}

		return true;

	}

}
