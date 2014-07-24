package net.openindustry.mod.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.openindustry.mod.OpenIndustry;
import net.openindustry.mod.container.ContainerReactantFurnace;
import net.openindustry.mod.gui.GuiReactantFurnace;
import net.openindustry.mod.tileentity.TileEntityReactantFurnace;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x,y,z);
		if(entity != null){
			switch(ID){
			case OpenIndustry.guiIDReactantFurnace:
				if(entity instanceof TileEntityReactantFurnace){
					return new ContainerReactantFurnace(player.inventory, (TileEntityReactantFurnace) entity);
				}
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x,y,z);
		if(entity != null){
			switch(ID){
			case OpenIndustry.guiIDReactantFurnace:
				if(entity instanceof TileEntityReactantFurnace){
					return new GuiReactantFurnace(player.inventory, (TileEntityReactantFurnace) entity);
				}
			}
		}
		return null;
	}
	
}
