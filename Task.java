/**
 * A task is an action performed on the map that makes some sort of meaningful change to the map
 * @author Christopher Chapline, James Fagan, Michelle Yung, Emile Leones
 *
 */
public abstract class Task 
{
	private int remainingWorkRequirement; //amount of work required for this task
	private int locationOfWorker; //location where unit needs to be to work on this task
	private int locationOfTask; //location where work is being done (i.e. where something is being built)
	private Map map;
	
	public Task(int work, int locWorker, int locTask, Map map )
	{
		remainingWorkRequirement = work;
		locationOfWorker = locWorker;
		locationOfTask = locTask;
		this.map = map;
	}
	
	public boolean decrement(int workDone)
	{
		remainingWorkRequirement -= workDone;
		if(remainingWorkRequirement <= 0)
		{
			performAction( this.map );
			return true;
		}
		return false;
	}
	
	public int getWorkerLocation()
	{
		return locationOfWorker;
	}
	
	public int getTaskLocation()
	{
		return locationOfTask;
	}
	
	public int getRemainingWorkRequirement() {
		return this.remainingWorkRequirement;
	}
	
	/**
	 * Performs the action for the call
	 * @param map The map that the task is being performed on
	 */
	public abstract void performAction( Map map ); 
}
