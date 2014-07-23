package net.openindustry.mod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.openindustry.mod.OpenIndustry;

public class MachineFrame extends Block{
	public MachineFrame(Material material){
		super(material);
		this.setHardness(4.0f);
		this.setResistance(5.0f);
		this.setStepSound(soundTypeMetal);
		
		this.setCreativeTab(OpenIndustry.openIndustryTab);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(OpenIndustry.modid + ":" + this.getUnlocalizedName().substring(5));
	}

}
