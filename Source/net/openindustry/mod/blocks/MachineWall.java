package net.openindustry.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.openindustry.mod.OpenIndustry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MachineWall extends Block{
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	public MachineWall() {
		super(Material.iron);
		this.setCreativeTab(OpenIndustry.openIndustryTab);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(OpenIndustry.modid + ":"
				+ "MachineWall");
		this.iconTop = iconRegister.registerIcon(OpenIndustry.modid + ":"
				+ "MachineFrame");
		this.iconFront = iconRegister.registerIcon(OpenIndustry.modid + ":"
				+ "MachineWall");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return side == 1 ? this.iconTop : (side == 0 ? this.iconTop
				: (side != metadata ? this.blockIcon : this.iconFront));
	}
}
