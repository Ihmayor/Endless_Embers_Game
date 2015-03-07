package playerRelated;

import mapRelated.BasicMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import MonsterRelated.Creature;

public class Player extends Creature{
	
	private final GameContainer gc;
	private long previousTime = 0;
	private Graphics g;
	private final StateBasedGame sbg;
	private BasicMap map;
	public final char name = 'P';
	
	//Basic Sprite Variables
		private SpriteSheet sheet;
		private Animation currentSprite, up, down,left,right;

		//Limited Vision Effect
		private Image shadow;		
	
	//Current Position of Player
		private int current_x = 4, current_y = 2;

	
	public Player(GameContainer gc, StateBasedGame sbg, BasicMap map) throws SlickException{
		this.gc = gc;
		this.sbg = sbg;
		this.map = map;
		super.x = current_x*32;
		super.x = current_y*32;
		sheet = new SpriteSheet("res/player/dummySheet.png", 32,32);
		shadow = new Image("res/player/evenLargerShadow.png");
		loadPlayerSprite(sheet);
		
	}
	
	private void loadPlayerSprite(SpriteSheet playerSheet){
		//Load Sprite Images for Player
				Image [] upSprite = {sheet.getSubImage(4,0),sheet.getSubImage(5, 0)};
				Image [] downSprite = {sheet.getSubImage(0,0), sheet.getSubImage(1,0)};
				Image [] rightSprite = {sheet.getSubImage(6,0), sheet.getSubImage(7,0)};
				Image [] leftSprite = {sheet.getSubImage(2,0),sheet.getSubImage(3,0)} ;
				

			//Set the duration of Animation in Milliseconds	
				int [] duration = {200,200};
				
			//Initialize Animations
				up = new Animation(upSprite, duration, false);
				down = new Animation (downSprite, duration, false);
				left = new Animation(leftSprite, duration, false);
				right = new Animation (rightSprite,duration,false);
				
				currentSprite = down;
	}
	
	public void render(Graphics g){
	this.g = g;
	currentSprite.draw((int) x, (int) y);//Draw what the Current sprite should look like.
	g.drawImage(shadow,(int)x-1110, (int)y-850); //Draw Shadow with a particular offset for the spotlight.
		
	}
	
	public void setMap(BasicMap map){
		this.map = map;//Just In case we're loading new floors.
	}	
	
	public boolean delayUpdate(long delta){
		return false;
	}
	
	public void update(long delta){
		int movementSpeed = 1;
		final int SIZE = 32;
		
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_UP)){
			currentSprite = up;
			
			if (y > 0 & (!(map.hasCollision(x, y - movementSpeed*SIZE))))
				y -= movementSpeed*SIZE;
		}
		
		else if (input.isKeyPressed(Input.KEY_DOWN)){
			currentSprite = down;
			
			if (y <= 475 &(!(map.hasCollision(x, y + movementSpeed*SIZE))))
				y += movementSpeed * SIZE;
			
		}
		
		else if (input.isKeyPressed(Input.KEY_LEFT)){
			currentSprite = left;
			
			if (x > 0 &(!(map.hasCollision(x - movementSpeed*SIZE, y))))
				x -= movementSpeed * SIZE;
			
		}
		
		else if (input.isKeyPressed(Input.KEY_RIGHT)){
			currentSprite = right;
			
			if (x <= 1080 &(!(map.hasCollision(x + movementSpeed*SIZE, y))))
				x += movementSpeed * SIZE;
		}	
		
	}
	
	
	
	//ALL CODE BELOW WAS CREATED IN ATTEMPT TO SMOOTH OUT MOVEMENT
	//WILL FIGURE OUT LATER. NOT PART OF THE ITERATION TASK LIST THIS WEEK
	//Yeah...
	private float getDistanceX(int current_x, int target_x)
	{
		float distance_x = Math.abs(current_x-target_x);
		return distance_x;
	}
	
	private float getDistanceY(int current_y, int target_y){
		float distance_y = Math.abs(current_y-target_y);
		return distance_y;
	}
	private void delayTimer(){
		for (long i = 0; i <10000;i++)
		{
			
		}
	}
	
	
		private void moveTowardsY(int current_y, int target_y)
	{			

		//FUNCTION RENDER SMOOTHER TRANSITIONS
		//Has to increment y until it's reach the target y
		//calls render appropriately.
	}
}