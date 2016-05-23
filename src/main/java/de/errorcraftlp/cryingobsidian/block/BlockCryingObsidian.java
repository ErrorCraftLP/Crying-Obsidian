package de.errorcraftlp.cryingobsidian.block;

import java.util.Random;

import javax.annotation.Nullable;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import de.errorcraftlp.cryingobsidian.Utils;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCryingObsidian extends BlockObsidian {

	public BlockCryingObsidian() {

		super();
		this.setUnlocalizedName("crying_obsidian_block");
		this.setRegistryName("crying_obsidian_block");
		this.setHardness(50.0F);
		this.setResistance(2000.0F);
		this.setCreativeTab(CreativeTabs.MISC);

	}

	@Override
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {

		return Item.getItemFromBlock(CryingObsidian.cryingObsidianBlock);

	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

		Utils.setSpawnPoint(world, player);
		return true;

	}

}
