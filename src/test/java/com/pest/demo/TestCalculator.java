//test 
package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCalculator {
    
	@Test
	public void test1() {
		Calculator calcTest = new Calculator();
		assertEquals("2+2 = 4",4, calcTest.add(2, 2));//if it fails the message will be outputted in the failure trace
	}
	@Test
	public void test2() {
		Calculator calcTest = new Calculator();
		assertEquals("2/0 = -999",-999, calcTest.divide(2, 0));
	}
	@Test
	public void test3() {
		Calculator calcTest = new Calculator();
		assertEquals("2*3 = 6",6, calcTest.multiply(2, 3));
	}
	@Test
	public void test4() {
		Calculator calcTest = new Calculator();
		assertEquals("3-1 = 2",2, calcTest.subtract(3, 1));
	}
    
}
