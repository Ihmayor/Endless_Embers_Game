package playerRelated;

import gameStates.GameOver;
import gameStates.GameScreenAssets;
import managers.CombatManager;
import managers.SoundManager;
import mapRelated.BasicMap;
import monsterRelated.Entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

//////
// Player
// Purpose: Manages the character controlled by the player
// Limit: ?
//////
public class Player extends Entity{
	
	//Variables used for Slick 2 Game Components
	private final GameContainer gc;
	private final StateBasedGame sbg;
	
	//Used for stair case movement 
	private boolean onStairs;
	
	//Variables used for Combat and related aspects
	private int experiencePoints = 0;
	private int pointsNextLevel = 1000;

	private int playerLevel = 1;
	private int damageIncrease = 0;//Could be increased for level ups
	private int criticalHitLimit= 30;
	private int missFactor = 5;
	
	//Basic Sprite Variables
	private SpriteSheet sheet;
	private Animation currentSprite, up, down,left,right;

	//Limited Vision Effect
	private Image shadow;		
	
	////FOR TEST PURPOSES ONLY////
	public Player (int x, int y){
		super(x,y);
		gc = null;
		sbg = null;
		name = "P";
	}
	////FOR TEST PURPOSES ONLY////
		
	// Initializes the player
	public Player(GameContainer gc, StateBasedGame sbg, BasicMap currentMap,int x, int y) throws SlickException{
		//Constructor used to 
		super(x,y);
		
		//Variables for the usage outside functions
		this.gc = gc;
		this.sbg = sbg;
		map = currentMap;
		
		//Initialize Variables
		name = "P";
		sheet = new SpriteSheet("res/player/template2.png", 32,32);
		shadow = new Image("res/player/largerShadow.png");
		loadPlayerSprite(sheet);
		
	}
	
	// This is what the player looks like (note that the sprite changes when the player is facing a different direction)
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
	
	
	// Draw the player
	public void render(Graphics g){
	currentSprite.draw((int) x, (int) y);//Draw what the Current sprite should look like.
	//g.drawImage(shadow,(int)x-1110, (int)y-850); //Draw Shadow with a particular offset for the spotlight
	}
	
