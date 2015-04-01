package gameStates;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState{
	private TrueTypeFont font;
	public static int ID = 3; 
	private GameContainer gc;
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
	    // load a default java font
		this.gc = gc;
	    Font awtFont = new Font("Times New Roman", Font.BOLD, 50);
	    font = new TrueTypeFont(awtFont, false);		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		//Draw Skull
		font.drawString(300, 50, "GAME OVER!", Color.white);
		g.setColor(Color.white);
		g.drawString("Press 'Q' to quit",300,100);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
	}
	@Override
	public void keyReleased (int key,char c){
	switch (key){
	case Input.KEY_Q:
		gc.exit();//Exits game. 	
		break;
		}	
	}

	@Override
	public int getID() {
		return ID;
	}

}
