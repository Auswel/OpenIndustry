package net.openindustry.mod;

import net.openindustry.mod.blocks.CopperOre;
import net.openindustry.mod.blocks.CrystallineLamp;
import net.openindustry.mod.blocks.MachineFrame;
import net.openindustry.mod.blocks.MachineWall;
import net.openindustry.mod.blocks.ReactantFurnace;
import net.openindustry.mod.blocks.ReactiveOre;
import net.openindustry.mod.blocks.SulfuricOre;
import net.openindustry.mod.blocks.TinOre;
import net.openindustry.mod.handler.*;
import net.openindustry.mod.items.*;
import net.openindustry.mod.tileentity.TileEntityReactantFurnace;
import net.openindustry.mod.worldgen.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/* My To-do list:
 *   - Copper/Tin/Bronze Tools
 *   - Crystalline Shard
 *     - Found by breaking grass, rare
 *     - 9 in crafting grid makes Crystalline Cluster
 *       - Smelt for Crystalline Ingot
 *         - Crystalline Tools & Armor
 *         - Crystalline Boots have Feather Falling
 *   - Reactive Tools
 *     - Reactive tools should have a 1/20 chance of auto-smelting an ore
 *   - Ore Grinder
 *     - Uses Reactive Clusters/Reactive Ingots to double ores
 */

@Mod(modid = OpenIndustry.modid, version = OpenIndustry.version)
public class OpenIndustry {
	public static final String modid = "OpenIndustry";
	public static final String version = "InDev 0.9.5";

	OpenIndustryWorldGen eventWorldGen = new OpenIndustryWorldGen();

	public static CreativeTabs openIndustryTab;

	@Instance(modid)
	public static OpenIndustry instance;

	// ITEMS
	public static Item itemBasePlate;
	public static Item itemReactiveCluster;
	public static Item itemReactiveIngot;
	public static Item itemCopperIngot;
	public static Item itemTinIngot;
	public static Item itemCrystallineIngot;
	public static Item itemSulfuricDust;
	public static Item itemCrystallineShard;
	public static Item itemCrystallineCluster;

	// ORES
	public static Block oreReactiveOre;
	public static Block oreTinOre;
	public static Block oreCopperOre;
	public static Block oreSulfuricOre;

	// MACHINES
	public static Block machMachineFrame;

	public static final int guiIDReactantFurnace = 0;
	public static Block machReactantFurnace_Idle;
	public static Block machReactantFurnace_Active;
	public static Block machCrystallineLamp;

