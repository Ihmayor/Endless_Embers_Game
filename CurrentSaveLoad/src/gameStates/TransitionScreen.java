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
	//State's ID
	public static final int ID = 4;
	
	//Used to enter other states
	private StateBasedGame sbg;
	
	//Different Images and Variables Used For Intro
	private Image controlImage;
	private Image explanationImage;
	private Animation animationIntro;
	private Image panicImage;
	private int slide = 0;
	
	
	//Initializes Transition Screen for Intro
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.sbg = sbg;
		Image [] animationImages = {new Image ("res/player/intro1.png"),
									new Image ("res/player/intro2.png"),
									new Image ("res/player/intro3.png"),
									new Image ("res/player/intro4.png"),
									new Image ("res/player/intro5.png"),
									new Image ("res/player/intro6.png"),
									new Image ("res/player/intro7.png"),
									new Image ("res/player/intro8.png"),
									new Image ("res/player/intro9.png"),
									new Image ("res/player/intro10.png"),
									new Image ("res/player/intro11.png")};
		int [] duration = {100,100,100,100,100,100,100,100,100,100,100000};
		animationIntro = new Animation (animationImages, duration, false);
		controlImage = new Image("res/interface/controls.png"); 
		panicImage = new Image("res/interface/panicSlide.png");
		explanationImage = new Image("res/interface/explanation.png");
	}
	

	//Draws Transition Screen based on Player Input
	@Override
	public void render(GameContainer arg0, StateBasedGame sbg, Graphics g)
			throws SlickException {
		animationIntro.draw(0,0);
		if (slide == 1)
			panicImage.draw(0,0);
		if (slide == 2)
			explanationImage.draw(0,0);
		if (slide >=3)
			controlImage.draw(0,0);
	}
	
	//Controls Keyboard Input
	@Override
	public void keyReleased (int key,char c){
		switch (key){		
		
		case Input.KEY_W:
			sbg.enterState(GameScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));	
			break;
		default:
			if (slide >=4)
				sbg.enterState(GameScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));	
			else
				slide++;
		}
	}

	
	//Updates Animation on Transition Screen
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		animationIntro.update(delta);
	}

	
	//Returns ID of state for state manager that controls
	//Flow of game
	@Override
	public int getID() {
		return ID;
	}

}
