package inputRelated;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

public class SlideOutMenu extends MouseOverArea{

	
	private StateBasedGame sbg;
	private final List <ButtonAction> actions = new ArrayList <ButtonAction>();
 	private boolean activated = false;
 	private boolean lastMouseOver = false;
	private int stateID;
	private boolean menuOpen = false;
 	
	public SlideOutMenu(GUIContext container,StateBasedGame game, int stateID,  Image image, int x, int y) {
		super(container, image, x, y);
		sbg = game;
		this.stateID = stateID;
	    super.setMouseOverColor(Color.yellow);
	  	 
	}

	   public void render(GameContainer gc, Graphics g) {
	          super.render(gc, g);
	   }
	
	
	   public boolean isActivated() {
	        return activated;
	    }
	 
	public void add(ButtonAction action){
		actions.add(action);
	}
	
	@Override
    public void mousePressed(int button, int x, int y) {
        if (isMouseOver() && sbg.getCurrentStateID() == stateID) {
            activated = !activated;
            if (menuOpen == false){
            	setX(500);
            	menuOpen = true;
            //	actions.get(0).perform();
            }
            else
            	{
            	setX(1110);
            	menuOpen = false;
            	//actions.get(1).perform();
            	}
        	}
    }
    
	
	
    
	 @Override
	    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
	        if (sbg.getCurrentStateID() == stateID) {
	            if (isMouseOver() && !lastMouseOver && isActivated()) {
	                lastMouseOver = true;
	            } else if (!isMouseOver()) {
	                lastMouseOver = false;
	            }
	        }
	        super.mouseMoved(oldx, oldy, newx, newy);
	    }
	 

	
}
