package biomesoplenty.common.world.layer;

import java.util.List;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import biomesoplenty.api.BOPBiomeManager;
import biomesoplenty.api.BOPBiomeManager.BiomeEntry;
import biomesoplenty.common.biomes.BOPSubBiome;

public class GenLayerSubBiome extends GenLayer
{
	public GenLayerSubBiome(long seed, GenLayer parent) 
	{
		super(seed);
		
		this.parent = parent;
	}
	
    @Override
	public int[] getInts(int x, int z, int width, int length)
    {
        int[] inputBiomeIDs = this.parent.getInts(x - 1, z - 1, width + 2, length + 2);
        int[] outputBiomeIDs = IntCache.getIntCache(width * length);

        for (int xi = 0; xi < width; ++xi) 
        {
        	for (int zi = 0; zi < length; ++zi) 
        	{
        		this.initChunkSeed(xi + x, zi + z);
        		int currentBiomeId = inputBiomeIDs[xi + 1 + (zi + 1) * (width + 2)];
        		
        		List<BiomeEntry> currentSubBiomes = BOPBiomeManager.overworldSubBiomes[currentBiomeId];
        		BOPSubBiome selectedSubBiome = currentSubBiomes != null ? (BOPSubBiome)currentSubBiomes.get(this.nextInt(currentSubBiomes.size())).biome : null;

        		if (selectedSubBiome != null)
        		{
        			/*if (SimplexNoise.noise((xi + x) * selectedSubBiome.getZoom(), (zi + z) * selectedSubBiome.getZoom()) > selectedSubBiome.getThreshold())  
        			{
            			System.out.println(SimplexNoise.noise((xi + x) * selectedSubBiome.getZoom(), (zi + z) * selectedSubBiome.getZoom()));
        				System.out.printf("Replaced Biome at %d, %d\n", xi + x, zi + z);
        				outputBiomeIDs[xi + zi * width] = selectedSubBiome.biomeID;
        			}
        			else outputBiomeIDs[xi + zi * width] = currentBiomeId;*/
        		}
        		else
        		{
        			outputBiomeIDs[xi + zi * width] = currentBiomeId;
        		}
        	}
        }

        return outputBiomeIDs;
    }
}