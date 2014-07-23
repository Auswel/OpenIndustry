package net.openindustry.mod.handler;

import net.minecraft.item.ItemStack;
import net.openindustry.mod.OpenIndustry;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() == OpenIndustry.itemReactiveCluster) return 400;
		return 0;
	}

	
}
