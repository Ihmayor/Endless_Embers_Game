package gameStates;

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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import combatRelated.CombatManager;

import playerRelated.Player;

import java.util.LinkedList;

public class Game extends BasicGameState {

	private StateBasedGame game;
	private BasicMap floorOne;

	private Player player;
	private String[][] entityArray;
	private BasicMonster monster;
	
	public static String statusUpdate;
	private LinkedList <BasicMonster> monsterList = new LinkedList<BasicMonster>();
	
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
		floorOne.render();
		monster.render(g);
		player.render(g);
		g.setColor(Color.white);
	    g.drawString(statusUpdate, 100, 490);
	    g.drawString("Press Q to quit", 700, 490);
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
	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
		long tmp = System.currentTimeMillis();
		prevTime = tmp;
		player.update(counter);
		
		//Prevent Monster from fleeing
		if (CombatManager.battleHappening == false){
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
