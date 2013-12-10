package model.structures;

import model.map.Resource;

public class Bed extends AbstractStructure {

	private static final long serialVersionUID = 4304144076281330676L;

	public Bed(int location, String name, Resource resourceUsed) {
		super(location, name, resourceUsed);
	}

	@Override public boolean providesBed() {
		return true;
	}
}
