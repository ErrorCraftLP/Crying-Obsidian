package de.errorcraftlp.cryingobsidian.tileentiy;

import java.util.UUID;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TileEntityCryingObsidianAdvanced extends TileEntity {
	private UUID ownerUUID;
	private UUID storedUUID;

	public TileEntityCryingObsidianAdvanced() {
		super(CryingObsidian.CRYING_OBSIDIAN_ADVANCED_TILE_ENTITY);
		MinecraftForge.EVENT_BUS.register(this); // Register this class in the event handler
	}

	@Override
	public CompoundNBT write(final CompoundNBT compound) {
		super.write(compound);

		if(ownerUUID != null) {
			compound.putUniqueId("OwnerUUID", ownerUUID);
		}

		if(storedUUID != null) {
			compound.putUniqueId("StoredUUID", storedUUID);
		}

		return compound;
	}

	@Override
	public void read(final CompoundNBT compound) {
		super.read(compound);
		ownerUUID = compound.getUniqueId("OwnerUUID");
		storedUUID = compound.getUniqueId("StoredUUID");
	}

	public UUID getOwnerUUID() {
		return ownerUUID;
	}

	public void setOwnerUUID(final UUID ownerUUID) {
		this.ownerUUID = ownerUUID;
	}

	public UUID getStoredUUID() {
		return storedUUID;
	}

	public void setStoredUUID(final UUID storedUUID) {
		this.storedUUID = storedUUID;
	}

	@SubscribeEvent
	public void onEntityDeath(final LivingDeathEvent event) {
		if(!world.isRemote) {
			final LivingEntity entity = event.getEntityLiving();

			if(entity instanceof MobEntity && getStoredUUID() != null && entity.getUniqueID().equals(getStoredUUID())) {
				event.setCanceled(true);
				entity.revive();
				entity.setHealth(entity.getMaxHealth());
				entity.moveToBlockPosAndAngles(pos.up(), entity.rotationYaw, entity.rotationPitch);

				if(getOwnerUUID() != null) {
					final PlayerEntity player = world.getPlayerByUuid(getOwnerUUID());
					if(player != null) {
						player.sendMessage(new TranslationTextComponent("message.entity_respawned"));
					}
				}
			}
		}
	}
}
