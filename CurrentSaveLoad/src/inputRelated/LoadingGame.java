package inputRelated;

import gameStates.GameScreenAssets;

import java.io.*;
import java.util.LinkedList;

import playerRelated.Player;
import managers.MonsterManager;
import mapRelated.BasicMap;
import monsterRelated.Entity;

public class LoadingGame {
	
//	int readFloor;
//	int readPlayerPositionX;
//	int readPlayerPositionY;
//	int readPlayerLevel;
//	int readPlayerExp;
//	int readPlayerHealth;
//
//	int[] readMonsterXPositions = new int[100];
//	int[] readMonsterYPositions = new int[100];
//	int[] readMonsterHealths = new int[100];


	public static void initLoadingGame(GameScreenAssets gameAssets, BasicMap currentMap, LinkedList<BasicMap> totalLevels,
									   Player player, MonsterManager monster, String[][] entityArray)
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
		for (int i = readFloor-1;i != 0;i--)
		{
			currentMap = totalLevels.removeLast();
		}
		//Set player related data
		player.loadStats(readPlayerLevel, readPlayerExp, readPlayerHealth);
		player.setPosition(readPlayerPositionX, readPlayerPositionY);
		//Set monster related data
		
		
	}
	
	
//	public void readSave()
//	{
//		try
//		{
//			FileReader fr = new FileReader("save.txt");
//			BufferedReader br = new BufferedReader(fr);
//			
//			//Reading player saved data
//			String readStringFloor = br.readLine();
//			readFloor = Integer.valueOf(readStringFloor);
//			String readStringPlayerPositionX = br.readLine();
//			readPlayerPositionX = Integer.valueOf(readStringPlayerPositionX);
//			String readStringPlayerPositionY = br.readLine();
//			readPlayerPositionY = Integer.valueOf(readStringPlayerPositionY);
//			String readStringPlayerLevel = br.readLine();
//			readPlayerLevel = Integer.valueOf(readStringPlayerLevel);
//			String readStringPlayerExp = br.readLine();
//			readPlayerExp = Integer.valueOf(readStringPlayerExp);
//			String readStringPlayerHealth = br.readLine();
//			readPlayerHealth = Integer.valueOf(readStringPlayerHealth);
//			
//			//Reading monster saved data
//			String lineRead = br.readLine();
//			for (int i = 0; lineRead != null; i++)
//			{
//				if (lineRead != null)
//				{
//					String readStringMonsterXPosition = lineRead;
//					readMonsterXPositions[i] = Integer.valueOf(readStringMonsterXPosition);
//					lineRead = br.readLine();
//				}
//				if (lineRead != null)
//				{
//					String readStringMonsterYPosition = lineRead;
//					readMonsterYPositions[i] = Integer.valueOf(readStringMonsterYPosition);
//					lineRead = br.readLine();
//				}
//				if (lineRead != null)
//				{
//					String readStringMonsterHealth = lineRead;
//					readMonsterHealths[i] = Integer.valueOf(readStringMonsterHealth);
//					lineRead = br.readLine();
//				}
//			}
//			
//			br.close();
//			fr.close();
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//	}

}
