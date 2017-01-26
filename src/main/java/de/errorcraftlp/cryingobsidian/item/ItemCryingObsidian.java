package de.errorcraftlp.cryingobsidian.item;

import de.errorcraftlp.cryingobsidian.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemCryingObsidian extends Item {

	public ItemCryingObsidian() {

		super();
		setUnlocalizedName("crying_obsidian_item");
		setRegistryName("crying_obsidian_item");
		setCreativeTab(CreativeTabs.MISC);

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {

		Utils.setSpawnPointAtPlayer(world, player);
		return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));

	}

	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity entity) {

		if(!player.world.isRemote && entity instanceof EntityLiving) {

			final NBTTagCompound itemNBT = stack.getOrCreateSubCompound(Utils.ID);
			itemNBT.setUniqueId("EntityUUID", entity.getUniqueID());

			player.sendMessage(new TextComponentString("The selected entity is now linked to this item. Right click on an advanced Crying Obsidian block where you want the entity to spawn when it dies.")); // TODO Localize

		}

		return true;

	}

}