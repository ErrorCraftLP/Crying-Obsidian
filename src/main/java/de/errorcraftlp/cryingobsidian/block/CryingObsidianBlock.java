package de.errorcraftlp.cryingobsidian.block;

import java.util.Iterator;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * 
 * @author ErrorCraftLP, Minecraft (Some methods are modified versions of vanilla ones)
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

            if(world.provider.canRespawnHere() && world.getBiomeGenForCoords(blockPos) != BiomeGenBase.hell) {
            	
                    EntityPlayer entityPlayer1 = this.getPlayerInBed(world, blockPos);

                    if (entityPlayer1 != null) {
                    	
                        return true;
                        
                    }

                    world.setBlockState(blockPos, iBlockState, 4);

                EntityPlayer.EnumStatus enumStatus = entityPlayer.trySleep(blockPos);

                if(enumStatus == EntityPlayer.EnumStatus.OK) {
                	
                    world.setBlockState(blockPos, iBlockState, 4);
                    
                    return true;
                    
                } else {
                	
                    if(enumStatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW) {
                    	
                        entityPlayer.addChatComponentMessage(new ChatComponentTranslation("Currently, you can only set the spawn point at night.", new Object[0]));
                        
                    } else if (enumStatus == EntityPlayer.EnumStatus.NOT_SAFE) {
                    	
                        entityPlayer.addChatComponentMessage(new ChatComponentTranslation("You cannot set your spawn point if they are monsters nearby!", new Object[0]));
                        
                    }

                    return true;
                    
                }
                
            } else {
            	
                world.newExplosion((Entity)null, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D, 5.0F, true, true);
                return true;
                
            }
            
        }
        
    }

    public EntityPlayer getPlayerInBed(World world, BlockPos blockPos) {
    	
        Iterator iterator = world.playerEntities.iterator();
        EntityPlayer entityPlayer;

        do {
        	
            if(!iterator.hasNext()) {
            	
                return null;
                
            }

            entityPlayer = (EntityPlayer)iterator.next();
            
        } while(!entityPlayer.isPlayerSleeping() || !entityPlayer.playerLocation.equals(blockPos));

        return entityPlayer;
        
    }
    
}
