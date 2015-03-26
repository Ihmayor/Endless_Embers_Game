
package managers;

import java.util.LinkedList;
import java.util.Random;

import mapRelated.BasicMap;
import monsterRelated.BasicMonster;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/////////////////////////////////////////////////////////////
//Monster Manager                                          //
//Purpose: Manage Multiple Monsters in a level		       //
//Limit: Currently only handles one type of monster.       //
//Features: Spawns Monsters 2*Level. Save for Level 7 	   //
/////////////////////////////////////////////////////////////

public class MonsterManager {

	
	private LinkedList<BasicMonster> monsterList = new LinkedList<BasicMonster>();
	private int level = 1;
	private BasicMap currentMap;
	private String[][] entityArray;
	
	
	//Monster Type1
	//Might Rename as "Soldiers" or "Guards"
	//Because they will march between two points like guards on patrol.
	private Image basicMonsterImage;
	private SpriteSheet basicMonsterSheet;
	private Animation basicMonsterAnimation;//Will bob monsters up and down some day
	
	
	
	
	//For test purposes Only
	public MonsterManager(){
		
	}
	//For test purposes only
	
	//Sets Map for Monster Manager
	public MonsterManager(BasicMap map){
		currentMap = map;
	}
	
	//Checks if Valid Entity Array
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
	
	
	//Sets entityArray for later monster spawning
	public String setEntityArray(String [][]entityArray){
		if ( checkEntityArray(entityArray) != null)
		{
			return "Invalid Entity Array";
		}
		this.entityArray = entityArray;
		return null;
	}
	
	//To do later: Put this into a private object and change this method to a private method.
	public int[] findValidPlacement(int monsterPathSize, BasicMap map, String [][] array){
		Random gen = new Random();
		int newX = gen.nextInt(35)*BasicMap.TILESIZE;
		int newY = gen.nextInt(16)*BasicMap.TILESIZE;
		int[]  newPosition = {newX, newY};
		
		for (int i = 0; i < BasicMap.widthByTiles; i ++){
			for (int c = 0; c < BasicMap.heightByTiles; c++){
				if (checkValidPlacement(newPosition, monsterPathSize, map, array) == null){
					return newPosition;
					}
				newPosition[0] = (newX+i*BasicMap.TILESIZE)%(1120);
				newPosition[1] = (newY+c*BasicMap.TILESIZE)%(512);
				}

			}
		return null;
	}
	
