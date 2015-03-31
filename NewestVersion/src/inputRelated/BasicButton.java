package inputRelated;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

//////////////////////////////////////////////////////////////////////////////////////////
//BASIC BUTTON TEMPLATE				      												//
//Purpose: Creates Button Template        												//			
//Limits: Relies on SLICK2D API         C  												//
//Author: Deque							  												//
//Source :https://evilzone.org/java/%28java-fames-tut%29-slick2d-buttons-buttons-buttons//
//////////////////////////////////////////////////////////////////////////////////////////

public class BasicButton extends MouseOverArea {

	 //Variables for Button Functionality
	 private boolean activated = false;
	 private boolean unclickable = false;
	 private boolean lastMouseOver = false;

	 //Variables for Image of Button
	 private Image inactiveButton;
	 private Image activeButton;
	 
	 //Variables to reference gameScreen where its used
     private final StateBasedGame sbg;
     private final int stateID;

     //Used for holding button press actions
     private final List <ButtonAction> actions = new ArrayList <ButtonAction>();
 	
     //Initializes Button
     public BasicButton(GUIContext container, int x, int y,
			StateBasedGame sbg, int stateID, Image inactiveButton, Image activeButton) throws SlickException {
		 	super(container, activeButton, x, y);
	        super.setMouseDownColor(Color.white);
	        super.setMouseOverColor(Color.yellow);
	        super.setMouseOverImage(activeButton);
	        this.sbg = sbg;
	        this.stateID = stateID;
	 
	        this.inactiveButton = inactiveButton;
	        this.activeButton = activeButton;
	}
     
    //Method to add actions to a button to perform
	public void add(ButtonAction action){
		actions.add(action);
	}
	
	//Disables button
	public void setUnClickable(boolean unclickable){
			this.unclickable = unclickable;
	}
	
	
	//Method used to tell when the mouse is hovering over the button
	@Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
	        if (sbg.getCurrentStateID() == stateID) {
	            if (isMouseOver() && !lastMouseOver && !isActivated()) {
	                lastMouseOver = true;
	            } else if (!isMouseOver()) {
	                lastMouseOver = false;
	            }
	        }
	        super.mouseMoved(oldx, oldy, newx, newy);
	    }

	
	//Renders button image depending on its activation
	@Override
    public void render(GUIContext guic, Graphics g) {
	        if (isActivated()&&!unclickable) {
	            g.drawImage(activeButton, getX(), getY());
	            activated = false;
	        }
	        super.render(guic, g);
		    if (unclickable)
		    {
		    	g.drawImage(inactiveButton, getX(), getY());
		    }
	    }
	 
	//Checks if button is activated
	public boolean isActivated() {return activated;}
	 
	//Sets the button activated or not.
    protected void setActivated(boolean b) {activated = b;}
	    
	    
	//Method that gets button to perform its action when pressed
	@Override
	public void mousePressed(int button, int x, int y) {
	    if (isMouseOver() && sbg.getCurrentStateID() == stateID) {
	         activated = !activated;
	         for (ButtonAction action: actions){
	          	action.perform();
	            }
	        }
	        super.mousePressed(button, x, y);
	    }
	
	//Gets the list of actions for the button
    public List <ButtonAction>getActions(){return actions;}
}
	
	

