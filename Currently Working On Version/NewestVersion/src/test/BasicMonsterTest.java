package test;

import static org.junit.Assert.*;
import mapRelated.BasicMap;
import monsterRelated.BasicMonster;
import monsterRelated.Entity;

import org.junit.Test;
import org.newdawn.slick.SlickException;

public class BasicMonsterTest {
	
	
	@Test
	public void testGenericMove() throws SlickException{
		//Arrange Data
		char [][] testMap = new char [BasicMap.widthByTiles][BasicMap.heightByTiles];
		String [][] test = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		for (int i = 0; i < BasicMap.widthByTiles; i++){
			for (int c = 0; c < BasicMap.heightByTiles; c++){
				test [i][c] = " ";
				testMap[i][c] = ' ';
				}
			}
		BasicMap map = new BasicMap(testMap);
		test[5][4] = "P";		
		BasicMonster b = new BasicMonster (map, 7*32, 7*32, 0);
		b.setEntityArray(test);
		
		//Act
		int [] testPosition = {5*32,4*32};//Not in the way player
		int [] monsterPos = {8*32, 7*32};//Monster's supposed current position
			
		//Assert
		assertEquals(false, b.isTaken(8*32,7*32));
		
		//Act
		b.update(testPosition,4000);//Move over by one.
		
		//Assert
		assertArrayEquals(monsterPos, b.getPosition());
		
	}
	
	
	@Test
	public void testPlayerMissing(){
		
		//Arrange Data
		BasicMap map = new BasicMap();
		BasicMonster b = new BasicMonster (map, 4*32, 5*32, 0);
		String [][] test = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		for (int i = 0; i < BasicMap.widthByTiles; i++){
			for (int c = 0; c < BasicMap.heightByTiles; c++){
				test [i][c] = " ";
				}
			}
		b.setEntityArray(test);
		int[] testPosition = {5*BasicMap.TILESIZE, 8*BasicMap.TILESIZE};
		assertEquals(false, ((Entity)b).isTaken(testPosition[0], testPosition[1]));
		
		//Assert and Act
		assertEquals("Player has disappeared from the map.",b.update(testPosition,4000));//Has an error message.
	
	}
	
	@Test
	public void testMonsterOverlappedPlayer(){
		//Arrange Data
				BasicMap map = new BasicMap();
				BasicMonster b = new BasicMonster (map, 4*32, 5*32, 0);
				String [][] test = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
				for (int i = 0; i < BasicMap.widthByTiles; i++){
					for (int c = 0; c < BasicMap.heightByTiles; c++){
						test [i][c] = " ";
						}
					}
				
				b.setEntityArray(test);
				int[] testPosition = {5*BasicMap.TILESIZE, 8*BasicMap.TILESIZE};
				assertEquals(false, ((Entity)b).isTaken(testPosition[0], testPosition[1]));
				test[5][8] = "M";
				//Assert and Act
				assertEquals("Player has disappeared from the map.",b.update(testPosition,4000));//Has an error message.
			
		
	}
	
	
	
	
	@Test
	public void testExpPointGain() throws SlickException{
		BasicMap map = new BasicMap();
		
		BasicMonster m = new BasicMonster (map, 10,10,100);
		assertEquals(1000, m.getMaxHealthPoints());
		assertEquals(500, m.getExpPointGain());
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////TESTS ABILITY TO SELECT A VALID SPOT WHEN NOT BLOCKED BY ANYTHING//////////////////////////////////

	@Test
	public void testFollowPlayer(){
		fail("Not yet implemented");
	}
	
	@Test
	public void testDoNotFollowPlayer(){
		
		fail ("Not yet implemented");
	}
	
	
	
	@Test
	public void testClosestSpotNotNear() throws SlickException{
		String [][] testArray = new String [35][16];
		char [][] testMap = new char [35][16];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap [i][c] = ' ';
			}
		}
		testArray[4][5] = "P";
		int [] playerPosition = {4*BasicMap.TILESIZE,5*BasicMap.TILESIZE};
		
		BasicMap map = new BasicMap(testMap);
		
		BasicMonster b = new BasicMonster(map, 6*32, 5*32, 0);
		b.setEntityArray(testArray);
		int [] expected = {5*32,5*32};
		
		b.findClosestSpot( playerPosition);
		
		assertArrayEquals(expected, b.getPosition());
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
 * Monster stops for obstacle
 * Check wander left
 * check wander right
 * check player stops in front of
 * check wall stops path
 * 
 * 
 * 
 * 
 * 
 */
	
	
	///////////////TESTS ABILITY TO NOT OVERLAP OBSTACLES///////////////////////////////////////////////////////////////////	
// @Test	
//	public void testClosest spot near Diagonal
//	
//	public void testClosestSpotNearHorizontal
//	
//
// public void testClosestSpotNearVertical

}
