package gameStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/////////////////////////////////////////////////////////////////////////////////////////////////////
//StateManager																					   //
//Purpose: This class manages the flow of the game through its states							   //
//Limit: Works only with SLICK2D API    														   //
/////////////////////////////////////////////////////////////////////////////////////////////////////

public class StateManager extends StateBasedGame {

		public StateManager(String name) {
		super(name);
	}
		
	// A list of states instantiated to be used by the game
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new MainMenuScreen());
		addState(new TransitionScreen());
		addState(new GameScreen());
		addState(new GameOverScreen());
		addState(new WinScreen());
	}

}
