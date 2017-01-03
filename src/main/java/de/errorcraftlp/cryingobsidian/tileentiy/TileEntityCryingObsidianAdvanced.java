package de.errorcraftlp.cryingobsidian.tileentiy;

import java.util.UUID;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCryingObsidianAdvanced extends TileEntity {

	private UUID owner;

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {

		super.writeToNBT(compound);
		compound.setUniqueId("Owner", owner);
		return compound;

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);
		owner = compound.getUniqueId("Owner");

	}

	public UUID getOwner() {

		return owner;

	}

	public void setOwner(UUID owner) {

		this.owner = owner;

	}

}
