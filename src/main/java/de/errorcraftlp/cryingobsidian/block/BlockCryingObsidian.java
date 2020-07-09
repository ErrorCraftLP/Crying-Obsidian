package de.errorcraftlp.cryingobsidian.block;

import de.errorcraftlp.cryingobsidian.misc.CryingObsidianConfig;
import de.errorcraftlp.cryingobsidian.misc.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class BlockCryingObsidian extends Block {
	public BlockCryingObsidian() {
		super(Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(50.0F, 2000.0F));
		setRegistryName(Utils.ID, "crying_obsidian_block");
	}

	@Override
	public ActionResultType onBlockActivated(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult hit) {
		if(CryingObsidianConfig.setSpawnPointAtBlock.get()) {
			Utils.setSpawnPointAtBlock(world, player, pos);
		} else {
			Utils.setSpawnPointAtPlayer(world, player);
		}
		return ActionResultType.SUCCESS;
	}
}
