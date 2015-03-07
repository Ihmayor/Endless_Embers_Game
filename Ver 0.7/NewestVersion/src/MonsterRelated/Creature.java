package MonsterRelated;

import mapRelated.BasicMap;

public class Creature {
	
	protected int oldx=0, oldy=0;
	protected int x,y;
	protected String [][] entityArray;
	protected String name;
	
	protected int healthPoints;
	protected int experiencePoints;
	
	
	
	public Creature (int x,int y){
		this.x = x;
		this.y = y;
	}
	public String[][]getEntityArray(){return entityArray;}
	public String getName(){return name;}

	public void setEntityArray(String[][] entityArray){this.entityArray = entityArray;}
	
	
	public int[] getPosition(){
		int[] position = new int[2];
		position[0] = x;
		position[1] = y;
		return position;
	}
	
	protected void updatePosition(int x, int y){
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
	

	protected boolean search(String name){
			boolean found = false;
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
	
	
}
