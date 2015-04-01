package test;

import static org.junit.Assert.*;
import mapRelated.BasicMap;
import monsterRelated.BasicMonster;
import monsterRelated.Entity;

import org.junit.Test;
import org.newdawn.slick.SlickException;

public class BasicMonsterTest {
	
	//Tests that the basic monster will move around the map properly
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
		BasicMonster b = new BasicMonster (map, 7*BasicMap.TILESIZE, 7*BasicMap.TILESIZE, 0);
		b.setEntityArray(test);
		
		//Act
		int [] testPosition = {5*BasicMap.TILESIZE,4*BasicMap.TILESIZE};//Not in the way player
		int [] monsterPos = {8*BasicMap.TILESIZE, 7*BasicMap.TILESIZE};//Monster's supposed current position
			
		//Assert
		assertEquals(false, b.isTaken(8*BasicMap.TILESIZE,7*BasicMap.TILESIZE));
		
		//Act
		b.update(testPosition,4000);//Move over by one.
		
		//Assert
		assertArrayEquals(monsterPos, b.getPosition());
		
	}
	
	//Checks that the player's location has not been overwritten or erased by another class
	@Test
	public void testPlayerMissing(){
		
		//Arrange Data
		BasicMap map = new BasicMap();
		BasicMonster b = new BasicMonster (map, 4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, 0);
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
	
	//Checks that the monster update never overlaps the player
	@Test
	public void testMonsterOverlappedPlayer(){
		//Arrange Data
				BasicMap map = new BasicMap();
				BasicMonster b = new BasicMonster (map, 4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, 0);
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
	
	
	
	//Tests the experience points gained from a kill
	@Test
	public void testExpPointGain() throws SlickException{
		BasicMap map = new BasicMap();
		
		BasicMonster m = new BasicMonster (map, 10,10,100);
		assertEquals(1000, m.getMaxHealthPoints());
		assertEquals(520, m.getExpPointGain());
	}
	
    //Tests that monsters are erased from the map when they die
	@Test
	public void testMonsterDead(){	
	String [][] testArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
	char [][] testMap = new char [BasicMap.widthByTiles][BasicMap.heightByTiles];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap [i][c] = ' ';
			}
		}
	testArray [4][5] = "P";
	BasicMap map = new BasicMap(testMap);
	BasicMonster m = new BasicMonster (map, 9*BasicMap.TILESIZE, 5*BasicMap.TILESIZE,100);
	m.setEntityArray(testArray);
    m.subtractHealth(1000);
    
    assertEquals(false, m.getAlive());
	int [] playerPosition = {4*BasicMap.TILESIZE,5*BasicMap.TILESIZE};
    m.update(playerPosition, 400);
    int [] expectedPosition = {9*BasicMap.TILESIZE, 5*BasicMap.TILESIZE};
    assertArrayEquals(expectedPosition, m.getPosition());
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////TESTS ABILITY TO FIND THE CLOSEST SPOT NEAR A PLAYER TO "FOLLOW" THEM.//////////////////////////////////
	
	@Test
	public void testFollowPlayerLeft() throws SlickException{
		String [][] testArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		char [][] testMap = new char [BasicMap.widthByTiles][BasicMap.heightByTiles];
		
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
		
		BasicMonster b = new BasicMonster(map, 6*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, 0);
		b.setEntityArray(testArray);
		int [] expected = {5*BasicMap.TILESIZE,5*BasicMap.TILESIZE};
		
		b.update(playerPosition, 400);
		assertArrayEquals(expected, b.getPosition());
	}
	

	@Test
	public void testFollowPlayerBelow() throws SlickException{
		String [][] testArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		char [][] testMap = new char [BasicMap.widthByTiles][BasicMap.heightByTiles];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap [i][c] = ' ';
			}
		}
		testArray[5][7] = "P";
		int [] playerPosition = {5*BasicMap.TILESIZE,7*BasicMap.TILESIZE};
		
		BasicMap map = new BasicMap(testMap);
		
		BasicMonster b = new BasicMonster(map, 5*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, 0);
		b.setEntityArray(testArray);
		int [] expected = {5*BasicMap.TILESIZE,6*BasicMap.TILESIZE};
		assertEquals(true, b.search("P"));
		b.update(playerPosition, 400);
		assertArrayEquals(expected, b.getPosition());
	}
	
	@Test
	public void testFollowPlayerRight() throws SlickException{
		String [][] testArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		char [][] testMap = new char [BasicMap.widthByTiles][BasicMap.heightByTiles];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap [i][c] = ' ';
			}
		}
		testArray[7][5] = "P";
		int [] playerPosition = {7*BasicMap.TILESIZE,5*BasicMap.TILESIZE};
		
		BasicMap map = new BasicMap(testMap);
		
		BasicMonster b = new BasicMonster(map, 5*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, 0);
		b.setEntityArray(testArray);
		int [] expected = {6*BasicMap.TILESIZE,5*BasicMap.TILESIZE};
		assertEquals(true, b.search("P"));
		b.update(playerPosition, 400);
		assertArrayEquals(expected, b.getPosition());
	}
	
	@Test
	public void testFollowPlayerUp() throws SlickException{
		String [][] testArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		char [][] testMap = new char [BasicMap.widthByTiles][BasicMap.heightByTiles];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap [i][c] = ' ';
			}
		}
		testArray[5][7] = "P";
		int [] playerPosition = {5*BasicMap.TILESIZE,7*BasicMap.TILESIZE};
		
		BasicMap map = new BasicMap(testMap);
		
		BasicMonster b = new BasicMonster(map, 5*BasicMap.TILESIZE, 9*BasicMap.TILESIZE, 0);
		b.setEntityArray(testArray);
		int [] expected = {5*BasicMap.TILESIZE,8*BasicMap.TILESIZE};
		assertEquals(true, b.search("P"));
		b.update(playerPosition, 400);
		assertArrayEquals(expected, b.getPosition());
	}
	
	@Test
	public void testFollowPlayerDiagonalUpLeft() throws SlickException{
		String [][] testArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		char [][] testMap = new char [BasicMap.widthByTiles][BasicMap.heightByTiles];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap [i][c] = ' ';
			}
		}
		testArray[5][5] = "P";
		int [] playerPosition = {5*BasicMap.TILESIZE,5*BasicMap.TILESIZE};
		
		BasicMap map = new BasicMap(testMap);
		
		BasicMonster b = new BasicMonster(map, 7*BasicMap.TILESIZE, 7*BasicMap.TILESIZE, 0);
		b.setEntityArray(testArray);
		int [] expected = {6*BasicMap.TILESIZE,6*BasicMap.TILESIZE};
		assertEquals(true, b.search("P"));
		b.update(playerPosition, 400);
		assertArrayEquals(expected, b.getPosition());
	}
	
	@Test
	public void testFollowPlayerDiagonalUpRight() throws SlickException{
		String [][] testArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		char [][] testMap = new char [BasicMap.widthByTiles][BasicMap.heightByTiles];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap [i][c] = ' ';
			}
		}
		testArray[7][3] = "P";
		int [] playerPosition = {7*BasicMap.TILESIZE,3*BasicMap.TILESIZE};
		
		BasicMap map = new BasicMap(testMap);
		
		BasicMonster b = new BasicMonster(map, 5*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, 0);
		b.setEntityArray(testArray);
		int [] expected = {6*BasicMap.TILESIZE,4*BasicMap.TILESIZE};
		assertEquals(true, b.search("P"));
		b.update(playerPosition, 400);
		assertArrayEquals(expected, b.getPosition());
	}
	
	@Test
	public void testFollowPlayerDiagonalDownLeft() throws SlickException{
		String [][] testArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		char [][] testMap = new char [BasicMap.widthByTiles][BasicMap.heightByTiles];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap [i][c] = ' ';
			}
		}
		testArray[5][9] = "P";
		int [] playerPosition = {5*BasicMap.TILESIZE,9*BasicMap.TILESIZE};
		
		BasicMap map = new BasicMap(testMap);
		
		BasicMonster b = new BasicMonster(map, 7*BasicMap.TILESIZE, 7*BasicMap.TILESIZE, 0);
		b.setEntityArray(testArray);
		int [] expected = {6*BasicMap.TILESIZE,8*BasicMap.TILESIZE};
		assertEquals(true, b.search("P"));
		b.update(playerPosition, 400);
		assertArrayEquals(expected, b.getPosition());
	}
	
	@Test
	public void testFollowPlayerDiagonalDownRight() throws SlickException{
		//Arrange Data
		String [][] testArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		char [][] testMap = new char [BasicMap.widthByTiles][BasicMap.heightByTiles];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap [i][c] = ' ';
			}
		}
		testArray[7][9] = "P";
		int [] playerPosition = {7*BasicMap.TILESIZE,9*BasicMap.TILESIZE};
		
		BasicMap map = new BasicMap(testMap);
		
		BasicMonster b = new BasicMonster(map, 5*BasicMap.TILESIZE, 7*BasicMap.TILESIZE, 0);
		b.setEntityArray(testArray);
		int [] expected = {6*BasicMap.TILESIZE,8*BasicMap.TILESIZE};		
		assertEquals(true, b.search("P"));
		
		//Act
		b.update(playerPosition, 400);
		//Assert
		assertArrayEquals(expected, b.getPosition());
	}
	

}
