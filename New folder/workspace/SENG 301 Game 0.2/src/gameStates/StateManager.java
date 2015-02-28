package gameStates;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StateManager extends StateBasedGame {

	
	public static void main (String [] args){
		
		//All the code below is how we get this game rolling and running.
		
		int screenWidth = 1120; // Set screen width to 35 tiles long (35*32px)
		int screenHeight = 512; // Set screen height to 16 tile long (16*32px)
			
			try
			{
				AppGameContainer appgc;
				appgc = new AppGameContainer(new StateManager("Basic Game Template"),screenWidth,screenHeight,false);
				appgc.start();
			}
			catch (SlickException ex)
			{
				Logger.getLogger(StateManager.class.getName()).log(Level.SEVERE, null, ex);
			}
	
	}
	public StateManager(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new Menu());//Very very important. Basically initializes the list of states in this game.
		addState(new Game());
		addState(new LoadGame());
	}

}
