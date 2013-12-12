package model.structures;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import model.map.Resource;
import model.map.ResourceType;
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
	private int location, amountOfResourceUsed;
	private ResourceType resourceUsed;
	private Set<Terrain> validTerrainTypes;
	private String name;
	
	/**
	 * Creates a structure with a specified name and location.
	 * @param location
	 * @param name
	 */
	public AbstractStructure(int location, int amountOfResourceUsed, String name, ResourceType resourceUsed, Terrain... validTerrainTypes) {
		this.location = location;
		this.amountOfResourceUsed = amountOfResourceUsed;
		this.name = name;
		this.resourceUsed = resourceUsed;
		this.validTerrainTypes = new HashSet<>( Arrays.asList( validTerrainTypes ) );
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
	
	public boolean providesDrink() {
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
	public ResourceType getResourceTypeUsed() {
		return resourceUsed;
	}
	
	public int getAmountOfResourceUsed() {
		return this.amountOfResourceUsed;
	}

	/**
	 * @param resourceUsed the resourceUsed to set
	 */
	public void setResourceTypeUsed(ResourceType resourceUsed) {
		this.resourceUsed = resourceUsed;
	}
	
	public Set<Terrain> getValidTerrainTypes() {
		return this.validTerrainTypes;
	}
}
