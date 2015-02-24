package gameStates;

import mapRelated.BasicMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import playerRelated.Player;

public class Game extends BasicGameState {

	private StateBasedGame game;
	private BasicMap floorOne;
	private Player player;
	
	public static final int ID = 1;
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame)
			throws SlickException {
	this.game = stateGame;
	floorOne = new BasicMap("res/map/floor1.tmx");	
	player = new Player(gc, stateGame,floorOne);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame stateGame, Graphics g)
			throws SlickException {
		floorOne.render();
		player.render(g);
		g.setColor(Color.white);
	    g.drawString("GAME IS NOW IN SESSION", 100, 490);
	    g.drawString("Press Q to quit", 400, 490);
	}
	
	public void keyReleased (int key, char c){
	switch (key){
	case Input.KEY_Q:
		GameContainer gc = game.getContainer();//Had to instantiate. I could've also made this another class variable.
		gc.exit();//Exits game. 
		
		break;
		}	
	}

	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int i)
			throws SlickException {
		player.update();
		
	}

	@Override
	public int getID() {
		return ID;
	}

}
