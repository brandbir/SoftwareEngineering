package main.java.com.uom.factory;



/**
 * This is the map factory. This Map factory relies on its 2 subclasses (SafeMapCreator and HazardousMapCreator) to 
 * define the factory method so that it returns an instance of the appropriate ConcreteProduct 
 */
public class MapFactory {    

	public Map createMap(String type, int noOfPlayers, int mapSize)  //need type to choose which level of game to instantiate
	{
		MapFactory creator = findCreatorForType(type); 
		return creator.createMap(noOfPlayers, mapSize);
	}

	public MapFactory findCreatorForType(String type)
	{
		if(type.equals("safe") || type.equals("Safe") || type.equals("SAFE"))  
		{
			 return new SafeMapCreator(); //SafeMap Creator is a concrete product
		}
		else if(type.equals("hazardous") || type.equals("Hazardous") || type.equals("HAZARDOUS"))
		{
			return new HazardousMapCreator();//Hazardous Map is a concrete product
		}
		return null;
	}
	
	public Map createMap(int noOfPlayers, int mapSize) {
		return null;
	}

}
