package net.openindustry.mod.blocks;

import java.util.Random;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.openindustry.mod.OpenIndustry;
import net.openindustry.mod.tileentity.TileEntityReactantFurnace;

public class ReactantFurnace extends BlockContainer {

	private boolean isActive;
	private static boolean keepInventory;
	private Random rand = new Random();
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	public ReactantFurnace(boolean isActive) {
		super(Material.iron);
		this.isActive = isActive;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(OpenIndustry.modid + ":"
				+ "ReactantFurnaceSide");
		this.iconTop = iconRegister.registerIcon(OpenIndustry.modid + ":"
				+ "ReactantFurnaceTop");
		this.iconFront = iconRegister.registerIcon(OpenIndustry.modid
				+ ":"
				+ (this.isActive ? "ReactantFurnaceFrontActive"
						: "ReactantFurnaceFrontIdle"));
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return side == 1 ? this.iconTop : (side == 0 ? this.iconTop
				: (side != metadata ? this.blockIcon : this.iconFront));
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

			if (block.func_149730_j() && !block2.func_149730_j()) {
				byt = 3;
			}
			if (block2.func_149730_j() && !block.func_149730_j()) {
				byt = 2;
			}
			if (block3.func_149730_j() && !block4.func_149730_j()) {
				byt = 5;
			}
			if (block4.func_149730_j() && !block3.func_149730_j()) {
				byt = 4;
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
		if (a == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
		if (a == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		if (a == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
		if (item.hasDisplayName()) {
			((TileEntityReactantFurnace) world.getTileEntity(x, y, z))
					.setGuiDisplayName(item.getDisplayName());
		}
	}

	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, OpenIndustry.instance,
					OpenIndustry.guiIDReactantFurnace, world, x, y, z);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityReactantFurnace();
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z,
			Random random) {
		if (this.isActive) {
			int direction = world.getBlockMetadata(x, y, z);

			float x1 = (float) x + 0.5F;
			float y1 = (float) y + random.nextFloat();
			float z1 = (float) z + 0.5F;

			float f = 0.52F;
			float f1 = random.nextFloat() * 0.6F - 0.3F;

			if (direction == 4) {
				world.spawnParticle("smoke", (double) (x1 - f), (double) (y1),
						(double) (z1 + f1), 0D, 0D, 0D);
				world.spawnParticle("flame", (double) (x1 - f), (double) (y1),
						(double) (z1 + f1), 0D, 0D, 0D);
			}

			if (direction == 5) {
				world.spawnParticle("smoke", (double) (x1 + f), (double) (y1),
						(double) (z1 + f1), 0D, 0D, 0D);
				world.spawnParticle("flame", (double) (x1 + f), (double) (y1),
						(double) (z1 + f1), 0D, 0D, 0D);
			}

			if (direction == 2) {
				world.spawnParticle("smoke", (double) (x1 + f1), (double) (y1),
						(double) (z1 - f), 0D, 0D, 0D);
				world.spawnParticle("flame", (double) (x1 + f1), (double) (y1),
						(double) (z1 - f), 0D, 0D, 0D);
			}

			if (direction == 3) {
				world.spawnParticle("smoke", (double) (x1 + f1), (double) (y1),
						(double) (z1 + f), 0D, 0D, 0D);
				world.spawnParticle("flame", (double) (x1 + f1), (double) (y1),
						(double) (z1 + f), 0D, 0D, 0D);
			}
		}
	}

	public static void updateReactantFurnaceBlockState(boolean active,
			World worldObj, int xCoord, int yCoord, int zCoord) {
		int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

		TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
		keepInventory = true;
		if (active) {
			worldObj.setBlock(xCoord, yCoord, zCoord,
					OpenIndustry.machReactantFurnace_Active);
		} else {
			worldObj.setBlock(xCoord, yCoord, zCoord,
					OpenIndustry.machReactantFurnace_Idle);
		}
		keepInventory = true;

		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

		if (tileentity != null) {
			tileentity.validate();
			worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
		}
	}

	public void breakBlock(World world, int x, int y, int z, Block oldblock,
			int oldMetadata) {
		if (!keepInventory) {
			TileEntityReactantFurnace tileentity = (TileEntityReactantFurnace) world
					.getTileEntity(x, y, z);

			if (tileentity != null) {
				for (int i = 0; i < tileentity.getSizeInventory(); i++) {
					ItemStack itemstack = tileentity.getStackInSlot(i);

					if (itemstack != null) {
						float f = this.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

						while (itemstack.stackSize > 0) {
							int j = this.rand.nextInt(21) + 10;

							if (j > itemstack.stackSize) {
								j = itemstack.stackSize;
							}

							itemstack.stackSize -= j;

							EntityItem item = new EntityItem(world,
									(double) ((float) x + f),
									(double) ((float) y + f1),
									(double) ((float) z + f2), new ItemStack(
											itemstack.getItem(), j,
											itemstack.getItemDamage()));

							if (itemstack.hasTagCompound()) {
								item.getEntityItem().setTagCompound(
										(NBTTagCompound) itemstack
												.getTagCompound().copy());
							}

							world.spawnEntityInWorld(item);
						}
					}
				}

				world.func_147453_f(x, y, z, oldblock);
			}
		}

		super.breakBlock(world, x, y, z, oldblock, oldMetadata);
	}

}
