package gameStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StateManager extends StateBasedGame {

		public StateManager(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState( new Menu());//Very very important. Basically initializes the list of states in this game.
		addState(new Game());
		addState(new LoadGame());
		addState(new GameOver());
	}

}
