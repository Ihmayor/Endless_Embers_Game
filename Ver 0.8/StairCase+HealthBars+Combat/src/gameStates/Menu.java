package gameStates;

import fancyThings.SoundInGame;
import inputRelated.MainMenuButtons;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class Menu extends BasicGameState {

	
	public static final int ID = 0; //Set the state's I.D 
	private MainMenuButtons menuButtons;
	
	private TrueTypeFont font;
	private SoundInGame sound;

	
	private Animation menuAnimation;
	private Image menuImage;
	
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame)
			throws SlickException {
		menuButtons = new MainMenuButtons(gc,stateGame);	
		
		Image [] menuImages = {new Image ("res/interface/test2.png"), new Image ("res/interface/test3.png"), new Image ("res/interface/test1.png"), new Image ("res/interface/test3.png")}; 
		int [] duration = {300, 150,100,100};
		menuAnimation = new Animation (menuImages, duration, false);
		
		menuImage = new Image ("res/interface/design2.png");
		
		///Comment this back in if you want music.
		//sound = new SoundInGame("res/sound/The Hero Of the Darkness.wav");
	    
		
		
	    // load font from a .ttf file
	    try {
	        InputStream inputStream = ResourceLoader.getResourceAsStream("res/interface/Sketch Gothic School.ttf");
	         
	        Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
	        awtFont = awtFont.deriveFont(50f); // set font size
	        font = new TrueTypeFont(awtFont, false);
	             
	    } catch (Exception e) {
	        e.printStackTrace();
	    }  
	}
	
	

	@Override
	public void render(GameContainer gc, StateBasedGame stateGame, Graphics g)
			throws SlickException {
		gc.setShowFPS(false); //Annoying FPS counter is hidden
		
		g.setColor(Color.white); //Sets strings to draw in the color white
		menuImage.draw(300,50);
		menuAnimation.draw(300,50);
//		font.drawString(400, 100, "Endless Embers", Color.white);

//	    g.drawString("Menu Screen", 440, 110); // Draws string to screen.
	    menuButtons.render(gc,g);//Renders the menu buttons
	}

	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
			//In game loops this is how it changes what's seen on screen. 
			//Say A character is supposed to move 5 pixels left because of 
			//Player input. Well this is where we would put that stuff.
			menuAnimation.update(delta);
	}
	
	@Override
	public int getID() {
		return ID;//ALSO very important. This is how the stateManager knows what we set the ID's of our states.
	}

}
