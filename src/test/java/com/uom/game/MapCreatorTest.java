package test.java.com.uom.game;

import static org.junit.Assert.*;
import main.java.com.uom.factory.Map;
import main.java.com.uom.factory.MapCreator;

import org.junit.Test;

public class MapCreatorTest {

	Map createdMap = null;
	Map createdMap2 = null;
	MapCreator create = new MapCreator();
	
	
	/**
	 * asserting that there can be only one instance of the class
	 */
	@Test 
	public void intializationTest1() {
		
			createdMap = create.createMap("hazardous", 3, 5);
			
			Class<? extends Map> c = createdMap.getClass(); 
			String packageName = c.getName(); 
			String className = c.getSimpleName();
			
			assertEquals("main.java.com.uom.factory.HazardousMap", packageName);
			assertEquals("HazardousMap", className);
			
			createdMap = create.createMap("safe", 3, 5);
			
			c = createdMap.getClass();
			packageName = c.getName(); 
			className = c.getSimpleName();
			
			System.out.println(packageName);
			assertNotEquals("main.java.com.uom.factory.SafeMap", packageName);
			assertNotEquals("SafeMap", className);
		
		
	}
	
}
