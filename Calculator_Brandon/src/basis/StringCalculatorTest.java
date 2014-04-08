package basis;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;

public class StringCalculatorTest
{
	HelloWorld helloWorld;
	
	@Before
	public void test_stadartd_message()
	{
		helloWorld = new HelloWorld();
	}
	
	@After
	public void test_standard_message()
	{
		helloWorld = null;
	}
	
	public void test_standard_message_0()
	{
		assertEquals("", HelloWorld.getMessage(0));
	}
	

}
