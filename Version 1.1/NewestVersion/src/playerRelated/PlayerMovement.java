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
	

	public void moveUp(Player player,BasicMap map){
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		
		int newY = y - BasicMap.TILESIZE;
		
		if (player.isTaken(x,newY))
			player.attack(x, newY);
		else if (!(map.hasCollision(x, newY))){
			player.updatePosition(x,newY);
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
	
	public void moveDiagonalUpRight(Player player, BasicMap map){
		
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		
		int newX = x + BasicMap.TILESIZE;
		int newY = y - BasicMap.TILESIZE;
		
		if (player.isTaken(newX, newY))
			player.attack(newX, newY);
		else if (!(map.hasCollision(newX, newY)))	
			{
				player.updatePosition(newX,newY);
				y = newY;
				x = newX;	
				if (map.isStairs(x, y)){
					player.setOnStairs(true);
					}
				if (map.isWin(x, y))
				{
				GameScreen.setWin(true);
				}
			
			}
	}
	
	public void moveLeft(Player player, BasicMap map){	
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
	
		int newX = x-BasicMap.TILESIZE;
		if (player.isTaken(newX, y))
			player.attack(newX,y);
		else if (!(map.hasCollision(newX, y))){
			player.updatePosition(newX,y);
			x = newX;
			if (map.isStairs(x, y)){
				player.setOnStairs(true);
						}
			if (map.isWin(x, y))
			{
			GameScreen.setWin(true);
			}
		
		}
		
	}


	public void moveNowhere(Player player, BasicMap map)
	{
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		if (map.isStairs(x, y)){
			player.setOnStairs(true);
		}
		if (map.isWin(x, y))
		{
			GameScreen.setWin(true);
		}

	}
	
	public void moveRight(Player player, BasicMap map){
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		int newX = x + BasicMap.TILESIZE;
		if  (player.isTaken(newX, y))
			player.attack(newX,y);
		else if (!(map.hasCollision(newX, y))){
				player.updatePosition(newX,y);
			x = newX;
			if (map.isStairs(x, y)){
				player.setOnStairs(true);
						}
			if (map.isWin(x, y))
			{
			GameScreen.setWin(true);
			}
		
			}
		}	
	
	
	public void moveDiagonalDownLeft(Player player, BasicMap map){
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		int newX = x-BasicMap.TILESIZE;
		int newY = y +BasicMap.TILESIZE;
		if (player.isTaken(newX, newY))
			player.attack(newX, newY);
		else if (!(map.hasCollision(newX,  newY)))
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
		
public void moveDown(Player player, BasicMap map){
	int x = player.getPosition()[0];
	int y = player.getPosition()[1];
    int newY = y +BasicMap.TILESIZE;
	if (player.isTaken(x, newY))
		player.attack (x, newY);
	else if (!(map.hasCollision(x, newY))){
		player.updatePosition(x,newY);
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

public void moveDiagonalDownRight(Player player, BasicMap map){
	int x = player.getPosition()[0];
	int y = player.getPosition()[1];
	int newX = x+BasicMap.TILESIZE;
	int newY = y+BasicMap.TILESIZE;
		if (player.isTaken(newX, newY)){
			player.attack(newX, newY);
		}
		else if (!(map.hasCollision(newX,newY)))
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
