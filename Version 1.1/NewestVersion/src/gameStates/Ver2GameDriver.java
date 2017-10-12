package gameStates;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

//Driver Used to run the game
//Contains Main Method
public class Ver2GameDriver {	

	public static void main (String [] args){
		
		//All the code below is how we get this game rolling and running.
		
		final int screenWidth = GameWindowSettings.getScreenWidth(); // Set screen width to 35 tiles long (35*32px)
		final int screenHeight = GameWindowSettings.getScreenHeight(); // Set screen height to 16 tile long (16*32px)
		
			try
			{
				AppGameContainer appgc;
				appgc = new AppGameContainer(new StateManager("Endless Embers"),screenWidth,screenHeight,false);
				appgc.start();
			}
			catch (SlickException ex)
			{
				Logger.getLogger(StateManager.class.getName()).log(Level.SEVERE, null, ex);
			}
	}
}
