
public enum Resource {
	
	tree("#"), stone("^");
	
	private String symbol;
	
	Resource(String res) {
		this.symbol = res;
	}
	
	public String getResource() {
		return this.symbol;
	}
}
