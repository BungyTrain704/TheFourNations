package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

import model.map.Map;
import model.map.ResourceType;
import model.structures.AbstractStructure;
import model.tasks.Task;
import model.units.Unit;

public class CivilizationState implements Serializable {

	private static final long serialVersionUID = 50960125111940890L;
	private Queue<Task> taskQueue;
	private ArrayList<Unit> units, unitsToKill;
	private ArrayList<AbstractStructure> structures;
	private HashMap<ResourceType, Integer> globalResourcePool;
	private Map map;

	public CivilizationState( Civilization civ ) {
		this( civ.getTaskQueue(), civ.getUnits(), civ.getUnitsToKill(), civ.getStructures(), civ.getGlobalResourcePool(), civ.getMap() );
	}

	public CivilizationState( Queue<Task> taskQueue, ArrayList<Unit> units, ArrayList<Unit> unitsToKill, 
			ArrayList<AbstractStructure> structures, HashMap<ResourceType, Integer> globalResourcePool, Map map ) {
		this.taskQueue = taskQueue;
		this.units = units;
		this.unitsToKill = unitsToKill;
		this.structures = structures;
		this.globalResourcePool = globalResourcePool;
		this.map = map;
	}

	/**
	 * @return the taskQueue
	 */
	public Queue<Task> getTaskQueue() {
		return taskQueue;
	}

	/**
	 * @return the units
	 */
	public ArrayList<Unit> getUnits() {
		return units;
	}

	/**
	 * @return the unitsToKill
	 */
	public ArrayList<Unit> getUnitsToKill() {
		return unitsToKill;
	}

	/**
	 * @return the structures
	 */
	public ArrayList<AbstractStructure> getStructures() {
		return structures;
	}

	/**
	 * @return the globalResourcePool
	 */
	public HashMap<ResourceType, Integer> getGlobalResourcePool() {
		return globalResourcePool;
	}

	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @param taskQueue the taskQueue to set
	 */
	public void setTaskQueue(Queue<Task> taskQueue) {
		this.taskQueue = taskQueue;
	}

	/**
	 * @param units the units to set
	 */
	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}

	/**
	 * @param unitsToKill the unitsToKill to set
	 */
	public void setUnitsToKill(ArrayList<Unit> unitsToKill) {
		this.unitsToKill = unitsToKill;
	}

	/**
	 * @param structures the structures to set
	 */
	public void setStructures(ArrayList<AbstractStructure> structures) {
		this.structures = structures;
	}

	/**
	 * @param globalResourcePool the globalResourcePool to set
	 */
	public void setGlobalResourcePool(
			HashMap<ResourceType, Integer> globalResourcePool) {
		this.globalResourcePool = globalResourcePool;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
	}

}
