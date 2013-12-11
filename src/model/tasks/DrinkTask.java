package model.tasks;

import model.map.Map;
import model.units.Unit;


public class DrinkTask extends Task {

	private static final long serialVersionUID = -3242173019984231381L;

	public DrinkTask(int work, int locWorker, int locTask, Map map, Unit unit) {
		super(work, locWorker, locTask, map);
	}

	@Override public void performAction() {
		int maxThirstLevel = unit.getMAX_THIRST_LEVEL();
		
		unit.setHungerLevel( maxThirstLevel);
		this.isDone = true;
	}

}
