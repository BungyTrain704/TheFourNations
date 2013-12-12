package model.structures;

import model.map.ResourceType;
import model.map.Terrain;

public class Well extends AbstractStructure {

	private static final long serialVersionUID = -2357473624902642107L;

	public Well(int location, String name, ResourceType resourceUsed) {
		//TODO: Determine how much stone is used to build a well
		super(location, 1, name, resourceUsed, Terrain.plains);
	}
	
	@Override public boolean providesDrink() {
		return true;
	}

}
