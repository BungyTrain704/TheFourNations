package model.units;

import model.Civilization;

/**
 * A standard unit with no special abilities or advancements. Concrete implementation
 * of it's abstract parent.
 * @author Christopher Chapline, James Fagan, Emily Leones, Michelle Yung
 *
 */
public class BasicUnit extends Unit {

	private static final long serialVersionUID = -1305684877971681010L;

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
