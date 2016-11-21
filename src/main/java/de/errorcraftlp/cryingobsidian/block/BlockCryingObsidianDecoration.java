package de.errorcraftlp.cryingobsidian.block;

import java.util.List;
import java.util.Random;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCryingObsidianDecoration extends BlockObsidian {

	public BlockCryingObsidianDecoration() {

		super();
		setUnlocalizedName("crying_obsidian_block_decoration");
		setRegistryName("crying_obsidian_block_decoration");
		setHardness(50.0F);
		setResistance(2000.0F);
		setCreativeTab(CreativeTabs.MISC);

	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {

		return Item.getItemFromBlock(CryingObsidian.cryingObsidianBlockDecoration);

	}


	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean adv) {

		list.add(I18n.format("desc.crying_obsidian_decoration"));

	}

}