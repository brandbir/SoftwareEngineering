package game;

/**
 * Handles the data of the Player's Position
 *
 */
public class Position
{

	private int x;
	private int y;
	
	/**
	 * Default Position Constructor
	 */
	public Position()
	{
		
	}
	
	/**
	 * Constructing a specific position
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	public Position(int xCoordinate, int yCoordinate)
	{
		x = xCoordinate;
		y = yCoordinate;
	}
	
	/**
	 * Set the xCoordinate
	 * @param xCoordinate
	 */
	public void setX(int xCoordinate)
	{
		x = xCoordinate;
	}
	
	/**
	 * Set the yCoordinate
	 * @param yCoordinate
	 */
	public void setY(int yCoordinate)
	{
		y = yCoordinate;
	}
	
	/**
	 * Get the x-coordinate
	 * @return xCoordinate
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Gets the y-coordinate
	 * @return yCoordinate
	 */
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
