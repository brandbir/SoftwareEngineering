package game;

public class Player
{
	Position position;
	
	public Player()
	{
		position = new Position();
	}
	
	public void move(char direction)
	{
		//TODO Use switch instead of multiple if conditions
		Position playerPos = new Position();
		
		if(direction =='L')
		{
			if(playerPos.getX() >= 0) //adhering to boundaries
				setPosition(playerPos.getX() - 1, playerPos.getY());
		}
		
		if(direction == 'R')
		{
			if(playerPos.getX() <= Game.getMap().getSize())
				setPosition(playerPos.getX() + 1, playerPos.getY());
		}
		
		if(direction == 'U')
		{
			if(playerPos.getY() >= 0)
				setPosition(playerPos.getX(), playerPos.getY() + 1);
		}
		
		if(direction == 'D')
		{
			if(playerPos.getY() <= Game.getMap().getSize())
				setPosition(playerPos.getX(), playerPos.getY() - 1);
		}
	}
	
	/**
	 * Handling Position validation
	 * @param x new xCoordinate
	 * @param y new yCoordinate
	 * @return true if position is valid otherwise
	 *		   false if position is not valid
	 */
	public boolean setPosition(Position p)
	{
		boolean set = true;
		int x = p.getX();
		int y = p.getY();
		
		if((x >= 0 && x < Game.getMap().getSize()) && (y >= 0 && y < Game.getMap().getSize()))
		{
			if(Game.getMap().colorMapping[x][y] == 0)
			{
				position.setX(x);
				position.setY(y);
			}
			else
			{
				set = false;
			}
			
		}
		else
		{
			set = false;
		}
	
		return set;
	}
	
	//TODO : to be deleted
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
}
