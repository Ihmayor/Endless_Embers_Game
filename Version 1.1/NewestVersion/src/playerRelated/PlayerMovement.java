package playerRelated;

import org.newdawn.slick.Input;

import gameStates.GameScreen;
import mapRelated.BasicMap;

public class PlayerMovement {

	
	public void movePlayer(Player player, BasicMap map, Input input)
	{
		//Diagonal Up Left
		if (input.isKeyPressed(Input.KEY_NUMPAD7)||input.isKeyPressed(Input.KEY_7)){
			 moveDiagonalUpLeft(player,map);
		 }
		
		//Normal Up
		else if (input.isKeyPressed(Input.KEY_UP)||input.isKeyPressed(Input.KEY_8)
				||input.isKeyPressed(Input.KEY_NUMPAD8)){
				moveUp(player,map);
		}
		
		//Diagonal Up Right
		else if (input.isKeyPressed(Input.KEY_NUMPAD9)||input.isKeyPressed(Input.KEY_9)){
				moveDiagonalUpRight(player,map);
		}

		//Normal Left
		else if (input.isKeyPressed(Input.KEY_LEFT)||input.isKeyPressed(Input.KEY_U)
				||input.isKeyPressed(Input.KEY_NUMPAD4)){
				moveLeft(player,map);
		}
		
		//PASS TURN
		else if (input.isKeyPressed(Input.KEY_NUMPAD5)||input.isKeyPressed(Input.KEY_I))
			{
			moveNowhere(player,map);
			}
		
		//Normal Right
		else if (input.isKeyPressed(Input.KEY_RIGHT)||input.isKeyPressed(Input.KEY_O)||
				input.isKeyPressed(Input.KEY_NUMPAD6)){
			moveRight(player,map);
			}
		
		//Diagonal Down Left
		else if (input.isKeyPressed(Input.KEY_NUMPAD1)||input.isKeyPressed(Input.KEY_J)){
				moveDiagonalDownLeft(player,map);
				}
		
		//Normal Down
		else if (input.isKeyPressed(Input.KEY_DOWN)||input.isKeyPressed(Input.KEY_K)||
				input.isKeyPressed(Input.KEY_NUMPAD2)){
			moveDown(player,map);
		}

		//Diagonal Down Right
		else if (input.isKeyPressed(Input.KEY_NUMPAD3)||input.isKeyPressed(Input.KEY_L)){
			moveDiagonalDownRight(player,map);
		}

	}
	
	public void moveDiagonalUpLeft(Player player,BasicMap map){
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		
		int newX = x-BasicMap.TILESIZE;
		int newY = y-BasicMap.TILESIZE;
		movePlayerToPos(newX, newY,player,map);
	 }
	
	private void movePlayerToPos(int x, int y, Player player, BasicMap map)
	{
		if (player.isTaken(x,y))
			player.attack(x, y);
		else if (!(map.hasCollision(x, y))){
			player.updatePosition(x,y);
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
		
		movePlayerToPos(x,newY, player,map);		
	}
	
	public void moveDiagonalUpRight(Player player, BasicMap map){
		
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		
		int newX = x + BasicMap.TILESIZE;
		int newY = y - BasicMap.TILESIZE;
		
		movePlayerToPos(newX, newY, player,map);
	}
	
	public void moveLeft(Player player, BasicMap map){	
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
	
		int newX = x-BasicMap.TILESIZE;
		movePlayerToPos(newX, y, player,map);
		
	}


	public void moveNowhere(Player player, BasicMap map)
	{
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		movePlayerToPos(x, y, player,map);
	}
	
	public void moveRight(Player player, BasicMap map){
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		int newX = x + BasicMap.TILESIZE;
		movePlayerToPos(newX, y, player,map);
	}	
	
	
	public void moveDiagonalDownLeft(Player player, BasicMap map){
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		int newX = x-BasicMap.TILESIZE;
		int newY = y +BasicMap.TILESIZE;
		movePlayerToPos(newX, newY, player,map);
	}
		
	public void moveDown(Player player, BasicMap map){
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		int newY = y +BasicMap.TILESIZE;
		movePlayerToPos(x, newY, player,map);
	}

	public void moveDiagonalDownRight(Player player, BasicMap map){
		int x = player.getPosition()[0];
		int y = player.getPosition()[1];
		int newX = x+BasicMap.TILESIZE;
		int newY = y+BasicMap.TILESIZE;
		movePlayerToPos(newX, newY, player,map);
	}



}
