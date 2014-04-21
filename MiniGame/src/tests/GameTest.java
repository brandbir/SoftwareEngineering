package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import game.Game;
import game.Map;
import game.Player;
import game.Position;
import game.Misc;

public class GameTest{

	Game game;
	Map map;
	Player player;
	ArrayList<Player> players;
	
	@Before
	public void before()
	{
		game = new Game();
		map = new Map();
		map.setSize(4, 5);
		map.generateMap();
		player = new Player(1);
		player.setPosition(map, new Position(1,2));
		
	    players = new ArrayList<Player>();
	    
	}
	
	
	
	@Test
	public void testNoOfPlayers()
	{
		players = Game.setNumPlayers(4, players);
		assertEquals(players.size(),4);
		
	}
	
	@Test 
	public void generateHTMLFile() throws IOException
	{
		int condition = Misc.writeToFile(map,"external/maps/map_player_1.html",true,player);
		assertEquals(condition,1);
		int condition1 = Misc.writeToFile(map,"external/maps/map_player_1.html",false,player);
		assertEquals(condition1,1);
		
		int condition2 = Misc.writeToFile(map,"external/map/map_player_1.html",false,player);
		assertEquals(condition2,-1);
	}

}
