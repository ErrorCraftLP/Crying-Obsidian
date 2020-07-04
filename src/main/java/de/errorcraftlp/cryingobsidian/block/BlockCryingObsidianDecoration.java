package de.errorcraftlp.cryingobsidian.block;

import java.util.List;

import javax.annotation.Nullable;

import de.errorcraftlp.cryingobsidian.misc.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockCryingObsidianDecoration extends Block {
	public BlockCryingObsidianDecoration() {
		super(Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(50.0F, 2000.0F));
		setRegistryName(Utils.ID, "crying_obsidian_block_decoration");
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(final ItemStack stack, @Nullable final IBlockReader world, final List<ITextComponent> tooltip, final ITooltipFlag tooltipFlag) {
		tooltip.add(new TranslationTextComponent("desc.crying_obsidian_decoration"));
	}
}