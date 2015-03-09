package monsterRelated;

import gameStates.Game;
import mapRelated.BasicMap;

public class Creature {
	
	protected int oldx = 0, oldy = 0;
	protected int x,y;
	protected String [][] entityArray;
	protected String name;
	
	protected int healthPoints = 200;
	protected int maxHealthPoints = 200;
	protected boolean alive = true;
	
	
	public Creature (int x,int y){
		this.x = x;
		this.y = y;
	}
	
/////////////////////////////////////////////////
//METHODS USED FOR SEARCHING UPDATING LOCATION IN ENTITY ARRAY
/////////////////////////////////////////////////
	protected void updatePosition(int x, int y){
		if (!alive)
			return;
		oldx = this.x;
		oldy = this.y;
		entityArray[oldx/BasicMap.TILESIZE][oldy/BasicMap.TILESIZE] = " ";
		entityArray[x/BasicMap.TILESIZE][y/BasicMap.TILESIZE] = name;
		this.x = x;
		this.y = y;
	}

	protected boolean isTaken(int x, int y){
		int xTile = x/BasicMap.TILESIZE;
		int yTile = y/BasicMap.TILESIZE;
		boolean isTaken = false;
		if (entityArray[xTile][yTile] != " ")
			isTaken = true;
		return isTaken;
	}
	

	public boolean search(String name){
			boolean found = false;
			//Search looks within the range of 2
			for (int row = (x/BasicMap.TILESIZE-2); row < (x/BasicMap.TILESIZE+2); row++)
			{
				for (int column = (y/BasicMap.TILESIZE)-2; column < y/BasicMap.TILESIZE+2; column++)
				{
					//If within bounds of the map.
					if (row >= 0 && row < BasicMap.heightByTiles && column >= 0 && column <= BasicMap.widthByTiles)
					{
						if (entityArray[row][column]!= null&&entityArray[row][column].equals(name))
						{
							found = true;
							break;
						}
					}
					
				}	
			}	
			return found;
	}
	
///////////////////////////////////////////
///////////Combat Methods/////////////////
/////////////////////////////////////////
	public void subtractHealth(int points){
		if (healthPoints - points <= 0){
			alive = false;
			entityArray[x/BasicMap.TILESIZE][y/BasicMap.TILESIZE] = " ";
			healthPoints = 0;
		}
		else
			healthPoints -= points;
	}
	
	public void addHealthPoints(int points){
		if (healthPoints+points > maxHealthPoints)
			healthPoints = maxHealthPoints;
		else
			healthPoints += points;
	}
	
	public int getHealthPoints(){return healthPoints;}
	public int getMaxHealthPoints() {return maxHealthPoints;}
	
	
	
	
	
	
	/////GENERAL GET METHODS///////////////////////////
	public void setEntityArray(String[][] entityArray){this.entityArray = entityArray;}
	
	public String[][]getEntityArray(){return entityArray;}
	public boolean getAlive(){return alive;}
	
	public String getName(){return name;}	
	
	public int[] getPosition(){
		int[] position = new int[2];
		position[0] = x;
		position[1] = y;
		return position;
	}
	
		
}
