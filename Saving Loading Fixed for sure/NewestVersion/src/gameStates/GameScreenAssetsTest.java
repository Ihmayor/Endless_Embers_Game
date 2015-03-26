package gameStates;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameScreenAssetsTest {

	
	@Test
	public void testTextLogCounter(){
		GameScreenAssets game = new GameScreenAssets();
		
		assertEquals(1, game.updateTextLog(0));
	}
	
	@Test
	public void testTextLogUpdate(){
		GameScreenAssets game = new GameScreenAssets();
		assertEquals(0, game.updateTextLog(201));
		
	}
	
	@Test
	public void testTextLogInvalidCounter(){
		GameScreenAssets game = new GameScreenAssets();
		assertEquals(0, game.updateTextLog(-100));
		
	}
}
