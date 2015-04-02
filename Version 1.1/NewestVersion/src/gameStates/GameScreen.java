package gameStates;

import inputRelated.LoadingGame;
import inputRelated.SavingGame;

import java.util.LinkedList;

import managers.CombatManager;
import managers.MonsterManager;
import managers.SoundManager;
import mapRelated.BasicMap;
import monsterRelated.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import playerRelated.Player;

//////////////////////////////////////////////
//Game										//
//Purpose: Controls all elements in the game//
//Limit: Works only in SLICK2D API		    //
//////////////////////////////////////////////

public class GameScreen extends BasicGameState {

	//Used for quitting the game
	private GameContainer gc;
	private GameScreenAssets gameAssets;
	private StateBasedGame sbg;
	
	//Entity Stuff
	private Player player;
	private String[][] entityArray;
	
	//Handles Monsters on screen
	private MonsterManager monsters;
	private BasicMap currentMap;
	
	//Counter for delaying Text-log and monsters
	private int textLogCounter = 0;
	private int monsterCounter = 0; //Must combine later

	//Maps Used Inside Game
	private BasicMap floorOne;
	private BasicMap floorTwo;
	private BasicMap floorThree;
	private BasicMap floorFour;
	private BasicMap floorFive;
	private BasicMap floorSix;
	private BasicMap floorSeven;

	//Sound Volume
	private float volume = 1.0f;
	
	//Collection of maps for all levels
	private LinkedList<BasicMap> totalLevels = new LinkedList<BasicMap>();
	
	
	//State ID
	public static final int ID = 1;

	//Win Game Boolean
	private static boolean winGame = false;
	
	//Loaded Saved Game Boolean
	private static boolean loadedGame = false;	
	
	//Set by the LoadingGame class
	//Also used by GameOver class
	public static void setLoadedGame(boolean value){loadedGame =value;}
	public static void setWin(boolean value) {winGame = value;}
	
