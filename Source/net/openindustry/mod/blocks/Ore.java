package net.openindustry.mod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.openindustry.mod.OpenIndustry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Ore extends Block {
	public Ore(Material material, Float hardness, Float resistance){
		super(material);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setStepSound(soundTypeStone);
		
		this.setCreativeTab(OpenIndustry.openIndustryTab);
	}
	
	public Item getItemDropped(int i, Random random, int j){
		return this == OpenIndustry.oreSulfuricOre ? OpenIndustry.itemSulfuricDust : Item.getItemFromBlock(this);
	}
	public int quantityDropped(Random random){
		return this == OpenIndustry.oreSulfuricOre ? 3 + random.nextInt(5) : 1;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(OpenIndustry.modid + ":" + this.getUnlocalizedName().substring(5));
	}

}
