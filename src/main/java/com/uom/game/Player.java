package main.java.com.uom.game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Handles the functionality of the Player
 *
 */
public class Player
{
	private Position position;
	private int number;
	
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
			if(map.colorMapping[x][y] == Map.TILE_GRASS)
			{
				newPos = Map.TILE_GRASS;
			}
			else
			{
				if(map.colorMapping[x][y] == Map.TILE_TREASURE)
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
	
}