	// BLOCKS
	public static Block blockMachineWall;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent preEvent) {
		openIndustryTab = new CreativeTabs("OpenIndustry") {
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return Item.getItemFromBlock(OpenIndustry.machMachineFrame);
			}
		};

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		// ********************************************
		// INITS
		// ********************************************
		// ITEMS
		// **************
		itemBasePlate = new OpenIndustryItems().setUnlocalizedName("BasePlate");
		itemReactiveCluster = new OpenIndustryItems()
				.setUnlocalizedName("ReactiveCluster");
		itemReactiveIngot = new OpenIndustryItems()
				.setUnlocalizedName("ReactiveIngot");
		itemCopperIngot = new OpenIndustryItems()
				.setUnlocalizedName("CopperIngot");
		itemTinIngot = new OpenIndustryItems().setUnlocalizedName("TinIngot");
		itemSulfuricDust = new OpenIndustryItems()
				.setUnlocalizedName("SulfuricDust");
		itemCrystallineShard = new OpenIndustryItems()
				.setUnlocalizedName("CrystallineShard");
		itemCrystallineIngot = new OpenIndustryItems()
				.setUnlocalizedName("CrystallineIngot");
		itemCrystallineCluster = new OpenIndustryItems()
				.setUnlocalizedName("CrystallineCluster");
		// **************
		// ORES
		// **************
		oreReactiveOre = new ReactiveOre(Material.rock)
				.setBlockName("ReactiveOre");
		oreCopperOre = new CopperOre(Material.rock).setBlockName("CopperOre");
		oreTinOre = new TinOre(Material.rock).setBlockName("TinOre");
		oreSulfuricOre = new SulfuricOre(Material.rock)
				.setBlockName("SulfuricOre");
		// **************
		// MACHINES
		// **************
		machMachineFrame = new MachineFrame(Material.iron)
				.setBlockName("MachineFrame");
		machReactantFurnace_Idle = new ReactantFurnace(false).setBlockName(
				"ReactantFurnace_Idle").setCreativeTab(openIndustryTab);
		machReactantFurnace_Active = new ReactantFurnace(true).setBlockName(
				"ReactantFurnace_Active").setLightLevel(0.575f);
		machCrystallineLamp = new CrystallineLamp()
				.setBlockName("CrystallineLamp");
		// **************
		// BLOCKS
		// **************
		blockMachineWall = new MachineWall().setBlockName("MachineWall");

		// ********************************************
		// REGISTRY
		// ********************************************
		// MACHINES
		// **************
		GameRegistry.registerBlock(machMachineFrame, "MachineFrame");
		GameRegistry.registerBlock(machReactantFurnace_Idle,
				"ReactantFurnace_Idle");
		GameRegistry.registerBlock(machReactantFurnace_Active,
				"ReactantFurnace_Active");
		GameRegistry.registerBlock(machCrystallineLamp, "CrystallineLamp");

		// **************
		// ITEMS
		// **************
		GameRegistry.registerItem(itemBasePlate, "BasePlate");
		GameRegistry.registerItem(itemReactiveCluster, "ReactiveCluster");
		GameRegistry.registerItem(itemReactiveIngot, "ReactiveIngot");
		GameRegistry.registerItem(itemCopperIngot, "CopperIngot");
		GameRegistry.registerItem(itemTinIngot, "TinIngot");
		GameRegistry.registerItem(itemCrystallineIngot, "CrystallineIngot");
		GameRegistry.registerItem(itemSulfuricDust, "SulfuricDust");
		GameRegistry.registerItem(itemCrystallineShard, "CrystallineShard");
		GameRegistry.registerItem(itemCrystallineCluster, "CrystallineCluster");

		// **************
		// ORES
		// **************
		GameRegistry.registerBlock(oreReactiveOre, "ReactiveOre");
		GameRegistry.registerBlock(oreCopperOre, "CopperOre");
		GameRegistry.registerBlock(oreTinOre, "TinOre");
		GameRegistry.registerBlock(oreSulfuricOre, "SulfuricOre");

		// **************
		// BLOCKS
		// **************
		GameRegistry.registerBlock(blockMachineWall, "MachineWall");

		// **************
		// GAME MODIFIERS
		// **************
		GameRegistry.registerWorldGenerator(eventWorldGen, 0);
		GameRegistry.registerFuelHandler(new FuelHandler());
		// **************

		MinecraftForge.addGrassSeed(new ItemStack(itemCrystallineShard), 1);

	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {

		GameRegistry.registerTileEntity(TileEntityReactantFurnace.class,
				"ReactantFurnace");

		// ********************************************
		// CRAFTING
		// ********************************************
		// Base Plate
		// **************
		GameRegistry.addRecipe(new ItemStack(itemBasePlate, 2), new Object[] {
				"III", "I I", "III", 'I', Items.iron_ingot });
		// **************
		// Machine Frame
		// **************
		GameRegistry.addRecipe(new ItemStack(machMachineFrame),
				new Object[] { "PPP", "PIP", "PPP", 'P', itemBasePlate, 'I',
						Items.iron_ingot });
		GameRegistry.addRecipe(new ItemStack(machMachineFrame), new Object[] {
				"WWW", "W W", "WWW", 'W', blockMachineWall });
		// **************
		// Reactant Furnace
		// **************
		GameRegistry.addRecipe(new ItemStack(machReactantFurnace_Idle),
				new Object[] { "PRP", "PMP", "RRR", 'P', itemBasePlate, 'R',
						itemReactiveIngot, 'M', machMachineFrame });
		// **************
		// Crystalline Cluster
		// **************
		GameRegistry.addRecipe(new ItemStack(itemCrystallineCluster),
				new Object[] { "SSS", "SRS", "SSS", 'S', itemCrystallineShard,
						'R', itemReactiveCluster });
		// **************
		// Crystalline Lamp
		// **************
		GameRegistry.addRecipe(new ItemStack(machCrystallineLamp),
				new Object[] { "GSG", "GPG", "CST", 'S', itemCrystallineShard,
						'G', Blocks.glass, 'C', itemCopperIngot, 'T',
						itemTinIngot, 'P', machMachineFrame });
		// **************
		// Machine Wall
		// **************
		GameRegistry.addShapelessRecipe(new ItemStack(blockMachineWall, 8),
				new ItemStack(machMachineFrame));

		// ********************************************
		// SMELTERY
		// ********************************************
		// ReactiveOre --> ReactiveCluster
		// **************
		GameRegistry.addSmelting(oreReactiveOre, new ItemStack(
				itemReactiveCluster), 5);
		// **************
		// ReactiveCluster --> ReactiveIngot
		// **************
		GameRegistry.addSmelting(itemReactiveCluster, new ItemStack(
				itemReactiveIngot), 25);
		// **************
		// **************
		// CopperOre --> CopperIngot
		// **************
		GameRegistry.addSmelting(oreCopperOre, new ItemStack(itemCopperIngot),
				15);
		// **************
		// TinOre --> TinIngot
		// **************
		GameRegistry.addSmelting(oreTinOre, new ItemStack(itemTinIngot), 15);
		// **************
		// CrystallineCluster --> CrystallineIngot
		// **************
		GameRegistry.addSmelting(itemCrystallineCluster, new ItemStack(
				itemCrystallineIngot), 15);
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent postEvent) {

	}
}
