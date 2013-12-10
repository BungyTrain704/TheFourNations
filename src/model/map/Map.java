package model.map;

import java.util.ArrayList;

import model.Civilization;

public class Map {

	//TODO: Remove - used for testing purposes
	public static void main(String[] args) {
		Map board = new Map();
		board.buildRoom(12, 27, 14, 30, Terrain.kitchen);
		System.out.print(board.toString());
	}

	//TODO: Modularize
	private int rows = 50;
	private int cols = 50;
	private int waterBorder = rows / 10;

	private Cell[][] map;

	public Map() {
		this.map = MapGenerator.generateMap(rows, cols, waterBorder );
	}

	/**
	 * Generates a textual representation of the map
	 */
	public String toString() {
		StringBuilder toString = new StringBuilder();
		String lineSeparator = System.lineSeparator();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Cell c = map[i][j];
				
				if ( c.hasUnit() )
					toString.append("U");
				else if( c.hasStructure() )
					toString.append("S");
				else if ( c.hasResource() ) {
					toString.append( c.getResource() );
				} else {
					toString.append( c.getTerrain() );
				}
			}
			toString.append( lineSeparator );
		}
		return toString.toString();
	}
	
	/**
	 * Returns the number of rows in the map array
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Returns the number of columns in the map array
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Returns the number of tiles in the map
	 */
	public int getMapSize() {
		return this.cols * this.rows;
	}
	
	/**
	 * Returns the cell at the specified location. The are numbered as such:
	 * 
	 * <tt><p><br>0 1 2 3 4 5 6 7<br>
	 * +- - - - - - - -+<br>
	 * |. . . . . . . .|0<br>
	 * |. . . . . . . .|1<br>
	 * |. . . . . . . .|2<br>
	 * |. . . . . . . .|3<br>
	 * |. . . . . . . .|4<br>
	 * |. . . . . . . .|5<br>
	 *  +- - - - - - - -+<br></tt></p>
	 *  
	 *  <p>And the location of a specific cell is given by the product of it's row and column.
	 *  Moving backwards from a specified cell, a row is given by the <code>location / columns</code>
	 *  and the column is given by <code>location % columns</code>.
	 * 
	 * @param location The cell's location within the map
	 * @return The cell at the given location
	 */
	public Cell getCell(int location)
	{
		return map[location/cols][location%cols];
	}
	
	public Cell getCell(int row, int col) {
		return map[row][col];
	}
	
	/**
	 * Fills the tiles between specified points with room tiles.
	 * 
	 * <p>Example: If it where called as such <code>buildRoom( 2, 2, 4, 4, Terrain.KITCHEN)</code>
	 * on the map below where the period character (".") represents valid tiles:</p>
	 * <tt>
	 * +---------------------+<br>
	 * |.....................|<br>
	 * |.....................|<br>
	 * |.....................|<br>
	 * |.....................|<br>
	 * |.....................|<br>
	 * |.....................|<br>
	 * +---------------------+<br></tt>
	 * 
	 * <p>Would become:</p>
	 * <tt>
	 * +---------------------+<br>
	 * |.....................|<br>
	 * |.....................|<br>
	 * |..KKK................|<br>
	 * |..KKK................|<br>
	 * |..KKK................|<br>
	 * |.....................|<br>
	 * +---------------------+<br></tt>
	 * 
	 * @param topLeftX The x-coordinate of the top left point of the room
	 * @param topLeftY The y-coordinate of the top left point of the room
	 * @param bottomRightX The x-coordinate of the bottom right point of the room
	 * @param bottomRightY The y-coordinate of the bottom right point of the room
	 * @param roomType The terrain to fill the given area with
	 * 
	 */
//    public void buildRoom(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY, Terrain roomType) {
//    	
//    	int startRow, startCol, endRow, endCol;
//    	
//    	//Check bounds
//    	if (topLeftX <= bottomRightX && topLeftY <= bottomRightY) {
//    		startRow = topLeftX;
//    		startCol = topLeftY;
//    		endRow = bottomRightX;
//    		endCol = bottomRightY;
//    	} else {
//    		startRow = bottomRightX;
//    		startCol = bottomRightY;
//    		endRow = topLeftX;
//    		endCol = topLeftY;
//    	}
//    	
//    	//TODO: Check for water and resources within the given area
//    	ArrayList<Cell> resourceTiles = new ArrayList<Cell>();
//
//    			//TODO: Remove singleton references
////    			if (Civilization.getInstance().getMap().getMapArray()[i][j].getTerrain().equals(Terrain.water)) {
////    				System.out.println("You cannot build a room here on water.");
////    				return;
////    			}
//    			//Check for resources on the location
//
//    			
//    			//TODO: Remove singleton references
//			for(int i = startRow; i <= endRow; i++) {
//				for (int j = startCol; j <= endCol; j++) {
//					if( this.map[i][j].hasResource() )
//						Civilization.getInstance().addTaskToQueue(task);
//	}
//}
//
//
//
//			for(int i = startRow; i <= endRow; i++) {
//        		for (int j = startCol; j <= endCol; j++) {
//        			//TODO: Remove singleton reference
////        			Civilization.getInstance().getMap().getMapArray()[i][j].setTerrain(roomType);
//        			this.map[i][j].setTerrain(roomType);
//        		}
//    	}	
//    }
    
    public void buildRoom(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY, Terrain roomType) {
        
        int startRow, startCol, endRow, endCol;
        
        //Check bounds
        if (topLeftX <= bottomRightX && topLeftY <= bottomRightY) {
                startRow = topLeftX;
                startCol = topLeftY;
                endRow = bottomRightX;
                endCol = bottomRightY;
        } else {
                startRow = bottomRightX;
                startCol = bottomRightY;
                endRow = topLeftX;
                endCol = topLeftY;
        }
        
        //TODO: Check for water and resources within the given area
        
        //Change the tiles in the given area to the specified terrain type
        for(int i = startRow; i <= endRow; i++) {
                for (int j = startCol; j <= endCol; j++) {
                        //TODO: Remove singleton references
//                        if (Civilization.getInstance().getMap().getMapArray()[i][j].getTerrain().equals(Terrain.water)) {
//                                System.out.println("You cannot build a room here on water.");
//                                return;
//                        }
                        //Check for resources on the location
                        if( this.map[i][j].hasResource() ) this.map[i][j].removeResource();
                        
                        //TODO: Remove singleton references
//                        if (Civilization.getInstance().getMap().getMapArray()[i][j].hasResource()) {
//                                Civilization.getInstance().getMap().getMapArray()[i][j].removeResource();
//                        }
                }
        }
                
        for(int i = startRow; i <= endRow; i++) {
                    for (int j = startCol; j <= endCol; j++) {
                            //TODO: Remove singleton reference
//                            Civilization.getInstance().getMap().getMapArray()[i][j].setTerrain(roomType);
                            this.map[i][j].setTerrain(roomType);
                    }
        }        
}



}
