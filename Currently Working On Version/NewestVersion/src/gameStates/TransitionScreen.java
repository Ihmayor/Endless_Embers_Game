package gameStates;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class TransitionScreen extends BasicGameState {
	public static final int ID = 4;
	
	private GameContainer gc;
	private StateBasedGame sbg;
	private Animation controlInstructions;
	private Image controlImage;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.gc = gc;
		this.sbg = sbg;
		// TODO Auto-generated method stub
		controlImage = new Image("res/interface/controls.png"); 
		
	}
	

	@Override
	public void render(GameContainer arg0, StateBasedGame sbg, Graphics g)
			throws SlickException {
		controlImage.draw(0,0);
	}
	
	@Override
	public void keyReleased (int key,char c){
		switch (key){
		case Input.KEY_Q:
			gc.exit();//Exits game. 	
			break;
			
		default:
			sbg.enterState(Game.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));	
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		//controlInstructions.update(delta);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	
	
}
