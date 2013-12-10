package model.structures;
import model.map.Resource;


public class Table extends AbstractStructure{

	private static final long serialVersionUID = 844649945126996075L;

	public Table(int location, String name, Resource resourceUsed ) {
		super(location, name, resourceUsed);
	}
	
	@Override public boolean providesFood() {
		return true;
	}

}