	//Initialize variables that aren't reliant on it being a new game or an old game
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame) throws SlickException {
		this.gc = gc;
		this.sbg = stateGame;

		//Load ALL  Maps of game
		floorOne = new BasicMap("res/map/floor1.tmx");
		floorTwo = new BasicMap("res/map/floor2.tmx");
		floorThree = new BasicMap("res/map/floor3.tmx");
		floorFour = new BasicMap("res/map/floor4.tmx");
		floorFive = new BasicMap("res/map/floor5.tmx");
		floorSix = new BasicMap("res/map/floor6.tmx");
		floorSeven = new BasicMap("res/map/floor7.tmx");
	
		//Needed to init totalLevels for usage in loading games
		totalLevels.add(floorSeven);
		totalLevels.add(floorSix);
		totalLevels.add(floorFive);
        totalLevels.add(floorFour);
		totalLevels.add(floorThree);
		totalLevels.add(floorTwo);
		totalLevels.add(floorOne);
	}
	
	//This method is run when we enter the state not at the start of the program like the init method
	@Override 
	public void enter(GameContainer gc, StateBasedGame stateGame) throws SlickException{
		this.gc = gc;
		//If new game (from a previously lost game) then clear the totalLevels
		totalLevels.clear();
		
		//Add floors to Linked List, last level first.
		totalLevels.add(floorSeven);
		totalLevels.add(floorSix);
		totalLevels.add(floorFive);
        totalLevels.add(floorFour);
		totalLevels.add(floorThree);
		totalLevels.add(floorTwo);
		totalLevels.add(floorOne);
		
		//Get current map from the end.
		currentMap = totalLevels.removeLast();
		
		
		//Create player's character 
		player = new Player(gc, stateGame,currentMap, 4*BasicMap.TILESIZE, 5*BasicMap.TILESIZE);
		
		//Place player's character onto map
		initEntityArray();
		if (!loadedGame)
			{
			entityArray[((Entity)player).getPosition()[0]/BasicMap.TILESIZE][((Entity)player).getPosition()[1]/BasicMap.TILESIZE] = player.getName();
			}
		//Update Monster Manager with currentMap
		monsters = new MonsterManager(currentMap);
		//Spawn Monsters
		monsters.init(entityArray, currentMap);
		//Set Combat manager
		CombatManager.setMonsterList(monsters.getMonsterList());
		gameAssets = new GameScreenAssets();
		//Re-writes initialization defaults with read information from saved file
		if (loadedGame)
		{
			initEntityArray();
			currentMap = LoadingGame.initLoadingGame(gameAssets, currentMap, totalLevels, player, monsters, entityArray);
		}
        //Initializes the Menu
		gameAssets.initMenu(gc, stateGame, ID);
		SoundManager.changeFloorMusic(gameAssets.getFloorLevel());	
		//Initializes the player's entity Array
		//Which is important for detecting monsters
		player.setEntityArray(monsters.getEntityArray());
		}
	
	
	
	
	
	
	// Initializes the entity array, this will hold the information 
	// about the objects in the game (such as the player)
	private void initEntityArray (){
		String [][] newArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		entityArray = newArray;
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				newArray[i][c] = " ";
			}
		}
		this.entityArray = newArray;
	}
	
	
	// Draws the display of the game
	@Override
	public void render(GameContainer gc, StateBasedGame stateGame, Graphics graphics)
			throws SlickException {
		
		//Render Map, Monsters, Player, GameScreen Assets
		currentMap.render();
		monsters.render(graphics);
		player.render(graphics);
		gameAssets.render(graphics, player);
	}
	
	
	// Manages the keyboard controls of the game
	@Override
	public void keyReleased (int key,char c){
		switch (key){
		case Input.KEY_Q:
			SavingGame.SaveGame(gameAssets, player, monsters);
			gc.exit();//Exits game. 	
			break;
		case Input.KEY_M: 
		case Input.KEY_ESCAPE:
			//Slide Out Menu Hot Key Controls
			if (gameAssets.menu.getMenuOpen() == true)
				gameAssets.getMenu().popIn();
			else
				gameAssets.getMenu().popOut();
			break;
		//Decrease volume
		case Input.KEY_A:
			volume -= 0.1f;
			if (volume < 0.0f)
				volume = 0.0f;
			SoundStore.get().setSoundVolume(volume);
			SoundStore.get().setMusicVolume(volume);
			break;
		
		//Increase volume
		case Input.KEY_S:
			volume += 0.1f;
			if (volume > 1.0f)	
				volume = 1.0f;
			SoundStore.get().setSoundVolume(volume);
			SoundStore.get().setMusicVolume(volume);
		}
	}

	
	// Updates the state of the game (player location, combat, monster movement etc..)
	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
		textLogCounter = gameAssets.updateTextLog(textLogCounter);

		if (!player.getAlive())
		{
			GameScreenAssets.queueTextLog.add( "Your player be dead");
			//Change state of game to game over state.
			stateGame.enterState(GameOverScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
		
		//Always let the player move.
		player.update(monsterCounter);
		
		
		//Update monster movement
		if (monsters.getMonsterList()!= null){
			monsters.update(player.getPosition(), monsterCounter);
			monsterCounter++;
			if (monsterCounter > 400)//Used to delay the monster's movement
				monsterCounter = 0;
			}
		
		//Load a new floor if the stairs are stepped on.
		if (player.getOnStairs()&&totalLevels.peekLast()!= null){
			currentMap = totalLevels.removeLast();
			player.setMap(currentMap);
			monsters.clearMonsters();
			monsters.increaseFloorLevel();
			monsters.init(entityArray, currentMap);		
			CombatManager.setMonsterList(monsters.getMonsterList());
			player.setEntityArray(monsters.getEntityArray());
			gameAssets.increaseFloorLevel();
			SoundManager.changeFloorMusic(gameAssets.getFloorLevel());
			player.setOnStairs(false);
			}
		
		//Note to self this needs testing.	
		 if (winGame)
			{
			sbg.enterState(WinScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.white));
			}
		}
	
	
	

	//Returns ID of state for state manager that controls
	//Flow of game
	@Override
	public int getID() {
		return ID;
	}

}
