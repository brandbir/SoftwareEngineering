package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.Game;
import game.Map;

public class MapTest {
	
	Game game = null;
	Map map = null;
	
	@Before
	public void before() 
	{
	    game = new Game();
		map = new Map();
		map.setSize(3,5);
		map.generateMap();
	}
	
	
	@Test
	public void settingMap1() {
		boolean condition = map.setSize(6, 10);	
		assertTrue(condition);
		
		//Game with 3 players of map size 4.
		boolean condition2 = map.setSize(3,5);
		assertTrue(condition2);
		
		//Game with 6 players and a mapsize of 10
			
	}
	
	public void testingGenerateMapYellowBlock()
	{
		//map.setSize(3, 6);
		
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

