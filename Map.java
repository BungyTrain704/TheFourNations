public class Map {

	public static void main(String[] args) {
		Map board = new Map();
		System.out.print(board.toString());
	}

	private int rows = 30;
	private int cols = 70;
	private int waterBorder = 3;

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
				if (map[i][j].hasResource()) {
					mapString += map[i][j].getResource();
				} else {
					mapString += map[i][j].getTerrain();
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

	private void generateWater() {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// HAVE A GOOD NIGHT, MICHELLITO!! -MLE
				if (i < waterBorder || j < waterBorder || i > (rows - waterBorder - 1) || j > (cols - waterBorder - 1)) {
					map[i][j].setTerrain(Terrain.water);
				}
			}
		}

		for (int k = waterBorder; k < rows * .75; k++) {
			for (int i = waterBorder - 1; i <= rows - waterBorder; i++) {
				for (int j = waterBorder - 1; j <= cols - waterBorder; j++) {
					if (map[i][j].getTerrain().equals(
							(Terrain.water).getTerrain())
							&& map[i][j].getAverage() < 200) {
						if (j == k - 1) {
							map[i][j  + 1].setTerrain(Terrain.water);
							map[i][j + 1].removeResource();
//							map[i][j + 2].setTerrain(Terrain.plains);
						}
						if (j == cols - k) {
							map[i][j - 1].setTerrain(Terrain.water);
							map[i][j - 1].removeResource();
//							map[i][j - 2].setTerrain(Terrain.plains);
						}
						if (i == k - 1) {
							map[i + 1][j].setTerrain(Terrain.water);
							map[i + 1][j].removeResource();
//							map[i + 2][j].setTerrain(Terrain.plains);
						}
						if (i == rows - k) {
							map[i - 1][j].setTerrain(Terrain.water);
							map[i - 1][j].removeResource();
//							map[i - 2][j].setTerrain(Terrain.plains);
						}
					}
				}
			}
		}

		for (int i = 1; i < rows - 1; i++) {
			for (int j = 1; j < cols - 1; j++)
				if (map[i + 1][j].getTerrain().equals(
						(Terrain.water).getTerrain())
						&& map[i - 1][j].getTerrain().equals(
								(Terrain.water).getTerrain())
						&& map[i][j + 1].getTerrain().equals(
								(Terrain.water).getTerrain())
						&& map[i][j - 1].getTerrain().equals(
								(Terrain.water).getTerrain())) {
					map[i][j].setTerrain(Terrain.water);
					map[i][j].removeResource();
				}
		}
	}

}
