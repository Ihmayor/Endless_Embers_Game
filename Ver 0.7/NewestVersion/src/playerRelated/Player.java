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
	
	
	//Basic Sprite Variables
	private SpriteSheet sheet;
		private Animation currentSprite, up, down,left,right;

	//Limited Vision Effect
	private Image shadow;		
	
	
	
		
		
	public Player(GameContainer gc, StateBasedGame sbg, BasicMap map,int x, int y) throws SlickException{
		//Constructor used to 
		super(x,y);
		//Variables for the usage outside functions
		this.gc = gc;
		this.sbg = sbg;
		this.map = map;
		//Initialize Variables
		name = "P";
		sheet = new SpriteSheet("res/player/template2.png", 32,32);
		shadow = new Image("res/player/largerShadow.png");
		loadPlayerSprite(sheet);
		
	}
	
	private void loadPlayerSprite(SpriteSheet playerSheet){
		//Load Sprite Images for Player
				Image [] upSprite = {sheet.getSubImage(0,3),
								     sheet.getSubImage(1,3),
								     sheet.getSubImage(2,3),
								     sheet.getSubImage(3,3)};
				Image [] downSprite = {sheet.getSubImage(0,0),
					     			  sheet.getSubImage(1,0),
					     			  sheet.getSubImage(2,0),
					     			  sheet.getSubImage(3,0)};
				Image [] rightSprite = {sheet.getSubImage(0,2),
										sheet.getSubImage(1,2),
										sheet.getSubImage(2,2),
										sheet.getSubImage(3,2)};
				
				Image [] leftSprite = {sheet.getSubImage(0,1),
					     			   sheet.getSubImage(1,1),
					     			   sheet.getSubImage(2,1),
					     			   sheet.getSubImage(3,1)};

			//Set the duration of Animation in Milliseconds	
				int [] duration = {10,10,10,10};
				
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
//	g.drawImage(shadow,(int)x-1110, (int)y-850); //Draw Shadow with a particular offset for the spotlight
	}
	
	
	public void setMap(BasicMap map){
		this.map = map;//Just In case we're loading new floors.
	}	
	
	public boolean delayUpdate(long delta){
		return false;
	}
	
	public void update(long delta){
		int movementSpeed = 1;
		final int SIZE = BasicMap.TILESIZE;//Variable used to shorten variable names
		int newX = 0;
		int newY = 0;

		Input input = gc.getInput();
		 
		if (input.isKeyPressed(Input.KEY_NUMPAD7)||input.isKeyPressed(Input.KEY_7)){
			 currentSprite = left;
			 newX = x-movementSpeed*SIZE;
			 newY = y-movementSpeed*SIZE;
			if (!(map.hasCollision(newX, newY))&& !(isTaken(newX/SIZE, newY/SIZE)))
				{
				updatePosition(newX,newY);
				x = newX;
				y = newY;
				map.isStairs(x,y);
				}
		 }

		else if (input.isKeyPressed(Input.KEY_UP)||input.isKeyPressed(Input.KEY_8)
				||input.isKeyPressed(Input.KEY_NUMPAD8)){
			currentSprite = up;
			newY = y - movementSpeed*SIZE;
			if (!(map.hasCollision(x, newY))&&!isTaken(x,newY)){
				updatePosition(x,newY);
				y = newY;
				map.isStairs(x,y);
			}
		}
		
		
		else if (input.isKeyPressed(Input.KEY_NUMPAD9)||input.isKeyPressed(Input.KEY_9)){
			currentSprite = right;
			newX = x + movementSpeed*SIZE;
			newY = y - movementSpeed*SIZE;
				if (!(map.hasCollision(newX, newY))&&!isTaken(newX, newY))	
				{
					updatePosition(newX,newY);
					y = newY;
					x = newX;	
					map.isStairs(x,y);
				}
		}

		
		else if (input.isKeyPressed(Input.KEY_LEFT)||input.isKeyPressed(Input.KEY_U)
				||input.isKeyPressed(Input.KEY_NUMPAD4)){
			currentSprite = left;
			newX = x-movementSpeed*SIZE;
			if (!(map.hasCollision(newX, y))&&!isTaken(newX, y)){
				updatePosition(newX,y);
				x = newX;
				map.isStairs(x,y);
			}
			
		}
		
		else if (input.isKeyPressed(Input.KEY_NUMPAD5)||input.isKeyPressed(Input.KEY_I))
			{
			currentSprite = down;
			}
		
		else if (input.isKeyPressed(Input.KEY_RIGHT)||input.isKeyPressed(Input.KEY_O)||
				input.isKeyPressed(Input.KEY_NUMPAD6)){
			currentSprite = right;
			newX = x + movementSpeed*SIZE;
			if (!(map.hasCollision(newX, y))&& !isTaken(newX, y)){
				updatePosition(newX,y);
				x = newX;
				map.isStairs(x,y);

			}
		}	
		
		
		else if (input.isKeyPressed(Input.KEY_NUMPAD1)||input.isKeyPressed(Input.KEY_J)){
				currentSprite = left;
				newX = x-movementSpeed*SIZE;
				newY = y +movementSpeed*SIZE;
				if (!(map.hasCollision(newX,  newY))&&!isTaken(newX, newY))
					{
					updatePosition(newX,newY);
					x = newX;
					y = newY;
					map.isStairs(x,y);
					}
				}
		else if (input.isKeyPressed(Input.KEY_DOWN)||input.isKeyPressed(Input.KEY_K)||
				input.isKeyPressed(Input.KEY_NUMPAD2)){
			currentSprite = down;
			newY = y +movementSpeed*SIZE;
			if (!(map.hasCollision(x, newY))&&!isTaken(x, newY)){
				updatePosition(x,newY);
				y = newY;
				map.isStairs(x,y);
				}
			
		}

		else if (input.isKeyPressed(Input.KEY_NUMPAD3)||input.isKeyPressed(Input.KEY_L)){
			currentSprite = right;
			newX = x-movementSpeed*SIZE;
			newY = y+movementSpeed*SIZE;
				if (!(map.hasCollision(newX,newY))&& !isTaken(newX, newY))
					{
					updatePosition(newX,newY);
					x = newX;
					y = newY;
					map.isStairs(x,y);
					}
			
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
