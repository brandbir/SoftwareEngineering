package game;

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
		
		if((x >= 0 && x < Game.getMap().getSize()) && (y >= 0 && y < Game.getMap().getSize()))
		{
			if(Game.getMap().colorMapping[x][y] == Map.TILE_GRASS)
			{
				position.setX(x);
				position.setY(y);
				newPos = Map.TILE_GRASS;
			}
			else
			{
				if(Game.getMap().colorMapping[x][y] == Map.TILE_TRES)
				{
					newPos = Map.TILE_TRES;
				}
				else
				{
					newPos = Map.TILE_WATER;
				}
			}
			
		}
		else
		{
			newPos = Map.TILE_INVALID;
		}
	
		return newPos;
		
		
	}
	
	//TODO : to be deleted in the near future. This method is still being used so make sure that
	// deletion is performed after the NECESSARY UPDATE
	public boolean setPosition(int x, int y)
	{
		boolean set = true;
		
		if((x >= 0 && x < Game.getMap().getSize()) && (y >= 0 && y < Game.getMap().getSize()))
		{
			if(Game.getMap().colorMapping[x][y] == 0)
			{
				position.setX(x);
				position.setY(y);
			}
			else
			{
				System.out.println("Not Green tile..");
				set = false;
			}
		}
		else
		{
			System.out.println("Outside Map..");
			set = false;
		}
	
		return set;
	}
	
	public Position getPosition()
	{
		return position;
	}
	
	public void setNumber(int num)
	{
		number = num;
	}
	
	public int getNumber()
	{
		return number;
	}
}
