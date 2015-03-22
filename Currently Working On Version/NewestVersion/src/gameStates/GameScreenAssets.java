package gameStates;

import inputRelated.ButtonAction;
import inputRelated.SlideOutMenu;// WILL USE!!! 

import java.util.LinkedList;

import managers.SoundManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import playerRelated.Player;

////////////////////////////////////////////////////
//GameScreenAssets								  //
//Purpose: Controls the various elements in the UI//
//Limit: Textlog has unified source of updates	  //
////////////////////////////////////////////////////

public class GameScreenAssets {

	//Floor Variables
	private int floorLevel = 1;
	
	//Menu object
	SlideOutMenu menu;
	GameContainer gc;
	//TextLog Variables
	public static String statusBackLog2 = " ";
	public static String statusBackLog1 = " ";
	public static String statusUpdate;
	
	//Linked lists for keeping track of the game's state.
	public static LinkedList <String> queueTextLog = new LinkedList<String>();
	
	public void increaseFloorLevel(){ floorLevel++;}

	public void initMenu(GameContainer gc, StateBasedGame stateGame, int ID) throws SlickException{
		  this.gc = gc;
	      menu = new SlideOutMenu(gc, stateGame, ID, new Image ("res/interface/menu2.png"), 1065, 50 );
	        menu.add(new ButtonAction(){ 
	    		public void perform(){
	    		//SoundManager.changeSound("res/sound/Play At Your Own Risk.wav");//I warned you. Not even sorry.	
	    		SoundManager.playSoundEffect("res/sound/SFX/Sword Swing.wav");
	    		}
	    		});
	}
	
	public SlideOutMenu getMenu(){return menu;}
	
	// Draws the UI elements
	public void render(Graphics g, Player player){
		
		//Render Text Log + Floor Status
		g.setColor(Color.white);
		g.drawString("Floor: "+floorLevel, 1000, 20);
	    g.drawString(statusUpdate, 600, 490);
	    g.drawString(statusBackLog1, 600, 470);
	    g.drawString(statusBackLog2, 600, 450);

	    //Draw Player Level
	    g.setColor(Color.yellow);
	    g.drawString("Lvl: "+player.getCurrentLevel(),10, 450);
	    
	    g.setColor(Color.white);
	    ///Draw Health Bar
	    g.drawString("HP", 80, 450);
	    g.drawString(""+player.getHealthPoints()+"/"+player.getMaxHealthPoints(), 420, 450);
	    Rectangle healthBar = new Rectangle(110, 450, 300 * player.getHealthPoints() / player.getMaxHealthPoints(), 20);
        GradientFill fillRed = new GradientFill(110, 0, new Color(255, 0, 0),
                                             460 + 300, 0, new Color(220,60, 0));

        g.setColor(Color.darkGray);
        g.fillRect(110, 450, 300, 20);
        g.fill(healthBar, fillRed); 
        
        
        //Draw Experience Bar
        g.setColor(Color.white);
	    g.drawString("EXP", 80, 480);
	    g.drawString(""+player.getExperiencePoints()+"/"+player.getPointsNextLevel(), 420, 480);
	    Rectangle expBar = new Rectangle(110, 480, 300*player.getExperiencePoints()/player.getPointsNextLevel(), 20);
        GradientFill fillGreen = new GradientFill(110, 0, new Color(90, 255, 20),
                                             480 + 300, 0, new Color(40, 180, 40));
        g.setColor(Color.darkGray);
        g.fillRect(110, 480, 300, 20);
        g.fill(expBar, fillGreen); 
        
        //Render menu
        menu.render(gc, g);
		
	}
	
	
	public void clearTextLog(){
		while (!queueTextLog.isEmpty())
			queueTextLog.pop();
	}
	
	// Scrolls the text log to display more recent information
	public int updateTextLog(int textLogCounter){
		if (textLogCounter < 0)
			return 0;
		
		if (textLogCounter > 200){
			String temp = queueTextLog.pollLast();
			if (temp!= null){
				statusBackLog2 = statusBackLog1;
				statusBackLog1 = statusUpdate;
				statusUpdate = ""+temp;
			}	
			return 0;
		}
		else
			return ++textLogCounter;
	}
	
	
}
