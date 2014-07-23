package net.openindustry.mod.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.openindustry.mod.OpenIndustry;
import cpw.mods.fml.common.IWorldGenerator;

public class OpenIndustryWorldGen implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case 0:
			//Generate Overworld
			generateSurface(world, random, chunkX*16,chunkZ*16);
			
		case -1:
			//Generate Nether
			generateNether(world, random, chunkX*16,chunkZ*16);
		case 1:
			//Generate The End
			generateEnd(world, random, chunkX*16,chunkZ*16);
		}
		
	}
	private void generateSurface(World world, Random random, int x, int z) {
		//this.addOreSpawn(OpenIndustry.ore*, world, random, x=BlockX, z=BlockZ, maxX, maxZ, VeinSize, ChanceToSpawn, MinY, MaxY);
		this.addOreSpawn(OpenIndustry.oreCopperOre, world, random, x, z, 16, 16, 3+random.nextInt(5), 25, 40, 72);	
		this.addOreSpawn(OpenIndustry.oreTinOre, world, random, x, z, 16, 16, 3+random.nextInt(5), 25, 40, 72);	
		this.addOreSpawn(OpenIndustry.oreReactiveOre, world, random, x, z, 16, 16, 6+random.nextInt(2), 32, 12, 72);	
		this.addOreSpawn(OpenIndustry.oreSulfuricOre, world, random, x, z, 16, 16, 2+random.nextInt(4), 20, 12, 30);	
	}
	private void generateNether(World world, Random random, int x, int z) {
		
		
	}
	private void generateEnd(World world, Random random, int x, int z) {
		
		
	}
	private void addOreSpawn(Block block, World world, Random random,
			int blockX, int blockZ, int maxX, int maxZ, int maxVeinSize, int chanceToSpawn, int minY, int maxY) {
		for(int i = 0; i < chanceToSpawn; i++ ){
			int posX = blockX + random.nextInt(maxX);
			int posZ = blockZ + random.nextInt(maxZ);
			int posY = minY + random.nextInt(maxY - minY);
			(new WorldGenMinable(block, maxVeinSize)).generate(world,random,posX,posY,posZ);
		}
		
	}
}
