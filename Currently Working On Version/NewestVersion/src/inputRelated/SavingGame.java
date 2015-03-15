package inputRelated;

import java.util.LinkedList;

import monsterRelated.BasicMonster;
import playerRelated.Player;

public class SavingGame {
	
	BasicMonster [] monsters;
	
	
	public SavingGame(LinkedList <BasicMonster> monsterList, Player player, int floorLevel){
		//Spawn Monsters in the same location they originally were.
		//So save their positions. Their health too.
		//Line will read monsterType, X, Y, health
		//Get Player position, exp points, health-points. 
		//Save floor level.
	}
	
	
	
	public static void SaveGame(){
		//First Write Floor number. Newline.
		//Player position. new line
		//Player exp points. new line
		//Player health points. new line.
		//Write === to signify stop and to read new data
		//Write monster type
		//Write monster x-coordinate
		//Write monster y-coordinate
		//Write monster health.
		//Repeat process until linked list is empty.
		//Write another *** to stop reading.
	}

}
