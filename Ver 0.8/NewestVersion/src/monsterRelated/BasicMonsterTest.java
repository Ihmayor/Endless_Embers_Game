package monsterRelated;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import mapRelated.BasicMap;

import org.junit.Test;
import org.newdawn.slick.SlickException;

public class BasicMonsterTest {
	
	
	@Test
	public void testGenericMove() throws SlickException{
		BasicMap map = new BasicMap();
		
		BasicMonster b = new BasicMonster (map, 4*32, 5*32, 0);
		String [][] test = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		for (int i = 0; i < BasicMap.widthByTiles; i++){
			for (int c = 0; c < BasicMap.heightByTiles; c++){
				test [i][c] = " ";
				}
			}
		test[5][4] = "P";
		
		b.setEntityArray(test);
		int [] testPosition = {5*32,4*32};//Not in the way player
		
		int [] monsterPos = {5*32, 5*32};//Monster's supposed current position
			
		assertEquals(false, b.isTaken(5,5));
		b.update(testPosition,4000);//Move over by one.
		
		assertArrayEquals(monsterPos, b.getPosition());
		
		testPosition[0] = 5*32;
		testPosition[1] = 5*32;
		test[5][4] = " ";
		test[6][5] = "P";

		assertEquals(true, b.isTaken(6*32,5*32));
		b.update(testPosition,4000);//Do not move
		
		assertArrayEquals(monsterPos, b.getPosition());
		
		testPosition[0] = 6*32;
		testPosition[1] = 6*32;
		test[6][5] = " ";
		test[6][6] = "P";

		monsterPos[0] = 6*32;
		
		assertEquals(false, b.isTaken(6*32,5*32));
		b.update(testPosition,4000);
		
		assertArrayEquals(monsterPos, b.getPosition());

		
	}
	
	
	
	@Test
	public void testExpPointGain() throws SlickException{
		BasicMap map = new BasicMap();
		
		BasicMonster m = new BasicMonster (map, 10,10,100);
		assertEquals(1000, m.getMaxHealthPoints());
		assertEquals(500, m.getExpPointGain());
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////TESTS ABILITY TO SELECT A VALID SPOT WHEN NOT BLOCKED BY ANYTHING//////////////////////////////////
	@Test
	public void testClosestSpotNotNear() throws SlickException{
		BasicMap map = new BasicMap();
		
		BasicMonster b = new BasicMonster(map, 4*32, 5*32, 0);
		int [] expected = {7*32,9*32};
		int [] test = b.getPosition();
		
		assertArrayEquals(expected, test);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////TESTS ABILITY TO NOT OVERLAP OBSTACLES///////////////////////////////////////////////////////////////////	
	@Test
	public void testClosestSpotNear() throws SlickException{
		
		fail("Not yet implemented");
	}
	
	public void testClosestSpotObstacle()throws SlickException{
			
			fail("Not yet implemented");
	}
		
//////////////////////////////////////////////////////////////////////////////////////////////////	
///////////////////TESTS FOR BOUNDARIES IN CLOSEST SPOT//////////////////////////////////////////
	@Test
	public void testClosestSpotWallLeft()throws SlickException{
		
		fail("Not yet implemented");
	}
	
		
	
	@Test
	public void testClosestSpotWallRight()throws SlickException{
		
		fail("Not yet implemented");
	}
	
	public void testClosestSpotWallUp()throws SlickException{
		
		fail("Not yet implemented");
	}
	
	public void testClosestSpotWallDown()throws SlickException{
		
		fail("Not yet implemented");
	}
	
	
	/*Things Needed to Be tested For:
	 * 
	 * 
	 * Need to check proper sight method works
	 * 
	 * Need to check for loop in Move to Closest Spot
	 * 
	 * 
	 * Check Render
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	

}
