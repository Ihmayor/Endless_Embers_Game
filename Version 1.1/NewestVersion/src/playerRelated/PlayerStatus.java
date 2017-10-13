package playerRelated;

import gameStates.GameScreenAssets;

public class PlayerStatus {

	
	//Variables used for Combat and related aspects
	private int experiencePoints = 0;
	private int pointsNextLevel = 10;
	
	private int playerLevel = 1;
	private int criticalHitLimit= 30;
	private int missFactor = 10;

	private Player player;
		public PlayerStatus(Player p)
		{
			player =p;
		}
		
		public PlayerStatus(Player p,int newLevel, int newExp, int newHealth)
		{
			playerLevel = newLevel;
			experiencePoints = newExp;
			pointsNextLevel = 10*(2*(newLevel));
			player.setMaxHealthPoints (30 + 50*(newLevel-1));
			player.addHealthPoints(newHealth-player.getHealthPoints());
			criticalHitLimit = 30+5*(newLevel-1);	
			missFactor = 10 - 5*(newLevel-1);
		}
		
		public String addExperiencePoints(int points){
			if (points <0)
				return "Can't gain negative EXP";
			
			//Add points given
			experiencePoints += points;
			if (levelUp())
			{
				GameScreenAssets.queueTextLog.add("Woohoo! Player has leveled Up!");
		//		SoundManager.playSoundEffect("res/sound/SFX/Level Up Ding.wav");
				return "Player has leveled up";
			}
			return null;
		}
	
    	//Method used when the player levels up
		public boolean levelUp(){
			
			if (experiencePoints >= pointsNextLevel){
				
				playerLevel++;
				
				//Increase Maximum Health & Heal Up Completely
				
				int increaseMaxHealth = player.getMaxHealthPoints() + 50;
				player.setMaxHealthPoints(increaseMaxHealth);
				player.addHealthPoints(increaseMaxHealth- player.getHealthPoints());
				criticalHitLimit += 5;
				if (missFactor > 5)
					missFactor -= 1;

				//Decrease Experience Points used up
				//Increase amount needed to next level
				experiencePoints = experiencePoints-pointsNextLevel;
				pointsNextLevel *= 2;
				return true;
				}
			return false;
		}
		
		public int getCriticaHitLimit() {return criticalHitLimit;}
		public int getMissFactor() {return missFactor;}
		public int getPlayerLevel() {return playerLevel;}

		public int getExperiencePoints() {return experiencePoints;}
		public int getPointsNextLevel() {return pointsNextLevel;}

}
