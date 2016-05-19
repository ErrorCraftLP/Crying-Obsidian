package de.errorcraftlp.cryingobsidian.item;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemCryingObsidian extends Item {

	public ItemCryingObsidian() {

		super();

		this.setUnlocalizedName("crying_obsidian_item");
		this.setRegistryName("crying_obsidian_item");
		this.setCreativeTab(CreativeTabs.MISC);

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {

		if(!world.isRemote) {

			final BlockPos playerLocation = player.getPosition();
			player.setSpawnPoint(playerLocation, true);

			if(CryingObsidian.enableChatMessage) {

				player.addChatComponentMessage(new TextComponentTranslation(I18n.translateToLocal("message.spawnpoint_set"), player.getDisplayName(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ()));

			}

		}

		return ActionResult.newResult(EnumActionResult.SUCCESS, stack);

	}

}