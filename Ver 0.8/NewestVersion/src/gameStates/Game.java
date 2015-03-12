////////////////
///PERSONAL NOTE THAT THIS IS ALWAYS MY CODE WHEN SWITCHING TABS//////



package gameStates;

import java.util.LinkedList;

import managers.CombatManager;
import managers.MonsterManager;
import managers.SoundManager;
import mapRelated.BasicMap;
import monsterRelated.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import playerRelated.Player;

////////
//Game
//Purpose: Controls all elements in the game
//Limit: ?
////////
public class Game extends BasicGameState {

	//Used for quitting the game
	private GameContainer gc;
	private GameScreenAssets gameAssets;
	
	//Entity Stuff
	private Player player;
	private String[][] entityArray;
	
	//Handles Monsters on screen
	private MonsterManager monsters;
	
	private BasicMap currentMap;
	
	//Counters for delaying textlog and monsters
	private int textLogCounter = 0;//Must implement as private//Can possibly renamed Text log speed???

	//Volume
	private float volume = 1.0f;
	
	private LinkedList<BasicMap> totalLevels = new LinkedList<BasicMap>();
	
	
	//State ID
	public static final int ID = 1;

	// initializes the game
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame) throws SlickException {
		
		//Used for changing game states
		this.gc = gc;
		gameAssets = new GameScreenAssets();
		
		//Load ALL  Maps of game
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
		
		
		//Create player's character 
		player = new Player(gc, stateGame,currentMap, 4*32, 5*32);
		
		//Place player's character onto map
		initEntityArray();
		
		monsters = new MonsterManager(currentMap);
		monsters.init(entityArray);		
		CombatManager.setMonsterList(monsters.getMonsterList());
		
		player.setEntityArray(monsters.getEntityArray());
		
		GameScreenAssets.statusUpdate = "Game is Now In Session";
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
	
	// Draws the display of the game
	@Override
	public void render(GameContainer gc, StateBasedGame stateGame, Graphics g)
			throws SlickException {
		
		//Render Map, Monsters, Player, GameScreen Assets
		currentMap.render();
		monsters.render(g);
		player.render(g);
		gameAssets.render(g, player);
	}
	
	// Manages the controls of the game
	@Override
	public void keyReleased (int key,char c){
	switch (key){
	case Input.KEY_Q:
		gc.exit();//Exits game. 	
		break;
	case Input.KEY_1:
		//Source of sound effect: https://www.freesound.org/people/JoelAudio/sounds/77611/
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
        //NOTE: Sound Volume changes sound effects
        // Music Volume changes background music
//		SoundStore.get().setSoundVolume(volume);
		SoundStore.get().setMusicVolume(volume);
		break;
		
	//Increase volume
	case Input.KEY_B:
		volume += 0.1f;
        if (volume > 1.0f)
           volume = 1.0f;
//      SoundStore.get().setSoundVolume(volume);
        SoundStore.get().setMusicVolume(volume);

		}
	}

	//Counters used to delay the text log and the movement of monsters
	private int monsterCounter = 0; //Must combine later
	
	// Updates the state of the game (where the player is, etc)
	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
		//Always let the player move.
		player.update(monsterCounter);
		
		textLogCounter = gameAssets.updateTextLog(textLogCounter);
		
		//Prevent Monster from fleeing
		if (CombatManager.battleHappening == false&&monsters.getMonsterList()!= null){
			monsters.update(player.getPosition(), monsterCounter);
			monsterCounter++;
			if (monsterCounter > 400)//Used to delay the monster's movement
				monsterCounter = 0;
			}
		
		//Load a new floor if the stairs are stepped on.
		if (player.getOnStairs()&&totalLevels.peekLast()!= null){
			currentMap = totalLevels.removeLast();
			player.setMap(currentMap);
			monsters.setMap(currentMap);
			gameAssets.increaseFloorLevel();
			player.setOnStairs(false);
		}
	}
	
	// ?
	@Override
	public int getID() {
		return ID;
	}

}
