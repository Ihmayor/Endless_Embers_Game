package monsterRelated;


import mapRelated.BasicMap;
import mapRelated.MapSpotCalculator;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class BasicMonster extends Entity{

	//Variables related to Images
	protected Image monsterImage;
	protected Animation monsterAnimation;
	
	//Variables related to spotting the player
	protected double monsterSightRange;
	protected boolean isActiveState = false;
	

	//Variables related to movement	
	private int counter;
	protected char direction;
	private int pathStart = 7*BasicMap.TILESIZE, pathEnd = 9*BasicMap.TILESIZE;
	private BasicMap map;
	
	//Variables related to combat
	public int damageLimit = 18;//This can be overridden by its children later. Just watch for it.
	protected boolean isAttacked = false;
	
	

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

	
	public BasicMonster(BasicMap currentMap, Animation monsterLook,int x, int y) throws SlickException
	{	
		super(x,y);
		map = currentMap;
		name = "M";
		monsterSightRange = 2;
		SpriteSheet basicMonsterSheet= new SpriteSheet("res/monster/dummySheet.png",BasicMap.TILESIZE,BasicMap.TILESIZE); 
		monsterImage = basicMonsterSheet.getSubImage(0, 0);
		monsterAnimation = monsterLook;
		monsterAnimation.setAutoUpdate(true);
		counter = 0;
		direction = 'R';
		maxHealthPoints = 100;
		healthPoints = maxHealthPoints;
	}
	
	

	//////////////////////////////////// STILL TEST METHODS ABOVE//////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	
	
	
	//Initializes Monster
	public BasicMonster(BasicMap currentMap,Animation monsterAnimation, Image monsterLook,int x, int y)
	{
		super(x,y);
		map = currentMap;
		name = "M";
		monsterSightRange = 2;
		monsterImage = monsterLook;
		this.monsterAnimation = monsterAnimation;
	//	monsterAnimation.setAutoUpdate(true);
		counter = 0;
		direction = 'R';
		maxHealthPoints = 20;
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
	
	
	//Methods dealing with monster combat
	public void setHealthPoints(int points) {healthPoints = points;}
	public void setMonsterMaxHealth(int monsterMaxHealth) {monsterMaxHealth = maxHealthPoints;}
	public int getExpPointGain() {return (maxHealthPoints/2)+10;}
	
	
	//Draws Monster to Screen
	public void render(Graphics graphics) throws SlickException{
		if (!alive)
			actDead();
		monsterAnimation.draw((int)x, (int)y);
	}
	
	//Updates Monster's Position
	public String update(int [] playerPosition, int counter)
	{
		
		this.counter = counter;
		oldx = x;
		oldy = y;
		//Check for Overlap
		if (playerPosition[0] < 0 || playerPosition [1] < 0)
			return "Invalid Player Position! Going Off Map";
		if (entityArray[playerPosition[0]/BasicMap.TILESIZE][playerPosition[1]/BasicMap.TILESIZE] == " " ||
			entityArray[playerPosition[0]/BasicMap.TILESIZE][playerPosition[1]/BasicMap.TILESIZE] == "M"){
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
		
	
	////////////////////////////////////////////
	/////////////Movement Methods///////////////
	////////////////////////////////////////////

	//Sets path that monsters wanders along
	public void setPath (int start, int end){
		pathStart = start;
		pathEnd = end;}
	
	//Moves monster according to its path
	private void wander(int [] playerPosition){
	if (!alive)
		return;
	
	int newX1 = x+BasicMap.TILESIZE;
	int newX2 = x-BasicMap.TILESIZE;	
	if ((newX2>= 0 && newX1<=1048)&&counter >= 400){
		//Change Direction
		if (newX1 > pathEnd)
			direction = 'L';

		//move rightwards	
		if (direction == 'R'&&!isTaken(newX1,y)){
			if (map.hasCollision(newX1, y))
			{
				direction = 'L';
				return;
			}
			updatePosition(newX1,y);
			x = newX1;
			
			}
		//move leftwards
		else if (direction == 'L' &&!isTaken(newX2,y)){
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
	
		
	//Used to find closestSpot near the player
	public void findClosestSpot(int[] player)
	{
		int newX = 0;
		int newY = 0;
		if (x == player[0]&& y!= player[1]){
			int[] newPosition = MapSpotCalculator.closestSpotHorizontal(map, player, this, new int[] {newX,newY});
			updatePosition(newPosition[0],newPosition[1]);				
			}
			
		else if (y == player[1]&& x != player[0])
		{
			closestSpotVertical(player,newX, newY);
		}
		else if (y!= player[1]&& x!= player[0])
		{
			int[] newPosition = MapSpotCalculator.closestSpotDiagonal(map, player, this, new int[] {newX,newY});
			updatePosition(newPosition[0],newPosition[1]);
		}
	}	
	
	
	

	
	

	//Finds closest vertical spots towards player
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

	
	

