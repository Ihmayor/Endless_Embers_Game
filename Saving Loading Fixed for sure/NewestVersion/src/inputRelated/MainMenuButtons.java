package inputRelated;

import java.io.File;

import gameStates.GameScreen;
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
	
	File f = new File("save.txt");
	
	if(f.exists() && f.isFile())
		{
		savedGameExists = true;
		}
	

	//Get the images of the buttons in their inactive/active states
	Image activeNewGame = new Image("res/interface/newGame.png");
	Image mouseOverNewGame = new Image("res/interface/newGameMouseOver.png");
	Image inactiveLoad = new Image ("res/interface/continueInActive.png");
	Image activeLoad = new Image("res/interface/continueActive.png");
	
	
	newGameButton = new BasicButton(gc, 330, 350, 
									    stateGame, MainMenuScreen.ID, activeNewGame, activeNewGame);
	loadGameButton = new BasicButton(gc, 540, 350, 
										stateGame, MainMenuScreen.ID, inactiveLoad, activeLoad);
	
	newGameButton.setMouseDownImage(mouseOverNewGame);
	newGameButton.setMouseOverImage(mouseOverNewGame);
	
	if (!savedGameExists){//Given that a saved game does not exist deactivate this button
		loadGameButton.setUnClickable(true);	
		loadGameButton.setMouseOverImage(inactiveLoad);
		loadGameButton.setMouseOverColor(Color.white);
		}
	else
	{
		loadGameButton.setUnClickable(false);
		loadGameButton.setMouseOverImage(new Image("res/interface/continueMouseOver.png"));
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
		if (savedGameExists){
			GameScreen.setLoadedGame(true);
			sbg.enterState(GameScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));	
			}
		}
		});
	}	
	
	//Draw buttons on main menu screen
	public void render(GameContainer gc, Graphics g){
		 newGameButton.render(gc, g);//Draw the buttons on screen.
		 loadGameButton.render(gc,g);
	}
	
	
}