	//Checks if spawned at a valid spot
	public String checkValidPlacement(int[]newPosition, int monsterPathSize, BasicMap map, String [][] array){
		Boolean allClear = true;
		int checkX = newPosition[0];
		int checkY = newPosition[1];
		for (int i = 0;i < monsterPathSize; i++)
		{
			if (checkX >= (1120) || checkY >= (512) || checkX < 0 || checkY < 0)
				return "Out of Bounds";
			else if (array[checkX/BasicMap.TILESIZE][checkY/BasicMap.TILESIZE] != " "){
				allClear = false;
				return "Entity Overlap";
				}
			else if (map.hasCollision(checkX, checkY))
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
	
	
	// Loads monster Images 
	private void loadMonsterTypes() throws SlickException{
		basicMonsterSheet= new SpriteSheet("res/monster/dummySheet.png",32,32); 
		basicMonsterImage = basicMonsterSheet.getSubImage(0, 0);
		Image [] monsterAnim = {basicMonsterSheet.getSubImage(0, 0), basicMonsterSheet.getSubImage(1, 0)};
		int [] duration = {250,250};
		basicMonsterAnimation = new Animation(monsterAnim, duration, false);
	}
	
	//Initializes the amount of monsters per level
	public void init(String [][] entityArray, BasicMap currentMap) throws SlickException{
		if (checkEntityArray(entityArray) !=null)
			return;
		loadMonsterTypes();
		this.entityArray = entityArray;
		BasicMonster monster1 = null;
		int pathSize = 4;
		
		//Adjusts path size for more confined maps.
		if (level >= 3)
			pathSize = 2;
		
		//Spawns number of monsters according to level
		if (level < 7)
		{
			for (int i = 0; i < level*2; i++){
				int[] spawnPosition = findValidPlacement (pathSize, currentMap, entityArray);
				while (spawnPosition == null)
					spawnPosition = findValidPlacement (pathSize, currentMap, entityArray);
			
				monster1 = new BasicMonster(currentMap, basicMonsterAnimation, basicMonsterImage, spawnPosition[0], spawnPosition[1]);
				monsterList.add(monster1);
				monster1.setPath(spawnPosition[0], spawnPosition[0]+3*BasicMap.TILESIZE);
				entityArray[monster1.getPosition()[0]/BasicMap.TILESIZE]
				     	   [monster1.getPosition()[1]/BasicMap.TILESIZE] = monster1.getName();
			}
		}
		
		//Special Treatment for Level 7
		else
		{
			for (int i = 0; i < 7; i++){
				int[] spawnPosition = findValidPlacement (pathSize, currentMap, entityArray);
				while (spawnPosition == null)
					spawnPosition = findValidPlacement (pathSize, currentMap, entityArray);
			
				monster1 = new BasicMonster(currentMap,basicMonsterAnimation, basicMonsterImage, spawnPosition[0], spawnPosition[1]);
				monsterList.add(monster1);
				monster1.setPath(spawnPosition[0], spawnPosition[0]+3*BasicMap.TILESIZE);
				entityArray[monster1.getPosition()[0]/BasicMap.TILESIZE]
				     	   [monster1.getPosition()[1]/BasicMap.TILESIZE] = monster1.getName();
			}
		
		}
		
		//Sets up monster methods
		BasicMonster [] monsters = monsterList.toArray(new BasicMonster [monsterList.size()]);
		for (BasicMonster m: monsters){
			m.setEntityArray(entityArray);
			m.setMonsterMaxHealth(level*10);
			m.setHealthPoints(level*10);
			if (level == 1)//Special Level 1 treatment
				m.damageLimit = 4;
			else
				m.damageLimit = 20+level*2;
			m.setMap(currentMap);
			}
		
	}
	
	//Calls render method for every monster inside the list.
	public void render(Graphics g) throws SlickException{
		
		BasicMonster [] monsters = monsterList.toArray(new BasicMonster [monsterList.size()]);
		for (BasicMonster m: monsters){
			m.render(g);
			}
		
	}

	
	public void loadMonsterList(int[] newMonsterXPositions, int[] newMonsterYPositions, 
								int[] newMonsterHealths, String[][] newEntityArray, BasicMap newMap, int newLevel)
	throws SlickException
	{
		this.level = newLevel;
		loadMonsterTypes();
		currentMap = newMap;
		monsterList = new LinkedList<BasicMonster>();
		BasicMonster monster;		

		int numOfMonsters = newMonsterXPositions.length;
		for (int i = 0;i < numOfMonsters;i++)
		{
			if (newMonsterXPositions[i] == 0 && newMonsterYPositions[i] == 0)
				break;
			monster = new BasicMonster(currentMap, basicMonsterAnimation, basicMonsterImage, newMonsterXPositions[i], newMonsterYPositions[i]);
			monster.setHealthPoints(newMonsterHealths[i]);
			monster.setPath(newMonsterXPositions[i], newMonsterXPositions[i]+3*BasicMap.TILESIZE);
			newEntityArray[monster.getPosition()[0]/BasicMap.TILESIZE]
			     	   [monster.getPosition()[1]/BasicMap.TILESIZE] = monster.getName();
			monsterList.add(monster);
		}
		
		BasicMonster [] monsters = monsterList.toArray(new BasicMonster [monsterList.size()]);
		for (BasicMonster m: monsters){
			m.setEntityArray(newEntityArray);
			m.setMonsterMaxHealth(level*20);
			m.damageLimit = level*2;
			m.setMap(currentMap);
			if (level == 1)//Special Level 1 treatment
				m.damageLimit = 4;
			else
				m.damageLimit = 20+level*2;
		}
		this.entityArray = newEntityArray;
		
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
	
	
	//Method to change level and change the amount of monsters.
	public void increaseFloorLevel(){level++;}
	
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
			return "Error! You accidentally cleared the player";//"Technically this checks if you've created an array
		}														//Of proper size, no nulls AND a player inside it.
		monsterList.clear();
		
		return null;
	}	
}
