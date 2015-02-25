package mapRelated;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class BasicMap
{
	//Variables to store tiles of the map info.
	private TiledMap map;
	private boolean [][] collisionArray;

	//Variables to store general map info
	private final int widthByTiles = 35;
	private final int heightByTiles = 16;
	
	
	public BasicMap(String tmxLocation) throws SlickException{
		map = new TiledMap(tmxLocation);//Instantiates the map based on loaded .tmx file
		collisionArray = new boolean[widthByTiles][heightByTiles];//Creates collision array
		initCollisionArray();//Initializes the Collision Array to get all blocked values of all the tiles on the map.
	}
	
	
	public boolean hasCollision (float x, float y)
	{
		int SIZE = 32;//Tiles are 32x32 px
		int xBlock = (int)x / SIZE; //Finds row of closest block to x,y
	    int yBlock = (int)y / SIZE; //finds column of closest block to x,y
	    
	    //Just a test conditional code.
//	    if (collisionArray[xBlock][yBlock])
//	    {
//	    	System.out.println("Player has been blocked");
//	    }
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
		map.render(0,0);//Draws map to screen starting at 0,0
	}
	
	
	public boolean[][] getCollisionArray(){return collisionArray;} // Returns collision array.

}
