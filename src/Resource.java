/**
 * A resource is stored in a cell and can be collected and used
 * in tasks undertaken by the units
 * @author Emily Leones, Michelle Yung, Christopher Chapline, James Fagan
 *
 */
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
