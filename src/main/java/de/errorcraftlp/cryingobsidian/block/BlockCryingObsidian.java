package de.errorcraftlp.cryingobsidian.block;

import java.util.Random;

import javax.annotation.Nullable;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class BlockCryingObsidian extends BlockObsidian {

	public BlockCryingObsidian() {

		super();

		this.setUnlocalizedName("crying_obsidian_block");
		this.setRegistryName("crying_obsidian_block");
		this.setHardness(50.0F);
		this.setResistance(2000.0F);
		this.setSoundType(SoundType.STONE);
		this.setCreativeTab(CreativeTabs.MISC);

	}

	@Override
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {

		return Item.getItemFromBlock(CryingObsidian.cryingObsidianBlock);

	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

		if(!world.isRemote && state.getBlock() == this) {

			final BlockPos playerLocation = player.getPosition();
			player.setSpawnPoint(playerLocation, true);

			if(CryingObsidian.enableChatMessage) {

				player.addChatComponentMessage(new TextComponentTranslation(I18n.translateToLocal("message.spawnpoint_set"), player.getDisplayName(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ()));

			}

		}

		return true;

	}

}
