import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Coordinator for the management of settlers
 * @author Christopher Chapline, James Fagan, Emily Leones, Michelle Yung
 *
 */
public class Civilization {

	private Queue<Task> taskQueue;
	private ArrayList<Unit> units;
	private ArrayList<AbstractStructure> structures;
	private static Civilization instance = new Civilization( 4 );
	private int currentFoodCount;
	
	private Civilization( int numberOfStartingUnits ) {
		//TODO: Generate some units
		this.taskQueue = new LinkedList<Task>();
		this.units = new ArrayList<Unit>();
		this.structures = new ArrayList<AbstractStructure>();
		this.setCurrentFoodCount(0);
	}
	
	public static Civilization getInstance() {
		return instance;
	}
	
	public void addTaskToQueue( Task task ) {
		this.taskQueue.add( task );
	}
	
	public boolean isAvailableTask() {
		return ! this.taskQueue.isEmpty();
	}
	
	public Task getNextTask() {
		return this.taskQueue.poll();
	}
	
	public void addUnit( Unit unit ) {
		this.units.add( unit );
	}
	
	public void addStructure( AbstractStructure structure ) {
		this.structures.add( structure );
	}

	public int getCurrentFoodCount() {
		return currentFoodCount;
	}

	public void setCurrentFoodCount(int currentFoodCount) {
		this.currentFoodCount = currentFoodCount;
	}
}
