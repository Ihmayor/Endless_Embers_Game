package inputRelated;

import gameStates.GameScreen;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class EndGameButtons {

	//Button Variables
	private BasicButton quitGameButton;
	private BasicButton newGameButton;
	
	
	//Used to for the actions of the buttons
	private StateBasedGame sbg;
	private GameContainer gc;
	
	
	public EndGameButtons (GameContainer container, StateBasedGame stateGame, int stateID) throws SlickException{
		
		sbg = stateGame;
		this.gc = container;
		
		
		//Get the images of the buttons in their inactive/active states
		Image activeNewGame = new Image("res/interface/newGameActive.png");
		Image mouseOverNewGame = new Image("res/interface/newGameMouseOver.png");
		Image activeQuitGame = new Image ("res/interface/exitGameActive.png");
		Image mouseOverQuitGame = new Image("res/interface/exitGameMouseOver.png");
		
		newGameButton = new BasicButton(gc, 330, 350, 
			    stateGame, stateID, activeNewGame, activeNewGame);
		quitGameButton = new BasicButton(gc, 540, 350, 
				stateGame,  stateID, activeQuitGame, activeQuitGame);
		
		newGameButton.setMouseOverImage(mouseOverNewGame);
		 
		quitGameButton.setMouseDownImage(mouseOverQuitGame);
		quitGameButton.setMouseOverImage(mouseOverQuitGame);
		
		//Creates an anon class for the button's action.
		newGameButton.add(new ButtonAction(){ 
			public void perform(){
			sbg.enterState(GameScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));	
			}
			});
		
		//Creates an anon class for the button's action.
		quitGameButton.add(new ButtonAction(){ 
			public void perform(){
				//Delete game/Over write old game here
				((GameContainer)gc).exit();	
			}
		});
		
		}
	
	
	
	public void render(GameContainer gc, Graphics g){
		 newGameButton.render(gc, g);//Draw the buttons on screen.
		 quitGameButton.render(gc,g);
	}
	
}
