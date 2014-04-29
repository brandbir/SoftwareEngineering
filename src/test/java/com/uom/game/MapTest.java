package test.java.com.uom.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.com.uom.game.Map;

public class MapTest {
	
	Map map = null;
	
	@Before
	public void before() 
	{
		map = new Map();
		map.setSize(3,5);
		map.generateMap();
	}
	
	/**
	 * testing limits
	 */
	@Test
	public void settingLimits() {
		//Game with 6 players and a mapsize of 10
		boolean condition = map.setSize(6, 10);	
		assertTrue(condition);
		
		//Game with 3 players of map size 4.
		condition = map.setSize(3,5);
		assertTrue(condition);	
		
		//Testing that the game will fail with 30 players
		condition = map.setSize(30,5);
		assertFalse(condition);
		
		//Testing that the game will fail with 1 player
		condition = map.setSize(1,5);
		assertFalse(condition);
		
	}
	
	/**
	 * Testing tile type
	 */
	@Test
	public void TileTypeTesting()
	{
		String check = map.getTileType(-1,4);
		assertNull(check);
		check = map.getTileType(100,4);
		assertNull(check);
		check = map.getTileType(2,100);
		assertNull(check);
		check = map.getTileType(2,-1);
		assertNull(check);
	}
	
	/**
	 * Asserting that only one yellow block is generated
	 */
	@Test
	public void testingGenerateMapYellowBlock()
	{	
		int yellowBlocks = 0;
		
		for(int i=0; i < 5; i++)
		{
			for(int j=0; j < 5; j++)
			{
				if(map.getTileType(i, j).equals("#F0E86D"))
					yellowBlocks++;
			}
		}
		assertEquals(yellowBlocks,1);
	}
}

