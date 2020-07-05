package de.errorcraftlp.cryingobsidian.item;

import java.util.List;

import javax.annotation.Nullable;

import de.errorcraftlp.cryingobsidian.misc.CryingObsidianConfig;
import de.errorcraftlp.cryingobsidian.misc.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
public class ItemCryingObsidian extends Item {
	public ItemCryingObsidian() {
		super(new Item.Properties().group(ItemGroup.MISC));
		setRegistryName(Utils.ID, "crying_obsidian_item");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final PlayerEntity player, final Hand hand) {
		Utils.setSpawnPointAtPlayer(world, player);
		return ActionResult.newResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final PlayerEntity player, final Entity entity) {
		if(!player.world.isRemote && entity instanceof MobEntity) {
			if(CryingObsidianConfig.enableRespawnWhitelist.get()) {
				final EntityType<?> entityType = entity.getType();
				for(final String whitelistEntry : CryingObsidianConfig.respawnWhitelist.get()) {
					final EntityType<?> entryType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(whitelistEntry));
					if(entryType.equals(entityType)) {
						final CompoundNBT itemNBT = stack.getOrCreateChildTag(Utils.ID);
						itemNBT.putUniqueId("EntityUUID", entity.getUniqueID());

						player.sendMessage(new TranslationTextComponent("message.entity_linked"));
						return true;
					}
				}
				final TranslationTextComponent message = new TranslationTextComponent("message.entity_whitelist");
				message.getStyle().setColor(TextFormatting.RED);
				player.sendMessage(message);
			} else {
				final CompoundNBT itemNBT = stack.getOrCreateChildTag(Utils.ID);
				itemNBT.putUniqueId("EntityUUID", entity.getUniqueID());

				player.sendMessage(new TranslationTextComponent("message.entity_linked"));
			}
		}
		return true;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(final ItemStack stack, @Nullable final World world, final List<ITextComponent> tooltip, final ITooltipFlag tooltipFlag) {
		final CompoundNBT itemNBT = stack.getChildTag(Utils.ID);

		if(itemNBT != null && itemNBT.getUniqueId("EntityUUID") != null) {
			tooltip.add(new TranslationTextComponent("desc.crying_obsidian_item"));
		}
	}
}