package gameStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LoadGame extends BasicGameState{

	private StateBasedGame game;
	public static final int ID = 2;
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame)
			throws SlickException {
		
		this.game = stateGame;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stateGame, Graphics g)
			throws SlickException {
		g.drawString("LOADING GAME...", 100, 50);
		g.drawString("Press 'Q' to quit", 100, 0);

	}

	public void keyReleased (int key, char c){
	switch (key){
	case Input.KEY_Q:
		GameContainer gc = game.getContainer();//Had to instantiate. I could've also made this another class variable.
		gc.exit();//Exits game. 
		break;
		}	
	}


	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
		
		
		
	}

	@Override
	public int getID() {
		return ID;
	}

}
