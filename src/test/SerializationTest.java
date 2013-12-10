package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Civilization;
import model.CivilizationState;
import model.map.Map;
import model.map.Resource;
import model.map.ResourceType;
import model.map.Terrain;
import model.structures.Bed;
import model.structures.Well;
import model.tasks.BuildStructureTask;

import org.junit.Test;

public class SerializationTest {

	@Test public void testSerialization() {
		//Get civ
		Civilization civ = Civilization.getInstance();
		
		//Create serialization object
		CivilizationState civStateInit = new CivilizationState( civ );

		//Add some stuff to the civilization
		Bed b = new Bed( 0, "BED", Resource.tree );
		civ.addStructure( b );
		civ.setResourceAmount( ResourceType.wood, 5000 );
		civ.setMap( new Map() );
		civ.getMap().getCell(300).setTerrain(Terrain.plains); //Prevent DisallowedTaskException
		civ.addTaskToQueue( new BuildStructureTask(5, 299, 300, civ.getMap(), new Well( 300, "WELL", Resource.stone ) ) );

		//Assertions
		assertEquals( 5000, civ.getResourceAmount( ResourceType.wood ) );
		assertTrue( civ.getStructures().contains(b) );
		assertFalse( civ.getTaskQueue().isEmpty() );

		//Parse serialization
		civ.parseCivilizationState(civStateInit);

		//Assertions
		assertFalse( 5000 == civ.getResourceAmount(ResourceType.wood) );
		assertFalse( civ.getStructures().contains(b) );
		assertTrue( civ.getTaskQueue().isEmpty() );
		
	}
}
