package model.units;

import model.Civilization;

public class BasicUnit extends Unit{

	public BasicUnit(String name, int maxEnergyLevel, int maxHungerLevel,
			int cols) {
		super(name, maxEnergyLevel, maxHungerLevel, cols);
		
	}

	/**
	 * Lose 1 hunger per update
	 */
	@Override protected void updateUnitCounters() {
		this.hungerLevel -= 1;
		
		if( this.hungerLevel <= 0 ) Civilization.getInstance().sentenceToDeath( this );
	}

}
