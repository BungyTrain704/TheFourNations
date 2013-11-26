package model.tasks;
import model.map.*;
import model.Civilization;

/**
 * Stores a resource in a stockpile
 * @author Christopher Chapline, James Fagan, Michelle Yung, Emily Leones
 *
 */
public class StoreResourceTask extends Task {

	private Resource resource;
	
	public StoreResourceTask(int work, int locWorker, int locTask, Map map, Resource resource) {
		super(work, locWorker, locTask, map);
		this.resource = resource;
	}

	@Override
	public void performAction() {
		Civilization.getInstance().storeResource( this.resource );
	}

}
