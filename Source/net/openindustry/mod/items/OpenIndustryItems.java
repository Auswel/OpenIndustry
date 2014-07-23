package net.openindustry.mod.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.openindustry.mod.OpenIndustry;

public class OpenIndustryItems extends Item{

	public OpenIndustryItems(){
		this.setCreativeTab(OpenIndustry.openIndustryTab);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(OpenIndustry.modid + ":" + this.getUnlocalizedName().substring(5));
	}
}
