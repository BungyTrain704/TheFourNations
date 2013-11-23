public enum Terrain {

	plains(true, " "), water(false, "~");

	private boolean accessible;
	private String symbol;

	Terrain(boolean access, String terrain) {
		this.accessible = access;
		this.symbol = terrain;
	}

	public boolean isAccessible() {
		return accessible;
	}
	
	public String getTerrain() {
		return this.symbol;
	}

}
