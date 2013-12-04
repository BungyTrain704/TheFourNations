package model.map;
import java.util.Random;

import model.structures.AbstractStructure;

public class Cell {
	
	private boolean hasUnit;
	private boolean isVisible;
	private boolean hasResource;
	private Terrain terrain;
	private Resource resource;
	private int average;
	private Random rand = new Random();
	private AbstractStructure item;
	
	public Cell() {
		terrain= Terrain.plains;
		hasResource = false;
		hasUnit = false;
		isVisible = false;
		average = rand.nextInt(300);
		item = null;
	}

	public void setTerrain(Terrain t) {
		terrain = t;
	}
	
	public Terrain getTerrain(){
		return terrain;
	}
	
	public void setUnit(boolean unitHere) {
		hasUnit = unitHere;
	}
	
	public boolean hasUnit(){
		return hasUnit;
	}
	
	public void setVisible() {
		isVisible = true;
	}
	
	public boolean visibility() {
		return isVisible;
	}
	
	public void setAverage(int avg) {
		average = avg;
	}
	
	public int getAverage() {
		return average;
	}
	
	public void setResource(Resource res) {
		resource = res;
		hasResource = true;
	}
	
	public Resource getResource() {
		return resource;
	}
	
	public boolean hasResource() {
		return hasResource;
	}
	
	public void removeResource() {
		resource = null;
		hasResource = false;
	}
	
	public boolean hasStructure() {
		return item != null;
	}
	
	public void addStructure(AbstractStructure built)	{
		item = built;
	}
}