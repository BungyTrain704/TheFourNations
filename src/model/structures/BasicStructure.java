package model.structures;
import model.map.Resource;


public class BasicStructure extends AbstractStructure{

	public BasicStructure(int location, String name, Resource resourceUsed ) {
		super(location, name, resourceUsed);
	}
	
	//TODO: Create actual structures -_-
	@Override public boolean providesFood() {
		return true;
	}

}
