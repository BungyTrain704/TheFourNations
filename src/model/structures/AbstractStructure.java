package model.structures;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import model.map.Resource;
import model.map.Terrain;

/**
 * Superclass to all in-game structures. A structure is a building
 * that is located within a room on the map is provides some sort
 * of functionality to the civilization. This functionality could be
 * stat bonuses, resource storage, etc.
 * @author Christopher Chapline, James Fagan, Emily Leones, Michelle Yung
 *
 */
public abstract class AbstractStructure implements Serializable {

	private static final long serialVersionUID = -2955392819275955126L;
	private int location;
	private Resource resourceUsed;
	private Set<Terrain> validTerrainTypes;
	private String name;
	
	/**
	 * Creates a structure with a specified name and location.
	 * @param location
	 * @param name
	 */
	public AbstractStructure(int location, String name, Resource resourceUsed, Terrain... validTerrainTypes) {
		this.location = location;
		this.name = name;
		this.resourceUsed = resourceUsed;
		this.validTerrainTypes = new HashSet<>( this.validTerrainTypes );
	}

	/**
	 * @return the location
	 */
	public int getLocation() {
		return location;
	}
	
	public boolean providesFood() {
		return false;
	}
	
	public boolean providesBed() {
		return false;
	}
	
	public boolean providesWater() {
		return false;
	}
	
	public boolean isAContainer() {
		return false;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(int location) {
		this.location = location;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the resourceUsed
	 */
	public Resource getResourceUsed() {
		return resourceUsed;
	}

	/**
	 * @param resourceUsed the resourceUsed to set
	 */
	public void setResourceUsed(Resource resourceUsed) {
		this.resourceUsed = resourceUsed;
	}
	
	public Set<Terrain> getValidTerrainTypes() {
		return this.validTerrainTypes;
	}
}
