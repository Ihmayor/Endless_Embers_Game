package test;

import static org.junit.Assert.*;
import managers.MonsterManager;
import mapRelated.BasicMap;

import org.junit.Test;

public class MonsterManagerTest {

	@Test
	public void testInvalidEntityArray(){
		MonsterManager m = new MonsterManager();
		String [][] testArray = new String [25][15];
		assertEquals("Entity Array Not Expected Size",m.checkEntityArray(testArray));
	}
	
	@Test
	public void testNullEntityArray(){
		MonsterManager m = new MonsterManager();
		String [][] testArray = new String [35][16];
		assertEquals("EntityArray cannot have null objects", m.checkEntityArray(testArray));
		
	}
	
	
	@Test
	public void testValidEntityArray(){
		//Arrange
		MonsterManager m = new MonsterManager();
		String [][] testArray = new String [35][16];

		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
			}
		}
		testArray[4][5] = "P";
		
		//Act and assert
		assertEquals(null,m.checkEntityArray(testArray));	
	}

	@Test
	public void testPlayerNotInEntityArray(){
		MonsterManager m = new MonsterManager();
		String [][] testArray = new String [35][16];

		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
			}
		}

		assertEquals("Player has disappeared offf entityArray","Player not in EntityArray",m.checkEntityArray(testArray));	
	}
	
	
	@Test
	public void testCheckValidPlacement(){
		//Arrange
		MonsterManager m = new MonsterManager();
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

		//Double check that its a valid entity Array
		assertEquals("Invalid Entity Array", m.setEntityArray(testArray));
		testArray[5][7] = "P";
		assertEquals(null, m.setEntityArray(testArray));
		int [] testPosition = {4*BasicMap.TILESIZE,5*BasicMap.TILESIZE};
		
		
		
		
		assertEquals(null, m.checkValidPlacement(testPosition, 4, new BasicMap(testMap), testArray));
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap [i][c] = 'B';
			}
		}
		testMap[31][15] = ' ';
		testMap[32][15] = ' ';
		testMap[33][15] = ' ';
		
		testPosition[0] = 992;
		testPosition[1] = 480;
		assertEquals("Map Overlap", m.checkValidPlacement(testPosition, 4, new BasicMap (testMap), testArray));
		
	}

	
	@Test
	public void testInvalidPositionGenerated(){
		
		MonsterManager m = new MonsterManager();
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
		BasicMap map = new BasicMap (testMap);
		int [] newPosition = {-100,-100};
		assertEquals("Out of Bounds", m.checkValidPlacement(newPosition, 4, map, testArray));
		int [] thirdTestPosition = {1120, 512};
		assertEquals("Out of Bounds", m.checkValidPlacement(thirdTestPosition, 4, map, testArray));
	}
	

	
	
	
	
	@Test
	public void testCheckOutOfBoundsPlacements(){
		//Arrange
		MonsterManager m = new MonsterManager();
		String [][] testArray = new String [35][16];
		char [][] testMap = new char [35][16];
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap[i][c] = ' ';
			}
		}

		//Double check that its a valid entity Array
		assertEquals("Invalid Entity Array", m.setEntityArray(testArray));
		testArray[5][7] = "P";
		assertEquals(null, m.setEntityArray(testArray));
		int [] testPosition = {2000,5};
		
			
		assertEquals("Do not place monsters out of game's boundaries","Out of Bounds", m.checkValidPlacement(testPosition, 4, new BasicMap(testMap), testArray));
	}

	
	
	
	@Test
	public void testCheckEntityOverlap(){
		//Arrange
		MonsterManager m = new MonsterManager();
		String [][] testArray = new String [35][16];
		char [][] testMap = new char [35][16];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				 testMap[i][c] = ' ';
				
			}
		}

		//Double check that its a valid entity Array
		assertEquals("Invalid Entity Array", m.setEntityArray(testArray));
		testArray[5][7] = "P";
		assertEquals(null, m.setEntityArray(testArray));
		int [] testPosition = {5*BasicMap.TILESIZE,7*BasicMap.TILESIZE};
				
		assertEquals("Should be preventing placement over the player and other monsters","Entity Overlap", m.checkValidPlacement(testPosition, 4, new BasicMap(testMap), testArray));
	}
	
	
	@Test
	public void testCheckMapOverlap(){
		//Arrange
		MonsterManager m = new MonsterManager();
		String [][] testArray = new String [35][16];
		char [][] testMap = new char [35][16];
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap[i][c] = ' ';
			}
		}

		//Double check that its a valid entity Array
		assertEquals("Invalid Entity Array", m.setEntityArray(testArray));
		testArray[5][7] = "P";
		assertEquals(null, m.setEntityArray(testArray));
		int [] testPosition = {9*BasicMap.TILESIZE,9*BasicMap.TILESIZE};
		
		
		testMap[9][9] = 'B';
		
		assertEquals("We should be preventing placement that overlaps obstacles","Map Overlap", m.checkValidPlacement(testPosition, 4, new BasicMap(testMap), testArray));
	}
