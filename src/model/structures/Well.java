package model.structures;

import model.map.Resource;
import model.map.Terrain;

public class Well extends AbstractStructure {

	private static final long serialVersionUID = -2357473624902642107L;

	public Well(int location, String name, Resource resourceUsed) {
		super(location, name, resourceUsed, Terrain.plains);
	}
	
	@Override public boolean providesWater() {
		return true;
	}

}
