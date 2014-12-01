package de.errorcraftlp.cryingobsidian.block;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class CryingObsidianBlock extends Block {

	public CryingObsidianBlock(Material material) {
		
		super(material);
		this.setCreativeTab(CryingObsidian.tabCryingObsidian);
		
		this.setHardness(50.0F);
		this.setResistance(2000.0F);
		this.setStepSound(soundTypePiston);
		
		
	}
	
    public MapColor getMapColor(IBlockState iBlockState) {
    	
        return MapColor.obsidianColor;
        
    }
    
    public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState iBlockState, EntityPlayer entityPlayer, EnumFacing enumFacing, float hitX, float hitY, float hitZ) {
    	
    	if(world.isRemote) {
    		
    		return true;
    		
    	}
    	
    	return false;
    	
    }
	
}
