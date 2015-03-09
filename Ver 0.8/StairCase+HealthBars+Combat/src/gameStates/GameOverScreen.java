package gameStates;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fancyThings.SoundInGame;

public class GameOverScreen extends BasicGameState{
	private TrueTypeFont font;
//	private SoundInGame sound;
	public static int ID = 3; 
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
	    // load a default java font
	    Font awtFont = new Font("Times New Roman", Font.BOLD, 50);
	    font = new TrueTypeFont(awtFont, false);
	    
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		//Draw Skull
		font.drawString(100, 50, "GAME OVER!", Color.white);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
