package game;

import java.util.Random;

public class Map
{
	static int size;
	int[][] colorMapping;
	private String[] mapColors = {"#66BA75", "#949AEF", "#F0E86D", "#867878 "};
	
	public static final int TILE_INVALID	= -1;
	public static final int TILE_GRASS		= 0;
	public static final int TILE_WATER		= 1;
	public static final int TILE_TREASURE	= 2;
	public static final int TILE_HIDDEN		= 3;
	
	public static final int NO_PLAYERS		= -1;

	void setSize(int size)
	{
		colorMapping = new int[size][size];
		Map.size = size;
	}

	int getSize()
	{
		return size;
	}

	//TODO : We need to make sure that there is a valid path to reach the destination tile
	void generateMap()
	{
		Random ran = new Random();

		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				int x = ran.nextInt(5);
				
				if (x < 3)
					colorMapping[i][j] = 0;
				else
					colorMapping[i][j] = 1;
			}
		}

		// assigning yellow block
		int row = ran.nextInt(size);
		int col = ran.nextInt(size);

		colorMapping[row][col] = 2;
	}

	void printMap()
	{
		for (int i = 0; i < size; i++)
		{
			System.out.print('|');
			
			for (int j = 0; j < size; j++)
			{
				System.out.print(colorMapping[i][j]);
				System.out.print('|');
			}
			
			System.out.println();
		}
	}

	String getTileType(int x, int y)
	{
		return mapColors[colorMapping[x][y]];
	}
	
	String getTileType(int x)
	{
		return mapColors[x];
	}
}
