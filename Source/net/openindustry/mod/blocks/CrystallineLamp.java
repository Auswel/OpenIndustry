package net.openindustry.mod.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.openindustry.mod.OpenIndustry;

public class CrystallineLamp extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	public CrystallineLamp() {
		super(Material.glass);
		this.setLightLevel(0.750f);
		this.setCreativeTab(OpenIndustry.openIndustryTab);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(OpenIndustry.modid + ":"
				+ "CrystallineLampSide");
		this.iconTop = iconRegister.registerIcon(OpenIndustry.modid + ":"
				+ "CrystallineLampTop");
		this.iconFront = iconRegister.registerIcon(OpenIndustry.modid + ":"
				+ "CrystallineLampSide");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return side == 1 ? this.iconTop : (side == 0 ? this.iconTop
				: (side != metadata ? this.blockIcon : this.iconFront));
	}

	public Item getItemDropped(World world, int x, int y, int z) {
		return Item.getItemFromBlock(OpenIndustry.machCrystallineLamp);
	}
}
