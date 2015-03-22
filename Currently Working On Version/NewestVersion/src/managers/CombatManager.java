package managers;

import gameStates.GameScreenAssets;

import java.util.LinkedList;
import java.util.Random;

import monsterRelated.BasicMonster;
import playerRelated.Player;

/////////////////////////////////////////////////////////////
//Combat  Manager                                          //
//Purpose: Deal with the combat inside the game		       //
//Limit: Monsters will only attack if players attack       // 
//Monsters will freeze movement if one monster is being    //
//attacked.  											   //
//Notes: Sends messages about the combat to text log	   //
/////////////////////////////////////////////////////////////

public class CombatManager {
	
	public static boolean battleHappening = false;
	private static LinkedList <BasicMonster> monsterList = new LinkedList<BasicMonster>();
	
	
	public static void setMonsterList(LinkedList <BasicMonster> monsters){monsterList = monsters;}
		
	// The monster will attack the player when it has been provoked	
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
    		actualCombat(currentFoe, player, criticalHitLimit, missFactor);
					
		}
		return null;
	}
	
	
	//Attacks Monster Accordingly to attack generate
	//TO DO: ADD SOUNDS
	private static void actualCombat(BasicMonster currentFoe, Player player, int criticalHitLimit, int missFactor){
	
		int attack = generateAttack(criticalHitLimit);//Generate Attack
		
		//Player Misses!
		if (attack <= missFactor){
			GameScreenAssets.queueTextLog.addFirst("You missed!");
			SoundManager.playSoundEffect("res/sound/SFX/Swoosh.wav");
			monsterAttacks (player,currentFoe);
			}
		
		
		//Average Hit
		else if (attack > missFactor && attack <= criticalHitLimit-missFactor)
		{
		
		GameScreenAssets.queueTextLog.addFirst("Average Hit: "+attack);
		SoundManager.playSoundEffect("res/sound/SFX/Sword Swing.wav");
		currentFoe.subtractHealth(attack);
		currentFoe.setIsAttacked(true);
			if (currentFoe.getHealthPoints() <= 0)
			{
				monsterDies(player, currentFoe);
				return;
			}
			monsterAttacks (player,currentFoe);
		}
		
		
		//Critical Hit
		else 
		{
		currentFoe.subtractHealth(attack);
		currentFoe.setIsAttacked(true);
		GameScreenAssets.queueTextLog.addFirst("Critical Hit: "+attack);
		SoundManager.playSoundEffect("res/sound/SFX/Sword Swing.wav");
			if (currentFoe.getHealthPoints() <= 0)
			{
				monsterDies(player, currentFoe);
				return;
			}
			monsterAttacks (player,currentFoe);
		}
	}
	
	
	// An attack roll is generated
	private static int generateAttack(int criticalHitLimit){
		Random gen = new Random();
		int attack = gen.nextInt(criticalHitLimit);
		return attack;
	}
	
	
	// Monster Attacks Back with randomly generated Number
	private static void monsterAttacks(Player player, BasicMonster currentFoe){
		int attack = generateAttack(currentFoe.damageLimit);
		player.subtractHealth(attack);	
		GameScreenAssets.queueTextLog.addFirst("Monster attacks back! Damage Done: "+attack);
		
		SoundManager.playSoundEffect("res/sound/SFX/Player Oof.wav");
	
	}
	
	
	// Handles what happens when a monster is killed
	private static void monsterDies(Player player, BasicMonster currentFoe){
		GameScreenAssets.queueTextLog.addFirst("You've Killed the monster!");
	//	SoundManager.playSoundEffect("res/sound/SFX/Monster Snarl.wav");
		player.addExperiencePoints(currentFoe.getExpPointGain());
		battleHappening = false;
		monsterList.remove(currentFoe);
	
	}
	
	
	// Retrieves monster reference from the monster list
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
