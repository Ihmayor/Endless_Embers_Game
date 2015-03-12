/////////////////////////////////////////////////////////////
//Monster Manager                                          //
//Purpose: Manage Multiple Monsters in a level		       //
//Limit: Inflexible to more than one level				   //
//Group: SENG 301 Group 16			            		   //
/////////////////////////////////////////////////////////////



package managers;

import java.util.LinkedList;
import java.util.Random;

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
	
	
	//Monster Type1
	//Might Rename as "Soldiers" or "Guards"
	//Because they will march between two points like guards on patrol.
	private Image basicMonsterImage;
	private SpriteSheet basicMonsterSheet;
	
	//Monster Type2
	//Will name wanderers
	//Since they will freely wander the entire screen
	private Image wanderImage;
	private Image wanderImage2;
	
	//Monster Type3
	//Will name hives
	//Since they will think collectively and 'swarm' the player
	private Image hiveImage;
	
	
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
		this.entityArray = entityArray;
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
					return newPosition;
					}
				newPosition[0] = i*BasicMap.TILESIZE*2;
				newPosition[1] = c*BasicMap.TILESIZE*3;
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
				return "Map Overlap";
				}
			
			checkX += BasicMap.TILESIZE;
		}

		if (allClear){
			return null;
		}
		else{
			return "Invalid Spot";
		}

	}
	
	private void loadMonsterTypes() throws SlickException{
		basicMonsterSheet= new SpriteSheet("res/monster/dummySheet.png",32,32); 
		basicMonsterImage = basicMonsterSheet.getSubImage(0, 0);
		
	}
	
	
	public void init(String [][] entityArray, BasicMap currentMap) throws SlickException{
		if (checkEntityArray(entityArray) !=null)
			return;
		this.entityArray = entityArray;
		loadMonsterTypes();

		BasicMonster monster1 = null;
		BasicMonster monster2 = null;
		BasicMonster monster3 = null;
		BasicMonster monster4 = null;
		BasicMonster monster5 = null;
		
		
		int[] spawnPosition = findValidPlacement (2, currentMap, entityArray);
		
		if (spawnPosition != null){
			monster1 = new BasicMonster(currentMap, basicMonsterImage, spawnPosition[0], spawnPosition[1]);
		monsterList.add(monster1);
		monster1.setPath(spawnPosition[0], spawnPosition[0]+3*BasicMap.TILESIZE);
		entityArray[monster1.getPosition()[0]/BasicMap.TILESIZE]
				   [monster1.getPosition()[1]/BasicMap.TILESIZE] = monster1.getName();
		}
		spawnPosition = findValidPlacement (5, currentMap, entityArray);
		
		if (spawnPosition != null){
			monster2 = new BasicMonster(currentMap, basicMonsterImage, spawnPosition[0], spawnPosition[1]);
			monsterList.add(monster2);
			entityArray[monster2.getPosition()[0]/BasicMap.TILESIZE]
				   [monster2.getPosition()[1]/BasicMap.TILESIZE] = monster2.getName();
			monster2.setPath(spawnPosition[0], spawnPosition[0]+3*BasicMap.TILESIZE);
		}
		spawnPosition = findValidPlacement (2, currentMap, entityArray);
		
		if (spawnPosition != null){
			monster3 = new BasicMonster(currentMap, basicMonsterImage, spawnPosition[0], spawnPosition[1]);
		monsterList.add(monster3);
		entityArray[monster1.getPosition()[0]/BasicMap.TILESIZE]
				   [monster1.getPosition()[1]/BasicMap.TILESIZE] = monster1.getName();
		monster3.setPath(spawnPosition[0], spawnPosition[0]+3*BasicMap.TILESIZE);}
		
	
		BasicMonster [] monsters = monsterList.toArray(new BasicMonster [monsterList.size()]);
		for (BasicMonster m: monsters){
			m.setEntityArray(entityArray);
			}
		setMap(currentMap);
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
	
	//Clears the monsters for the next Level
	public String clearMonsters(){
		for (int i = 0; i < BasicMap.widthByTiles; i++){
			for (int c = 0; c <BasicMap.heightByTiles; c ++){
				if (entityArray[i][c] != "P")
					entityArray[i][c] = " ";
			}
		}
		if (checkEntityArray (entityArray) != null){
			return "Error! You accidentally cleared the player";
		}
		while (!monsterList.isEmpty()) {
	        monsterList.removeFirst();
	    }
		return null;
	}
	
	
	
}
