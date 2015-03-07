package MonsterRelated;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import mapRelated.BasicMap;

import org.junit.Test;
import org.newdawn.slick.SlickException;

public class BasicMonsterTest {

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////TESTS ABILITY TO SELECT A VALID SPOT WHEN NOT BLOCKED BY ANYTHING//////////////////////////////////
	@Test
	public void testClosestSpotNotNear() throws SlickException{
		BasicMap map = new BasicMap("/res/map/secondTest.tmx");
		BasicMonster b = new BasicMonster(map, null, 4*32, 5*32);
		int [] expected = {7*32,9*32};
		int [] test = b.getPosition();
		
		assertArrayEquals(expected, test);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////TESTS ABILITY TO NOT OVERLAP OBSTACLES///////////////////////////////////////////////////////////////////	
	@Test
	public void testClosestSpotNear(){
		
		fail("Not yet implemented");
	}
	
	public void testClosestSpotObstacle(){
			
			fail("Not yet implemented");
	}
		
//////////////////////////////////////////////////////////////////////////////////////////////////	
///////////////////TESTS FOR BOUNDARIES IN CLOSEST SPOT//////////////////////////////////////////
	@Test
	public void testClosestSpotWallLeft(){
		
		fail("Not yet implemented");
	}
	
		
	
	@Test
	public void testClosestSpotWallRight(){
		
		fail("Not yet implemented");
	}
	
	public void testClosestSpotWallUp(){
		
		fail("Not yet implemented");
	}
	
	public void testClosestSpotWallDown(){
		
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
