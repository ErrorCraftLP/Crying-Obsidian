package de.errorcraftlp.cryingobsidian.item;

import de.errorcraftlp.cryingobsidian.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemCryingObsidian extends Item {

	public ItemCryingObsidian() {

		super();
		setUnlocalizedName("crying_obsidian_item");
		setRegistryName("crying_obsidian_item");
		setCreativeTab(CreativeTabs.MISC);

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

		Utils.setSpawnPointAtPlayer(world, player);
		return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));

	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {

		if(!player.world.isRemote) {

			final NBTTagCompound entityNBT = stack.getOrCreateSubCompound(Utils.ID);
			entity.writeToNBTAtomically(entityNBT);

		}

		return true;

	}

}