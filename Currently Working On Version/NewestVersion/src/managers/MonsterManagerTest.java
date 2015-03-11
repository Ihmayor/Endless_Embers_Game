package managers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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

		assertEquals("Player not in EntityArray",m.checkEntityArray(testArray));	
	}
	
	
	@Test
	public void testFindValidPlacement(){
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

		//Double check that its a valid entity Array
		assertEquals("Invalid Entity Array", m.setEntityArray(testArray));
		testArray[5][7] = "P";
		assertEquals(null, m.setEntityArray(testArray));
		int [] testPosition = {4,5};
		
		char [][] testMap = new char [35][16];
		for (char[] row: testMap){
			for (char i: row)
			{
				i = ' ';
			}
		}
		
		
		assertEquals(null, m.checkValidPlacement(testPosition, 4, new BasicMap(testMap), testArray));
		
	}
	
	
	
	@Test
	public void testCheckOutOfBoundsPlacements(){
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

		//Double check that its a valid entity Array
		assertEquals("Invalid Entity Array", m.setEntityArray(testArray));
		testArray[5][7] = "P";
		assertEquals(null, m.setEntityArray(testArray));
		int [] testPosition = {2000,5};
		
		char [][] testMap = new char [35][16];
		for (char[] row: testMap){
			for (char i: row)
			{
				i = ' ';
			}
		}	
		assertEquals("Out of Bounds", m.checkValidPlacement(testPosition, 4, new BasicMap(testMap), testArray));
	}
	
	@Test
	public void testCheckEntityOverlap(){
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

		//Double check that its a valid entity Array
		assertEquals("Invalid Entity Array", m.setEntityArray(testArray));
		testArray[5][7] = "P";
		assertEquals(null, m.setEntityArray(testArray));
		int [] testPosition = {5*BasicMap.TILESIZE,7*BasicMap.TILESIZE};
		
		char [][] testMap = new char [35][16];
		for (char[] row: testMap){
			for (char i: row)
			{
				i = ' ';
			}
		}
		
		assertEquals("Entity Overlap", m.checkValidPlacement(testPosition, 4, new BasicMap(testMap), testArray));
	}
	
	
	@Test
	public void testCheckMapOverlap(){
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

		//Double check that its a valid entity Array
		assertEquals("Invalid Entity Array", m.setEntityArray(testArray));
		testArray[5][7] = "P";
		assertEquals(null, m.setEntityArray(testArray));
		int [] testPosition = {9*BasicMap.TILESIZE,9*BasicMap.TILESIZE};
		
		char [][] testMap = new char [35][16];
		for (char[] row: testMap){
			for (char i: row)
			{
				i = ' ';
			}
		}
		testMap[9][9] = 'B';
		
		assertEquals("Map Overlap", m.checkValidPlacement(testPosition, 4, new BasicMap(testMap), testArray));
	}
	
	@Test
	public void testFindPlacement_NoBlocks (){
		//Arrange
		String [][] testArray = new String [35][16];

		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				testArray[i][c] = " ";
			}
		}
		char [][] testMap = new char [35][16];
		for (char[] row: testMap){
			for (char i: row)
			{
				i = ' ';
			}
		}

		
		fail("not yet implemented");
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////TESTS TO DO///////////////////////////////////////////////////////
//	
//	@Test
//	public void testFindPlacement_OneSpotLeft_EntityArrayOnly (){
//		//Arrange
//		String [][] testArray = new String [35][16];
//
//		for (int i = 0; i < BasicMap.widthByTiles; i++)
//		{
//			for (int c = 0; c < BasicMap.heightByTiles; c++)
//			{
//				testArray[i][c] = " ";
//			}
//		}
//		char [][] testMap = new char [35][16];
//		for (char[] row: testMap){
//			for (char i: row)
//			{
//				i = ' ';
//			}
//		}
//
//		
//		fail("not yet implemented");
//	}
//		
//	@Test
//	public void testFindPlacement_OneSpotLeft_MapArrayOnly (){
//		//Arrange
//		String [][] testArray = new String [35][16];
//
//		for (int i = 0; i < BasicMap.widthByTiles; i++)
//		{
//			for (int c = 0; c < BasicMap.heightByTiles; c++)
//			{
//				testArray[i][c] = " ";
//			}
//		}
//		char [][] testMap = new char [35][16];
//		for (char[] row: testMap){
//			for (char i: row)
//			{
//				i = ' ';
//			}
//		}
//
//		
//		fail("not yet implemented");
//	}
//
//	@Test
//	public void testFindPlacement_NoSpotsLeft (){
//		//Arrange
//		String [][] testArray = new String [35][16];
//
//		for (int i = 0; i < BasicMap.widthByTiles; i++)
//		{
//			for (int c = 0; c < BasicMap.heightByTiles; c++)
//			{
//				testArray[i][c] = " ";
//			}
//		}
//		char [][] testMap = new char [35][16];
//		for (char[] row: testMap){
//			for (char i: row)
//			{
//				i = ' ';
//			}
//		}
//
//		
//		fail("not yet implemented");
//	}
//	
	
}
