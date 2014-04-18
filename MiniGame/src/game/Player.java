package game;

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
	public int move(char direction)
	{
		switch(direction)
		{
			case 'L':
				return setPosition(new Position(position.getX(), position.getY() - 1));
			
			case 'R':
				return setPosition(new Position(position.getX(), position.getY() + 1));
			
			case 'U':
				return setPosition(new Position(position.getX() - 1, position.getY()));
			
			case 'D':
				return setPosition(new Position(position.getX() + 1, position.getY()));
			
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
	public int setPosition(Position p)
	{
		int newPos = Map.TILE_INVALID;
		int x = p.getX();
		int y = p.getY();
		
		//Checking that the position is within the game's map
		if((x >= 0 && x < Game.getMap().getSize()) && (y >= 0 && y < Game.getMap().getSize()))
		{
			if(Game.getMap().colorMapping[x][y] == Map.TILE_GRASS)
			{
				newPos = Map.TILE_GRASS;
			}
			else
			{
				if(Game.getMap().colorMapping[x][y] == Map.TILE_TREASURE)
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
}
