package monsterRelated;

import mapRelated.BasicMap;

public class PrintingTests {
	
	///THIS IS A TERRIBLE TERRBLE HABIT AND BAD PRACTICE WITH CODING
	//BUT I LIKE PRINTING ALL VALUES IN ORDER TO NARROW DOWN THE PROBLEM OF THINGS
	//So yeah....
	
	
	public static void printEntityArray (String[][] entityArray){
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		for (int i = 0; i < BasicMap.heightByTiles; i++)
		{
		
			for (int c = 0; c < BasicMap.widthByTiles; c++){

				System.out.print("|");
				System.out.print(entityArray[c][i]);
				System.out.print("|");
			}	
			System.out.println("");
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
			
		}
		
	}

}