//	
	@Test
	public void testFindPlacement_NoBlocks (){
		//Arrange
		MonsterManager m = new MonsterManager();
		String [][] testArray = new String [35][16];
		char [][] testMap = new char [35][16];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap[i][c] = ' ';
			}
		}
		int [] testPosition = m.findValidPlacement(4, new BasicMap(testMap), testArray);
		
		assertNotNull(testPosition);
		
	}

	
	@Test
	public void testFindPlacement_OneSpotLeft_EntityArrayOnly (){
		//Arrange
		String [][] testArray = new String [35][16];
		char [][] testMap = new char [35][16];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = "M";
				testMap[i][c] = ' ';
			}
		}
		testArray[30][15] = " ";
		testArray[31][15] = " ";
		testArray[32][15] = " ";
		testArray[33][15] = " ";
		
		MonsterManager m = new MonsterManager();
		m.setEntityArray(testArray);
		int [] testPosition = m.findValidPlacement(4, new BasicMap(testMap), testArray);
		int [] expectedPosition = {30*BasicMap.TILESIZE,15*BasicMap.TILESIZE};
		assertArrayEquals("Check that it can find Valid last spot",expectedPosition, testPosition);
		
	}
	
	@Test
	public void testFindPlacement_OneSpotLeft_Edges(){
		String [][] testArray = new String [35][16];
		char [][] testMap = new char [35][16];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = "M";
				testMap[i][c] = ' ';
			}
		}
		testArray[31][15] = " ";
		testArray[32][15] = " ";
		testArray[33][15] = " ";
		testArray[34][15] = " ";
		
		MonsterManager m = new MonsterManager();
		m.setEntityArray(testArray);
		int [] testPosition = m.findValidPlacement(4, new BasicMap(testMap), testArray);
		int [] expectedPosition = {31*BasicMap.TILESIZE,15*BasicMap.TILESIZE};
		assertArrayEquals("Check that it can find corners",expectedPosition, testPosition);
		
		testArray[31][15] = "M";
		testArray[32][15] = "M";
		testArray[33][15] = "M";
		testArray[34][15] = "M";
	
		testArray[34][12] = " ";
		testArray[34][13] = " ";
		testArray[34][14] = " ";
		testArray[34][15] = " ";
		testPosition = m.findValidPlacement(4, new BasicMap(testMap), testArray);
		assertArrayEquals("Check that it cannot look vertically" ,null, testPosition);
	}
	
	
	
	@Test
	public void testFindPlacement_OneSpotLeft_MapArrayOnly (){
		//Arrange
		String [][] testArray = new String [35][16];
		char [][] testMap = new char [35][16];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
				testMap[i][c] = 'B';
			}
		}
		
		
		testMap[30][15] = ' ';
		testMap[31][15] = ' ';
		testMap[32][15] = ' ';
		testMap[33][15] = ' ';
		
		MonsterManager m = new MonsterManager();
		int [] testPosition = m.findValidPlacement(4, new BasicMap(testMap), testArray);
		int [] expectedPosition = {30*BasicMap.TILESIZE,15*BasicMap.TILESIZE};
		assertArrayEquals("Check that it can see overlap with map",expectedPosition, testPosition);
		
	}

	
	
	
	@Test
	public void testFindPlacement_NoSpotsLeft (){
		MonsterManager m = new MonsterManager();
		String [][] testArray = new String [35][16];
		char [][] testMap = new char [35][16];
		
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = "P";
				testMap[i][c] = 'B';
			}
		}
		int [] testPosition = new int [2];
		testPosition = m.findValidPlacement(4, new BasicMap(testMap), testArray);
		assertEquals( null, testPosition);
	}
	
	
	
	@Test
	public void testClearMonsters(){
		MonsterManager m = new MonsterManager();
		String [][] testArray = new String [35][16];
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
			}
		}
		testArray[4][5] = "P";
		assertEquals(null, m.setEntityArray(testArray));
		assertEquals(null, m.clearMonsters());
	}
	
}
