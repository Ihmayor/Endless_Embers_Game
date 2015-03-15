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

public class SlideOutMenu extends MouseOverArea{

	
	private StateBasedGame sbg;
	private GUIContext gc;
	private final List <ButtonAction> actions = new ArrayList <ButtonAction>();
 	private boolean activated = false;
	private int stateID;
	private boolean menuOpen = false;
//	private BasicButton returnButton;
	private BasicButton exitGameButton;
 	
	public SlideOutMenu(GUIContext container,StateBasedGame game, int stateID,  Image image, int x, int y) throws SlickException {
		super(container, image, x, y);
		sbg = game;
		gc = container;
		this.stateID = stateID;
	    super.setMouseOverColor(Color.yellow);
//	    returnButton = new BasicButton(container, x+20, y+270, game, 
//	    		stateID, new Image ("res/interface/returnGame.png"), new Image ("res/interface/returnGameMouseOver.png"));
	    exitGameButton = new BasicButton(container, x+20, y+310, game, 
	    		stateID, new Image ("res/interface/exitGame.png"), new Image ("res/interface/exitGameMouseOver.png"));
	  	 
//		returnButton.add(new ButtonAction(){ 
//			public void perform(){
//				System.out.println("Pressed");

//			}
//			});
//		
		exitGameButton.add(new ButtonAction(){
			public void perform(){
				((GameContainer)gc).exit();	
			}	
		});

	}

	   public void render(GameContainer gc, Graphics g) {
	          super.render(gc, g);
//	          returnButton.render(gc,g);
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
            System.out.println("Test");
            if (menuOpen == false){
            	setX(300);
//            	returnButton.setX(500+60);
            	exitGameButton.setX(300+260);
            	menuOpen = true;
            }
            else{
            	setX(1110);
            	exitGameButton.setX(1110+40);
            	menuOpen = false;
            }
        super.mousePressed(button, x, y);
       }
    }
    
	public void setMenuOpenTrue(){menuOpen = true;}
	
}
