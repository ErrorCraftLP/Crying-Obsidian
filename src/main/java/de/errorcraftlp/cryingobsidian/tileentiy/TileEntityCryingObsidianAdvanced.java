package de.errorcraftlp.cryingobsidian.tileentiy;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCryingObsidianAdvanced extends TileEntity {

	private UUID owner;
	private Entity storedEntity;

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {

		super.writeToNBT(compound);
		compound.setUniqueId("Owner", owner);
		final NBTTagCompound entityNBT = new NBTTagCompound();
		storedEntity.writeToNBT(entityNBT);
		compound.setTag("EntityNBT", entityNBT);
		return compound;

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);
		owner = compound.getUniqueId("Owner");
		storedEntity = EntityList.createEntityFromNBT(compound.getCompoundTag("EntityNBT"), world);

	}

	public UUID getOwner() {

		return owner;

	}

	public void setOwner(UUID owner) {

		this.owner = owner;

	}

	public Entity getStoredEntity() {

		return storedEntity;

	}

	public void setStoredEntity(Entity storedEntity) {

		this.storedEntity = storedEntity;

	}

}
