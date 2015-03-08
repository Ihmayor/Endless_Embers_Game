package gameStates;

import java.util.LinkedList;

import mapRelated.BasicMap;
import monsterRelated.BasicMonster;
import monsterRelated.Creature;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import playerRelated.Player;

import combatRelated.CombatManager;

public class Game extends BasicGameState {

	private StateBasedGame game;
	private BasicMap floorOne;

	private Player player;
	private String[][] entityArray;
	private BasicMonster monster;
	
	private int floorLevel = 1;
	public static String statusUpdate;
	private static String statusBackLog1 = " ";
	private static String statusBackLog2 = " ";
	private LinkedList <BasicMonster> monsterList = new LinkedList<BasicMonster>();
	public static LinkedList <String> queueTextLog = new LinkedList<String>();
	
	private long prevTime;
	
	public static final int ID = 1;
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame) throws SlickException {
		
		this.game = stateGame;
		prevTime = 0;
		floorOne = new BasicMap("res/map/singleTilePassageWay.tmx");	
		
		SpriteSheet monsterSheet = new SpriteSheet("res/player/dummySheet.png",32,32); 
		Image monsterImage = monsterSheet.getSubImage(0, 0);
		
		//Set up the creatures that appear initially
		player = new Player(gc, stateGame,floorOne, 4*32, 5*32);
		monster = new BasicMonster(floorOne, monsterImage, 7*32, 11*32);
		initEntityArray();
	//	sound = new SoundInGame("res/sound/Catacombs.wav");
		monsterList.add(monster);
		CombatManager.setMonsterList(monsterList);
		player.setEntityArray(entityArray);
		monster.setEntityArray(entityArray);
		
		statusUpdate = "Game is Now In Session";
	}
	
	
	

	private void initEntityArray (){
		String [][] newArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		entityArray = newArray;
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				entityArray[i][c] = " ";
			}
		}
		entityArray[((Creature)player).getPosition()[0]/32][((Creature)player).getPosition()[1]/32] = player.getName();
		entityArray[monster.getPosition()[0]/32][monster.getPosition()[1]/32] = monster.getName();
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame stateGame, Graphics g)
			throws SlickException {
		
		//Render Map, Monsters, Player
		floorOne.render();
		monster.render(g);
		player.render(g);
		
		//Render Text Log + Floor Status
		g.setColor(Color.white);
		g.drawString("Floor: "+floorLevel, 1000, 20);
	    g.drawString(statusUpdate, 600, 490);
	    g.drawString(statusBackLog1, 600, 470);
	    g.drawString(statusBackLog2, 600, 450);
	    g.drawString("Press Q to quit", 700, 20);

	    ///Draw Health Bar
	    g.drawString("HP", 60, 450);
	    g.drawString(""+player.getHealthPoints()+"/"+player.getMaxHealthPoints(), 400, 450);
	    Rectangle healthBar = new Rectangle(90, 450, 300 * player.getHealthPoints() / player.getMaxHealthPoints(), 20);
        GradientFill fillRed = new GradientFill(90, 0, new Color(255, 0, 0),
                                             460 + 300, 0, new Color(220,60, 0));

        g.setColor(Color.darkGray);
        g.fillRect(90, 450, 300, 20);
        g.fill(healthBar, fillRed); 
        
        
        //Draw Experience Bar
        g.setColor(Color.white);
	    g.drawString("EXP", 60, 480);
	    g.drawString(""+player.getExperiencePoints()+"/"+player.getPointsNextLevel(), 400, 480);
	    Rectangle expBar = new Rectangle(90, 480, 300*player.getExperiencePoints()/player.getPointsNextLevel(), 20);
        GradientFill fillGreen = new GradientFill(90, 0, new Color(90, 255, 20),
                                             480 + 300, 0, new Color(40, 180, 40));
        g.setColor(Color.darkGray);
        g.fillRect(90, 480, 300, 20);
        g.fill(expBar, fillGreen); 
        
	    
	}
	
	@Override
	public void keyReleased (int key,char c){
	switch (key){
	case Input.KEY_Q:
		GameContainer gc = game.getContainer();//Had to instantiate. I could've also made this another class variable.
		gc.exit();//Exits game. 	
		break;
		}	
	}

	int counter = 0;
	int counter2 = 0;
	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
		long tmp = System.currentTimeMillis();
		prevTime = tmp;
		player.update(counter);
		if (counter2 >= 200)
		{
			String temp = queueTextLog.pollLast();
			if (temp!= null){
				statusBackLog2 = statusBackLog1;
				statusBackLog1 = statusUpdate;
				statusUpdate = ""+temp;
			}	
			//queueTextLog.removeLast();
			counter2 =0;
		}
		else
			counter2++;
		//Prevent Monster from fleeing
		if (CombatManager.battleHappening == false&&monster.getAlive()){
			monster.update(player.getPosition(), counter++);
			if (counter > 500)//Used to delay the monster's movement
				counter = 0;
			}
	}
	
	@Override
	public int getID() {
		return ID;
	}

}
