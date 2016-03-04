package de.errorcraftlp.cryingobsidian.item;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemCryingObsidian extends Item {

	public ItemCryingObsidian() {

		super();

		this.setUnlocalizedName("crying_obsidian_item");
		//this.setRegistryName("crying_obsidian_item");
		this.setCreativeTab(CreativeTabs.tabMisc);

	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

		if(!world.isRemote) {

			final BlockPos playerLocation = player.getPosition();
			player.setSpawnPoint(playerLocation, true);

			if(CryingObsidian.enableChatMessage) {

				player.addChatComponentMessage(new ChatComponentTranslation(StatCollector.translateToLocal("message.spawnpoint_set"), player.getDisplayName(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ()));

			}

		}

		return stack;

	}

}