package model.map;
import model.Civilization;

public class Map {

	public static void main(String[] args) {
		Map board = new Map();
		System.out.print(board.toString());
	}

	private int rows = 30;
	private int cols = 70;
	private int waterBorder = rows / 10;

	private Cell[][] map;

	public Map() {

		map = new Cell[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				map[i][j] = new Cell();
			}
		}
		setTerrain();
		generateWater();

	}

	public String toString() {
		String mapString = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (map[i][j].hasUnit())
					mapString += "U";
				else if(map[i][j].hasStructure())
					mapString += "S";
				else if (map[i][j].hasResource()) {
					mapString += map[i][j].getResource().toString();
				} else {
					mapString += map[i][j].getTerrain().toString();
				}
			}
			mapString += "\n";
		}
		return mapString;
	}

	private void setTerrain() {
		int average;
		for (int i = 1; i < rows - 1; i++) {
			for (int j = 1; j < cols - 1; j++) {
				average = 0;
				for (int m = i - 1; m <= i + 1; m++) {
					for (int n = j - 1; n <= j + 1; n++) {
						average += map[i][j].getAverage();
					}
				}
				map[i][j].setAverage(average / 9);
			}
		}

		for (int i = 1; i < rows - 1; i++) {
			for (int j = 1; j < cols - 1; j++) {
				int a = map[i][j].getAverage();
				if (a < 10) {
					map[i][j].setResource(Resource.tree);
				} else if (290 < a) {
					map[i][j].setResource(Resource.stone);
				}
			}
		}
	}

	public int getMapSize() {
		return this.cols * this.rows;
	}
	
	private void generateWater() {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i < waterBorder || j < waterBorder || i > (rows - waterBorder - 1) || j > (cols - waterBorder - 1)) {
					map[i][j].setTerrain(Terrain.water);
				}
			}
		}

		for (int k = waterBorder; k < rows * .75; k++) {
			for (int i = waterBorder - 1; i <= rows - waterBorder; i++) {
				for (int j = waterBorder - 1; j <= cols - waterBorder; j++) {
					if (map[i][j].getTerrain().equals(Terrain.water)
							&& map[i][j].getAverage() < 200) {
						if (j == k - 1) {
							map[i][j  + 1].setTerrain(Terrain.water);
							map[i][j + 1].removeResource();
						}
						if (j == cols - k) {
							map[i][j - 1].setTerrain(Terrain.water);
							map[i][j - 1].removeResource();
						}
						if (i == k - 1) {
							map[i + 1][j].setTerrain(Terrain.water);
							map[i + 1][j].removeResource();
						}
						if (i == rows - k) {
							map[i - 1][j].setTerrain(Terrain.water);
							map[i - 1][j].removeResource();
						}
					}
				}
			}
		}

		for (int i = 1; i < rows - 1; i++) {
			for (int j = 1; j < cols - 1; j++)
				if (map[i + 1][j].getTerrain().equals(Terrain.water)
						&& map[i - 1][j].getTerrain().equals(Terrain.water)
						&& map[i][j + 1].getTerrain().equals(Terrain.water)
						&& map[i][j - 1].getTerrain().equals(Terrain.water)) {
					map[i][j].setTerrain(Terrain.water);
					map[i][j].removeResource();
				}
		}
	}
	
	public Cell getCell(int location)
	{
		return map[location/cols][location%cols];
	}
	
	public Cell[][] getMapArray() {
		return map;
	}
	
    public void buildRoom(int x, int y, int q, int r, Terrain roomType) {
    	
    	int startRow, startCol, endRow, endCol;
    	
    	
    	if (x <= q && y <= r) {
    		startRow = x;
    		startCol = y;
    		endRow = q;
    		endCol = r;
    	} else {
    		startRow = q;
    		startCol = r;
    		endRow = x;
    		endCol = y;
    	}
    	
    	for(int i = startRow; i <= endRow; i++) {
    		for (int j = startCol; j <= endCol; j++) {
//    			if (Civilization.getInstance().getMap().getMapArray()[i][j].getTerrain().equals(Terrain.water)) {
//    				System.out.println("You cannot build a room here on water.");
//    				return;
//    			}
    			if (Civilization.getInstance().getMap().getMapArray()[i][j].hasResource()) {
    				Civilization.getInstance().getMap().getMapArray()[i][j].removeResource();
    			}
    		}
    	}
    		
    	for(int i = startRow; i <= endRow; i++) {
        		for (int j = startCol; j <= endCol; j++) {
        			Civilization.getInstance().getMap().getMapArray()[i][j].setTerrain(roomType);
        		}
    	}	
    }



}
