package gameStates;

import inputRelated.MainMenuButtons;

import managers.SoundManager;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

//////
//Menu
//Purpose: Does this class load the main menu (when the game is first started) or the in-game menu screen? (accessed while game is in progress)
//Limit: ?
//////
public class Menu extends BasicGameState {

	
	public static final int ID = 0; //Set the state's I.D 
	private MainMenuButtons menuButtons;
	
	
	
	private Animation menuAnimation;
	
	// Initializes the menu
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame)
			throws SlickException {
		menuButtons = new MainMenuButtons(gc,stateGame);
		Image [] menuImages = {new Image ("res/interface/design2.png"), new Image ("res/interface/design2slide2.png")}; 
		int [] duration = {300,300};
		menuAnimation = new Animation (menuImages, duration, false);
		
		
		///Comment this back in if you want music.
		SoundManager.changeSound("res/sound/Flying In The Face Of Hope.wav");
	    
		
	}
	
	
	// Draws the menu screen
	@Override
	public void render(GameContainer gc, StateBasedGame stateGame, Graphics g)
			throws SlickException {
		gc.setShowFPS(false); //FPS counter is hidden		
		menuAnimation.draw(300,50);
	    menuButtons.render(gc,g);//Renders the menu buttons
	}

	// Updates the menu state (as buttons are clicked, menus are accessed, etc)
	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
			menuAnimation.update(delta);
	}
	
	// ?
	@Override
	public int getID() {
		return ID;
	}

}
