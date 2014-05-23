package main.java.com.uom.game;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Observer;

import main.java.com.uom.factory.Map;

/**
 * Handles the functionality of the Player
 *
 */
public class Player implements Observer
{
	private Position position;
	private int number;
	private Team team = null;
	
	/**
	 * Default Player Constructor
	 */
	public Player()
	{
		position = new Position();
	}
	
	/**
	 * Constructor used for creating a player based on other's player details
	 * @param p Player 
	 */
	public Player(Player p)
	{
		number = p.getNumber();
		Position pos = p.getPosition();
		position = new Position(pos.getX(), pos.getY());
		team = p.getTeam();
	}
	
	/**
	 * Player Constructor with a particular number
	 * @param number - identification number
	 */
	public Player(int num)
	{
		number = num;
		position = new Position();

	}
	
	
	/**
	 * Gets the current position of the player
	 * @return current position
	 */
	public Position getPosition()
	{
		return position;
	}
	
	/**
	 * Sets the player Number
	 * @param num player's number
	 */
	public void setNumber(int num)
	{
		number = num;
	}
	
	/**
	 * Gets the player number
	 * @return player's number
	 */
	public int getNumber()
	{
		return number;
	}
	
	/**
	 * Gets the team of the player
	 * @return Team
	 */
	public Team getTeam()
	{
		return team;
	}
	
	public void setTeam(Team t)
	{
		team = t;
	}
	
	/**
	 * Moves the player to a different position
	 * @param direction Direction's movement
	 * @return new position of the player
	 */
	public int move(Map map, char direction)
	{
		switch(direction)
		{
			case 'L':
				return setPosition(map, new Position(position.getX(), position.getY() - 1));
			
			case 'R':
				return setPosition(map, new Position(position.getX(), position.getY() + 1));
			
			case 'U':
				return setPosition(map, new Position(position.getX() - 1, position.getY()));
			
			case 'D':
				return setPosition(map, new Position(position.getX() + 1, position.getY()));
			
			default:
				return Map.TILE_INVALID;
		}
	}
	
	/**
	 * Handling Position validation
	 * @param x new xCoordinate
	 * @param y new yCoordinate
	 * @return true if position is valid otherwise
	 *		   false if position is not valid
	 */
	public int setPosition(Map map, Position p)
	{
		int newPos = Map.TILE_INVALID;
		int x = p.getX();
		int y = p.getY();
		
		//Checking that the position is within the game's map
		if((x >= 0 && x < map.getSize()) && (y >= 0 && y < map.getSize()))
		{
			if(Map.colorMapping[x][y] == Map.TILE_GRASS)
			{
				newPos = Map.TILE_GRASS;
			}
			else
			{
				if(Map.colorMapping[x][y] == Map.TILE_TREASURE)
					newPos = Map.TILE_TREASURE;
				
				else
					newPos = Map.TILE_WATER;
			}
			
			position.setX(x);
			position.setY(y);
			
		}
		else
		{
			newPos = Map.TILE_INVALID;
		}
	
		return newPos;
	}

	/**
	 * Copies the players of the game to a temporarily ArrayList
	 * @param players players to be copied
	 * @return copied players
	 */
	public static ArrayList<Player> copyPlayers(ArrayList<Player> players)
	{
		ArrayList<Player> copiedPlayers = new ArrayList<Player>();
		
		for (Player p : players)
			copiedPlayers.add(new Player(p));
		
		return copiedPlayers;
	}
	
	/**
	 * Gets the initial position of a particular player. This is 
	 * the exact position from which he starts the game
	 * @param player player to obtain his first initial position
	 * @param map game map
	 * @return initial position of the player
	 */
	public static Position getInitialPosition(Player player, Map map)
	{
		Position pos = null;
		int posColor = Map.TILE_INVALID;
		Random ran = new Random();
		
		do
		{
			pos = new Position(ran.nextInt(map.getSize()), ran.nextInt(map.getSize()));
			posColor = player.setPosition(map, pos);
		}
		while(posColor != Map.TILE_GRASS);
		
		return pos;
	}

	
	/**
	 * This overriding method is used by each Team to update the HTML map for
	 * every player within the team if the game is being played in a collaborative mode 
	 */
	@Override
	public void update(Observable t, Object arg)
	{
		Game.generateHTMLFiles(false, this, Game.getMap(), (Position)arg);
	}
}
