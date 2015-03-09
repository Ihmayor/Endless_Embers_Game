package mapRelated;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class BasicMap
{
	private TiledMap map;
	private char [][] mapArray;

	public static final int widthByTiles = 35;
	public static final int heightByTiles = 16;
	public static final int TILESIZE = 32;
	
	
	public BasicMap(String tmxLocation) throws SlickException{
		map = new TiledMap(tmxLocation);
		mapArray = new char[widthByTiles][heightByTiles];
		initMapArray();
	}
	
	public boolean hasCollision (float x, float y)
	{
		int xBlock = (int)x / TILESIZE;
	    int yBlock = (int)y / TILESIZE;
	    
	    //Just a test conditional code.
	    if (mapArray[xBlock][yBlock] == 'B')
	    	return true;
	    
	    else
	    	return false;
	    
	}
		
	public void render()
	{
		map.render(0,0);
	}
	
	public boolean isStairs (float x, float y)
	{
		int xStairs = (int)x / TILESIZE;
	    int yStairs = (int)y / TILESIZE;
	    
	    //Just a test conditional code.
	    if (mapArray[xStairs][yStairs] == 'S')
	    {
	    	System.out.println("Player has stepped on the stairs!");
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}
	
	private void initMapArray (){
		String value;
		for (int c = 0; c < widthByTiles; c++){
			for (int r = 0; r < heightByTiles; r ++)
			{
				int tileID = map.getTileId(c, r, 0);
				if (r == 0 & c == 0)
				{
				System.out.println(tileID);
				}
                value = map.getTileProperty(tileID, "blocked", "false");
                if ("true".equals(value))
                {
                    mapArray[c][r] = 'B';
                }
                value = map.getTileProperty(tileID, "stairs", "false");
                if ("true".equals(value))
                {
                	mapArray[c][r] = 'S';
                }
			}
		}
	}
}
