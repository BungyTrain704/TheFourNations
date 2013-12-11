package model.tasks;

import model.map.Map;
import model.units.Unit;

public class SleepTask extends Task {


	private static final long serialVersionUID = 3337353166802863444L;

	public SleepTask(int work, int locWorker, int locTask, Map map, Unit unit) {
		super(work, locWorker, locTask, map);
	}

	@Override public void performAction() {
		int maxEnergyLevel = unit.getMAX_ENERGY_LEVEL();
		
		unit.setEnergyLevel( maxEnergyLevel );
		this.isDone = true;
	}

}