package mapRelated;

public class MapSpotCalculator {

	
	
	//Finds closest diagonal spots towards player
	public int[] closestSpotDiagonal(BasicMap map, int [] player,Entity entity , int[] newPosition)
	{
		int x = entity.getPosition()[0];
		int y = entity.getPosition()[1];
		
		int newX = newPosition[0];
		int newY = newPosition[1];
		
		int[] foundPosition;
		
		if (y > player[1])
			newY = y - BasicMap.TILESIZE;
		else
			newY = y + BasicMap.TILESIZE;
		
		if (x > player [0])
			newX = x - BasicMap.TILESIZE;
		else
			newX = x + BasicMap.TILESIZE;		
		
		if (!entity.isTaken(newX, newY) && !map.hasCollision(newX, newY))
				{
				foundPosition = new int[] {newX,newY};
				x = newX;
				y = newY;
				}
		
		else if (!entity.isTaken(x, newY) && !map.hasCollision(x, newY))
				{
			foundPosition =  new int[]{x,newY};
				y = newY;
			    }
		
		else if (!entity.isTaken(newX, y) && !map.hasCollision(newX, y))
				{
			foundPosition =  new int[] {newX, y};
				x = newX;
				}		
		
		return foundPosition;
	}

	
	
}
