package main.java.com.uom.factory;


public class HazardousMapCreator extends MapFactory
{
	/**
	 * creating hazardousMap instance
	 */
	public Map createMap(int noOfPlayers, int mapSize)
	{
		Map hazardMap = HazardousMap.getInstance(noOfPlayers, mapSize);
		return hazardMap;
	}
}
