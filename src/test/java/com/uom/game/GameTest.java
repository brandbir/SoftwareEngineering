package test.java.com.uom.game;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.com.uom.game.Game;
import main.java.com.uom.game.Map;
import main.java.com.uom.game.Misc;
import main.java.com.uom.game.Player;
import main.java.com.uom.game.Position;

public class GameTest
{

	Map map;
	Player player;
	ArrayList<Player> players;
	
	@Before
	public void before()
	{
		map = new Map();
		getMapTest();
		map.setSize(4, 5);
		map.generateMap();

		player = new Player(1);
		player.setPosition(map, new Position(1,2));
		
		players = new ArrayList<Player>();
	}
	
	/**
	 * ensuring amount of players is as set
	 */
	@Test
	public void testNoOfPlayers()
	{
		players = Game.setNumPlayers(4, players);
		assertEquals(players.size(),4);
		assertNotNull(players.size());
	}
	/**
	 * Testing that HTML file are generated as expected
	 */
	@Test 
	public void generateHTMLFile() 
	{
		int condition = Misc.writeToFile(map,"external/maps/map_player_1.html",true,player);
		assertEquals(condition,1);
		int condition1 = Misc.writeToFile(map,"external/maps/map_player_1.html",false,player);
		assertEquals(condition1,1);
		
		int condition2 = Misc.writeToFile(map,"external/map/map_player_1.html",false,player);
		assertEquals(condition2,-1);
	}
	
	/**
	 * testing the delete files method
	 */
	@Test 
	public void testingDeleteFilesMethod() 
	{
		File folder = new File("external/maps");
		int numberOfFiles = folder.list().length;
		String[] subfiles;
		
		Misc.deleteFiles("external/mapssafda");
		subfiles = folder.list();
		
		assertEquals(subfiles.length, numberOfFiles);
		
		Misc.deleteFiles("external/maps");
		subfiles = folder.list();
		
		assertEquals(subfiles.length,0);
	}
		
	
	/**
	 * testing getMap and getPlayerSize
	 */
	@Test
	public void getMapTest()
	{
		assertNull(Game.getMap());
		assertEquals(Game.getPlayerSize(),0);
	}
	
	/**
	 * testing that water block are being set
	 */
	@Test
	public void testingWater()
	{
		testingColourBlocks(map.getTileType(Map.TILE_WATER));
	}
	
	/**
	 * testing that grass block are being set
	 */
	@Test
	public void testingGrass()
	{
		testingColourBlocks(map.getTileType(Map.TILE_GRASS));
	}
	
	/**
	 * testing that treasure block are being set
	 */
	@Test
	public void testingTreasure()
	{
		testingColourBlocks(map.getTileType(Map.TILE_TREASURE));
	}
	
	
	/**
	 * testing that winners are being set
	 */
	public void testingColourBlocks(String colour)
	{
		map.setSize(4, 5);
		map.generateMap();
		System.out.println("\n");
		map.printMap();
		
		player = new Player(1);
		player.setPosition(map, new Position(1,2));
		int nextTile = -1;
		
		ArrayList<Player> winners = new ArrayList<Player>();
		for(int i=0; i < 5; i++)
		{
			for(int j=0; j < 5; j++)
			{
				if(map.getTileType(i, j).equals(colour))  //checking for Yellow block
				{
					if(map.getTileType(i, j-1) != null)
					{
						player.setPosition(map, new Position(i,j-1)); //setting position of player next to winning block
						nextTile = player.move(map, 'R');
						break;
					}
					else if(map.getTileType(i, j+1) != null)
					{
						player.setPosition(map, new Position(i,j+1)); 
						nextTile = player.move(map, 'L');
						break;
					}
					else if(map.getTileType(i-1, j) != null)
					{
						player.setPosition(map, new Position(i-1,j)); 
						nextTile = player.move(map, 'D');
						break;
					}	
					else if(map.getTileType(i+1, j) != null)
					{
						player.setPosition(map, new Position(i+1,j)); 
					    nextTile = player.move(map, 'U');
						break;
					}

				}
				  
			}
		}

		Game.movePlayer(nextTile, player, winners);
		
		if(colour == map.getTileType(Map.TILE_GRASS))
			assertEquals(winners.size(),0);
		if(colour == map.getTileType(Map.TILE_WATER))
			assertEquals(winners.size(),0);
		if(colour == map.getTileType(Map.TILE_TREASURE))
			assertEquals(winners.size(),1);
	}
	

	
	/**
	 * testing remove players method
	 */
	@Test 
	public void removingPlayer()
	{
		ArrayList<Player> players2 = new ArrayList<Player>();
		Player p5 = new Player(5);
		Player p6 = new Player(6);
		players2.add(p5);
		players2.add(p6);
		players2 = Game.removePlayer(p6.getNumber(), players2);
		assertEquals(players2.size(),1);
	}
	
}
