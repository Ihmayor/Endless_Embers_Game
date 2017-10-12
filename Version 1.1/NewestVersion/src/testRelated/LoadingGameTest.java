package testRelated;

import static org.junit.Assert.assertNotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import gameStates.GameScreenAssets;
import inputRelated.LoadingGame;
import managers.MonsterManager;
import mapRelated.BasicMap;
import playerRelated.Player;

class LoadingGameTest {

	@Before
	void writeSaveFile() throws IOException {
		FileWriter fw = new FileWriter("save.txt");
		PrintWriter pw = new PrintWriter(fw);
		//First Write Floor number. Newline.
		pw.println(4);
		//Player position. new line
		pw.println(12);
		pw.println(10);
		//Player level
		pw.println(2);
		//Player experience points. new line
		pw.println(12);
		//Player health points. new line.
		pw.println(10);
		
		//Start loop for saving monster info
	    //Write monster x-coordinate and y-coordinate
	    pw.println(2);
	    pw.println(6);
	    //Write monster health
	    pw.println(1);
		pw.close();
		fw.close();
	}
	
	
	@Test
	void testNotNullLoadedMap() throws SlickException{
	
		//Arrange
		BasicMap mapToTest = new BasicMap();
		GameScreenAssets gameAssets = new GameScreenAssets();
		LinkedList<BasicMap> totalLevels = new LinkedList<BasicMap>();
		MonsterManager monsters = new MonsterManager(mapToTest);
		Player player = new Player(4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE, mapToTest);		
		String[][] entityArray =  new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		
		//Act 
		mapToTest = LoadingGame.initLoadingGame(gameAssets, mapToTest, totalLevels, player, monsters, entityArray);
		
		
		//Assert
		assertNotNull("Failed to load Map", mapToTest);
	}

}
