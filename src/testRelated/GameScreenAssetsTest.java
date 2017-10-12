package test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import gameStates.GameScreenAssets;

import org.junit.Test;

public class GameScreenAssetsTest {


	//Checks that text log counters works as expected
	@Test
	public void testTextLogCounter(){
		GameScreenAssets game = new GameScreenAssets();
		assertEquals(1, game.updateTextLog(0));
	}
	
	//Tests that text log counter resets
	@Test
	public void testTextLogUpdate(){
		GameScreenAssets game = new GameScreenAssets();
		assertEquals(0, game.updateTextLog(201));
		
	}
	
	//Tests that it rejects invalid counter numbers
	@Test
	public void testTextLogInvalidCounter(){
		GameScreenAssets game = new GameScreenAssets();
		assertEquals(0, game.updateTextLog(-100));
		
	}
}
