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

public class BasicButton extends MouseOverArea {


	//Source :https://evilzone.org/java/%28java-fames-tut%29-slick2d-buttons-buttons-buttons/
	 private boolean activated = false;
	 private boolean lastMouseOver = false;
	 private final Image inactiveButton;
	 private boolean unclickable = false;
	 private final Image activeButton;
     private final StateBasedGame sbg;
     private final int stateID;

     private final List <ButtonAction> actions = new ArrayList <ButtonAction>();
 	
     
     public BasicButton(GUIContext container, int x, int y,
			StateBasedGame sbg, int stateID, Image inactiveButton, Image activeButton) throws SlickException {
		 	super(container, inactiveButton, x, y);
	        super.setMouseDownColor(Color.white);
	        super.setMouseOverColor(Color.yellow);
	        super.setMouseOverImage(activeButton);
	        this.sbg = sbg;
	        this.stateID = stateID;
	 
	        this.inactiveButton = inactiveButton;
	        this.activeButton = activeButton;
	}
	public void add(ButtonAction action){
		actions.add(action);
	}
	
	public void setUnClickable(boolean unclickable){
			this.unclickable = unclickable;
	}
	
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
	 
	    @Override
	    public void render(GUIContext guic, Graphics g) {
	        if (activated&&!unclickable) {
	            g.drawImage(activeButton, getX(), getY());
	        } else {
	            g.drawImage(inactiveButton, getX(), getY());
	            super.render(guic, g);
	        }
	    }
	 
	    public boolean isActivated() {
	        return activated;
	    }
	 
	    protected void setActivated(boolean b) {
	        activated = b;
	    }
	    
	
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
	    public List <ButtonAction>getActions(){return actions;}
	 
}
	
	

