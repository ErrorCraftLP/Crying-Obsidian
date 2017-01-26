package de.errorcraftlp.cryingobsidian.tileentiy;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TileEntityCryingObsidianAdvanced extends TileEntity {

	private UUID ownerUUID;
	private UUID storedUUID;

	public TileEntityCryingObsidianAdvanced() {

		super();
		MinecraftForge.EVENT_BUS.register(this); // Register this class in the event handler

	}

	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound compound) {

		super.writeToNBT(compound);

		if(ownerUUID != null) {

			compound.setUniqueId("OwnerUUID", ownerUUID);

		}

		if(storedUUID != null) {

			compound.setUniqueId("StoredUUID", storedUUID);

		}

		return compound;

	}

	@Override
	public void readFromNBT(final NBTTagCompound compound) {

		super.readFromNBT(compound);
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

			final EntityLivingBase entity = event.getEntityLiving();

			if(getStoredUUID() != null && entity.getUniqueID().equals(getStoredUUID())) {

				event.setCanceled(true);
				entity.isDead = false;
				entity.setHealth(entity.getMaxHealth());
				entity.moveToBlockPosAndAngles(pos.up(), entity.rotationYaw, entity.rotationPitch);

				if(getOwnerUUID() != null) {

					final EntityPlayer player = world.getPlayerEntityByUUID(getOwnerUUID());

					if(player != null) {

						player.sendMessage(new TextComponentString("An entity that was bound to one of your Crying Obsidian blocks died. It respawned."));

					}

				}

			}

		}

	}

}
