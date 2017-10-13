package playerRelated;

import gameStates.GameScreen;
import mapRelated.BasicMap;

public class PlayerMovement {

	
	public void moveDiagonalUpLeft(Player player,BasicMap map){
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		
		int newX = x-BasicMap.TILESIZE;
		int newY = y-BasicMap.TILESIZE;
		if (player.isTaken(newX, newY))
			player.attack(newX,newY);
		else if (!(map.hasCollision(newX, newY)))
			{
			player.updatePosition(newX,newY);
			x = newX;
			y = newY;
			
			if (map.isStairs(x, y)){
				player.setOnStairs(true);
				}
			if (map.isWin(x, y))
				{
				GameScreen.setWin(true);
				}
			}
	 }



}
