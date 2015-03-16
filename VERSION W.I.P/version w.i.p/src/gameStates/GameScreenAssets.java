package gameStates;

import inputRelated.SlideOutMenu;// WILL USE!!! 

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;//Will also use!!
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

import playerRelated.Player;

public class GameScreenAssets {

	//Floor Variables
	private int floorLevel = 1;
	
	//TextLog Variables
	public static String statusBackLog2 = " ";
	public static String statusBackLog1 = " ";
	public static String statusUpdate;
	
	//Linked lists for keeping track of the game's state.
	public static LinkedList <String> queueTextLog = new LinkedList<String>();
	
	public void increaseFloorLevel(){ floorLevel++;}
	
	public void render(Graphics g, Player player){
		
		//Render Text Log + Floor Status
		g.setColor(Color.white);
		g.drawString("Floor: "+floorLevel, 1000, 20);
	    g.drawString(statusUpdate, 600, 490);
	    g.drawString(statusBackLog1, 600, 470);
	    g.drawString(statusBackLog2, 600, 450);


	    ///Draw Health Bar
	    g.drawString("HP", 60, 450);
	    g.drawString(""+player.getHealthPoints()+"/"+player.getMaxHealthPoints(), 400, 450);
	    Rectangle healthBar = new Rectangle(90, 450, 300 * player.getHealthPoints() / player.getMaxHealthPoints(), 20);
        GradientFill fillRed = new GradientFill(90, 0, new Color(255, 0, 0),
                                             460 + 300, 0, new Color(220,60, 0));

        g.setColor(Color.darkGray);
        g.fillRect(90, 450, 300, 20);
        g.fill(healthBar, fillRed); 
        
        
        //Draw Experience Bar
        g.setColor(Color.white);
	    g.drawString("EXP", 60, 480);
	    g.drawString(""+player.getExperiencePoints()+"/"+player.getPointsNextLevel(), 400, 480);
	    Rectangle expBar = new Rectangle(90, 480, 300*player.getExperiencePoints()/player.getPointsNextLevel(), 20);
        GradientFill fillGreen = new GradientFill(90, 0, new Color(90, 255, 20),
                                             480 + 300, 0, new Color(40, 180, 40));
        g.setColor(Color.darkGray);
        g.fillRect(90, 480, 300, 20);
        g.fill(expBar, fillGreen); 
        
        
	}
	
	public int updateTextLog(int textLogCounter){
		if (textLogCounter < 0)
			return 0;
		
		if (textLogCounter > 200){
			String temp = GameScreenAssets.queueTextLog.pollLast();
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
