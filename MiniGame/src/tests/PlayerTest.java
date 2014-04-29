package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import game.Map;
import game.Player;
import game.Position;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest
{

	Map map;
	Player player;
	Position position;
	ArrayList<Player> players;
	
	@Before
	public void before()
	{
		map = new Map();
		player = new Player();
		map.setSize(4, 5);
		map.generateMap();
		
		players = new ArrayList<Player>();
		for(int i = 0; i < 5; i++)
		{
			players.add(new Player(i+1));
		}
	}

	/**
	 * testing setNumber and getNumber methods
	 */
	@Test
	public void testingNumberOfPlayers()
	{
		player.setNumber(4);
		assertEquals(player.getNumber(),4);
	}
	
	/**
	 * ensuring that with an invalid move the position will remain the same
	 */
	@Test
	public void testingBounderiesL() 
	{
		player.setPosition(map,new Position(0,0));
		player.move(map,'L');//invalid char
		Position pos = player.getPosition();
		
		//asserting that player did not move 
		assertEquals(pos.getX(),0);
		assertEquals(pos.getY(),0);
		
		//asserting the type/colour of a particular tile
		int value = player.setPosition(map, new Position(1,3));
		if(map.getTileType(1, 3).equals("#66BA75"))
		{
			assertEquals(0,value);
		}
		else if(map.getTileType(1, 3).equals("#949AEF"))
		{
			assertEquals(1,value);
		}
		else if(map.getTileType(1, 3).equals("#F0E86D"))
		{
			assertEquals(2,value);
		}
		else if(map.getTileType(1, 3).equals("#867878"))
		{
			assertEquals(3,value);
		}
	}
	
	/**
	 * testing that the up move is working
	 */
	@Test
	public void testingBounderiesU() 
	{
		
		player.setPosition(map,new Position(2,2));
		player.move(map,'U');
		Position pos = player.getPosition();
		
		//asserting that player did not move 
		assertEquals(pos.getX(),1);
		assertEquals(pos.getY(),2);
	}
	
	
	/**
	 * testing that the Down Move is actually working
	 */
	@Test
	public void testingBounderiesD() 
	{
		player.setPosition(map,new Position(2,2));
		player.move(map,'D');//invalid char
		Position pos = player.getPosition();
		
		//asserting that player did not move 
		assertEquals(pos.getX(),3);
		assertEquals(pos.getY(),2);
	}
	
	/**
	 * testing that the right move is actually working
	 */
	@Test
	public void testingBounderiesR() 
	{
		
		player.setPosition(map,new Position(2,2));
		player.move(map,'R');//invalid char
		Position pos = player.getPosition();
		
		//asserting that player did not move 
		assertEquals(pos.getX(),2);
		assertEquals(pos.getY(),3);
	}
	
	/**
	 * Testing TILE_INVALID
	 */
	@Test
	public void testingInvalidTile() 
	{
		player.setPosition(map,new Position(2,2));
		int condition = player.move(map,'M');//invalid char
		assertEquals(condition, -1);
	}
	
	/**
	 * testing copyPlayers method
	 */
	@Test
	public void copyPlayers()
	{
		ArrayList<Player> copiedPlayers = new ArrayList<Player>();
		copiedPlayers = Player.copyPlayers(players);
		
		int x_coord1, x_coord2;
		int y_coord1, y_coord2;

		for(int i = 0; i < players.size(); i++)
		{
			x_coord1 = copiedPlayers.get(i).getPosition().getX();
			x_coord2 = players.get(i).getPosition().getX();
			assertEquals(x_coord1,x_coord2);

			y_coord1 = copiedPlayers.get(i).getPosition().getY();
			y_coord2 = players.get(i).getPosition().getY();
			assertEquals(y_coord1, y_coord2);
		}	
	}
	
	/**
	 * ensuring initial position of player is actually green
	 */
	@Test
	public void testingInitialTileType()
	{
		Position p = Player.getInitialPosition(player, map);
		assertEquals(map.getTileType(p.getX(), p.getY()), "#66BA75");
	}
}
