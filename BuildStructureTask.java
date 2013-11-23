/**
 * Builds a structure at a specified location
 * @author Christopher Chapline, James Fagan, Emily Leones, Michelle Yung
 *
 */
public class BuildStructureTask extends Task {

	private AbstractStructure structureToBuild;
	
	public BuildStructureTask(int work, int locWorker, int locTask, Map map, AbstractStructure structure ) {
		super(work, locWorker, locTask, map);
		this.structureToBuild = structure;
	}

	/**
	 * {@inheritDoc}
	 * <p>Adds the structure to the map and to the civilization</p>
	 */
	@Override public void performAction() {
		//TODO: Add structure to the map
		Civilization.getInstance().addStructure( this.structureToBuild );
	}
}
