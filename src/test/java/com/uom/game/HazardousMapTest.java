package test.java.com.uom.game;

import static org.junit.Assert.*;
import main.java.com.uom.factory.HazardousMap;
import main.java.com.uom.factory.Map;

import org.junit.Before;
import org.junit.Test;

public class HazardousMapTest {

	Map map;
	 
	@Before
	public void before() 
	{
		map = HazardousMap.getInstance(3, 5); 
		  
	}
	
	 /** testing a map size of 6*6 = 36  
			 */
		@Test
		public void testingGenerate36()
		{ 
			System.out.println(System.currentTimeMillis());
			
			map.setSize(3, 6); //3 players with a mapsize of 6
			
			int minWater = (int) Math.ceil(6 * 6 * 0.25);
			int maxWater = (int) Math.floor(6 * 6 * 0.35);  //using floor to ensure it is less than 35%
			
			map.generateMap();
			
			int yellowBlocks = 0;
			int blueBlocks = 0;
			
			for(int i=0; i < 6; i++)
			{
				for(int j=0; j < 6; j++)
				{
					if(map.getTileType(i, j).equals("#949AEF"))
						blueBlocks++;
					if(map.getTileType(i, j).equals("#F0E86D"))
						yellowBlocks++;
				}
			}
			
			assertEquals(yellowBlocks,1);
			
			boolean condition = false; 
			
			if(blueBlocks <= maxWater && blueBlocks >= minWater)
				condition = true;
			
			assertTrue(condition);   
			
			
		}
}
