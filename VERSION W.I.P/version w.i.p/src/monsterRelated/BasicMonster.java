package monsterRelated;


import java.util.Random;

import mapRelated.BasicMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

//////
//BasicMonster
//Purpose: Controls and manages the various monsters
//Limit: ?
//////
public class BasicMonster extends Entity{

	protected boolean isActiveState = false;
	protected Image monsterImage;
	protected Animation monsterAnimation;
	protected double monsterSightRange;
	protected char direction;
	public int damageLimit = 20;//This can be overridden by its children later. Just watch for it.
	private BasicMap map;
	private int pathStart = 7*BasicMap.TILESIZE, pathEnd = 9*BasicMap.TILESIZE;
	
	
	private int counter;
	

	///////////////////////////////////
	//////////For TEST ONLY////////////
	public BasicMonster(BasicMap map, int x, int y, int counter){
		super(x,y);
		this.counter = counter;
		this.map = map;
		name = "M";
		monsterSightRange = 2;
		counter = 0;
		direction = 'R';
		maxHealthPoints = 1000;
		healthPoints = maxHealthPoints;
		}

	//////////For TEST ONLY////////////
	///////////////////////////////////
	
	
	
	

	
	// This determines the attributes of the basic monster (its HP, how it looks, etc)
	public BasicMonster(BasicMap currentMap, Animation monsterLook,int x, int y) throws SlickException
	{
		super(x,y);
		map = currentMap;
		name = "M";
		monsterSightRange = 2;
		SpriteSheet basicMonsterSheet= new SpriteSheet("res/monster/dummySheet.png",32,32); 
		monsterImage = basicMonsterSheet.getSubImage(0, 0);
		monsterAnimation = monsterLook;
		monsterAnimation.setAutoUpdate(true);
		counter = 0;
		direction = 'R';
		maxHealthPoints = 10;
		healthPoints = maxHealthPoints;
	}

	// Is this a repeat of the previous function?
	public BasicMonster(BasicMap currentMap, Image monsterLook,int x, int y)
	{
		super(x,y);
		map = currentMap;
		name = "M";
		monsterSightRange = 2;
		monsterImage = monsterLook;
		counter = 0;
		direction = 'R';
		maxHealthPoints = 10;
		healthPoints = maxHealthPoints;
	}

	///////Methods dealing with the state of the monster///////
	public boolean getMonsterState(){return isActiveState;}
	
	// The monster will be active when it sees the player
	public void setMonsterState(boolean foundPlayer){
		isActiveState = foundPlayer;
	}
	
	// This is what happens when the monster dies
	public void actDead() throws SlickException{
		monsterImage = new Image ("res/monster/dead.png");
	}
	
	// The experience the player will gain when he/she kills this monster
	public int getExpPointGain() {return maxHealthPoints/2;}
	
	
	// Draws the monster
	public void render(Graphics g) throws SlickException{
		if (!alive)
			actDead();
		g.drawImage(monsterImage, (int)x, (int)y);
	//	monsterAnimation.draw((int)x, (int)y);
	}
	
	// Updates the state of the monster (its position, whether it sees the player, etc)
	public String update(int [] playerPosition, int counter)
	{
		this.counter = counter;
		oldx = x;
		oldy = y;
		
		//Check for Overlap
		if (entityArray[playerPosition[0]/32][playerPosition[1]/32] == " " ||
			entityArray[playerPosition[0]/32][playerPosition[1]/32] == "M"){
			return "Player has disappeared from the map.";}

		
		isActiveState = search("P");
		if (isActiveState){
			//This is supposed to use a more intelligent wander.
//			x = findClosestSpot(getPosition(), playerPosition)[0];
//			y = findClosestSpot(getPosition(),playerPosition)[1];
			wander(playerPosition);
//			Game.queueTextLog.addFirst ("Monster has spotted you!");
		}
		else{
			wander(playerPosition);
		}
		
		return null;
	}
		
////////////////////////////////////////////////	
	///More advanced version of search not properly implmented or used
//	private boolean monsterSees(int[] playerPosition){
//		double distance = Math.sqrt((double)( (playerPosition[0]- x)^2+(playerPosition[1]-y)^2));
//		if (distance < monsterSightRange)
//			return true;
//		else
//			return false;
//	}
//	
	
	////////////////////////////////////////////
	/////////////Movement Methods///////////////
	////////////////////////////////////////////
	
	// The path the monster will take when moving
	public void setPath (int start, int end){
		pathStart = start;
		pathEnd = end;}
	
	// This is how the monster will move when it is not fighting
	private void wander(int [] playerPosition){
	if (!alive)
		return;
	
	int newX1 = x+BasicMap.TILESIZE;
	int newX2 = x-BasicMap.TILESIZE;	
	if ((newX2>= 0 || newX1<=1048)&&counter >= 400){
		//Change Direction
		if (x > pathEnd)
			direction = 'L';

		//move rightwards	
		if (direction == 'R'&&x <= pathEnd&&!isTaken(newX1,y)){
			if (map.hasCollision(newX1, y))
			{
				direction = 'L';
				return;
			}
			updatePosition(newX1,y);
			x = newX1;
			
			}
		//move leftwards
		else if (direction == 'L' && x >= pathStart&&!isTaken(newX2,y)){
				if (map.hasCollision(newX2, y))
				{
					direction = 'R';
					return;
				}
				updatePosition(newX2,y);
				x = newX2;
				//Change Direction
				if (x  < pathStart){
					direction = 'R';
				}
		}
			
		counter = 0;
	}
	else
		counter++;
	}
	
	
/////////////////////////////////////////////////////////////////////////
//////////////////////// To Be Implemented //////////////////////////////	
/////////////////////////////////////////////////////////////////////////
	
	////Used for a more intelligent wander
	////Will most likely be adapted for following fleeing players.
	public int[] findClosestSpot(int[]current, int[] player)
	{	
		oldx = x;
		oldy = y;
		Random gen = new Random();
		int newY = gen.nextInt(y+32-(y-1))+y-32;
		int newX = gen.nextInt(x+32-(x-1))+x-32;
		if (current[0] == player[0]&& current[1]!= player[1]){
			if(!isTaken(x, newY))
			{	
				newX = x;
			}
		}
			
		else if (current[1] == player[1]&& current[0] != player[0])
		{
			while(!isTaken(newX, y))
			{
				newY = y;
				newX = gen.nextInt(x+1-(x-1))+x-1;			}
		}
		
		else if (current[1]!= player[1]&& current[0]!= player[0])
		{
			while(!isTaken(x, newY))
			{	
				newX = gen.nextInt(x+32-(x-32))+x-32;
				newY = gen.nextInt(y+32-(y-32))+y-32;	
			}
		}
		int [] newPosition = {newX,newY};
		return newPosition;
	}

	
	
}
