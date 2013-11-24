import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The unit is the basic entity of the game. It it the superclass to all other autonomous units within the game.
 * The unit performs tasks by pulling them from a global job queue and works on them until other needs otherwise present
 * themselves.
 * 
 * @author Christopher Chapline, James Fagan, Emily Leones, Michelle Yung
 *
 */
public abstract class Unit {
	
	private String name;
	private HashMap<UnitSkill, Integer> skills;
	private int energyLevel, hungerLevel;
	private final int MAX_HUNGER_LEVEL, MAX_ENERGY_LEVEL;
	private Task currentTask;
	private Queue<Integer> movementQueue;
	private boolean currentlyWorking = false;
	private int location;
	private final int COLS; //TODO: figure out how to initialize
	
	/**
	 * Creates a unit that has a specific set of skills
	 * @param name The name of the unit
	 * @param skills A map of skills
	 * @param maxEnergyLevel The maximum amount of energy that this unit can have. They start the game at 75% of their max energy level.
	 * 							They will need to sleep at 15% of their max energy level.
	 * @param maxHungerLevel The maximum amount of food energy that this unit can have. They start the game at 75% of their max hunger
	 * 							level. They will need to eat at 15% of their max energy level.
	 */
	public Unit( String name, HashMap<UnitSkill, Integer> skills, int maxEnergyLevel, int maxHungerLevel, int location, int cols ) {
		this.name = name;
		this.energyLevel = (int)(maxEnergyLevel * 0.75);
		this.hungerLevel = (int)(maxHungerLevel * 0.75);
		this.MAX_ENERGY_LEVEL = maxEnergyLevel;
		this.MAX_HUNGER_LEVEL = maxHungerLevel;
		this.skills = skills;
		this.movementQueue = new LinkedList<Integer>();
		this.location = location;
		this.COLS = cols;
	}
	
	/**
	 * Starts with the default skillset
	 * @param name The name of the unit
	 * @param maxEnergyLevel The maximum amount of energy that this unit can have. They start the game at 75% of their max energy level.
	 * 							They will need to sleep at 15% of their max energy level.
	 * @param maxHungerLevel The maximum amount of food energy that this unit can have. They start the game at 75% of their max hunger
	 * 							level. They will need to eat at 15% of their max energy level.
	 */
	public Unit( String name, int maxEnergyLevel, int maxHungerLevel, int cols) {
		this.name = name;
		this.energyLevel = (int)(maxEnergyLevel * 0.75);
		this.hungerLevel = (int)(maxHungerLevel * 0.75);
		this.MAX_ENERGY_LEVEL = maxEnergyLevel;
		this.MAX_HUNGER_LEVEL = maxHungerLevel;
		this.COLS = cols;
		this.movementQueue = new LinkedList<Integer>();

		//Initialize all skills at one
		this.skills = new HashMap<UnitSkill, Integer>();
		for( UnitSkill us : UnitSkill.values() )
			this.skills.put(us, 1);
	}
	
	
	/**
	 * Gives the unit a new task to work on
	 * @param task
	 */
	public void setCurrentTask( Task task ) {
		this.currentTask = task;
	}
	
	/**
	 * @return True if the current task that the unit is working on is finished or not
	 */
	public boolean hasFinishedCurrentTask() {
		return this.currentTask.getRemainingWorkRequirement() <= 0;
	}

	/**
	 * If the unit does not need sleep, does not need food, and currently has no task; then provide then they can be assigned a task
	 * @return True if the unit is not otherwise occupied
	 */
	public boolean needsNewTask() {
		return !needsToEat() && !needsToSleep() && this.currentTask == null;
	}
	
	/**
	 * If this unit's hunger level is below a certain point, then they need to eat
	 * @return True if the unit needs to sleep, false otherwise.
	 */
	public boolean needsToEat() {
		return this.hungerLevel <= (int) Math.floor( this.MAX_HUNGER_LEVEL * 0.15 );
	}
	
	/**
	 * If this unit's energy level is below a certain point, then they need to sleep
	 * @return True if the unit needs to sleep, false otherwise
	 */
	public boolean needsToSleep() {
		return this.energyLevel <= (int) Math.floor( this.MAX_ENERGY_LEVEL * 0.15 );
	}
	
	/**
	 * Generates a queue of movements for the unit to take to move to a desired location
	 * @param destination The location on the map that the unit will move to
	 */
	public void generatePath( int destination ) {
		this.movementQueue.clear();
		
		int tempLocation = location;
		while(tempLocation != destination)
		{
			if(tempLocation % COLS < destination % COLS)
				movementQueue.add(++tempLocation);
			else if(tempLocation % COLS > destination % COLS)
				movementQueue.add(--tempLocation);
			
			if(tempLocation/COLS < destination/COLS)
			{
				tempLocation += COLS;
				movementQueue.add(tempLocation);
			}
			else
			{
				tempLocation -= COLS;
				movementQueue.add(tempLocation);
			}	
		}	
		//TODO: WRITE PATHING 
	}
	
	/**
	 * Updates the unit's hunger and energy levels. Called every tick.
	 */
	public abstract void updateUnitCounters();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the skills
	 */
	public HashMap<UnitSkill, Integer> getSkills() {
		return skills;
	}

	/**
	 * @return the energyLevel
	 */
	public int getEnergyLevel() {
		return energyLevel;
	}

	/**
	 * @return the hungerLevel
	 */
	public int getHungerLevel() {
		return hungerLevel;
	}

	/**
	 * @return the currentTask
	 */
	public Task getCurrentTask() {
		return currentTask;
	}

	/**
	 * @return the currentlyWorking
	 */
	public boolean isCurrentlyWorking() {
		return currentlyWorking;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(HashMap<UnitSkill, Integer> skills) {
		this.skills = skills;
	}

	/**
	 * @param energyLevel the energyLevel to set
	 */
	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}

	/**
	 * @param hungerLevel the hungerLevel to set
	 */
	public void setHungerLevel(int hungerLevel) {
		this.hungerLevel = hungerLevel;
	}

	/**
	 * @param currentlyWorking the currentlyWorking to set
	 */
	public void setCurrentlyWorking(boolean currentlyWorking) {
		this.currentlyWorking = currentlyWorking;
	}
}
