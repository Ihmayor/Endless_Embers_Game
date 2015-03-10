/////////////////////////////////////////////////////////////
//Monster Manager                                          //
//Purpose: Manage Multiple Monsters in a level		       //
//Limit: Inflexible to more than one level				   //
//Author: I.M  Group: SENG 301 Group 16					   //
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
	
	public MonsterManager(BasicMap map){
		currentMap = map;
	}
	
	public void init(String [][] entityArray) throws SlickException{
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
	
	public void update(int[] playerPosition, int counter){

		BasicMonster [] monsters = monsterList.toArray(new BasicMonster [monsterList.size()]);
		for (BasicMonster m: monsters){
			m.update(playerPosition, counter);
			}
	}
	
	
	
	public String [][] getEntityArray(){return entityArray;}	
		
	
	public LinkedList<BasicMonster> getMonsterList() {return monsterList;}
	
	
	
	//////Method to change level and change the amount of monsters.
	public void setLevel(int currentLevel, BasicMap map){
		//Current level = #ofmonsters*(level/2)
	}
	public void setMap(BasicMap newMap){

		BasicMonster [] monsters = monsterList.toArray(new BasicMonster [monsterList.size()]);
		for (BasicMonster m: monsters){
			m.setMap(newMap);
			}
		
	}
	
	
	
}
