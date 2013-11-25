import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Coordinator for the management of settlers. This class is a singleton.
 * @author Christopher Chapline, James Fagan, Emily Leones, Michelle Yung
 *
 */
public class Civilization {

	private Queue<Task> taskQueue;
	private ArrayList<Unit> units;
	private ArrayList<AbstractStructure> structures;
	private static Civilization instance = new Civilization( 1 );
	private int currentFoodCount;
	private Map map;
	
	/**
	 * Creates a civilization with a specified number of starting units
	 * @param numberOfStartingUnits
	 */
	private Civilization( int numberOfStartingUnits ) {
		//TODO: Generate some units
		
		
		this.taskQueue = new LinkedList<Task>();
		this.units = new ArrayList<Unit>();
		for(int i = 0; i < numberOfStartingUnits; i++)
		{
			units.add(new BasicUnit("U", 100, 100, 70));
		}	
		this.structures = new ArrayList<AbstractStructure>();
		this.setCurrentFoodCount(0);
	}
	
	public void setMap(Map m)
	{
		this.map = m;
		for(Unit u: units)
		{
			u.setLocation(1085);
		}
		map.getCell(1085).removeResource();
		map.getCell(1085).setUnit(true);
	}
	
	/**
	 * Returns the singleton instance of this class
	 * @return The Civilization instance
	 */
	public static Civilization getInstance() {
		return instance;
	}
	
	/**
	 * Adds a task to the job queue. Jobs will be periodically
	 * pulled off the job queue by units in the civilization
	 * and the units will perform the jobs as long as they are able.
	 * 
	 * @param task The task to the add to the job queue
	 */
	public void addTaskToQueue( Task task ) {
		this.taskQueue.add( task );
	}
	
	/**
	 * Determines if there is an available task on the job queue
	 * @return True if there is a task on the job queue, false otherwise.
	 */
	public boolean isAvailableTask() {
		return ! this.taskQueue.isEmpty();
	}
	
	/**
	 * Pulls a job from the job queue and returns it.
	 * @return The next job at the top of the job queue. If no job is available, null is returned.
	 * @see Queue.#poll() Queue.poll()
	 */
	public Task getNextTask() {
		return this.taskQueue.poll();
	}
	
	/**
	 * Adds a unit to the list of maintined units.
	 * @param unit The unit to add to the list
	 */
	public void addUnit( Unit unit ) {
		this.units.add( unit );
	}
	
	/**
	 * Removes the specified unit from the list of units. Occurs in the instance of unit death.
	 * @param unit The unit to remove
	 * @return True if the unit was removed from the list.
	 */
	public boolean removeUnit( Unit unit ) {
		return this.units.remove(unit);
	}
	
	/**
	 * Adds a structure to the list of structures in this civilization.
	 * @param structure The structure to add
	 */
	public void addStructure( AbstractStructure structure ) {
		this.structures.add( structure );
	}

	/**
	 * Returns the global food count
	 * @return The amount of food shared across the civilization
	 */
	public int getCurrentFoodCount() {
		return currentFoodCount;
	}
	
	/**
	 * Attempts to remove a certain amount of food from the global food store. If
	 * there is enough food, the desired amount will be returned. Otherwise, the
	 * amount of food currently in the stores will be returned and the global food
	 * store will be set to 0.
	 * @param desiredAmountOfFood The amount of food you would like to remove from
	 * 								the global food store.
	 * @return The desired amount if it is available, or all available food if the desired
	 * 			amount could not be met.
	 */
	public int pollFoodStore( int desiredAmountOfFood ) {
		if( this.currentFoodCount >= desiredAmountOfFood ) {
			this.currentFoodCount -= desiredAmountOfFood;
			return desiredAmountOfFood;
		}
		else {
			int amountTaken = this.currentFoodCount;
			this.currentFoodCount = 0;
			return amountTaken;
		}
	}

	/**
	 * Sets the currentFood count to the specified level
	 * @param currentFoodCount The new food count
	 */
	public void setCurrentFoodCount(int currentFoodCount) {
		this.currentFoodCount = currentFoodCount;
	}
	
	/**
	 * Adds food to the global food count
	 * @param foodToAdd
	 */
	public void addToFoodCount( int foodToAdd ) {
		this.currentFoodCount += foodToAdd;
	}
	
	public void update()
	{
		for(Unit person: units)
		{
			Cell currentCell = map.getCell(person.getLocation());
			person.update();
			currentCell.setUnit(false);
			map.getCell(person.getLocation()).setUnit(true);
		}	
	}
}
