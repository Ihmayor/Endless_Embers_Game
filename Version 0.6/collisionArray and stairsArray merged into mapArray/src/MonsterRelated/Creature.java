package MonsterRelated;

import mapRelated.BasicMap;

public class Creature {
	
	protected int x,y;
	protected String [][] entityArray;
	protected String name;
	
	public void setEntityArray(String[][] entityArray){this.entityArray = entityArray;}
	
	protected boolean search(String name){
			boolean found = false;
			for (int row = (x/32-2); row < (x/32+2); row++)
			{
				for (int column = (y/32)-2; column < y/32+2; column++)
				{
					if (row < 0 || row >= 32 || column <0 || column >= 16)
						break;
					if (entityArray[row][column]!= null)
					{
						if (entityArray[row][column].equals(name))
						{
							found = true;
							break;
						}
						else
							break;
					}
				}
			}
			return found;
	}
	
	
	public int[] getPosition(){
		int[] position = new int[2];
		position[0] = x;
		position[1] = y;
		return position;
	}

	protected boolean isTaken(int x, int y){
		boolean isTaken = false;
		if (entityArray[x][y] != " ")
			isTaken = true;
		return isTaken;
	}
	
	
	public void setPosition(int [] otherPosition, String otherName){
		String [][] newArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		entityArray = newArray;
		for (int i = 0; i < BasicMap.widthByTiles-1; i++)
			for (int c =0; c < BasicMap.heightByTiles-1; i++)
			{
				entityArray[i][i] = " ";
			}
		newArray[x][y] = name; 
		newArray[otherPosition[0]][otherPosition[1]] = otherName;
		newArray = entityArray;
	}
	

}
