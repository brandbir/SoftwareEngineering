package main.java.com.uom.factory;


public class SafeMapCreator extends MapFactory
{
	/**
	 * creating safeMap instance
	 */
	public Map createMap(int noOfPlayers, int mapSize)
	{
		Map safeMap = SafeMap.getInstance(noOfPlayers, mapSize); 
		return safeMap;
	}
}
 