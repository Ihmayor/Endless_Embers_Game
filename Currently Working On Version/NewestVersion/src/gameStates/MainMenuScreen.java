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

//////////////////////////////////////////////////////////////////////
//Menu														        //
//Purpose: Loads the main menu screen when the game is first started//
//Limit: Current Continue button remains inactive			        //
//////////////////////////////////////////////////////////////////////

public class MainMenuScreen extends BasicGameState {

	
	public static final int ID = 0; //Set the state's I.D 
	
	//Variables used for UI elements
	private MainMenuButtons menuButtons;
	private Animation menuAnimation;
	
	
	// Initializes the menu
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame)
			throws SlickException {
		
		//Initializes menu buttons
		menuButtons = new MainMenuButtons(gc,stateGame);
	
		//Initializes Menu's Animation
		Image [] menuImages = {new Image ("res/interface/design2.png"), new Image ("res/interface/design2slide2.png")}; 
		int [] duration = {300,300};
		menuAnimation = new Animation (menuImages, duration, false);
		
		//Plays games music
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

	// Updates the menu's animation
	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
			menuAnimation.update(delta);
	}
	
	//Returns ID of state for state manager that controls
    //Flow of game
	@Override
	public int getID() {
		return ID;
	}

}
