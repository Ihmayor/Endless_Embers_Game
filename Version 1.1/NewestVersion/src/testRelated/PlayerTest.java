package testRelated;

import static org.junit.Assert.*;


import mapRelated.BasicMap;

import org.junit.Test;

import playerRelated.Player;
import playerRelated.PlayerMovement;
import playerRelated.PlayerStatus;

public class PlayerTest {


	
	@Test
	public void testExperiencePoints(){
		Player player = new Player (10, 10);
		PlayerStatus playerStats = player.getPlayerStatus(); 
		playerStats.addExperiencePoints(10);
		assertEquals(0, playerStats.getExperiencePoints());
		assertEquals(20,playerStats.getPointsNextLevel());
		playerStats.addExperiencePoints(20);
		assertEquals(40,playerStats.getPointsNextLevel());
		playerStats.addExperiencePoints(40);
		assertEquals(80,playerStats.getPointsNextLevel());
		playerStats.addExperiencePoints(80);
		assertEquals(160,playerStats.getPointsNextLevel());
		playerStats.addExperiencePoints(160);
		assertEquals(320,playerStats.getPointsNextLevel());	
		playerStats.addExperiencePoints(320);
		assertEquals(640,playerStats.getPointsNextLevel());	
		assertEquals(7, playerStats.getPlayerLevel());
		
	}

	@Test
	public void testNegativeExperiencePoints(){
		//Arrange
		Player player = new Player (10,10);		
		PlayerStatus playerStats = player.getPlayerStatus(); 
		assertEquals("Can't gain negative EXP",playerStats.addExperiencePoints(-100));
	}
	@Test
	public void test_NoLevelUp() {
		Player player = new Player(10,10);
		PlayerStatus playerStats = player.getPlayerStatus(); 
		assertEquals(null,playerStats.addExperiencePoints(1));
	}
	
	
	@Test 
	public void testPlayerLevelUp(){
		Player player = new Player (10,10);		
		PlayerStatus playerStats = player.getPlayerStatus(); 
		assertEquals("Player has leveled up",playerStats.addExperiencePoints(2000));
	}
	
	
	@Test
	public void testPlayerMovementMoveLeft()
	{
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
		BasicMap map = new BasicMap(testMap);
		Player p = new Player(4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, map);
		PlayerMovement movement = new PlayerMovement();
		p.setEntityArray(testArray);
		int [] expected = {3*BasicMap.TILESIZE, 5*BasicMap.TILESIZE};
		movement.moveLeft(p, map);
		assertArrayEquals(expected, p.getPosition());
		
	}
	

	@Test
	public void testPlayerMovementMoveRight()
	{
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
		BasicMap map = new BasicMap(testMap);
		Player p = new Player(4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, map);
		PlayerMovement movement = new PlayerMovement();
		p.setEntityArray(testArray);
		int [] expected = {5*BasicMap.TILESIZE, 5*BasicMap.TILESIZE};
		movement.moveRight(p, map);
		assertArrayEquals(expected, p.getPosition());
	}
	
	@Test
	public void testPlayerMoveDiagonalDownRight()
	{
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
		BasicMap map = new BasicMap(testMap);
		Player p = new Player(4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, map);
		PlayerMovement movement = new PlayerMovement();
		p.setEntityArray(testArray);
		int [] expected = {5*BasicMap.TILESIZE, 6*BasicMap.TILESIZE};
		movement.moveDiagonalDownRight(p, map);
		assertArrayEquals(expected, p.getPosition());
	}
	
	
	@Test
	public void testPlayerMoveDiagonalUpLeft()
	{
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
		BasicMap map = new BasicMap(testMap);
		Player p = new Player(4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, map);
		PlayerMovement movement = new PlayerMovement();
		p.setEntityArray(testArray);
		int [] expected = {3*BasicMap.TILESIZE, 4*BasicMap.TILESIZE};
		movement.moveDiagonalUpLeft(p, map);
		assertArrayEquals(expected, p.getPosition());
	}
	
	@Test
	public void testPlayerMoveDiagonalDownLeft()
	{
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
		BasicMap map = new BasicMap(testMap);
		Player p = new Player(4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, map);
		PlayerMovement movement = new PlayerMovement();
		p.setEntityArray(testArray);
		int [] expected = {3*BasicMap.TILESIZE, 6*BasicMap.TILESIZE};
		movement.moveDiagonalDownLeft(p, map);
		assertArrayEquals(expected, p.getPosition());
	}
	
	@Test
	public void testPlayerMoveUp()
	{
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
		BasicMap map = new BasicMap(testMap);
		Player p = new Player(4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, map);
		PlayerMovement movement = new PlayerMovement();
		p.setEntityArray(testArray);
		int [] expected = {4*BasicMap.TILESIZE, 4*BasicMap.TILESIZE};
		movement.moveUp(p, map);
		assertArrayEquals(expected, p.getPosition());
	}
	
	@Test
	public void testPlayerMoveDown()
	{
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
		BasicMap map = new BasicMap(testMap);
		Player p = new Player(4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, map);
		PlayerMovement movement = new PlayerMovement();
		p.setEntityArray(testArray);
		int [] expected = {4*BasicMap.TILESIZE, 6*BasicMap.TILESIZE};
		movement.moveDown(p, map);
		assertArrayEquals(expected, p.getPosition());
	}
	
	
	
		
}

