package playerRelated;

import org.junit.Test;
import org.newdawn.slick.SlickException;

import static org.junit.Assert.*;

public class PlayerTest {


	
	@Test
	public void testExperiencePoints() throws SlickException{
		Player player = new Player (10, 10);
		player.addExperiencePoints(1001);
		assertEquals(1, player.getExperiencePoints());
		assertEquals(2000,player.getPointsNextLevel());
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
		assertEquals(1000, player.getExperiencePoints());
		assertEquals(2000, player.getPointsNextLevel());
	}
}
