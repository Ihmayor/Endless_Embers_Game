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
	}
	
	@Test
	public void testIsStairs(){
		char [][] test = new char [35][16];
		test[3][4] = 'S';
		BasicMap map = new BasicMap(test);
		assertEquals(true, map.isStairs(3*32,4*32));		
		
	}
	
	//Need to write a has collision test
	//Need to write a isStairs test

}
