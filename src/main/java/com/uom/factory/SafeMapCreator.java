package main.java.com.uom.factory;


public class SafeMapCreator extends MapCreator
{
	public Map createMap(int noOfPlayers, int mapSize)
	{
		Map safeMap = SafeMap.getInstance(noOfPlayers, mapSize); 
		return safeMap;
	}
}
 