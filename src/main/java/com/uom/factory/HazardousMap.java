package main.java.com.uom.factory;

import java.util.Random;

public class HazardousMap extends Map 
{
	int blueCount =0;
	private HazardousMap(int noOfPlayers, int mapSize)
	{ 
 		super(noOfPlayers, mapSize);
	} 
	  
	public static Map getInstance(int noOfPlayers, int mapSize)
	{
		if(Map.mapInstance == null)
			Map.mapInstance = new HazardousMap(noOfPlayers, mapSize);
		return Map.mapInstance;  
	}
	
	public int generateMap() 
	{
		Random ran = new Random();
		int minWater = (int) Math.ceil((size*size)*0.25);  //ensuring it is not less than 25% ax jek namlu round jigi 6 al 25 block mentri 6.25 propja jigi 25% so jigi less than 25 6
		
		int randRow;
		int randCol;
		
		do{
			randRow = ran.nextInt(size);
			randCol = ran.nextInt(size);
			colorMapping[randRow][randCol] = 1;
			blueCount++;
		}while(blueCount < minWater);//adding 25% blue blocks immediately

				
		int remainingBlocks = (size*size) - minWater; //deducting blue blocks from the total no of blocks
		
		int maxWater = (int) Math.floor((remainingBlocks) *0.1);
		int waterUpperBound = maxWater + minWater; //10% + 25% = 35%
		
		for (int i = 0; i < size; i++)
		{ 
			for (int j = 0; j < size; j++)
			{
				
				int x = ran.nextInt(2); 
				
				if (x == 1 || blueCount >= waterUpperBound)
				{
					if(colorMapping[i][j] != 1)
					{
						colorMapping[i][j] = 0;
						
					}
				}
					
				else if(x == 0 && blueCount < waterUpperBound)
				{
					if(colorMapping[i][j] != 1)
					{
						colorMapping[i][j] = 1;
						blueCount += 1;
					}
				}
					
			}
			
		}
		
		// random rows and columns for yellow block
		do{
			randRow = ran.nextInt(size);
			randCol = ran.nextInt(size);
		}while(colorMapping[randRow][randCol] == 1); //keep looping till we find a blue tile
			
		// assigning yellow block
		colorMapping[randRow][randCol] = 2;
		
		return blueCount;
	}
}
