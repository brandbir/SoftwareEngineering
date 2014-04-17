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
		System.out.println("pos:" + position.getX() + " " + position.getY());
		switch(direction)
		{
			case 'L':
				setPosition(position.getX(), position.getY() - 1);
				System.out.println("new pos:" + position.getX() + " " + position.getY());

				break;
			
			case 'R':
				setPosition(position.getX(), position.getY() + 1);
				System.out.println("new pos:" + position.getX() + " " + position.getY());

				break;
			
			case 'U':
				setPosition(position.getX() - 1, position.getY());
				System.out.println("new pos:" + position.getX() + " " + position.getY());

				break;
			
			case 'D':
				setPosition(position.getX() + 1, position.getY());
				System.out.println("new pos:" + position.getX() + " " + position.getY());

				break;
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
}
