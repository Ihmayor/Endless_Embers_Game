package inputRelated;

import gameStates.GameScreenAssets;

import java.io.*;
import java.util.LinkedList;

import managers.MonsterManager;
import monsterRelated.BasicMonster;
import playerRelated.Player;

public class SavingGame {
		
	public static void SaveGame(GameScreenAssets gsa, Player p, MonsterManager mm){
		try
		{
			FileWriter fw = new FileWriter("save.txt");
			PrintWriter pw = new PrintWriter(fw);
			//First Write Floor number. Newline.
			pw.println(gsa.getFloorLevel());
			//Player position. new line
			pw.println(p.getPosition()[0]);
			pw.println(p.getPosition()[1]);
			//Player level
			pw.println(p.getCurrentLevel());
			//Player experience points. new line
			pw.println(p.getExperiencePoints());
			//Player health points. new line.
			pw.println(p.getHealthPoints());
			//Start loop for saving monster info
			LinkedList<BasicMonster> mmLL = (LinkedList<BasicMonster>) mm.getMonsterList().clone();
			while (mmLL.peekFirst() != null)
			{
				BasicMonster currentNode = mmLL.removeFirst();
				//Don't have monster type as of now
				//Write monster x-coordinate and y-coordinate
				pw.println(currentNode.getPosition()[0]);
				pw.println(currentNode.getPosition()[1]);
				//Write monster health
				pw.println(currentNode.getHealthPoints());
			}
			pw.close();
			fw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
