import static org.junit.Assert.*;

import org.junit.Test;


public class CalculatorTest
{
	Calculator c = new Calculator();

	@Test
	public void testAdd()
	{
		assertEquals("1 + 1 = 2", 2, c.add(1, 1));
	}
	
	@Test
	public void testSubtract()
	{
		assertEquals("2 - 1 = 1", 1, c.subtract(2, 1));
	}
	@Test
	public void testMultiply()
	{
		assertEquals("2 * 1 = 2", 2, c.multiply(2, 1));
	}
	
	@Test
	public void testDivide()
	{
		assertEquals("10 / 2 = 5", 5, c.divide(10, 2));
	}
}
