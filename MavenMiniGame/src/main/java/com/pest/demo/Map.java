package main.java.com.pest.demo;

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
	private String[] mapColors = {"#66BA75", "#949AEF", "#F0E86D", "#867878"};
	private static Scanner keyboard = new Scanner(System.in);
	
	//Tiles colour codes
	public static final int TILE_INVALID	= -1;
	public static final int TILE_GRASS		= 0;
	public static final int TILE_WATER		= 1;
	public static final int TILE_TREASURE	= 2;
	public static final int TILE_HIDDEN		= 3;

	/**
	 * Sets the size of the Map
	 * @param size map size
	 */
	public boolean setSize(int players, int mapSize)
	{
	    boolean condition = false;
		//added for validation
		if(players >= 2 && players <= 4)
		{
			while(mapSize < 5 || mapSize > 50)
			{
				System.out.print("Size of map should be between 5 and 50.  Please re-enter size : ");
				mapSize = keyboard.nextInt();
			}
			condition = true;
		}
		else if(players >=5 && players <= 8)
		{
			while(mapSize<8 || mapSize > 50)
			{
				System.out.println("Size of map should be between 8 and 50.  Please re-enter size : ");
				mapSize = keyboard.nextInt();
			}
			condition = true;
		}
		
		colorMapping = new int[mapSize][mapSize];
		Map.size = mapSize;
		
		return condition;
	}
	
	/**
	 * Gets the size of the map
	 * @return map size
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * Generates the internal map structure
	 */
	public void generateMap()
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

	/**
	 * Prints the map for testing purposes
	 */
	public void printMap()
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

	/**
	 * Returns the colour of a particular tile specified by the x-y coordinates
	 * @param x xCoordinate
	 * @param y yCoordinate
	 * @return tile's colour
	 */
	public String getTileType(int x, int y)
	{
		if((x < 0 || x > Map.size) || (y < 0 || y > Map.size))
			return null;
		else
			return mapColors[colorMapping[x][y]];
	}
	
	/**
	 * Returns the coded colour of the particular tile
	 * @param code colour's code
	 * @return actual colour in hex value
	 */
	public String getTileType(int code)
	{
		return mapColors[code];
	}
}
