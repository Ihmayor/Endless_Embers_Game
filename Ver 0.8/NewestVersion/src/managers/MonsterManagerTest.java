package managers;

import static org.junit.Assert.assertEquals;
import mapRelated.BasicMap;

import org.junit.Test;
import org.newdawn.slick.SlickException;

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
	
}
