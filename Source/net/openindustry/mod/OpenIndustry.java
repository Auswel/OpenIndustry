package net.openindustry.mod;

import net.openindustry.mod.blocks.*;
import net.openindustry.mod.handler.*;
import net.openindustry.mod.items.*;
import net.openindustry.mod.worldgen.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = OpenIndustry.modid, version = OpenIndustry.version)
public class OpenIndustry {
	public static final String modid = "OpenIndustry";
	public static final String version = "InDev 2014.07.23_47";

	OpenIndustryWorldGen eventWorldGen = new OpenIndustryWorldGen();

	public static CreativeTabs openIndustryTab;

	// ITEMS
	public static Item itemBasePlate;
	public static Item itemReactiveCluster;
	public static Item itemReactiveIngot;
	public static Item itemCopperIngot;
	public static Item itemTinIngot;
	public static Item itemSulfuricDust;

	// ORES
	public static Block oreReactiveOre;
	public static Block oreTinOre;
	public static Block oreCopperOre;
	public static Block oreSulfuricOre;

	// MACHINES
	public static Block machMachineFrame;
	public static Block machReactantFurnace_Idle;
	public static Block machReactantFurnace_Active;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent preEvent) {
		openIndustryTab = new CreativeTabs("OpenIndustry") {
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return Item.getItemFromBlock(OpenIndustry.machMachineFrame);
			}
		};
		//********************************************
		// INITS
		//********************************************
		// ITEMS
		//**************
		itemBasePlate = new OpenIndustryItems().setUnlocalizedName("BasePlate");
		itemReactiveCluster = new OpenIndustryItems()
				.setUnlocalizedName("ReactiveCluster");
		itemReactiveIngot = new OpenIndustryItems()
				.setUnlocalizedName("ReactiveIngot");
		itemCopperIngot = new OpenIndustryItems()
				.setUnlocalizedName("CopperIngot");
		itemTinIngot = new OpenIndustryItems().setUnlocalizedName("TinIngot");
		itemSulfuricDust = new OpenIndustryItems().setUnlocalizedName("SulfuricDust");
		//**************
		// ORES
		//**************
		oreReactiveOre = new ReactiveOre(Material.rock)
				.setBlockName("ReactiveOre");
		oreCopperOre = new CopperOre(Material.rock).setBlockName("CopperOre");
		oreTinOre = new TinOre(Material.rock).setBlockName("TinOre");
		oreSulfuricOre = new SulfuricOre(Material.rock)
				.setBlockName("SulfuricOre");
		//**************
		// MACHINES
		//**************
		machMachineFrame = new MachineFrame(Material.iron)
				.setBlockName("MachineFrame");
		machReactantFurnace_Idle = new ReactantFurnace(false).setBlockName("ReactantFurnace_Idle").setCreativeTab(openIndustryTab);
		machReactantFurnace_Active = new ReactantFurnace(true).setBlockName("ReactantFurnace_Active").setLightLevel(0.575f);
		//********************************************
		// REGISTRY
		//********************************************
		// MACHINES
		//**************
		GameRegistry.registerBlock(machMachineFrame, "MachineFrame");
		GameRegistry.registerBlock(machReactantFurnace_Idle, "ReactantFurnace_Idle");
		GameRegistry.registerBlock(machReactantFurnace_Active, "ReactantFurnace_Active");
		
		//**************
		// ITEMS
		//**************
		GameRegistry.registerItem(itemBasePlate, "BasePlate");
		GameRegistry.registerItem(itemReactiveCluster, "ReactiveCluster");
		GameRegistry.registerItem(itemReactiveIngot, "ReactiveIngot");
		GameRegistry.registerItem(itemCopperIngot, "CopperIngot");
		GameRegistry.registerItem(itemTinIngot, "TinIngot");
		GameRegistry.registerItem(itemSulfuricDust, "SulfuricDust");
		
		//**************
		// ORES
		//**************
		GameRegistry.registerBlock(oreReactiveOre, "ReactiveOre");
		GameRegistry.registerBlock(oreCopperOre, "CopperOre");
		GameRegistry.registerBlock(oreTinOre, "TinOre");
		GameRegistry.registerBlock(oreSulfuricOre, "SulfuricOre");
		
		//**************
		// GAME MODIFIERS
		//**************
		GameRegistry.registerWorldGenerator(eventWorldGen, 0);
		GameRegistry.registerFuelHandler(new FuelHandler());
		//**************
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		//********************************************
		// CRAFTING
		//********************************************
		// BASE PLATE
		//**************
		GameRegistry.addRecipe(new ItemStack(itemBasePlate, 2), new Object[] {
				"III", "I I", "III", 'I', Items.iron_ingot });
		//**************
		// MACHINE FRAME
		//**************
		GameRegistry.addRecipe(new ItemStack(machMachineFrame),
				new Object[] { "PPP", "PIP", "PPP", 'P', itemBasePlate, 'I',
						Items.iron_ingot });
		//**************
		
		//********************************************
		// SMELTERY
		//********************************************
		//ReactiveOre --> ReactiveCluster
		//**************
		GameRegistry.addSmelting(oreReactiveOre, new ItemStack(
				itemReactiveCluster), 5);
		//**************
		//CopperOre --> CopperIngot
		//**************
		GameRegistry.addSmelting(oreCopperOre, new ItemStack(itemCopperIngot),
				5);
		//**************
		//TinOre --> TinIngot
		//**************
		GameRegistry.addSmelting(oreTinOre, new ItemStack(itemTinIngot), 5);
		//**************
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent postEvent) {

	}
}
