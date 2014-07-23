package net.openindustry.mod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.openindustry.mod.OpenIndustry;

public class ReactiveOre extends Ore{
	public ReactiveOre(Material material){
		super(material,3.0f,5.0f);
	}
}
