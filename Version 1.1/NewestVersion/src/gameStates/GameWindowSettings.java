package gameStates;

import mapRelated.BasicMap;

public class GameWindowSettings {

	private static final int screenWidth = BasicMap.TILESIZE*BasicMap.widthByTiles;
	private static final int screenHeight = BasicMap.TILESIZE*BasicMap.heightByTiles;
	
	public static int getScreenWidth() {return screenWidth;}
	public static int getScreenHeight() {return screenHeight;}
	
	
	
}
