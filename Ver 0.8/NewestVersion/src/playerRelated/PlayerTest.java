package playerRelated;

import static org.junit.Assert.fail;
import mapRelated.BasicMap;

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
	
}
