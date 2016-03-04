package de.errorcraftlp.cryingobsidian.block;

import java.util.Random;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockCryingObsidian extends BlockObsidian {

	public BlockCryingObsidian() {

		super();

		this.setUnlocalizedName("crying_obsidian_block");
		//this.setRegistryName("crying_obsidian_block");
		this.setHardness(50.0F);
		this.setResistance(2000.0F);
		this.setStepSound(soundTypePiston);
		this.setCreativeTab(CreativeTabs.tabMisc);

	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {

		return Item.getItemFromBlock(CryingObsidian.cryingObsidianBlock);

	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {

		if(!world.isRemote && state.getBlock() == this) {

			final BlockPos playerLocation = player.getPosition();
			player.setSpawnPoint(playerLocation, true);

			if(CryingObsidian.enableChatMessage) {

				player.addChatComponentMessage(new ChatComponentTranslation(StatCollector.translateToLocal("message.spawnpoint_set"), player.getDisplayName(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ()));

			}

		}

		return true;

	}

}
