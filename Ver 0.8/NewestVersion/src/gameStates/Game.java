////////////////
///PERSONAL NOTE THAT THIS IS ALWAYS MY CODE WHEN SWITCHING TABS//////



package gameStates;

import java.util.LinkedList;

import managers.CombatManager;
import managers.MonsterManager;
import managers.SoundManager;
import mapRelated.BasicMap;
import monsterRelated.BasicMonster;
import monsterRelated.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import playerRelated.Player;

public class Game extends BasicGameState {

	private StateBasedGame game;

	//Entity Stuff
	private Player player;
	private String[][] entityArray;
	
	//Will soon replace with monster manager
	//For better management of monsters
	private BasicMonster monster;
	private MonsterManager monsters;
	
	//Floor Variables
	private int floorLevel = 1;
	private BasicMap currentMap;
	
	public static String statusUpdate;
	private static String statusBackLog1 = " ";
	private static String statusBackLog2 = " ";
	
	private float volume = 1.0f;
	
	//Linked lists for keeping track of the game's state.
	private LinkedList <BasicMonster> monsterList = new LinkedList<BasicMonster>();
	public static LinkedList <String> queueTextLog = new LinkedList<String>();
	private LinkedList<BasicMap> totalLevels = new LinkedList<BasicMap>();
	
	
	
//	private long prevTime;
	
	public static final int ID = 1;
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame) throws SlickException {
		
		//Used for changing game states
		this.game = stateGame;
	
		//prevTime = 0;
		
		//Load ALL  Maps
		//might move to map class???
		BasicMap floorOne = new BasicMap("res/map/floor1.tmx");
		BasicMap floorTwo = new BasicMap("res/map/floor2.tmx");
		BasicMap floorThree = new BasicMap("res/map/floor3.tmx");
		//Add them to the Linked List, last level first.
		totalLevels.add(floorThree);
		totalLevels.add(floorTwo);
		totalLevels.add(floorOne);
		//Get current map from the end.
		currentMap = totalLevels.removeLast();
		
		
		//Set up the creatures that appear initially
		player = new Player(gc, stateGame,currentMap, 4*32, 5*32);
		//monster = new BasicMonster(currentMap, monsterImage, 7*32, 11*32);
		initEntityArray();
		
		monsters = new MonsterManager(currentMap);
		monsters.init(entityArray);
		
		CombatManager.setMonsterList(monsters.getMonsterList());
		
		//Again Monster stuff will be moved.
		player.setEntityArray(monsters.getEntityArray());
		
		statusUpdate = "Game is Now In Session";
	}
	
	
	
	//Debating on keeping this here...
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
		entityArray[((Entity)player).getPosition()[0]/32][((Entity)player).getPosition()[1]/32] = player.getName();
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame stateGame, Graphics g)
			throws SlickException {
		
		//Render Map, Monsters, Player
		currentMap.render();
		monsters.render(g);
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
	case Input.KEY_1:
		SoundManager.playSoundEffect("res/sound/SFX/Sword Swing.wav");
		break;

	case Input.KEY_ESCAPE:
		//Open Menu with this key.
		break;
		
	case Input.KEY_M: 
		//Open up menu
		break;
		
	//Decrease volume
	case Input.KEY_A:
		volume -= 0.1f;
        if (volume < 0.0f)
           volume = 0.0f;
		SoundStore.get().setMusicVolume(volume);
		break;
		
	//Increase volume
	case Input.KEY_B:
		volume += 0.1f;
        if (volume > 1.0f)
           volume = 1.0f;
        SoundStore.get().setSoundVolume(volume);
		}
	}

	//Counters used to delay the text log and the movement of monsters
	int counter = 0; //Must combine later
	int counter2 = 0;//Must implement as private//Can possibly renamed Text log speed???
	
	
	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
		//Always let the player move.
		player.update(counter);
		
		//TextLog Code. Counter is the speed of which the text log updates itself
		//NOT A SCROLLING TEXT LOG.
		//Once we lose the 3rd status
		//We lose it forever!
		if (counter2 >= 200)
		{
			String temp = queueTextLog.pollLast();
			if (temp!= null){
				statusBackLog2 = statusBackLog1;
				statusBackLog1 = statusUpdate;
				statusUpdate = ""+temp;
			}	
			counter2 =0;
		}
		else
			counter2++;
		
		
		//Prevent Monster from fleeing
		if (CombatManager.battleHappening == false&&monsters.getMonsterList()!= null){
			monsters.update(player.getPosition(), counter);
			counter++;
			if (counter > 400)//Used to delay the monster's movement
				counter = 0;
			}
		
		//Load a new floor if the stairs are stepped on.
		if (player.getOnStairs()&&totalLevels.peekLast()!= null){
			currentMap = totalLevels.removeLast();
			player.setMap(currentMap);
			monsters.setMap(currentMap);
			floorLevel++;
			player.setOnStairs(false);
		}
	}
	
	@Override
	public int getID() {
		return ID;
	}

}
