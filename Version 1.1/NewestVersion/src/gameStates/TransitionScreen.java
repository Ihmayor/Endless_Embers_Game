package gameStates;

import managers.SoundManager;

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
	private Image controlImage2;
	private Animation explanationAnimation;
	private Animation animationIntro;
	private Image panicImage;
	private int slide = 0; //Used to keep track of which images/animations to draw to screen
	
	
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
									new Image ("res/player/intro11.png"),
									new Image ("res/player/intro12.png"),
									new Image ("res/player/intro13.png"),
									new Image ("res/player/intro14.png"),
									new Image ("res/player/intro15.png"),
									new Image ("res/player/intro16.png"),
									new Image ("res/player/intro17.png"),
									new Image ("res/player/intro18.png"),
									new Image ("res/player/intro19.png"),
									new Image ("res/player/intro20.png"),
									new Image ("res/player/intro21.png"),
									new Image ("res/player/intro22.png"),
									new Image ("res/player/intro23.png"),
									new Image ("res/player/intro24.png"),
									new Image ("res/player/intro25.png"),
									new Image ("res/player/intro26.png"),
									new Image ("res/player/intro27.png"),
									new Image ("res/player/intro28.png")};
		int [] introDuration = {300,300,300,300,300,300,300,300,300,300,
				           300,300,300,300,300,300,300,300,300,300,
				           300,300,300,300,300,300,300,10000};//Duration of milliseconds per every image
		animationIntro = new Animation (animationImages, introDuration, false);
		controlImage = new Image("res/interface/controls.png"); 
		controlImage2 = new Image("res/interface/controls2.png");
		panicImage = new Image("res/interface/panic.png");
		
		Image [] explanationImages = {new Image ("res/interface/ember1.png"),
								    new Image ("res/interface/ember2.png"),
								    new Image ("res/interface/ember1.png"),
								    new Image ("res/interface/ember2.png"),
								    new Image ("res/interface/ember1.png"),
								    new Image ("res/interface/ember2.png"),
								    new Image ("res/interface/ember3.png"),
								    new Image ("res/interface/ember4.png")};
		
		int [] explanationDuration = {300, 300,300,300,300,300,9980, 1000000};//Duration of milliseconds per every image
		explanationAnimation = new Animation (explanationImages,explanationDuration, false);
	}
	
	@Override
	public void enter (GameContainer gc, StateBasedGame sbg)
	{
			//Load State's music
			SoundManager.changeSound("res/sound/Extra Effort.wav");
		
	}

	//Draws Transition Screen based on Player Input
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.black);
		if (slide < 1)
			animationIntro.draw(0,0);
		else if (slide == 1)
			panicImage.draw(0,0);
		else if (slide == 2)
			explanationAnimation.draw(0,0);
		else if (slide == 3)
			controlImage.draw(0,0);
		else if (slide == 4)
			controlImage2.draw(0,0);		
		g.drawString("PRESS 'W' TO SKIP INTRO", 780, 470);
	}
	
	//Controls Keyboard Input
	@Override
	public void keyReleased (int key,char c){
		switch (key){		
		case Input.KEY_W:
			sbg.enterState(GameScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));	
			break;
		default:
			if (slide >=5)
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
		if (slide == 2)
			explanationAnimation.update(delta);
		
	}

	
	//Returns ID of state for state manager that controls
	//Flow of game
	@Override
	public int getID() {
		return ID;
	}

}
