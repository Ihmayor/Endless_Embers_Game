package testRelated;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import managers.CombatManager;
import mapRelated.BasicMap;
import monsterRelated.BasicMonster;

import org.junit.Test;

import playerRelated.Player;

public class CombatManagerTest {

	
	//Tests when no monster reference is found for combat
	@Test
	public void testMonsterRefNotFound(){
		//Arrange
		Player p = new Player(4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE);
		//Act and Assert
		assertEquals("Error! No Monster Found to Fight",CombatManager.attackLoop(p, 400, 10, 1, 2));
	}
	
	//Tests that monster references can be found for combat
	@Test
	public void testMonsterRefFound(){
		//Arrange
		Player p = new Player(4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE);
		LinkedList<BasicMonster> testMonsterList = new LinkedList<BasicMonster>();
		BasicMap map = new BasicMap();
		BasicMonster m = new BasicMonster(map, 3*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, 4000);
		testMonsterList.add(m);
		CombatManager.setMonsterList(testMonsterList);
		
		//Act and assert
		assertEquals(null, CombatManager.attackLoop(p, 300,10, 3*BasicMap.TILESIZE, 5*BasicMap.TILESIZE));
	}
}
