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
		
		int screenWidth = 1120; // Set screen width to 35 tiles long (35*32px)
		int screenHeight = 512; // Set screen height to 16 tile long (16*32px)
		
			try
			{
				AppGameContainer appgc;
				appgc = new AppGameContainer(new StateManager("Basic Game Template"),screenWidth,screenHeight,false);
				appgc.start();
			}
			catch (SlickException ex)
			{
				Logger.getLogger(StateManager.class.getName()).log(Level.SEVERE, null, ex);
			}
	}
}
