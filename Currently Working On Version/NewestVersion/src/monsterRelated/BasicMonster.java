package monsterRelated;


import java.util.Random;

import mapRelated.BasicMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class BasicMonster extends Entity{

	protected boolean isActiveState = false;
	protected Image monsterImage;
	protected Animation monsterAnimation;
	protected double monsterSightRange;
	protected char direction;
	public int damageLimit = 20;//This can be overridden by its children later. Just watch for it.
	private BasicMap map;
	private int pathStart = 7*BasicMap.TILESIZE, pathEnd = 9*BasicMap.TILESIZE;
	protected boolean isAttacked = false;
	
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
		maxHealthPoints = 100;
		healthPoints = maxHealthPoints;
	}
	//////////////////////////////////// STILL TEST METHODS ABOVE//////////////////////////

	public BasicMonster(BasicMap currentMap, Image monsterLook,int x, int y)
	{
		super(x,y);
		map = currentMap;
		name = "M";
		monsterSightRange = 2;
		monsterImage = monsterLook;
		counter = 0;
		direction = 'R';
		maxHealthPoints = 100;
		healthPoints = maxHealthPoints;
	}

	///////Methods dealing with the state of the monster///////
	public boolean getMonsterState(){return isActiveState;}
	
	
	public void setMonsterState(boolean foundPlayer){
		isActiveState = foundPlayer;
	}
	public void setIsAttacked (boolean attacked){isAttacked = attacked;}
	
	public void actDead() throws SlickException{
		monsterImage = new Image ("res/monster/dead.png");
	}
	
	
	public int getExpPointGain() {return maxHealthPoints/2;}
	
	
	
	public void render(Graphics g) throws SlickException{
		if (!alive)
			actDead();
		g.drawImage(monsterImage, (int)x, (int)y);
	//	monsterAnimation.draw((int)x, (int)y);
	}
	
	
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
		if (isActiveState || (isAttacked&&!isActiveState)){
			findClosestSpot(playerPosition);
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
	
	public void setPath (int start, int end){
		pathStart = start;
		pathEnd = end;}
	
	
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
	public void findClosestSpot(int[] player)
	{
		int newX = 0;
		int newY = 0;
		if (x == player[0]&& y!= player[1]){
				closestSpotHorizontal (player, newX, newY);
			}
			
		else if (y == player[1]&& x != player[0])
		{
			closestSpotVertical(player,newX, newY);
		}
		else if (y!= player[1]&& x!= player[0])
		{
			closestSpotDiagonal(player,newX, newY);
				
		    }
	}	
	
	
	private void closestSpotDiagonal(int [] player, int newX, int newY)
	{

		if (y > player[1])
			newY = y - BasicMap.TILESIZE;
		else
			newY = y + BasicMap.TILESIZE;
		
		if (x > player [0])
			newX = x - BasicMap.TILESIZE;
		else
			newX = x + BasicMap.TILESIZE;		
		
		if (!isTaken(newX, newY) && !map.hasCollision(newX, newY))
				{
				updatePosition(newX,newY);
				x = newX;
				y = newY;
				}
		
		else if (!isTaken(x, newY) && !map.hasCollision(x, newY))
				{
				updatePosition(x,newY);
				y = newY;
			    }
		
		else if (!isTaken(newX, y) && !map.hasCollision(newX, y))
				{
				updatePosition(newX, y);
				x = newX;
				}		
	}
	
	
	
	private void closestSpotHorizontal(int [] player, int newX, int newY){
		if (player[1] > y)
			newY = y+ BasicMap.TILESIZE;
		else
			newY = y- BasicMap.TILESIZE;
		if (!isTaken(x,newY)&&!map.hasCollision(x,newY))
			{		
			updatePosition(x,newY);
			y = newY;
			}
	}
	
	
	
	private void closestSpotVertical(int [] player, int newX, int newY){
		
		if (player[0] > x)
			newX = x + BasicMap.TILESIZE;
		else
			newX = x - BasicMap.TILESIZE;
		
		if (!isTaken(newX,y)&&!map.hasCollision(newX, y))
			{
			updatePosition(newX,y);
			x = newX;
			}
	}
}

	
	

