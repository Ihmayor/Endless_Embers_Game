/////////////////////////////////////////////////////////////
//Combat  Manager                                          //
//Purpose: Deal with the combat inside the game		       //
//Limit: Monsters will only attack if players attack       // 
//Monsters will freeze movement if one monster is being    //
//attacked.  											   //
//Features: Sends messages about the combat to text log	   //
//Group: SENG 301 Group 16			            		   //
/////////////////////////////////////////////////////////////

package managers;

//////
//CombatManager
//Purpose: Controls the combat functions of the game (calculations, dice rolls, etc)
//Limit: ?
//////
import gameStates.GameScreenAssets;

import java.util.LinkedList;
import java.util.Random;

import monsterRelated.BasicMonster;
import monsterRelated.Entity;
import playerRelated.Player;

public class CombatManager {
	
	public static boolean battleHappening = false;
	private Player player;//Will use later for when the monster moves on its own.
	private static LinkedList <BasicMonster> monsterList = new LinkedList<BasicMonster>();
	
	public void setPlayerRef (Player player){this.player = player;}
	public static void setMonsterList(LinkedList <BasicMonster> monsters){monsterList = monsters;}
	
	
	public static String attackLoop(Player player, int criticalHitLimit, int missFactor, int monsterX, int monsterY ){
		battleHappening = true;
		if (getMonsterRef(monsterX, monsterY) == null)
		{
			GameScreenAssets.statusUpdate = "You are attacking a ghost! Oh no! Run away before it gets you!";
			battleHappening = false;
			return "Error! No Monster Found to Fight";
		}
		else
		{
			BasicMonster currentFoe = getMonsterRef(monsterX,monsterY);
		//	while (currentFoe.search("P")&&currentFoe.getAlive()&&player.getAlive()){
				actualCombat(currentFoe, player, criticalHitLimit, missFactor);
				//delay();
			//}
					
		}
		return null;
	}
	
	
	//Add delay
	private static void delay(){for (double i = 0; i < 10000000; i++);}
	
	
	private static void actualCombat(BasicMonster currentFoe, Player player, int criticalHitLimit, int missFactor){
		int attack = generateAttack(criticalHitLimit);
		if (attack <= missFactor){
			GameScreenAssets.queueTextLog.addFirst("You missed!");
			//delay();
			attack = generateAttack(currentFoe.damageLimit);
			player.subtractHealth(attack);	
			GameScreenAssets.queueTextLog.addFirst("Monster attacks back! Damage Done: "+attack);
			}
		
		else if (attack > missFactor && attack <= criticalHitLimit-missFactor)
		{
		
		GameScreenAssets.queueTextLog.addFirst("Average Hit: "+attack); 
		currentFoe.subtractHealth(attack);
			if (currentFoe.getHealthPoints() <= 0)
			{

				GameScreenAssets.queueTextLog.addFirst("You've Killed the monster!");
				player.addExperiencePoints(currentFoe.getExpPointGain());
				battleHappening = false;
				monsterList.remove(currentFoe);
				//delay();
				return;
			}
			//delay();
			attack = generateAttack(currentFoe.damageLimit);
			player.subtractHealth(attack);	
			GameScreenAssets.queueTextLog.addFirst("Monster attacks back! Damage Done: "+attack);
			//delay();
		}
		
		else 
		{
		currentFoe.subtractHealth(attack);	
		GameScreenAssets.queueTextLog.addFirst("Critical Hit: "+attack);
			if (currentFoe.getHealthPoints() <= 0)
			{
				GameScreenAssets.queueTextLog.addFirst("You've Killed the monster!");
				player.addExperiencePoints(currentFoe.getExpPointGain());
				battleHappening = false;
				monsterList.remove(currentFoe);
			//	delay();
				return;
			}
		//	delay();
			attack = generateAttack(currentFoe.damageLimit);
			player.subtractHealth(attack);	
			GameScreenAssets.queueTextLog.addFirst("Monster attacks back! Damage Done: "+attack);
	//		delay();
		}
	}
	
	
	private static int generateAttack(int criticalHitLimit){
		Random gen = new Random();
		int attack = gen.nextInt(criticalHitLimit);
		return attack;
	}
	
	
	
	private static BasicMonster getMonsterRef(int x, int y){
		BasicMonster monsterFound = null;
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
