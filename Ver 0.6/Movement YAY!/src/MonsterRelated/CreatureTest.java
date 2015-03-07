package MonsterRelated;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import mapRelated.BasicMap;

import org.junit.Test;

public class CreatureTest {

	
	@Test
	public void testSearchOneCreature() {
		//Set-Up
		Creature c = new Creature(4*32,4*32);
		String [][] test = new String[BasicMap.widthByTiles][BasicMap.heightByTiles];
		test[3][4] = "M";	
		c.setEntityArray(test);
		String[][] test1 = c.entityArray;
		//Test if the we /definitely/ have the same array inside this creature
		assertArrayEquals(test, test1);
		//Test to see if there is a Player nearby
		assertEquals(false, c.search("P"));
		//Test to see if there is an 'M' or essentially if it can spot itself
		assertEquals(true, c.search("M"));
	}
	@Test
	public void testWanderCreature(){
		Creature c = new Creature(3*32, 4*32);
		c.name = "M";
	
		String [][] test = new String[35][16];
		for (int i = 0; i <BasicMap.widthByTiles; i++)
		{
			for (int d = 0; d < BasicMap.heightByTiles; d++)
				test[i][d] = " ";
		}
		c.setEntityArray(test);
		c.updatePosition(5*32, 7*32);
		c.updatePosition(2*32, 4*32);
		c.updatePosition(5*32, 5*32);
		

		String [][] test2 = new String[35][16];
		for (int i = 0; i <BasicMap.widthByTiles; i++)
		{
			for (int d = 0; d < BasicMap.heightByTiles; d++)
				test2[i][d] = " ";
		}
		test2[5][5] = "M";
	
		assertArrayEquals(test2,c.getEntityArray());
		
	}
	
	@Test
	public void testSetPositionOneCreature(){
		//Test Set-Up
		Creature c = new Creature(3*32, 4*32);
		c.name = "M";
		//We /have/ to create an array with " " in every spot.
		//Hence the terrible need for loop logic.
		String [][] test = new String[35][16];
		for (int i = 0; i <BasicMap.widthByTiles; i++)
		{
			for (int d = 0; d < BasicMap.heightByTiles; d++)
				test[i][d] = " ";
		}
		//Writes an 'M' in a spot that we later erase.
		test[3][4] = "M";
		c.setEntityArray(test);
		
		//Updates the position of the creature
		c.updatePosition(4*32, 4*32);
		
		//Create a blank another array to compare to
		String[][] testEntity = new String[BasicMap.widthByTiles][BasicMap.heightByTiles];
		for (int i = 0; i <BasicMap.widthByTiles; i++)
		{
			for (int d = 0; d < BasicMap.heightByTiles; d++)
				testEntity[i][d] = " ";
		}
		//Manually store the new position
		testEntity[4][4] = "M";
		//Get the entityArray for this creature 
		String[][] entityArray = c.getEntityArray();
		//Check if they are the same.
		assertArrayEquals(testEntity, entityArray);
	}
	
	@Test
	public void testTwoMovingCreatures(){
		Creature c = new Creature(5*32,2*32);
		Creature c2 = new Creature(7*32,9*32);
		
		String [][] testEntity = new String[BasicMap.widthByTiles][ BasicMap.heightByTiles];
		
		for (int i = 0; i <BasicMap.widthByTiles; i++)
		{
			for (int d = 0; d < BasicMap.heightByTiles; d++)
				testEntity[i][d] = " ";
		}
		testEntity[3][5] = "T";
		testEntity[5][5] = "B";
		c.setEntityArray(testEntity);
		c2.setEntityArray(testEntity);
		
		c.updatePosition(9*32, 9*32);
		c2.updatePosition(5*32, 1*32);
		
		assertEquals(true, c2.isTaken(9, 9)); //C2 knows where the update position of c
		assertArrayEquals(c.getEntityArray(), c2.getEntityArray());//They both have the same array
		
	}
	
	@Test
	public void isTakenOneCreature(){
		//Set-Up Test
		String[][] testEntity = new String[BasicMap.widthByTiles][BasicMap.heightByTiles];
		for (int i = 0; i <BasicMap.widthByTiles; i++)
		{
			for (int d = 0; d < BasicMap.heightByTiles; d++)
				testEntity[i][d] = " ";
		}
		testEntity[3][5] = "T";
		Creature c = new Creature(3*32, 5*32);
		c.name = "T";
		c.setEntityArray(testEntity);
		
		//The entity array should know there is something in the array.
		assertEquals(false, c.isTaken(4,5));
		assertEquals(true,c.isTaken(3,5));
	}
	
	@Test
	public void isTakenTwoCreatures(){
		String[][] testEntity = new String[BasicMap.widthByTiles][BasicMap.heightByTiles];
		for (int i = 0; i <BasicMap.widthByTiles; i++)
		{
			for (int d = 0; d < BasicMap.heightByTiles; d++)
				testEntity[i][d] = " ";
		}
		testEntity[3][5] = "T";
		testEntity[5][5] = "B";
		Creature c = new Creature(3*32, 5*32);
		Creature c2 = new Creature(5*32, 5*32);
		c.name = "T";
		c2.name = "B";
		c.setEntityArray(testEntity);
		c2.setEntityArray(testEntity);
		//The entity array should know there is something in the array.
		assertEquals(false, c.isTaken(4,5));
		assertEquals(true,c.isTaken(5,5));
		assertEquals(false, c2.isTaken(2, 6));
		assertEquals(true, c.isTaken(3,5));
	}

}
