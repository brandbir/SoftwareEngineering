package game;

import java.util.Random;
import java.util.Scanner;

/**
 * Map Class that handles all the functionality of the 
 * generated Map that will be used for the duration of
 * the Game
 */
public class Map
{
	static int size;
	int[][] colorMapping;
	private String[] mapColors = {"#66BA75", "#949AEF", "#F0E86D", "#867878 "};
	private static Scanner keyboard = new Scanner(System.in);
	
	public static final int TILE_INVALID	= -1;
	public static final int TILE_GRASS		= 0;
	public static final int TILE_WATER		= 1;
	public static final int TILE_TREASURE	= 2;
	public static final int TILE_HIDDEN		= 3;
	
	public static final int NO_PLAYERS		= -1;

	/**
	 * Sets the size of the Map
	 * @param size map size
	 */
	public void setSize(int players, int size)
	{
		//added for validation
		if(players >=2 && players<=4)
		{
			while(size<5 || size >50)
			{
				System.out.println("Size of map should be between 5 and 50.  Please re-enter size : ");
				size = keyboard.nextInt();
			}
		}
		else if(players >=5 && players <=8)
		{
			while(size<8 || size >50)
			{
				System.out.println("Size of map should be between 8 and 50.  Please re-enter size : ");
				size = keyboard.nextInt();
			}
		}
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
				
				if (x < 4)
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
