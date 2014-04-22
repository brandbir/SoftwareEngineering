package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import game.Game;
import game.Map;
import game.Player;
import game.Position;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	Map map;
	Player player;
	Position position;
	Game game;
	ArrayList<Player> players;
	
	@Before
	public void before()
	{
		map = new Map();
		player = new Player();
		game = new Game();
		map.setSize(4, 5);
		map.generateMap();
		
		players = new ArrayList<Player>();
		for(int i = 0; i < 5; i++)
		{
			players.add(new Player(i+1));
		}
	}

	
	/*@Test
	public void testingNumberOfPlayers()
	{
		player.setNumber(4);
		assertEquals(player.getNumber(),4);
	}*/
	//assertEquals(,-1);   for invalid move
	
	//ensuring that with an invalid move the position will remain the same
	@Test
	public void testingBounderiesL() 
	{
		
		//Position pos = new Position();
		player.setPosition(map,new Position(0,0));
		player.move(map,'L');//ivalid char
		Position pos = player.getPosition();
		
		//asserting that player did not move 
		assertEquals(pos.getX(),0);
		assertEquals(pos.getY(),0);
	}
	
	//testing that the up move is working
	@Test
	public void testingBounderiesU() 
	{
		
		//Position pos = new Position();
		player.setPosition(map,new Position(2,2));
		player.move(map,'U');//ivalid char
		Position pos = player.getPosition();
		
		//asserting that player did not move 
		assertEquals(pos.getX(),1);
		assertEquals(pos.getY(),2);
		
		
		
	}
	
	
	//testing that the Down Move is actually working
	@Test
	public void testingBounderiesD() 
	{
		
		//Position pos = new Position();
		player.setPosition(map,new Position(2,2));
		player.move(map,'D');//ivalid char
		Position pos = player.getPosition();
		
		//asserting that player did not move 
		assertEquals(pos.getX(),3);
		assertEquals(pos.getY(),2);
	}
	
	
	//testing that the right move is actually working
	@Test
	public void testingBounderiesR() 
	{
		
		//Position pos = new Position();
		player.setPosition(map,new Position(2,2));
		player.move(map,'R');//ivalid char
		Position pos = player.getPosition();
		
		//asserting that player did not move 
		assertEquals(pos.getX(),2);
		assertEquals(pos.getY(),3);
	}
	
	//Testing TILE_INVALID
	@Test
	public void testingInvalidTile() 
	{
		
		//Position pos = new Position();
		player.setPosition(map,new Position(2,2));
		int condition = player.move(map,'M');//ivalid char
		
		assertEquals(condition, -1);
	}
	
	
	//testing copyPlayers method
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
	
	//ensuring initial position of player is actually green
	@Test
	public void testingInitialTileType()
	{
		 //Game.handlingPlayerEvents(true);  //obtaining player's initial position
		 Position p = Player.getInitialPosition(player, map);
		 assertEquals(map.getTileType(p.getX(), p.getY()), "#66BA75");
	}

}