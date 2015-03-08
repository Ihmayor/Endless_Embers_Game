package gameStates;

import java.awt.Font;
import java.io.InputStream;

import inputRelated.MainMenuButtons;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class Menu extends BasicGameState {

	
	public static final int ID = 0; //Set the state's I.D 
	private MainMenuButtons menuButtons;
	
	private TrueTypeFont font;
	private TrueTypeFont font2;
	
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame)
			throws SlickException {
		menuButtons = new MainMenuButtons(gc,stateGame);	
	
		Font awtFont = new Font("Times New Roman", Font.BOLD, 40);
	    font = new TrueTypeFont(awtFont, false);
	         
	    // load font from a .ttf file
	    try {
	        InputStream inputStream = ResourceLoader.getResourceAsStream("res/interface/Sketch Gothic School.ttf");
	         
	        Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
	        awtFont2 = awtFont2.deriveFont(50f); // set font size
	        font2 = new TrueTypeFont(awtFont2, false);
	             
	    } catch (Exception e) {
	        e.printStackTrace();
	    }  
	
		}
	
	

	@Override
	public void render(GameContainer gc, StateBasedGame stateGame, Graphics g)
			throws SlickException {
		gc.setShowFPS(false); //Annoying FPS counter is hidden
		
		g.setColor(Color.white); //Sets strings to draw in the color white
		font2.drawString(400, 100, "Endless Embers", Color.white);
//	    g.drawString("Menu Screen", 440, 110); // Draws string to screen.
	    menuButtons.render(gc,g);//Renders the menu buttons
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
			//In game loops this is how it changes what's seen on screen. 
			//Say A character is supposed to move 5 pixels left because of 
			//Player input. Well this is where we would put that stuff.
	}
	
	@Override
	public int getID() {
		return ID;//ALSO very important. This is how the stateManager knows what we set the ID's of our states.
	}

}
