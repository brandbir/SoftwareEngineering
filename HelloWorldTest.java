


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HelloWorldTest {
	HelloWorld h;
	@Before
    public void test_standard_message()
    {
    	 h = new HelloWorld();
    }
	
    @After
    public void test_standard_message_after() {
    	h = null;
    }
    @Test
    public void test0()
    {
    	assertEquals("", h.getMessage(0));
    }
    @Test
    public void test1()
    {
    	assertEquals("HelloWorld", h.getMessage(1));
    }
    
    @Test
	public void test2() {
		assertEquals("HelloWorldHelloWorld", h.getMessage(2));
	}
    
	@Test
	public void test3() {
		assertEquals("HelloWorldHelloWorldHelloWorld", h.getMessage(3));
	}

}
