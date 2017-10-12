package testRelated;

import static org.junit.Assert.*;
import mapRelated.BasicMap;

import org.junit.Test;

public class BasicMapTest {

	//Check that map can tell if there's a collision at some given x,y
	@Test
	public void testHasCollision() {
		char [][] test = new char [35][16];
		test[3][4] = 'B';
		BasicMap map = new BasicMap(test);
		assertEquals(true, map.hasCollision(3*32,4*32));		
		assertEquals(false, map.isStairs(4*32,6*32));
		
	}
	
	//Check that map can tell if there's stairs at some given x,y
	@Test
	public void testIsStairs(){
		char [][] test = new char [35][16];
		test[3][4] = 'S';
		BasicMap map = new BasicMap(test);
		assertEquals(true, map.isStairs(3*32,4*32));		
		assertEquals(false, map.isStairs(4*32,6*32));
	}
	
	//Check that the map will reject invalid stairs input
	@Test
	public void testInvalidStairsInput(){
		char [][] test = new char [35][16];
		test [3][4] = 'S';
		BasicMap map = new BasicMap(test);
		assertEquals(false,map.isStairs(-40,-21));
		
	}
	
	//Check that the map will reject invalid collision input
	@Test
	public void testInvalidCollisioninput(){
		char [][] test = new char [35][16];
		test[3][4] = 'S';
		BasicMap map = new BasicMap(test);
		assertEquals(false, map.hasCollision(-40,-21));
		
	}
	
}
