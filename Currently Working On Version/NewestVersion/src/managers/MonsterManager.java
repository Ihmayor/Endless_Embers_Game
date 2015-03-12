/////////////////////////////////////////////////////////////
//Monster Manager                                          //
//Purpose: Manage Multiple Monsters in a level		       //
//Limit: Inflexible to more than one level				   //
//Group: SENG 301 Group 16			            		   //
/////////////////////////////////////////////////////////////



package managers;

import java.util.LinkedList;

import mapRelated.BasicMap;
import monsterRelated.BasicMonster;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import playerRelated.Player;

public class MonsterManager {

	
	private Player player;
	private LinkedList<BasicMonster> monsterList = new LinkedList<BasicMonster>();
	private int level;
	private BasicMap currentMap;
	private String[][] entityArray;
	
	
	//For test purposes Only
	public MonsterManager(){
		
	}
	//For test purposes only
	
	public MonsterManager(BasicMap map){
		currentMap = map;
	}
	
	
	public String checkEntityArray(String [][] entityArray){
		if (entityArray.length*entityArray[0].length != 35*16)
			return "Entity Array Not Expected Size";
		
		for (String[] row: entityArray){
			for (String s:row)
			{
				if (s == null)
					return "EntityArray cannot have null objects";
			}
		}
		
		boolean playerFound = false;
		for(String[] row:entityArray)
		{
			for (String s: row)
			{
				if (s.equals("P"))
					playerFound = true;
			}
		}
		
		if (!playerFound)
			return "Player not in EntityArray"; 
		
		return null;
	}
	
	
	public String setEntityArray(String [][]entityArray){
		if ( checkEntityArray(entityArray) != null)
		{
			return "Invalid Entity Array";
		}
		
		
		return null;
	}
	
	
	//Put this into a private object.
	public int[] findValidPlacement(int monsterPathSize, BasicMap map, String [][] array){
		int newX = 0;
		int newY = 0;
		int[]  newPosition = {newX, newY}; 
		
		for (int i = 0; i < BasicMap.widthByTiles; i ++){
			for (int c = 0; c < BasicMap.heightByTiles; c++){
				if (checkValidPlacement(newPosition, monsterPathSize, map, array) == null){
					return newPosition;}
				
				
				newPosition[0] = i*BasicMap.TILESIZE;
				newPosition[1] = c*BasicMap.TILESIZE;
				}
			}
		
		return null;
	}
	
	 
	
	public String checkValidPlacement(int[]newPosition, int monsterPathSize, BasicMap map, String [][] array){
		Boolean allClear = true;
		int checkX = newPosition[0];
		int checkY = newPosition[1];
		for (int i = 0;i < monsterPathSize; i++)
		{
			if (checkX > 1120 || checkY > 512)
				return "Out of Bounds";
			if (array[checkX/BasicMap.TILESIZE][checkY/BasicMap.TILESIZE] != " "){
				allClear = false;
				return "Entity Overlap";
				}
			if (map.hasCollision(checkX, checkY))
				{
				allClear = false;
				return "Map Overlap";//return map overlap?
				}
			checkX += BasicMap.TILESIZE;
		}
		
		if (allClear)
			return null;
		else
			return "Invalid Spot";
	}
	
	
	
	
	public void init(String [][] entityArray) throws SlickException{
		if (checkEntityArray(entityArray) !=null)
			return;
		
		SpriteSheet monsterSheet = new SpriteSheet("res/monster/dummySheet.png",32,32); 
		Image monsterImage = monsterSheet.getSubImage(0, 0);
		BasicMonster monster1 = new BasicMonster(currentMap, monsterImage, 7*32, 11*32);
		BasicMonster monster2 = new BasicMonster(currentMap, monsterImage, 17*32, 3*32);
		BasicMonster monster3 = new BasicMonster(currentMap, monsterImage, 22*32, 11*32);
		monster2.setPath(17*32, 20*32);
		monster3.setPath(22*32, 24*32);
		
		monsterList.add(monster1);
		monsterList.add(monster2);
		monsterList.add(monster3);
		
		BasicMonster [] monsters = monsterList.toArray(new BasicMonster [monsterList.size()]);
		for (BasicMonster m:monsters)
		{
			entityArray[m.getPosition()[0]/BasicMap.TILESIZE][m.getPosition()[1]/BasicMap.TILESIZE] = m.getName();
		}
		
		this.entityArray = entityArray;
		
		for (BasicMonster m: monsters){
			m.setEntityArray(entityArray);
			}
	}
	
	public void render(Graphics g) throws SlickException{
		
		BasicMonster [] monsters = monsterList.toArray(new BasicMonster [monsterList.size()]);
		for (BasicMonster m: monsters){
			m.render(g);
			}
		
	}
	
	//Calls update method for every monster inside list
	public void update(int[] playerPosition, int counter){

		BasicMonster [] monsters = monsterList.toArray(new BasicMonster [monsterList.size()]);
		for (BasicMonster m: monsters){
			m.update(playerPosition, counter);
			}
	}
	
	
	//Returns the entityArray with the monsters placed on them
	public String [][] getEntityArray(){return entityArray;}	
		
	//Returns the linked list of monsters
	public LinkedList<BasicMonster> getMonsterList() {return monsterList;}
	
	
	
	//////Method to change level and change the amount of monsters.
	public void setLevel(int currentLevel, BasicMap map){
		//Current level = #ofmonsters*(level/2)
	}
	
	//Calls the setMap function for all the monsters inside the list
	public void setMap(BasicMap newMap){

		BasicMonster [] monsters = monsterList.toArray(new BasicMonster [monsterList.size()]);
		for (BasicMonster m: monsters){
			m.setMap(newMap);
			}
		
	}
	
	
	
}
