package model.structures;
import model.map.ResourceType;
import model.map.Terrain;


public class Table extends AbstractStructure{

	private static final long serialVersionUID = 844649945126996075L;

	public Table(int location, String name, ResourceType resourceUsed ) {
		//TODO: Determine how much wood is used to build a table
		super(location, 1, name, resourceUsed, Terrain.kitchen);
	}
	
	@Override public boolean providesFood() {
		return true;
	}

}
