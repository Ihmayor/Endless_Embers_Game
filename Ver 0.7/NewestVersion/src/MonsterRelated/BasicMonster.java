package monsterRelated;

import java.util.Random;

import mapRelated.BasicMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class BasicMonster extends Creature{

	protected boolean isActiveState = false;
	protected Image monsterImage;
	protected double monsterSightRange;
	protected char direction;
	public int damageLimit = 20;//This can be overridden by its children later. Just watch for it.
	
	private int counter;
	
	//////////For TEST ONLY////////////////
	public BasicMonster(int x, int y, int counter){
		super(x,y);
		this.counter = counter;
		name = "M";
		monsterSightRange = 2;
		counter = 0;
		direction = 'R';
		maxHealthPoints = 100;
		healthPoints = maxHealthPoints;
		}
	///////////////////////////////////
	
	
	
	
	public BasicMonster(BasicMap map, Image monsterLook,int x, int y)
	{
		super(x,y);
		name = "M";
		monsterSightRange = 2;
		monsterImage = monsterLook;
		counter = 0;
		direction = 'R';
		maxHealthPoints = 100;
		healthPoints = maxHealthPoints;
	}
	
	
	public boolean getMonsterState(){return isActiveState;}
	
	
	public void setMonsterState(boolean foundPlayer){
		isActiveState = foundPlayer;
	}
	
	
	public void render(Graphics g) throws SlickException{
		if (!alive)
			actDead();
		g.drawImage(monsterImage, (int)x, (int)y);
	}
	public void update(int [] playerPosition, int counter)
	{
		this.counter = counter;
		oldx = x;
		oldy = y;
		if (entityArray[playerPosition[0]/32][playerPosition[1]/32] == " " ||
			entityArray[playerPosition[0]/32][playerPosition[1]/32] == "M"){
			PrintingTests.printEntityArray(entityArray);}

		
		isActiveState = search("P");
		if (isActiveState){
			//This is supposed to use a more intelligent wander.
//			x = findClosestSpot(getPosition(), playerPosition)[0];
//			y = findClosestSpot(getPosition(),playerPosition)[1];
			wander(playerPosition,7*BasicMap.TILESIZE,9*BasicMap.TILESIZE);
//			Game.statusUpdate = "Monster has spotted you!";
		}
		else{
			wander(playerPosition,7*BasicMap.TILESIZE,9*BasicMap.TILESIZE);
		}
	}
		
	///More advanced version of search
	private boolean monsterSees(int[] playerPosition){
		double distance = Math.sqrt((double)( (playerPosition[0]- x)^2+(playerPosition[1]-y)^2));
		if (distance < monsterSightRange)
			return true;
		else
			return false;
	}
	
	

	/////////////Movement Methods///////////////
	////////////////////////////////////////////////////////////
	private void wander(int [] playerPosition, int pathStart, int pathEnd){
	
	int newX1 = x+BasicMap.TILESIZE;
	int newX2 = x-BasicMap.TILESIZE;	
	if ((newX2>= 0 || newX1<=1080)&&counter >= 500){
		//Change Direction
		if (x > pathEnd)
			direction = 'L';

		//move rightwards	
		if (direction == 'R'&&x <= pathEnd&&!isTaken(newX1,y)){
			updatePosition(newX1,y);
			x = newX1;
			}
		//move leftwards
		else if (direction == 'L' && x >= pathStart&&!isTaken(newX2,y)){
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

	
	public void actDead() throws SlickException{
		monsterImage = new Image ("res/interface/test.png");
	}
	
	
	public int getExpPointGain() {return maxHealthPoints/2;}
	
}
