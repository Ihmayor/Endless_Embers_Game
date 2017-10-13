package inputRelated;

import gameStates.GameScreenAssets;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import managers.MonsterManager;
import monsterRelated.BasicMonster;
import playerRelated.Player;
import playerRelated.PlayerStatus;

public class SavingGame {
		
	public static void SaveGame(GameScreenAssets gsa, Player player, MonsterManager monsterManage){
		try
		{
			PlayerStatus playerStatus = player.getPlayerStatus();
			FileWriter fw = new FileWriter("save.txt");
			PrintWriter pw = new PrintWriter(fw);
			//First Write Floor number. Newline.
			pw.println(gsa.getFloorLevel());
			//Player position. new line
			pw.println(player.getPosition()[0]);
			pw.println(player.getPosition()[1]);
			//Player level
			pw.println(playerStatus.getPlayerLevel());
			//Player experience points. new line
			pw.println(playerStatus.getExperiencePoints());
			//Player health points. new line.
			pw.println(player.getHealthPoints());
			//Start loop for saving monster info
			LinkedList<BasicMonster> monsterLinkedList = (LinkedList<BasicMonster>) monsterManage.getMonsterList().clone();
			while (monsterLinkedList.peekFirst() != null)
			{
				BasicMonster currentNode = monsterLinkedList.removeFirst();
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
