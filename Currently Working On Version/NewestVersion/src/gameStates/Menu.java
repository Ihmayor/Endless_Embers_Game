package gameStates;

import inputRelated.MainMenuButtons;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import managers.SoundManager;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {

	
	public static final int ID = 0; //Set the state's I.D 
	private MainMenuButtons menuButtons;
	
	
	
	private Animation menuAnimation;
	
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame)
			throws SlickException {
		menuButtons = new MainMenuButtons(gc,stateGame);
		JFrame test2 = new JFrame("test");
		JDialog test = new JDialog(test2,"test", true);
		test.isVisible();
		Image [] menuImages = {new Image ("res/interface/design2.png"), new Image ("res/interface/design2slide2.png")}; 
		int [] duration = {300,300};
		menuAnimation = new Animation (menuImages, duration, false);
		
		
		///Comment this back in if you want music.
		SoundManager.changeSound("res/sound/The Hero Of the Darkness.wav");
	    
		
	}
	
	

	@Override
	public void render(GameContainer gc, StateBasedGame stateGame, Graphics g)
			throws SlickException {
		gc.setShowFPS(false); //FPS counter is hidden		
		menuAnimation.draw(300,50);
	    menuButtons.render(gc,g);//Renders the menu buttons
	}

	
	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
			menuAnimation.update(delta);
	}
	
	
	@Override
	public int getID() {
		return ID;
	}

}
