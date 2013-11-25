/**
 * Builds a structure at a specified location
 * @author Christopher Chapline, James Fagan, Emily Leones, Michelle Yung
 *
 */
public class BuildStructureTask extends Task {

	private AbstractStructure structureToBuild;
	
	/**
	 * Creates a task that results in a structure being added to the map
	 * @param work The amount of work required to complete this task (number of game ticks)
	 * @param locWorker The location the worker working on this task will be
	 * @param locTask The location that the task will be located at
	 * @param map The map that this task takes place on
	 * @param structure The structure being built during this task
	 */
	public BuildStructureTask(int work, int locWorker, int locTask, Map map, AbstractStructure structure ) {
		super(work, locWorker, locTask, map);
		this.structureToBuild = structure;
	}

	/**
	 * {@inheritDoc}
	 * <p>Adds the structure to the map and to the civilization</p>
	 */
	@Override public void performAction() {
		super.map.getCell(super.locationOfTask).addStructure(this.structureToBuild);
		Civilization.getInstance().addStructure( this.structureToBuild );
	}
}
