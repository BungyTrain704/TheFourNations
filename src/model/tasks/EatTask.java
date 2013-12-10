package model.tasks;

import model.Civilization;
import model.map.Map;
import model.map.ResourceType;
import model.units.Unit;

public class EatTask extends Task {

	private static final long serialVersionUID = -1116613252893400073L;

	public EatTask(int work, int locWorker, int locTask, Map map, Unit unit) {
		super(work, locWorker, locTask, map);
	}

	@Override public void performAction() {
		int currentHungerLevel = unit.getHungerLevel();
		int maxHungerLevel = unit.getMAX_HUNGER_LEVEL();
		
		int amountRetrieved = Civilization.getInstance().pollResource(ResourceType.food, maxHungerLevel - currentHungerLevel );
		unit.setHungerLevel( unit.getHungerLevel() + amountRetrieved );
		this.isDone = true;
	}

}
