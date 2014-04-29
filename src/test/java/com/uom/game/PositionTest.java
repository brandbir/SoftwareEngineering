package test.java.com.uom.game;

import static org.junit.Assert.*;
import main.java.com.uom.game.Position;

import org.junit.Before;
import org.junit.Test;

public class PositionTest {
	
	Position pos;
	Position pos2;
	Position pos3;
	Position pos4;
	
	@Before
	public void before()
	{
		pos = new Position(0,0);
		pos2 = new Position(3,4);
		pos4 = new Position(3,4);
	}

	/**
	 * testing Positions
	 */
	@Test
	public void testingPositions() {
		//testing position 0,0
		assertEquals(pos.getX(),0);
		assertEquals(pos.getY(),0);
		
		//testing position 3,4
		assertEquals(pos2.getX(), 3);
		assertEquals(pos2.getY(), 4);
	}
	
	/**
	 * asserting setters
	 */
	@Test
	public void testingSetters()
	{
		pos3 = new Position();
		pos3.setX(5); // setting positions manually
		pos3.setY(6);
		assertEquals(pos3.getX(),5);
		assertEquals(pos3.getY(),6);
	}
	
	/**
	 * comparing different positions
	 */
	@Test
	public void testingEqualPositions()
	{
		boolean condition1 = pos2.equals(pos4);
		assertTrue(condition1);
		boolean condition2 = pos2.equals(pos);
		assertFalse(condition2);
		
	}
	
}
