package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenBOPTaiga2 extends WorldGenAbstractTree
{
	private final int minTreeHeight;
	private final int randomTreeHeight;

	private final Block wood;
	private final Block leaves;
	
	private final int metaWood;
	private final int metaLeaves;
	
	private final int altNo;

	public WorldGenBOPTaiga2(Block wood, Block leaves, int metaWood, int metaLeaves, boolean doBlockNotify, int minTreeHeight, int randomTreeHeight, int altNo)
	{
		super(doBlockNotify);
		
		this.wood = wood;
		this.leaves = leaves;
		this.metaWood = metaWood;
		this.metaLeaves = metaLeaves;
		this.minTreeHeight = minTreeHeight;
		this.randomTreeHeight = randomTreeHeight;
		
		this.altNo = altNo;
	}

    @Override
	public boolean generate(World world, Random random, int x, int y, int z)
    {
        int l = random.nextInt(randomTreeHeight) + minTreeHeight;
        int i1;
        int j1;
        int k1;

        switch (altNo)
        {
            case 1:
                i1 = 2 + random.nextInt(4);
                j1 = l - i1;
                k1 = 2 + random.nextInt(4);
                break;

            case 2:
                i1 = 4 + random.nextInt(4);
                j1 = l - i1;
                k1 = 2;
                break;
                
            case 3:
                i1 = 1 - random.nextInt(4);
                j1 = l - i1;
                k1 = 2 + random.nextInt(2);
                break;
                
            case 4:
                i1 = 8 + random.nextInt(4);
                j1 = l - i1;
                k1 = 2 + random.nextInt(2);
                break;
                
            case 5:
                i1 = 2;
                j1 = l - i1;
                k1 = 2 + random.nextInt(2);
                break;
                
            case 6:
                i1 = 2 + random.nextInt(4);
                j1 = l - i1;
                k1 = 2 + random.nextInt(2);
                break;
                
            case 7:
                i1 = 2;
                j1 = l - i1;
                k1 = 3;
                break;
                
            case 8:
                i1 = 2 + random.nextInt(3);
                j1 = l - i1;
                k1 = 2 + random.nextInt(2);
                break;

            default:
                i1 = 1 + random.nextInt(2);
                j1 = l - i1;
                k1 = 2 + random.nextInt(2);
                break;
        }

        boolean flag = true;

        if (y >= 1 && y + l + 1 <= 256)
        {
            int i2;
            int l3;

            for (int l1 = y; l1 <= y + 1 + l && flag; ++l1)
            {
                boolean flag1 = true;

                if (l1 - y < i1)
                {
                    l3 = 0;
                }
                else
                {
                    l3 = k1;
                }

                for (i2 = x - l3; i2 <= x + l3 && flag; ++i2)
                {
                    for (int j2 = z - l3; j2 <= z + l3 && flag; ++j2)
                    {
                        if (l1 >= 0 && l1 < 256)
                        {
                            Block block = world.func_147439_a(i2, l1, j2);

                            if (!block.isAir(world, i2, l1, j2) && !block.isLeaves(world, i2, l1, j2))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                Block block1 = world.func_147439_a(x, y - 1, z);

                boolean isSoil = block1.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling)Blocks.sapling);
                if (isSoil && y < 256 - l - 1)
                {
                    block1.onPlantGrow(world, x, y - 1, z, x, y, z);
                    l3 = random.nextInt(2);
                    i2 = 1;
                    byte b0 = 0;
                    int k2;
                    int i4;

                    for (i4 = 0; i4 <= j1; ++i4)
                    {
                        k2 = y + l - i4;

                        for (int l2 = x - l3; l2 <= x + l3; ++l2)
                        {
                            int i3 = l2 - x;

                            for (int j3 = z - l3; j3 <= z + l3; ++j3)
                            {
                                int k3 = j3 - z;

                                if ((Math.abs(i3) != l3 || Math.abs(k3) != l3 || l3 <= 0) && world.func_147439_a(l2, k2, j3).canBeReplacedByLeaves(world, l2, k2, j3))
                                {
                                    //TODO:	setBlockAndMetadata()
                                    this.func_150516_a(world, l2, k2, j3, leaves, this.metaLeaves);
                                }
                            }
                        }

                        if (l3 >= i2)
                        {
                            l3 = b0;
                            b0 = 1;
                            ++i2;

                            if (i2 > k1)
                            {
                                i2 = k1;
                            }
                        }
                        else
                        {
                            ++l3;
                        }
                    }

                    i4 = random.nextInt(3);

                    for (k2 = 0; k2 < l - i4; ++k2)
                    {
                        Block block2 = world.func_147439_a(x, y + k2, z);

                        if (block2.isAir(world, x, y + k2, z) || block2.isLeaves(world, x, y + k2, z))
                        {
                            //TODO:	setBlockAndMetadata()
							this.func_150516_a(world, x, y + k2, z, wood, metaWood);
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
}
