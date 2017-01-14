package de.errorcraftlp.cryingobsidian.tileentiy;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCryingObsidianAdvanced extends TileEntity {

	private UUID owner;
	private NBTTagCompound storedEntityNBT;

	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound compound) {

		super.writeToNBT(compound);

		if(owner != null) {

			compound.setUniqueId("Owner", owner);

		}

		if(storedEntityNBT != null) {

			compound.setTag("EntityNBT", storedEntityNBT);

		}

		return compound;

	}

	@Override
	public void readFromNBT(final NBTTagCompound compound) {

		super.readFromNBT(compound);
		owner = compound.getUniqueId("Owner");
		storedEntityNBT = compound.getCompoundTag("EntityNBT");

	}

	public UUID getOwner() {

		return owner;

	}

	public void setOwner(final UUID owner) {

		this.owner = owner;

	}

	public Entity getStoredEntity() {

		return EntityList.createEntityFromNBT(storedEntityNBT, world);

	}

	public void setStoredEntityNBT(final NBTTagCompound entityNBT) {

		storedEntityNBT = entityNBT;

	}

}
