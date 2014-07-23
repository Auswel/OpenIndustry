package net.openindustry.mod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.openindustry.mod.OpenIndustry;

public class ReactantFurnace extends BlockContainer {

	private boolean isActive;

	@SideOnly(Side.CLIENT)
	private IIcon iconFront;

	public ReactantFurnace(boolean isActive) {
		super(Material.iron);
		this.isActive = isActive;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(OpenIndustry.modid + ":"
				+ "ReactantFurnaceSide");
		this.iconFront = iconRegister.registerIcon(OpenIndustry.modid
				+ ":"
				+ (this.isActive ? "ReactantFurnaceFrontActive"
						: "ReactantFurnaceFrontIdle"));
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return side == 3 ? this.iconFront : side == 1 ? this.blockIcon
				: (side != metadata ? this.blockIcon : this.blockIcon);
	}

	public Item getItemDropped(World world, int x, int y, int z) {
		return Item.getItemFromBlock(OpenIndustry.machReactantFurnace_Idle);
	}

	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDirection(world, x, y, z);
	}

	private void setDirection(World world, int x, int y, int z) {
		if (!(world.isRemote)) {
			Block block = world.getBlock(x, y, z - 1);
			Block block2 = world.getBlock(x, y, z + 1);
			Block block3 = world.getBlock(x - 1, y, z);
			Block block4 = world.getBlock(x + 1, y, z);

			byte byt = 3;
			if (block2.func_149730_j() && !block.func_149730_j()) {
				byt = 2;
			}
			if (block.func_149730_j() && !block2.func_149730_j()) {
				byt = 3;
			}
			if (block4.func_149730_j() && !block3.func_149730_j()) {
				byt = 4;
			}
			if (block3.func_149730_j() && !block4.func_149730_j()) {
				byt = 5;
			}
			world.setBlockMetadataWithNotify(x, y, z, byt, 2);
		}
	}

	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase player, ItemStack item) {
		int a = MathHelper
				.floor_double((double) (player.rotationYaw * 4.0f / 360.0f) + 0.5D) & 3;
		if (a == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
		if (a == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		if (a == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
		if (a == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return null;
	}

}
