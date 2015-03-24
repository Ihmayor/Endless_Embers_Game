package playerRelated;

import org.junit.Test;
import org.newdawn.slick.SlickException;

import static org.junit.Assert.*;

public class PlayerTest {


	
	@Test
	public void testExperiencePoints() throws SlickException{
		Player player = new Player (10, 10);
		player.addExperiencePoints(10);
		assertEquals(0, player.getExperiencePoints());
		assertEquals(20,player.getPointsNextLevel());
	}

	@Test
	public void testNegativeExperiencePoints() throws SlickException{
		//Arrange
		Player player = new Player (10,10);		
		assertEquals("Can't gain negative EXP",player.addExperiencePoints(-100));
	}
	
	@Test 
	public void testPlayerLevelUp() throws SlickException{
		Player player = new Player (10,10);		
		assertEquals("Player has leveled up",player.addExperiencePoints(2000));
	}
}
