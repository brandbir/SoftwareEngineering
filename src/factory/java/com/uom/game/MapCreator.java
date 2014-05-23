package factory.java.com.uom.game;



/**
 * This is the map creator. This Map Creator relies on its 2 subclasses (SafeMapCreator and HazardousMapCreator) to 
 * define the factory method so that it returns an instance of the appropriate ConcreteProduct 
 */
public class MapCreator {    

	public Map createMap(String type, int noOfPlayers, int mapSize)  //need type to choose which level of game to instantiate
	{
		MapCreator creator = findCreatorForType(type); 
		return creator.createMap(noOfPlayers, mapSize);
	}

	public MapCreator findCreatorForType(String type)
	{
		if(type.equals("safe")) 
		{
			 return new SafeMapCreator(); 				//SafeMap Creator is a concrete product
		}
		else if(type.equals("hazardous"))
		{
			return new HazardousMapCreator();   //Hazardous Map is a concrete product
		}
		return null;  //check
	}
	
	public Map createMap(int noOfPlayers, int mapSize) {
		return null;  
	}

}
