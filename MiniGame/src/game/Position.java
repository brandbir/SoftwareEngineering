package game;

public class Position
{

	private int x;
	private int y;
	
	public Position()
	{
		
	}
	
	public Position(int xCoordinate, int yCoordinate)
	{
		x = xCoordinate;
		y = yCoordinate;
	}
	
	public void setX(int xCoordinate)
	{
		x = xCoordinate;
	}
	
	public void setY(int yCoordinate)
	{
		y = yCoordinate;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	/**
	 * Overriding the equals method for comparing two Positions
	 * @param p Position
	 * @return true if the Positions are equivalent, otherwise
	 *		   false if the Positions are not equivalent
	 */
	public boolean equals(Position p)
	{
		boolean equal = false;
		
		if(x == p.getX() && y == p.getY())
			equal = true;
		
		return equal;
	}

}
