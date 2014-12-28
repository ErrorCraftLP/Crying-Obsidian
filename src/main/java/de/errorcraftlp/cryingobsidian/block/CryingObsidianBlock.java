package de.errorcraftlp.cryingobsidian.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import de.errorcraftlp.cryingobsidian.CryingObsidian;

/**
 * 
 * This is the class of the Crying Obsidian block.
 * 
 * @see Block
 * 
 * @author ErrorCraftLP, Minecraft (Some methods are modified versions of vanilla ones)
 * 
 * @since 1.0.0
 *
 */
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
            
        } else {
        	
            if(iBlockState.getBlock() != this) {
            	
            	return true;
            	
            }
            
            world.setSpawnPoint(blockPos);
            entityPlayer.addChatComponentMessage(new ChatComponentTranslation("Set spawn point to coordinates: x = " + blockPos.getX() + ", y = " + blockPos.getY() + ", z = " + blockPos.getY()));
            
            return true;
            
        }
        
    }
    
}
