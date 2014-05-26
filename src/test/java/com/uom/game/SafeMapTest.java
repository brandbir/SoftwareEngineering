package test.java.com.uom.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import main.java.com.uom.factory.Map;
import main.java.com.uom.factory.SafeMap;

import org.junit.Before;
import org.junit.Test;

public class SafeMapTest {

	Map map = null;
	
	@Before
	public void before() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException 
	{
		Map.setInstanceNull();
		assertFalse(map instanceof SafeMap);
		map = SafeMap.getInstance(3, 5);
		assertTrue(map instanceof SafeMap);
	} 
	
	  
	 /** testing a map size of 5*5 = 25  
	 */
	@Test
	public void testingGenerate25()
	{
		System.out.println(System.currentTimeMillis());
		map.setSize(3, 5);   //3 players with a mapsize of 5
		
		int maxWater = (int) Math.floor(5 * 5 * 0.1);
		
		map.generateMap();
		
		int yellowBlocks = 0;
		int blueBlocks = 0;
		
		for(int i=0; i < 5; i++)
		{
			for(int j=0; j < 5; j++)
			{
				if(map.getTileType(i, j).equals("#949AEF"))
					blueBlocks++;
				if(map.getTileType(i, j).equals("#F0E86D"))
					yellowBlocks++;
			} 
		} 
		
		assertEquals(yellowBlocks,1);
		
		boolean condition = false;
		
		if(blueBlocks <= maxWater)
			condition = true;
		
		assertTrue(condition); 
		
	}
}
