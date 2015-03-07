package mapRelated;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class BasicMap
{
	private TiledMap map;
	private boolean [][] collisionArray;
	private boolean [][] stairsArray;

	public static final int widthByTiles = 35;
	public static final int heightByTiles = 16;
	public static final int TILESIZE = 32;
	
	
	public BasicMap(String tmxLocation) throws SlickException{
		map = new TiledMap(tmxLocation);
		collisionArray = new boolean[widthByTiles][heightByTiles];
		stairsArray = new boolean[widthByTiles][heightByTiles];
		initCollisionArray();
		initStairsArray();
	}
	
	public boolean hasCollision (float x, float y)
	{
		int xBlock = (int)x / TILESIZE;
	    int yBlock = (int)y / TILESIZE;
	    
	    //Just a test conditional code.
	    if (collisionArray[xBlock][yBlock])
	    {
	    	System.out.println("Player has been blocked");
	    }
	    return collisionArray[xBlock][yBlock];
	}

	
	//Initializes the Collision Array by reading the TMX files and reading all the tiles that have
		//The 'blocked' property set true
		//Note: In tiled you must manually set the tile to have the blocked property to true.
		private void initCollisionArray (){
			for (int c = 0; c < widthByTiles; c++){
				for (int r = 0; r < heightByTiles; r ++)
				{
					int tileID = map.getTileId(c, r, 0);
					if (r == 0 & c == 0)
					{
					System.out.println(tileID);
					}
	                String value = map.getTileProperty(tileID, "blocked", "false");
	                if ("true".equals(value))
	                {
	                    collisionArray[c][r] = true;
	                }
				}
			}
		}
		
	public void render()
	{
		map.render(0,0);
	}
	
	public boolean[][] getCollisionArray(){return collisionArray;}
	
	//DOWN ONWARDS ARE THE STAIRS METHODS I ADDED  *MATTEO*
	public boolean isStairs (float x, float y)
	{
		int xStairs = (int)x / TILESIZE;
	    int yStairs = (int)y / TILESIZE;
	    
	    //Just a test conditional code.
	    if (stairsArray[xStairs][yStairs])
	    {
	    	System.out.println("Player has stepped on the stairs!");
	    }
	    return stairsArray[xStairs][yStairs];
	}
	
	private void initStairsArray (){
		for (int c = 0; c < widthByTiles; c++){
			for (int r = 0; r < heightByTiles; r ++)
			{
				int tileID = map.getTileId(c, r, 0);
				if (r == 0 & c == 0)
				{
				System.out.println(tileID);
				}
                String value = map.getTileProperty(tileID, "stairs", "false");
                if ("true".equals(value))
                {
                    stairsArray[c][r] = true;
                }
			}
		}
	}
	
	public boolean[][] getStairsArray(){return stairsArray;}
}
