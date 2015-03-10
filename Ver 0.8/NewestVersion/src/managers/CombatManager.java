package managers;

import gameStates.Game;

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
	
	
	public static void attackLoop(Player player, int criticalHitLimit, int missFactor, int monsterX, int monsterY ){
		battleHappening = true;
		if (getMonsterRef(monsterX, monsterY) == null)
		{
			Game.statusUpdate = "You are attacking a ghost! Oh no! Run away before it gets you!";
			battleHappening = false;
		}
		else
		{
			BasicMonster currentFoe = getMonsterRef(monsterX,monsterY);
		//	while (currentFoe.search("P")&&currentFoe.getAlive()&&player.getAlive()){
				actualCombat(currentFoe, player, criticalHitLimit, missFactor);
				//delay();
			//}
					
		}
	}
	
	private static void delay(){for (double i = 0; i < 10000000; i++);}
	
	//Add delay
	private static void actualCombat(BasicMonster currentFoe, Player player, int criticalHitLimit, int missFactor){
		int attack = generateAttack(criticalHitLimit);
		if (attack <= missFactor){
			Game.queueTextLog.addFirst("You missed!");
			//delay();
			}
		
		else if (attack > missFactor && attack <= criticalHitLimit-missFactor)
		{
		
		Game.queueTextLog.addFirst("Average Hit: "+attack); 
		currentFoe.subtractHealth(attack);
			if (currentFoe.getHealthPoints() <= 0)
			{

				Game.queueTextLog.addFirst("You've Killed the monster!");
				player.addExperiencePoints(currentFoe.getExpPointGain());
				battleHappening = false;
				//delay();
				return;
			}
			//delay();
			attack = generateAttack(currentFoe.damageLimit);
			player.subtractHealth(attack);	
			Game.queueTextLog.addFirst("Monster attacks back! Damage Done: "+attack);
			//delay();
		}
		
		else 
		{
		currentFoe.subtractHealth(attack);	
		Game.queueTextLog.addFirst("Critical Hit: "+attack);
			if (currentFoe.getHealthPoints() <= 0)
			{
				Game.queueTextLog.addFirst("You've Killed the monster!");
				player.addExperiencePoints(currentFoe.getExpPointGain());
				battleHappening = false;
			//	delay();
				return;
			}
		//	delay();
			attack = generateAttack(currentFoe.damageLimit);
			player.subtractHealth(attack);	
			Game.queueTextLog.addFirst("Monster attacks back! Damage Done: "+attack);
	//		delay();
		}
	}
	
	
	private static int generateAttack(int criticalHitLimit){
		Random gen = new Random();
		int attack = gen.nextInt(criticalHitLimit);
		return attack;
	}
	
	//Might have to make public for testing.
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
