package test.java.com.uom.game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.java.com.uom.factory.Map;
import main.java.com.uom.factory.SafeMap;
import main.java.com.uom.game.Player;
import main.java.com.uom.game.Position;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest
{

	Map map;
	Player player;
	ArrayList<Player> players;
	
	@Before
	public void before()
	{
		map = SafeMap.getInstance(4, 5); //setting 4 players with map size of 5*5
		player = new Player();
		map.generateMap();
		players = new ArrayList<Player>();
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
	 * ensuring that with an invalid left move the position will remain the same
	 */
	@Test
	public void testingBounderiesLeft() 
	{
		player.setPosition(map,new Position(0,0));
		player.move(map,'L');//invalid move
		Position pos = player.getPosition();
		
		//asserting that player did not move 
		assertEquals(pos.getX(),0);
		assertEquals(pos.getY(),0);
	}
	
	/**
	 * Testing invalid up move
	 */
	@Test
	public void testingBounderiesUp() 
	{
		player.setPosition(map,new Position(0,0));
		player.move(map,'U');//invalid move
		Position pos = player.getPosition();
		
		//asserting that player did not move 
		assertEquals(pos.getX(),0);
		assertEquals(pos.getY(),0);
	}
	/**
	 * Testing invalid down move
	 */
	@Test
	public void testingBounderiesDown() 
	{
		player.setPosition(map,new Position(4,4));
		player.move(map,'D');//invalid move
		Position pos = player.getPosition();
		
		//asserting that player did not move 
		assertEquals(pos.getX(),4);
		assertEquals(pos.getY(),4);
	}
	
	/**
	 * Testing invalid right move
	 */
	@Test
	public void testingBounderiesRight() 
	{
		player.setPosition(map,new Position(4,4));
		player.move(map,'R');//invalid move
		Position pos = player.getPosition();
		
		//asserting that player did not move 
		assertEquals(pos.getX(),4);
		assertEquals(pos.getY(),4);
	}
	
	/**
	 * asserting the colour of a particular tile
	 */
	@Test
	public void testingTileColour()
	{
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
	public void testingUp() 
	{
		
		player.setPosition(map,new Position(2,2));
		player.move(map,'U');
		Position pos = player.getPosition();
		
		//asserting that player did move up 
		assertEquals(pos.getX(),1);
		assertEquals(pos.getY(),2);
	}
	
	
	/**
	 * testing that the Down Move is actually working
	 */
	@Test
	public void testingDown() 
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
	public void testingRight() 
	{
		
		player.setPosition(map,new Position(2,2));
		player.move(map,'R');
		Position pos = player.getPosition();
		
		//asserting that player did move to the right
		assertEquals(pos.getX(),2);
		assertEquals(pos.getY(),3);
	}
	
	/**
	 * testing that the left move is actually working
	 */
	@Test
	public void testingLeft() 
	{
		
		player.setPosition(map,new Position(2,2));
		player.move(map,'L');
		Position pos = player.getPosition();
		
		//asserting that player did move to the left 
		assertEquals(pos.getX(),2);
		assertEquals(pos.getY(),1);
	}
	
	/**
	 * Testing TILE_INVALID
	 */
	@Test
	public void testingInvalidTile() 
	{
		player.setPosition(map,new Position(2,2));
		int condition = player.move(map,'M');
		assertEquals(condition, -1);
	}
	
	/**
	 * testing copyPlayers method
	 */
	@Test
	public void copyPlayers()
	{
		for(int i = 0; i < 5; i++)
		{
			players.add(new Player(i+1));
		}
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
