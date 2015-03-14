package inputRelated;

import gameStates.Game;
import gameStates.LoadGame;
import gameStates.Menu;
import managers.SoundManager;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class PopUpButtons {

	//Initialize pop-up menu buttons
	private AnimatedButton resumeButton;
	private AnimatedButton saveButton;
	private AnimatedButton quitButton;
	
	private Animation animationButton;
	
	private StateBasedGame sbg;
	
	public PopUpButtons (GameContainer gc, StateBasedGame stateGame) throws SlickException{
		
	sbg = stateGame;
	
	//Array instantiated to satisfy animated button class.
	Image []  buttonImages = {new Image("res/interface/Sprial1.png")};
	//Duration of animation
	int [] duration = {50};
	animationButton = new Animation (buttonImages, duration,false);

	//Get the images of the buttons in their inactive/active states
	Image inactiveResume = new Image("PLACEHOLDER");
	Image activeResume = new Image("PLACEHOLDER");
	Image inactiveSave = new Image ("PLACEHOLDER");
	Image activeSave = new Image("PLACEHOLDER");
	Image activeQuit = new Image("PLACEHOLDER");
	Image inactiveQuit = new Image("PLACEHOLDER");
	
	resumeButton = new AnimatedButton(gc, animationButton, 390, 350, stateGame, Menu.ID, inactiveResume, activeResume);
	saveButton = new AnimatedButton(gc, animationButton, 590, 350, stateGame, Menu.ID, inactiveSave, activeSave);
	quitButton = new AnimatedButton(gc, animationButton, 590, 350, stateGame, Menu.ID, inactiveQuit, activeQuit);

	//Creates an anon class for the button's action.
	resumeButton.add(new ButtonAction(){ 
		public void perform(){
				
		}
		});
	
	
	//Creates an anon class for the button's action.
	saveButton.add(new ButtonAction(){ 
		public void perform(){
				
		}
		});
	quitButton.add(new ButtonAction(){
		public void perform(){
			
		}
		});
	}
	
	public void render(GameContainer gc, Graphics g){
		 resumeButton.render(gc, g);//Draw the buttons on screen.
		 saveButton.render(gc,g);
		 quitButton.render(gc,g);
	}
}
