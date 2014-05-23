package factory.java.com.uom.game;


public class SafeMapCreator extends MapCreator
{
	public Map createMap(int noOfPlayers, int mapSize)
	{
		Map safeMap = SafeMap.getInstance(noOfPlayers, mapSize); // sippost = new Map()...ed namlu getInstance habba SafeMap Singleton!
		return safeMap;
	}
}
