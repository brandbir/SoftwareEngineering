package factory.java.com.uom.game;


public class HazardousMapCreator extends MapCreator
{
	public Map createMap(int noOfPlayers, int mapSize)
	{
		Map hazardMap = HazardousMap.getInstance(noOfPlayers, mapSize);
		return hazardMap;
	}
}
