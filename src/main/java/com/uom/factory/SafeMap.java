package main.java.com.uom.factory;

import java.util.Random;

public class SafeMap extends Map
{
	  
	private SafeMap(int noOfPlayers, int mapSize)
	{
		super(noOfPlayers, mapSize);
	}  
	
	public static Map getInstance(int noOfPlayers, int mapSize)
	{
		if(Map.mapInstance == null) 
			Map.mapInstance = new SafeMap(noOfPlayers, mapSize);
		return Map.mapInstance;
	}

	public int generateMap() 
	{
		Random ran = new Random(); 
		int blueCounter = 0;
		int maxWater = (int) Math.floor((size*size) *0.1);   //obtaining 10% of the total area ensuring it is less
		for (int i = 0; i < size; i++)
		{ 
			for (int j = 0; j < size; j++)
			{
				int x = ran.nextInt(2);
				 
				if (x == 1 || blueCounter >= maxWater)
				{
					colorMapping[i][j] = 0;
				}
					
				else if(x == 0 && blueCounter < maxWater)
				{
					colorMapping[i][j] = 1;
					blueCounter += 1;
				}
					
			}
		}
		 
		// random rows and columns for yellow block
		int randRow;
		int randCol;
		
		do{
			randRow = ran.nextInt(size);
			randCol = ran.nextInt(size);
		}while(colorMapping[randRow][randCol] == 1); //keep looping till we find a blue tile
			
		colorMapping[randRow][randCol] = 2;	  //if blue tile is found change it with a treasure tile;
	
		return blueCounter;
	}
	
}
