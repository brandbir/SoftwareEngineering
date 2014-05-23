package test.java.com.uom.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factory.java.com.uom.game.Map;
import factory.java.com.uom.game.MapCreator;

public class MapCreatorTest {

	Map createdMap = null;
	Map createdMap2 = null;
	MapCreator create = new MapCreator();
	
	
	
	@Test 
	public void intializationTest1() {
		
		createdMap = create.createMap("hazardous", 3, 5);
		
		Class c = createdMap.getClass(); 
		String packageName = c.getName(); 
		String className = c.getSimpleName();
		
		assertEquals("factory.java.com.uom.game.HazardousMap", packageName);
		assertEquals("HazardousMap", className);
		
		
	}
	
	/**
	 * asserting that there can be only one instance of the class
	 */
	@Test
	public void initializationTest2()
	{
		createdMap = create.createMap("safe", 3, 5);
		
		Class c = createdMap.getClass();
		String packageName = c.getName(); 
		String className = c.getSimpleName();
		
		System.out.println(packageName);
		assertNotEquals("factory.java.com.uom.game.SafeMap", packageName);
		assertNotEquals("SafeMap", className);
	}
	
	

}
