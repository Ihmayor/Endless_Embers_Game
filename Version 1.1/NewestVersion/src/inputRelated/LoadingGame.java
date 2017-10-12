package inputRelated;

import gameStates.GameScreenAssets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import managers.CombatManager;
import managers.MonsterManager;
import mapRelated.BasicMap;
import monsterRelated.Entity;

import org.newdawn.slick.SlickException;

import playerRelated.Player;

public class LoadingGame {
	
	public static BasicMap initLoadingGame(GameScreenAssets gameAssets, BasicMap changeMap, LinkedList<BasicMap> xTotalLevels,
			   Player player, MonsterManager monsters, String[][] entityArray)
			   throws SlickException
	{	

		int readFloor = 0;
		int readPlayerPositionX = 0;
		int readPlayerPositionY = 0;
		int readPlayerLevel = 0;
		int readPlayerExp = 0;
		int readPlayerHealth = 0;
		int[] readMonsterXPositions = new int[50];
		int[] readMonsterYPositions = new int[50];
		int[] readMonsterHealths = new int[50];

		/*READ FROM SAVE FILE*/
		try	
		{
			FileReader fr = new FileReader("save.txt");
			BufferedReader br = new BufferedReader(fr);

			//Reading player saved data
			LoadPlayerData(br, readFloor, readPlayerPositionX, readPlayerPositionY, readPlayerLevel, readPlayerExp, readPlayerHealth);
		
			//Reading monster saved data
			String lineRead = br.readLine();
			for (int i = 0; lineRead != null; i++)
				{
					if (lineRead != null)
					{
					String readStringMonsterXPosition = lineRead;
					readMonsterXPositions[i] = Integer.valueOf(readStringMonsterXPosition);
					lineRead = br.readLine();
					}
					
					if (lineRead != null)
					{
						String readStringMonsterYPosition = lineRead;
						readMonsterYPositions[i] = Integer.valueOf(readStringMonsterYPosition);
						lineRead = br.readLine();
					}
					if (lineRead != null)
					{
					String readStringMonsterHealth = lineRead;
					readMonsterHealths[i] = Integer.valueOf(readStringMonsterHealth);
					lineRead = br.readLine();
					}
				
				}

			br.close();
			fr.close();
			}
		catch (IOException e)
		{
			e.printStackTrace();
		}	

		//Set floor level
		gameAssets.setFloorLevel(readFloor);
		//Set current map
		if (xTotalLevels.toArray().length>0) {
			for (int i = readFloor - 1;i != 0;i--)
			{
				changeMap = xTotalLevels.removeLast();
			}			
		}
		//Set player related data
		player.loadStats(readPlayerLevel, readPlayerExp, readPlayerHealth);
		player.setPosition(readPlayerPositionX, readPlayerPositionY);
		player.setMap(changeMap);
		entityArray[((Entity)player).getPosition()[0]/32][((Entity)player).getPosition()[1]/32] = player.getName();
		//Set monster related data
		monsters.loadMonsterList(readMonsterXPositions, readMonsterYPositions, readMonsterHealths, entityArray, changeMap, readFloor);
		CombatManager.setMonsterList(monsters.getMonsterList());

		player.setEntityArray(entityArray);		
		return changeMap;
	}

	private static void LoadPlayerData(BufferedReader br, int readFloor, int readPlayerPositionX, int readPlayerPositionY, int readPlayerExp, int readPlayerLevel, int readPlayerHealth) throws IOException {
		//Reading player saved data
		String readStringFloor = br.readLine();
		readFloor = Integer.valueOf(readStringFloor);
		String readStringPlayerPositionX = br.readLine();
		readPlayerPositionX = Integer.valueOf(readStringPlayerPositionX);
		String readStringPlayerPositionY = br.readLine();
		readPlayerPositionY = Integer.valueOf(readStringPlayerPositionY);
		String readStringPlayerLevel = br.readLine();
		readPlayerLevel = Integer.valueOf(readStringPlayerLevel);
		String readStringPlayerExp = br.readLine();
		readPlayerExp = Integer.valueOf(readStringPlayerExp);
		String readStringPlayerHealth = br.readLine();
		readPlayerHealth = Integer.valueOf(readStringPlayerHealth);
	}


}

