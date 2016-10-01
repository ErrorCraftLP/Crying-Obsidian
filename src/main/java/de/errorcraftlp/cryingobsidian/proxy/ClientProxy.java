package de.errorcraftlp.cryingobsidian.proxy;

import de.errorcraftlp.cryingobsidian.CryingObsidian;
import de.errorcraftlp.cryingobsidian.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ClientProxy extends ServerProxy {

	@Override
	public void registerModels() {

		final ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		mesher.register(Item.getItemFromBlock(CryingObsidian.cryingObsidianBlock), 0, new ModelResourceLocation(Utils.ID + ":crying_obsidian_block", "inventory"));
		mesher.register(CryingObsidian.cryingObsidianItem, 0, new ModelResourceLocation(Utils.ID + ":crying_obsidian_item", "inventory"));

	}

}
