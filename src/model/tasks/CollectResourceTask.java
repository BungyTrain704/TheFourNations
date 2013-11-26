package model.tasks;



import java.util.ArrayList;

import model.Civilization;
import model.map.Map;
import model.map.Resource;
import model.map.Terrain;



public class CollectResourceTask extends Task{
	
	public CollectResourceTask(int work, int locWorker, int locTask, Map map) {
		super(work, locWorker, locTask, map);
	}

	@Override
	public void performAction() {
		Map m = Civilization.getInstance().getMap();
		Resource res = m.getCell(super.getTaskLocation()).getResource();
		m.getCell(super.getTaskLocation()).removeResource();
		
		ArrayList<Integer> storedCells = new ArrayList<Integer>();
		
		// Find stockpiles on the map
		for( int i = 0; i < m.getMapSize(); i++ ) {
			if( m.getCell(i).getTerrain() == Terrain.stockpile ) {
				storedCells.add( i );
			}
		}
		
		// TODO: Implement smarter pathing - i.e: Unit moves to closest stockpile, not just the first one found
		int loc = storedCells.get(0);
		StoreResourceTask srt = new StoreResourceTask(1, loc, loc, m, res );
		this.unit.setCurrentTask(srt);
	}
	


}
