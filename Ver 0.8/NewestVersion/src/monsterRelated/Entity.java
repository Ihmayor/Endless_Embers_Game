package monsterRelated;

import gameStates.Game;
import mapRelated.BasicMap;

public class Entity {
	
	//Has both the old position and current position 
	protected int oldx = 0, oldy = 0;
	protected int x,y;
	
	//Used to check for overlap with other monsters
	protected String [][] entityArray;
	
	//Name is created (note: never let it equal to 1 )
	protected String name;
	
	//Variables related with the health of the character
	protected int healthPoints = 200;
	protected int maxHealthPoints = 200;
	protected boolean alive = true;
	
	
	protected BasicMap map;
	
	//Every Entity Uses this.
	public Entity (int x,int y){
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
		if (x <0 || y <0)
			return false;
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
	
/////////////////////////////////////////
///////////Combat Methods////////////////
/////////////////////////////////////////
	public String subtractHealth(int points){
		if (points < 0)
			return "Cannot subtract negative health points";
		
		if (healthPoints - points <= 0){
			alive = false;
			entityArray[x/BasicMap.TILESIZE][y/BasicMap.TILESIZE] = " ";
			healthPoints = 0;
		}
		else
			healthPoints -= points;
		return null;
	}
	
	
	public String addHealthPoints(int points){
		if (points < 0)
			return "Cannot add negative health points";
			
		if (healthPoints+points > maxHealthPoints)
			healthPoints = maxHealthPoints;
		else
			healthPoints += points;
		
		return null;
	}
	
	public int getHealthPoints(){return healthPoints;}
	public int getMaxHealthPoints() {return maxHealthPoints;}
	
	
	
	
	
	
	/////GENERAL GET/SET METHODS///////////////////////////
	public String setEntityArray(String[][] entityArray){
		
		if (entityArray.length*entityArray[0].length != 35*16)
			return "Entity Array Not Expected Size";
		
		for (String[] row: entityArray){
			for (String s:row)
			{
				if (s == null)
					return "EntityArray cannot have null objects";
			}
		}
		this.entityArray = entityArray;
		
		return null;}
	
	public String[][]getEntityArray(){return entityArray;}
	public boolean getAlive(){return alive;}
	
	public String getName(){return name;}	
	
	public int[] getPosition(){
		int[] position = new int[2];
		position[0] = x;
		position[1] = y;
		return position;
	}
	
	public void setMap(BasicMap map){
		this.map = map;//Just In case we're loading new floors.
	}	
	
}
