package MonsterRelated;

import java.util.Random;

import mapRelated.BasicMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class BasicMonster extends Creature{

	protected boolean isActiveState = false;
	protected String name;
	protected Image monster;
	protected double monsterSightRange;
	public String getName(){return name;}
	
	public BasicMonster(BasicMap map, Image monster,int x, int y)
	{
		super(x,y);
		name = "M";
		monsterSightRange = 2;
		this.monster = monster;
	}
	
	
	public boolean getMonsterState(){return isActiveState;}
	
	
	public void setMonsterState(boolean foundPlayer){
		isActiveState = foundPlayer;
	}
	
	
	public void render(Graphics g){
		g.drawImage(monster, (int)x, (int)y);
	}
	
	public void update(int [] playerPosition)
	{
		oldx = x;
		oldy = y;
		isActiveState = monsterSees(playerPosition);
		if (isActiveState){
//			x = findClosestSpot(getPosition(), playerPosition)[0];
//			y = findClosestSpot(getPosition(),playerPosition)[1];
			updatePosition(x,y);
		}
		else
			System.out.println("Test Else branch Monster Update!");
//			wander(playerPosition, "P");
	}
	
	private boolean monsterSees(int[] playerPosition){
		double distance = Math.sqrt((double)( (playerPosition[0]- x)^2+(playerPosition[1]-y)^2));
		if (distance < monsterSightRange)
			return true;
		else
			return false;
	}
	
	
	private void wander(int [] playerPosition, String otherName){
	Random gen = new Random();
	oldx = x;
	oldy = y;
	x = gen.nextInt(x+32 - (x-32))+x-32;
	y = gen.nextInt(y+32 - (y-32))+y-32;
	updatePosition(x,y);
	}
	
	
	public int[] findClosestSpot(int[]current, int[] player)
	{	
		oldx = x;
		oldy = y;
		Random gen = new Random();
		int newY = gen.nextInt(y+32-(y-1))+y-32;
		int newX = gen.nextInt(x+32-(x-1))+x-32;
		if (current[0] == player[0]&& current[1]!= player[1]){
			while(!isTaken(x, newY))
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
