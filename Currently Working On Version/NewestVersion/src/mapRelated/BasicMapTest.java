package mapRelated;

import static org.junit.Assert.*;

import org.junit.Test;

public class BasicMapTest {

	@Test
	public void testHasCollision() {
		char [][] test = new char [35][16];
		test[3][4] = 'B';
		BasicMap map = new BasicMap(test);
		assertEquals(true, map.hasCollision(3*32,4*32));		
		assertEquals(false, map.isStairs(4*32,6*32));
		
	}
	
	@Test
	public void testIsStairs(){
		char [][] test = new char [35][16];
		test[3][4] = 'S';
		BasicMap map = new BasicMap(test);
		assertEquals(true, map.isStairs(3*32,4*32));		
		assertEquals(false, map.isStairs(4*32,6*32));
	}
	
	
	@Test(expected= ArrayIndexOutOfBoundsException.class)
	public void testInvalidStairsInput(){
		char [][] test = new char [35][16];
		test[3][4] = 'S';
		BasicMap map = new BasicMap(test);
		map.isStairs(-40,-21);
		
	}
	
	@Test (expected= ArrayIndexOutOfBoundsException.class)
	public void testInvalidCollisioninput(){
		char [][] test = new char [35][16];
		test[3][4] = 'S';
		BasicMap map = new BasicMap(test);
		map.hasCollision(-40,-21);
		
	}
	
}
