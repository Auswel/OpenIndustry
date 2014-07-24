package net.openindustry.mod.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.openindustry.mod.OpenIndustry;
import net.openindustry.mod.tileentity.TileEntityReactantFurnace;
import net.openindustry.mod.container.ContainerReactantFurnace;

public class GuiReactantFurnace extends GuiContainer{

	public TileEntityReactantFurnace reactantFurnace;
	
	public static final ResourceLocation bground = new ResourceLocation(OpenIndustry.modid +":"+"textures/gui/guireactantfurnace.png");
	
	public GuiReactantFurnace(InventoryPlayer inventoryPlayer, TileEntity entity) {
		super(new ContainerReactantFurnace(inventoryPlayer,(TileEntityReactantFurnace) entity));
		this.reactantFurnace = (TileEntityReactantFurnace)entity;
		this.xSize = 176;
		this.ySize = 166;
	}
	
	public void drawGuiContainerForegroundLayer(int p1, int p2){
		String name = "Reactant Furnace";
		this.fontRendererObj.drawString(name, this.xSize/2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 118, this.ySize - 94, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if(this.reactantFurnace.isBurning()) {
			int k = this.reactantFurnace.getBurnTimeRemainingScaled(40);
			int j = 40 - k;
			drawTexturedModalRect(guiLeft + 29, guiTop + 65, 176, 0, 40 - j, 10);
		}

		int k = this.reactantFurnace.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 10, k + 1, 16);
	}

}
