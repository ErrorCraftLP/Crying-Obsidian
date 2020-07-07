package de.errorcraftlp.cryingobsidian.misc;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IFixableData;

public class CryingObsidianDataFixer implements IFixableData {
	@Override
	public int getFixVersion() {
		return 1;
	}

	@Override
	public NBTTagCompound fixTagCompound(final NBTTagCompound compound) {
		final String oldID = compound.getString("id");

		if(oldID.equals("minecraft:crying_obsidian_advanced_tile_entity")) {
			compound.setString("id", Utils.ID + ":crying_obsidian_advanced_tile_entity");
		}

		return compound;
	}
}