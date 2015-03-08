package combatRelated;

import gameStates.Game;

import java.util.LinkedList;
import java.util.Random;

import monsterRelated.Creature;

public class CombatManager {
	
	public static boolean battleHappening = false;
	
	private static LinkedList <Creature> monsterList = new LinkedList<Creature>();
	
	public static void setMonsterList(LinkedList <Creature> monsters){monsterList = monsters;}
	
	
	public static void attackLoop(Creature c, int criticalHitLimit, int missFactor, int monsterX, int monsterY ){
		battleHappening = true;
		if (getMonsterRef(monsterX, monsterY) == null)
		{
			Game.statusUpdate = "You are attacking a ghost! Oh no! Run away before it gets you!";
			battleHappening = false;
		}
		else
		{
			Creature currentFoe = getMonsterRef(monsterX,monsterY);
			int attack = generateAttack(criticalHitLimit);
			if (attack <= missFactor){
			Game.statusUpdate = "You miss. yeah...The monster was embarrased by your fail too...";
			}
			
			else if (attack > missFactor && attack <= criticalHitLimit/2)
			{
				
			Game.statusUpdate = "Average Hit: "+attack;
			}
			
			else 
			{
			Game.statusUpdate = "Critical Hit against monster! Wow! Much Strength! Such Cool!"+attack;	
			}
					
		}
	}
	
	private static int generateAttack(int criticalHitLimit){
		Random gen = new Random();
		int attack = gen.nextInt(criticalHitLimit);
		return attack;
	}
	
	//Might have to make public for testing.
	private static Creature getMonsterRef(int x, int y){
		Creature monsterFound = null;
		int [] monsterPosition = new int [2];
		for (int i = 0; i < monsterList.size(); i++)
			{
			monsterPosition = monsterList.get(i).getPosition();
			if (monsterPosition[0] == x && monsterPosition[1] == y)
				{
				monsterFound = monsterList.get(i);
				}
			}
		return monsterFound;
	}

}
