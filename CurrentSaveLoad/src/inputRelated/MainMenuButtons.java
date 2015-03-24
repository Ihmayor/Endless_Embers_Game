package inputRelated;

import gameStates.LoadGame;
import gameStates.MainMenuScreen;
import gameStates.TransitionScreen;


import managers.SoundManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenuButtons {
	
	//Button Variables
	private BasicButton loadGameButton;
	private BasicButton newGameButton;
	
	
	//Used to for the actions of the buttons
	private StateBasedGame sbg;
	private boolean savedGameExists = false;
	
	public MainMenuButtons (GameContainer gc, StateBasedGame stateGame) throws SlickException{
	
	sbg = stateGame;
	

	//Get the images of the buttons in their inactive/active states
	Image inactiveNewGame = new Image("res/interface/newGame.png");
	Image activeNewGame = new Image("res/interface/newGameMouseOver.png");
	Image inactiveLoad = new Image ("res/interface/continueInActive.png");
	Image activeLoad = new Image("res/interface/continueActive.png");
	
	
	newGameButton = new BasicButton(gc, 390, 350, 
									    stateGame, MainMenuScreen.ID, inactiveNewGame, activeNewGame);
	loadGameButton = new BasicButton(gc, 590, 350, 
										stateGame, MainMenuScreen.ID, inactiveLoad, activeLoad);
	
	newGameButton.setMouseDownImage(activeNewGame);
	newGameButton.setMouseOverImage(activeNewGame);
	
	if (!savedGameExists){//Given that a saved game does not exist deactivate this button
		loadGameButton.setUnClickable(true);	
		loadGameButton.setMouseOverImage(inactiveLoad);
		loadGameButton.setMouseOverColor(Color.white);
		}
	
	//Creates an anon class for the button's action.
	newGameButton.add(new ButtonAction(){ 
		public void perform(){
		sbg.enterState(TransitionScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));	
	//	SoundManager.changeSound("res/sound/Play At Your Own Risk.wav");//I warned you. Not even sorry.
		SoundManager.changeSound("res/sound/Catacombs.wav");
		}
		});
	
	
	//Creates an anon class for the button's action.
	loadGameButton.add(new ButtonAction(){ 
		public void perform(){
		if (savedGameExists)
			sbg.enterState(LoadGame.ID, new FadeOutTransition(Color.white), new FadeInTransition(Color.white) );	
		}
		});
	}
	
	public void setLoadGameState(boolean savedGameExists){
	//TO WORK ON METHOD: This is used to activate the load game button.	
	//And associated actions
	}
	
	
	public void render(GameContainer gc, Graphics g){
		 newGameButton.render(gc, g);//Draw the buttons on screen.
		 loadGameButton.render(gc,g);
	}
	
	
}
