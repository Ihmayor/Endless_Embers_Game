package MonsterRelated;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreatureTest {

	@Test
	public void testSearch() {
		Creature c = new Creature();
		c.x = 4;
		c.y = 4;
		String [][] test = new String[32][16];
		test[3][4] = "M";
		
		c.setEntityArray(test);
		assertEquals(false, c.search("P"));
		assertEquals(true, c.search("M"));
	}
	
	
	
	@Test
	public void testSetPosition(){
		
		fail("Not yet Implemented");
	}
	
	@Test
	public void isTaken(){
		
		fail("Not Yet Implemented");
	}
	
	
	
	/*Proper Implementation of Entity Array
	 * 
	 * - Check 2-d array is as it seems
	 * - Check search method
	 * - 
	 * 
	 * 
	 * 
	 */

}
