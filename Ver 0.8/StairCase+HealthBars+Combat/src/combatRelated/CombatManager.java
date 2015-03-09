package combatRelated;

import gameStates.Game;

import java.util.LinkedList;
import java.util.Random;

import monsterRelated.BasicMonster;
import monsterRelated.Creature;
import playerRelated.Player;

public class CombatManager {
	
	public static boolean battleHappening = false;
	private Player player;
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
		System.out.println("Average Hit: "+attack);
		currentFoe.subtractHealth(attack);
			if (currentFoe.getHealthPoints() <= 0)
			{

				Game.queueTextLog.addFirst("You've Killed the monster!");
				System.out.println("You've Killed the Monster!");
				int testValue = 10000;
				player.addExperiencePoints(currentFoe.getExpPointGain());
				//delay();
				return;
			}
			//delay();
			attack = generateAttack(currentFoe.damageLimit);
			player.subtractHealth(attack);	
			Game.queueTextLog.addFirst("Monster attacks! Damage Done: "+attack);
			System.out.println("Monster has Attacked!");
			//delay();
		}
		
		else 
		{
		currentFoe.subtractHealth(attack);	
		System.out.println("Critical Hit against monster! Wow! Much Strength! Such Cool!"+attack);
		Game.queueTextLog.addFirst("Wow! Much Strength! Such Cool!"+attack);
			if (currentFoe.getHealthPoints() <= 0)
			{
				Game.queueTextLog.addFirst("You've Killed the monster!");
				System.out.println("You've Killed the Monster!");
				player.addExperiencePoints(currentFoe.getExpPointGain());
			//	delay();
				return;
			}
		//	delay();
			attack = generateAttack(currentFoe.damageLimit);
			player.subtractHealth(attack);	
			Game.queueTextLog.addFirst("Monster attacks! Damage Done: "+attack);
			System.out.println("Monster has Attacked!");
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
