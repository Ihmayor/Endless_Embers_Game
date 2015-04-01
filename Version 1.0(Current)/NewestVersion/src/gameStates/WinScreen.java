package gameStates;

import inputRelated.EndGameButtons;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import managers.SoundManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class WinScreen extends BasicGameState{

	public static final int ID = 5;
	private EndGameButtons buttons;
	
	private GameContainer gc;
	@Override
	public void enter (GameContainer gc, StateBasedGame sbg){
		SoundManager.changeSound("res/sound/Back To Programming.wav");
		GameScreen.setWin(false);//Reset win boolean for future new games
		try{
			 
    		File file = new File("save.txt");
 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
    	}catch(Exception e){
 
    		e.printStackTrace();
    	}
		//Reset loadedGame/Win values
		GameScreen.setWin(false);
		GameScreen.setLoadedGame(false);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
			this.gc = gc;
		    buttons = new EndGameButtons(gc, sbg, WinScreen.ID);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.drawString("YOU WON!!!", 480,200);
		buttons.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
	}
	
	// Manages the keyboard controls of the game
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
