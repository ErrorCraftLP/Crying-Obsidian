package de.errorcraftlp.cryingobsidian.item;

import java.util.List;

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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

			player.sendMessage(new TextComponentTranslation(I18n.translateToLocal("message.entity_linked")));

		}

		return true;

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List<String> tooltip, final boolean advanced) {

		final NBTTagCompound itemNBT = stack.getSubCompound(Utils.ID);

		if(itemNBT != null && itemNBT.getUniqueId("EntityUUID") != null) {

			tooltip.add(net.minecraft.client.resources.I18n.format("desc.crying_obsidian_item")); // Can't use an import here because there are two I18n classes

		}

	}

}