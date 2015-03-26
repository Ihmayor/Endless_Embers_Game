package gameStates;

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
	
	private GameContainer gc;
	@Override
	public void enter (GameContainer gc, StateBasedGame sbg){
		SoundManager.changeSound("res/sound/Back To Programming.wav");
		GameScreen.setWin(false);//Reset win boolean for future new games
		//For testing purposes only will delete later
		try{
			// File (or directory) with old name
			File file = new File("save.txt");
			if (!file.exists())
				return;
			// File (or directory) with new name
			Random gen = new Random();
			int gameTxtID = gen.nextInt(1000); 
			File file2 = new File("wonGame.txt"+gameTxtID);
			if(file2.exists()) throw new java.io.IOException("file exists");

			// Rename file (or directory)
			boolean success = file.renameTo(file2);
			if (!success) {
				System.out.println("error renaming!");
				}
			}
		
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
	    
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
			this.gc = gc;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.drawString("YOU WON!!!", 480,200);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
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