	// Update the state of the player (if s/hes dead)
	public void update(long delta){
		
		//If the player is not alive change game state.
		if (!alive){
			
			GameScreenAssets.queueTextLog.add( "Your player be dead");
			
			//Change state of game to game over sate.
			sbg.enterState(GameOver.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
			//Change Sound to Game Over State's sound
			SoundManager.changeSound("res/sound/A Time To Lose.wav");
			}
		
		
		
		//Input used to get keyboard controls
		Input input = gc.getInput();
		 
		//Diagonal Up Left
		if (input.isKeyPressed(Input.KEY_NUMPAD7)||input.isKeyPressed(Input.KEY_7)){
			 moveDiagonalUpLeft();
		 }
		
		//Normal Up
		else if (input.isKeyPressed(Input.KEY_UP)||input.isKeyPressed(Input.KEY_8)
				||input.isKeyPressed(Input.KEY_NUMPAD8)){
				moveUp();
		}
		
		//Diagonal Up Right
		else if (input.isKeyPressed(Input.KEY_NUMPAD9)||input.isKeyPressed(Input.KEY_9)){
				moveDiagonalUpRight();
		}

		//Normal Left
		else if (input.isKeyPressed(Input.KEY_LEFT)||input.isKeyPressed(Input.KEY_U)
				||input.isKeyPressed(Input.KEY_NUMPAD4)){
				moveLeft();
		}
		
		//PASS TURN
		else if (input.isKeyPressed(Input.KEY_NUMPAD5)||input.isKeyPressed(Input.KEY_I))
			{
			moveNowhere();
			}
		
		//Normal Right
		else if (input.isKeyPressed(Input.KEY_RIGHT)||input.isKeyPressed(Input.KEY_O)||
				input.isKeyPressed(Input.KEY_NUMPAD6)){
			moveRight();
			}
		
		//Diagnoal Down Left
		else if (input.isKeyPressed(Input.KEY_NUMPAD1)||input.isKeyPressed(Input.KEY_J)){
				moveDiagonalDownLeft();
				}
		
		//Normal Down
		else if (input.isKeyPressed(Input.KEY_DOWN)||input.isKeyPressed(Input.KEY_K)||
				input.isKeyPressed(Input.KEY_NUMPAD2)){
			moveDown();
		}

		//Diagonal Down Right
		else if (input.isKeyPressed(Input.KEY_NUMPAD3)||input.isKeyPressed(Input.KEY_L)){
			moveDiagonalDownRight();
		}
		 
	}

	
////////////METHODS DEALING WITH MOVEMENT//////////////////////
	
	private void moveDiagonalUpLeft(){
			 currentSprite = left;
			int newX = x-BasicMap.TILESIZE;
			int newY = y-BasicMap.TILESIZE;
			if (isTaken(newX, newY))
				attack(newX,newY);
			else if (!(map.hasCollision(newX, newY)))
				{
				updatePosition(newX,newY);
				x = newX;
				y = newY;
				
				if (map.isStairs(x, y)){
					onStairs = true;
					setMap(map);
					}
				}
		 }
	
	
	private void moveUp(){
			currentSprite = up;
			int newY = y - BasicMap.TILESIZE;
			if (isTaken(x,newY))
				attack(x, newY);
			else if (!(map.hasCollision(x, newY))){
				updatePosition(x,newY);
				y = newY;
				if (map.isStairs(x, y)){
					onStairs = true;
					setMap(map);
					}
			}
		}
		
		
	private void moveDiagonalUpRight(){
			currentSprite = right;
			int newX = x + BasicMap.TILESIZE;
			int newY = y - BasicMap.TILESIZE;
			
			if (isTaken(newX, newY))
				attack(newX, newY);
			else if (!(map.hasCollision(newX, newY)))	
				{
					updatePosition(newX,newY);
					y = newY;
					x = newX;	
					if (map.isStairs(x, y)){
						onStairs = true;
						setMap(map);
						}
				}
		}

	private void moveLeft(){	
			currentSprite = left;
			int newX = x-BasicMap.TILESIZE;
			if (isTaken(newX, y))
				attack(newX,y);
			else if (!(map.hasCollision(newX, y))){
				updatePosition(newX,y);
				x = newX;
				map.isStairs(x,y);
			}
			
		}
		
	private void moveNowhere()
			{
			currentSprite = down;
			
			}
		
	private void moveRight(){
			currentSprite = right;
			int newX = x + BasicMap.TILESIZE;
			if  (isTaken(newX, y))
				attack(newX,y);
			else if (!(map.hasCollision(newX, y))){
				updatePosition(newX,y);
				x = newX;
				if (map.isStairs(x, y)){
					onStairs = true;
					setMap(map);
					}
				}
			}	
		
		
	private void moveDiagonalDownLeft(){
				currentSprite = left;
				int newX = x-BasicMap.TILESIZE;
				int newY = y +BasicMap.TILESIZE;
				if (isTaken(newX, newY))
					attack(newX, newY);
				else if (!(map.hasCollision(newX,  newY)))
					{
					updatePosition(newX,newY);
					x = newX;
					y = newY;
					if (map.isStairs(x, y)){
						onStairs = true;
						setMap(map);
						}
					}
				}
				
	private void moveDown(){
			currentSprite = down;
			int newY = y +BasicMap.TILESIZE;
			if (isTaken(x, newY))
				attack (x, newY);
			else if (!(map.hasCollision(x, newY))){
				updatePosition(x,newY);
				y = newY;
				if (map.isStairs(x, y)){
					onStairs = true;
					setMap(map);
					}
				}
			
			}

	private void moveDiagonalDownRight(){
			currentSprite = right;
			int newX = x+BasicMap.TILESIZE;
			int newY = y+BasicMap.TILESIZE;
				if (isTaken(newX, newY)){
					attack(newX, newY);
				}
				else if (!(map.hasCollision(newX,newY)))
					{
					updatePosition(newX,newY);
					x = newX;
					y = newY;
					if (map.isStairs(x, y)){
						onStairs = true;
						setMap(map);
						}
					}
			
				}
	
///////////METHOD DEALING WITH LEVELING UP////////////////////
	
	// When the player is awared experience points
	public String addExperiencePoints(int points){
		if (points <0)
			return "Can't gain negative EXP";
		
		//Add points given
		experiencePoints += points;
		if (levelUp())
		{
			GameScreenAssets.queueTextLog.add("Woohoo! Player has leveled Up!");
			return "Player has leveled up";
		}
		
			
		return null;
	}
	
	// What happens when the player levels up
	private boolean levelUp(){
		
		if (experiencePoints >= pointsNextLevel){
			playerLevel++;
			//Increase Maximum Health & Heal Up Completely
			maxHealthPoints += playerLevel*maxHealthPoints/3;
			healthPoints = maxHealthPoints;
				
			//Decrease Experience Points used up
			//Increase amount needed to next level
			experiencePoints = experiencePoints-pointsNextLevel;
			pointsNextLevel *= playerLevel;
			return true;
			}
		return false;
		
	}

		
	
	
	public int getExperiencePoints(){return experiencePoints;}
	
	public int getPointsNextLevel() {return pointsNextLevel;}
///////////METHODS DEALING WITH COMBAT///////////////////	
	
	private void attack(int monsterX, int monsterY){
	CombatManager.attackLoop(this,criticalHitLimit, missFactor, monsterX, monsterY);	
	}
	
	
//////////Method dealing with stairs///////////////

	public boolean getOnStairs(){return onStairs;}
	
	public void setOnStairs(boolean var){onStairs = var;}

	
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////	
//////////////////////////////////////////////////////////////////////////////////////////////////////	
	//ALL CODE BELOW WAS CREATED IN ATTEMPT TO SMOOTH OUT MOVEMENT/////////
	
	///////////THAT STILL NEEDS TO BE IMPLEMENTED./////////////
	
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
