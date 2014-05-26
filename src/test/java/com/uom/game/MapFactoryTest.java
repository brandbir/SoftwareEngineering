package test.java.com.uom.game;

import static org.junit.Assert.*;
import main.java.com.uom.factory.HazardousMap;
import main.java.com.uom.factory.Map;
import main.java.com.uom.factory.MapFactory;
import main.java.com.uom.factory.SafeMap;

import org.junit.Test;

public class MapFactoryTest {

	Map createdMap = null;
	MapFactory create = new MapFactory();
	
	
	/**
	 * asserting that there can be only one instance of the class
	 */
	@Test 
	public void intializationTest1() {
		
			Map.setInstanceNull();
			createdMap = create.createMap("safe", 3, 5);
			
			createdMap = HazardousMap.getInstance(4, 5);
			assertFalse(createdMap instanceof HazardousMap);
			assertTrue(createdMap instanceof SafeMap);
			
			Class<? extends Map> c = createdMap.getClass(); 
			String packageName = c.getName(); 
			String className = c.getSimpleName();
			
			assertEquals("main.java.com.uom.factory.SafeMap", packageName);
			assertEquals("SafeMap", className); 
			
		
	} 
	
}
