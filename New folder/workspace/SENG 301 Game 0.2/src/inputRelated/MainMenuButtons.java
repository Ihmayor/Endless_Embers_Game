package inputRelated;

import gameStates.Game;
import gameStates.LoadGame;
import gameStates.Menu;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenuButtons {
	
	private AnimatedButton loadGameButton;
	private AnimatedButton newGameButton;
	private Animation animationButton;
	private StateBasedGame game;
	private boolean savedGameExists = false;
	
	public MainMenuButtons (GameContainer gc, StateBasedGame stateGame) throws SlickException{
	
	game = stateGame;
	Image []  buttonImages = {new Image("res/interface/Sprial1.png"), new Image("res/interface/Sprial1.png"), new Image ("res/interface/Sprial1.png")}; 
	int [] duration = {50,50,50};
	animationButton = new Animation (buttonImages, duration,false);

	Image inactiveNewGame = new Image("res/interface/newGameButton1.png");
	Image activeNewGame = new Image("res/interface/newGameButton2.png");
	
	Image inactiveLoad = new Image ("res/interface/loadGame.png");
	Image activeLoad = new Image("res/interface/loadGame2.png");
	
	newGameButton = new AnimatedButton(gc, animationButton, 440, 200, stateGame, Menu.ID, inactiveNewGame, activeNewGame);
	loadGameButton = new AnimatedButton(gc, animationButton, 440, 300, stateGame, Menu.ID, inactiveLoad, activeLoad);
	if (!savedGameExists)
		{
		loadGameButton.setMouseOverColor(Color.white);
		}
	newGameButton.add(new ButtonAction(){ 
		public void perform(){
		game.enterState(Game.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));		
		}
		});
	
	loadGameButton.add(new ButtonAction(){ 
		public void perform(){
		if (savedGameExists)
			game.enterState(LoadGame.ID, new FadeOutTransition(Color.white), new FadeInTransition(Color.white) );	
		}
		});
		
	}
	
	public void setLoadGameState(boolean savedGameExists){
		
	}
	
	public void render(GameContainer gc, Graphics g){
		 newGameButton.render(gc, g);
		 loadGameButton.render(gc,g);
		
	}
}