package inputRelated;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

////////////////////////////////////////////////////////
//Slide Out Menu									  //
//Purpose: Controls Slide Out Menu/Associated Buttons //
//Limit: Does not smoothly pop out					  //
//Has not other buttons than exiting the game		  //
////////////////////////////////////////////////////////

public class SlideOutMenu extends MouseOverArea{

	//Used Changing States/Closing Game
	private StateBasedGame sbg;
	private GUIContext gc;
	
	//Sets menu's state
	private boolean activated = false;
	private boolean menuOpen = false;

	private BasicButton exitGameButton;
	
	//Button Variables used for Constructor
	private final List <ButtonAction> actions = new ArrayList <ButtonAction>();
	private int stateID;
	
	
	public SlideOutMenu(GUIContext container,StateBasedGame game, int stateID,  Image image, int x, int y) throws SlickException {
		super(container, image, x, y);
		sbg = game;
		gc = container;
		this.stateID = stateID;
	    super.setMouseOverColor(Color.yellow);
	    exitGameButton = new BasicButton(container, 1140, y+315, game, 
	    		stateID, new Image ("res/interface/saveExit.png"), new Image ("res/interface/saveExitMouseOver.png"));
	  	 
		exitGameButton.add(new ButtonAction(){
			public void perform(){
				((GameContainer)gc).exit();	
			}	
		});

	}

	   public void render(GameContainer gc, Graphics g) {
	          super.render(gc, g);
	          exitGameButton.render(gc,g);
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
            	popOut();
            }
            else{
            	popIn();
            }
        super.mousePressed(button, x, y);
       }
    }
    
	public boolean getMenuOpen(){return menuOpen;}
	
	public void popOut(){
		setX(300);
    	exitGameButton.setX(610);
    	menuOpen = true;
    	
	}
	
	public void popIn(){
		setX(1065);
    	exitGameButton.setX(1150);
    	menuOpen = false;
	}
	
}
