package MonsterRelated;

import java.util.Random;

import mapRelated.BasicMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class BasicMonster extends Creature{
	private boolean isActiveState = false;
	private String name;
	private Image monster;
	
	public String getName(){return name;}
	
	public BasicMonster(BasicMap map, Image monster)
	{
		super.x = 7*32;
		super.y = 10*32;
		super.name = "M";
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
		if (search("P"))
			isActiveState = true;
		else 
			isActiveState = false;
		
		if (isActiveState){
			x = findClosestSpot(getPosition(), playerPosition)[0];
			y = findClosestSpot(getPosition(),playerPosition)[1];
			super.setPosition(playerPosition, "P");
		}
		else
			wander(playerPosition, "P");
	}
	
	private void wander(int [] playerPosition, String otherName){
	Random gen = new Random();
	x = gen.nextInt(x+32 - (x-32))+x-32;
	y = gen.nextInt(y+32 - (y-32))+y-32;
	super.setPosition(playerPosition, "P");
	}
	
	
	public int[] findClosestSpot(int[]current, int[] player)
	{
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
