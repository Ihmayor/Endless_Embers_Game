package gameStates;

import inputRelated.LoadingGame;
import inputRelated.SavingGame;

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

//////////////////////////////////////////////
//Game										//
//Purpose: Controls all elements in the game//
//Limit: Works only in SLICK2D API		    //
//////////////////////////////////////////////

public class GameScreen extends BasicGameState {

	//Used for quitting the game
	private GameContainer gc;
	private GameScreenAssets gameAssets;
	
	//Entity Stuff
	private Player player;
	private String[][] entityArray;
	
	//Handles Monsters on screen
	private MonsterManager monsters;
	private BasicMap currentMap;
	
	//Counter for delaying textlog and monsters
	private int textLogCounter = 0;
	private int monsterCounter = 0; //Must combine later

	//Volume
	private float volume = 1.0f;
	
	//Load Maps
	BasicMap floorOne;
	BasicMap floorTwo;
	BasicMap floorThree;
	BasicMap floorFour;
	BasicMap floorFive;
	BasicMap floorSix;
	BasicMap floorSeven;

	private LinkedList<BasicMap> totalLevels = new LinkedList<BasicMap>();
	
	
	
	//State ID
	public static final int ID = 1;

	private static boolean loadedGame = false;	
	
	public static void setLoadedGame (boolean value) {loadedGame = value;}
	
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame) throws SlickException{
		this.gc = gc;
		//Load Map Files
		floorOne = new BasicMap("res/map/floor1.tmx");
		floorTwo = new BasicMap("res/map/floor2.tmx");
		floorThree = new BasicMap("res/map/floor3.tmx");
		floorFour = new BasicMap("res/map/floor4.tmx");
		floorFive = new BasicMap("res/map/floor5.tmx");
		floorSix = new BasicMap("res/map/floor6.tmx");
		floorSeven = new BasicMap("res/map/floor7.tmx");
		
		totalLevels.add(floorSeven);
		totalLevels.add(floorSix);
		totalLevels.add(floorFive);
        totalLevels.add(floorFour);
		totalLevels.add(floorThree);
		totalLevels.add(floorTwo);
		totalLevels.add(floorOne);
		
		
	}
	
	
	@Override
	public void enter(GameContainer gc, StateBasedGame stateGame) throws SlickException {
		//Used for changing game states
		//setLoadedGame(true);
		this.gc = gc;
		System.out.println(loadedGame);
		
		//Add them to the Linked List, last level first.
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
		player = new Player(gc, stateGame,currentMap, 4*32, 5*32);
		
		//Place player's character onto map
		initEntityArray();
		
		monsters = new MonsterManager(currentMap);
		monsters.init(entityArray, currentMap);		
		CombatManager.setMonsterList(monsters.getMonsterList());
		
		
		GameScreenAssets.statusUpdate = "Game is Now In Session";
		gameAssets = new GameScreenAssets();
	
		if (loadedGame)
		{
			System.out.println("test");
			reInitEntityArray();
			currentMap = LoadingGame.initLoadingGame(gameAssets, currentMap, totalLevels, player, monsters, entityArray);
			if (gameAssets.getFloorLevel() == 1)
				SoundManager.changeSound("res/sound/Catacombs.wav");
			else 
				SoundManager.changeSound("res/sound/Tank Battle.wav");
		
		}
	
        
        //Draw Menu
		gameAssets.initMenu(gc, stateGame, ID);
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
				entityArray[i][c] = " ";
			}
		}
		entityArray[((Entity)player).getPosition()[0]/32][((Entity)player).getPosition()[1]/32] = player.getName();
	}
	
	
	private void reInitEntityArray(){
		String [][] newArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
		entityArray = newArray;
		for (int i = 0; i < BasicMap.widthByTiles; i++)
		{
			for (int c = 0; c < BasicMap.heightByTiles; c++)
			{
				entityArray[i][c] = " ";
			}
		}
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
	
	
	// Manages the keyboard controls of the game
	@Override
	public void keyReleased (int key,char c){
		switch (key){
		case Input.KEY_Q:
				SavingGame.SaveGame(gameAssets, player, monsters);
				gc.exit();//Exits game.
			
			break;
		case Input.KEY_1:
			//Source of sound effect: https://www.freesound.org/people/JoelAudio/sounds/77611/
			SavingGame.SaveGame(gameAssets, player, monsters);
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
		//Always let the player move.
		player.update(monsterCounter);
		
		textLogCounter = gameAssets.updateTextLog(textLogCounter);

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
			SoundManager.changeSound("res/sound/Tank Battle.wav");
			player.setOnStairs(false);
		}
	}
	
	

	//Returns ID of state for state manager that controls
	//Flow of game
	@Override
	public int getID() {
		return ID;
	}

}
